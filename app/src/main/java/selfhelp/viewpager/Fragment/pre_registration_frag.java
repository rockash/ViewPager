package selfhelp.viewpager.Fragment;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import java.text.DateFormat;
import java.util.Calendar;

import selfhelp.viewpager.R;

import static selfhelp.viewpager.R.id.dateofbirth_enter;


public class pre_registration_frag extends Fragment implements View.OnClickListener {
    String LOG = "pre_registration_frag";
    Fragment fragment;
    LinearLayout layout1;
    LinearLayout layout2 ;
    //EditText FIRST NAME
    EditText firstname_enter;
    String firstname;
    //EditText LAST NAME
    EditText lastname_enter;
    String lastname;
    //EditText DATE
    Calendar calendar = Calendar.getInstance() ;
    DateFormat formate;
    EditText date_enter;
    Button date;
    //EditText MOBILE NUMBER
    EditText mobileno_enter1;
    EditText mobileno_enter2;
    String mobileno1;
    String mobileno2;
    //EditText LANDLINE NUMBER
    EditText landlineno_enter1;
    EditText landlineno_enter2;
    String landline1;
    String landline2;
    //EditText EMAIL
    EditText email_enter;
    String email;
    RadioButton rb;
    String gender;
    //Button NEXT
    Button next ;
    //Button PREVIOUS
    Button previous ;
    //Button SUBMIT
    Button submit ;
    //EditText PIN
    EditText pincode_enter;
    String pincode;
    //EditText ADDRESS LINE 1
    EditText addressline1_enter;
    String addressline1;
    //EditText ADDRESS LINE 2
    //EditText addressline2_enter;
    String addressline2;
    //EditText CITY/AREA
    EditText cityarea_enter;
    String cityarea;
    //EditText STATE
    EditText state_enter;
    String state;
    //EditText COUNTRY
    EditText country_enter;
    String country;
    // Spinner RELATION
    Spinner relation_enter;
    String relation;
    Context context ;
    public pre_registration_frag() {
        // Required empty public constructor

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View mView=inflater.inflate(R.layout.fragment_pre_registration_frag, container, false);
        context =container.getContext();
        layout1 = (LinearLayout)mView.findViewById(R.id.layout1);
        layout2 = (LinearLayout)mView.findViewById(R.id.layout2);
        firstname_enter = (EditText) mView.findViewById(R.id.firstname_enter);
        lastname_enter = (EditText) mView.findViewById(R.id.lastname_enter);
        formate = DateFormat.getDateInstance();
        date_enter = (EditText) mView.findViewById(dateofbirth_enter);
        date = (Button) mView.findViewById(R.id.calendar_button);
        date.setOnClickListener(this);
        date_enter.setOnClickListener(this);
        mobileno_enter1 = (EditText) mView.findViewById(R.id.mobileno_enter1);
        mobileno_enter2 = (EditText) mView.findViewById(R.id.mobileno_enter2);
        landlineno_enter1 = (EditText) mView.findViewById(R.id.landlineno_enter1);
        landlineno_enter2 = (EditText) mView.findViewById(R.id.landlineno_enter2);
        email_enter = (EditText) mView.findViewById(R.id.email_enter);
        pincode_enter = (EditText) mView.findViewById(R.id.pincode_enter);
        addressline1_enter = (EditText) mView.findViewById(R.id.addressline1_enter);
        //addressline2_enter = (EditText) mView.findViewById(R.id.addressline2_enter);
        cityarea_enter = (EditText) mView.findViewById(R.id.cityarea_enter);
        state_enter = (EditText) mView.findViewById(R.id.state_enter);
        country_enter = (EditText) mView.findViewById(R.id.country_enter);
        relation_enter = (Spinner) mView.findViewById(R.id.relation_enter);
        next = (Button)mView.findViewById(R.id.nextbutton);
        next.setOnClickListener(this);
        previous = (Button)mView.findViewById(R.id.previousbutton);
        previous.setOnClickListener(this);
        submit = (Button)mView.findViewById(R.id.submitbutton);
        submit.setOnClickListener(this);
        //updatedate();

        return mView;

    }

    public void setDate(){
        new DatePickerDialog(context,d,calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH),calendar.get(Calendar.DAY_OF_MONTH)).show();
    }

    DatePickerDialog.OnDateSetListener d = new DatePickerDialog.OnDateSetListener(){
        @Override
        public void onDateSet(DatePicker view,int year, int monthOfYear,int dayOfMonth){
            calendar.set(Calendar.YEAR,year);
            calendar.set(Calendar.MONTH,monthOfYear);
            calendar.set(Calendar.DAY_OF_MONTH,dayOfMonth);
            //updatedate();
            date_enter.setText( "" + dayOfMonth + "-" + (monthOfYear + 1) + "-" + year );


        }

    };
    public void updatedate(){
        date.setText(formate.format(calendar.getTime()));
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.calendar_button :
                setDate();
                break;
            case R.id.dateofbirth_enter :
                setDate();
                break;
            case R.id.nextbutton :
                layout1.setVisibility(View.GONE);
                layout2.setVisibility(View.VISIBLE);
                //Toast.makeText(context,"next button clicked", Toast.LENGTH_SHORT).show();
                break;
            case R.id.previousbutton :
                layout2.setVisibility(View.GONE);
                layout1.setVisibility(View.VISIBLE);
                break;
            case R.id.submitbutton :
                firstname = firstname_enter.getText().toString();
                lastname = lastname_enter.getText().toString();
                mobileno1 = mobileno_enter1.getText().toString();
                mobileno2 = mobileno_enter2.getText().toString();
                landline1 = landlineno_enter1.getText().toString();
                landline2 = landlineno_enter2.getText().toString();
                email = email_enter.getText().toString();
                pincode = pincode_enter.getText().toString();
                addressline1 = addressline1_enter.getText().toString();
               // addressline2 = addressline2_enter.getText().toString();
                cityarea = cityarea_enter.getText().toString();
                state = state_enter.getText().toString();
                country = country_enter.getText().toString();
                relation = relation_enter.getSelectedItem().toString();
                Toast.makeText(context,"send data to be implemented",Toast.LENGTH_SHORT).show();
                break;
            case R.id.radiobutton_male :
                 addListenerOnButton(v);
                 break;
            case R.id.radiobutton_female :
                addListenerOnButton(v);
                break;
            case R.id.radiobutton_other :
                addListenerOnButton(v);
                break;
            default:
                Log.d(LOG,"default case");
        }
    }
    public String addListenerOnButton(View view) {

        //Radiogroup GENDER
        final RadioGroup gender_enter = (RadioGroup) view.findViewById(R.id.gender_enter);

        gender_enter.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // get selected radio button from radioGroup
                int selectedId = gender_enter.getCheckedRadioButtonId();

                // find the radiobutton by returned id
                rb = (RadioButton) v.findViewById(selectedId);
                gender = (String) rb.getText();
                Toast.makeText(context,
                        rb.getText(), Toast.LENGTH_SHORT).show();

            }

        });


return gender;
    }
}