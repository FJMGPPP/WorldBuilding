package com.fjmg.worldbuilding;

import android.app.Application;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Build;

import com.fjmg.worldbuilding.data.DataBase;

public class WorldBuildingApplication extends Application {
    public static String IDCHANEL = "WorldBuildingChannel";
    @Override
    public void onCreate()
    {
        super.onCreate();
        DataBase.create(this);
        createNotificationChannel();
    }

    private void createNotificationChannel()
    {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)
        {
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            String nameChannel = getString(R.string.name_channel);
            NotificationChannel notificationChannel = new NotificationChannel(IDCHANEL,
                    nameChannel,
                    importance);
            long[] ArrayVibraciones = new long[]{200,200,10,2000,30};
            notificationChannel.setVibrationPattern(ArrayVibraciones);
            getSystemService(NotificationManager.class).createNotificationChannel(notificationChannel);
        }
    }
}
