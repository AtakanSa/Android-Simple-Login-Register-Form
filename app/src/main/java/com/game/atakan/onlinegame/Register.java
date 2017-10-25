package com.game.atakan.onlinegame;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

public class Register extends Activity {

    EditText Username,Password,Name,Email;
    String username,password,name,email;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        Username = (EditText)findViewById(R.id.editText);
        Password = (EditText)findViewById(R.id.editText2);
        Name = (EditText)findViewById(R.id.editText3);
        Email = (EditText)findViewById(R.id.editText4);
    }

    public void saveInfo(View view){
        username = Username.getText().toString();
        password = Password.getText().toString();
        name = Name.getText().toString();
        email = Email.getText().toString();

        BackgroundTask backgroundTask = new BackgroundTask();
        backgroundTask.execute(name,password,name,email);
        finish();
    }

    class BackgroundTask extends AsyncTask<String,Void,String>
    {
        String add_info_url;


        @Override
        protected void onPreExecute() {
            add_info_url ="http://atakansari.com/atakan/register.php";
        }

        @Override
        protected String doInBackground(String... params) {
            String Username,Password,Name,Email;
            Username = params[0];
            Password = params[1];
            Name = params[2];
            Email = params[3];

            try {
                URL url = new URL(add_info_url);
                HttpURLConnection httpURLConnection =(HttpURLConnection)url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream,"UTF-8"));
                String data_string = URLEncoder.encode("Username","UTF-8")+"="+ URLEncoder.encode(Username,"UTF-8")+"&"+
                        URLEncoder.encode("Password","UTF-8")+"="+ URLEncoder.encode(Password,"UTF-8")+"&"+
                        URLEncoder.encode("Name","UTF-8")+"="+ URLEncoder.encode(Name,"UTF-8")+"&"+
                        URLEncoder.encode("Email","UTF-8")+"="+ URLEncoder.encode(Email,"UTF-8");
                bufferedWriter.write(data_string);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();
                InputStream inputStream = httpURLConnection.getInputStream();
                inputStream.close();
                httpURLConnection.disconnect();
                return "Kayıt Oluşturuldu";

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }
        @Override
        protected void onPostExecute(String result) {
            Toast.makeText(getApplicationContext(), result, Toast.LENGTH_LONG).show();
        }
    }
}
