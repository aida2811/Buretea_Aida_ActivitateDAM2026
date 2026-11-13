package com.example.seminar04;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class AddActivity extends AppCompatActivity {

    private EditText locuri_et;
    private EditText culoare_et;
    private CheckBox are_veste_cb;
    private EditText marca_et;
    private SwitchCompat are_motor_sw;
    private Button add_btn;

    private int pozitieEditare = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_add_styled); // Sau activity_add în funcție de fișierul folosit

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        locuri_et = findViewById(R.id.nr_locuri_et);
        culoare_et = findViewById(R.id.culoare_et);
        are_veste_cb = findViewById(R.id.are_veste_cb);
        marca_et = findViewById(R.id.marca_et);
        are_motor_sw = findViewById(R.id.are_motor_sw);
        add_btn = findViewById(R.id.add_btn);

        Intent intent = getIntent();
        if (intent != null && intent.hasExtra("barca_de_editat")) {
            Barca barca = intent.getParcelableExtra("barca_de_editat");
            pozitieEditare = intent.getIntExtra("pozitie", -1);

            if (barca != null) {

                locuri_et.setText(String.valueOf(barca.getNrLocuri()));
                culoare_et.setText(barca.getCuloare());
                marca_et.setText(barca.getMarca());
                are_veste_cb.setChecked(barca.isAreVeste());
                are_motor_sw.setChecked(barca.isAreMotor());

                add_btn.setText("Modifică Barcă");
            }
        }
    }

    public void addBarca(View view) {
        int nrLocuri = 0;
        String culoare = (culoare_et != null && culoare_et.getText() != null) ? culoare_et.getText().toString() : "";
        boolean areVeste = (are_veste_cb != null) && are_veste_cb.isChecked();
        String marca = (marca_et != null && marca_et.getText() != null) ? marca_et.getText().toString() : "";
        boolean areMotor = (are_motor_sw != null) && are_motor_sw.isChecked();

        try {
            if (locuri_et != null && locuri_et.getText() != null && !locuri_et.getText().toString().isEmpty()) {
                nrLocuri = Integer.parseInt(locuri_et.getText().toString());
            }
        } catch (Exception e) {
            nrLocuri = 0;
        }

        Barca barca = new Barca(nrLocuri, culoare, areMotor, marca, areVeste);

        Intent it = new Intent();
        it.putExtra("barca", barca);
        it.putExtra("pozitie", pozitieEditare);

        setResult(RESULT_OK, it);
        finish();
    }
}