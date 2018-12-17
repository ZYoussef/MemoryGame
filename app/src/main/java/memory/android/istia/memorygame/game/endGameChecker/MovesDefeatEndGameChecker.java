package memory.android.istia.memorygame.game.endGameChecker;

import memory.android.istia.memorygame.game.GameManager;

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
