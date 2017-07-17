package iamdilipkumar.com.musicmachine;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import butterknife.OnClick;
import iamdilipkumar.com.musicmachine.services.DownloadIntentService;
import iamdilipkumar.com.musicmachine.utils.Playlists;

public class MainActivity extends AppCompatActivity {

    public static final String SONG_NAME = "songname";
    private boolean mBound = false;

    private ServiceConnection mServiceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            mBound = true;
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
        //if()
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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
