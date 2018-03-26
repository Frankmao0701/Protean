package com.frank.client;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.frank.protean.IVetifyBackListener;
import com.frank.protean.Vetify;
import com.frank.protean.VetifyController;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = "Frank Client";
    private boolean connected;
    private TextView tv_get;
    private TextView tv_motify;
    private VetifyController vetifyController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv_get = findViewById(R.id.tv_get);
        tv_motify = findViewById(R.id.tv_motify);
        tv_get.setOnClickListener(this);
        tv_motify.setOnClickListener(this);
        bindServer();
    }

    private ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            vetifyController = VetifyController.Stub.asInterface(service);
            try {
                vetifyController.registerListener(iVetifyBackListener);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
            connected = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            connected = false;
        }
    };

    private IVetifyBackListener iVetifyBackListener = new IVetifyBackListener.Stub() {


        @Override
        public void onVetifyBack(Vetify vetify) throws RemoteException {
            Log.e(TAG, "id token::" + vetify.getId() + vetify.getToken());
        }
    };


    private void bindServer() {
        Intent intent = new Intent();
        intent.setPackage("com.frank.protean");
        intent.setAction("com.frank.server.action");
        bindService(intent, serviceConnection, Context.BIND_AUTO_CREATE);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (vetifyController != null && vetifyController.asBinder().isBinderAlive()) {
            try {
                vetifyController.unRegisterListener(iVetifyBackListener);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
        if (connected) {
            unbindService(serviceConnection);
        }
    }

    private Vetify vetify;

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_get:
                if (connected) {
                    try {
                        vetify = vetifyController.getVetify();
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                }
                break;
            case R.id.tv_motify:
                break;
        }
    }
}
