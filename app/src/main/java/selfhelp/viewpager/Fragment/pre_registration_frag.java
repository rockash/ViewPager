package selfhelp.viewpager.Fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import selfhelp.viewpager.R;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link pre_registration_frag.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link pre_registration_frag#newInstance} factory method to
 * create an instance of this fragment.
 */
public class pre_registration_frag extends Fragment {

    public pre_registration_frag() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View mView=inflater.inflate(R.layout.fragment_pre_registration_frag, container, false);
        return mView;
    }
}