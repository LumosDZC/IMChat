package com.hx.imchat.user;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.hx.imchat.user.login.data.LoginDataSource;
import com.hx.imchat.user.login.data.LoginRepository;

/**
 * @author: Hx
 * @date: 2022年03月31日 0:47
 */
public class UserViewModelFactory implements ViewModelProvider.Factory {
    @NonNull
    @Override
    @SuppressWarnings("unchecked")
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        //isAssignableFrom()判断是否为某个类的父类
        if (modelClass.isAssignableFrom(UserViewModel.class)) {
            return (T) new UserViewModel(LoginRepository.getInstance(new LoginDataSource()));
        } else {
            throw new IllegalArgumentException("Unknown ViewModel class");
        }
    }
}
