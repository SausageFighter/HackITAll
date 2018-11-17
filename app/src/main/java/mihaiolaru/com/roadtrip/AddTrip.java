package mihaiolaru.com.roadtrip;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddTrip extends AppCompatActivity {

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_trip);

        Button button = (Button)findViewById(R.id.button);
        View settings = findViewById(R.id.settings);

        final EditText start_place = ((EditText)findViewById(R.id.editText));
        final EditText end_place = ((EditText)findViewById(R.id.editText3));

        Intent intentGet = getIntent();
        final String start_coords1 = intentGet.getStringExtra("start_coords");
        if(start_coords1 != null) {
            Float tmp_lat = Float.parseFloat(start_coords1.split(":")[0]);
            Float tmp_lng = Float.parseFloat(start_coords1.split(":")[1]);
            @SuppressLint("DefaultLocale") String formattedString1 = String.format("%.02f", tmp_lat);
            @SuppressLint("DefaultLocale") String formattedString2 = String.format("%.02f", tmp_lng);
            start_place.setText(formattedString1+":"+formattedString2);
        }
        final String end_coords1 = intentGet.getStringExtra("end_coords");
        if(end_coords1 != null) {
            Float tmp_lat = Float.parseFloat(end_coords1.split(":")[0]);
            Float tmp_lng = Float.parseFloat(end_coords1.split(":")[1]);
            @SuppressLint("DefaultLocale") String formattedString1 = String.format("%.02f", tmp_lat);
            @SuppressLint("DefaultLocale") String formattedString2 = String.format("%.02f", tmp_lng);
            end_place.setText(formattedString1+":"+formattedString2);
        }

        start_place.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AddTrip.this,chooseLocation.class);
                intent.putExtra("from","start");
                if(start_coords1 != null)
                    intent.putExtra("start_coords",start_coords1);
                if(end_coords1 != null)
                    intent.putExtra("end_coords",end_coords1);
                startActivity(intent);
            }
        });

        end_place.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AddTrip.this,chooseLocation.class);
                intent.putExtra("from","end");
                if(start_coords1 != null)
                    intent.putExtra("start_coords",start_coords1);
                if(end_coords1 != null)
                intent.putExtra("end_coords",end_coords1);
                startActivity(intent);
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = getIntent();
                String start_coords = myIntent.getStringExtra("start_coords");
                if(start_coords == null)
                    return;
                String dest_coords = myIntent.getStringExtra("end_coords");
                if(dest_coords == null)
                    return;
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
