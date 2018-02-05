package com.ragew.creativesolutions.e_cor;

import android.content.Intent;
import android.media.Image;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class LoginPage extends AppCompatActivity {
    private EditText m_username = findViewById(R.id.usernameTextField);
    private EditText m_password = findViewById(R.id.passwordTextField);
    private Button m_loginButton = findViewById(R.id.loginButton);
    private ImageView m_imageView = findViewById(R.id.parentLogo);
    private String username;
    private String password;
    private Intent homePage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);
        username = m_username.getText().toString();
        password = m_password.getText().toString();
        m_loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (username == "test" && password == "test"){
                    homePage = new Intent(LoginPage.this, HomePage.class);
                    startActivity(homePage);
                    finish();
                } else {
                    Snackbar.make(m_imageView, "Login Failed", Snackbar.LENGTH_LONG).show();
                }
            }
        });

    }
}
