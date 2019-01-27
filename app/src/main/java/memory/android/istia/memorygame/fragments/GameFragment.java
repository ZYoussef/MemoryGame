package memory.android.istia.memorygame.fragments;


import android.graphics.Point;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.GridLayout;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import memory.android.istia.memorygame.MainActivity;
import memory.android.istia.memorygame.R;
import memory.android.istia.memorygame.enums.EnumDifficulty;
import memory.android.istia.memorygame.game.GameManager;
import memory.android.istia.memorygame.utils.FragmentController;


public class GameFragment extends Fragment implements View.OnClickListener {

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

        return inflater.inflate(R.layout.fragment_game, container, false);
    }

    private Point getCardSize(EnumDifficulty difficulty) {

        DisplayMetrics displayMetrics = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int height = displayMetrics.heightPixels;
        int width = displayMetrics.widthPixels;

        switch(difficulty){
            case EASY: return new Point(width / 3, height / 3);
            case MEDIUM: return new Point(width / 5, height / 3);
            case HARD: return new Point(width / 5, height / 6);
            default: return new Point(width / 3, height / 3);
        }
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        int nbPair = 0;
        EnumDifficulty difficulty = EnumDifficulty.values()[getArguments().getInt("difficulty")];
        switch(difficulty){
            case EASY: nbPair = 2; break;
            case MEDIUM: nbPair = 4; break;
            case HARD: nbPair = 8; break;
        }

        mGameManager = new GameManager(nbPair, difficulty);

        mGridLayout = view.findViewById(R.id.gridLayoutGame);
        mTextViewTime = view.findViewById(R.id.textViewTime);
        mTextViewNbPairFound = view.findViewById(R.id.textViewNbPairFound);
        buttonMenu = view.findViewById(R.id.buttonMenu);

        buttonMenu.setOnClickListener(this);


        setGridSize(difficulty);
        Point cardSize = getCardSize(difficulty);
        fillGridWithCards(cardSize);

        if(getArguments().getBoolean("timeLimit")){
            mGameManager.setTimeLimit(difficulty, mTextViewTime);
            view.findViewById(R.id.imageViewClock).setVisibility(View.VISIBLE);
        }

        if(getArguments().getBoolean("hitLimit")){
            mGameManager.setMovesLimit(difficulty, mTextViewNbPairFound, getContext());
        }
    }

    private void setGridSize(EnumDifficulty difficulty){
        switch(difficulty){
            case EASY:
                // 2 paires
                mGridLayout.setColumnCount(2);
                mGridLayout.setRowCount(2);
                break;
            case MEDIUM:
                // 4 paires
                mGridLayout.setColumnCount(4);
                mGridLayout.setRowCount(2);
                break;
            case HARD:
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
        ((MainActivity) getActivity()).playClickSound();
        mGameManager.cardClicked(mID);
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.buttonMenu){
            FragmentController.getInstance().openFragment(FragmentController.Fragments.MAIN_MENU);
        }
    }
}
