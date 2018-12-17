package memory.android.istia.memorygame.game;

import java.util.ArrayList;
import java.util.List;

import memory.android.istia.memorygame.game.endGameChecker.IEndGameChecker;
import memory.android.istia.memorygame.game.endGameChecker.MovesDefeatEndGameChecker;
import memory.android.istia.memorygame.game.endGameChecker.TimeDefeatEndGameChecker;
import memory.android.istia.memorygame.game.endGameChecker.VictoryEndGameChecker;

/**
 * GameManager--- Gère l'ensemble de la logique du jeu
 *
 * @author Sébastien, Thomas, Youssef
 * @version 1.0
 */
public class GameManager implements IGameManager {

    private int mCardsPair;
    private int mCardsPairFound;

    private List<IEndGameChecker> endGameCheckers;

    public GameManager(int cardsPair){
        mCardsPair = cardsPair;
        mCardsPairFound = 0;

        endGameCheckers = new ArrayList<>();

        //Création de l'observateur pour la victoire et on l'attache au GameManager
        IEndGameChecker victoryChecker = new VictoryEndGameChecker(this);
        attach(victoryChecker);
    }

    /**
     * Rajoute une condition de défaite pour le jeu avec une limite de temps
     * @param timeLimit limite de temps en seconde
     */
    public void setTimeLimit(int timeLimit){
        IEndGameChecker timeChecker = new TimeDefeatEndGameChecker(this, timeLimit);
        attach(timeChecker);
        ((TimeDefeatEndGameChecker) timeChecker).execute();
    }

    /**
     * Rajoute une condition de défaite suivant un nombre de coups limite à jouer
     * @param movesLimit Limite du nombre de coup
     */
    public void setMovesLimit(int movesLimit){
        IEndGameChecker moveLimit = new MovesDefeatEndGameChecker(this, movesLimit);
        attach(moveLimit);
    }

    /**
     * Sera appelée par un des EndGameChecker pour spécifier la fin de la partie
     * @param victory
     */
    public void endOfGame(boolean victory){

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

    ////////////////////////////////////////////////////////////
    /////////////////IMPLEMENTATION DE L'INTERFACE//////////////
    ////////////////////////////////////////////////////////////
    @Override
    public void attach(IEndGameChecker endGameChecker) {
        this.endGameCheckers.add(endGameChecker);
    }

    @Override
    public void detach(IEndGameChecker endGameChecker) {
        this.endGameCheckers.remove(endGameChecker);
    }

    @Override
    public void notifyEndGameCheckers() {
        for(IEndGameChecker egc : endGameCheckers){
            egc.update();
        }
    }
}
