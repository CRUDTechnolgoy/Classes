package com.crud.myfirstapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;

public class Dashboard extends AppCompatActivity {

    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        listView = (ListView) findViewById(R.id.listView);
        String vCitys[] = getResources().getStringArray(R.array.city);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(Dashboard.this, android.R.layout.simple_list_item_1, vCitys);

        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Log.e("Dashboard", String.valueOf(position));
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_dashbord, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_add) {
            boolean wrapInScrollView = true;
            new MaterialDialog.Builder(Dashboard.this)
                    .title("Enter Your Name")
                    .autoDismiss(false)
                    .customView(R.layout.dialog_changepassword_customview, wrapInScrollView)
                    .positiveText("ADD")
                    .negativeText("CANCEL")
                    .onPositive(new MaterialDialog.SingleButtonCallback() {
                        @Override
                        public void onClick(final MaterialDialog dialog, DialogAction which) {
                            EditText feedback = (EditText) dialog.findViewById(R.id.act_name);
                            Log.e("Alert Click", feedback.getText().toString());
                        }
                    })
                    .onNegative(new MaterialDialog.SingleButtonCallback() {
                        @Override
                        public void onClick(MaterialDialog dialog, DialogAction which) {
                            dialog.dismiss();
                        }
                    }).show();
            return true;
        } else if (item.getItemId() == R.id.action_exit) {
            Log.e("menu clickded", "action exit");
            this.finish();
        } else if (item.getItemId() == R.id.action_goto) {
            Log.e("menu clickded", "detail");
            Intent i = new Intent(Dashboard.this, WebViewActivity.class);
            //passing data from one activity to another
            i.putExtra("url", "www.google.com");
            startActivity(i);
        }
        return super.onOptionsItemSelected(item);
    }
}
