package com.hx.imchat.basework;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import java.util.List;

/**
 * @author: Hx
 * @date: 2022年03月27日 0:15
 */
public class ViewPagerAdapter extends FragmentStateAdapter {

    private final List<Fragment> fragments;

    public ViewPagerAdapter(FragmentActivity fragmentActivity, List<Fragment> fragments){
        super(fragmentActivity);
        this.fragments = fragments;
    }


    @Override
    public int getItemCount() {
        return fragments.size();
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        return fragments.get(position);
    }
}
