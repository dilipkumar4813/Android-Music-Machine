package iamdilipkumar.com.musicmachine;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import iamdilipkumar.com.musicmachine.services.ActivityHandler;
import iamdilipkumar.com.musicmachine.services.DownloadIntentService;
import iamdilipkumar.com.musicmachine.services.PlayerService;
import iamdilipkumar.com.musicmachine.utils.Playlists;

public class MainActivity extends AppCompatActivity {

    public static final String SONG_NAME = "songname";
    private PlayerService mPlayerService;
    private boolean mBound = false;
    private Messenger mServiceMessenger;
    private Messenger mActivityMessenger = new Messenger(new ActivityHandler(this));

    @BindView(R.id.btn_play)
    Button btnPlay;


    private ServiceConnection mServiceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            mBound = true;
            mServiceMessenger = new Messenger(iBinder);
            Message message = Message.obtain();
            message.arg1 = 2;
            message.arg2 = 1;
            message.replyTo = mActivityMessenger;
            try {
                mServiceMessenger.send(message);
            } catch (RemoteException e) {
                e.printStackTrace();
            }

            /*PlayerService.LocalBinder localBinder = (PlayerService.LocalBinder) iBinder;
            mPlayerService = localBinder.getService();

            if (mPlayerService.isPlaying()) {
                btnPlay.setText("Pause");
            }*/
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            mBound = false;
        }
    };

    @OnClick(R.id.btn_download)
    void downloadSong() {
        Toast.makeText(this, "Downloading", Toast.LENGTH_SHORT).show();

        for (String song : Playlists.songs) {
            Intent intent = new Intent(MainActivity.this, DownloadIntentService.class);
            intent.putExtra(SONG_NAME, song);
            startService(intent);
        }
    }

    @OnClick(R.id.btn_play)
    void mediaPlaying() {
        /*PlayerService playerService = new PlayerService();
        bindService()*/
        if (mBound) {
            /*if (mPlayerService.isPlaying()) {
                mPlayerService.pause();
                btnPlay.setText("Play");
            } else {*/
            Intent intent = new Intent(MainActivity.this, PlayerService.class);
            startService(intent);
              /*  mPlayerService.play();
                btnPlay.setText("Pause");
            }*/

            Message message = Message.obtain();
            message.arg1 = 2;
            message.replyTo = mActivityMessenger;
            try {
                mServiceMessenger.send(message);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);
    }

    public void changePlayButtonText(String text) {
        btnPlay.setText(text);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Intent intent = new Intent(MainActivity.this, PlayerService.class);
        bindService(intent, mServiceConnection, Context.BIND_AUTO_CREATE);
    }

    @Override
    protected void onStop() {
        super.onStop();

        if (mBound) {
            unbindService(mServiceConnection);
            mBound = false;
        }
    }
}
