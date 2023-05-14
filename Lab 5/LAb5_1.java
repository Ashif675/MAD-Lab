import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText editText;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = findViewById(R.id.editText);
        button = findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String amountString = editText.getText().toString();
                if (!amountString.isEmpty()) {
                    double amountUSD = Double.parseDouble(amountString);
                    double conversionRate = 73.5; // Use the current conversion rate here
                    double amountINR = amountUSD * conversionRate;
                    String amountINRString = String.format("%.2f", amountINR);
                    Toast.makeText(MainActivity.this, "Amount in INR: " + amountINRString, Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, "Please enter an amount in USD", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
