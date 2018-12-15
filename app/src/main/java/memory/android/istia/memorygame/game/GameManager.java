package memory.android.istia.memorygame.game;

import memory.android.istia.memorygame.game.endGameChecker.TimeDefeatEndGameChecker;

/**
 * GameManager--- Gère l'ensemble de la logique du jeu
 *
 * @author Sébastien, Thomas, Youssef
 * @version 1.0
 */
public class GameManager {

    private int mCardsPair;
    private int mCardsPairFound;

    public GameManager(int cardsPair){
        mCardsPair = cardsPair;
        mCardsPairFound = 0;
    }

    /**
     * Rajoute une condition de défaite pour le jeu avec une limite de temps
     * @param timeLimit limite de temps en seconde
     */
    public void setTimeLimit(int timeLimit){
        new TimeDefeatEndGameChecker(this, timeLimit);
    }

    /**
     * Vérification de la victoire, appelée à chaque retournement de carte
     */
    private void checkVictory(){
        if(mCardsPair == mCardsPairFound){
            endOfGame(true);
        }
    }

    /**
     * Sera appelée par un des EndGameChecker pour spécifier la fin de la partie
     * @param victory
     */
    public void endOfGame(boolean victory){

    }


}
