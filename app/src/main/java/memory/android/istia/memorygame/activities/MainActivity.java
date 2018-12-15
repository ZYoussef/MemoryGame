package memory.android.istia.memorygame.activities;

import android.app.Activity;
import android.os.Bundle;

import memory.android.istia.memorygame.R;
import memory.android.istia.memorygame.utils.SharedPreferenceManager;

/**
 * MainActivity--- Affichage du menu principal de l'application :
 *  - Jouer (lance une nouvelle partie)
 *  - Options (accès aux options de l'application)
 *  - Scores (accès aux X plus gros scores réalisé)
 *  - Crédits (accès aux crédits de l'application)
 *
 * @author Sébastien, Thomas, Youssef
 * @version 1.0
 */
public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Initialisation du SharedPreference qui sera globale pour toute l'application
        SharedPreferenceManager.init(getApplicationContext());
    }
}
