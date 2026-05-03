package com.solvd.socialnetwork.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.xml.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@XmlRootElement(name = "user")
@XmlAccessorType(XmlAccessType.FIELD)
public class User {

    @XmlAttribute(name = "id")
    @JsonProperty("id")
    private Long id;
    @XmlElement(name = "username")
    @JsonProperty("username")
    private String username;
    @XmlElement(name = "firstName")
    @JsonProperty("firstName")
    private String firstName;
    @JsonProperty("lastName")
    @XmlElement(name = "lastName")
    private String lastName;
    @XmlElement(name = "email")
    @JsonProperty("email")
    private String email;
    @XmlTransient
    @JsonIgnore
    private String password;
    @XmlElement(name = "profilePicture")
    @JsonIgnore
    private String profilePicture;
    @XmlTransient
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate birthDate;
    @XmlTransient
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime registerDate;

    public User () {}

    public User(Long id, String username, String firstName, String lastName, String email, String password, String profilePicture, LocalDate birthDate, LocalDateTime registerDate) {
        this.id = id;
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.profilePicture = profilePicture;
        this.birthDate = birthDate;
        this.registerDate = registerDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getRegisterDate() {
        return registerDate;
    }

    public void setRegisterDate(LocalDateTime registerDate) {
        this.registerDate = registerDate;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public String getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(String profilePicture) {
        this.profilePicture = profilePicture;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
