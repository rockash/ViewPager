package selfhelp.viewpager;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import selfhelp.viewpager.Fragment.about_hospital_frag;

public class testactivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_testactivity);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
       // setSupportActionBar(toolbar);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        String type=getIntent().getExtras().getString("from");
        //custom constructor intent recogniser
       testactivity(Integer.parseInt(type));

    }
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

       //custom constructor to relate to clicks in viewpager to clicks in the navigation drawer
    public void testactivity(int position){

        int id = position;

        // Create a new fragment and specify the fragment to show based on nav item clicked
        Fragment fragment = null;
        Fragment fragmentClass =null ;
        String title = "";
        switch (id) {

//            case R.id.pre_registration:
//                fragmentClass = SecondFragment.class;
//                break;
//            case R.id.find_a_doctor:
//                fragmentClass = ThirdFragment.class;
//                break;
//            case R.id.book_an_appointment:
//                fragmentClass = ThirdFragment.class;
//                break;
//            case R.id.book_a_health_package:
//                fragmentClass = ThirdFragment.class;
//                break;
//            case R.id.my_appointments:
//                fragmentClass = ThirdFragment.class;
//                break;
//            case R.id.check_your_profile:
//                fragmentClass = ThirdFragment.class;
//                break;
//            case R.id.how_to_reach:
//                fragmentClass = ThirdFragment.class;
//                break;
            case 0:
                fragmentClass = new about_hospital_frag();
                title = "";
                break;
//            *//*case R.id.news:
//                fragmentClass = ThirdFragment.class;
//                break;
//            case R.id.feedback:
//                fragmentClass = ThirdFragment.class;
//                break;*//*
            default:
                Log.w(this.getClass().getSimpleName(),
                        "Reached Default in view pager item click switch case");
                break;
        }

        try {
            fragment = (Fragment) fragmentClass;
            getSupportActionBar().setTitle(title);
            // change icon to arrow drawable
            getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_drawer);
            // Insert the fragment by replacing any existing fragment
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.fragment_container, fragment).commit();
            } catch (Exception e) {
            e.printStackTrace();
        }
        }


    public boolean onNavigationItemSelected(MenuItem menuItem) {
        // Handle navigation view item clicks here.
        int id = menuItem.getItemId();

        // Create a new fragment and specify the fragment to show based on nav item clicked
        Fragment fragment = null;
        Fragment fragmentClass =null ;
        switch (id) {
            case R.id.home:
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                break;
            /*
            case R.id.pre_registration:
                fragmentClass = SecondFragment.class;
                break;
            case R.id.find_a_doctor:
                fragmentClass = ThirdFragment.class;
                break;
            case R.id.book_an_appointment:
                fragmentClass = ThirdFragment.class;
                break;
            case R.id.book_a_health_package:
                fragmentClass = ThirdFragment.class;
                break;
            case R.id.my_appointments:
                fragmentClass = ThirdFragment.class;
                break;
            case R.id.check_your_profile:
                fragmentClass = ThirdFragment.class;
                break;
            case R.id.how_to_reach:
                fragmentClass = ThirdFragment.class;
                break;
            */case R.id.about_hospital:
                fragmentClass = new about_hospital_frag();
                break;
            /*case R.id.news:
                fragmentClass = ThirdFragment.class;
                break;
            case R.id.feedback:
                fragmentClass = ThirdFragment.class;
                break;*/
            default:
                Log.w(this.getClass().getSimpleName(),
                        "Reached Default in onNavigationDrawerItemSelected!");
                break;
        }

        try {
            fragment = (Fragment) fragmentClass;
        } catch (Exception e) {
           e.printStackTrace();
       }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);

        drawer.closeDrawer(GravityCompat.START);
       return true;
    }
}

