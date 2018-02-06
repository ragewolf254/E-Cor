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
import android.widget.Toast;

public class LoginPage extends AppCompatActivity {
    private TextView m_username;
    private TextView m_password;
    private Button m_loginButton;
    private ImageView m_imageView;
    private String username;
    private String password;
    private Intent[] homePage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);
        m_username = findViewById(R.id.usernameTextField);
        m_password = findViewById(R.id.passwordTextField);
        m_loginButton = findViewById(R.id.loginButton);
        m_imageView = findViewById(R.id.parentLogo);
        homePage = new Intent[1];

        m_loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                username = m_username.getText().toString();
                password = m_password.getText().toString();
                if (username.equals("test") && password.equals("test")){
                    homePage[0] = new Intent(LoginPage.this, HomePage.class);
                    startActivity(homePage[0]);
                    finish();
                } else {
                    Snackbar.make(m_imageView, "Login Failed", Snackbar.LENGTH_LONG).show();
                }
            }
        });

    }
}
