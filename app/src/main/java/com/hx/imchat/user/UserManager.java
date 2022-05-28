package com.hx.imchat.user;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: Hx
 * @date: 2022年03月27日 16:41
 */
public class UserManager {

    private List<User> users;
    private UserManager(){
        users = new ArrayList<>();
    }

    public List<User> getUsers() {
        return users;
    }
    public void setUsers(List<User> users) {
        this.users = users;
    }

    private static UserManager INSTANCE;
    public static UserManager getInstance(){
        if(INSTANCE == null){
            INSTANCE = new UserManager();
        }
        return INSTANCE;
    }
}
