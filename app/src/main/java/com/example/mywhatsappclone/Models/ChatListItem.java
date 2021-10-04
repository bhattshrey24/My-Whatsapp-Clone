package com.example.mywhatsappclone.Models;

import java.util.ArrayList;

public class ChatListItem { // combined i.e whether its one to one or group chat , one thing is for sure that it will have profilepic, username and lastmessage
    String name,profilePic,lastMessage;
    boolean isGroup;
    String id;
    public ChatListItem() {
    }

    public ChatListItem(String name, String lastMessage) {
        this.name = name;
        this.lastMessage = lastMessage;
    }

    public ChatListItem(String name, String profilePic, String lastMessage) {
        this.name = name;
        this.profilePic = profilePic;
        this.lastMessage = lastMessage;
    }

    public ChatListItem(String name, String profilePic, String lastMessage, boolean isGroup, String id) {
        this.name = name;
        this.profilePic = profilePic;
        this.lastMessage = lastMessage;
        this.isGroup = isGroup;
        this.id = id;
    }

    public void setIsGroup(boolean group) {
        isGroup = group;
    }

    public void setId(String id) {
        this.id = id;
    }

    public boolean getIsGroup() {
        return isGroup;
    }

    public String getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setProfilePic(String profilePic) {
        this.profilePic = profilePic;
    }

    public void setLastMessage(String lastMessage) {
        this.lastMessage = lastMessage;
    }

    public String getName() {
        return name;
    }

    public String getProfilePic() {
        return profilePic;
    }

    public String getLastMessage() {
        return lastMessage;
    }
}
