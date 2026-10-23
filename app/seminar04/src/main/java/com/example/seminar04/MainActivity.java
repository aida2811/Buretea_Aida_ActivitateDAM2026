package com.example.seminar04;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

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
    }

    public void addBarca(View view) {
        Intent it = new Intent(this, AddActivity.class);
        startActivityForResult(it, 45);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 45) {
            if (resultCode == RESULT_OK && data != null && data.getExtras() != null) {
                Barca barca = (Barca) data.getExtras().getSerializable("barca");
                if (barca != null) {
                    Toast.makeText(this, barca.toString(), Toast.LENGTH_LONG).show();

                    TextView barca_tv = findViewById(R.id.barca_tv);
                    String existing_text = barca_tv.getText().toString();
                    String target_text = existing_text + (!existing_text.isEmpty() ? "\n" : "") + barca.toString();
                    barca_tv.setText(target_text);
                }
            }
        }
    }
}