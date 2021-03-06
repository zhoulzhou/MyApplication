package com.example.demo.rxreg;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.example.demo.common.LogUtils;
import com.example.demo.myapplication.R;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;

public class MainTestActivity extends AppCompatActivity{
    private TextView mTextView;
    private final static String TAG = "testRxjava";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTextView = (TextView) findViewById(R.id.text);

        testRxjava();
        testRxjava2();
    }

    private void testRxjava(){

        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                LogUtils.d(TAG,"emitter 1");
                emitter.onNext(1);

                LogUtils.d(TAG,"emitter 3");
                emitter.onNext(3);

                LogUtils.d(TAG,"emitter 5");
                emitter.onNext(5);

                LogUtils.d(TAG,"emitter 7");
                emitter.onNext(7);

            }
        }).subscribe(new Observer<Integer>() {
            int i = 1;
            Disposable mdisposable;
            @Override
            public void onSubscribe(Disposable d) {
                mdisposable = d;
                LogUtils.d(TAG,"onSubscribe Disposable= " + d.isDisposed());
            }

            @Override
            public void onNext(Integer integer) {
                LogUtils.d(TAG,"onNext integer= " + integer);
                i += 2;
                LogUtils.d(TAG,"onNext i= " + i);
                if(i == 5){
                    mdisposable.dispose();
                    LogUtils.d(TAG,"onNext mdisposable= " + mdisposable.isDisposed());
                }
            }

            @Override
            public void onError(Throwable e) {
                LogUtils.d(TAG,"onError Throwable= " + e.getMessage());
            }

            @Override
            public void onComplete() {
                LogUtils.d(TAG,"onComplete ");
            }
        });
    }

    @SuppressLint("CheckResult")
    private void testRxjava2(){
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                emitter.onNext(1);
                emitter.onNext(2);
                emitter.onNext(3);
                emitter.onNext(4);
            }
        }).map(new Function<Integer, String>() {
            @Override
            public String apply(Integer integer) throws Exception {
                return "map result " + integer;
            }
        }).subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) throws Exception {
                LogUtils.d(TAG,"Consumer accept " + s);
            }
        });
    }
}
