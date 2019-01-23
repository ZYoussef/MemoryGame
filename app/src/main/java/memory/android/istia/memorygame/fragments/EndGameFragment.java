package memory.android.istia.memorygame.fragments;


import android.graphics.Color;
import android.graphics.Point;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
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

import memory.android.istia.memorygame.R;
import memory.android.istia.memorygame.game.ScoreManager;

/**
 * A simple {@link Fragment} subclass.
 */
public class EndGameFragment extends DialogFragment {


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
        View view = inflater.inflate(R.layout.fragment_end_game, container, false);

        buttonHome = view.findViewById(R.id.buttonHome);
        star = view.findViewById(R.id.imageViewStars);
        textViewScore = view.findViewById(R.id.textViewScore);
        textViewResult = view.findViewById(R.id.textViewResult);

        setData();


        buttonHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getDialog().cancel();
                memory.android.istia.memorygame.utils.FragmentController.getInstance().openFragment(memory.android.istia.memorygame.utils.FragmentController.Fragments.MAIN_MENU);
            }
        });
        setCancelable(false);
        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        return view;
    }

    private void setData() {
        if(getArguments().getBoolean("victory")){
            textViewResult.setText(R.string.Victory);
            int score = getArguments().getInt("score");
            textViewScore.setText("" + score);

            if(score > 700){
                star.setImageResource(R.drawable.star_1);
            }
            else if(score > 500){
                star.setImageResource(R.drawable.star_2);
            }
            else if(score > 200){
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

}
