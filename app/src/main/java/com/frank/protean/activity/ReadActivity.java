package com.frank.protean.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.PagerSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.frank.protean.R;
import com.frank.protean.adapter.ContentAdapter;
import com.frank.protean.bean.ContentPageBean;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Frankmao on 2017/6/8.
 */

public class ReadActivity extends AppCompatActivity {
    public static final String TAG = ReadActivity.class.getSimpleName();
    private RecyclerView recycleView;
    private List<String> urls;
    private List<ContentPageBean> mData;
    private ContentAdapter mAdapter;
    private TextView switch_mode;
    private LinearLayoutManager manager;
    private PagerSnapHelper horSnapHelper;
    private boolean isHor = false;
    private boolean isLoading;
    private Handler mHandler;
    private boolean isChange;
    private String chargeId;
    private boolean preIsLocked = true;
    private boolean nextIsLocked = true;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read);
        initView();
    }

    private void initView() {
        mHandler = new Handler();
        urls = new ArrayList<>();
        mData = new ArrayList<>();

        urls.add("https:\\/\\/jpapps.qoo-app.com\\/comic-test\\/1001\\/2\\/052a60786c0d36eaae7f0b94edbb6931.jpg");
        urls.add("https:\\/\\/jpapps.qoo-app.com\\/comic-test\\/1001\\/2\\/4e6c30ff73e5a11d7d24675dd77a5002.jpg");
        urls.add("https:\\/\\/jpapps.qoo-app.com\\/comic-test\\/1001\\/2\\/a88a4da26ff88b145cf05461b40aba12.jpg");
        urls.add("https:\\/\\/jpapps.qoo-app.com\\/comic-test\\/1001\\/2\\/a4d402bf626cf501816b976d7c49536d.jpg");
        urls.add("https:\\/\\/jpapps.qoo-app.com\\/comic-test\\/1001\\/2\\/a67970ca9d5f1206f2ca50819c713d34.jpg");
        for (int i = 0; i < urls.size(); i++) {
            ContentPageBean pageBean = new ContentPageBean();
            pageBean.url = urls.get(i);
            pageBean.id = i + 2 + "";
            mData.add(pageBean);
        }
        mAdapter = new ContentAdapter(this);
        mAdapter.setOnBackClickListener(new ContentAdapter.OnBackClickListener() {
            @Override
            public void onBack(int position, String id) {
                mData.remove(position);
//                mAdapter.notifyItemRemoved(position);
                List<ContentPageBean> temp = new ArrayList<>();
                ContentPageBean pageBean = new ContentPageBean();
                pageBean.id = 100 + "";
                pageBean.url = "https:\\/\\/jpapps.qoo-app.com\\/comic-test\\/1001\\/3\\/88ee5b866a698e38c3fa294fc1835185.jpg";
                ContentPageBean pageBean2 = new ContentPageBean();
                pageBean2.id = 200 + "";
                pageBean2.url = "https:\\\\/\\\\/jpapps.qoo-app.com\\\\/comic-test\\\\/1001\\\\/3\\\\/6699ffded74fc3d11105b1e956a93bc9.jpg";
                temp.add(pageBean);
                temp.add(pageBean2);
                mData.addAll(position, temp);
                mAdapter.notifyItemRangeChanged(position, mData.size());
//                mAdapter.notifyDataSetChanged();
//                mAdapter.notifyItemRangeInserted(position, temp.size());
//                chargeId = id;
//                Intent intent = new Intent(ReadActivity.this, PayActivity.class);
//                startActivityForResult(intent, 0);
            }
        });
        mAdapter.setData(mData);
        recycleView = (RecyclerView) findViewById(R.id.recycleView);
        switch_mode = (TextView) findViewById(R.id.switch_mode);
        manager = new LinearLayoutManager(this);
        horSnapHelper = new PagerSnapHelper();
        if (isHor) {
            manager.setOrientation(LinearLayoutManager.HORIZONTAL);
            horSnapHelper.attachToRecyclerView(recycleView);
        } else {
            manager.setOrientation(LinearLayoutManager.VERTICAL);
            horSnapHelper.attachToRecyclerView(null);
        }
        recycleView.setLayoutManager(manager);

        recycleView.setAdapter(mAdapter);

        recycleView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                int lastVisibleItemPosition = manager.findLastVisibleItemPosition();
                int firstVisibleItemPosition = manager.findFirstVisibleItemPosition();
                int itemCount = manager.getItemCount();
                Log.e("ReadActivity", firstVisibleItemPosition + "::" + lastVisibleItemPosition + "::" + itemCount + "::" + dx + "::" + dy + "::" + mData.size());
                if (isHor) {
                    if (firstVisibleItemPosition == lastVisibleItemPosition && lastVisibleItemPosition >= itemCount - 1 && dx >= 0) {
                        if (nextIsLocked) {
                            showNextLockView();
                            nextIsLocked = false;
                        }
                    }
                    if (firstVisibleItemPosition == lastVisibleItemPosition && firstVisibleItemPosition <= 0 && dx <= 0) {
                        if (preIsLocked) {
                            showPreLockView();
                            preIsLocked = false;
                        }
                    }
                } else {
                    if (lastVisibleItemPosition >= itemCount - 1 && dy >= 0) {
                        if (nextIsLocked) {
                            showNextLockView();
                            nextIsLocked = false;
                        }
//                        loadMore();
//                        setFootView();
                    }
                    if (firstVisibleItemPosition <= 0 && dy < 0) {
                        if (preIsLocked) {
                            showPreLockView();
                            preIsLocked = false;
                        }
//                        loadHeadMore();
//                        setHeadView();
                    }
                }
            }
        });

        switch_mode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isHor) {
                    isHor = false;
                    switch_mode.setText("切换成横向");
                    horSnapHelper.attachToRecyclerView(null);
                    manager.setOrientation(LinearLayoutManager.VERTICAL);
//                    recycleView.setLayoutManager(manager);
//                    mAdapter.notifyDataSetChanged();
                } else {
                    isHor = true;
                    switch_mode.setText("切换成竖向");
                    manager.setOrientation(LinearLayoutManager.HORIZONTAL);
//                    recycleView.setLayoutManager(manager);
                    horSnapHelper.attachToRecyclerView(recycleView);
//                    mAdapter.notifyDataSetChanged();
                }
            }
        });
    }

    private void showNextLockView() {
        final ContentPageBean pageBean = new ContentPageBean();
        pageBean.id = "1";
        pageBean.muilView = 1;
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                mAdapter.addBottomLock(pageBean);
            }
        }, 0);
    }

    private void showPreLockView() {
        final ContentPageBean pageBean = new ContentPageBean();
        pageBean.id = "0";
        pageBean.muilView = 1;
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                mAdapter.addHead(pageBean);
            }
        }, 0);
    }

