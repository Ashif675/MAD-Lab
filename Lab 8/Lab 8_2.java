import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "CountdownTimerApp";
    private static final long COUNTDOWN_DURATION = 60000; // Countdown duration in milliseconds
    private static final long INTERVAL = 1000; // Interval in milliseconds for onTick() callback

    private CountDownTimer countDownTimer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        countDownTimer = new CountDownTimer(COUNTDOWN_DURATION, INTERVAL) {
            @Override
            public void onTick(long millisUntilFinished) {
                Log.d(TAG, "Time remaining: " + millisUntilFinished / 1000 + " seconds");
            }

            @Override
            public void onFinish() {
                Log.d(TAG, "Countdown finished");
            }
        };

        countDownTimer.start();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        countDownTimer.cancel();
    }
}
