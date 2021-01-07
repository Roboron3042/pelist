package com.example.pelist;

import android.database.Cursor;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.util.Log;
import android.view.View;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import bd.DBManager;
import bd.DatabaseHelper;

public class MainActivity extends AppCompatActivity {

    private DBManager dbManager;

    private ListView lista;

    private SimpleCursorAdapter adapter;

    final String[] from = new String[] { DatabaseHelper.NOMBRE};

    final int[] to = new int[] { R.id.nombre };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_empty);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        dbManager = new DBManager(this);
        dbManager.open();
        Cursor cursor = dbManager.fetch();
        //Log.i("Cursor", cursor);

        lista = (ListView) findViewById(R.id.list_view);
        lista.setEmptyView(findViewById(R.id.empty));

        adapter = new SimpleCursorAdapter(this, R.layout.activity_main, cursor, from, to, 0);
        //adapter = new SimpleCursorAdapter()
        adapter.notifyDataSetChanged();

        lista.setAdapter(adapter);

        lista = (ListView) findViewById(R.id.list_view);

        // OnCLickListiner For List Items
/*
        lista.setOnItemClickListener(new AdapterView.OnItemClickListener()

            {
                @Override
                public void onItemClick (AdapterView < ? > parent, View view,int position, long viewId)
                {
                String id = idTextView.getText().toString();
                String title = titleTextView.getText().toString();
                String desc = descTextView.getText().toString();

                Intent modify_intent = new Intent(getApplicationContext(), ModifyCountryActivity.class);
                modify_intent.putExtra("title", title);
                modify_intent.putExtra("desc", desc);
                modify_intent.putExtra("id", id);

                startActivity(modify_intent);
                }
            });
            */

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}