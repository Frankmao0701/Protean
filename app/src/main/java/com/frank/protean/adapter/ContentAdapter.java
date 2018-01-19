package com.frank.protean.adapter;

import android.content.Context;
import android.graphics.PointF;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.davemorrissey.labs.subscaleview.ImageSource;
import com.davemorrissey.labs.subscaleview.ImageViewState;
import com.davemorrissey.labs.subscaleview.SubsamplingScaleImageView;
import com.davemorrissey.labs.subscaleview.decoder.DecoderFactory;
import com.davemorrissey.labs.subscaleview.decoder.ImageDecoder;
import com.davemorrissey.labs.subscaleview.decoder.ImageRegionDecoder;
import com.frank.protean.R;
import com.frank.protean.ScreenUtils;
import com.frank.protean.bean.ContentPageBean;
import com.frank.protean.photoview.PhotoView;
import com.frank.protean.wiget.PicassoDecoder;
import com.frank.protean.wiget.PicassoRegionDecoder;
import com.squareup.picasso.Picasso;

import java.io.File;

import okhttp3.OkHttpClient;

/**
 * Created by Frankmao on 2017/8/22.
 */

public class ContentAdapter extends BaseReadAdapter<ContentAdapter.ListViewHolder, ContentAdapter.LockViewHolder, ContentAdapter.LoadingViewHolder> {
    private OnBackClickListener onBackClickListener;

    public ContentAdapter(@NonNull Context context) {
        super(context);
    }

    public void setOnBackClickListener(OnBackClickListener onBackClickListener) {
        this.onBackClickListener = onBackClickListener;
    }

    @Override
    public ListViewHolder onCreateItemViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        RecyclerView.ViewHolder holder = null;
        View view = inflater.inflate(R.layout.layout_item_content, parent, false);
        return new ListViewHolder(view);
    }

    @Override
    public void onBindItemViewHolder(final ListViewHolder holder, int position) {
//        Log.e("ContentAdapter", position + "");
        final ContentPageBean pageBean = getItem(position);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        params.width = ScreenUtils.getScreenWidth(mContext) - 2;
        holder.img_content.setScaleType(ImageView.ScaleType.FIT_CENTER);
        Picasso.with(mContext).load(pageBean.url).into(holder.img_content);
//        holder.img_content.setLayoutParams(params);

    }

    public void loadImageByUrl(SubsamplingScaleImageView scaleImageView, final String url, final OkHttpClient okHttpClient) {
        scaleImageView.setMaxScale(5.0f);
        final Picasso picasso = Picasso.with(scaleImageView.getContext());

        scaleImageView.setBitmapDecoderFactory(new DecoderFactory<ImageDecoder>() {
            @Override
            public ImageDecoder make() throws IllegalAccessException, java.lang.InstantiationException {

                return new PicassoDecoder(url, picasso);
            }
        });

        scaleImageView.setRegionDecoderFactory(new DecoderFactory<ImageRegionDecoder>() {
            @Override
            public ImageRegionDecoder make() throws IllegalAccessException, java.lang.InstantiationException {
                return new PicassoRegionDecoder(okHttpClient);
            }
        });

//        scaleImageView.setOnImageEventListener(new SubScalingImageViewListener());
        scaleImageView.setImage(ImageSource.uri(url));
    }

    @Override
    public LockViewHolder onCreateLockViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        RecyclerView.ViewHolder holder = null;
        View view = inflater.inflate(R.layout.layout_item_lock, parent, false);
        return new LockViewHolder(view);
    }

    @Override
    public void onBindLockViewHolder(LockViewHolder holder, final int position) {
        holder.tv_title.setText(getItem(position).title);

//        data.remove(1);
//        mAdapter.notifyItemRemoved(1);
//        List<String> temp = new ArrayList<>();
//        temp.add("新增数据0");
//        temp.add("新增数据1");
//        data.addAll(1,temp);
//        mAdapter.notifyItemRangeInserted(1,temp.size());
        holder.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackClickListener.onBack(position, getItem(position).id);
            }
        });
    }

    @Override
    public LoadingViewHolder onCreateLoadingViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        RecyclerView.ViewHolder holder = null;
        View view = inflater.inflate(R.layout.layout_item_loading, parent, false);
        return new LoadingViewHolder(view);
    }

    @Override
    public void onBindLoadingViewHolder(LoadingViewHolder holder, int position) {

    }


    public static class ListViewHolder extends RecyclerView.ViewHolder {
        PhotoView img_content;

        public ListViewHolder(View itemView) {
            super(itemView);
            img_content = (PhotoView) itemView.findViewById(R.id.img_content);
        }
    }

    public static class LockViewHolder extends RecyclerView.ViewHolder {
        Button button;
        TextView tv_title;

        public LockViewHolder(View itemView) {
            super(itemView);
            button = (Button) itemView.findViewById(R.id.button);
            tv_title = (TextView) itemView.findViewById(R.id.tv_title);
        }
    }

    public static class LoadingViewHolder extends RecyclerView.ViewHolder {

        public LoadingViewHolder(View itemView) {
            super(itemView);
        }
    }

    public interface OnBackClickListener {
        void onBack(int position, String id);
    }

}
