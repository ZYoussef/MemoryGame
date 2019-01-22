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
import android.widget.TextView;

import memory.android.istia.memorygame.R;
import memory.android.istia.memorygame.game.GameManager;


public class GameFragment extends Fragment {

    private GridLayout mGridLayout;
    private GameManager mGameManager;
    private TextView mTextViewTime;
    private TextView mTextViewNbPairFound;


    public GameFragment() {


    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment


        int nbPair = 0;
        switch(getArguments().getString("difficulty")){
            case "easy": nbPair = 2; break;
            case "medium": nbPair = 4; break;
            case "hard": nbPair = 8; break;
        }
        mGameManager = new GameManager(nbPair);



        View view =  inflater.inflate(R.layout.fragment_game, container, false);

        mGridLayout = view.findViewById(R.id.gridLayoutGame);
        mTextViewTime = view.findViewById(R.id.textViewTime);
        mTextViewNbPairFound = view.findViewById(R.id.textViewNbPairFound);


        setGridSize(getArguments().getString("difficulty"));
        Point cardSize = getCardSize(getArguments().getString("difficulty"));
        Log.d("test", "taille calculée : " + cardSize.x + " / " + cardSize.y);
        fillGridWithCards(cardSize);

        if(getArguments().getBoolean("timeLimit")){
            mGameManager.setTimeLimit(100, mTextViewTime);
        }

        if(getArguments().getBoolean("hitLimit")){
            mGameManager.setMovesLimit(300, mTextViewNbPairFound);
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
            return new Point(width / 5, height / 5);
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
        Log.d("test", "nb element : " + mGridLayout.getChildCount());
    }
}
