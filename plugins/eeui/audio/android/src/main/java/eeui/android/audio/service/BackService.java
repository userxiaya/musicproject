package eeui.android.audio.service;

import android.app.ActivityManager;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.IBinder;
import android.text.TextUtils;
import android.util.Log;
import android.widget.RemoteViews;

import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import eeui.android.audio.R;
import eeui.android.audio.entry.audioEntry;

public class BackService extends Service {
    public static ButtonBroadcastReceiver receiver;
    public final static String INTENT_BUTTONID_TAG = "ButtonId";

    public static final String ACTION_SERVICE = "com.notification.intent.action.ButtonClick";// 广播标志
    //    通知栏
    public final static int ACTION_NEXT = 1;// 下一首广播标志
    public final static int ACTION_PREV = 2;// 上一首广播标志
    public final static int ACTION_PLAY_PAUSE = 3;// 播放暂停广播
    public final static int ACTION_CLOSE = 4;// 播放暂停广播
    private static final int NOTIFICATION_ID = 123789;
    private static String songName = "";
    private static String singerName = "";
    private static String notificationCover = "";

    private static NotificationManager mNotificationManager;
    private static NotificationCompat.Builder mNotificationBuilder;
    private static RemoteViews notRemoteView;
    private static RemoteViews bigNotRemoteView;
    private static Context mContext;
    private static BackService service;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        String url = intent.getStringExtra("url");
        boolean bool = intent.getBooleanExtra("bool", false);
        MusicService.getService().play(url);
        MusicService.getService().setLoop(bool);
        return null;
    }

    public static BackService getService() {
        if (service == null)
            service = new BackService();
        return service;
    }

    /**
     * 进程是否存活
     * @return
     */
    public boolean isRunningProcess() {
        ActivityManager manager =(ActivityManager)getSystemService(Context.ACTIVITY_SERVICE);
        String processName = getPackageName();
        if(manager==null)
            return false;
        List<ActivityManager.RunningAppProcessInfo> runnings = manager.getRunningAppProcesses();
        if (runnings != null) {
            for (ActivityManager.RunningAppProcessInfo info : runnings) {
                if(TextUtils.equals(info.processName,processName)){
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if(intent!=null) {
            String url = intent.getStringExtra("url");
            boolean bool = intent.getBooleanExtra("bool", false);
            MusicService.getService().play(url);
            MusicService.getService().setLoop(bool);
            initNotify();
        }
        else {
            mContext = this;
            resetNotification();
            close();
        }
        return super.onStartCommand(intent, flags, startId);
    }


    @Override
    public void onCreate() {
        super.onCreate();
        boolean isRun = isRunningProcess();
        if(isRun==false) {
            mContext = this;
            resetNotification();
            close();
        }
    }


    private class ButtonBroadcastReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (action.equals(ACTION_SERVICE)) {
                int buttonId = intent.getIntExtra(INTENT_BUTTONID_TAG, 0);
                switch (buttonId) {
                    case ACTION_NEXT:
                        MusicService.getService().pause();
                        MusicService.getService().nextSong();
                        break;
                    case ACTION_PREV:
                        MusicService.getService().pause();
                        Log.i("ACTION_NEXT","上一曲");
                        MusicService.getService().lastSong();
                        break;
                    case ACTION_CLOSE:
                        MusicService.getService().stop();
                        close();
                        Log.i("ACTION_SERVICE","关闭");
                        break;
                    case ACTION_PLAY_PAUSE:
                        playOrPause();
                        Log.i("ACTION_SERVICE","开始暂停/播放");
                        break;
                    default:
                        break;
                }//通过传递过来的ID判断按钮点击属性或者通过getResultCode()获得相应点击事件
            }
        }
    }
    public void playOrPause() {
        Boolean flag = MusicService.getService().isPlay();
        if(flag){
            MusicService.getService().pause();
        } else {
            MusicService.getService().start();
        }
    }

    public void resetNotification() {
        if(mNotificationBuilder==null && mNotificationManager==null) {
            mNotificationBuilder = createNotification();
            mNotificationManager = (NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
        }
    }
    public static void setSong(String songNameStr, String singerNameStr, String notificationCoverStr) {
        songName = songNameStr;
        singerName = singerNameStr;
        notificationCover = notificationCoverStr;
    }

    private NotificationCompat.Builder createNotification() {
        String packageName = mContext.getPackageName();
        notRemoteView = new RemoteViews(packageName, R.layout.player_notification);
        bigNotRemoteView = new RemoteViews(packageName, R.layout.player_notification_expanded);
        setupNotification(notRemoteView);
        setupNotification(bigNotRemoteView);
        Intent intent = new Intent(mContext, audioEntry.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(mContext, 1, intent, PendingIntent.FLAG_ONE_SHOT);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(mContext, mContext.getString(R.string.notification_channel_id))
                .setOngoing(true)
                .setAutoCancel(true)
                .setSmallIcon(R.drawable.ic_icon)
                .setVisibility(NotificationCompat.VISIBILITY_PUBLIC)
                .setCustomContentView(notRemoteView)
                .setCustomBigContentView(bigNotRemoteView)
                .setPriority(NotificationCompat.PRIORITY_MAX)
                .setContentIntent(pendingIntent);
        return builder;
    }

    private void setupNotification(RemoteViews remoteViews) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            if (remoteViews!=null){
                //注册广播
                if (receiver == null) {
                    receiver = new ButtonBroadcastReceiver();
                    IntentFilter intentFilter = new IntentFilter();
                    intentFilter.addAction(ACTION_SERVICE);
                    mContext.registerReceiver(receiver, intentFilter);
                }

                //设置点击的事件
                Intent buttonIntent = new Intent(ACTION_SERVICE);

                /* 播放/暂停  按钮 */
                buttonIntent.putExtra(INTENT_BUTTONID_TAG, ACTION_PLAY_PAUSE);
                PendingIntent intent_paly = PendingIntent.getBroadcast(mContext, ACTION_PLAY_PAUSE, buttonIntent, PendingIntent.FLAG_UPDATE_CURRENT);
                remoteViews.setOnClickPendingIntent(R.id.notificationPlayPause, intent_paly);

                /* 下一曲  按钮 */
                buttonIntent.putExtra(INTENT_BUTTONID_TAG, ACTION_NEXT);
                PendingIntent intent_next = PendingIntent.getBroadcast(mContext, ACTION_NEXT, buttonIntent, PendingIntent.FLAG_UPDATE_CURRENT);
                remoteViews.setOnClickPendingIntent(R.id.notificationFForward, intent_next);

                /* 上一曲  按钮 */
                buttonIntent.putExtra(INTENT_BUTTONID_TAG, ACTION_PREV);
                PendingIntent prev_next = PendingIntent.getBroadcast(mContext, ACTION_PREV, buttonIntent, PendingIntent.FLAG_UPDATE_CURRENT);
                remoteViews.setOnClickPendingIntent(R.id.notificationFRewind, prev_next);

                /* 关闭  按钮 */
                buttonIntent.putExtra(INTENT_BUTTONID_TAG, ACTION_CLOSE);
                PendingIntent intent_close = PendingIntent.getBroadcast(mContext, ACTION_CLOSE, buttonIntent, PendingIntent.FLAG_UPDATE_CURRENT);
                remoteViews.setOnClickPendingIntent(R.id.notificationStop, intent_close);
            }

        }

    }
    private static Bitmap bitmap;
    public static void returnBitMap(final String url, final RemoteViews remoteViews) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                URL imageurl = null;

                try {
                    imageurl = new URL(url);
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }
                try {
                    HttpURLConnection conn = (HttpURLConnection)imageurl.openConnection();
                    conn.setDoInput(true);
                    conn.connect();
                    InputStream is = conn.getInputStream();
                    bitmap = BitmapFactory.decodeStream(is);
                    is.close();
                    //阻塞main线程，休眠1秒钟
                    Thread.sleep(1000);
                    remoteViews.setImageViewBitmap(R.id.notificationCover, bitmap);
                    mNotificationManager.notify(NOTIFICATION_ID, mNotificationBuilder.build());
                } catch (IOException | InterruptedException e) {
                    bitmap = null;
                    e.printStackTrace();
                }
            }
        }).start();

