package com.example.workmanager;

import android.app.AlarmManager;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.provider.Settings;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

import static androidx.core.content.ContextCompat.getSystemService;

public class Myworkerthread extends Worker {

    android.os.Handler Customhandler = new android.os.Handler();

    public Myworkerthread(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
    }

    @NonNull
    @Override
    public Result doWork() {
        displayNotificationmanager("work will be here ","work is finished");
       //setAlaram(System.currentTimeMillis()+(60*1000));


        return Result.SUCCESS;
    }



    public void displayNotificationmanager(String task,String desc){

        NotificationManager manager = (NotificationManager) getApplicationContext().getSystemService(Context.NOTIFICATION_SERVICE);

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            NotificationChannel chanel_1 = new NotificationChannel("simpletask","onemoretask",NotificationManager.IMPORTANCE_DEFAULT);


            manager.createNotificationChannel(chanel_1);

        }

        NotificationCompat.Builder builder = new NotificationCompat.Builder(getApplicationContext(),"simpletask")
                .setContentTitle(task)
                .setContentText(desc)
                .setSmallIcon(R.mipmap.ic_launcher);

        manager.notify(1,builder.build());

    }


}
