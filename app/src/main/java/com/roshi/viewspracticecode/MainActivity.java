package com.roshi.viewspracticecode;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.MultiAutoCompleteTextView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;
import android.widget.ToggleButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;

public class MainActivity extends AppCompatActivity {

    // Views Declaration
    EditText editName;
    RadioGroup radioGroupGender;
    CheckBox checkTerms;
    Button btnSubmit;
    SwitchCompat switchWifi;
    ToggleButton toggleStatus;
    MultiAutoCompleteTextView multiAuto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Linking XML views
        editName = findViewById(R.id.editName);
        radioGroupGender = findViewById(R.id.radioGroupGender);
        checkTerms = findViewById(R.id.checkTerms);
        btnSubmit = findViewById(R.id.btnSubmit);
        switchWifi = findViewById(R.id.switchWifi);
        toggleStatus = findViewById(R.id.toggleStatus);
        multiAuto = findViewById(R.id.multiAuto);

        // Setting up MultiAutoComplete Skills
        String[] skills = {"Java", "Kotlin", "Android", "Python", "C++"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_dropdown_item_1line, skills);
        multiAuto.setAdapter(adapter);
        multiAuto.setTokenizer(new MultiAutoCompleteTextView.CommaTokenizer());

        // Button Click Logic
        btnSubmit.setOnClickListener(view -> {
            String name = editName.getText().toString().trim();

            if (name.isEmpty()) {
                Toast.makeText(this, getString(R.string.msg_enter_name), Toast.LENGTH_SHORT).show();
                return;
            }

            if (radioGroupGender.getCheckedRadioButtonId() == -1) {
                Toast.makeText(this, getString(R.string.msg_select_gender), Toast.LENGTH_SHORT).show();
                return;
            }

            if (!checkTerms.isChecked()) {
                Toast.makeText(this, getString(R.string.msg_agree_terms), Toast.LENGTH_SHORT).show();
                return;
            }

            // Get Gender
            int selectedId = radioGroupGender.getCheckedRadioButtonId();
            RadioButton rb = findViewById(selectedId);
            String gender = rb.getText().toString();

            String status = "WiFi: " + (switchWifi.isChecked() ? "ON" : "OFF");

            Toast.makeText(this, "Name: " + name + "\nGender: " + gender + "\n" + status, Toast.LENGTH_LONG).show();
        });
    }
}