package com.miu360.retrofit.service.presenter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.miu360.retrofit.service.datacall.DataStorage;
import com.miu360.retrofit.service.datacall.DataStoragePlace;

import com.miu360.retrofit.service.entity.Book;
import com.miu360.retrofit.service.manager.DataManager;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by murphy on 2017/11/21.
 * 具体的实现网络请求
 */

public class BookPresenter implements Presenter{
    private DataManager manager;
    CompositeDisposable composite;

    private Context mContext;
    private DataStoragePlace mBookData;
    private Book mBook;
    public BookPresenter (Context mContext){
        this.mContext = mContext;
    }

    @Override
    public void onCreate() {
        manager = new DataManager(mContext);
        composite =  new CompositeDisposable();
    }

    @Override
    public void onStart() {

    }

    @Override
    public void onStop() {
        if (composite.isDisposed()){
            composite.dispose();
        }
    }

    @Override
    public void pause() {

    }

    @Override
    public void attachData(DataStorage dataStorage) {
        mBookData = (DataStoragePlace)dataStorage;
    }

    @Override
    public void attachIncomingIntent(Intent intent) {

    }

    public void getSearchBooks(String name,String tag,int start,int count){
        if(manager == null){
            return;
        }
        manager.getSearchBooks(name,tag,start,count)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Book>(){
                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                        mBookData.onError("请求失败！！");
                    }

                    @Override
                    public void onComplete() {
                        if (mBook != null){
                            mBookData.onSuccess(mBook);
                        }
                    }

                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        composite.add(d);
                        Log.e("tag","tag:"+composite.size());
                    }

                    @Override
                    public void onNext(Book book) {
                        mBook = book;
                    }
                });
    }

}
