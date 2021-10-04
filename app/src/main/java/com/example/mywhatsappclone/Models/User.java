package com.example.mywhatsappclone.Models;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

//Todo (Not Todo) this will just store the data of the user who is authenticated
// Remeber that for firebase to covert it to correct data , you have to name it same as you did in firestore i.e if in firebase you used userId then here also use userId and no other name
public class User {
    String nameOfUser,profilePicOfUser,userId;
    List<String> groupIds,oneToOneChatIds;
    //Map<String,>

    public User() {
    }

    public User(String nameOfUser, String profilePicOfUser, String userId, List<String> groupIds, List<String> oneToOneChatIds) {
        this.nameOfUser = nameOfUser;
        this.profilePicOfUser = profilePicOfUser;
        this.userId = userId;
        this.groupIds = groupIds;
        this.oneToOneChatIds = oneToOneChatIds;
    }

    public void setNameOfUser(String nameOfUser) {
        this.nameOfUser = nameOfUser;
    }

    public void setProfilePicOfUser(String profilePicOfUser) {
        this.profilePicOfUser = profilePicOfUser;
    }

    public void setUserId(String userId) { //I guess this is useless cause id is given bu firebase only
        this.userId = userId;
    }

    public void setGroupIds(List<String> groupIds) {
        this.groupIds = groupIds;
    }

    public void setOneToOneChatIds(List<String> oneToOneChatIds) {
        this.oneToOneChatIds = oneToOneChatIds;
    }

    public String getNameOfUser() {
        return nameOfUser;
    }

    public String getProfilePicOfUser() {
        return profilePicOfUser;
    }

    public String getUserId() {
        return userId;
    }

    public List<String> getGroupIds() {
        return groupIds;
    }

    public List<String> getOneToOneChatIds() {
        return oneToOneChatIds;
    }
}
