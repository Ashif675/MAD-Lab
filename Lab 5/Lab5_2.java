import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    private ImageView modiImageView;
    private ImageView rahulImageView;
    private Handler handler;
    private Runnable runnable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        modiImageView = findViewById(R.id.modiImageView);
        rahulImageView = findViewById(R.id.rahulImageView);

        modiImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                modiImageView.setVisibility(View.INVISIBLE);
                rahulImageView.setVisibility(View.VISIBLE);
                handler = new Handler();
                runnable = new Runnable() {
                    @Override
                    public void run() {
                        rahulImageView.setVisibility(View.INVISIBLE);
                        modiImageView.setVisibility(View.VISIBLE);
                    }
                };
                handler.postDelayed(runnable, 2000);
            }
        });

        rahulImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rahulImageView.setVisibility(View.INVISIBLE);
                modiImageView.setVisibility(View.VISIBLE);
                handler = new Handler();
                runnable = new Runnable() {
                    @Override
                    public void run() {
                        modiImageView.setVisibility(View.INVISIBLE);
                        rahulImageView.setVisibility(View.VISIBLE);
                    }
                };
                handler.postDelayed(runnable, 2000);
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (handler != null && runnable != null) {
            handler.removeCallbacks(runnable);
        }
    }
}
