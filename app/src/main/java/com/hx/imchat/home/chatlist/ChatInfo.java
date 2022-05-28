package com.hx.imchat.home.chatlist;

/**
 * @author: Hx
 * @date: 2022年04月08日 22:31
 */
public class ChatInfo {
    private int Id;
    private String title;
    private String describe;

    public ChatInfo(int id, String title, String describe) {
        Id = id;
        this.title = title;
        this.describe = describe;
    }


    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

}


