package com.example.seminar04;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class AddActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);


        setContentView(R.layout.activity_add);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    public void addBarca(View view) {
        EditText locuri_et = findViewById(R.id.nr_locuri_et);
        EditText culoare_et = findViewById(R.id.culoare_et);
        CheckBox are_veste_cb = findViewById(R.id.are_veste_cb);
        EditText marca_et = findViewById(R.id.marca_et);
        SwitchCompat are_motor_sw = findViewById(R.id.are_motor_sw);

        int nrLocuri = 0;
        String culoare = culoare_et.getText().toString();
        boolean areVeste = are_veste_cb.isChecked();
        String marca = marca_et.getText().toString();
        boolean areMotor = are_motor_sw != null && are_motor_sw.isChecked();

        try {
            nrLocuri = Integer.parseInt(locuri_et.getText().toString());
        } catch (Exception e) {}

        Barca barca = new Barca(nrLocuri, culoare, areMotor, marca, areVeste);

        Intent it = new Intent();
        it.putExtra("barca", barca);

        setResult(RESULT_OK, it);
        finish();
    }
}