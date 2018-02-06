package com.example.saad_kamaal.bankingagent.models;

import com.example.saad_kamaal.bankingagent.adapters.ChatterBoxAdapter;

/**
 * Created by saad_kamaal on 10/3/2017.
 */

public class MessageModel {
    private String message;
    private String userId;

    public MessageModel() {
    }

    public MessageModel(String message, String userId) {
        this.message = message;
        this.userId = userId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
