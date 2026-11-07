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
                it.putExtra("lista", listaBarci);
                startActivity(it);
            }
        });
    }

    public void addBarca(View view) {
        Intent it = new Intent(this, AddActivity.class);
        startActivityForResult(it, 45);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 45) {
            if (resultCode == RESULT_OK && data != null) {

                Barca barca = data.getParcelableExtra("barca");
                if (barca != null) {
                    listaBarci.add(barca);

                    Toast.makeText(this, "Adăugat: " + barca.toString(), Toast.LENGTH_LONG).show();

                    TextView barca_tv = findViewById(R.id.barca_tv);
                    String existing_text = barca_tv.getText().toString();
                    String target_text = existing_text + (!existing_text.isEmpty() ? "\n" : "") + barca.toString();
                    barca_tv.setText(target_text);
                }
            }
        }
    }
}