package com.hx.imchat.user.login.data;

import com.hx.imchat.user.User;

/**
 * 从远程数据源请求身份验证和用户信息并维护登录状态和用户凭据信息的内存缓存的类。
 */
public class LoginRepository {

    private static volatile LoginRepository instance;
    private LoginDataSource dataSource;
    private User user = null;

    /**
     * 单例模式
     */
    private LoginRepository(LoginDataSource dataSource) {
        this.dataSource = dataSource;
    }

    public static LoginRepository getInstance(LoginDataSource dataSource) {
        if (instance == null) {
            instance = new LoginRepository(dataSource);
        }
        return instance;
    }

    public boolean isLoggedIn() {
        return user != null;
    }

    public void logout() {
        user = null;
        dataSource.logout();
    }

    /**
     * 获取User
     */
    public User getUser() {
        return user;
    }

    /**
     * 设置User
     */
    public void setUser(User user) {
        this.user = user;
    }

    /**
     * 在这利用LoginDataSource类中的登录网络请求，保存登录用户的数据
     */
    public Result<User> login(String account, String password) {

        Result result = dataSource.login(account, password);
        //如果登录成功，在本类保存LoggedInUser变量数据
        if (result instanceof Result.Success) {
            //先将result强制转换为success子类，再获取LoggedInUser
            setUser(((Result.Success<User>) result).getData());
        }
        return result;
    }
}