package memory.android.istia.memorygame.game.end_game_checker;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.TextView;

import memory.android.istia.memorygame.constantes.Constantes;
import memory.android.istia.memorygame.enums.EnumDifficulty;
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

    private GameManager mGameManager;
    private int mTimeLimit;
    private int mCountDown;
    private EnumDifficulty mDifficulty;
    private boolean gameIsRunning;
    private Context mContext;
    private TextView mEditTextTime;

    public TimeDefeatEndGameChecker(GameManager gm, EnumDifficulty difficulty, TextView editTextTime, Context context){
        this.mGameManager = gm;
        this.gameIsRunning = true;
        this.mDifficulty = difficulty;
        this.mEditTextTime = editTextTime;
        this.mContext = context;

        switch(difficulty){
            case EASY:
                this.mTimeLimit = 20;
                this.mCountDown = 20;
                break;
            case MEDIUM:
                this.mTimeLimit = 50;
                this.mCountDown = 50;
                break;
            case HARD:
                this.mTimeLimit = 80;
                this.mCountDown = 80;
                break;
        }


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
        switch(mDifficulty){
            case EASY:
                return Constantes.MAXSCORE - ((this.mTimeLimit - this.mCountDown - 5) * 50);
            case MEDIUM:
                return Constantes.MAXSCORE - ((this.mTimeLimit - this.mCountDown - 15) * 40);
            case HARD:
                return Constantes.MAXSCORE - ((this.mTimeLimit - this.mCountDown - 35) * 30);
            default: return Constantes.MAXSCORE;
        }
    }

    /**
     * Lance un chronomère en background qui avertira le GameManager quand le temps
     * sera écoulé
     * @param voids voids
     * @return Null
     */
    @Override
    protected Void doInBackground(Void... voids) {

        //Chronomètre
        while(this.mCountDown> 0 && gameIsRunning){
            try {
                publishProgress(this.mCountDown);
                Thread.sleep(1000);
                this.mCountDown-= 1;
            } catch (InterruptedException e) {
                Log.e("error", e.getMessage());
                Thread.currentThread().interrupt();
            }
        }
        publishProgress(this.mCountDown);
        notifyGameManager();
        return null;
    }

    public void setGameIsRunning(boolean value){
        this.gameIsRunning = value;
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
        this.mEditTextTime.setText(String.format(mContext.getResources().getConfiguration().locale, "%d", this.mCountDown));
    }
}
