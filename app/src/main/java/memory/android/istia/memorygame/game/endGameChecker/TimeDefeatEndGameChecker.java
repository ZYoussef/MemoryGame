package memory.android.istia.memorygame.game.endGameChecker;

import android.os.AsyncTask;

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

    public TimeDefeatEndGameChecker(GameManager gm, int timeLimit){
        this.mGameManager = gm;
        this.mTimeLimit = timeLimit;
    }

    @Override
    public void notifyGameManager() {
        mGameManager.endOfGame(false);
    }

    @Override
    public void update() {
        //N'a pas besoin  de recevoir les données de la partie
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
        while(this.mTimeLimit > 0){
            try {
                Thread.sleep(1000);
                this.mTimeLimit -= 1;
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
        publishProgress(values);

        //TODO Avertir le GameManager pour l'affichage du chronomètre visuellement
    }
}
