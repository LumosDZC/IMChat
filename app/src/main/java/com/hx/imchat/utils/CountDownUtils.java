package com.hx.imchat.utils;

import android.os.Handler;

/**
 * @author: Hx
 * @date: 2022年04月05日 1:19
 */
public class CountDownUtils {

    public static final int COUNTDOWN_ING = 0;
    public static final int COUNTDOWN_FINISH = 1;
    private int currentSecond = 6;
    private Handler handler;
    private int m;

    public CountDownUtils(CountdownCallback callback) {

        handler = new Handler(msg -> {
            switch (msg.what) {
                case COUNTDOWN_ING: {
                    secondDown();
                    callback.callback(getCurrentSecond(), false, m);
                    if (getCurrentSecond() > 0) {
                        handler.sendMessageDelayed(handler.obtainMessage(COUNTDOWN_ING),
                                1000);
                    } else
                        handler.sendMessage(handler.obtainMessage(COUNTDOWN_FINISH));
                    return true;
                }

                case COUNTDOWN_FINISH: {
                    //倒计时结束
                    callback.callback(0, true, m);
                    return true;
                }
            }
            return false;
        });
    }

    private void secondDown() {
        currentSecond--;
    }

    public int getCurrentSecond() {
        return currentSecond;
    }

    public void startCountDown(int second, int msg) {
        m = msg;
        currentSecond = second + 1;
        handler.sendMessage(handler.obtainMessage(COUNTDOWN_ING));
    }

    public interface CountdownCallback {
        void callback(int currentSecond, boolean finished, int msg);
    }
}
