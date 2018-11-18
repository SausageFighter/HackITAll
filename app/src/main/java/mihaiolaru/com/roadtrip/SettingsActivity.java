package mihaiolaru.com.roadtrip;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.text.DecimalFormat;

public class SettingsActivity extends AppCompatActivity {

    int consumption;
    int capacity;

    EditText consumptionInput;
    EditText capacityInput;
    TextView range;
    Button inapoiButton;
    SharedPreferences sharedPreferences;
    Button addressButton;
    TextView addressView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

//        TextView textView = (TextView)findViewById(R.id.editText8);

        consumptionInput = (EditText)findViewById(R.id.consum);
        capacityInput = (EditText)findViewById(R.id.baterie);
        addressView = (TextView)findViewById(R.id.textView);

        sharedPreferences = getSharedPreferences("pref", Context.MODE_PRIVATE);
        String battery = sharedPreferences.getString("battery","");
        String cons = sharedPreferences.getString("consumption","");
        String crd = sharedPreferences.getString("crdAddress","");

        capacityInput.setText(battery);
        consumptionInput.setText(cons);
        addressView.setText(crd);


        try {
            if (Double.parseDouble(capacityInput.getText().toString()) == 0)
                return;
            if (!consumptionInput.getText().toString().isEmpty()) {
                Float result = Float.parseFloat(consumptionInput.getText().toString()) / Float.parseFloat(capacityInput.getText().toString());
                range = (TextView) findViewById(R.id.range);
                String formattedString = String.format("%.02f", result);
                range.setText(formattedString+ " km");
            }
        }catch (Exception e){e.printStackTrace();}

        final SharedPreferences.Editor editor = sharedPreferences.edit();

        consumptionInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                try {
                    if (Double.parseDouble(capacityInput.getText().toString()) == 0)
                        return;
                    if (!capacityInput.getText().toString().isEmpty()) {

                        editor.putString("battery",capacityInput.getText().toString());
                        editor.putString("consumption",consumptionInput.getText().toString());
                        editor.commit();

//                        consumptionInput.setText(sharedPreferences.getString("consumption","").toString());


                        Float result = Float.parseFloat(sharedPreferences.getString("battery","")) / Float.parseFloat(sharedPreferences.getString("consumption",""));
                        range = (TextView) findViewById(R.id.range);
                        String formattedString = String.format("%.02f", result);
                        range.setText(formattedString+ " km");
                    }
                }catch (Exception e){e.printStackTrace();}
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

//        consumptionInput.getText;

        capacityInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                try {
                    if (Double.parseDouble(capacityInput.getText().toString()) == 0)
                        return;
                    if (!consumptionInput.getText().toString().isEmpty()) {

                        editor.putString("battery",capacityInput.getText().toString());
                        editor.putString("consumption",consumptionInput.getText().toString());
                        editor.commit();

//                        capacityInput.setText(sharedPreferences.getString("battery",""));

                        Float result = Float.parseFloat(sharedPreferences.getString("battery","")) / Float.parseFloat(sharedPreferences.getString("consumption",""));
                        //Float result = Float.parseFloat(consumptionInput.getText().toString()) / Float.parseFloat(capacityInput.getText().toString());
                        range = (TextView) findViewById(R.id.range);
                        String formattedString = String.format("%.02f", result);
                        range.setText(formattedString+ " km");
                    }
                }catch (Exception e){e.printStackTrace();}
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

//        Double result =  Double.parseDouble(consumptionInput.getText().toString()) / Double.parseDouble(consumptionInput.getText().toString());
//        range = (TextView)findViewById(R.id.range);
//        range.setText(result.toString());
        inapoiButton = (Button)findViewById(R.id.inapoi);
        inapoiButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                consumptionInput.setText(sharedPreferences.getString("consumption",""));
                capacityInput.setText(sharedPreferences.getString("battery",""));
                Intent settingsScreen = new Intent(SettingsActivity.this, AddTrip.class);
                startActivity(settingsScreen);
            }
        });
        Intent _intent = getIntent();
        TextView text = (TextView)findViewById(R.id.textView);

        if(_intent.getStringExtra("homeAddress") != null) {
//            String[] crdArray = _intent.getStringExtra("homeAddress").split(":");
            //crd[0].substring(0,4);
//            text.setText(_intent.getStringExtra("homeAddress"));
//            String newCrd = crdArray[0].substring(0,4) + ":" + crdArray[1].substring(0,4);
            editor.putString("crdAddress",addressView.getText().toString());
            editor.commit();
//            text.setText(newCrd);
            //text.setText(_intent.getStringExtra("homeAddress"));
        }

        addressButton = (Button)findViewById(R.id.AddressHome);
        addressButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent addressMapScreen = new Intent(SettingsActivity.this,chooseLocation.class);
                addressMapScreen.putExtra("from","home");
                startActivity(addressMapScreen);
            }
        });
    }

}
