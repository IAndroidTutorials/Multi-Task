package com.prateekj;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class HomeActivity extends Activity {
    private Integer counter = 0;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);
    }

    public void moveToPushupCounter(View view) {
        Intent intent = new Intent(this, MyActivity.class);
        startActivity(intent);
    }

}
