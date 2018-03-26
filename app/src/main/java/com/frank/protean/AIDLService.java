package com.frank.protean;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteCallbackList;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.util.Log;

import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Created by Frankmao on 2018/3/23.
 */

public class AIDLService extends Service {
    private final String TAG = "Frank Server";
    private Vetify vetify;
    private AtomicBoolean mIsServiceDestoryed = new AtomicBoolean(false);

    @Override
    public void onCreate() {
        super.onCreate();

    }

    private RemoteCallbackList<IVetifyBackListener> mListenerList = new RemoteCallbackList<>();

    private final VetifyController.Stub stub = new VetifyController.Stub() {
        @Override
        public Vetify getVetify() throws RemoteException {
            new Thread(new ServiceWorker()).start();
            return vetify;
        }

        @Override
        public void addVetifyInOut(Vetify vetify) throws RemoteException {

        }

        @Override
        public void registerListener(IVetifyBackListener listener) throws RemoteException {
            mListenerList.register(listener);
            final int N = mListenerList.beginBroadcast();
            mListenerList.finishBroadcast();
            Log.d(TAG, "registerListener, current size:" + N);
        }

        @Override
        public void unRegisterListener(IVetifyBackListener listener) throws RemoteException {
            boolean success = mListenerList.unregister(listener);

            if (success) {
                Log.d(TAG, "unregister success.");
            } else {
                Log.d(TAG, "not found, can not unregister.");
            }
            final int N = mListenerList.beginBroadcast();
            mListenerList.finishBroadcast();
            Log.d(TAG, "unregisterListener, current size:" + N);
        }
    };

    @Override
    public void onDestroy() {
        mIsServiceDestoryed.set(true);
        super.onDestroy();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return stub;
    }


    private void onVetify(Vetify vetify) {
        final int N = mListenerList.beginBroadcast();
        for (int i = 0; i < N; i++) {
            IVetifyBackListener l = mListenerList.getBroadcastItem(i);
            if (l != null) {
                try {
                    l.onVetifyBack(vetify);//onNewBookArrived()方法在客户端的binder线程池中执行, 当前线程被挂起, 因此如果是耗时操作的话， 注意不要在UI线程执行客户端的aidl方法().
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
        }
        mListenerList.finishBroadcast();

    }

    private class ServiceWorker implements Runnable {
        @Override
        public void run() {
            // do background processing here.....
            if (!mIsServiceDestoryed.get()) {
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Vetify vetify = new Vetify();
                vetify.setId("回调Id");
                vetify.setToken("回调Token");
                onVetify(vetify);
            }
        }
    }


}