//        return bitmap;
    }

    public void updatePlayerState(int drawableId) {
        if(mNotificationManager==null) {
            return;
        }
        if (notRemoteView != null){
            notRemoteView.setImageViewResource(R.id.notificationPlayPause, drawableId);
        }
        if (bigNotRemoteView != null) {
            bigNotRemoteView.setImageViewResource(R.id.notificationPlayPause, drawableId);
        }
        mNotificationManager.notify(NOTIFICATION_ID, mNotificationBuilder.build());
    }
    public static void updatePlayerImage() {
        if (notRemoteView != null){
            notRemoteView.setImageViewResource(R.id.notificationCover, R.drawable.default_cover);
            returnBitMap(notificationCover,notRemoteView);
        }
        if (bigNotRemoteView != null){
            bigNotRemoteView.setImageViewResource(R.id.notificationCover, R.drawable.default_cover);
            returnBitMap(notificationCover,bigNotRemoteView);
        }
        mNotificationManager.notify(NOTIFICATION_ID, mNotificationBuilder.build());
    }

    public static void updateNotification(int drawableId) {
        if (mNotificationBuilder == null) return;
        if (drawableId != -1) {
            if (notRemoteView != null){
                notRemoteView.setImageViewResource(R.id.notificationPlayPause, drawableId);
            }
            if (bigNotRemoteView != null) {
                bigNotRemoteView.setImageViewResource(R.id.notificationPlayPause, drawableId);
            }
        }
        if (notRemoteView != null){
            notRemoteView.setTextViewText(R.id.notificationSongName, songName);
            notRemoteView.setTextViewText(R.id.notificationArtist, singerName);
        }
        if (bigNotRemoteView != null){
            bigNotRemoteView.setTextViewText(R.id.notificationSongName, songName);
            bigNotRemoteView.setTextViewText(R.id.notificationArtist, singerName);
        }
        mNotificationManager.notify(NOTIFICATION_ID, mNotificationBuilder.build());
        updatePlayerImage();

    }

    public void initNotify() {
        boolean isRun = isRunningProcess();
        if(isRun!=false) {
            mContext = this;
            resetNotification();
            updateNotification(R.drawable.ic_pause);
        }
    }

    public void close() {
        if(mNotificationManager!=null) {
            mNotificationManager.cancel(NOTIFICATION_ID);
            mNotificationManager = null;
            mNotificationBuilder = null;
        }
        if(receiver !=null) {
            mContext.unregisterReceiver(receiver);
            receiver = null;
        }
    }

}
