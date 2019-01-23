package memory.android.istia.memorygame.fragments;


import android.graphics.Point;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.GridLayout;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import memory.android.istia.memorygame.R;
import memory.android.istia.memorygame.game.GameManager;
import memory.android.istia.memorygame.utils.FragmentController;


public class GameFragment extends Fragment {

    private GridLayout mGridLayout;
    private GameManager mGameManager;
    private TextView mTextViewTime;
    private TextView mTextViewNbPairFound;
    private Button buttonMenu;


    public GameFragment() {


    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment


        int nbPair = 0;
        String difficulty = getArguments().getString("difficulty");
        switch(difficulty){
            case "easy": nbPair = 2; break;
            case "medium": nbPair = 4; break;
            case "hard": nbPair = 8; break;
        }
        mGameManager = new GameManager(nbPair);



        View view =  inflater.inflate(R.layout.fragment_game, container, false);

        mGridLayout = view.findViewById(R.id.gridLayoutGame);
        mTextViewTime = view.findViewById(R.id.textViewTime);
        mTextViewNbPairFound = view.findViewById(R.id.textViewNbPairFound);
        buttonMenu = view.findViewById(R.id.buttonMenu);

        buttonMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentController.getInstance().openFragment(FragmentController.Fragments.MAIN_MENU);
            }
        });


        setGridSize(getArguments().getString("difficulty"));
        Point cardSize = getCardSize(getArguments().getString("difficulty"));
        fillGridWithCards(cardSize);

        if(getArguments().getBoolean("timeLimit")){
            mGameManager.setTimeLimit(difficulty, mTextViewTime);
            view.findViewById(R.id.imageViewClock).setVisibility(View.VISIBLE);
        }

        if(getArguments().getBoolean("hitLimit")){
            mGameManager.setMovesLimit(difficulty, mTextViewNbPairFound);
        }

        return view;
    }

    private Point getCardSize(String difficulty) {

        DisplayMetrics displayMetrics = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int height = displayMetrics.heightPixels;
        int width = displayMetrics.widthPixels;

        if(difficulty == "easy"){
            return new Point(width / 3, height / 3);
        }
        else if(difficulty == "medium"){
            return new Point(width / 5, height / 3);
        }
        else{
            return new Point(width / 5, height / 6);
        }
    }

    private void setGridSize(String difficulty){
        switch(difficulty){
            case "easy":
                // 2 paires
                mGridLayout.setColumnCount(2);
                mGridLayout.setRowCount(2);
                break;
            case "medium":
                // 4 paires
                mGridLayout.setColumnCount(4);
                mGridLayout.setRowCount(2);
                break;
            case "hard":
                // 8 paires
                mGridLayout.setColumnCount(4);
                mGridLayout.setRowCount(4);
                break;
        }
    }

    private void fillGridWithCards(Point cardSize){
        FragmentTransaction fragmentTransaction;
        mGridLayout.removeAllViews();

        for(CardFragment cf : this.mGameManager.getCardFragments()){
            fragmentTransaction = getChildFragmentManager().beginTransaction();
            fragmentTransaction.add(R.id.gridLayoutGame,cf,null).commit();
            cf.resizeCard(cardSize.x, cardSize.y);
        }

    }


    public void clickOnCard(int mID) {
        mGameManager.cardClicked(mID);
    }
}
