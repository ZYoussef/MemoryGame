package memory.android.istia.memorygame;

import android.content.Context;

import memory.android.istia.memorygame.utils.NotificationServiceManager;

public class TimerNotification implements Runnable {

    private Context context;
    private static int timeBetweenNotification = 18000000;

    public TimerNotification(Context context){
        this.context = context;
    }
    @Override
    public void run() {
        while(true){
            try {
                Thread.sleep(timeBetweenNotification);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            NotificationServiceManager.getInstance().sendNewNotification(context.getString(R.string.timer), context.getString(R.string.notif_timer), R.drawable.clock);
        }

    }
}
