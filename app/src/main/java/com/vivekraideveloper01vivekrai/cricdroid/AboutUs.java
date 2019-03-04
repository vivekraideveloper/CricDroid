package com.vivekraideveloper01vivekrai.cricdroid;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.r0adkll.slidr.Slidr;

public class AboutUs extends AppCompatActivity {
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);
        Slidr.attach(this);
        toolbar = findViewById(R.id.aboutUsToolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("About US");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
}
