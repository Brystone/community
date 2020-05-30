package com.community.util;

import com.community.entity.User;
import org.springframework.stereotype.Component;

@Component
public class HostHolder {

    //初始化ThreadLocal
    private ThreadLocal<User> userLocal = new ThreadLocal();

    public void setUser(User user) {
        userLocal.set(user);
    }

    public User getUser() {
        return userLocal.get();
    }

    public void clear() {
        userLocal.remove();
    }
}
