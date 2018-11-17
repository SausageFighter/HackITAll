package mihaiolaru.com.roadtrip;

import android.content.Intent;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class AddTrip extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_trip);

        Button button = (Button)findViewById(R.id.button);
        View settings = findViewById(R.id.settings);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mapScreen = new Intent(AddTrip.this, ShowTrip.class);
                startActivity(mapScreen);
            }
        });

        settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             //   Intent settingsActivity = new Intent(AddTrip.this, SettingsActivity.class);
               // startActivity(settingsActivity);
            }
        });
    }
}
