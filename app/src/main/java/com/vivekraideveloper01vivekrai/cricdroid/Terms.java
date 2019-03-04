package com.vivekraideveloper01vivekrai.cricdroid;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.r0adkll.slidr.Slidr;
public class Terms extends AppCompatActivity {

    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_terms);
        Slidr.attach(this);
        toolbar = findViewById(R.id.termsToolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Terms");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
}

