package com.example.demo.logger;

import org.springframework.context.ApplicationEvent;

public class LoginEvent extends ApplicationEvent {
    private String username;
    private boolean success;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public LoginEvent(Object source, String username, boolean success) {
        super(source);
    }

}
