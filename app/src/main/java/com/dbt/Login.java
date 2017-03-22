package com.dbt;

import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.dbt.Application.App;
import com.parse.ParseUser;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class Login extends AppCompatActivity implements View.OnClickListener {


    protected EditText emailEd, passEd;
    protected Button loginBtn, gotoRegister;
    protected TextView forgot, loginHeader;
    protected App a;
    private String lname, lemail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.login);
        initView();

    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.login_btn) {
            //TODO: Add Login Parse Button Action Code
            a.showProgressDialog("Please Wait", "Logging in ..", this);
            Toast.makeText(this, "Login Button Clicked : " + emailEd.getText() + "-" + passEd.getText(), Toast.LENGTH_SHORT).show();
        } else if (view.getId() == R.id.goto_register) {
            //TODO: Add Register Button Action Code
            startActivity(new Intent(Login.this, Registration.class));
            Toast.makeText(this, "Register Button Clicked ", Toast.LENGTH_SHORT).show();
        }
    }

    private void initView() {
        a = new App(this);
        emailEd = (EditText) findViewById(R.id.email_ed);
        passEd = (EditText) findViewById(R.id.pass_ed);
        loginBtn = (Button) findViewById(R.id.login_btn);
        loginBtn.setOnClickListener(Login.this);
        forgot = (TextView) findViewById(R.id.forgot);
        gotoRegister = (Button) findViewById(R.id.goto_register);
        gotoRegister.setOnClickListener(Login.this);
        loginHeader = (TextView) findViewById(R.id.login_header);
        loginHeader.setPaintFlags(loginHeader.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
    }
}
