package com.example.ccw.midtermexam;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.HashMap;

public class RegisterActivity extends AppCompatActivity{

    EditText email,password,name,phone,address;
    Button register,back;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reg);

        email = (EditText) findViewById(R.id.Rusername);
        password = (EditText) findViewById(R.id.password);
        name = (EditText) findViewById(R.id.Name);
        phone = (EditText) findViewById(R.id.phoneNumber);
        address = (EditText) findViewById(R.id.userAddress);
        register = (Button) findViewById(R.id.registerbtn);
        back = (Button) findViewById(R.id.backbtn);


        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (email.getText().toString().equals("")){
                    email.setError("This field cannot be empty");
                }
                if (password.getText().toString().equals("")){
                    password.setError("This field cannot be empty");
                }
                if (name.getText().toString().equals("")){
                    name.setError("This field cannot be empty");
                }
                if (phone.getText().toString().equals("")){
                    phone.setError("This field cannot be empty");
                }
                if (address.getText().toString().equals("")){
                    address.setError("This field cannot be empty");
                }

                else {
                adduser(v);
                }
            }
        });


        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegisterActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });

    }

    public void adduser(View v){
        final String mail = email.getText().toString();
        final String pass = password.getText().toString();
        final String Username = name.getText().toString();
        final String Phone = phone.getText().toString();
        final String Address = address.getText().toString();


        class AddUser extends AsyncTask<Void,Void,String> {

            @Override
            protected String doInBackground(Void... voids) {
                HashMap<String, String> hashMap = new HashMap<>();
                hashMap.put("username", mail);
                hashMap.put("password", pass);
                hashMap.put("name", Username);
                hashMap.put("address", Address);
                hashMap.put("phone", Phone);

                RequestHandler rh = new RequestHandler();
                String s = rh.sendPostRequest(" http://slumberjer.com/android/a172/midterm/register.php", hashMap);
                return s;
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                if (s.equalsIgnoreCase("success")){
                    Toast.makeText(RegisterActivity.this, "Success", Toast.LENGTH_SHORT).show();

                }
                else
                    Toast.makeText(RegisterActivity.this, "Failed", Toast.LENGTH_SHORT).show();


            }

        }
       AddUser adduser = new AddUser();
        adduser.execute();

    }
}

