# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Build and Run

```bash
# Compile
mvn compile

# Package into JAR
mvn package

# Run via Maven
mvn exec:java -Dexec.mainClass="com.solvd.socialnetwork.Main"

# Run the JAR directly
java -cp target/socialnetwork-1.0-SNAPSHOT.jar com.solvd.socialnetwork.Main
```

There are no tests in this project.

## Dependencies

- **MySQL Connector/J 9.6.0** — JDBC driver for MySQL
- **Log4j2 2.25.3** — logging (currently only used in `PostDAO`)

## Database Setup

Requires a local MySQL instance. Credentials are hardcoded in `dao/mysqlimpl/ConnectionPool.java` — there is no external config file:
- URL: `jdbc:mysql://localhost:3306/socialnetwork`
- User: `root`
- Password: `00000000`
- Pool size: 5 connections

The database and all tables must be created manually before running. There are no schema or migration files in the repo.

**Known bug in `ConnectionPool`**: the URL constant is `"JDBC:mysql://..."` (uppercase) — the correct form is `"jdbc:mysql://..."` (lowercase). This will cause the driver not to be found at runtime.

## Architecture

### DAO Pattern with JDBC

All database access follows a three-layer structure:

1. **`dao/IBaseDAO<T>`** — generic interface with four CRUD methods: `getById`, `save`, `update`, `deleteById`.
2. **`dao/I*DAO`** — one interface per entity, most extending `IBaseDAO`. Exceptions:
   - `IFollowDAO` — does **not** extend `IBaseDAO`; uses `follow(Follow)`, `unfollow(Long, Long)`, `get(Long, Long)`.
   - `IPostHashtagDAO` — does **not** extend `IBaseDAO`; uses composite key methods `getById(Long, Long)`, `save(PostHashtag)`, `deleteById(Long, Long)`, `getAll()`.
   - `IHashtagDAO` adds `getAll()` and `getByName(String name)`.
   - `IPostDAO` adds `findByUserId(Long userId)`.
3. **`dao/mysqlimpl/*DAO`** — concrete implementations using raw JDBC `PreparedStatement`. All DAOs extend `AbstractMySQLDAO`, which provides `getConnection()` and `releaseConnection(Connection)` wrappers around the pool.

### Connection Pool

`dao/mysqlimpl/ConnectionPool.java` is a Singleton managing connections via `BlockingQueue`. All DAOs follow this pattern:

```java
Connection con = getConnection();
try (PreparedStatement stm = con.prepareStatement(sql)) {
    // use stm and ResultSet (via nested try-with-resources)
} catch (SQLException e) {
    e.printStackTrace();
} finally {
    releaseConnection(con);
}
```

The `Connection` itself is obtained outside try-with-resources (it must be returned to the pool, not closed). `PreparedStatement` and `ResultSet` use try-with-resources inside the outer try block.

### DAO Implementation Status

- **Fully implemented**: `UserDAO`, `StoryDAO`, `FriendshipDAO`, `ChatMemberDAO`, `PostHashtagDAO`, `HashtagDAO`, `FollowDAO`, `ChatDAO`, `LikeDAO`, `PostDAO`
- **Implemented with bugs** (see below): `GroupDAO`, `MessageDAO`
- **Missing — no implementation file**: `CommentDAO`, `GroupMemberDAO`

### Known Bugs in Existing Code

**`GroupDAO.update()`** (`dao/mysqlimpl/GroupDAO.java`): The SQL string has 6 `?` placeholders (`SET id=?, adminId=?, name=?, description=?, imageUrl=? WHERE id=?`) but only 5 `setX()` calls are made — `stm.setLong(6, group.getId())` for the WHERE clause is missing, causing `SQLException` at runtime.

**`MessageDAO.save()`** (`dao/mysqlimpl/MessageDAO.java`): The SQL inserts columns `(id, userId, mediaUrl, createDate, expireDate)` but the setter calls bind `chatId` (param 3), `content` (param 4), and `sentDate` (param 5) — a mismatch between the SQL column list and the actual values being bound.

When fixing or extending any DAO, audit all `ps.setX(index, value)` calls — indices must be sequential starting at 1, matching the `?` placeholders in the SQL string in order.
