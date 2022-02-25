package com.fjmg.worldbuilding.utils;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Base64;
import android.util.Patterns;

import androidx.annotation.RequiresApi;
import androidx.navigation.NavDeepLinkBuilder;
import androidx.preference.Preference;
import androidx.preference.PreferenceManager;
import androidx.preference.PreferenceScreen;

import com.fjmg.worldbuilding.R;
import com.fjmg.worldbuilding.WorldBuildingApplication;
import com.fjmg.worldbuilding.data.model.User;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Utils {


    static final String EMAIL_PATTERN = Patterns.EMAIL_ADDRESS.pattern();
    static final String PASSWORD_PATTERN = "^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-])(?!.*\\s).{8,20}";
    static final String USER_PATTERN = "^[A-Za-z]\\w{1,29}$";
    public static String IMAGEN_DEFAULT;

    public static boolean isPasswordValid(String password)
    {
        Pattern pattern = Pattern.compile(PASSWORD_PATTERN);
        Matcher matcher = pattern.matcher(password);
        return matcher.matches();
    }
    public static boolean isEmailValid(String email)
    {
        Pattern pattern = Pattern.compile(EMAIL_PATTERN);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
    public static boolean isUsernameValid(String username)
    {
        Pattern pattern = Pattern.compile(USER_PATTERN);
        Matcher matcher = pattern.matcher(username);
        return matcher.matches();
    }

    public static String DrawdeableEncode(Drawable imagen)
    {

           BitmapDrawable bitmapDraw = (BitmapDrawable)imagen;
           Bitmap bitmap = bitmapDraw.getBitmap();
           ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
           bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
           byte[] byteArray = byteArrayOutputStream.toByteArray();
           return Base64.encodeToString(byteArray, Base64.DEFAULT);
    }
    public static Drawable DrawdeableDecode(String base64, Context context)
    {
        byte[] byteArray  = Base64.decode(base64,Base64.DEFAULT);
        Bitmap bitmap = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);
        BitmapDrawable drawdeable = new BitmapDrawable(context.getResources(),bitmap);
        return  (Drawable)drawdeable;
    }

    public static String BitmapEncode(Bitmap imagen)
    {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        imagen.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
        byte[] byteArray = byteArrayOutputStream.toByteArray();
        return Base64.encodeToString(byteArray, Base64.DEFAULT);
    }
    public static Bitmap BitmapDecode(String base64)
    {
        byte[] byteArray  = Base64.decode(base64,Base64.DEFAULT);
        return BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);
    }
    @RequiresApi(api = Build.VERSION_CODES.O)
    static public void EnviarNotificacion(Context context, Activity activity, Bundle bundle,String titulo , String contenido)
    {
        int color = getColorByPreference(context);
        int icon = getIconPreference(context);
        Uri sound = getTonePreference(context);
        PendingIntent pendingIntent = new NavDeepLinkBuilder(context)
                .setGraph(R.navigation.nav_graph)
                .setDestination(R.id.universosFragment)
                .setArguments(bundle)
                .createPendingIntent();
        Notification.Builder builder = new Notification.Builder(context, WorldBuildingApplication.IDCHANEL)
                .setSmallIcon(icon)
                .setSound(sound)
                .setAutoCancel(true)
                .setColor(activity.getColor(color))
                .setContentTitle(titulo)
                .setContentText(contenido)
                .setContentIntent(pendingIntent);
        NotificationManager notificationManager = (NotificationManager) activity.getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(new Random().nextInt(100),builder.build());
    }

    private static int getColorByPreference(Context context)
    {
       String color = PreferenceManager.getDefaultSharedPreferences(context).getString(context.getString(R.string.coloresNotification), "");

       switch (color)
       {
           case "Blanco":
               return R.color.white;
           case "Azul":
               return R.color.purple_700;
           default:
               return R.color.white;
       }

    }

    private static int getIconPreference(Context context)
    {
        String color = PreferenceManager.getDefaultSharedPreferences(context).getString(context.getString(R.string.coloresNotification), "");

        switch (color)
        {
            case "a":
                return R.drawable.ic_person_icon_bar;
            case "b":
                return R.drawable.vector_bandera;
            default:
                return R.color.white;
        }

    }
    private static Uri getTonePreference(Context context)
    {
        String tono = PreferenceManager.getDefaultSharedPreferences(context).getString(context.getString(R.string.ringtone), "");
        Uri alarmSound = null;
        switch (tono)
        {
            case "tone":
             alarmSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_RINGTONE);
            break;
            case "bell":
                alarmSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM);
            break;
            case "morning":
                alarmSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALL);
                break;
            case "piano":
                alarmSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
                break;
            case "flow":
                alarmSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALL);
                break;
        }
        return alarmSound;

    }


    @RequiresApi(api = Build.VERSION_CODES.O)
    static public void EnviarNotificacion(Context context, Activity activity,String titulo , String contenido)
    {
        Uri sound = getTonePreference(context);
        int color = getColorByPreference(context);
        int icon = getIconPreference(context);
        Notification.Builder builder = new Notification.Builder(context, WorldBuildingApplication.IDCHANEL)
                .setSmallIcon(icon)
                .setColor(activity.getColor(color))
                .setSound(sound)
                .setAutoCancel(true)
                .setContentTitle(titulo)
                .setContentText(contenido);
        NotificationManager notificationManager = (NotificationManager) activity.getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(new Random().nextInt(100),builder.build());
    }
}
