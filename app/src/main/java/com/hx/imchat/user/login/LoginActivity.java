package com.hx.imchat.user.login;

import static com.hx.imchat.basework.BaseActivity.startMainActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.StringRes;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.hx.imchat.R;
import com.hx.imchat.basework.App;
import com.hx.imchat.databinding.ActivityLoginBinding;


public class LoginActivity extends AppCompatActivity {

    static final String TAG = "TAG_LoginActivity";
    LoginViewModel loginViewModel;
    ActivityLoginBinding binding;
    boolean isAgreeProtocol;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        //创建viewModel,
        loginViewModel = new ViewModelProvider(this, new LoginViewModelFactory())
                .get(LoginViewModel.class);

        final EditText phoneNumberEditText = binding.phoneNumber;
        final EditText passwordEditText = binding.password;
        final ImageView loginButton = binding.login;
        final ProgressBar loadingProgressBar = binding.loading;
        final CheckBox agreeCheckbox = binding.cbLoginAgree;
        final SharedPreferences sp = getSharedPreferences("loginData", MODE_PRIVATE);
        final SharedPreferences.Editor editor = sp.edit();

        String account = sp.getString("account", null);
        if (account != null) phoneNumberEditText.setText(account);
        String password = sp.getString("password", null);
        if (password != null) passwordEditText.setText(password);

        //监视输入信息，更新对应ui
        loginViewModel.getLoginFormState().observe(this, loginFormState -> {
            if (loginFormState == null) {
                return;
            }
            //设置登录键的可用性
            loginButton.setEnabled(loginFormState.isDataValid());

            //在输入框显示错误提醒
            if (loginFormState.getUsernameError() != null) {
                phoneNumberEditText.setError(getString(loginFormState.getUsernameError()));
            }
            if (loginFormState.getPasswordError() != null) {
                passwordEditText.setError(getString(loginFormState.getPasswordError()));
            }
        });

        //监视登录结果
        loginViewModel.getLoginResult().observe(this, loginResult -> {
            if (loginResult == null) {
                return;
            }
            //隐藏加载进度条
            loadingProgressBar.setVisibility(View.GONE);

            //显示登录失败信息
            if (loginResult.getError() != null) {
                showLoginFailed(loginResult.getError());
            }
            //显示登录成功信息,跳转页面
            if (loginResult.getSuccessInfo() != null) {
                showLoginSuccess(loginResult.getSuccessInfo());
                //存储登录数据
                editor.putString("account", phoneNumberEditText.getText().toString());
                editor.putString("password", passwordEditText.getText().toString());
                editor.commit();

                startMainActivity(this);
                finish();
            }
        });

        TextWatcher afterTextChangedListener = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // ignore
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // ignore
            }

            @Override
            public void afterTextChanged(Editable s) {
                loginViewModel.loginDataChanged(phoneNumberEditText.getText().toString(),
                        passwordEditText.getText().toString());
            }
        };
        phoneNumberEditText.addTextChangedListener(afterTextChangedListener);
        passwordEditText.addTextChangedListener(afterTextChangedListener);

        //登录键监视
        loginButton.setOnClickListener(v -> {
            if (isAgreeProtocol) {
                loadingProgressBar.setVisibility(View.VISIBLE);
                //传入用户名和密码
                loginViewModel.login(phoneNumberEditText.getText().toString(),
                        passwordEditText.getText().toString());
            } else {
                Toast.makeText(App.getContext(), "请先同意协议", Toast.LENGTH_SHORT).show();
            }
        });

        //是否同意协议监视
        agreeCheckbox.setOnCheckedChangeListener((buttonView, isChecked) -> isAgreeProtocol = isChecked);
    }

    /**
     * 更新登录成功UI
     */
    private void showLoginSuccess(LoggedInUserView model) {
        String welcome = getString(R.string.welcome) + model.getName();
        Toast.makeText(getApplicationContext(), welcome, Toast.LENGTH_LONG).show();
    }

    /**
     * 更新登录失败UI
     */
    private void showLoginFailed(@StringRes Integer errorString) {
        Toast.makeText(getApplicationContext(), "登录失败，账号或密码错误", Toast.LENGTH_SHORT).show();
    }
}