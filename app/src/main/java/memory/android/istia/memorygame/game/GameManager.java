package memory.android.istia.memorygame.game;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import memory.android.istia.memorygame.R;
import memory.android.istia.memorygame.fragments.CardFragment;
import memory.android.istia.memorygame.game.endGameChecker.IEndGameChecker;
import memory.android.istia.memorygame.game.endGameChecker.MovesDefeatEndGameChecker;
import memory.android.istia.memorygame.game.endGameChecker.TimeDefeatEndGameChecker;
import memory.android.istia.memorygame.game.endGameChecker.VictoryEndGameChecker;
import memory.android.istia.memorygame.utils.FragmentController;
import memory.android.istia.memorygame.utils.SharedPreferenceManager;

/**
 * GameManager--- Gère l'ensemble de la logique du jeu
 *
 * @author Sébastien, Thomas, Youssef
 * @version 1.0
 */
public class GameManager implements IGameManager {

    private int mCardsPair;
    private int mCardsPairFound;
    private String difficulty;

    private List<IEndGameChecker> mEndGameCheckers;
    private List<CardFragment> mCardFragments;

    private CardFragment mLastCard;
    private CardFragment mBeforeLastCard; //l'avant dernière

    private boolean gameIsRunning;

    public GameManager(int cardsPair, String difficulty){
        mCardsPair = cardsPair;
        mCardsPairFound = 0;
        this.difficulty = difficulty;

        mEndGameCheckers = new ArrayList<>();
        mCardFragments = new ArrayList<>();

        this.gameIsRunning = true;

        //Création de l'observateur pour la victoire et on l'attache au GameManager
        IEndGameChecker victoryChecker = new VictoryEndGameChecker(this);
        attach(victoryChecker);

        //Création des cartes
        createCards();
    }

    private void createCards() {
        int cardCount = 0;
        for(int i = 0; i < mCardsPair; i++){
            CardFragment cf1 = CardFragment.newInstance(cardCount, i, getCardImage(i), getBackCard());
            cardCount++;
            CardFragment cf2 = CardFragment.newInstance(cardCount, i, getCardImage(i), getBackCard());
            cardCount++;

            mCardFragments.add(cf1);
            mCardFragments.add(cf2);
        }

        Collections.shuffle(mCardFragments);

    }

    public int getBackCard(){
        if(SharedPreferenceManager.read(SharedPreferenceManager.Settings.DECK_SELECTED, "") == "incredibles"){
            return R.drawable.incredible_back;
        }
        else if(SharedPreferenceManager.read(SharedPreferenceManager.Settings.DECK_SELECTED, "") == "kid"){
            return R.drawable.kid_back;
        }

        return R.drawable.incredible_back;
    }

    public int getCardImage(int nb){
        if(SharedPreferenceManager.read(SharedPreferenceManager.Settings.DECK_SELECTED, "") == "incredibles"){
            switch(nb){
                case 0:return R.drawable.card_incredible_frozone;
                case 1: return R.drawable.card_incredible_kid;
                case 2: return R.drawable.card_indredible_edna;
                case 3: return R.drawable.card_indredible_baby;
                case 4: return R.drawable.card_indredible_father;
                case 5: return R.drawable.card_indredible_mother;
                case 6: return R.drawable.card_indredible_screenslaver;
                case 7: return R.drawable.card_indredible_sister;
                case 8: return R.drawable.card_indredible_syndrome;
                default: return R.drawable.card_indredible_syndrome;
            }
        }
        else if(SharedPreferenceManager.read(SharedPreferenceManager.Settings.DECK_SELECTED, "") == "kid"){
            switch(nb){
                case 0:return R.drawable.card_kid_angry;
                case 1: return R.drawable.card_kid_full_stretch;
                case 2: return R.drawable.card_kid_kiss;
                case 3: return R.drawable.card_kid_shoked;
                case 4: return R.drawable.card_kid_smile;
                case 5: return R.drawable.card_kid_smile_eye_opened;
                case 6: return R.drawable.card_kid_squash;
                case 7: return R.drawable.card_kid_worry;
                case 8: return R.drawable.card_kid_stretch;
                default: return R.drawable.card_kid_stretch;
            }
        }
        else{
            switch(nb){
                case 0:return R.drawable.card_incredible_frozone;
                case 1: return R.drawable.card_incredible_kid;
                case 2: return R.drawable.card_indredible_edna;
                case 3: return R.drawable.card_indredible_baby;
                case 4: return R.drawable.card_indredible_father;
                case 5: return R.drawable.card_indredible_mother;
                case 6: return R.drawable.card_indredible_screenslaver;
                case 7: return R.drawable.card_indredible_sister;
                case 8: return R.drawable.card_indredible_syndrome;
                default: return R.drawable.card_indredible_syndrome;
            }
        }
    }

