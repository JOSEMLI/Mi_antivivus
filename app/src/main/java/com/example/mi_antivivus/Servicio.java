package com.example.mi_antivivus;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

public class Servicio extends Activity {

    //private final VibrateReceiver receiver = new VibrateReceiver();
    private IntentFilter intentFilter;

    @Override
    protected void onStop() {
        super.onStop();
        NotificationManager notificationManager = (NotificationManager)
                getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.cancel(2);
    }

    @Override
    protected void onResume() {
        super.onResume();
        NotificationManager notificationManager = (NotificationManager)
                getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.cancel(3);
    }

    @Override
    protected void onDestroy() {
        //unregisterReceiver(receiver);
        super.onDestroy();
    }

    @Override public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().addFlags(
                WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON |WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD|
                        WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED);
        setContentView(R.layout.activity_servicio);

        setContentView(R.layout.activity_servicio);

        intentFilter = new IntentFilter("RESPONSE");
        intentFilter.addCategory(Intent.CATEGORY_DEFAULT);
        //registerReceiver(receiver, intentFilter);



        Button arrancar = (Button) findViewById(R.id.boton_arrancar);
        arrancar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    startForegroundService(new Intent(Servicio.this, ServicioMusica.class));
                } else {
                    startService(new Intent(Servicio.this,
                            ServicioMusica.class));
                }
            }
        });

        Button detener = (Button) findViewById(R.id.boton_detener);
        detener.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                stopService(new Intent(Servicio.this,
                        ServicioMusica.class));
            }
        });
    }
}