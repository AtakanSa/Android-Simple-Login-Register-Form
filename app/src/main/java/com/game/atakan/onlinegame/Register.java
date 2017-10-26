package com.game.atakan.onlinegame;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
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

import static java.lang.Character.isLetter;

public class Register extends Activity {

    EditText Username,Password,Name,Email;
    String username,password,name,email;
    TextView tx1,tx2,tx3,tx4;
    boolean isLegit=false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        Username = (EditText)findViewById(R.id.editText);
        Password = (EditText)findViewById(R.id.editText2);
        Name = (EditText)findViewById(R.id.editText3);
        Email = (EditText)findViewById(R.id.editText4);

        tx1 = (TextView) findViewById(R.id.textView3);
        tx2 = (TextView) findViewById(R.id.textView4);
        tx3 = (TextView) findViewById(R.id.textView5);
        tx4 = (TextView) findViewById(R.id.textView6);

        tx1.setVisibility(View.GONE);
        tx2.setVisibility(View.GONE);
        tx3.setVisibility(View.GONE);
        tx4.setVisibility(View.GONE);
    }

    public void saveInfo(View view){
        username = Username.getText().toString();
        password = Password.getText().toString();
        name = Name.getText().toString();
        email = Email.getText().toString();

        BackgroundTask backgroundTask = new BackgroundTask();

        if(!nameValidate(username).equals("")){
            tx1.setVisibility(View.VISIBLE);
            tx1.setText(nameValidate(username));
            isLegit=false;
        }
        else{
            tx1.setVisibility(View.GONE);
            isLegit=true;
        }
        if(!passValidate(password).equals("")){
            tx2.setVisibility(View.VISIBLE);
            tx2.setText(passValidate(password));
            isLegit=false;
        }
        else{
            tx2.setVisibility(View.GONE);
            isLegit=true;
        }
        if(!nameValidate(name).equals("")){
            tx3.setVisibility(View.VISIBLE);
            tx3.setText(nameValidate(name));
            isLegit=false;
        }
        else{
            tx3.setVisibility(View.GONE);
            isLegit=true;
        }
        if(!mailValidate(email).equals("")){
            tx4.setVisibility(View.VISIBLE);
            tx4.setText(mailValidate(email));
            isLegit=false;
        }
        else{
            tx4.setVisibility(View.GONE);
            isLegit=true;
        }

        if(isLegit){
            backgroundTask.execute(name,password,name,email);
            finish();
        }

    }

    public String nameValidate(String name){

        if(name.contains("@") || name.contains("!") || name.contains(".") || name.contains("'") || name.contains("$"))
            return "İsim Özel Karakterler İçeremez";
        int letterCount =0;
        for(int i=0;i<name.length();i++){

            if(isLetter(name.charAt(i)))
                letterCount++;
        }
        if(letterCount<2){
        return "İsim 2 harften kısa olamaz";
        }

        if(name.length()>12){
            return "İsim 12 karakterden uzun olamaz";
        }
        return "";
    }

    public String passValidate(String pass){
        int letterCount =0;
        for(int i=0;i<pass.length();i++){

            if(isLetter(pass.charAt(i)))
                letterCount++;
        }
        if(letterCount<2){
        return "Şifre 2 karakterden kısa olamaz";
        }
        else if(letterCount>12){
            return "Şifre 12 karakterden uzun olamaz";
        }


        return "";
    }


    public String mailValidate(String mail) {

        if(mail.indexOf(' ') != -1)
            return "Geçerli bir mail giriniz";
        if(mail.indexOf('@') == -1)
            return "Geçerli bir mail giriniz";
        if(mail.indexOf('.') == -1)
            return "Geçerli bir mail giriniz";
        if(mail.charAt(0) == '@')
            return "Geçerli bir mail giriniz";
        if(mail.charAt(0) == '.')
            return "Geçerli bir mail giriniz";
        if(mail.charAt(mail.indexOf('@')+1) == '.')
            return "Geçerli bir mail giriniz";
        if((mail.indexOf('.')+2) == mail.length())
            return "Geçerli bir mail giriniz";

        return "";

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
