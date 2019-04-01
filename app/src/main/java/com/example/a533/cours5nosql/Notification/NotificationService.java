package com.example.a533.cours5nosql.Notification;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.Build;
import android.os.IBinder;
import android.support.annotation.Nullable;

import com.example.a533.cours5nosql.MainActivity;
import com.example.a533.cours5nosql.Notification.model.ImportantMessageModel;
import com.example.a533.cours5nosql.Notification.model.MessageModel;
import com.example.a533.cours5nosql.R;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import org.w3c.dom.DocumentType;

public class NotificationService extends Service {
    public static final String CHANNEL_ID = "NotificationService";
    NotificationManager notificationManager;
    FirebaseFirestore database;
    int idNotification = 2;

    @Override
    public void onCreate() {
        createNotificationChannel();
        database = FirebaseFirestore.getInstance();
        listenForNotificationMessage();
        listenForNotificationImportantMessage();
        super.onCreate();
    }

    public void createNotificationChannel(){
        createNotificationChannelService();
        createNotificationChannelMessage();
        createNotificationChannelImportantMessage();
    }

    private void createNotificationChannelService(){
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            String channelId = CHANNEL_ID;
            CharSequence channelName = "NotificationService";
            String channelDescription = "Notification service";
            int channelImportance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(channelId, channelName, channelImportance);
            channel.setDescription(channelDescription);
            notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }

    private void createNotificationChannelMessage(){
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            String channelId = "42";
            CharSequence channelName = "NotificationMessage";
            String channelDescription = "Notification de message";
            int channelImportance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(channelId, channelName, channelImportance);
            channel.setDescription(channelDescription);
            notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }

    private void createNotificationChannelImportantMessage(){
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            String channelId = "43";
            CharSequence channelName = "NotificationImportantMessage";
            String channelDescription = "Notification de message important";
            int channelImportance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(channelId, channelName, channelImportance);
            channel.setDescription(channelDescription);
            notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Intent notificationServiceIntent = new Intent(this, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, notificationServiceIntent, 0);
        android.support.v4.app.NotificationCompat.Builder builder = new android.support.v4.app.NotificationCompat.Builder(this, CHANNEL_ID)
                .setSmallIcon(R.drawable.icon)
                .setContentTitle("Notification service")
                .setContentIntent(pendingIntent);
        Notification notification = builder.build();
        startForeground(1, notification);
        //todo ligne du listen
        return START_STICKY;
    }

    private void listenForNotificationMessage(){
        database.collection("Notification").addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@javax.annotation.Nullable QuerySnapshot queryDocumentSnapshots, @javax.annotation.Nullable FirebaseFirestoreException e) {
                for(DocumentChange documentChange : queryDocumentSnapshots.getDocumentChanges()){
                    if(documentChange.getType() == DocumentChange.Type.ADDED){
                        MessageModel messageModel = documentChange.getDocument().toObject(MessageModel.class);
                        sendNotificationForMessage(messageModel);
                    }
                }
            }
        });
    }

    private void listenForNotificationImportantMessage(){
        database.collection("NotificationImportante").addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@javax.annotation.Nullable QuerySnapshot queryDocumentSnapshots, @javax.annotation.Nullable FirebaseFirestoreException e) {
                for(DocumentChange documentChange : queryDocumentSnapshots.getDocumentChanges()){
                    if(documentChange.getType() == DocumentChange.Type.ADDED){
                        ImportantMessageModel importantMessageModel = documentChange.getDocument().toObject(ImportantMessageModel.class);
                        sendNotificationForImportantMessage(importantMessageModel);
                    }
                }
            }
        });
    }

    private void sendNotificationForMessage(MessageModel messageModel){
        Notification notification = NotificationCreator.createNotificationForMessage(this, messageModel);
        notificationManager.notify(idNotification, notification);
        idNotification++;
    }

    private void sendNotificationForImportantMessage(ImportantMessageModel importantMessageModel){
        Notification notification = NotificationCreator.createNotificationForImportantMessage(this, importantMessageModel);
        notificationManager.notify(idNotification, notification);
        idNotification++;
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
