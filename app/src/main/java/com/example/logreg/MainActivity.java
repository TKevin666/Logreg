package com.example.logreg;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button login;
    Button register;
    EditText felhnev;
    EditText jelszo;
    SharedPreferences shared;
    SharedPreferences.Editor editor;
    DataBase dataBase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (felhnev.getText().length()>0 && jelszo.getText().length()>0){
                    Intent regist=new Intent(MainActivity.this,RegisterActivity.class);
                    startActivity(regist);
                    finish();
                }
                else{
                    hiba();
                }
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bejelentkezes();
            }
        });

    }

    private void init(){

        login=findViewById(R.id.btn_login);
        register=findViewById(R.id.btn_reg);
        felhnev=findViewById(R.id.input_felhnev);
        jelszo=findViewById(R.id.input_jelszo);
        shared=getSharedPreferences("data", Context.MODE_PRIVATE);
        editor=shared.edit();
        dataBase=new DataBase(MainActivity.this);

    }

    private void hiba(){
        Toast.makeText(this, "Nem megfelelő adatok!", Toast.LENGTH_SHORT).show();
    }

    private void bejelentkezes() {
        if(!(felhnev.getText().toString().isEmpty() && jelszo.getText().toString().isEmpty())) {
            Cursor adatok = dataBase.login(felhnev.getText().toString(), jelszo.getText().toString());
            if(adatok.getCount() == 1) {
                Intent logged = new Intent(MainActivity.this, LoggedInActivity.class);
                while (adatok.moveToNext()){
                    logged.putExtra("nev", adatok.getString(0));
                }
                startActivity(logged);
                finish();
            } else {
                hiba();
            }
        } else {
            Toast.makeText(this, "A bejelntkezéshez kötelező adatokat megadni!", Toast.LENGTH_SHORT).show();
        }
    }
}