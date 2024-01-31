package com.example.websiteapi.api.controller;

import com.example.websiteapi.api.model.MailMessage;
import com.example.websiteapi.services.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.net.http.HttpResponse;
import java.util.LinkedHashMap;

@CrossOrigin(origins = {"http://localhost:3000", "https://ahmetyavuzm.github.io/next_app/"})
@RestController
public class MessageController {

    private MessageService messageService;

    @Autowired
    public MessageController(MessageService messageService){
        this.messageService = messageService;
    }
    @PostMapping("/send-message")
    public boolean postMessage(@RequestBody LinkedHashMap<String,String> body){

        MailMessage message = new MailMessage(body.get("email"),body.get("subject"),body.get("message"));

        return messageService.sendMessage(message);
    };

}
