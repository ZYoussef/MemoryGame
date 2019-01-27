package memory.android.istia.memorygame.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.ArraySet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Set;

import memory.android.istia.memorygame.MainActivity;
import memory.android.istia.memorygame.R;
import memory.android.istia.memorygame.game.ScoreManager;
import memory.android.istia.memorygame.utils.FragmentController;
import memory.android.istia.memorygame.utils.SharedPreferenceManager;

public class ScoreFragment extends Fragment implements View.OnClickListener {

    private Button buttonHome;

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
        return inflater.inflate(R.layout.fragment_score, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        setScores();

        buttonHome = getView().findViewById(R.id.buttonMenu);
        buttonHome.setOnClickListener(this);

    }

    private void setScores(){
        if(getView() != null){
            setScoreEasy();
            setscoreMedium();
            setScoreHard();
        }
    }

    private void setScoreEasy(){
        ArrayList<Integer> score = ScoreManager.getInstance().getScores_easy();
        if(score.size() > 0){
            TextView score_1 = getView().findViewById(R.id.score_easy_1);
            score_1.setText(String.valueOf(score.get(0)));
        }

        if(score.size() > 1) {
            TextView score_2 = getView().findViewById(R.id.score_easy_2);
            score_2.setText(String.valueOf(score.get(1)));
        }

        if(score.size() > 2) {
            TextView score_3 = getView().findViewById(R.id.score_easy_3);
            score_3.setText(String.valueOf(score.get(2)));
        }
    }

    private void setscoreMedium(){
        ArrayList<Integer> score = ScoreManager.getInstance().getScores_medium();
        if(score.size() > 0){
            TextView score_1 = getView().findViewById(R.id.score_medium_1);
            score_1.setText(String.valueOf(score.get(0)));
        }

        if(score.size() > 1) {
            TextView score_2 = getView().findViewById(R.id.score_medium_2);
            score_2.setText(String.valueOf(score.get(1)));
        }

        if(score.size() > 2) {
            TextView score_3 = getView().findViewById(R.id.score_medium_3);
            score_3.setText(String.valueOf(score.get(2)));
        }
    }

    private void setScoreHard(){
        ArrayList<Integer> score = ScoreManager.getInstance().getScores_hard();
        if(score.size() > 0){
            TextView score_1 = getView().findViewById(R.id.score_hard_1);
            score_1.setText(String.valueOf(score.get(0)));
        }

        if(score.size() > 1) {
            TextView score_2 = getView().findViewById(R.id.score_hard_2);
            score_2.setText(String.valueOf(score.get(1)));
        }

        if(score.size() > 2) {
            TextView score_3 = getView().findViewById(R.id.score_hard_3);
            score_3.setText(String.valueOf(score.get(2)));
        }
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.buttonMenu){
            ((MainActivity) getActivity()).playClickSound();
            FragmentController.getInstance().openFragment(FragmentController.Fragments.MAIN_MENU);
        }
    }
}
