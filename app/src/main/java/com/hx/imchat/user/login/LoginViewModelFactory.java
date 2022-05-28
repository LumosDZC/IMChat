package com.hx.imchat.user.login;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.hx.imchat.user.login.data.LoginDataSource;
import com.hx.imchat.user.login.data.LoginRepository;

/**
 * 用ViewModel provider factory 实例化 LoginViewModel，这样可以调用LoginViewModel的带参构造器
 * 所以需要给 LoginViewModel 一个非空的构造器
 */
public class LoginViewModelFactory implements ViewModelProvider.Factory {

    @NonNull
    @Override
    @SuppressWarnings("unchecked")
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        //isAssignableFrom()判断是否为某个类的父类
        if (modelClass.isAssignableFrom(LoginViewModel.class)) {
            return (T) new LoginViewModel(LoginRepository.getInstance(new LoginDataSource()));
        } else {
            throw new IllegalArgumentException("Unknown ViewModel class");
        }
    }
}