//    private void setFootView() {
//        if (recycleView == null || recycleView.getAdapter() == null) return;
//        int itemCount = recycleView.getAdapter().getItemCount();
//        RecyclerView.ViewHolder viewHolder = recycleView.findViewHolderForAdapterPosition(itemCount - 1);
//        if (viewHolder instanceof FootViewHolder) {
//            FootViewHolder footViewHolder = (FootViewHolder) viewHolder;
//            if (isHor) {
//                footViewHolder.showHorNoMore();
//            } else {
//                footViewHolder.showNoMore();
//            }
//        }
//    }

//    protected void setHeadView() {
//        if (recycleView == null || recycleView.getAdapter() == null) return;
//        int itemCount = recycleView.getAdapter().getItemCount();
//        RecyclerView.ViewHolder viewHolder = recycleView.findViewHolderForAdapterPosition(0);
//        if (viewHolder instanceof FootViewHolder) {
//            FootViewHolder footViewHolder = (FootViewHolder) viewHolder;
//            if (isHor) {
//                footViewHolder.showHorFirst();
//            } else {
//                footViewHolder.showNoMore();
//            }
//        }
//    }


    private void loadHeadMore() {
        if (isLoading) {
            return;
        }
        isLoading = true;
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                List<String> temp = new ArrayList<>();
                temp.add("0");
                temp.add("1");
                temp.add("2");
                temp.add("3");
                temp.add("4");
//                temp.add("https:\\/\\/jpapps.qoo-app.com\\/comic-test\\/1001\\/3\\/88ee5b866a698e38c3fa294fc1835185.jpg");
//                temp.add("https:\\/\\/jpapps.qoo-app.com\\/comic-test\\/1001\\/3\\/6699ffded74fc3d11105b1e956a93bc9.jpg");
//                temp.add("https:\\/\\/jpapps.qoo-app.com\\/comic-test\\/1001\\/3\\/e787b382db8160362afd065ea6311b19.jpg");
//                temp.add("https:\\/\\/jpapps.qoo-app.com\\/comic-test\\/1001\\/3\\/e1f638cdc3ab5846ad03fa2f5dafa355.jpg");
//                temp.add("https:\\/\\/jpapps.qoo-app.com\\/comic-test\\/1001\\/3\\/0799dad7a6ed0f09b85c85c6f65c854a.jpg");
//                temp.add("https:\\/\\/jpapps.qoo-app.com\\/comic-test\\/1001\\/3\\/805d95203a4d2b09c8866c5a1c7ce5b7.jpg");
                urls.addAll(0, temp);
                mAdapter.notifyItemRangeInserted(0, temp.size());
//                mAdapter.notifyItemRangeChanged(temp.size(), urls.size() - temp.size() + 1);
//                mAdapter.notifyItemRangeChanged(temp.size() + 1, urls.size() - temp.size() + 1);
//                mAdapter.notifyDataSetChanged();
                isLoading = false;
            }
        }, 2000);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 0) {
            if (!TextUtils.isEmpty(chargeId)) {
                for (int i = 0; i < mData.size(); i++) {
                    if (mData.get(i).id.equals(chargeId)) {
                        mData.get(i).title = "充值完成";
                        mAdapter.notifyItemChanged(i);
                    }
                }
            }
        }
    }

    private void loadMore() {
        Log.e(TAG, "loadMore");
        if (isLoading) {
            return;
        }
        isLoading = true;
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                urls.add("https:\\/\\/jpapps.qoo-app.com\\/comic-test\\/1001\\/3\\/88ee5b866a698e38c3fa294fc1835185.jpg");
                urls.add("https:\\/\\/jpapps.qoo-app.com\\/comic-test\\/1001\\/3\\/6699ffded74fc3d11105b1e956a93bc9.jpg");
                urls.add("https:\\/\\/jpapps.qoo-app.com\\/comic-test\\/1001\\/3\\/e787b382db8160362afd065ea6311b19.jpg");
                urls.add("https:\\/\\/jpapps.qoo-app.com\\/comic-test\\/1001\\/3\\/e1f638cdc3ab5846ad03fa2f5dafa355.jpg");
                urls.add("https:\\/\\/jpapps.qoo-app.com\\/comic-test\\/1001\\/3\\/0799dad7a6ed0f09b85c85c6f65c854a.jpg");
                urls.add("https:\\/\\/jpapps.qoo-app.com\\/comic-test\\/1001\\/3\\/805d95203a4d2b09c8866c5a1c7ce5b7.jpg");
                mAdapter.notifyDataSetChanged();
                isLoading = false;
            }
        }, 2000);
    }

//    private void reflectMethod() {
//        try {
//            Method method = SnapHelper.class.getDeclaredMethod("destroyCallbacks", null);
//            method.setAccessible(true);
//            method.invoke(horSnapHelper, null);
//
//        } catch (NoSuchMethodException e) {
//            e.printStackTrace();
//        } catch (IllegalAccessException e) {
//            e.printStackTrace();
//        } catch (InvocationTargetException e) {
//            e.printStackTrace();
//        }
//
//    }
//
//    public void reflectField() {
//        Field field = null;
//        try {
//            field = SnapHelper.class.
//                    getDeclaredField("mRecyclerView");
//            field.setAccessible(true);
//            RecyclerView recyclerView = (RecyclerView) field.get(horSnapHelper);
//            recyclerView = null;
//        } catch (NoSuchFieldException e) {
//            e.printStackTrace();
//        } catch (IllegalAccessException e) {
//            e.printStackTrace();
//        }
//
//    }

}
