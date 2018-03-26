package com.frank.protean.wiget;

import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.os.Build;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.frank.protean.R;


/**
 * 阅读页面过度item(head foot left right)
 * Created by frank on 2017/11/30.
 */
public class ReadOverViewHolder extends RecyclerView.ViewHolder {

    protected ProgressBar mLoadMorePb;
    protected TextView mFooterMsgText;
    protected View mFooterView;
    protected LinearLayout ll_loading_over;
    protected ImageView img_load;
    protected TextView tv_tips;
    protected RelativeLayout rl_read_loading;
    protected LinearLayout ll_top_over;
    protected AnimationDrawable animationDrawable;
    protected Context mContext;

    public ReadOverViewHolder(View mFooterView) {
        super(mFooterView);
        this.mContext = mFooterView.getContext();
        this.mFooterView = mFooterView;
        this.mFooterView.setVisibility(View.GONE);
//        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
//                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
//        params.gravity = Gravity.CENTER;
//        mFooterView.setLayoutParams(params);
        mLoadMorePb = mFooterView.findViewById(R.id.loadingPg);
        mFooterMsgText = mFooterView.findViewById(R.id.footerTxt);
        ll_loading_over = mFooterView.findViewById(R.id.ll_loading_over);
        img_load = mFooterView.findViewById(R.id.img_load);
        tv_tips = mFooterView.findViewById(R.id.tv_tips);
        rl_read_loading = mFooterView.findViewById(R.id.rl_read_loading);
        ll_top_over = mFooterView.findViewById(R.id.ll_top_over);
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
//            mFooterMsgText.setTextColor(this.mContext.getColor(R.color.font_light_gray));
//        } else {
//            //noinspection deprecation
//            mFooterMsgText.setTextColor(this.mContext.getResources().getColor(R.color.font_light_gray));
//        }
    }


    public void showLoading() {
        if (mLoadMorePb != null
                && mFooterMsgText != null
                && mFooterView != null && ll_top_over != null && rl_read_loading != null) {
            mFooterView.setVisibility(View.VISIBLE);
            mLoadMorePb.setVisibility(View.VISIBLE);
            ll_top_over.setVisibility(View.VISIBLE);
            rl_read_loading.setVisibility(View.GONE);
            mFooterMsgText.setText("加载中...");
        }
    }

    public void showNoMore() {
        showMore("没有更多了");
    }

    public void hideMore() {
        if (mLoadMorePb != null
                && mFooterMsgText != null
                && mFooterView != null) {
            mFooterView.setVisibility(View.VISIBLE);
            mLoadMorePb.setVisibility(View.GONE);
            mFooterMsgText.setText("");
            mFooterMsgText.setVisibility(View.GONE);
        }
    }

    public void showMore(String msg) {
        if (mLoadMorePb != null
                && mFooterMsgText != null
                && mFooterView != null && ll_top_over != null && rl_read_loading != null) {
            mFooterView.setVisibility(View.VISIBLE);
            mLoadMorePb.setVisibility(View.GONE);
            ll_top_over.setVisibility(View.VISIBLE);
            rl_read_loading.setVisibility(View.GONE);
            mFooterMsgText.setVisibility(View.VISIBLE);
            mFooterMsgText.setText(msg);
        }
    }

    public void showCenterLoading() {
        if (mFooterMsgText != null && ll_top_over != null && rl_read_loading != null && img_load != null && tv_tips != null && ll_loading_over != null) {
            mFooterView.setVisibility(View.VISIBLE);
            ll_top_over.setVisibility(View.GONE);
            rl_read_loading.setVisibility(View.VISIBLE);
            ll_loading_over.setVisibility(View.VISIBLE);
            tv_tips.setVisibility(View.GONE);
//            showImgLoading();
        }

    }

    public void showCenterTips(String msg) {
        if (mFooterMsgText != null && ll_top_over != null && rl_read_loading != null && img_load != null && tv_tips != null && ll_loading_over != null) {
            mFooterView.setVisibility(View.VISIBLE);
            ll_top_over.setVisibility(View.GONE);
            rl_read_loading.setVisibility(View.VISIBLE);
            ll_loading_over.setVisibility(View.GONE);
//            dismissImgLoading();
            tv_tips.setVisibility(View.VISIBLE);
            tv_tips.setText(msg);
        }
    }

    private void showImgLoading() {
        if (animationDrawable == null) {
            animationDrawable = (AnimationDrawable) img_load.getDrawable();
        }
        animationDrawable.start();
    }

    private void dismissImgLoading() {
        if (animationDrawable != null) {
            animationDrawable.stop();
        }
        if (rl_read_loading != null) {
            rl_read_loading.setVisibility(View.GONE);
        }
    }

    public void hide() {
        if (mFooterView != null) {
            mFooterView.setVisibility(View.GONE);
        }
    }

    public TextView getmFooterMsgText() {
        return mFooterMsgText;
    }
}
