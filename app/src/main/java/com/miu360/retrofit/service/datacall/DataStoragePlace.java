package com.miu360.retrofit.service.datacall;

import com.miu360.retrofit.service.entity.Book;

/**
 * Created by murphy on 2017/11/21.
 * 定义接口，用于回调请求得到的数据;这里是retrofit请求到的数据放置的地方
 */

public interface DataStoragePlace extends DataStorage{
    void onSuccess(Book mBook);
    void onError(String result);
}
