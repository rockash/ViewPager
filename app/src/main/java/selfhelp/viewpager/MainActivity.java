package selfhelp.viewpager;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.location.LocationManager;
import android.net.Uri;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Vector;

public class MainActivity extends AppCompatActivity{
   // private int pos = 0;
    //int currentPage = 0;
    public ViewPager pager;
    ImageView img;
    // User Session Manager object
    static UserSessionManager session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        clearPreferences();
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_NOSENSOR);

        setContentView(R.layout.activity_main);
        // Session class instance
        session = new UserSessionManager(getApplicationContext());
        ImageAdapter adapter = new ImageAdapter(this);
        pager = (ViewPager) findViewById(R.id.myviewpager);
        pager.setAdapter(adapter);
        pager.setPageMargin(5);

        /*Toast.makeText(getApplicationContext(),
                "User Login Status: " + session.isUserLoggedIn(),
                Toast.LENGTH_LONG).show();*/


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //session.logoutUser();
    }
    private void clearPreferences() {
        try {
            // clearing app data
            Runtime runtime = Runtime.getRuntime();
            runtime.exec("pm clear package selfhelp.viewpager");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onBackPressed() {
        // TODO Auto-generated method stub
        super.onBackPressed();
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK
                | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();

    }
    @Override
    protected void onResume() {
        super.onResume();
        LocationManager manager = (LocationManager) getSystemService( Context.LOCATION_SERVICE );

            Log.d("GPS ENABLED","gpsenabled value in onresume : "+ImageAdapter.gpsenabled);
        if (ImageAdapter.gpsenabled == true && manager != null) {
            if (manager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
                ImageAdapter.gpsenabled = false;
                Intent i = new Intent(android.content.Intent.ACTION_VIEW,
                        Uri.parse("google.navigation:q=" + 18.9591624 + ","
                                + 72.8199164 + ""));
                startActivity(i);
            } else {
                ImageAdapter.gpsenabled = false;
            }
        }
        }


/*
    public void select(int position) {
        pos = position % 10;
        currentPage = pos;
        int pageCount = 12;

        if (position == pageCount - 1) {
            pager.setCurrentItem(1, false);        }
        if (position == 0) {
            pager.setCurrentItem(pageCount - 2, false);
        }
    }@SuppressWarnings("StatementWithEmptyBody")
@Override
public boolean onNavigationItemSelected(MenuItem item) {
    // Handle navigation view item clicks here.
    int id = item.getItemId();

    if (id == R.id.home) {

    } else if (id == R.id.home) {

    } else if (id == R.id.pre_registration) {

    } else if (id == R.id.find_a_doctor) {

    } else if (id == R.id.book_an_appointment) {

    } else if (id == R.id.book_a_health_package) {

    }else if (id == R.id.my_appointments) {

    }else if (id == R.id.check_your_profile) {

    }else if (id == R.id.how_to_reach) {

    }else if (id == R.id.about_hospital) {

    }else if (id == R.id.news) {

    }else if (id == R.id.feedback) {

    }

    DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
    drawer.closeDrawer(GravityCompat.START);
    return true;
}

*/
}
