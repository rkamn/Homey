package com.example.rakesh.homey;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class ConfirmHostDetails extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.confirm_host_details);
    }

    public void hostsucessful(View view) {
        Intent intent = new Intent(this,Hostsucessful.class);
        startActivity(intent);
    }
}
