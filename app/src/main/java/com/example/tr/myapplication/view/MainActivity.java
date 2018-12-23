package com.example.tr.myapplication.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.tr.myapplication.MyApplication;
import com.example.tr.myapplication.R;
import com.example.tr.myapplication.domain.work.MyWorker;
import com.example.tr.myapplication.view.mvp.MainActivityPresenter;
import com.example.tr.myapplication.view.mvp.view.IMainActivityView;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import javax.inject.Inject;

import androidx.work.Constraints;
import androidx.work.Data;
import androidx.work.OneTimeWorkRequest;
import androidx.work.WorkManager;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity implements IMainActivityView {

    @BindView(R.id.textView2)
    TextView textViewResults;

    @Inject
    MainActivityPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        ((MyApplication) getApplication())
                .getAppComponent()
                .inject(MainActivity.this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        presenter.start();
        presenter.setView(this);
    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.resume();
    }

    @Override
    public void onPause() {
        super.onPause();
        presenter.pause();
    }

    @Override
    protected void onStop() {
        super.onStop();
        presenter.stop();
    }

    @OnClick(R.id.button)
    void submit() {
        presenter.doPriQ();
    }

    @OnClick(R.id.button2)
    void submit2() {
        Executor executor = Executors.newSingleThreadExecutor();
        executor.execute(() -> {
            long time = System.currentTimeMillis();
            runOnUiThread(() -> textViewResults.setText("" + time));
        });
    }

    @OnClick(R.id.button3)
    void submit3() {
        Data data = new Data.Builder()
                .putString(MyWorker.EXTRA_TITLE, "Message from Activity!")
                .putString(MyWorker.EXTRA_TEXT, "Hi! I have come from activity.")
                .build();

        Constraints constraints = new Constraints.Builder()
                .setRequiresCharging(true)
                .build();

        final OneTimeWorkRequest simpleRequest = new OneTimeWorkRequest.Builder(MyWorker.class)
                .setInputData(data)
                .setConstraints(constraints)
                .addTag("simple_work")
                .build();

        WorkManager.getInstance().enqueue(simpleRequest);

        WorkManager.getInstance().getWorkInfoByIdLiveData(simpleRequest.getId())
                .observe(this, workInfo -> {
                    if (workInfo != null) {
                        textViewResults.setText("SimpleWorkRequest: " + workInfo.getState().name() + "\n");
                    }

                    if (workInfo != null && workInfo.getState().isFinished()) {
                        String message = workInfo.getOutputData().getString(MyWorker.EXTRA_OUTPUT_MESSAGE);
                        textViewResults.setText("" + message);
                    }
                });
    }

    @Override
    public void showResultsFromJob(long time) {
        textViewResults.setText("" + time);
    }


}
