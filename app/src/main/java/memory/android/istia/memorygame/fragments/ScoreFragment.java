package memory.android.istia.memorygame.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import memory.android.istia.memorygame.R;
import memory.android.istia.memorygame.game.ScoreManager;
import memory.android.istia.memorygame.utils.SharedPreferenceManager;

public class ScoreFragment extends Fragment {


    public ScoreFragment() {
        // Required empty public constructor
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_score, container, false);

        TextView tv = view.findViewById(R.id.textViewTest);

        for(int score : ScoreManager.getInstance().getScores()){
            tv.setText(tv.getText() + " " + score);
        }
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


    }
}
