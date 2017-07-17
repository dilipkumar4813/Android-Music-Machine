package iamdilipkumar.com.musicmachine;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.Message;
import android.support.annotation.Nullable;

/**
 * Created on 16/07/17.
 *
 * @author dilipkumar4813
 * @version 1.0
 */

public class DownloadService extends Service {

    private static final String TAG = "DownloadService";
    private DownloadHandler mHandler;

    @Override
    public void onCreate() {
        DownloadThread thread = new DownloadThread();
        thread.setName("DownloadSongs");
        thread.start();

        while (mHandler == null) {
            mHandler = thread.mHandler;
            mHandler.setService(this);
        }
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        String song = intent.getStringExtra(MainActivity.SONG_NAME);

        Message message = Message.obtain();
        message.obj = song;
        message.arg1 = startId;
        mHandler.sendMessage(message);

        return Service.START_REDELIVER_INTENT;
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
