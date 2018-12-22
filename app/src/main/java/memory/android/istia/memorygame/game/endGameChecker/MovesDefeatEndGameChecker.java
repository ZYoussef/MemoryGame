package memory.android.istia.memorygame.game.endGameChecker;

import memory.android.istia.memorygame.game.GameManager;

/**
 * MovesDefeatEndGameChecker--- implémente IEndGameChecker
 *  Condition de défaite selon un nombte de coups limité
 *  Appelera le GameManager lorsque le nombre des coups joués atteint le nombre de coups limite
 *
 * @author Sébastien, Thomas, Youssef
 * @version 1.0
 */
public class MovesDefeatEndGameChecker implements IEndGameChecker {

    private GameManager mGameManager;
    private int mMovesLimit;
    private int mMovesPlayed;

    public MovesDefeatEndGameChecker(GameManager gameManager, int movesLimit){
        this.mGameManager = gameManager;
        this.mMovesLimit = movesLimit;
        this.mMovesPlayed = 0;
    }

    @Override
    public void notifyGameManager() {
        //Défaite
        this.mGameManager.endOfGame(false);
    }

    @Override
    public void update() {
        //Rajoute un coup joué + vérification victoire ou défaite
        this.mMovesPlayed++;

        if(this.mMovesPlayed >= this.mMovesLimit){
            notifyGameManager();
        }
    }
}
