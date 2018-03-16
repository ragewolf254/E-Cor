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

    //Event Planner
    private String plannerFirstName;
    private String plannerLastName;
    private String plannerEmail;
    private String plannerContactNumber;
    private String plannerAddress;
    private String isActive;
    private Integer transactionFinish;
    //Client
    private String clientFirstName;
    private String clientLastName;
    private String clientEmail;
    private String clientContactNumber;
    private String clientAddress;
    private String clientID;
    private String eventName;


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
                SuperTask.execute(LoginPage.this,"planners", TaskConfig.PLANNER_URL);
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
                        //startActivity(homePage);
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
                    Log.d("All the info", m_userObject.toString());
                    //Client
                    clientID = m_userObject.getString("client_id");
                    clientFirstName = m_userObject.getString("client_firstname");
                    clientLastName = m_userObject.getString("client_lastname");
                    clientEmail = m_userObject.getString("client_email");
                    clientAddress = m_userObject.getString("client_address");
                    clientContactNumber = m_userObject.getString("client_contact_no");
                    eventName = m_userObject.getString("schedule_title");

                    //Planner
                    plannerFirstName = m_userObject.getString("event_planner_firstname");
                    plannerLastName = m_userObject.getString("event_planner_lastname");
                    plannerEmail = m_userObject.getString("event_planner_email");
                    plannerAddress = m_userObject.getString("event_planner_address");
                    plannerContactNumber = m_userObject.getString("event_planner_contact_no");
                    isActive = m_userObject.getString("transaction_isActive");
                    transactionFinish = m_userObject.getInt("schedule_enddate");
                    /*firstName = m_userObject.getString("client_firstname");
                    lastName = m_userObject.getString("client_lastname");
                    email = m_userObject.getString("client_email");
                    contactNumber = m_userObject.getString("client_contact_no");
                    clientAddress = m_userObject.getString("client_address");
                    //Toast.makeText(LoginPage.this, clientAddress,Toast.LENGTH_LONG).show();
                    //Toast.makeText(LoginPage.this, lastName,Toast.LENGTH_LONG).show();
                    //Toast.makeText(LoginPage.this, email,Toast.LENGTH_LONG).show();
                    */
                    //Client
                    homePage.putExtra("clientFirstName", clientFirstName);
                    homePage.putExtra("clientLastName", clientLastName);
                    homePage.putExtra("clientEmail", clientEmail);
                    homePage.putExtra("clientContactNumber",clientContactNumber);
                    homePage.putExtra("clientAddress",clientAddress);
                    homePage.putExtra("eventTitle", eventName);
                    //Planner
                    homePage.putExtra("event_planner_firstname", plannerFirstName);
                    homePage.putExtra("event_planner_lastname", plannerLastName);
                    homePage.putExtra("event_planner_email", plannerEmail);
                    homePage.putExtra("event_planner_contact_no",plannerContactNumber);
                    homePage.putExtra("event_planner_address",plannerAddress);
                    homePage.putExtra("transaction_isActive", isActive);
                    homePage.putExtra("calendarMark", transactionFinish);
                    startActivity(homePage);
                }catch (JSONException x){
                    x.printStackTrace();
                }
                break;
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
        contentValues.put("client_id", String.valueOf(this.clientID));
        return contentValues;
    }
}
