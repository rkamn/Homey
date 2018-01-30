package com.example.rakesh.homey;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.regex.Pattern;

public class Register extends AppCompatActivity implements View.OnClickListener {
    private EditText etfname,etphone,etemail,etpassword,etretypepassword;
    private Button blogin;
    DatabaseHelper myDb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);
        myDb=new DatabaseHelper(this);
        etfname=(EditText) findViewById(R.id.etfname);
        etemail=(EditText) findViewById(R.id.etemail);
        etphone=(EditText) findViewById(R.id.etphone);
        etpassword=(EditText)findViewById(R.id.etpassword);
        etretypepassword=(EditText)findViewById(R.id.etretypepassword);
        blogin=(Button) findViewById(R.id.blogin);
        blogin.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.blogin:
                if(validateFields()){
                    //*data lines inseted into database after validation
                    SharedPreferences preferences = getSharedPreferences("Myprefs" ,MODE_PRIVATE);

                    String email = etemail.getText().toString();
                    String password = etpassword.getText().toString();

                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putString("email", email);
                    editor.putString("pwd", password);

                    editor.apply();

                    //end data insetion lines
                    Intent b = new Intent(this, Home.class);
                    startActivity(b);
                    break;

                }
            /*case R.id.bpleaseregister:
                // write you code here
                break;*/
        }
    }
    public boolean validateFields(){
        if("".equals(etfname.getText().toString())){
            Toast.makeText(this,"please fill the blanks",Toast.LENGTH_SHORT).show();
            return false;
        }else if (etfname.getText().toString().length()<2){
            etfname.setError("name is too short");
            return false;
        }else if ("".equals(etemail.getText().toString())){
            Toast.makeText(this,"please fill banks",Toast.LENGTH_SHORT).show();
            return false;
        }else if (TextUtils.isEmpty(etemail.getText().toString()) || !Patterns.EMAIL_ADDRESS.matcher(etemail.getText().toString()).matches()){
            etemail.setError("enter valid email");
            return false;
        }else if("".equals(etphone.getText().toString())){
            Toast.makeText(this,"please fill blank space",Toast.LENGTH_SHORT).show();
            return false;
        }else if(etphone.getText().toString().length()<10){
            etphone.setError("Mobile no. too short");
            return false;
        }else if(!Pattern.compile("^[7-9]").matcher(etphone.getText().toString()).find()) {
            Toast.makeText(this,"please enter valid mobile no.",Toast.LENGTH_SHORT).show();
            return false;
        }else if("".equals(etpassword.getText().toString())){
            Toast.makeText(this, "please fill the blank space", Toast.LENGTH_SHORT).show();
            return false;
        }else if(etpassword.getText().toString().length()<6){
            etpassword.setError("password is too short");
            return false;
        }else if("".equals(etretypepassword.getText().toString())){
            Toast.makeText(this, "please fill the blank", Toast.LENGTH_SHORT).show();
            return false;
        }else if(!etpassword.getText().toString().equals(etretypepassword.getText().toString())){
            etretypepassword.setError("password not matches");
            return false;
        }
        return true;
    }

}