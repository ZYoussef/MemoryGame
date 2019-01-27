package memory.android.istia.memorygame;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import java.util.Timer;
import java.util.TimerTask;

import memory.android.istia.memorygame.enums.EnumSharedPreferences;
import memory.android.istia.memorygame.utils.NotificationServiceManager;
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

    private MediaPlayer mediaPlayerMusic;
    private MediaPlayer mediaPlayerClick;

    private boolean timerNotifIsRunning;


    private Timer timer = new Timer();

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
        FragmentController.getInstance().openFragment(FragmentController.Fragments.MAIN_MENU);

        if(!timerNotifIsRunning)
            startTimer();

        mediaPlayerMusic = MediaPlayer.create(this, R.raw.back_music);
        mediaPlayerMusic.setLooping(true);

        mediaPlayerClick = MediaPlayer.create(this, R.raw.button_click);


        //Musique
        if(SharedPreferenceManager.read(EnumSharedPreferences.SOUND_IS_ON, true)){
            setMusic(true);
        }
    }

    public void setMusic(boolean state){

        if(state){
            mediaPlayerMusic.start();
        }
        else{
            mediaPlayerMusic.pause();
        }
    }

    public void playClickSound(){
        if(SharedPreferenceManager.read(EnumSharedPreferences.SOUND_IS_ON, true)) {
            mediaPlayerClick.start();
        }
    }

    public void startTimer() {
        timerNotifIsRunning = true;

        Thread t = new Thread(new TimerNotification(this));
        t.start();
    }

    @Override
    public void onBackPressed() {
        FragmentController.getInstance().onBack();
    }
}
