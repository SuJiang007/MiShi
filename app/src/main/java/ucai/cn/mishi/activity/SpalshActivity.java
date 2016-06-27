package ucai.cn.mishi.activity;

import android.content.Intent;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import ucai.cn.mishi.R;

public class SpalshActivity extends AppCompatActivity {

    ImageView miv_splash;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spalsh);
        initView();
    }

    private void initView() {
        miv_splash = (ImageView) findViewById(R.id.splash);
    }

    @Override
    protected void onStart() {
        super.onStart();
        new Thread(new Runnable() {
            @Override
            public void run() {
                SystemClock.sleep(2000);
                startActivity(new Intent(SpalshActivity.this,MainActivity.class));
                finish();
            }
        }).start();
    }
}
