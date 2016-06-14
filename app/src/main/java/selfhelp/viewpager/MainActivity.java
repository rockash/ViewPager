package selfhelp.viewpager;

import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.List;
import java.util.Vector;

public class MainActivity extends AppCompatActivity {
   // private int pos = 0;
    //int currentPage = 0;
    public ViewPager pager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ImageAdapter adapter = new ImageAdapter(this);
        ViewPager pager = (ViewPager) findViewById(R.id.myviewpager);
        pager.setAdapter(adapter);
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
