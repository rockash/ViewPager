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

import java.text.DateFormat;
import java.util.Calendar;

import selfhelp.viewpager.R;

import static selfhelp.viewpager.R.id.dateofbirth_enter;


public class pre_registration_frag extends Fragment implements View.OnClickListener {
    String LOG = "pre_registration_frag";
    Fragment fragment;
    LinearLayout layout1 = (LinearLayout) getView().findViewById(R.id.layout1);
    LinearLayout layout2 = (LinearLayout) getView().findViewById(R.id.layout2);
    //EditText FIRST NAME
    EditText firstname_enter = (EditText) getView().findViewById(R.id.firstname_enter);
    String firstname = firstname_enter.getText().toString();
    //EditText LAST NAME
    EditText lastname_enter = (EditText) getView().findViewById(R.id.lastname_enter);
    String lastname = lastname_enter.getText().toString();
    //EditText DATE
    Calendar calendar;
    DateFormat formate = DateFormat.getDateInstance();
    EditText date_enter = (EditText) getView().findViewById(dateofbirth_enter);
    Button date = (Button) getView().findViewById(R.id.calendar_button);
    //EditText MOBILE NUMBER
    private final EditText mobileno_enter1 = (EditText) getView().findViewById(R.id.mobileno_enter1);
    private final EditText mobileno_enter2 = (EditText) getView().findViewById(R.id.mobileno_enter2);
    String mobileno1 = mobileno_enter1.getText().toString();
    String mobileno2 = mobileno_enter2.getText().toString();
    //EditText LANDLINE NUMBER
    private final EditText landlineno_enter1 = (EditText) getView().findViewById(R.id.landlineno_enter1);
    private final EditText landlineno_enter2 = (EditText) getView().findViewById(R.id.landlineno_enter2);
    String landline1 = landlineno_enter1.getText().toString();
    String landline2 = landlineno_enter2.getText().toString();
    //EditText EMAIL
    private final EditText email_enter = (EditText) getView().findViewById(R.id.email_enter);
    String email = email_enter.getText().toString();
    //Radiogroup GENDER         //TODO handle input
    private final RadioGroup gender_enter = (RadioGroup) getView().findViewById(R.id.gender_enter);
    String gender = String.valueOf(getView().findViewById(RadioGroup.generateViewId()));
    RadioButton rb = new RadioButton(getActivity());
    //Button NEXT
    Button next;
    //EditText PIN
    private final EditText pincode_enter = (EditText) getView().findViewById(R.id.pincode_enter);
    String pincode = pincode_enter.getText().toString();
    //EditText ADDRESS LINE 1
    private final EditText addressline1_enter = (EditText) getView().findViewById(R.id.addressline1_enter);
    String addressline1 = addressline1_enter.getText().toString();
    //EditText ADDRESS LINE 2
    private final EditText addressline2_enter = (EditText) getView().findViewById(R.id.addressline2_enter);
    String addressline2 = addressline2_enter.getText().toString();
    //EditText CITY/AREA
    private final EditText cityarea_enter = (EditText) getView().findViewById(R.id.cityarea_enter);
    String cityarea = cityarea_enter.getText().toString();
    //EditText STATE
    private final EditText state_enter = (EditText) getView().findViewById(R.id.state_enter);
    String state = state_enter.getText().toString();
    //EditText COUNTRY
    private final EditText country_enter = (EditText) getView().findViewById(R.id.country_enter);
    String country = country_enter.getText().toString();
    // Spinner RELATION
    private final Spinner relation_enter = (Spinner) getView().findViewById(R.id.relation_enter);
    String relation = relation_enter.getSelectedItem().toString();
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
        date.setOnClickListener((View.OnClickListener) getActivity());
        date_enter.setOnClickListener((View.OnClickListener) getActivity());
        updatedate();

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
            date_enter.setText( "" + dayOfMonth + "-" + (monthOfYear + 1) + "-" + year );
            updatedate();

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
            case R.id.nextbutton :
                layout1.setVisibility(View.GONE);
                layout2.setVisibility(View.VISIBLE);
                break;
            default:
                Log.d(LOG,"default case");
        }
    }

}