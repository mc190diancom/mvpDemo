package com.miu360.retrofit.service.manager;

import android.content.Context;

import com.miu360.retrofit.service.RetrofitHelper;
import com.miu360.retrofit.service.RetrofitService;
import com.miu360.retrofit.service.entity.Book;

import io.reactivex.Observable;

/**
 * Created by murphy on 2017/11/21.
 * 这个类其实就是为了让你更方便的调用RetrofitService 中定义的方法：
 */

public class DataManager {
    private RetrofitService mRetrofitService;

    public DataManager(Context context){
        this.mRetrofitService = RetrofitHelper.getInstance(context).getServer();
    }

    public Observable<Book> getSearchBooks(String name, String tag, int start, int count){
        return mRetrofitService.getSearchBooks(name,tag,start,count);
    }
}
