package com.hx.imchat.user.login;

import android.util.Patterns;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.hx.imchat.R;
import com.hx.imchat.user.User;
import com.hx.imchat.user.login.data.LoginRepository;
import com.hx.imchat.user.login.data.Result;

public class LoginViewModel extends ViewModel {

    private final MutableLiveData<LoginFormState> loginFormState = new MutableLiveData<>();
    private final MutableLiveData<LoginResult> loginResult = new MutableLiveData<>();
    private final LoginRepository loginRepository;

    LoginViewModel(LoginRepository loginRepository) {
        this.loginRepository = loginRepository;
    }

    LiveData<LoginFormState> getLoginFormState() {
        return loginFormState;
    }

    LiveData<LoginResult> getLoginResult() {
        return loginResult;
    }

    /**
     * 利用LoginRepository类中的登录，然后从结果中获取LoggedInUser，并设置登录成功或登录失败
     */
    public void login(String account, String password) {
        // 可以在单独的异步作业中启动，获取登录结果，
        Result<User> result = loginRepository.login(account, password);

        //用判断结果是否为成功子类的实例来判断登录结果
        if (result instanceof Result.Success) {
            User user = ((Result.Success<User>) result).getData();
            loginResult.setValue(new LoginResult(new LoggedInUserView(user.getName())));
        } else {
            loginResult.setValue(new LoginResult(R.string.login_failed));
        }
    }

    /**
     * 监听用户输入，显示对应错误提示
     */
    public void loginDataChanged(String username, String password) {
        //用户名无效
        if (!isUserNameValid(username)) {
            loginFormState.setValue(new LoginFormState(R.string.invalid_phone, null));
        }
        //密码无效
        else if (!isPasswordValid(password)) {
            loginFormState.setValue(new LoginFormState(null, R.string.invalid_password));
        }
        //输入信息有效
        else {
            loginFormState.setValue(new LoginFormState(true));
        }
    }

    /**
     * 用户名验证检查
     */
    private boolean isUserNameValid(String username) {
        if (username == null) {
            return false;
        }
        if (username.trim().length() == 11) {
            return Patterns.PHONE.matcher(username).matches();
        } else {
            return false;
        }
    }

    /**
     * 密码验证检查
     */
    private boolean isPasswordValid(String password) {
        return password != null && password.trim().length() > 5;
    }
}