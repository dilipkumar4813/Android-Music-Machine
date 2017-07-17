package iamdilipkumar.com.musicmachine.services;

import android.os.Handler;
import android.os.Message;
import android.os.RemoteException;

import iamdilipkumar.com.musicmachine.MainActivity;

/**
 * Created on 17/07/17.
 *
 * @author dilipkumar4813
 * @version 1.0
 */

public class ActivityHandler extends Handler {

    private MainActivity mMainActivity;

    public ActivityHandler(MainActivity mainActivity) {
        mMainActivity = mainActivity;
    }

    @Override
    public void handleMessage(Message msg) {
        Message message = Message.obtain();
        switch (msg.arg1) {
            case 0:
                message.arg1 = 0;

                mMainActivity.changePlayButtonText("Pause");
                if (message.arg2 != 1) {
                    try {
                        msg.replyTo.send(message);
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                }
                break;
            case 1:
                // Pause the music
                // Change to play button
                message.arg1 = 1;

                mMainActivity.changePlayButtonText("Play");

                if (msg.arg2 != 1) {
                    try {
                        msg.replyTo.send(message);
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }

                }
                break;
        }
    }
}
