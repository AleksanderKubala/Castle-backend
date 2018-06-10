package com.example.demo.WebSocket;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
public class GreetingController {


    /*private SimpMessagingTemplate template;

    @Autowired
    public GreetingController(SimpMessagingTemplate template) {
        this.template = template;
    }*/
    public GreetingController() {};

    /*
    @MessageMapping("/hello")
    @SendTo("/hello/receive")
    public Greeting validate() {

        Person person = new Person("Sean", 21);
        Greeting greeting = new Greeting("Hi", person);

        FireGreeting r = new FireGreeting( this );
        new Thread(r).start();

        return greeting;
    }*/

    /*
    public void fireGreeting() {
        System.out.println("Fire");
        Person person = new Person("Bean", 34);
        Greeting greeting = new Greeting("Bye", person);
        this.template.convertAndSend("/hello/receive", greeting);
    }*/
}