package memory.android.istia.memorygame.game.endGameChecker;

import android.content.Context;
import android.widget.TextView;

import memory.android.istia.memorygame.R;
import memory.android.istia.memorygame.enums.EnumDifficulty;
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


    private GameManager mGameManager;
    /**
     * Nombre de coups max à jouer
     */
    private int mMovesLimit;

    /**
     * Nombre de coups joués
     */
    private int mMovesPlayed;

    private EnumDifficulty difficulty;

    private TextView movesLeftUI;

    private Context context;

    public MovesDefeatEndGameChecker(GameManager gameManager, EnumDifficulty difficulty, TextView movesLeftUI, Context context){
        this.mGameManager = gameManager;
        this.mMovesPlayed = 0;
        this.movesLeftUI = movesLeftUI;
        this.difficulty = difficulty;
        this.context = context;

        switch(difficulty){
            case EASY:
                this.mMovesLimit = 4 * 3;
                break;
            case MEDIUM:
                this.mMovesLimit = 8 * 3;
                break;
            case HARD:
                this.mMovesLimit = 16 * 3;
                break;
        }

        this.movesLeftUI.setText(context.getString(R.string.remainingMoves)  + (this.mMovesLimit - this.mMovesPlayed));
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
        this.movesLeftUI.setText(context.getString(R.string.remainingMoves) + (this.mMovesLimit - this.mMovesPlayed));

        if(this.mMovesPlayed >= this.mMovesLimit){
            notifyGameManager();
        }
    }

    @Override
    public int calculateScore()
    {
        switch(this.difficulty){
            case EASY:
                return MAX_SCORE - ((this.mMovesPlayed - 4) * 100);
            case MEDIUM:
                return MAX_SCORE - ((this.mMovesPlayed - 8) * 75);
            case HARD:
                return MAX_SCORE - ((this.mMovesPlayed - 32) * 25);
            default: return MAX_SCORE;
        }
    }
}
