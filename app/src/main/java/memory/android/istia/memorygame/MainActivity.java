package memory.android.istia.memorygame;

import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;

import java.util.Locale;

import memory.android.istia.memorygame.constantes.Constantes;
import memory.android.istia.memorygame.enums.EnumLanguage;
import memory.android.istia.memorygame.enums.EnumSettings;
import memory.android.istia.memorygame.services.AudioService;
import memory.android.istia.memorygame.utils.CustomFragmentManager;
import memory.android.istia.memorygame.utils.NotificationServiceManager;
import memory.android.istia.memorygame.utils.SharedPreferenceManager;

/**
 * MainActivity--- Activité de l'application (UNIQUE)
 * Initialisation du SharedPreferenceManager et du CustomFragmentManager
 *
 * @author Sébastien, Thomas, Youssef
 * @version 1.0
 */
public class MainActivity extends FragmentActivity {

    //Permet de savoir si l'application est stoppé ou non
    protected static boolean isVisible = false;

    private MediaPlayer mediaPlayerClick;
    private boolean timerNotifIsRunning;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Initialisation du SharedPreference qui sera globale pour toute l'application
        SharedPreferenceManager.init(getApplicationContext());

        //Initialisation du CustomFragmentManager
        CustomFragmentManager.init(getSupportFragmentManager());

        //Initialisation du notificationsManager
        NotificationServiceManager.init(getApplicationContext());

        //Affichage du menu principal
        CustomFragmentManager.getInstance().openFragment(CustomFragmentManager.Fragments.MAIN_MENU);

        Log.e("test", "Valeur sound_is_on : " + SharedPreferenceManager.read(EnumSettings.SOUND_IS_ON, true));

        setLanguage();

        setMusic(SharedPreferenceManager.read(EnumSettings.SOUND_IS_ON, true));


        if(!timerNotifIsRunning)
            startTimer();

        mediaPlayerClick = MediaPlayer.create(this, R.raw.button_click);

    }

    public void setMusic(boolean state){

        if(state && !isMyServiceRunning(AudioService.class)){
            Intent svc= new Intent(this, AudioService.class);
            startService(svc);
        }
        else if(!state && isMyServiceRunning(AudioService.class)){
            Intent svc= new Intent(this, AudioService.class);
            stopService(svc);
        }
    }

    public void playClickSound(){
        if(SharedPreferenceManager.read(EnumSettings.SOUND_IS_ON, true)) {
            mediaPlayerClick.start();
        }
    }

    /**
     * Timer de notifications
     * Envois une notification toutes les 30 minutes
     */
    private void startTimer() {
        timerNotifIsRunning = true;

        Thread t = new Thread(new TimerNotification(this, Constantes.TEMPSNOTIFICATION));
        t.start();
    }

    /**
     * Modifie la langue de l'application si celle enregistré n'est pas celle par défaut
     */
    private void setLanguage(){
        Locale current = getResources().getConfiguration().locale;
        EnumLanguage currentLanguage = EnumLanguage.values()[SharedPreferenceManager.read(EnumSettings.LANGUAGE_SELECTED, 0)];

        if(!current.toString().equals(currentLanguage.toString())){
            Locale locale = new Locale(currentLanguage.toString());
            Locale.setDefault(locale);
            Configuration config = new Configuration();
            config.locale = locale;
            getBaseContext().getResources().updateConfiguration(config, getBaseContext().getResources().getDisplayMetrics());

            finish();
            overridePendingTransition(0, 0);
            startActivity(getIntent());
            overridePendingTransition(0, 0);
        }
    }

    @Override
    public void onBackPressed() {
        CustomFragmentManager.getInstance().onBack();
    }
    
    /*
    * On coupe le service de musique quand l'application passe en pause
    */
    @Override
    public void onPause(){
        super.onPause();
        setMusic(false);
        isVisible = false;
    }
    
    @Override
    public void onResume(){
        super.onResume();
        setMusic(SharedPreferenceManager.read(EnumSettings.SOUND_IS_ON, true));
        isVisible = true;
    }

    private boolean isMyServiceRunning(Class<?> serviceClass) {
        ActivityManager manager = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
        for (ActivityManager.RunningServiceInfo service : manager.getRunningServices(Integer.MAX_VALUE)) {
            if (serviceClass.getName().equals(service.service.getClassName())) {
                return true;
            }
        }
        return false;
    }
}
