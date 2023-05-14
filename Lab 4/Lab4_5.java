import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    private ImageView imageView;
    private boolean isTrumpImageDisplayed = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageView = findViewById(R.id.imageView);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isTrumpImageDisplayed) {
                    imageView.setImageResource(R.drawable.pm_image);
                    isTrumpImageDisplayed = false;
                } else {
                    imageView.setImageResource(R.drawable.trump_image);
                    isTrumpImageDisplayed = true;
                }
            }
        });
    }
}
