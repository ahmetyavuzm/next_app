package com.example.websiteapi.api.model;

public class MailMessage {
    public String email;
    public String subject;
    public String message;

    public MailMessage(String email, String subject, String message){
        this.email = email;
        this.subject = subject;
        this.message = message;
    }

}
