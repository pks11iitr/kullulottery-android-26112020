package com.kuil.KuilLottery.commonutils;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.media.RingtoneManager;
import android.os.Build;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;
import androidx.core.app.TaskStackBuilder;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.kuil.KuilLottery.R;
import com.kuil.KuilLottery.acitvities.NotificationActivity;

import java.util.Random;

public class NotificationService extends FirebaseMessagingService {

    @Override
    public void onMessageReceived(@NonNull RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);
      if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.O){
          Random random = new Random(10000000);
          int n = random.nextInt();

          Intent notificationIntent= new Intent(NotificationService.this, NotificationActivity.class);
          PendingIntent pendingIntent = PendingIntent.getActivity(NotificationService.this,1,notificationIntent,0);
          int notifyid = n;
          String channel_id = "channel_kuil";
          CharSequence name = "kuil";
          int importance = NotificationManager.IMPORTANCE_HIGH;
          NotificationChannel mChannel = new NotificationChannel(channel_id, name, importance);
          NotificationManager mNotificationManager = (NotificationManager) NotificationService.this.getSystemService(NotificationService.this.NOTIFICATION_SERVICE);
          mNotificationManager.createNotificationChannel(mChannel);

          Notification notification = new NotificationCompat.Builder(NotificationService.this, channel_id)
                  .setColor(Color.parseColor("#E96125"))
                  .setSmallIcon(R.drawable.logo)
                  .setLargeIcon(BitmapFactory.decodeResource(NotificationService.this.getResources(),
                          R.drawable.logo))
                  .setContentTitle(remoteMessage.getData().get("title"))
                  .setContentText(remoteMessage.getData().get("body"))
                  .setVibrate(new long[]{1000, 1000})
                  .setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM))
                  .setContentIntent(pendingIntent)
                  .build();
          mNotificationManager.notify(notifyid, notification);
      }
      else {

          Intent notificationIntent = new Intent(NotificationService.this, NotificationActivity.class);

          TaskStackBuilder stackBuilder = TaskStackBuilder.create(NotificationService.this);
          stackBuilder.addParentStack(NotificationActivity.class);
          stackBuilder.addNextIntent(notificationIntent);

          PendingIntent pendingIntent = stackBuilder.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);

          NotificationCompat.Builder builder = new NotificationCompat.Builder(NotificationService.this);

          Notification notification = builder.setContentTitle(remoteMessage.getData().get("title"))
                  .setColor(Color.parseColor("#E96125"))
                  .setContentText(remoteMessage.getData().get("body"))
                  .setSmallIcon(R.drawable.logo)
                  .setLargeIcon(BitmapFactory.decodeResource(NotificationService.this.getResources(),
                          R.drawable.logo))
                  .setVibrate(new long[]{1000, 1000})
                  .setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM))
                  .setPriority(NotificationCompat.PRIORITY_HIGH)

                  .setStyle(new NotificationCompat.BigTextStyle()
                          .bigText(remoteMessage.getData().get("body")))
                  .setContentIntent(pendingIntent).build();

          NotificationManager notificationManager = (NotificationManager) NotificationService.this.getSystemService(Context.NOTIFICATION_SERVICE);
          notificationManager.notify(0, notification);
      }
    }
}
