package com.hx.imchat.basework;

import static android.content.Intent.FLAG_ACTIVITY_NEW_TASK;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.navigation.NavigationBarView;
import com.hx.imchat.R;
import com.hx.imchat.databinding.ActivityMainBinding;
import com.hx.imchat.home.HomeFragment;
import com.hx.imchat.user.UserFragment;

import java.util.ArrayList;
import java.util.List;

public class BaseActivity extends AppCompatActivity {

    private static final String TAG = "TAG_MainActivity";
    ActivityMainBinding binding;
    ViewPager2 pager;
    NavigationBarView nav;
    List<Fragment> fragments;

    public static void startMainActivity(Context context) {
        Intent intent = new Intent(context, BaseActivity.class);
        intent.setFlags(FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

//        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
//        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);

        fragments = new ArrayList<>();
        fragments.add(new HomeFragment());
        fragments.add(new UserFragment());

        setBinding();
        //viewpager
        ViewPagerAdapter adapter = new ViewPagerAdapter(this, fragments);
        pager.setAdapter(adapter);
        //nav
        nav.getMenu().add(0, 0, 1, R.string.nav_home).setIcon(R.drawable.ic_home);
        nav.getMenu().add(0, 1, 1, R.string.nav_user).setIcon(R.drawable.ic_user);
        nav.setLabelVisibilityMode(NavigationBarView.LABEL_VISIBILITY_AUTO);
        setListener();
    }

    private void setBinding() {
        pager = binding.viewpager2View;
        nav = binding.navView;

    }

    private void setListener() {

        //设置ViewPager滑动监听
        pager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                nav.getMenu().getItem(position).setChecked(true);
            }
        });

        nav.setOnItemSelectedListener(item -> {
            pager.setCurrentItem(item.getItemId(), true);
            return true;
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        binding = null;
    }

    /**
     * 返回ViewPager2是否可以滑动
     */
    public boolean isScrollable() {
        return pager.isUserInputEnabled();
    }

    /**
     * 设置ViewPager2是否可以滑动
     */
    public void setPagerScrollable(Boolean scrollable) {
        pager.setUserInputEnabled(scrollable);
    }
}