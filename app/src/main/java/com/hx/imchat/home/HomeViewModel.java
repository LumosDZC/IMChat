package com.hx.imchat.home;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

/**
 * @author: Hx
 * @date: 2022年03月26日 17:54
 */
public class HomeViewModel extends ViewModel {

    private final MutableLiveData<Boolean> isFinishRefreshing;

    public HomeViewModel() {
        isFinishRefreshing = new MutableLiveData<>();
    }

    /**
     * 提供列表刷新监视
     */
    public LiveData<Boolean> getRefresh(){
        return isFinishRefreshing;
    }

    /**
     * 提供刷新列表监视
     */
    public void setFinishRefresh(Boolean isFinish){
        isFinishRefreshing.postValue(isFinish);
    }
}
