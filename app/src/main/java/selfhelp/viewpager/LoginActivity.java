package selfhelp.viewpager;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    Button btnLogin, btnSignup;

    EditText txtusername, txtPassword;
    String authenticationcheck = "";

    // Defined URL  where to send data


    // User Session Manager Class
    UserSessionManager session;

    //constructor
    public LoginActivity() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        // User Session Manager
        session = new UserSessionManager(LoginActivity.this);

        // get Mobile number, Password input text
        txtusername = (EditText) findViewById(R.id.txtUsername);
        txtPassword = (EditText) findViewById(R.id.txtPassword);

        /*
        Toast.makeText(getApplicationContext(),
                "User Login Status: " + session.isUserLoggedIn(),
                Toast.LENGTH_LONG).show();
        */

        // User Login button
        btnLogin = (Button) findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(this);

        // User Signup button
        btnSignup = (Button) findViewById(R.id.btnSignup);
        btnSignup.setOnClickListener(this);
        TrustManager[] trustAllCerts = new TrustManager[]{
                new X509TrustManager() {

                    public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                        return null;
                    }

                    public void checkClientTrusted(java.security.cert.X509Certificate[] certs, String authType) {
                        //No need to implement.
                    }

                    public void checkServerTrusted(java.security.cert.X509Certificate[] certs, String authType) {
                        //No need to implement.
                    }
                }
        };

        // Install the all-trusting trust manager
        try {
            SSLContext sc = SSLContext.getInstance("SSL");
            sc.init(null, trustAllCerts, new java.security.SecureRandom());
            HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
        } catch (Exception e) {
            System.out.println(e);
        }




    }


    private void setBtnLogin(){
        // Get username, password from EditText
        String username = txtusername.getText().toString();
        String password = txtPassword.getText().toString();


               /* // Validate if username, password is filled
                if(username.trim().length() > 0 && password.trim().length() > 0) {

                    // For testing puspose username, password is checked with static data
                    // username = admin
                    // password = admin
*/

        new getcanlogin().execute();


        if ((username.equals("1") && password.equals("") || authenticationcheck.contains("P100"))) {

            // Creating user login session
            // Statically storing name="Android Example"
            // and email="androidexample84@gmail.com"
            session.createUserLoginSession(username, password);
            String type=getIntent().getExtras().getString("from");
            //custom constructor intent recogniser
            Integer position = Integer.parseInt(type);

            if((position == 1) || (position == 2) || (position == 0))
            {
                Intent intent;
                intent = new Intent(this, FragmentActivity.class);
                intent.putExtra("from", ""+position);
                startActivity(intent);
                finish();
            }
            else {
            // Starting MainActivity
            Intent i = new Intent(LoginActivity.this, MainActivity.class);
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

            // Add new Flag to start new Activity
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(i);
            finish();
            }

        }
        else{
            Toast.makeText(LoginActivity.this, "Please check for incorrect username,password", Toast.LENGTH_SHORT).show();
        }

    }

    private void setBtnSignup(){
        Intent i = new Intent(getApplicationContext(), SignupActivity.class);
        startActivity(i);
        finish();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnLogin:
                setBtnLogin();
                break;

            case R.id.btnSignup:
                setBtnSignup();
                break;

        }
    }


    private class getcanlogin extends AsyncTask<String, String, String> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(String... params) {
            if (checkInternetConnection())
                try {
                    URL url;
                    url = new URL("https://prms.ril.com/RBoxWS/RBoxWS.asmx/AuthenticateUser?");
                    authenticationcheck = GetText(url);
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }

            return authenticationcheck;
        }

        @Override
        protected void onPostExecute(String s) {


        }
    }

    // Create GetText Metod
    public String GetText(URL url) throws UnsupportedEncodingException {
        String username = txtusername.getText().toString();
        String password = txtPassword.getText().toString();

        // Create data variable for sent values to server
        String concatunp = "in\\" + username + ":" + password;
        String authstring = Base64.encodeToString(concatunp.getBytes(), Base64.URL_SAFE | Base64.NO_WRAP);

        String text = "";
        BufferedReader reader = null;

        // Send data
        try {

            // Send POST data request
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestProperty("Authorization", "Basic " + authstring);
            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            conn.setRequestMethod("POST");
            conn.setDoInput(true);
            conn.setDoOutput(true);
//            conn.setRequestProperty("userName", username);
//            conn.setRequestProperty("password", password);

//            userName=string&password=string
            conn.connect();

            String str = "userName=" + username + "&password=" + password;
            byte[] outputInBytes = str.getBytes("UTF-8");
            OutputStream os = conn.getOutputStream();
            os.write(outputInBytes);
            os.close();
            // Get the server response

            reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            StringBuilder sb = new StringBuilder();
            String line = null;

            // Read Server Response
            while ((line = reader.readLine()) != null) {
                // Append server response in string
                sb.append(line + "\n");
            }


            text = sb.toString();

        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            try {

                reader.close();
            } catch (Exception ex) {
            }
        }

        // Show response on activity
        //////////// Toast.makeText(this,text,Toast.LENGTH_SHORT);

        return text;
    }

    private boolean checkInternetConnection() {
        // get Connectivity Manager object to check connection
        ConnectivityManager connec = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

        // Check for network connections
        if (connec.getNetworkInfo(0).getState() == android.net.NetworkInfo.State.CONNECTED ||
                connec.getNetworkInfo(0).getState() == android.net.NetworkInfo.State.CONNECTING ||
                connec.getNetworkInfo(1).getState() == android.net.NetworkInfo.State.CONNECTING ||
                connec.getNetworkInfo(1).getState() == android.net.NetworkInfo.State.CONNECTED) {
            //////////////////////////   Toast.makeText(this, " Connected to internet", Toast.LENGTH_LONG).show();
            return true;
        } else if (
                connec.getNetworkInfo(0).getState() == android.net.NetworkInfo.State.DISCONNECTED ||
                        connec.getNetworkInfo(1).getState() == android.net.NetworkInfo.State.DISCONNECTED) {
            //////////////////  Toast.makeText(this, " Not Connected to internet", Toast.LENGTH_LONG).show();
            return false;
        }
        return false;
    }

  /*  public boolean isConnectedToServer(URL url, int timeout,String authstring) {
        try {

            url = new URL("https://prms.ril.com/RBoxWS/RBoxWS.asmx/AuthenticateUser?");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestProperty ("Authorization", "Basic " + authstring);
            conn.setRequestMethod("POST");
           // conn.setRequestProperty("username",username );
           // conn.setRequestProperty("password",password );
            conn.setDoInput(true);
            conn.setDoOutput(true);
            conn.setConnectTimeout(timeout);
            conn.connect();
             return true;

        } catch (Exception e) {
            //////////////////////////   Toast.makeText(this,"not connected to server",Toast.LENGTH_SHORT).show();
            return false;
        }
    }
*/


}