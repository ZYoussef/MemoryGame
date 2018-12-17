package memory.android.istia.memorygame.activities;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import memory.android.istia.memorygame.utils.NotificationServiceManager;
import memory.android.istia.memorygame.R;
import memory.android.istia.memorygame.utils.FragmentController;
import memory.android.istia.memorygame.utils.SharedPreferenceManager;

/**
 * MainActivity--- Activité de l'application (UNIQUE)
 * Initialisation du SharedPreferenceManager et du FragmentController
 *
 * @author Sébastien, Thomas, Youssef
 * @version 1.0
 */
public class MainActivity extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Initialisation du SharedPreference qui sera globale pour toute l'application
        SharedPreferenceManager.init(getApplicationContext());

        //Initialisation du FragmentController
        FragmentController.init(getSupportFragmentManager());

        //Initialisation du notificationsManager
        NotificationServiceManager.init(getApplicationContext());

        //Affichage du menu principal
        //FragmentController.getInstance().openFragment(FragmentController.Fragments.MAIN_MENU);
    }


    @Override
    public void onBackPressed() {
        FragmentController.getInstance().onBack();
    }
}