    public void cardClicked(int cardID){
        CardFragment newCard = getCardByID(cardID);

        //1ère carte, on enregistre et met visible
        if(this.mBeforeLastCard == null){
            mBeforeLastCard = newCard;
            mBeforeLastCard.setCardVisibility(true);
            return;
        }
        //2nd carte, pareil + vérif des paires
        else if(this.mLastCard == null){
            this.mLastCard = newCard;

            this.mLastCard.setCardVisibility(true);

            if(mLastCard.getPairNumber() == mBeforeLastCard.getPairNumber()){
                mLastCard.setPairFound(true);
                mBeforeLastCard.setPairFound(true);
                mCardsPairFound++;
            }
        }
        //On reprend une carte,
        else{
            //Si pas paire, on cache les anciennes cartes
            if(!mLastCard.getPairFound()){
                this.mLastCard.setCardVisibility(false);
                this.mBeforeLastCard.setCardVisibility(false);
            }

            //Puis celle retournée devient la beforeLastCard
            this.mBeforeLastCard = newCard;
            this.mLastCard = null;

            //Qu'on met visible
            this.mBeforeLastCard.setCardVisibility(true);
        }

        //Signaler à tous les endGameChecker qu'un coup a été joué
        notifyEndGameCheckers();
    }

    /**
     * Rajoute une condition de défaite pour le jeu avec une limite de temps
     * @param difficulty limite de temps en seconde
     */
    public void setTimeLimit(String difficulty, TextView timeUI){
        IEndGameChecker timeChecker = new TimeDefeatEndGameChecker(this, difficulty, timeUI);
        attach(timeChecker);
        ((TimeDefeatEndGameChecker) timeChecker).execute();
    }

    /**
     * Rajoute une condition de défaite suivant un nombre de coups limite à jouer
     * @param difficulty Limite du nombre de coup
     * @param movesLeftUI
     */
    public void setMovesLimit(String difficulty, TextView movesLeftUI){
        IEndGameChecker moveLimit = new MovesDefeatEndGameChecker(this, difficulty, movesLeftUI);
        attach(moveLimit);
    }

    /**
     * Sera appelée par un des EndGameChecker pour spécifier la fin de la partie
     * @param victory
     */
    public void endOfGame(boolean victory){
        if(gameIsRunning){
            Bundle bundle = new Bundle();
            int score = calculateScore();
            bundle.putInt("score", score);
            bundle.putBoolean("victory", victory);
            gameIsRunning = false;

            ScoreManager.getInstance().addToScore(score, this.difficulty);

            for(IEndGameChecker egc : mEndGameCheckers){
                if(egc instanceof TimeDefeatEndGameChecker){
                    ((TimeDefeatEndGameChecker) egc).setGameIsRunning(false);
                }
            }

            FragmentController.getInstance().openFragmentAsPopup(FragmentController.Fragments.END_GAME, bundle);
        }
    }

    private int calculateScore(){
        int score = 0;
        for(IEndGameChecker egc : mEndGameCheckers){
            int x = egc.calculateScore();
            Log.e("test", "Calcul score " + x);
            score += x;
        }

        score /= mEndGameCheckers.size();
        return score;
    }

    ////////////////////////////////////////////////////////////
    ///////////////////GETTERS & SETTERS////////////////////////
    ////////////////////////////////////////////////////////////
    public int getCardsPairFound(){
        return this.mCardsPairFound;
    }

    public int getCardsPair(){
        return this.mCardsPair;
    }

    public List<CardFragment> getCardFragments(){
        return this.mCardFragments;
    }

    public CardFragment getCardByID(int ID){
        for(CardFragment cf : mCardFragments){
            if(cf.getCardID() == ID) return cf;
        }
        return null;
    }

    ////////////////////////////////////////////////////////////
    /////////////////IMPLEMENTATION DE L'INTERFACE//////////////
    ////////////////////////////////////////////////////////////
    @Override
    public void attach(IEndGameChecker endGameChecker) {
        this.mEndGameCheckers.add(endGameChecker);
    }

    @Override
    public void detach(IEndGameChecker endGameChecker) {
        this.mEndGameCheckers.remove(endGameChecker);
    }

    @Override
    public void notifyEndGameCheckers() {
        for(IEndGameChecker egc : mEndGameCheckers){
            egc.update();
        }
    }
}
