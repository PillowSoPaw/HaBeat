package com.habeat;

import android.content.Intent;
import android.content.res.Configuration;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.os.Bundle;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.habeat.data.SQLiteContactDAO;
import com.habeat.sms.SmsManager;
import com.habeat.supportgroup.Contact;
import com.habeat.supportgroup.SupportGroupActivity;

//this is the main screen of the app
public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{
    public static String PACKAGE_NAME;

    private Toolbar toolbar;
    private NavigationView drawer;
    private DrawerLayout drawerLayout;
    private  ActionBarDrawerToggle drawerToggle;
    private SmsManager smsManager;
    private RadioGroup radioGroup;
    private CravingType selectedCravingType;
    private SQLiteContactDAO SQLiteContactDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //sets the appbar to the activity
        toolbar = (Toolbar) findViewById(R.id.appbar);
        setSupportActionBar(toolbar);

        drawer = (NavigationView) findViewById(R.id.main_drawer);
        drawer.setNavigationItemSelectedListener(this);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawerToggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.drawer_open,R.string.drawer_close);

        drawerLayout.setDrawerListener(drawerToggle);
        drawerToggle.syncState();

        smsManager = new SmsManager(this);
        SQLiteContactDAO = new SQLiteContactDAO(this);

        selectedCravingType = CravingType.CIGARETTE;
        radioGroup = (RadioGroup) findViewById(R.id.radioGroup);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.cigaretteRadioBtn: {
                        selectedCravingType = CravingType.CIGARETTE;
                        Log.e("MainActivity.java", "Cigarette craving type selected.");
                        break;
                    }

                    case R.id.alcoholRadioBtn: {
                        selectedCravingType = CravingType.ALCOHOL;
                        Log.e("MainActivity.java", "Alchohol craving type selected.");
                        break;
                    }
                }
            }
        });

//        //makes the drawer open at application start
//        getSupportActionBar().setDisplayShowHomeEnabled(true);
//
//        //sets the navigation drawer
//        NavigationDrawerFragment drawerFragment =  (NavigationDrawerFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_nav_drawer);
//        drawerFragment.setUp(R.id.fragment_nav_drawer,(DrawerLayout) findViewById(R.id.drawer_layout), toolbar);

        PACKAGE_NAME = getApplicationContext().getPackageName();
    }

    @Override
      public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        drawerToggle.onConfigurationChanged(newConfig);
    }


    @Override
    public boolean onNavigationItemSelected(MenuItem menuItem) {
        Intent intent = null;

        if(menuItem.getItemId() == R.id.drawerItem1){
            drawerLayout.closeDrawer(GravityCompat.START);
            intent = new Intent(this,SupportGroupActivity.class);
            startActivity(intent);
            return true;
        }
        return false;
    }

    public void onClickCravingBtn(View view) {
        notifySupportGroup();
        Intent intent = new Intent(getApplicationContext(), selectedCravingType.randomActivity());
        startActivity(intent);
    }

    private void notifySupportGroup() {
        smsManager.sendToAll(SQLiteContactDAO.getAllContacts().toArray(new Contact[SQLiteContactDAO
                .getContactsCount()]), "<User> is having a craving and needs your help");
        Toast.makeText(getApplicationContext(),
                "Your support group has been notified", Toast.LENGTH_SHORT).show();
    }
}
