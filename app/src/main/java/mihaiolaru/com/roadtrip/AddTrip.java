package mihaiolaru.com.roadtrip;

import android.content.Intent;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

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
                String start_coords = ((EditText)findViewById(R.id.editText)).getText().toString();
                String dest_coords = ((EditText)findViewById(R.id.editText3)).getText().toString();
                String start_date = ((EditText)findViewById(R.id.editText2)).getText().toString();
                String end_date = ((EditText)findViewById(R.id.editText4)).getText().toString();

                Intent mapScreen = new Intent(AddTrip.this, ShowTrip.class);
                mapScreen.putExtra("start_coords", start_coords);
                mapScreen.putExtra("dest_coords", dest_coords);
                mapScreen.putExtra("start_date", start_date);
                mapScreen.putExtra("end_date", end_date);

                startActivity(mapScreen);
            }
        });

        settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent settingsActivity = new Intent(AddTrip.this, SettingsActivity.class);
                startActivity(settingsActivity);
            }
        });
    }
}
