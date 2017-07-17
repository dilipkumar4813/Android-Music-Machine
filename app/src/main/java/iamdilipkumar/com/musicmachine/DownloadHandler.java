package iamdilipkumar.com.musicmachine;

import android.os.Handler;
import android.os.Message;
import android.util.Log;

/**
 * Created on 16/07/17.
 *
 * @author dilipkumar4813
 * @version 1.0
 */

public class DownloadHandler extends Handler {

    private static final String TAG = "DownloadTask";
    private DownloadService service;

    @Override
    public void handleMessage(Message msg) {
        downloadSong(msg.obj.toString());
        service.stopSelf(msg.arg1);
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

    public void setService(DownloadService service) {
        this.service = service;
    }
}
