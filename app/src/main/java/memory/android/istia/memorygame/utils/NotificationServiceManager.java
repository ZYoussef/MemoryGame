package memory.android.istia.memorygame.utils;


import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.v4.app.NotificationCompat;

import memory.android.istia.memorygame.activities.MainActivity;

/**
 * NotificationServiceManager--- Permet d'envoyer des notifications
 * A initialiser dans le MainActivity puis il suffit ensuite d'appeler la méthode sendNewNotification
 *
 * @author Sébastien, Thomas, Youssef
 *
 * @version 1.0
 */
public class NotificationServiceManager {

    private NotificationManager mNotificationManager;
    private Context mContext;

    private int mNotifyId;

    private static NotificationServiceManager mNotificationServiceManager;

    private NotificationServiceManager(Context context){
        mContext = context;
        mNotificationManager = (NotificationManager) mContext.getSystemService(Context.NOTIFICATION_SERVICE);
        mNotifyId = 0;
    }

    public static void init(Context context){
        mNotificationServiceManager = new NotificationServiceManager(context);
    }

    public static NotificationServiceManager getInstance(){
        if(mNotificationServiceManager == null){
            throw new IllegalStateException("NotificationManager has not been initialised");
        }

        return mNotificationServiceManager;
    }

    /**
     * Permet de créer une nouvelle notification
     * @param notificationTitle Titre de la notification
     * @param notificationContent Contenu de la notification
     * @param smallIconId Icone (drawable) à afficher
     */
    public void sendNewNotification(String notificationTitle, String notificationContent, int smallIconId){

        String id = "channel_default_id";
        Intent intent = new Intent(mContext, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(mContext, 0, intent, 0);;
        NotificationCompat.Builder builder = new NotificationCompat.Builder(mContext, id);

        builder.setContentTitle(notificationTitle)
                .setSmallIcon(smallIconId)
                .setContentText(notificationContent)
                .setDefaults(Notification.DEFAULT_ALL)
                .setAutoCancel(true)
                .setContentIntent(pendingIntent);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel mChannel = mNotificationManager.getNotificationChannel(id);

            if (mChannel == null) {
                //Channel null, on le créer (obligatoire pour les versions récentes d'Android
                mChannel = new NotificationChannel(id, "channel_default_title", NotificationManager.IMPORTANCE_HIGH);
                mChannel.enableVibration(true);
                mNotificationManager.createNotificationChannel(mChannel);
            }
        }
        else {
            builder.setPriority(Notification.PRIORITY_HIGH);
        }

        mNotificationManager.notify(mNotifyId, builder.build());

        mNotifyId++;
    }
}
