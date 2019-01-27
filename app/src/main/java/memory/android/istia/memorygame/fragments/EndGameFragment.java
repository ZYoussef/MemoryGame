package memory.android.istia.memorygame.fragments;


import android.graphics.Color;
import android.graphics.Point;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentController;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import memory.android.istia.memorygame.MainActivity;
import memory.android.istia.memorygame.R;
import memory.android.istia.memorygame.game.ScoreManager;

/**
 * A simple {@link Fragment} subclass.
 */
public class EndGameFragment extends DialogFragment implements View.OnClickListener {


    private Button buttonHome;

    private ImageView star;
    private TextView textViewResult;
    private TextView textViewScore;

    public EndGameFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_end_game, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        buttonHome = view.findViewById(R.id.buttonHome);
        star = view.findViewById(R.id.imageViewStars);
        textViewScore = view.findViewById(R.id.textViewScore);
        textViewResult = view.findViewById(R.id.textViewResult);

        setData();

        buttonHome.setOnClickListener(this);
        setCancelable(false);
        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        super.onViewCreated(view, savedInstanceState);
    }

    private void setData() {
        if(getArguments().getBoolean("victory")){
            textViewResult.setText(R.string.Victory);
            int score = getArguments().getInt("score");
            textViewScore.setText("" + score);

            if(score > 800){
                star.setImageResource(R.drawable.star_1);
            }
            else if(score > 600){
                star.setImageResource(R.drawable.star_2);
            }
            else if(score > 300){
                star.setImageResource(R.drawable.star_3);
            }
        }
        else{
            star.setImageResource(R.drawable.star_4);
            textViewResult.setText(R.string.defeat);
            textViewScore.setText("0");
        }
    }

    @Override
    public void onResume() {
        super.onResume();

        Window window = getDialog().getWindow();
        Point size = new Point();

        Display display = window.getWindowManager().getDefaultDisplay();
        display.getSize(size);

        int width = size.x;
        int height = size.y;

        window.setLayout((int) (width * 0.75), (int) (height * 0.75));
        window.setGravity(Gravity.CENTER);
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.buttonHome){
            ((MainActivity) getActivity()).playClickSound();
            getDialog().cancel();
            memory.android.istia.memorygame.utils.FragmentController.getInstance().openFragment(memory.android.istia.memorygame.utils.FragmentController.Fragments.MAIN_MENU);
        }
    }
}
