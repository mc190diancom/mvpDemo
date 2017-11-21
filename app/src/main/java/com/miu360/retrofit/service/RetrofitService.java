package com.miu360.retrofit.service;

import com.miu360.retrofit.service.entity.Book;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by murphy on 2017/11/21.
 * 请求数据专门的一个类(接口)，这里只列出了一个
 */

public interface RetrofitService {
    @GET("book/search")
    Observable<Book> getSearchBooks(@Query("q") String name,
                                   @Query("tag") String tag,
                                   @Query("start") int start,
                                   @Query("count") int count);
}
