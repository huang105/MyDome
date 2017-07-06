package bwei.huanghui.headdomes2;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_main);
        Timer t=new Timer();
        t.schedule(new TimerTask() {
            @Override
            public void run() {
                Intent it=new Intent(MainActivity.this,HomeActivity.class);
                startActivity(it);
            }
        },3000);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
