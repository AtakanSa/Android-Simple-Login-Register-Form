package com.game.atakan.onlinegame;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends Activity {

    Button b1, b2;
    TextView tv;
    EditText etUsername,etPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        b1 = (Button) findViewById(R.id.button);
        b2 = (Button) findViewById(R.id.button2);
        tv = (TextView) findViewById(R.id.textView);
        etUsername = (EditText)findViewById(R.id.editText5);
        etPassword = (EditText)findViewById(R.id.editText6);


        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();

        if (networkInfo != null && networkInfo.isConnected()) {
            tv.setVisibility(View.INVISIBLE);
        } else {
            b1.setEnabled(false);
            b2.setEnabled(false);
        }
    }

    public void Register(View view) {
        startActivity(new Intent(this, Register.class));
    }

    public void Login(View view){
        Login login = new Login(this);
        String user,pass;
        user = etUsername.getText().toString();
        pass = etPassword.getText().toString();
        login.execute("login",user,pass);

    }
}