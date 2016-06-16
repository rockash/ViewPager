package selfhelp.viewpager;

import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.HashMap;
import java.util.List;
import java.util.Vector;

public class MainActivity extends AppCompatActivity {
   // private int pos = 0;
    //int currentPage = 0;
    public ViewPager pager;
    ImageView img;
    // User Session Manager object
    UserSessionManager session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        clearPreferences();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Session class instance
        session = new UserSessionManager(getApplicationContext());
        ImageAdapter adapter = new ImageAdapter(this);
        pager = (ViewPager) findViewById(R.id.myviewpager);
        pager.setAdapter(adapter);

        Toast.makeText(getApplicationContext(),
                "User Login Status: " + session.isUserLoggedIn(),
                Toast.LENGTH_LONG).show();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        session.logoutUser();
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


/*
    public void select(int position) {
        pos = position % 10;
        currentPage = pos;
        int pageCount = 12;

        if (position == pageCount - 1) {
            pager.setCurrentItem(1, false);
        }
        if (position == 0) {
            pager.setCurrentItem(pageCount - 2, false);
        }
    }
*/
}
