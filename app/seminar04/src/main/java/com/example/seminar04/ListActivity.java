package com.example.seminar04;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class ListActivity extends AppCompatActivity {

    public static final int REQUEST_CODE_EDIT = 100;

    private ArrayList<Barca> listaBarci;
    private BarcaAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        Intent intent = getIntent();
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.TIRAMISU) {
            listaBarci = intent.getParcelableArrayListExtra("lista", Barca.class);
        } else {
            listaBarci = intent.getParcelableArrayListExtra("lista");
        }

        if (listaBarci == null) {
            listaBarci = new ArrayList<>();
        }

        adapter = new BarcaAdapter(listaBarci, this, R.layout.item_barca);

        ListView lv = findViewById(R.id.listView);
        lv.setAdapter(adapter);


        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Barca barcaSelectata = listaBarci.get(position);

                Intent intentEdit = new Intent(ListActivity.this, AddActivity.class);
                intentEdit.putExtra("barca_de_editat", barcaSelectata);
                intentEdit.putExtra("pozitie", position);
                startActivityForResult(intentEdit, REQUEST_CODE_EDIT);
            }
        });


        lv.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Barca barcaSteasa = listaBarci.remove(position);
                adapter.notifyDataSetChanged();


                Intent resultIntent = new Intent();
                resultIntent.putParcelableArrayListExtra("lista_actualizata", listaBarci);
                setResult(RESULT_OK, resultIntent);

                Toast.makeText(getApplicationContext(), "S-a șters: " + barcaSteasa.getMarca(), Toast.LENGTH_SHORT).show();
                return true;
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_CODE_EDIT && resultCode == RESULT_OK && data != null) {
            Barca barcaModificata = data.getParcelableExtra("barca");
            int pozitie = data.getIntExtra("pozitie", -1);

            if (barcaModificata != null && pozitie != -1) {
                listaBarci.set(pozitie, barcaModificata);
                adapter.notifyDataSetChanged();


                Intent resultIntent = new Intent();
                resultIntent.putParcelableArrayListExtra("lista_actualizata", listaBarci);
                setResult(RESULT_OK, resultIntent);
            }
        }
    }
}