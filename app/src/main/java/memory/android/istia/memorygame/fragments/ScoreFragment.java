package memory.android.istia.memorygame.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

import memory.android.istia.memorygame.MainActivity;
import memory.android.istia.memorygame.R;
import memory.android.istia.memorygame.game.ScoreManager;
import memory.android.istia.memorygame.utils.CustomFragmentManager;

/**
 * Affichage des scores
 * Récupération depuis le ScoreManager et affichage dans le TextView associé à chaque score
 */
public class ScoreFragment extends Fragment implements View.OnClickListener {

    public ScoreFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_score, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if(getView() != null){
            Button buttonHome = getView().findViewById(R.id.buttonMenu);
            buttonHome.setOnClickListener(this);

            setScores();
        }
    }

    private void setScores(){
        if(getView() != null){
            setScoreEasy();
            setScoreMedium();
            setScoreHard();
        }
    }

    private void setScoreEasy(){
        ArrayList<Integer> score = (ArrayList) ScoreManager.getInstance().getScoresEasy();
        if(!score.isEmpty()){
            TextView score1 = getView().findViewById(R.id.score_easy_1);
            score1.setText(String.valueOf(score.get(0)));
        }

        if(score.size() > 1) {
            TextView score2 = getView().findViewById(R.id.score_easy_2);
            score2.setText(String.valueOf(score.get(1)));
        }

        if(score.size() > 2) {
            TextView score3 = getView().findViewById(R.id.score_easy_3);
            score3.setText(String.valueOf(score.get(2)));
        }
    }

    private void setScoreMedium(){
        ArrayList<Integer> score = (ArrayList) ScoreManager.getInstance().getScoresMedium();
        if(!score.isEmpty()){
            TextView score1 = getView().findViewById(R.id.score_medium_1);
            score1.setText(String.valueOf(score.get(0)));
        }

        if(score.size() > 1) {
            TextView score2 = getView().findViewById(R.id.score_medium_2);
            score2.setText(String.valueOf(score.get(1)));
        }

        if(score.size() > 2) {
            TextView score3 = getView().findViewById(R.id.score_medium_3);
            score3.setText(String.valueOf(score.get(2)));
        }
    }

    private void setScoreHard(){
        ArrayList<Integer> score = (ArrayList) ScoreManager.getInstance().getScoresHard();
        if(!score.isEmpty()){
            TextView score1 = getView().findViewById(R.id.score_hard_1);
            score1.setText(String.valueOf(score.get(0)));
        }

        if(score.size() > 1) {
            TextView score2 = getView().findViewById(R.id.score_hard_2);
            score2.setText(String.valueOf(score.get(1)));
        }

        if(score.size() > 2) {
            TextView score3 = getView().findViewById(R.id.score_hard_3);
            score3.setText(String.valueOf(score.get(2)));
        }
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.buttonMenu){

            if(getActivity() instanceof MainActivity){
                ((MainActivity) getActivity()).playClickSound();
            }

            CustomFragmentManager.getInstance().openFragment(CustomFragmentManager.Fragments.MAIN_MENU);
        }
    }
}
