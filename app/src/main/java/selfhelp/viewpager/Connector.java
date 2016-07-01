package selfhelp.viewpager;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.preference.PreferenceManager;
import android.util.Log;
import android.widget.Toast;

import org.apache.http.conn.ssl.SSLSocketFactory;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Ashwin Pillai on 6/29/2016.
 */
public class Connector {

    private Context ctx;
    public SharedPreferences sh_pf;
    private StringBuilder result = null;

    public Connector(Context ctx, URL url) {
        this.ctx = ctx;
        HttpURLConnection urlConnection = null;

        try {
            urlConnection = (HttpURLConnection) url.openConnection();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            urlConnection.setDoOutput(true);
            urlConnection.setChunkedStreamingMode(0);

            OutputStream out = null;
            try {
                out = new BufferedOutputStream(urlConnection.getOutputStream());
            } catch (IOException e) {
                e.printStackTrace();
            }
            writeStream(out);

            InputStream in = null;
            try {
                in = new BufferedInputStream(urlConnection.getInputStream());
            } catch (IOException e) {
                e.printStackTrace();
            }
            readStream(in);
        } finally {
            urlConnection.disconnect();
        }

    }

    // TODO readStream logic
    private void readStream(InputStream in){
        BufferedReader inbr = new BufferedReader(new InputStreamReader(in));
        String line;
        String results="";

        try {
            while ((line = inbr.readLine()) != null)
            {
                //setStrResponse(getStrResponse() + line);
                //strResponse=""+line;
                results=""+line;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        Log.v("response",results);


    }

    // TODO writeStream logic
    private void writeStream(OutputStream out){


    }


    private boolean checkInternetConnection() {
        // get Connectivity Manager object to check connection
        ConnectivityManager connec = (ConnectivityManager) ctx.getSystemService(Context.CONNECTIVITY_SERVICE);

        // Check for network connections
        if (connec.getNetworkInfo(0).getState() == android.net.NetworkInfo.State.CONNECTED ||
                connec.getNetworkInfo(0).getState() == android.net.NetworkInfo.State.CONNECTING ||
                connec.getNetworkInfo(1).getState() == android.net.NetworkInfo.State.CONNECTING ||
                connec.getNetworkInfo(1).getState() == android.net.NetworkInfo.State.CONNECTED) {
            Toast.makeText(ctx, " Connected ", Toast.LENGTH_LONG).show();
            return true;
        } else if (
                connec.getNetworkInfo(0).getState() == android.net.NetworkInfo.State.DISCONNECTED ||
                        connec.getNetworkInfo(1).getState() == android.net.NetworkInfo.State.DISCONNECTED) {
            Toast.makeText(ctx, " Not Connected ", Toast.LENGTH_LONG).show();
            return false;
        }
        return false;
    }
}
