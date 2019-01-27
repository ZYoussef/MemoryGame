package memory.android.istia.memorygame.game.endGameChecker;

import memory.android.istia.memorygame.game.GameManager;

/**
 * VictoryEndGameChecker--- implémente IEndGameChecker
 *  Condition de victoire pour le jeu (lorsque toutes les paires sont trouvées
 *
 *  (avertir GameManager ou créer un fragment ici et l'ajouter ?)
 *
 * @author Sébastien, Thomas, Youssef
 * @version 1.0
 */
public class VictoryEndGameChecker implements IEndGameChecker {

    private int mNbPairs;
    private GameManager mGameManager;

    public VictoryEndGameChecker(GameManager gameManager){
        this.mGameManager = gameManager;
        this.mNbPairs = this.mGameManager.getCardsPair();
    }

    @Override
    public void notifyGameManager() {
        this.mGameManager.endOfGame(true);
    }

    @Override
    public void update() {
        if(this.mGameManager.getCardsPairFound() == mNbPairs){
            notifyGameManager();
        }
    }

    @Override
    public int calculateScore() {
        return MAX_SCORE;
    }
}
