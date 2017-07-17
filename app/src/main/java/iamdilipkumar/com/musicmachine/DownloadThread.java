package iamdilipkumar.com.musicmachine;

import android.os.Looper;
import android.util.Log;

/**
 * Created on 16/07/17.
 *
 * @author dilipkumar4813
 * @version 1.0
 */

public class DownloadThread extends Thread {

    public DownloadHandler mHandler;

    @Override
    public void run() {
        Looper.prepare();
        mHandler = new DownloadHandler();
        Looper.loop();
    }
}
