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
    public static final int MAX_SCORE = 50;
    private GameManager mGameManager;
    /**
     * La limite du temps en seconde
     */
    private int mTimeLimit;
    /**
     * Décompteur
     */
    private int mCountDown;

    private TextView editTextTime;

    public TimeDefeatEndGameChecker(GameManager gm, int timeLimit, TextView editTextTime){
        this.mGameManager = gm;
        this.mTimeLimit = timeLimit;
        this.mCountDown = timeLimit;
        this.editTextTime = editTextTime;
        Log.d("TEST", "init time checker");
    }

    @Override
    public void notifyGameManager() {
        mGameManager.endOfGame(false, calculateScore());
    }

    @Override
    public void update() {
        //N'a pas besoin  de recevoir les données de la partie
    }

    @Override
    public int calculateScore() {

        // calcul de la marge
        int marge = (30*this.mTimeLimit)/100;
        // Cas d'un score parfait , avec une marge de 30%
        if(this.mCountDown >= this.mTimeLimit-marge)
            return MAX_SCORE;
        else
            // Le pourcentage sur le score max. la marge est prise en compte aussi
            return ((this.mCountDown-marge)*MAX_SCORE/this.mTimeLimit);
    }

    /**
     * Lance un chronomère en background qui avertira le GameManager quand le temps
     * sera écoulé
     * @param voids
     * @return Null
     */
    @Override
    protected Void doInBackground(Void... voids) {
        Log.d("TEST", "chrono started");

        //Chronomètre
        /*while(this.mTimeLimit > 0){
            try {
                Thread.sleep(1000);
                this.mTimeLimit -= 1;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }*/
        while(this.mCountDown> 0){
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

    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);

        //TODO Avertir le GameManager pour l'affichage du chronomètre visuellement
        editTextTime.setText("Temps restant : " + this.mCountDown);
    }


}
