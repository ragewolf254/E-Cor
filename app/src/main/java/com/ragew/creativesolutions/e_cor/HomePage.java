package com.ragew.creativesolutions.e_cor;

import android.content.ContentValues;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.github.sundeepk.compactcalendarview.CompactCalendarView;
import com.github.sundeepk.compactcalendarview.domain.Event;
import com.ragew.creativesolutions.e_cor.Utils.SuperTask;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class HomePage extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, SuperTask.TaskListener {

    private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MMMM - yyyy", Locale.getDefault());
    CompactCalendarView compactCalendarView;
    private ImageView m_imageView;
    private TextView userNameTV;
    private TextView userEmailTV;
    private String firstname;
    private String lastname;
    private String fullname;
    private String email;
    private String contactNumber;
    private String address;

    Bundle informationBundle = new Bundle();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        //Calendar
        final TextView monthDisplay = findViewById(R.id.month_display);
        compactCalendarView = findViewById(R.id.compactcalendar_view);
        compactCalendarView.setFirstDayOfWeek(Calendar.MONDAY);
        compactCalendarView.setUseThreeLetterAbbreviation(true);

        //Do calendar shit here
        compactCalendarView = findViewById(R.id.compactcalendar_view);
        compactCalendarView.setFirstDayOfWeek(Calendar.MONDAY);
        compactCalendarView.setUseThreeLetterAbbreviation(true);
        m_imageView = findViewById(R.id.parentLogo);

        final Event testEvent = new Event(Color.RED,1518227261000L, "Wedding Day!!!");
        compactCalendarView.addEvent(testEvent);
        final Event testEvent2 = new Event(Color.BLUE,1518256061000L, "Birth Day!!!");
        compactCalendarView.addEvent(testEvent2);

        compactCalendarView.setListener(new CompactCalendarView.CompactCalendarViewListener() {
            @Override
            public void onDayClick(Date dateClicked) {
                List<Event> events = compactCalendarView.getEvents(dateClicked);
                Toast.makeText(HomePage.this,"Day was clicked: " + dateClicked + " with events " + events,Toast.LENGTH_LONG).show();
            }

            @Override
            public void onMonthScroll(Date firstDayOfNewMonth) {
                monthDisplay.setText(simpleDateFormat.format(firstDayOfNewMonth));
            }
        });

        m_imageView = findViewById(R.id.parentLogo);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        Snackbar.make(fab, "Login Successful!", Snackbar.LENGTH_LONG).show();

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        View headerView = navigationView.getHeaderView(0);

        //Get Intent Values
        Bundle loginValues = getIntent().getExtras();
        if (loginValues != null){
            firstname = loginValues.getString("firstName");
            lastname = loginValues.getString("lastName");
            email = loginValues.getString("email");
            contactNumber = loginValues.getString("contact");
            address = loginValues.getString("address");
        } else {
            //Toast.makeText(HomePage.this,"Tangina wala akong value",Toast.LENGTH_LONG).show();
        }

        userNameTV = headerView.findViewById(R.id.userName);
        userEmailTV = headerView.findViewById(R.id.userEmail);

        //Assign the name and email
        fullname = firstname + " " + lastname;

        userNameTV.setText(fullname);
        userEmailTV.setText(email);

        informationBundle.putString("clientName",fullname);
        informationBundle.putString("contactNumber",contactNumber);
        informationBundle.putString("address",address);

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
            getFragmentManager().popBackStack();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home_page, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        getFragmentManager().popBackStackImmediate();
        if (id == R.id.home) {
            // Handle the camera action
        } else if (id == R.id.transaction_history) {
            TransactionHistory transactionHistory = new TransactionHistory();
            android.support.v4.app.FragmentManager manager = getSupportFragmentManager();
            manager.beginTransaction().replace(R.id.baseContent,transactionHistory).commit();
            getFragmentManager().popBackStackImmediate();
        } else if (id == R.id.current_transaction_status) {
            TransactionStatus transactionStatus = new TransactionStatus();
            android.support.v4.app.FragmentManager manager = getSupportFragmentManager();
            manager.beginTransaction().replace(R.id.baseContent,transactionStatus).commit();
            getFragmentManager().popBackStackImmediate();
        } else if (id == R.id.client_information) {
            ClientInformation clientInformation = new ClientInformation();
            android.support.v4.app.FragmentManager manager = getSupportFragmentManager();
            clientInformation.setArguments(informationBundle);
            manager.beginTransaction().replace(R.id.baseContent,clientInformation).commit();
            getFragmentManager().popBackStackImmediate();
        } else if (id == R.id.nav_logout) {
            Intent logoutIntent = new Intent(HomePage.this, LoginPage.class);
            startActivity(logoutIntent);
            finish();
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onTaskRespond(String id, String json) {
        String testString = json;
        switch (id) {
            case "names": {
                try {
                    JSONObject m_nameObject = new JSONObject(testString);
                    Log.d("User",m_nameObject.toString());
                    //Toast.makeText(HomePage.this, m_nameObject.toString(), Toast.LENGTH_LONG).show();
                }catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public ContentValues setRequestValues(String id, ContentValues contentValues) {
        switch (id){
            case "names":{
                contentValues.put("client_id","");
            }
        }
        return contentValues;
    }
}
