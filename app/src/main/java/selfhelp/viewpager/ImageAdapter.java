package selfhelp.viewpager;

import android.content.Context;
import android.content.Intent;
import android.support.v4.view.PagerAdapter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;


/**
 * Created by Ashwin Pillai on 6/10/2016.
 */


public class ImageAdapter extends PagerAdapter {
    LoginActivity loginobject;
    UserSessionManager sessionobject;
    Context context;
    private static final String TAG = "ImageAdapter";
    private static final boolean DEBUG = false;
     int mFakeCount;




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
        sessionobject=new UserSessionManager(mContext);
    }
    @Override
    public int getCount() {
        return Integer.MAX_VALUE;
    }



    @Override
    public Object instantiateItem(ViewGroup collection,  int position) {
        // make the size larger, and change the position
        // to trick viewpager into paging forever
        LayoutInflater mInflater = LayoutInflater.from(context);
        //Log.d(TAG,"Layout inflator object created");
        View itemview = (View) mInflater.inflate(R.layout.pager_adapter_layout, collection, false);
        //Log.d(TAG,"view object created");
        ImageView img = (ImageView) itemview.findViewById(R.id.imageView);
        //Log.d(TAG,"imageview object created");
         if (position >= mResources.length) {
            int newPosition = Math.abs(position) % mResources.length;
            position = Math.abs(newPosition);
             mFakeCount++ ;
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

        final int finalPosition = position;
        img.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
            /*    if(!sessionobject.isUserLoggedIn()){
                    // user is not logged in redirect him to Login Activity
                    Intent i = new Intent(context, testactivity.class);
                    // Closing all the Activities
                    i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    // Add new Flag to start new Activity
                    i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    // Staring Login Activity
                    context.startActivity(i);
                }
*/


                   /*  BitmapFactory.Options options = new BitmapFactory.Options();
                options.inSampleSize = 4;
                Bitmap preview_bitmap = BitmapFactory.decodeResource(context.getResources(),(Integer) root.getTag(), options);

                 */

                /*

                Toast.makeText(context, "opens activity for position"+pos, Toast.LENGTH_SHORT).show();

                Snackbar.make(arg0, "opens activity for position" + pos, Snackbar.LENGTH_SHORT)
                        .setAction("Action", null)
                        .show();


                Intent intent = new Intent(context, LoginActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);

                */

                      //if else cases for if user logged in and needs login page redirect
                if (sessionobject.isUserLoggedIn()) {
                    Intent intent = new Intent(context, LoginActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(intent);
                }
                    else {
                    Intent intent = new Intent(context, testactivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(intent);

                         }



                }

        });

        return itemview;
        }

        // do your layout inflating and what not here.
        // position will now refer to a correct index into your mResources list
        // even if the user has paged so many times that the view has wrapped


    @Override
    public boolean isViewFromObject(View view, Object object) {

        return view == object;
    }

    int setPageMargin (int marginPixels){
        marginPixels = 0;
        return marginPixels;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((LinearLayout) object);
    }
    @Override public float getPageWidth(int position) {
        float nbPages = 3;          // You could display partial pages using a float value
        return (1 / nbPages);
    }
    private void debug(String message) {
        if (DEBUG) {
            Log.d(TAG, message);
        }
    }

}
