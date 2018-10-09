package com.example.demo.mvp.view;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public abstract class BaseFragment extends Fragment implements IBaseView{

    public abstract int getContentViewId();
    public abstract void initAllMembersView(Bundle savedInstanceState);

    protected Context mContext;
    protected View mRootView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mRootView = inflater.inflate(getContentViewId(), container, false);
        this.mContext = getActivity();
        initAllMembersView(savedInstanceState);
        return mRootView;
    }

    protected boolean isAttachedContext(){
        return getActivity() != null;
    }

    public void checkActivityAttached(){
        if(getActivity() == null){
            throw new ActivityNotAttachedException();
        }
    }

    public static class ActivityNotAttachedException extends RuntimeException{
        public ActivityNotAttachedException(){
            super("fragment has disconnected from activity ---");
        }
    }

    @Override
    public void showLoading() {
        checkActivityAttached();
//        ((BaseFragmentActivity) mContext).showLoading();
    }


    @Override
    public void hideLoading() {
        checkActivityAttached();
//        ((BaseFragmentActivity) mContext).hideLoading();
    }

    @Override
    public void showToast(String msg) {
        checkActivityAttached();
//        ((BaseFragmentActivity) mContext).showToast(msg);
    }

    @Override
    public void showErr() {
        checkActivityAttached();
//        ((BaseFragmentActivity) mContext).showErr();
    }

}
