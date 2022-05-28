package com.hx.imchat.home;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.hx.imchat.basework.App;
import com.hx.imchat.databinding.FragmentHomeBinding;
import com.hx.imchat.home.chatlist.ChatInfo;
import com.hx.imchat.home.chatlist.ChatListAdapter;
import com.hx.imchat.home.chatlist.ChatListInfo;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * @author: Hx
 * @date: 2022年03月26日 17:52
 */
public class HomeFragment extends Fragment {

    private static final String TAG = "TAG_HomeFragment";
    boolean isAllPermissionGranted = false;
    FragmentHomeBinding binding;
    HomeViewModel homeViewModel;
    RecyclerView rl_chatList;
    SwipeRefreshLayout srl_refreshTestList;
    Context mContext;
    ActivityResultLauncher permissionLauncher;
    LinearLayoutManager layoutManager;
    ActivityResultLauncher<String> albumLauncher;
    ChatListAdapter chatListAdapter;
    ArrayList<ChatInfo> list;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        //绑定的时候获取activity的上下文
        mContext = context;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        homeViewModel = new ViewModelProvider(this).get(HomeViewModel.class);
        //设置权限请求器
        permissionLauncher = registerForActivityResult(new ActivityResultContracts.RequestMultiplePermissions(),
                result -> isAllPermissionGranted = !result.containsValue(false));
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        binding = FragmentHomeBinding.inflate(inflater, container, false);

        //视图绑定
        bindView();
        //设置时间
        setTime();
        return binding.getRoot();
    }

    /**
     * 绑定视图
     */
    private void bindView() {
        srl_refreshTestList = binding.srlTestRefresh;
        rl_chatList = binding.rlChatlist;
    }

    @Override
    public void onStart() {
        super.onStart();
        //初始化UI功能
        initUiFunction();
    }

    /**
     * 设置UI功能
     */
    private void initUiFunction() {

        //监视列表刷新
        homeViewModel.getRefresh().observe(getViewLifecycleOwner(), isFinish -> srl_refreshTestList.setRefreshing(!isFinish));

        //设置列表
        list = ChatListInfo.getTempEvaluationInfo();
        chatListAdapter = new ChatListAdapter(list);
        layoutManager = new LinearLayoutManager(App.getContext());
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        layoutManager.setSmoothScrollbarEnabled(true);
        rl_chatList.setLayoutManager(layoutManager);

        layoutManager = new LinearLayoutManager(App.getContext());
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        layoutManager.setSmoothScrollbarEnabled(true);
        rl_chatList.setLayoutManager(layoutManager);
        rl_chatList.setAdapter(chatListAdapter);

        //TODO:刷新列表
        srl_refreshTestList.setOnRefreshListener(this::refreshTestList);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    /**
     * 刷新列表
     */
    private void refreshTestList() {
        new Thread(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            homeViewModel.setFinishRefresh(true);
        }).start();
    }

    /**
     * 设置时间
     */
    private void setTime() {
        TextView time = binding.tvHomeTime;
        Date date = new Date();
        SimpleDateFormat ft = new SimpleDateFormat("M月d日 E");
        time.setText(ft.format(date));
    }
}
