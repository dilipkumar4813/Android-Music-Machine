package iamdilipkumar.com.musicmachine;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    public static final String SONG_NAME = "songname";

    @OnClick(R.id.btn_download)
    void downloadSong() {
        Toast.makeText(this, "Downloading", Toast.LENGTH_SHORT).show();

        /*DownloadTask thread = new DownloadTask();
        thread.setName("DownloadSongs");
        thread.start();*/

        for (String song : Playlists.songs) {
            /*Message message = Message.obtain();
            message.obj = song;
            thread.mHandler.sendMessage(message);*/

            Intent intent = new Intent(MainActivity.this, DownloadService.class);
            intent.putExtra(SONG_NAME, song);
            startService(intent);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
