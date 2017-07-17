package iamdilipkumar.com.musicmachine.services;

import android.app.IntentService;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.util.Log;

import iamdilipkumar.com.musicmachine.MainActivity;

/**
 * Created on 17/07/17.
 *
 * @author dilipkumar4813
 * @version 1.0
 */

public class DownloadIntentService extends IntentService {
    private static final String TAG = "DownloadIntentService";

    public DownloadIntentService() {
        super("DownloadIntentService");
        setIntentRedelivery(true);
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        String song = intent.getStringExtra(MainActivity.SONG_NAME);
        downloadSong(song);
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
}
