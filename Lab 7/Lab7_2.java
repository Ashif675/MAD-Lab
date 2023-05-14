import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.VideoView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private VideoView videoView;
    private Button playButton;
    private Button pauseButton;
    private Button stopButton;

    private MediaController mediaController;
    private MediaPlayer mediaPlayer;
    private boolean isPlaying = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        videoView = findViewById(R.id.videoView);
        playButton = findViewById(R.id.playButton);
        pauseButton = findViewById(R.id.pauseButton);
        stopButton = findViewById(R.id.stopButton);

        playButton.setOnClickListener(this);
        pauseButton.setOnClickListener(this);
        stopButton.setOnClickListener(this);

        mediaController = new MediaController(this);
        mediaController.setAnchorView(videoView);
        videoView.setMediaController(mediaController);

        String videoPath = "android.resource://" + getPackageName() + "/" + R.raw.your_video_file; // Replace "your_video_file" with the actual video file name (without extension) placed in the "res/raw" folder
        Uri videoUri = Uri.parse(videoPath);
        videoView.setVideoURI(videoUri);

        mediaPlayer = new MediaPlayer();
        mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                // Adjust the video view size to match the video's aspect ratio
                videoView.seekTo(1);
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.playButton:
                if (!isPlaying) {
                    videoView.start();
                    mediaPlayer.start();
                    isPlaying = true;
                }
                break;
            case R.id.pauseButton:
                if (isPlaying) {
                    videoView.pause();
                    mediaPlayer.pause();
                    isPlaying = false;
                }
                break;
            case R.id.stopButton:
                if (isPlaying) {
                    videoView.stopPlayback();
                    mediaPlayer.stop();
                    isPlaying = false;
                }
                break;
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (isPlaying) {
            videoView.pause();
            mediaPlayer.pause();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (isPlaying) {
            videoView.start();
            mediaPlayer.start();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mediaPlayer.release();
    }
}
