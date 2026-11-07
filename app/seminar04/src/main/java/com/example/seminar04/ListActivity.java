package com.example.seminar04;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class ListActivity extends AppCompatActivity {

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
                Toast.makeText(getApplicationContext(), "Selectat: " + barcaSelectata.toString(), Toast.LENGTH_SHORT).show();
            }
        });

        lv.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Barca barcaSteasa = listaBarci.remove(position);
                adapter.notifyDataSetChanged();
                Toast.makeText(getApplicationContext(), "S-a șters: " + barcaSteasa.getMarca(), Toast.LENGTH_SHORT).show();
                return true;
            }
        });
    }
}