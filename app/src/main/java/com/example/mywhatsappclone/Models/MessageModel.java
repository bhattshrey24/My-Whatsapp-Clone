package com.example.mywhatsappclone.Models;

import java.sql.Timestamp;
import java.util.Comparator;

public class MessageModel  {
 String msg,senderId;
 long timeStamp;

    public MessageModel() {
    }

    public MessageModel(String msg, String senderId, long timeStamp) {
        this.msg = msg;
        this.senderId = senderId;
        this.timeStamp = timeStamp;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public void setSenderId(String senderId) {
        this.senderId = senderId;
    }

    public void setTimeStamp(long timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String getMsg() {
        return msg;
    }

    public String getSenderId() {
        return senderId;
    }

    public long getTimeStamp() {
        return timeStamp;
    }



}
