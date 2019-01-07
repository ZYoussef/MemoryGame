package memory.android.istia.memorygame.game;

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

/**
 * GameManager--- Gère l'ensemble de la logique du jeu
 *
 * @author Sébastien, Thomas, Youssef
 * @version 1.0
 */
public class GameManager implements IGameManager {

    private int mCardsPair;
    private int mCardsPairFound;

    private List<IEndGameChecker> mEndGameCheckers;
    private List<CardFragment> mCardFragments;

    private CardFragment mLastCard;
    private CardFragment mBeforeLastCard; //l'avant dernière

    public GameManager(int cardsPair){
        mCardsPair = cardsPair;
        mCardsPairFound = 0;

        mEndGameCheckers = new ArrayList<>();
        mCardFragments = new ArrayList<>();

        //Création de l'observateur pour la victoire et on l'attache au GameManager
        IEndGameChecker victoryChecker = new VictoryEndGameChecker(this);
        attach(victoryChecker);


        //Création des cartes
        createCards();
    }

    private void createCards() {
        int cardCount = 0;
        for(int i = 0; i < mCardsPair; i++){
            CardFragment cf1 = CardFragment.newInstance(cardCount, i, getCardImage(i), R.drawable.main_background);
            cardCount++;
            CardFragment cf2 = CardFragment.newInstance(cardCount, i, getCardImage(i), R.drawable.main_background);
            cardCount++;
            mCardFragments.add(cf1);
            mCardFragments.add(cf2);
        }

        Collections.shuffle(mCardFragments);
    }

    public int getCardImage(int nb){
        switch(nb){
            case 0: return R.drawable.blue_button00;
            case 1: return R.drawable.green_button00;
            case 2: return R.drawable.red_button00;
            case 3: return R.drawable.grey_button00;
            case 4: return R.drawable.yellow_button00;
            default: return R.drawable.blue_button00;
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
     * @param timeLimit limite de temps en seconde
     */
    public void setTimeLimit(int timeLimit, TextView timeUI){
        IEndGameChecker timeChecker = new TimeDefeatEndGameChecker(this, timeLimit, timeUI);
        attach(timeChecker);
        ((TimeDefeatEndGameChecker) timeChecker).execute();
    }

    /**
     * Rajoute une condition de défaite suivant un nombre de coups limite à jouer
     * @param movesLimit Limite du nombre de coup
     * @param movesLeftUI
     */
    public void setMovesLimit(int movesLimit, TextView movesLeftUI){
        IEndGameChecker moveLimit = new MovesDefeatEndGameChecker(this, movesLimit, movesLeftUI);
        attach(moveLimit);
    }

    /**
     * Sera appelée par un des EndGameChecker pour spécifier la fin de la partie
     * @param victory
     */
    public void endOfGame(boolean victory, int score){
        Log.d("TEST", "END OF GAME - VICTOIRE="+victory+", score="+score);
        FragmentController.getInstance().openFragmentAsPopup(FragmentController.Fragments.END_GAME, null);
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
