package eeui.android.audio.event;
import android.util.Log;

import java.net.HttpURLConnection;
import java.net.URL;


public class tool {
    public static synchronized boolean isConnect(String urlStr) {
      int counts = 0;
      Boolean iscanUse = false;
      if (urlStr == null || urlStr.length() <= 0) {
         return false;
      }
      while (counts < 5) {
      try {
         URL url = new URL(urlStr);
         HttpURLConnection con = (HttpURLConnection) url.openConnection();
         int state = con.getResponseCode();
         if (state == 200) {
            iscanUse = true;
         }
         break;
      } catch (Exception ex) {
          counts++;
          iscanUse = false;
          urlStr = null;
          continue;
         }
      }
      return iscanUse;
    }
}
