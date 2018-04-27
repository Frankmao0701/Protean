package com.frank.protean;

import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Created by Frankmao on 2018/3/26.
 */

public class MessengerService extends Service {
    private static final String TAG = MessengerService.class.getSimpleName();
    private static final int MSG_FROM_CLIENT = 0x01;
    private Messenger mMessenger = new Messenger(new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case MSG_FROM_CLIENT:
                    Log.e(TAG, "receive msg from Client:" + msg.getData().getString("test_url"));
                    getAsycData(msg.getData().getString("test_url"), msg);
                    break;
                default:
                    super.handleMessage(msg);
            }

        }
    });

    //模拟异步请求
    private void getAsycData(String url, Message msg) {
        Messenger client = msg.replyTo;
        Message relpyMessage = Message.obtain(null, 2);
        SystemClock.sleep(5000);
        Bundle bundle = new Bundle();
        bundle.putString("test", "嗯，你的消息我已经收到，现在给你数据。");
        relpyMessage.setData(bundle);
        try {
            client.send(relpyMessage);
        } catch (RemoteException e) {
            e.printStackTrace();
        }

    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return mMessenger.getBinder();
    }
}