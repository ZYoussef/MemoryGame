package memory.android.istia.memorygame;

import android.content.Context;
import android.util.Log;

import memory.android.istia.memorygame.utils.NotificationServiceManager;

public class TimerNotification implements Runnable {

    private Context context;
    private int timeBetweenNotification;
    private boolean sendNotification;

     TimerNotification(Context context, int timeBetweenNotification){
        this.context = context;
        this.timeBetweenNotification = timeBetweenNotification;
        this.sendNotification = true;
    }
    @Override
    public void run() {
        while(sendNotification){
            try {
                Thread.sleep(timeBetweenNotification);
            } catch (InterruptedException e) {
                Log.e("error", e.getMessage());
                Thread.currentThread().interrupt();
            }
            NotificationServiceManager.getInstance().sendNewNotification(context.getString(R.string.timer), context.getString(R.string.notif_timer), R.drawable.clock);
        }
    }

    public void stopSendingNotification(){
         this.sendNotification = false;
    }
}
