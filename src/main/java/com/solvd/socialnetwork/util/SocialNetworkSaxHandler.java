package com.solvd.socialnetwork.util;

import com.solvd.socialnetwork.model.*;
import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class SocialNetworkSaxHandler extends DefaultHandler {

    private final List<User> users = new ArrayList<>();
    private final List<Post> posts = new ArrayList<>();
    private final List<Hashtag> hashtags = new ArrayList<>();
    private final List<PostHashtag> postHashtags = new ArrayList<>();
    private final List<Friendship> friendships = new ArrayList<>();
    private final List<Follow> follows = new ArrayList<>();

    private boolean inUser, inPost, inHashtag, inPostHashtag, inFriendship, inFollow;

    private User currentUser;
    private Post currentPost;
    private Hashtag currentHashtag;
    private PostHashtag currentPostHashtag;
    private Friendship currentFriendship;
    private Follow currentFollow;

    private final StringBuilder value = new StringBuilder();

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) {
        value.setLength(0);
        switch (qName) {
            case "user":
                inUser = true;
                currentUser = new User();
                break;
            case "post":
                inPost = true;
                currentPost = new Post();
                break;
            case "hashtag":
                inHashtag = true;
                currentHashtag = new Hashtag();
                break;
            case "postHashtag":
                inPostHashtag = true;
                currentPostHashtag = new PostHashtag();
                break;
            case "friendship":
                inFriendship = true;
                currentFriendship = new Friendship();
                break;
            case "follow":
                inFollow = true;
                currentFollow = new Follow();
                break;
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) {
        value.append(ch, start, length);
    }

    @Override
    public void endElement(String uri, String localName, String qName) {
        String text = value.toString().trim();

        if (inUser) {
            switch (qName) {
                case "user":
                    users.add(currentUser);
                    inUser = false;
                    break;
                case "id":
                    currentUser.setId(Long.parseLong(text));
                    break;
                case "username":
                    currentUser.setUsername(text);
                    break;
                case "firstName":
                    currentUser.setFirstName(text);
                    break;
                case "lastName":
                    currentUser.setLastName(text);
                    break;
                case "email":
                    currentUser.setEmail(text);
                    break;
                case "password":
                    currentUser.setPassword(text);
                    break;
                case "profilePicture":
                    currentUser.setProfilePicture(text);
                    break;
                case "birthDate":
                    currentUser.setBirthDate(LocalDate.parse(text));
                    break;
                case "registerDate":
                    currentUser.setRegisterDate(LocalDateTime.parse(text));
                    break;
            }
        } else if (inPost) {
            switch (qName) {
                case "post":
                    posts.add(currentPost); inPost = false;
                    break;
                case "id":
                    currentPost.setId(Long.parseLong(text));
                    break;
                case "userId":
                    currentPost.setUserId(Long.parseLong(text));
                    break;
                case "content":
                    currentPost.setContent(text);
                    break;
                case "mediaType":
                    currentPost.setMediaType(text);
                    break;
                case "mediaUrl":
                    currentPost.setMediaUrl(text);
                    break;
                case "createdDate":
                    currentPost.setCreatedDate(LocalDateTime.parse(text));
                    break;
            }
        } else if (inHashtag) {
            switch (qName) {
                case "hashtag":
                    hashtags.add(currentHashtag);
                    inHashtag = false;
                    break;
                case "id":
                    currentHashtag.setId(Long.parseLong(text));
                    break;
                case "name":
                    currentHashtag.setName(text);
                    break;
            }
        } else if (inPostHashtag) {
            switch (qName) {
                case "postHashtag":
                    postHashtags.add(currentPostHashtag);
                    inPostHashtag = false;
                    break;
                case "postId":
                    currentPostHashtag.setPostId(Long.parseLong(text));
                    break;
                case "hashtagId":
                    currentPostHashtag.setHashtagId(Long.parseLong(text));
                    break;
            }
        } else if (inFriendship) {
            switch (qName) {
                case "friendship":
                    friendships.add(currentFriendship);
                    inFriendship = false;
                    break;
                case "id":
                    currentFriendship.setId(Long.parseLong(text));
                    break;
                case "userFromId":
                    currentFriendship.setUserFromId(Long.parseLong(text));
                    break;
                case "userToId":
                    currentFriendship.setUserToId(Long.parseLong(text));
                    break;
                case "requestDate":
                    currentFriendship.setRequestDate(LocalDateTime.parse(text));
                    break;
                case "approveDate":
                    currentFriendship.setApproveDate(LocalDateTime.parse(text));
                    break;
            }
        } else if (inFollow) {
            switch (qName) {
                case "follow":
                    follows.add(currentFollow);
                    inFollow = false;
                    break;
                case "followingId":
                    currentFollow.setFollowingId(Long.parseLong(text));
                    break;
                case "followedId":
                    currentFollow.setFollowedId(Long.parseLong(text));
                    break;
                case "createDate":
                    currentFollow.setCreateDate(LocalDateTime.parse(text));
                    break;
            }
        }
    }

    public List<User> getUsers() { return users; }
    public List<Post> getPosts() { return posts; }
    public List<Hashtag> getHashtags() { return hashtags; }
    public List<PostHashtag> getPostHashtags() { return postHashtags; }
    public List<Friendship> getFriendships() { return friendships; }
    public List<Follow> getFollows() { return follows; }
}
