package com.example.logreg;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {

    EditText emailcim,felhasznalo,password,teljes;
    Button reg,vissza;
    DataBase dataBase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        init();

        vissza.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent main = new Intent(RegisterActivity.this, MainActivity.class);
                startActivity(main);
                finish();
            }
        });

        reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                regist();
            }
        });



    }

    private void init(){
        emailcim=findViewById(R.id.felhasznalo_Email);
        felhasznalo=findViewById(R.id.felhasznalo_felhnev);
        password=findViewById(R.id.felhasznalo_jelszo);
        teljes=findViewById(R.id.felhasznalo_teljesnev);
        reg=findViewById(R.id.btn_reg);
        vissza=findViewById(R.id.btn_vissza);
        dataBase=new DataBase(RegisterActivity.this);
    }

    public void regist() {
        if(!emailcim.getText().toString().isEmpty() && !felhasznalo.getText().toString().isEmpty() && !password.getText().toString().isEmpty() && !teljes.getText().toString().isEmpty()) {
            boolean ok = dataBase.register(emailcim.getText().toString(), felhasznalo.getText().toString(), password.getText().toString(), teljes.getText().toString());
            if(ok = true) {
                Toast.makeText(this, "Sikeres regisztráció", Toast.LENGTH_SHORT).show();
                Intent main = new Intent(RegisterActivity.this, MainActivity.class);
                startActivity(main);
                finish();
            } else {
                Toast.makeText(this, "Sikertelen regisztráció", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this, "A regisztrációhoz az összes mező kitöltése kötelező", Toast.LENGTH_SHORT).show();
        }
    }
}