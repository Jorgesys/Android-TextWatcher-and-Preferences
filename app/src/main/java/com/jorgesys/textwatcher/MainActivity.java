package com.jorgesys.textwatcher;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        EditText myEditText = (EditText)findViewById(R.id.myEditText);

        //Get last value saved from preferences.
        myEditText.setText(getValue(getApplicationContext()));

        myEditText.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // Fires right as the text is being changed (even supplies the range of text)
                saveValue(getApplicationContext(), s.toString());//Save value to preferences
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {
                // Fires right before text is changing
            }

            @Override
            public void afterTextChanged(Editable s) {
                // Fires right after the text has changed

            }
        });

    }


    //Preferences name.
    private String PREFS_KEY = "myPreferences";

    //Set preferece value.
    public void saveValue(Context context, String text) {
        SharedPreferences settings = context.getSharedPreferences(PREFS_KEY, MODE_PRIVATE);
        SharedPreferences.Editor editor;
        editor = settings.edit();
        editor.putString("editTextValue", text);
        editor.commit();
    }
    //Retrieve preference value.
    public String getValue(Context context) {
        SharedPreferences preferences = context.getSharedPreferences(PREFS_KEY, MODE_PRIVATE);
        return  preferences.getString("editTextValue", "");
    }
}
