package mihaiolaru.com.roadtrip;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class SettingsActivity extends AppCompatActivity {

    int consumption;
    int capacity;

    EditText consumptionInput;
    EditText capacityInput;
    TextView range;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

//        TextView textView = (TextView)findViewById(R.id.editText8);

        consumptionInput = (EditText)findViewById(R.id.consum);
        capacityInput = (EditText)findViewById(R.id.baterie);

        consumptionInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(!capacityInput.getText().toString().isEmpty()){
                    Double result =  Double.parseDouble(consumptionInput.getText().toString()) / Double.parseDouble(consumptionInput.getText().toString());
                    range = (TextView)findViewById(R.id.range);
                    range.setText(result.toString());
                }
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

                if(!consumptionInput.getText().toString().isEmpty()){
                    Double result =  Double.parseDouble(consumptionInput.getText().toString()) / Double.parseDouble(consumptionInput.getText().toString());
                    range = (TextView)findViewById(R.id.range);
                    range.setText(result.toString());
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

//        Double result =  Double.parseDouble(consumptionInput.getText().toString()) / Double.parseDouble(consumptionInput.getText().toString());
//        range = (TextView)findViewById(R.id.range);
//        range.setText(result.toString());
    }

}
