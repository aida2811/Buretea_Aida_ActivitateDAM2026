package com.example.seminar04;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    public static final int REQUEST_CODE_ADD = 45;
    public static final int REQUEST_CODE_LIST = 88;

    private ArrayList<Barca> listaBarci = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


        Button btnVeziLista = findViewById(R.id.btn_vezi_lista);
        btnVeziLista.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(getApplicationContext(), ListActivity.class);
                it.putParcelableArrayListExtra("lista", listaBarci);
                startActivityForResult(it, REQUEST_CODE_LIST);
            }
        });


        Button btnGalerie = findViewById(R.id.btn_galerie_imagini);
        btnGalerie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ImaginiActivity.class);
                startActivity(intent);
            }
        });
    }


    public void addBarca(View view) {
        Intent it = new Intent(this, AddActivity.class);
        startActivityForResult(it, REQUEST_CODE_ADD);
    }

    private void actualizeazaTextView() {
        TextView barca_tv = findViewById(R.id.barca_tv);
        StringBuilder sb = new StringBuilder();
        for (Barca b : listaBarci) {
            sb.append(b.toString()).append("\n");
        }
        barca_tv.setText(sb.toString().trim());
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK && data != null) {
            if (requestCode == REQUEST_CODE_ADD) {
                Barca barca = data.getParcelableExtra("barca");
                if (barca != null) {
                    listaBarci.add(barca);
                    Toast.makeText(this, "Adăugat: " + barca.getMarca(), Toast.LENGTH_SHORT).show();
                    actualizeazaTextView();
                }
            } else if (requestCode == REQUEST_CODE_LIST) {

                ArrayList<Barca> listaNoua = data.getParcelableArrayListExtra("lista_actualizata");
                if (listaNoua != null) {
                    listaBarci = listaNoua;
                    actualizeazaTextView();
                }
            }
        }
    }
}