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

import memory.android.istia.memorygame.R;
import memory.android.istia.memorygame.enums.EnumDifficulty;
import memory.android.istia.memorygame.game.GameManager;
import memory.android.istia.memorygame.utils.CustomFragmentManager;

/**
 * Gère l'affichage du jeu
 * Les cartes sont récupérés depuis le GameManager et disposées sur un gridLayout
 * Suivant les options, les TextViews du temps et du nombre de coups sont visible ou non
 */
public class GameFragment extends Fragment implements View.OnClickListener {

    private GridLayout mGridLayout;
    private GameManager mGameManager;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.fragment_game, container, false);
    }

    /**
     * Calcule la taille des cartes selon le nombre de cartes devant être affichées (difficulté)
     * afin que tout puisse passer sur l'écran
     * @param difficulty difficulté
     * @return Point(x = width, y = heght)
     */
    private Point getCardSize(EnumDifficulty difficulty) {

        DisplayMetrics displayMetrics = new DisplayMetrics();
        if(getActivity() != null && getActivity().getWindowManager() != null){
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
        return new Point(0, 0);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mGridLayout = view.findViewById(R.id.gridLayoutGame);
        TextView textViewTime = view.findViewById(R.id.textViewTime);
        TextView  textViewNbPairFound = view.findViewById(R.id.textViewNbPairFound);
        Button buttonMenu = view.findViewById(R.id.buttonMenu);

        buttonMenu.setOnClickListener(this);


        int nbPair = 0;
        EnumDifficulty difficulty = EnumDifficulty.EASY;

        if(getArguments() != null){
            difficulty = EnumDifficulty.values()[getArguments().getInt("difficulty")];
        }

        switch(difficulty){
            case EASY: nbPair = 2; break;
            case MEDIUM: nbPair = 4; break;
            case HARD: nbPair = 8; break;
        }

        mGameManager = new GameManager(nbPair, difficulty);

        setGridSize(difficulty);
        Point cardSize = getCardSize(difficulty);
        fillGridWithCards(cardSize);

        if(getArguments().getBoolean("timeLimit")){
            mGameManager.setTimeLimit(difficulty, textViewTime, getContext());
            view.findViewById(R.id.imageViewClock).setVisibility(View.VISIBLE);
        }

        if(getArguments().getBoolean("hitLimit")){
            mGameManager.setMovesLimit(difficulty, textViewNbPairFound, getContext());
        }
    }

    /**
     * Grille du gridLayout selon la difficulté
     * @param difficulty difficulté
     */
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

    /**
     * Récupère les cardFragment depuis le GameManager et les ajoute dans le gridLayout
     * Les cardFragments sont redimensionnée selon cardSize
     * @param cardSize Taille des cartes
     */
    private void fillGridWithCards(Point cardSize){
        FragmentTransaction fragmentTransaction;
        mGridLayout.removeAllViews();

        for(CardFragment cf : this.mGameManager.getCardFragments()){
            fragmentTransaction = getChildFragmentManager().beginTransaction();
            fragmentTransaction.add(R.id.gridLayoutGame,cf,null).commit();
            cf.resizeCard(cardSize.x, cardSize.y);
        }

    }

    /**
     *  Transmet l'id de la carte clickée au GameManager
     * @param mID ID de la carte sur lequel le click a été effecté
     */
    public void clickOnCard(int mID) {
        mGameManager.cardClicked(mID);
    }

    /**
     * Retour au menu principal
     * @param v view
     */
    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.buttonMenu){
            CustomFragmentManager.getInstance().openFragment(CustomFragmentManager.Fragments.MAIN_MENU);
        }
    }
}
