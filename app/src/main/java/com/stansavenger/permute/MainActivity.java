package com.stansavenger.permute;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONException;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    RadioGroup inputRadioGroup, outputRadioGroup;
    RadioButton inputRadioButton,outputRadioButton ;
    TextView baseCurrencyView, convertedCurrencyView;
    Double convertedCurrencyValue;
    String convertedCurrencyInText;
    Button convertButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);

        inputRadioGroup = findViewById(R.id.inputRadioGroup);
        outputRadioGroup = findViewById(R.id.outputRadioGroup);

        baseCurrencyView = findViewById(R.id.baseCurrency);
        convertedCurrencyView = findViewById(R.id.convertedCurrency);

        convertButton = findViewById(R.id.convert);
        convertButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                try {
                    getConvertedCurrency();
                    convertedCurrencyInText = convertedCurrencyValue.toString();
                    convertedCurrencyView.setText(convertedCurrencyInText);
                } catch (IOException | JSONException e) {
                    e.printStackTrace();
                }
            }
        });


//        baseCurrencyView.addTextChangedListener(new TextWatcher() {
//
//            @Override
//            public void afterTextChanged(Editable input) {}
//
//            @Override
//            public void beforeTextChanged(CharSequence input, int start, int count, int after) {
//            }
//
//            @Override
//            public void onTextChanged(CharSequence input, int start, int before, int count) {
//                if(input.length() != 0){
//                    try {
//                        getConvertedCurrency();
//                        convertedCurrencyInText = convertedCurrencyValue.toString();
//                        convertedCurrencyView.setText(convertedCurrencyInText);
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }
//                }else{
//                    convertedCurrencyView.setText("");
//                }
//            }
//        });
    }

    public void onInputRadioButtonClicked(View view) {
        int radioId = inputRadioGroup.getCheckedRadioButtonId();
        inputRadioButton = findViewById(radioId);

        baseCurrencyView.setHint(inputRadioButton.getText());
    }

    public void onOutputRadioButtonClicked(View view) {
        int radioId = outputRadioGroup.getCheckedRadioButtonId();
        outputRadioButton = findViewById(radioId);

        convertedCurrencyView.setHint(outputRadioButton.getText());
    }

    public void getConvertedCurrency() throws IOException, JSONException {
        int inputRadioId = inputRadioGroup.getCheckedRadioButtonId();
        inputRadioButton = findViewById(inputRadioId);
        int outputRadioId = outputRadioGroup.getCheckedRadioButtonId();
        outputRadioButton = findViewById(outputRadioId);

        double baseCurrencyParsed = Double.parseDouble(baseCurrencyView.getText().toString());
        String inputRadioButtonToString = inputRadioButton.getText().toString(), outputRadioButtonToString = outputRadioButton.getText().toString();

        ConvertCurrency.ConvertCurrencyMethod(baseCurrencyParsed, inputRadioButtonToString, outputRadioButtonToString);

        convertedCurrencyValue = Data.GetCurrencyValue(outputRadioButtonToString);
    }


}