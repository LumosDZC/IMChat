package com.hx.imchat.user;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.bumptech.glide.Glide;
import com.hx.imchat.R;
import com.hx.imchat.databinding.FragmentUserBinding;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * @author: Hx
 * @date: 2022年03月26日 17:51
 */
public class UserFragment extends Fragment {

    private static final String TAG = "TAG_UserFragment";
    FragmentUserBinding binding;
    Context mContext;
    TextView userName;
    TextView userPhone;
    CircleImageView userHeader;
    UserViewModel viewModel;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        //绑定的时候获取activity的上下文
        mContext = context;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = new ViewModelProvider(this, new UserViewModelFactory()).get(UserViewModel.class);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentUserBinding.inflate(inflater, container, false);

        //绑定视图
        bindView();
        //初始化UI
        initUI();
        //设置监听
        initUIFunction();
        return binding.getRoot();
    }

    /**
     * 绑定视图
     */
    private void bindView() {
        userName = binding.user.tvUserName;
        userPhone = binding.user.tvUserPhoneNumber;
        userHeader = binding.user.cvUserHeader;
    }

    /**
     * 初始化UI
     */
    private void initUI() {
        binding.itemUserName.userInfoName.setText(getResources().getString(R.string.name));
        binding.itemUserAge.userInfoName.setText(getResources().getString(R.string.age));
        binding.itemUserSex.userInfoName.setText(getResources().getString(R.string.sex));
        binding.itemUserOccupation.userInfoName.setText(getResources().getString(R.string.occupation));
        Glide.with(this).load(R.mipmap.avatar).into(userHeader);
    }

    /**
     * 初始化UI功能
     */
    private void initUIFunction() {

        //监视用户数据变化
        viewModel.getUser().observe(getViewLifecycleOwner(), user -> {
            setUserUIData(user);
            viewModel.notifyUserDataChanged();
        });

        //修改姓名
        binding.itemUserName.userItem.setOnClickListener(view -> {

        });

        //修改年龄
        binding.itemUserAge.userItem.setOnClickListener(view -> {

        });

        //修改性别
        binding.itemUserSex.userItem.setOnClickListener(view -> {

        });

        //修改职业
        binding.itemUserOccupation.userItem.setOnClickListener(view -> {

        });

        binding.logout.setOnClickListener(v -> getActivity().finish());
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    /**
     * 更新UI数据
     */
    private void setUserUIData(@NonNull User user) {
        userName.setText(user.getName());
        userPhone.setText(user.getPhoneNumber());
        binding.itemUserName.userInfoContent.setText(user.getName());
        binding.itemUserAge.userInfoContent.setText(Integer.toString(user.getAge()));
        binding.itemUserSex.userInfoContent.setText(user.getMan() ? "男" : "女");
        binding.itemUserOccupation.userInfoContent.setText(user.getOccupation());
    }

}
