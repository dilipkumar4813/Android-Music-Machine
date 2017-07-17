package iamdilipkumar.com.musicmachine.services;

import android.os.Handler;
import android.os.Message;
import android.os.RemoteException;

/**
 * Created on 17/07/17.
 *
 * @author dilipkumar4813
 * @version 1.0
 */

public class PlayerHandler extends Handler {

    private PlayerSeparateService mPlayerSeparateService;

    public PlayerHandler(PlayerSeparateService playerSeparateService) {
        mPlayerSeparateService = playerSeparateService;
    }

    @Override
    public void handleMessage(Message msg) {
        switch (msg.arg1) {
            case 0:
                // Play
                mPlayerSeparateService.play();
                break;
            case 1:
                // Pause
                mPlayerSeparateService.pause();
                break;
            case 2:
                // isPlaying
                int isPlaying = mPlayerSeparateService.isPlaying() ? 1 : 0;
                Message message = Message.obtain();
                message.arg1 = isPlaying;

                if (msg.arg2 == 1) {
                    message.arg2 = 1;
                }
                message.replyTo = mPlayerSeparateService.mMessenger;
                try {
                    message.replyTo.send(message);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
                break;
        }
    }
}
