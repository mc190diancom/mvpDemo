package com.miu360.retrofit.service.presenter;

import android.content.Intent;

import com.miu360.retrofit.service.datacall.DataStorage;

/**
 * Created by murphy on 2017/11/21.
 * 用于网络请求的基础类
 */

public interface Presenter {
    void onCreate();

    void onStart();//暂时没用到

    void onStop();

    void pause();//暂时没用到

    void attachData(DataStorage dataStorage);

    void attachIncomingIntent(Intent intent);//暂时没用到

}
