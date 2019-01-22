package memory.android.istia.memorygame.game.endGameChecker;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;

import memory.android.istia.memorygame.game.GameManager;

/**
 * TimeDefeatEndGameChecker--- implémente IEndGameChecker
 *  Condition de défaite pour le temps spécifié
 *  Appelera le GameManager lorsque le temps sera écoulé
 *
 * @author Sébastien, Thomas, Youssef
 * @version 1.0
 */
public class TimeDefeatEndGameChecker extends AsyncTask<Void, Integer, Void> implements IEndGameChecker {

    /**
     * Score max possible
     */
    private GameManager mGameManager;
    /**
     * La limite du temps en seconde
     */
    private int mTimeLimit;
    /**
     * Décompteur
     */
    private int mCountDown;

    private String difficulty;
    private boolean gameIsRunning;

    private TextView editTextTime;

    public TimeDefeatEndGameChecker(GameManager gm, String difficulty, TextView editTextTime){
        this.mGameManager = gm;
        this.gameIsRunning = true;

        switch(difficulty){
            case "easy":
                this.mTimeLimit = 20;
                this.mCountDown = 20;
                break;
            case "medium":
                this.mTimeLimit = 50;
                this.mCountDown = 50;
                break;
            case "hard":
                this.mTimeLimit = 80;
                this.mCountDown = 80;
                break;
        }

        this.difficulty = difficulty;
        this.editTextTime = editTextTime;
    }

    @Override
    public void notifyGameManager() {
        mGameManager.endOfGame(false);
    }

    @Override
    public void update() {
        //N'a pas besoin  de recevoir les données de la partie
    }

    @Override
    public int calculateScore() {
        switch(difficulty){
            case "easy":
                return MAX_SCORE - ((this.mTimeLimit - this.mCountDown - 5) * 50);
            case "medium":
                return MAX_SCORE - ((this.mTimeLimit - this.mCountDown - 15) * 40);
            case "hard":
                return MAX_SCORE - ((this.mTimeLimit - this.mCountDown - 35) * 30);
            default: return MAX_SCORE;
        }


    }

    /**
     * Lance un chronomère en background qui avertira le GameManager quand le temps
     * sera écoulé
     * @param voids
     * @return Null
     */
    @Override
    protected Void doInBackground(Void... voids) {

        //Chronomètre
        while(this.mCountDown> 0 && gameIsRunning){
            try {
                Thread.sleep(1000);
                this.mCountDown-= 1;
                publishProgress(this.mCountDown);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        notifyGameManager();
        return null;
    }

    public void setGameIsRunning(boolean value){
        this.gameIsRunning = value;
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
        this.editTextTime.setText("" + this.mCountDown);
    }


}
