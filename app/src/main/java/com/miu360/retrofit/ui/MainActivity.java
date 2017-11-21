package com.miu360.retrofit.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.miu360.retrofit.R;
import com.miu360.retrofit.service.entity.Book;
import com.miu360.retrofit.service.presenter.BookPresenter;
import com.miu360.retrofit.service.datacall.DataStoragePlace;

public class MainActivity extends AppCompatActivity {
    private BookPresenter mBookPresenter = new BookPresenter(this);
    TextView tv_show;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mBookPresenter.onCreate();//retrofit的初始化
        mBookPresenter.attachData(mBookData);//把异步的数据放置在DataStoragePlace这个类的实例当中
        initView();
    }

    private void initView() {
        tv_show = (TextView) findViewById(R.id.tv_show);
        initData();
    }

    /**
     * 数据请求，实际请求在专门的类
     */
    private void initData() {
        mBookPresenter.getSearchBooks("金瓶梅", null, 0, 1);
        mBookPresenter.getSearchBooks("西游记", null, 0, 1);
        mBookPresenter.getSearchBooks("红楼梦", null, 0, 1);
    }

    /**
     * 把异步得到的数据用于展示
     */
    private DataStoragePlace mBookData = new DataStoragePlace() {
        @Override
        public void onSuccess(Book mBook) {
            Log.e("tag","tag:"+mBook.getBooks().get(0).getTags().get(0).getName());
            tv_show.setText(tv_show.getText().toString()+"\n"+mBook.getBooks().get(0).getTags().get(0).getName());
        }

        @Override
        public void onError(String result) {
            Toast.makeText(MainActivity.this,result, Toast.LENGTH_SHORT).show();
        }
    };


    @Override
    protected void onDestroy(){
        super.onDestroy();
        mBookPresenter.onStop();
    }
}
