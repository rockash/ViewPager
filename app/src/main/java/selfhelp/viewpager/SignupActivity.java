package selfhelp.viewpager;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by Ashwin Pillai on 6/15/2016.
 */
public class SignupActivity extends Activity {
    Button Register;

    EditText txtfirstname, txtlastname, txtphone, txtpassword;

    public SignupActivity() {
        super();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        // get Email, Password input text
        txtfirstname = (EditText) findViewById(R.id.txtFirstName);
        txtlastname = (EditText) findViewById(R.id.txtLastName);
        txtphone = (EditText) findViewById(R.id.txtPhone);
        txtpassword = (EditText) findViewById(R.id.txtPassword);

       /*
       // Signup button click event
        Register.setOnClickListener(new View.OnClickListener() {


        }
       */

    }
}
