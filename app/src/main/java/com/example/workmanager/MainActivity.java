package com.example.workmanager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.work.OneTimeWorkRequest;
import androidx.work.PeriodicWorkRequest;
import androidx.work.WorkInfo;
import androidx.work.WorkManager;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {

    Button btn_performwork;
    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //final OneTimeWorkRequest request = new OneTimeWorkRequest.Builder(Myworkerthread.class).build();

        final PeriodicWorkRequest refreshWork = new PeriodicWorkRequest.Builder(Myworkerthread.class, 16, TimeUnit.MINUTES).build();
        btn_performwork=findViewById(R.id.btn_perform);
        textView=findViewById(R.id.tv_workinfo);

        btn_performwork.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                WorkManager.getInstance().enqueue(refreshWork);
            }
        });

        WorkManager.getInstance().getWorkInfoByIdLiveData(refreshWork.getId()).observe(this, new Observer<WorkInfo>() {
            @Override
            public void onChanged(WorkInfo workInfo) {
                String status = workInfo.getState().name();

                textView.append(status+"\n");
            }
        });
    }
}
