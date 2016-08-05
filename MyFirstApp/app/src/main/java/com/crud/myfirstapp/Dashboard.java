package com.crud.myfirstapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class Dashboard extends AppCompatActivity {

    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        listView=(ListView)findViewById(R.id.listView);
        String vCitys[] = getResources().getStringArray(R.array.city);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(Dashboard.this, android.R.layout.simple_list_item_1, vCitys);

        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Log.e("Dashboard",String.valueOf(position));
            }
        });
    }
}
