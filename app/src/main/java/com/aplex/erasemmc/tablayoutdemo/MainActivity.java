package com.aplex.erasemmc.tablayoutdemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @InjectView(R.id.top_btn)
    public Button mTopBtn;
    @InjectView(R.id.bottom_btn)
    public Button mBottomBtn;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        ButterKnife.inject(this);
    }

    @OnClick(R.id.top_btn)
    void turnToTopTab(){
        Intent intent = new Intent(this,TopTabLayoutActivity.class);
        startActivity(intent);
    }
    @OnClick(R.id.bottom_btn)
    void turnToBottomTab(){
        Intent intent =  new Intent(this,BottomTabLayoutActivity.class);
        startActivity(intent);
    }
}
