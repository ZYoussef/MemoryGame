package memory.android.istia.memorygame.fragments;


import android.graphics.Color;
import android.graphics.Point;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import memory.android.istia.memorygame.MainActivity;
import memory.android.istia.memorygame.R;
import memory.android.istia.memorygame.utils.CustomFragmentManager;

/**
 * Fragment pour l'affichage de la popUp de fin de partie
 * Gère l'affichage du nombre d'étoiles et des scores
 */
public class EndGameFragment extends DialogFragment implements View.OnClickListener {

    private ImageView mStar;
    private TextView mTextViewResult;
    private TextView mTextViewScore;

    public EndGameFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_end_game, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        Button buttonHome = view.findViewById(R.id.buttonHome);
        mStar = view.findViewById(R.id.imageViewStars);
        mTextViewScore = view.findViewById(R.id.textViewScore);
        mTextViewResult = view.findViewById(R.id.textViewResult);

        setData();

        buttonHome.setOnClickListener(this);
        setCancelable(false);

        if(getDialog() != null && getDialog().getWindow() != null){
            getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        }

        super.onViewCreated(view, savedInstanceState);
    }

    /**
     * Attribue les arguments (victory, score, nombre d'étoiles) aux views correspondantes
     */
    private void setData() {
        if(getArguments() != null){
            if(getArguments().getBoolean("victory")){
                mTextViewResult.setText(R.string.Victory);
                int score = getArguments().getInt("score");
                mTextViewScore.setText(String.format(getResources().getConfiguration().locale, "%d", score));
                
                if(score > 800){
                    mStar.setImageResource(R.drawable.star_1);
                }
                else if(score > 600){
                    mStar.setImageResource(R.drawable.star_2);
                }
                else if(score > 300){
                    mStar.setImageResource(R.drawable.star_3);
                }
            }
            else{
                mStar.setImageResource(R.drawable.star_4);
                mTextViewResult.setText(R.string.defeat);
                mTextViewScore.setText("0");
            }
        }
    }

    @Override
    public void onResume() {
        super.onResume();

        Window window = getDialog().getWindow();
        Point size = new Point();

        if(window != null && window.getWindowManager() != null){
            Display display = window.getWindowManager().getDefaultDisplay();
            display.getSize(size);

            int width = size.x;
            int height = size.y;

            window.setLayout((int) (width * 0.75), (int) (height * 0.75));
            window.setGravity(Gravity.CENTER);
        }
    }

    /**
     * Retour au menu principal
     * @param v view
     */
    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.buttonHome){

            if(getActivity() instanceof MainActivity){
                ((MainActivity) getActivity()).playClickSound();
            }

            getDialog().cancel();
            CustomFragmentManager.getInstance().openFragment(CustomFragmentManager.Fragments.MAIN_MENU);
        }
    }
}
