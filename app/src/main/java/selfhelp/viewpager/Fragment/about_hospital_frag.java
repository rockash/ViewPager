package selfhelp.viewpager.Fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import selfhelp.viewpager.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class about_hospital_frag extends Fragment {


    public about_hospital_frag() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View mView=inflater.inflate(R.layout.fragment_about_hospital_frag, container, false);
        return mView;
    }

}
