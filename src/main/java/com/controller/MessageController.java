package com.controller;

import com.model.Notification;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MessageController {

    private RabbitTemplate rabbitTemplate;

    public MessageController(RabbitTemplate rabbitTemplate){
        this.rabbitTemplate = rabbitTemplate;
    }

    @GetMapping("/message")
    public String sendMessage(@RequestParam String message){

        rabbitTemplate.convertAndSend("test", message);
        return "Wrzucono wiadomość na rabbitmq";
    }

    @PostMapping("/note")
    public String sendNotification(@RequestBody Notification notification){
        rabbitTemplate.convertAndSend("test", notification);
        return "Notyfikacja wysłana!";
    }
}
