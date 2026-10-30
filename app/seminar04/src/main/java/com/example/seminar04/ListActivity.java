package com.example.seminar04;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class ListActivity extends AppCompatActivity {

    private ArrayList<Barca> listaBarci;
    private ArrayAdapter<Barca> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        Intent intent = getIntent();
        listaBarci = (ArrayList<Barca>) intent.getSerializableExtra("lista");

        if (listaBarci == null) {
            listaBarci = new ArrayList<>();
        }

        adapter = new ArrayAdapter<>(
                getApplicationContext(),
                android.R.layout.simple_list_item_1,
                listaBarci
        );

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
                Toast.makeText(getApplicationContext(), "S-a sters: " + barcaSteasa.getMarca(), Toast.LENGTH_SHORT).show();
                return true;
            }
        });
    }
}