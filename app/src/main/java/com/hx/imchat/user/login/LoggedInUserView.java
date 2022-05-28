package com.hx.imchat.user.login;

/**
 * 向 UI 公开经过身份验证的用户详细信息, 存放UI可调用的数据
 */
public class LoggedInUserView {

    private String name;

    public LoggedInUserView(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}