package com.hx.imchat.user.login.data;

import com.hx.imchat.user.User;

import java.io.IOException;

/**
 * 通过网络请求处理身份验证登录凭据并检索用户信息。
 */
public class LoginDataSource {

    //网络请求登录操作
    public Result login(String account, String password) {
        // TODO: 处理登录身份
        try {
            if (!account.equals(password)) {

                User user =
                        new User("阿伟", account, 15,
                                true, "南亭村委主席");
                return new Result.Success<>(user);
            }
            else return new Result.Error(new Exception("账号或密码错误"));

        } catch (Exception e) {
            return new Result.Error(new IOException("账号或密码错误", e));
        }
    }

    //网络请求登出操作
    public void logout() {
        // TODO: 撤销认证
    }
}