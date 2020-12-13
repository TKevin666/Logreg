package com.example.logreg;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class LoggedInActivity extends AppCompatActivity {

    TextView felhasznaloNeve;
    Button logout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logged_in);

        felhasznaloNeve.setText(getIntent().getStringExtra("nev"));

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent main = new Intent(LoggedInActivity.this, MainActivity.class);
                startActivity(main);
                finish();
            }
        });

    }

    private void init(){
        felhasznaloNeve=findViewById(R.id.felhasznaloneve);
        logout=findViewById(R.id.btn_logout);
    }
}