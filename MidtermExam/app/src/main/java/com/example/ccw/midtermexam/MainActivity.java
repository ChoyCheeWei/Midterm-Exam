package com.example.ccw.midtermexam;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity {
    EditText email, password;
    Button login, cancel;
    TextView tvRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvRegister = (TextView) findViewById(R.id.Registertv);
        email = (EditText) findViewById(R.id.Useremail);
        password = (EditText) findViewById(R.id.pass);
        cancel = (Button) findViewById(R.id.cancelbtn);
        login = (Button) findViewById(R.id.loginbtn);

        login.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (email.getText().toString().equals("")){
                    email.setError("This field cannot be empty");
                }
                if (password.getText().toString().equals("")){
                    password.setError("This field cannot be empty");
                }

                else {
                    login(v);
                }
            }

        });

        tvRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,RegisterActivity.class);
                startActivity(intent);
            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finishAffinity();
                System.exit(0);
            }
        });


    }

    public void login (View v){
        final String mail = email.getText().toString();
        final String pass = password.getText().toString();

        class Login extends AsyncTask<Void, Void, String> {

            @Override
            protected String doInBackground(Void... voids) {
                HashMap<String, String> hashMap = new HashMap<>();
                hashMap.put("username", mail);
                hashMap.put("password", pass);


                RequestHandler rh = new RequestHandler();
                String s = rh.sendPostRequest(" http://slumberjer.com/android/a172/midterm/login.php", hashMap);
                return s;
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                if (s.equalsIgnoreCase("success")) {
                    Toast.makeText(MainActivity.this, "Success", Toast.LENGTH_SHORT).show();

                }
                else {
                    Toast.makeText(MainActivity.this, "Failed", Toast.LENGTH_SHORT).show();
                }
            }
        }
        Login login = new Login();
        login.execute();
        }
}

