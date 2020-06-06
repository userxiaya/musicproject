package eeui.android.audio.module;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.res.AssetFileDescriptor;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.util.Log;

import com.alibaba.fastjson.JSONObject;
import com.taobao.weex.annotation.JSMethod;
import com.taobao.weex.bridge.JSCallback;
import com.taobao.weex.common.WXModule;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import app.eeui.framework.extend.module.eeuiJson;
import app.eeui.framework.extend.module.eeuiPage;
import app.eeui.framework.ui.eeui;
import eeui.android.audio.R;
import eeui.android.audio.event.AudioEvent;
import eeui.android.audio.service.BackService;
import eeui.android.audio.service.MusicService;

public class WeexaudioModule extends WXModule {

    private static JSCallback callback;

    private boolean isBool = false;

    public WeexaudioModule() {
        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this);
        }
    }

    @JSMethod
    public void play(String playerObject) {
        JSONObject json = eeuiJson.parseObject(playerObject);
        String url = eeuiJson.getString(json,"url");
        url = eeuiPage.rewriteUrl(mWXSDKInstance, url);
        String songName = eeuiJson.getString(json,"songName");
        String singerName = eeuiJson.getString(json,"singerName");
        String image = eeuiJson.getString(json,"image");
        // split

        songName = songName.length() > 0 ? songName: "empty";
        singerName = singerName.length() > 0 ? singerName: "empty";
        image = image.length() > 0 ? image: "";
        // split

        BackService.setSong(songName,singerName,image);
        BackService backService = BackService.getService();
        if(backService!=null) {
            BackService.updateNotification(R.drawable.ic_pause);
        }
        if (MusicService.getService().playNext(url)) {
            return;
        }
        Intent in = new Intent(mWXSDKInstance.getContext(), BackService.class);
        in.putExtra("url", url);
        in.putExtra("loop", isBool);
        mWXSDKInstance.getContext().startService(in);
    }

    @JSMethod
    public void pause() {
        MusicService.getService().pause();
    }

    @JSMethod
    public void stop() {
        MusicService.getService().stop();
    }

    @JSMethod
    public void seek(int msec) {
        MusicService.getService().seek(msec);
    }

    @JSMethod(uiThread = false)
    public boolean isPlay() {
        return MusicService.getService().isPlay();
    }

    @JSMethod
    public void volume(int volume) {
        MusicService.getService().volume(volume);
    }

    @JSMethod
    public void loop(boolean loop) {
        isBool = loop;
        MusicService.getService().setLoop(isBool);
    }

    @JSMethod
    public void setCallback(JSCallback call) {
        callback = call;
    }

    @JSMethod
    public void getDuration(String url, JSCallback call) {
        new PlayAsyncTask().execute(url, call);
    }

    @SuppressLint("StaticFieldLeak")
    private class PlayAsyncTask extends AsyncTask<Object, Integer, Object> {
        @Override
        protected Object doInBackground(Object... objects) {
            String url = eeuiPage.rewriteUrl(mWXSDKInstance, String.valueOf(objects[0]));
            JSCallback call = (JSCallback) objects[1];
            MediaPlayer player = new MediaPlayer();
            try {
                if (url.startsWith("file://assets/")) {
                    AssetFileDescriptor assetFile = eeui.getApplication().getAssets().openFd(url.substring(14));
                    player.setDataSource(assetFile.getFileDescriptor(), assetFile.getStartOffset(), assetFile.getLength());
                }else{
                    player.setDataSource(url);
                }
                player.prepare();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
            float duration = player.getDuration();
            player.release();
            if (call != null) {
                Map<String, Object> data = new HashMap<>();
                data.put("duration", duration);
                data.put("url", url);
                call.invoke(data);
            }
            return null;
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(AudioEvent e) {
        if (callback == null) {
            return;
        }
        Map<String, Object> data = new HashMap<>();
        data.put("url", e.url == null ? "" : e.url);
        data.put("current", e.current);
        data.put("duration", e.total);
        data.put("percent", (e.total == 0 ? 0 : e.current / (float) e.total));
        switch (e.state) {
            case AudioEvent.STATE_STARTPLAY:
                data.put("status", "start");
                break;
            case AudioEvent.STATE_PLAY:
                data.put("status", "play");
                break;
            case AudioEvent.STATE_COMPELETE:
                data.put("status", "compelete");
                break;
            case AudioEvent.STATE_ERROR:
                data.put("status", "error");
                break;
            case AudioEvent.STATE_SEEK_COMPELETE:
                data.put("status", "seek");
                break;
            case AudioEvent.STATE_BufferingUpdate:
                data.put("status", "buffering");
                break;
            case AudioEvent.STATE_NEXT_SONG:
                data.put("status", "next_song");
                break;
            case AudioEvent.STATE_LAST_SONG:
                data.put("status", "last_song");
                break;
            case AudioEvent.STATE_TIMEOUT:
                data.put("status", "timeOut");
                break;
            default:
                return;
        }
        callback.invokeAndKeepAlive(data);
    }
}
