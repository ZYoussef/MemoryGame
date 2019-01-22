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

/**
 * A simple {@link Fragment} subclass.
 */
public class EndGameFragment extends DialogFragment {


    private Button buttonHome;

    private ImageView star1;
    private ImageView star2;
    private ImageView star3;
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
        star1 = view.findViewById(R.id.imageViewStar1);
        star2 = view.findViewById(R.id.imageViewStar2);
        star3 = view.findViewById(R.id.imageViewStar3);
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
        }
        else{
            textViewResult.setText(R.string.defeat);
        }

        textViewScore.setText("" + getArguments().getInt("score"));

        switch(getArguments().getInt("nbStar")){
            case 1:
                star1.setVisibility(View.VISIBLE);
                star2.setVisibility(View.INVISIBLE);
                star2.setVisibility(View.INVISIBLE);
                break;
            case 2:
                star1.setVisibility(View.INVISIBLE);
                star2.setVisibility(View.VISIBLE);
                star2.setVisibility(View.VISIBLE);
                break;
            case 3:
                star1.setVisibility(View.VISIBLE);
                star2.setVisibility(View.VISIBLE);
                star2.setVisibility(View.VISIBLE);
                break;
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
