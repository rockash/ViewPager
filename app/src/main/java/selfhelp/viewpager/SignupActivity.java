package selfhelp.viewpager;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Ashwin Pillai on 6/15/2016.
 */
public class SignupActivity extends Activity {
    Button Register;
    private static final String EMAIL_PATTERN = "^[a-zA-Z0-9#_~!$&'()*+,;=:.\"(),:;<>@\\[\\]\\\\]+@[a-zA-Z0-9-]+(\\.[a-zA-Z0-9-]+)*$";
    private Pattern pattern = Pattern.compile(EMAIL_PATTERN);
    private Matcher matcher;

    EditText txtfirstname, txtlastname, txtphone, txtpassword,txtemail;
    TextInputLayout FirstNamewrapper,LastNameWrapper,PhoneWrapper,EmailWrapper,PasswordWrapper;

    public SignupActivity() {
        super();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        Register = (Button) findViewById(R.id.btnSignup);

        //
        FirstNamewrapper = (TextInputLayout) findViewById(R.id.FirstNameWrapper);
        LastNameWrapper =   (TextInputLayout) findViewById(R.id.LastNameWrapper);
        PhoneWrapper = (TextInputLayout) findViewById(R.id.PhoneWrapper);
        EmailWrapper = (TextInputLayout) findViewById(R.id.EmailWrapper);
        PasswordWrapper = (TextInputLayout) findViewById(R.id.PasswordWrapper);

        // get EditText  first name  , last name  , phone  , Email  , Password
        txtfirstname = (EditText) findViewById(R.id.txtFirstName);
        txtlastname = (EditText) findViewById(R.id.txtLastName);
        txtphone = (EditText) findViewById(R.id.txtPhone);
        txtemail = (EditText) findViewById(R.id.txtEmail);
        txtpassword = (EditText) findViewById(R.id.txtPassword);



       // Signup button click event
        Register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String firstname = FirstNamewrapper.getEditText().getText().toString();
                String lastname = LastNameWrapper.getEditText().getText().toString();
                String phone = PhoneWrapper.getEditText().getText().toString();
                String email = EmailWrapper.getEditText().getText().toString();
                String password = PasswordWrapper.getEditText().getText().toString();

               /* if (!validateEmail(email)) {
                    EmailWrapper.setError("Not a valid email address!");
                } else if (!validatePassword(password)) {
                    passwordWrapper.setError("Not a valid password!");
                } else {
                    FirstNamewrapper.setErrorEnabled(false);
                    LastNameWrapper.setErrorEnabled(false);
                    PhoneWrapper.setErrorEnabled(false);
                    EmailWrapper.setErrorEnabled(false);
                    PasswordWrapper.setErrorEnabled(false);    
*/
                    Toast.makeText(getApplicationContext(),
                            "Sign up complete",
                            Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(getApplicationContext(), LoginActivity.class);
                    startActivity(i);
                    finish();
                }
            });
    }

    public boolean validateEmail(String email) {
        matcher = pattern.matcher(email);
        return matcher.matches();
    }
}
