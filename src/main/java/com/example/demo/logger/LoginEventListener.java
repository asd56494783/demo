package com.example.demo.logger;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class LoginEventListener {

    @EventListener
    public void handleLoginEvent(LoginEvent event) {
        String username = event.getUsername();
        boolean success = event.isSuccess();

        if (success) {
            System.out.println("登入成功 - 用户名：" + username);
        } else {
            System.out.println("登入失敗 - 用户名：" + username);
        }
    }
}
