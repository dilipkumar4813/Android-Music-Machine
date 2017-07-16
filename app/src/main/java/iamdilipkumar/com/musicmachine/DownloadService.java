package iamdilipkumar.com.musicmachine;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.IntDef;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Created on 16/07/17.
 *
 * @author dilipkumar4813
 * @version 1.0
 */

public class DownloadService extends Service {

    private static final String TAG = "DownloadService";

    @Override
    public int onStartCommand(Intent intent,int flags, int startId) {
        String song = intent.getStringExtra(MainActivity.SONG_NAME);
        downloadSong(song);
        return Service.START_REDELIVER_INTENT;
    }

    private void downloadSong(String song) {
        long endTime = System.currentTimeMillis() + 10 * 1000;

        while (System.currentTimeMillis() < endTime) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        Log.d(TAG, song + " downloaded");
    }


    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
