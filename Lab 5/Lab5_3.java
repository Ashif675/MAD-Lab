import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private ImageView ironManImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ironManImageView = findViewById(R.id.ironManImageView);

        // Example animations
        applyAlphaAnimation();
        applyScaleAnimation();
        applyTranslationAnimation();
    }

    private void applyAlphaAnimation() {
        Animation alphaAnimation = AnimationUtils.loadAnimation(this, R.anim.alpha_animation);
        ironManImageView.startAnimation(alphaAnimation);
    }

    private void applyScaleAnimation() {
        Animation scaleAnimation = AnimationUtils.loadAnimation(this, R.anim.scale_animation);
        ironManImageView.startAnimation(scaleAnimation);
    }

    private void applyTranslationAnimation() {
        Animation translationAnimation = AnimationUtils.loadAnimation(this, R.anim.translation_animation);
        ironManImageView.startAnimation(translationAnimation);
    }
}
