package com.pelist.main;

/**
 * Autores:
 * Roberto Michán Sánchez
 * Tomás Goizueta Díaz-Parreño
 */

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

import db.DBManager;
import db.DatabaseHelper;

public class MovieListActivity extends AppCompatActivity {

    private DBManager dbManager;

    private ListView listView;
    Context context;
    Resources resources;


    private SimpleCursorAdapter adapter;

    TextView language_dialog,text1;
    boolean lang_selected;

    final String[] from = new String[] { DatabaseHelper._ID,
            DatabaseHelper.SUBJECT, DatabaseHelper.YEAR };

    final int[] to = new int[] { R.id.id, R.id.title, R.id.desc };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.fragment_emp_list);
        setTitle(getIntent().getStringExtra("title"));

        dbManager = new DBManager(this);
        dbManager.open();
        Cursor cursor = dbManager.fetch_movies(Integer.parseInt(getIntent().getStringExtra("id")));

        listView = (ListView) findViewById(R.id.list_view);
        listView.setEmptyView(findViewById(R.id.empty));

        adapter = new SimpleCursorAdapter(this, R.layout.activity_view_item, cursor, from, to, 0);
        adapter.notifyDataSetChanged();

        listView.setAdapter(adapter);

        // OnCLickListiner For List Items
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long viewId) {
                TextView idTextView = (TextView) view.findViewById(R.id.id);
                TextView titleTextView = (TextView) view.findViewById(R.id.title);
                TextView descTextView = (TextView) view.findViewById(R.id.desc);

                String id = idTextView.getText().toString();
                String title = titleTextView.getText().toString();
                String desc = descTextView.getText().toString();

                Intent modify_intent = new Intent(getApplicationContext(), ModifyMovieActivity.class);
                modify_intent.putExtra("name", getIntent().getStringExtra("title"));
                modify_intent.putExtra("title", title);
                modify_intent.putExtra("desc", desc);
                modify_intent.putExtra("id", id);
                modify_intent.putExtra("dir", cursor.getString(3));
                modify_intent.putExtra("year", cursor.getString(4));
                modify_intent.putExtra("score", cursor.getString(5));
                modify_intent.putExtra("date", cursor.getString(6));
                modify_intent.putExtra("list", getIntent().getStringExtra("id"));

                startActivity(modify_intent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        if (id == R.id.add_list) {

            Intent add_mem = new Intent(this, AddMovieActivity.class);
            add_mem.putExtra("list", getIntent().getStringExtra("id"));
            add_mem.putExtra("title", getIntent().getStringExtra("title"));
            startActivity(add_mem);

        }
        return super.onOptionsItemSelected(item);
    }
}