package eeui.android.audio.event;

import android.util.Log;

import androidx.arch.core.util.Function;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import eeui.android.audio.service.MusicService;

public class MyExecutor {

private static ExecutorService executor = Executors.newCachedThreadPool() ;

public static void fun() throws Exception {

executor.submit(new Runnable(){
    public void run() {
        try {
            Thread.sleep(10000);
//            MusicService.timeOut(true);
        } catch(Exception e) {
          throw new RuntimeException("报错啦！！");
        }
      }
    });

}
public static void timeoutReset() throws Exception {

        executor.submit(new Runnable(){
            public void run() {
                try {
                    Thread.sleep(1000);
//                    MusicService.resetTimeout();
                } catch(Exception e) {
                    throw new RuntimeException("报错啦！！");
                }
            }
        });

    }

}
