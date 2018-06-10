package com.example.demo.WebSocket;

public class FireGreeting implements Runnable {

    private GreetingController listener;

    public FireGreeting(GreetingController listener) {
        this.listener = listener;
    }

    @Override
    public void run() {
        for(int i = 0; i < 5; i++) {
            try {
                Thread.sleep( 5000 );
                //listener.fireGreeting();
            } catch ( InterruptedException e ) {
                e.printStackTrace();
            }
        }
    }
}
