package com.hx.imchat.user;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.hx.imchat.user.login.data.LoginRepository;

/**
 * @author: Hx
 * @date: 2022年03月26日 17:53
 */
public class UserViewModel extends ViewModel {

    private final MutableLiveData<User> user;
    private final LoginRepository loginRepository;


    UserViewModel(LoginRepository loginRepository) {
        user = new MutableLiveData<>();
        user.setValue(loginRepository.getUser());
        this.loginRepository = loginRepository;
    }

    /**
     * 提供User数据变化监视
     */
    public LiveData<User> getUser() {
        return user;
    }

    /**
     * 更新用户数据
     */
    public void notifyUserDataChanged() {
        loginRepository.setUser(user.getValue());
    }

    public void setUserName(String name) {
        User u = user.getValue();
        u.setName(name);
        user.setValue(u);
    }

    public void setUserAge(int age) {
        User u = user.getValue();
        u.setAge(age);
        user.setValue(u);
    }

    public void setUserSex(boolean isMan) {
        User u = user.getValue();
        u.setMan(isMan);
        user.setValue(u);
    }

    public void setUserOccupation(String occupation) {
        User u = user.getValue();
        u.setOccupation(occupation);
        user.setValue(u);
    }
}
