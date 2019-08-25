package com.zhanggouzi.servicecarddemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class DemoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo);
        Intent intent = getIntent();

        String summary = intent.getStringExtra("INTENT_KEY_DATA");
        String detail = intent.getStringExtra("INTENT_KEY_ALL");

        ((TextView) findViewById(R.id.summary)).setText(summary);
        ((TextView ) findViewById(R.id.detail)).setText(detail);

    }
}
