package com.example.lsy.mytestview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.lsy.mytestview.view.MyTestView1;
import com.example.lsy.mytestview.view.MyTestView2;
import com.example.lsy.mytestview.view.MyTestView3;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.my_test_view1)
    MyTestView1 myTestView1;

    @OnClick(R.id.my_test_view1)
    void startObjectAnimation(){
        Log.e("startObjectAnimation","clicking");
        myTestView1.startObjectAmin();
    }


    @BindView(R.id.myTestView2)
    MyTestView2 myTestView2;

    @OnClick(R.id.myTestView2)
    void startBallAnimation(){
        Log.e("startBallAnimation","clicking");
        myTestView2.startBallAnimation();
    }

    @BindView(R.id.myTestView3)
    MyTestView3 myTestView3;

    @OnClick(R.id.myTestView3)
    void startAnim(){
        Log.e("startAnim","clicking");
        myTestView3.startAnim();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }
}
