package memory.android.istia.memorygame.game.endGameChecker;

import android.widget.TextView;

import memory.android.istia.memorygame.game.GameManager;

/**
 * MovesDefeatEndGameChecker--- implémente IEndGameChecker
 * Condition de défaite selon un nombre de coups limité
 * Appelera le GameManager lorsque le nombre des coups joués atteint le nombre de coups limite
 *
 * @author Sébastien, Thomas, Youssef
 * @version 1.0
 */
public class MovesDefeatEndGameChecker implements IEndGameChecker {

    public static final int MAX_SCORE = 50;
    private GameManager mGameManager;
    /**
     * Nombre de coups max à jouer
     */
    private int mMovesLimit;

    /**
     * Nombre de coups joués
     */
    private int mMovesPlayed;

    private TextView movesLeftUI;

    public MovesDefeatEndGameChecker(GameManager gameManager, int movesLimit, TextView movesLeftUI){
        this.mGameManager = gameManager;
        this.mMovesLimit = movesLimit;
        this.mMovesPlayed = 0;
        this.movesLeftUI = movesLeftUI;

        this.movesLeftUI.setText("Coups restants : " + (this.mMovesLimit - this.mMovesPlayed));
    }

    @Override
    public void notifyGameManager() {
        //Défaite
        this.mGameManager.endOfGame(false, calculateScore());
    }

    @Override
    public void update() {
        //Rajoute un coup joué + vérification victoire ou défaite
        this.mMovesPlayed++;
        this.movesLeftUI.setText("Coups restants : " + (this.mMovesLimit - this.mMovesPlayed));

        if(this.mMovesPlayed >= this.mMovesLimit){
            notifyGameManager();
        }
    }

    @Override
    public int calculateScore()
    {
        return (this.mMovesPlayed * MAX_SCORE)/this.mMovesLimit;
    }
}
