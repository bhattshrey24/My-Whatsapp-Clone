package com.example.mywhatsappclone.Models;

import java.util.Map;
// Todo always remeber that names here should match exaclty in firestore or else it wont be able to convert it into custom object
public class OneToOneChatModel {
    Map<String,String> User1,User2,lastMessage;

    public OneToOneChatModel() {
    // for Firebase purpose
    }

    public OneToOneChatModel(Map<String, String> user1, Map<String, String> user2, Map<String, String> lastMessage) {
        User1 = user1;
        User2 = user2;
        this.lastMessage = lastMessage;
    }

    public void setUser1(Map<String, String> user1) {
        User1 = user1;
    }

    public void setUser2(Map<String, String> user2) {
        User2 = user2;
    }

    public void setLastMessage(Map<String, String> lastMessage) {
        this.lastMessage = lastMessage;
    }

    public Map<String, String> getUser1() {
        return User1;
    }

    public Map<String, String> getUser2() {
        return User2;
    }

    public Map<String, String> getLastMessage() {
        return lastMessage;
    }
}
