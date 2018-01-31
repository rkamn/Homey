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
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class Login1 extends AppCompatActivity implements View.OnClickListener{
    public static SharedPreferences sharedPreferences;
    private EditText etemail,etpassword;
    private ImageView ivlogo;
    private Button bsubmit,bsignup;
    private TextView tvregisterlink;
    private DatabaseHelper myDb;
    public static SharedPreferences.Editor editor;

    private String email, pwd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login1);

        myDb = new DatabaseHelper(this);
        etemail=(EditText) findViewById(R.id.etemail);
        etpassword=(EditText) findViewById(R.id.etpassword);

        bsubmit=(Button) findViewById(R.id.bsubmit);
        bsignup=(Button) findViewById(R.id.bsignup);



        bsubmit.setOnClickListener(this);
        bsignup.setOnClickListener(this);

    }
    @Override
    public void onClick(View v){
        switch (v.getId()){

            case  R.id.bsubmit:

                if(validateFields()) {
                    sharedPreferences = getSharedPreferences("Myprefs", MODE_PRIVATE);
                    String savedemail = sharedPreferences.getString("email", "");
                    String savedpassword = sharedPreferences.getString("pwd", "");

                    if(savedemail.equals(email) && savedpassword.equals(pwd)) {
                        Intent a = new Intent(this, Home.class);
                        startActivity(a);
                        Toast.makeText(this,"welcome sir",Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(this,"please register ur credentials",Toast.LENGTH_SHORT).show();
                    }
                }
                break;

            case R.id.bsignup:
                //logic for signup
                Intent Intent=new Intent(this,Register.class);
                startActivity(Intent);
                break;
           /* case   R.id.bwelcome:
                //display a toast
                break;*/
        }
    }
    public boolean validateFields(){
        email = etemail.getText().toString();
        pwd = etpassword.getText().toString();
        if ("".equals(email)){
            Toast.makeText(this,"please fill banks",Toast.LENGTH_SHORT).show();
            return false;
        }else if (TextUtils.isEmpty(email) || !Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            etemail.setError("enter valid email");
            return false;
        }else if("".equals(pwd)){
            Toast.makeText(this, "please fill the blank space", Toast.LENGTH_SHORT).show();
            return false;
        }else if(pwd.length()<5){
            etpassword.setError("password is too short");
            return false;
        }
        return true;
    }
}
