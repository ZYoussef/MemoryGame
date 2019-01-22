package memory.android.istia.memorygame;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;

import java.util.Timer;
import java.util.TimerTask;

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

    private MediaPlayer mediaPlayer;
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
        startTimer();
        mediaPlayer = new MediaPlayer();
        mediaPlayer = MediaPlayer.create(this, R.raw.back_music);
        mediaPlayer.setLooping(true);


        //Musique
        if(SharedPreferenceManager.read(SharedPreferenceManager.Settings.SOUND_IS_ON, true)){
            setMusic(true);
        }
    }

    public void setMusic(boolean state){

        if(state){
            mediaPlayer.start();
        }
        else{
            mediaPlayer.pause();
        }

    }

    public void startTimer() {
        timer = new Timer();
        TimerTask timerTask = new TimerTask() {

            @Override
            public void run() {
                runOnUiThread(new Runnable() {

                    @Override
                    public void run() {
                    // NOTIFICATION
                    }
                });
            }
        };
        // 1800000 milliseconds = 30minutes
        timer.scheduleAtFixedRate(timerTask, 1800000, 1800000);
    }

    @Override
    public void onBackPressed() {
        FragmentController.getInstance().onBack();
    }
}
