package com.ragew.creativesolutions.e_cor;

import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.ragew.creativesolutions.e_cor.Config.TaskConfig;
import com.ragew.creativesolutions.e_cor.Utils.SuperTask;

import org.json.JSONException;
import org.json.JSONObject;

public class LoginPage extends AppCompatActivity implements SuperTask.TaskListener {
    private TextView m_username;
    private TextView m_password;
    private Button m_loginButton;
    private ImageView m_imageView;
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private String email;
    private String contactNumber;
    private String clientAddress;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);
        m_username = findViewById(R.id.usernameTextField);
        m_password = findViewById(R.id.passwordTextField);
        m_loginButton = findViewById(R.id.loginButton);
        m_imageView = findViewById(R.id.parentLogo);


        //final Intent homePage = new Intent(LoginPage.this, HomePage.class);
        //Snackbar.make(m_imageView, "Logout Success", Snackbar.LENGTH_LONG).show();


        m_loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                username = m_username.getText().toString();
                password = m_password.getText().toString();
                // call on button click
                SuperTask.execute(LoginPage.this,"login", TaskConfig.LOGIN_URL);
                SuperTask.execute(LoginPage.this,"names",TaskConfig.CLIENT_URL);
            }
        });

    }
    String myID;
    // implement superTask listener
    @Override
    public void onTaskRespond(String id, String json) {
        String dataDetails = json;
        Intent homePage = new Intent(LoginPage.this, HomePage.class);
        switch (id){
            case "login":{
                // parse json string here for user details

                try {
                    JSONObject m_userObject = new JSONObject(dataDetails);
                    //Toast.makeText(LoginPage.this, m_userObject.toString(),Toast.LENGTH_LONG).show();
                    boolean isSuccess = m_userObject.getBoolean("success");
                    if (isSuccess == true){
                        //Toast.makeText(LoginPage.this, "I'm Triggered",Toast.LENGTH_LONG).show();
                        startActivity(homePage);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                break;
            }
            case "names":{
                try {
                    JSONObject m_userObject = new JSONObject(dataDetails);
                    //Toast.makeText(LoginPage.this, m_userObject.toString(),Toast.LENGTH_LONG).show();
                    firstName = m_userObject.getString("client_firstname");
                    lastName = m_userObject.getString("client_lastname");
                    email = m_userObject.getString("client_email");
                    contactNumber = m_userObject.getString("client_contact_no");
                    clientAddress = m_userObject.getString("client_address");
                    //Toast.makeText(LoginPage.this, clientAddress,Toast.LENGTH_LONG).show();
                    //Toast.makeText(LoginPage.this, lastName,Toast.LENGTH_LONG).show();
                    //Toast.makeText(LoginPage.this, email,Toast.LENGTH_LONG).show();
                    homePage.putExtra("firstName", firstName);
                    homePage.putExtra("lastName", lastName);
                    homePage.putExtra("email", email);
                    homePage.putExtra("contact",contactNumber);
                    homePage.putExtra("address",clientAddress);
                    startActivity(homePage);
                }catch (JSONException x){
                    x.printStackTrace();
                }
            }
        }
    }



    @Override
    public ContentValues setRequestValues(String id, ContentValues contentValues) {
        // put values to contentValues
        // put(key, value)
        // check controllers for the correct keys
        // $this->input->post(key)
        contentValues.put("username", this.username);
        contentValues.put("password", this.password);
        contentValues.put("client_id", 1);
        return contentValues;
    }
}
