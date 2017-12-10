package com.sayed;

public class GreetingServiceImpl implements GreetingService {

    @Override
    public String getGreeting(String name) {
        return "hello" + name + "";
    }
}
