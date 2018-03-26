package com.frank.protean.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.davemorrissey.labs.subscaleview.ImageSource;
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
import com.frank.protean.wiget.ReadOverViewHolder;
import com.squareup.picasso.Picasso;

import java.io.File;

import okhttp3.OkHttpClient;

/**
 * Created by Frankmao on 2017/8/22.
 */

public class ContentAdapter extends BaseReadAdapter<ContentAdapter.ListViewHolder, ReadOverViewHolder, ReadOverViewHolder> {
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
        ContentPageBean pageBean = getItem(position);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        params.width = ScreenUtils.getScreenWidth(mContext) - 2;
        holder.img_content.setScaleType(ImageView.ScaleType.FIT_CENTER);
        Picasso.with(mContext).load(pageBean.url).into(holder.img_content);
//        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
//        params.width = ScreenUtils.getScreenWidth(mContext) - 2;
//        Glide.with(mContext).asFile().load(pageBean.url).into(new SimpleTarget<File>() {
//            @Override
//            public void onResourceReady(File resource, Transition<? super File> transition) {
//                ImageSource imageSource = ImageSource.uri(Uri.fromFile(resource));
//                holder.img_content.setImage(imageSource);
//                holder.img_content.setZoomEnabled(true);
//            }
//
//            @Override
//            public void onLoadFailed(@Nullable Drawable errorDrawable) {
//                super.onLoadFailed(errorDrawable);
//            }
//
//            @Override
//            public void onLoadStarted(@Nullable Drawable placeholder) {
//                ImageSource imageSource =ImageSource.resource(R.mipmap.ic_launcher);
//                holder.img_content.setImage(imageSource);
//            }
//        });


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
