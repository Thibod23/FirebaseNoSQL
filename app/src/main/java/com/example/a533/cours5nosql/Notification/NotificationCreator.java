package com.example.a533.cours5nosql.Notification;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;

import com.example.a533.cours5nosql.MainActivity;
import com.example.a533.cours5nosql.Notification.model.ImportantMessageModel;
import com.example.a533.cours5nosql.Notification.model.MessageModel;
import com.example.a533.cours5nosql.R;

public class NotificationCreator {
    public static Notification createNotificationForMessage(Context context, MessageModel message){
        android.support.v4.app.NotificationCompat.Builder builder = new android.support.v4.app.NotificationCompat.Builder(context, "42")
                .setSmallIcon(R.drawable.icon)
                .setContentTitle(message.getSender())
                .setContentText(message.getMessage())
                .setPriority(android.support.v4.app.NotificationCompat.PRIORITY_DEFAULT);
        return builder.build();
    }
    public static Notification createNotificationForImportantMessage(Context context, ImportantMessageModel message){
        Intent notificationServiceIntent = new Intent(context, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, notificationServiceIntent, 0);
        android.support.v4.app.NotificationCompat.Builder builder = new android.support.v4.app.NotificationCompat.Builder(context, "43")
                .setSmallIcon(R.drawable.icon)
                .setContentTitle(message.getSender())
                .setContentText(message.getMessage())
                .setPriority(NotificationCompat.PRIORITY_MAX)
                .addAction(R.drawable.icon, "Marqué comme lu", pendingIntent);
        return builder.build();
    }

}
