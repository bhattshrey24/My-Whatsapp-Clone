package com.example.mywhatsappclone.Models;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class GroupChatModel {
    Map<String,String> lastMessage;
    List<String> admins,members;
    String nameOfRoom,profilePicOfRoom,DescriptionOfRoom;

    public GroupChatModel() {
    }

    public GroupChatModel(Map<String, String> lastMessage, List<String> admins, List<String> members, String nameOfRoom, String profilePicOfRoom, String descriptionOfRoom) {
        this.lastMessage = lastMessage;
        this.admins = admins;
        this.members = members;
        this.nameOfRoom = nameOfRoom;
        this.profilePicOfRoom = profilePicOfRoom;
        DescriptionOfRoom = descriptionOfRoom;
    }

    public void setAdmins(List<String> admins) {
        this.admins = admins;
    }

    public void setMembers(List<String> members) {
        this.members = members;
    }

    public List<String> getAdmins() {
        return admins;
    }

    public List<String> getMembers() {
        return members;
    }

    public void setLastMessage(Map<String, String> lastMessage) {
        this.lastMessage = lastMessage;
    }

    public void setNameOfRoom(String nameOfRoom) {
        this.nameOfRoom = nameOfRoom;
    }

    public void setProfilePicOfRoom(String profilePicOfRoom) {
        this.profilePicOfRoom = profilePicOfRoom;
    }

    public void setDescriptionOfRoom(String descriptionOfRoom) {
        DescriptionOfRoom = descriptionOfRoom;
    }


    public Map<String, String> getLastMessage() {
        return lastMessage;
    }

    public String getNameOfRoom() {
        return nameOfRoom;
    }

    public String getProfilePicOfRoom() {
        return profilePicOfRoom;
    }

    public String getDescriptionOfRoom() {
        return DescriptionOfRoom;
    }
}
