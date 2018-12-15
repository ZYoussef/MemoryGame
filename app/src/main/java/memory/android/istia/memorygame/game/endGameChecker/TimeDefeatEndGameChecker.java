package memory.android.istia.memorygame.game.endGameChecker;

import memory.android.istia.memorygame.game.GameManager;

/**
 * TimeDefeatEndGameChecker--- implémente IEndGameChecker
 *  Condition de défaite pour le temps spécifié
 *  Appelera le GameManager lorsque le temps sera écoulé
 *
 *  TODO : A lancer dans un nouveau service + lancer le chronomètre, voir pour l'affichage
 *  (avertir GameManager ou créer un fragment ici et l'ajouter ?)
 *
 * @author Sébastien, Thomas, Youssef
 * @version 1.0
 */
public class TimeDefeatEndGameChecker implements IEndGameChecker {

    private GameManager mGameManager;
    private int mTimeLimit;

    public TimeDefeatEndGameChecker(GameManager gm, int timeLimit){
        this.mGameManager = gm;
        this.mTimeLimit = timeLimit;
    }

    @Override
    public void notifyGameManager() {
        mGameManager.endOfGame(false);
    }
}
