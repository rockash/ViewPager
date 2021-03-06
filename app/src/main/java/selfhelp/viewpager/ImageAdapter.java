package selfhelp.viewpager;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.LocationManager;
import android.net.Uri;
import android.support.v4.view.PagerAdapter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.Locale;


/**
 * Created by Ashwin Pillai on 6/10/2016.
 */


public class ImageAdapter extends PagerAdapter {

    static UserSessionManager sessionobject;
    Context context;
    private static final String TAG = "ImageAdapter";
    private static final boolean DEBUG = false;
    int mFakeCount;
    Intent intent;
    static LocationManager manager= null;
    float lat = 18.9591624f;
    float lng = 72.8199164f;
    String maplLabel = "HNH Hospital";
    String uri = String.format(Locale.ENGLISH, "http://maps.google.com/maps?daddr=%f,%f (%s)", lat, lng, maplLabel);
    public static boolean gpsenabled =false;

    int[] mResources = {
            R.drawable.about_hospital_cr,
            R.drawable.howtoreach,
            R.drawable.member_registration,
            R.drawable.book_a_health_package,
            R.drawable.book_an_appoinment,
            R.drawable.my_appoinment_img,
            R.drawable.check_your_profile,
            R.drawable.feedback_cr,

    };

    public ImageAdapter(Context mContext) {
        mFakeCount = mResources.length;
        context = mContext;
        sessionobject = new UserSessionManager(mContext);
    }

    @Override
    public int getCount() {
        return Integer.MAX_VALUE;
    }


    @Override
    public Object instantiateItem(ViewGroup collection, int position) {
        // make the size larger, and change the position
        // to trick viewpager into paging forever
        LayoutInflater mInflater = LayoutInflater.from(context);
        //Log.d(TAG,"Layout inflator object created");
        View itemview = (View) mInflater.inflate(R.layout.pager_adapter_layout, collection, false);
        //Log.d(TAG,"view object created");
        final ImageView img = (ImageView) itemview.findViewById(R.id.imageView);
        //Log.d(TAG,"imageview object created");

        if (position >= mResources.length) {
            int newPosition = Math.abs(position) % mResources.length;
            position = Math.abs(newPosition);
            mFakeCount++;
        }

        final int pos = Math.abs(position) % mResources.length;

        img.setImageResource(mResources[position]);
        // Log.d("imageloadcode","image load successful");
        img.setScaleType(ImageView.ScaleType.FIT_XY);
        collection.addView(itemview);


        /*.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                Intent i = new Intent(MainActivity.this, LoginActivity.class);
                i.putExtra("flag", flag);
                i.putExtra("position", position);
                startActivity(i);
            }

        });*/
        // Log.d("add images","added views to the container"+ position);

        img.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
            /*
            if(!sessionobject.isUserLoggedIn()){
                    // user is not logged in redirect him to Login Activity
                    Intent i = new Intent(context, FragmentActivity.class);
                    // Closing all the Activities
                    i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    // Add new Flag to start new Activity
                    i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    // Staring Login Activity
                    context.startActivity(i);
                }


                Toast.makeText(context, "opens activity for position"+pos, Toast.LENGTH_SHORT).show();

                Snackbar.make(arg0, "opens activity for position" + pos, Snackbar.LENGTH_SHORT)
                        .setAction("Action", null)
                        .show();


                Intent intent = new Intent(context, LoginActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);

                */



                    //if else cases for if user logged in and needs login page redirect



                    switch (pos) {
                        //TODO      cover all cases
                        case 0:

                            intent = new Intent(context, FragmentActivity.class);
                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            intent.putExtra("from", ""+pos);
                            //custom constructor variable pass to FragmentActivity
                            //intent.putExtra("POSITION",0);
                            context.startActivity(intent);

                            break;

                        case 1: //

                             manager = (LocationManager) context.getSystemService( Context.LOCATION_SERVICE );
                            if ( !manager.isProviderEnabled( LocationManager.GPS_PROVIDER ) ) {
                                gpsenabled = true;
                                buildAlertMessageNoGps();
                                Log.d("GPS ENABLED","gps enabled value after dialog create from imageadapter : "+gpsenabled);
                            }
                            else {
                                gpsenabled = false;
                                mapsintent();
                                Log.d("GPS ENABLED","gps enabled value after intent from imageadapter : "+gpsenabled);
                            }

                            break;
                        case 2:
                            if (!sessionobject.isUserLoggedIn()) {
                                Intent intent = new Intent(context, LoginActivity.class);
                                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                intent.putExtra("from", ""+pos);
                                context.startActivity(intent);
                            }
                            else {
                                intent = new Intent(context, FragmentActivity.class);
                                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                intent.putExtra("from", "" + pos);
                                //custom constructor variable pass to FragmentActivity
                                //intent.putExtra("POSITION",0);
                                ((Activity) context).startActivity(intent);
                            }
                            break;
                        case 3:
                                Toast.makeText(context,"try to click me, NOT !",Toast.LENGTH_SHORT).show();
                            break;
                        case 4:
                            Toast.makeText(context,"try to click me, NOT !",Toast.LENGTH_SHORT).show();
                            break;
                        case 5:
                            Toast.makeText(context,"try to click me, NOT !",Toast.LENGTH_SHORT).show();
                            break;
                        case 6:
                            Toast.makeText(context,"try to click me, NOT !",Toast.LENGTH_SHORT).show();
                            break;
                        case 7:
                            Toast.makeText(context,"try to click me, NOT !",Toast.LENGTH_SHORT).show();
                            break;
                        case 8:
                            Toast.makeText(context,"try to click me, NOT !",Toast.LENGTH_SHORT).show();
                            break;


                    }

            }

        });

        return itemview;
    }
    // do your layout inflating and what not here.
    // position will now refer to a correct index into your mResources list
    // even if the user has paged so many times that the view has wrapped

    void buildAlertMessageNoGps() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setMessage("Your GPS seems to be disabled, do you want to enable it?")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(@SuppressWarnings("unused") final DialogInterface dialog, @SuppressWarnings("unused") final int id) {
                        context.startActivity(new Intent(android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS));

                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(final DialogInterface dialog, @SuppressWarnings("unused") final int id) {
                        dialog.cancel();
                    }
                });
        final AlertDialog alert = builder.create();
        alert.show();
    }

    void mapsintent(){

        Intent intent = new Intent(Intent.ACTION_VIEW,
                Uri.parse(uri));

        try
        {
            context.startActivity(intent);
            gpsenabled = false;
        }
        catch(ActivityNotFoundException ex)
        {
            try
            {
                Intent unrestrictedIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
                context.startActivity(unrestrictedIntent);
            }
            catch(ActivityNotFoundException innerEx)
            {
                Toast.makeText(context, "Please install a maps application", Toast.LENGTH_LONG).show();
            }
        }
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {

        return view == object;
    }

    int setPageMargin(int marginPixels) {
        marginPixels = 0;
        return marginPixels;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((LinearLayout) object);
    }

    @Override
    public float getPageWidth(int position) {
        float nbPages = 3;          // You could display partial pages using a float value
        return (1 / nbPages);
    }

    private void debug(String message) {
        if (DEBUG) {
            Log.d(TAG, message);
        }
    }
}
