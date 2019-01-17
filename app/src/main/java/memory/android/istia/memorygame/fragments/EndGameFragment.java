package memory.android.istia.memorygame.fragments;


import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import memory.android.istia.memorygame.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class EndGameFragment extends DialogFragment {


    public EndGameFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootview = inflater.inflate(R.layout.fragment_end_game, container, false);

        return rootview;
    }

}
