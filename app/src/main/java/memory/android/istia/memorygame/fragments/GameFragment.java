package memory.android.istia.memorygame.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.GridLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import memory.android.istia.memorygame.R;
import memory.android.istia.memorygame.game.GameManager;


public class GameFragment extends Fragment {

    private GridLayout mGridLayout;
    private GameManager mGameManager;
    private TextView mTextViewTime;
    private TextView mTextViewNbPairFound;

    public GameFragment() {
        //Todo enlever le bouchon
        mGameManager = new GameManager(3);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_game, container, false);

        mGridLayout = view.findViewById(R.id.gridLayoutGame);
        mTextViewTime = view.findViewById(R.id.textViewTime);
        mTextViewNbPairFound = view.findViewById(R.id.textViewNbPairFound);

        setGridSize(getArguments().getString("difficulty"));
        fillGridWithCards();

        mGameManager.setTimeLimit(10, mTextViewTime);
        mGameManager.setMovesLimit(3, mTextViewNbPairFound);

        return view;
    }

    private void setGridSize(String difficulty){
        switch(difficulty){
            case "easy":
                // 3 colonnes, deux lignes = 6 cartes | 3 paires
                mGridLayout.setColumnCount(3);
                mGridLayout.setRowCount(2);
                break;
            case "medium":
                // 6 paires sur 2 lignes
                mGridLayout.setColumnCount(6);
                mGridLayout.setRowCount(2);
                break;
        }
    }

    private void fillGridWithCards(){
        FragmentTransaction fragmentTransaction;
        mGridLayout.removeAllViews();

        for(CardFragment cf : this.mGameManager.getCardFragments()){
            fragmentTransaction = getChildFragmentManager().beginTransaction();
            fragmentTransaction.add(R.id.gridLayoutGame,cf,null).commit();
        }
    }


    public void clickOnCard(int mID) {
        mGameManager.cardClicked(mID);

    }
}
