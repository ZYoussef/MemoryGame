package memory.android.istia.memorygame.game.endGameChecker;

import android.util.Log;

import memory.android.istia.memorygame.game.GameManager;

/**
 * VictoryEndGameChecker--- implémente IEndGameChecker
 *  Condition de victoire pour le jeu (lorsque toutes les paires sont trouvées
 *
 *
 *  TODO : A lancer dans un nouveau service + lancer le chronomètre, voir pour l'affichage
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
        this.mGameManager.endOfGame(true, calculateScore());
    }

    @Override
    public void update() {
        if(this.mGameManager.getCardsPairFound() == mNbPairs){
            notifyGameManager();
        }
    }

    @Override
    public int calculateScore() {
        //TODO calcul score victoire
        return 100;
    }
}
