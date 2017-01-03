package com.example.android.chargerpoints;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class HelpActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);

        setContentView(R.layout.activity_help);
        TextView backTextView = (TextView) findViewById(R.id.back);
        backTextView.setOnClickListener(new View.OnClickListener() {
            // The code in this method will be executed when the numbers View is clicked on.
            @Override
            public void onClick(View view) {
                Intent backIntent = new Intent(HelpActivity.this, MainActivity.class);
                startActivity(backIntent);
            }
        });
    }
}
