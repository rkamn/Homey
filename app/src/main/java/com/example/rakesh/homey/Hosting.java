package com.example.rakesh.homey;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class Hosting extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hosting);
    }

    public void nexthosting(View view) {
        Intent intent = new Intent(this,HostDetails.class);
        startActivity(intent);
    }
}
