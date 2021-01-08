package com.pelist.main;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.SimpleCursorAdapter;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import android.app.AlertDialog;
import android.content.DialogInterface;

import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.pelist.main.R;

import db.DBManager;
import db.DatabaseHelper;

public class CountryListActivity extends AppCompatActivity {

    private DBManager dbManager;

    private ListView listView;
    Context context;
    Resources resources;


    private SimpleCursorAdapter adapter;

    TextView language_dialog,text1;
    boolean lang_selected;

    final String[] from = new String[] { DatabaseHelper._ID,
            DatabaseHelper.SUBJECT, DatabaseHelper.DESC };

    final int[] to = new int[] { R.id.id, R.id.title, R.id.desc };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        String v= getString(R.string.watched_movies_list);

        setContentView(R.layout.fragment_emp_list);

        dbManager = new DBManager(this);
        dbManager.open();
        Cursor cursor = dbManager.fetch();

        listView = (ListView) findViewById(R.id.list_view);
        listView.setEmptyView(findViewById(R.id.empty));

        adapter = new SimpleCursorAdapter(this, R.layout.activity_view_record, cursor, from, to, 0);
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

                Intent modify_intent = new Intent(getApplicationContext(), ModifyCountryActivity.class);
                modify_intent.putExtra("title", title);
                modify_intent.putExtra("desc", desc);
                modify_intent.putExtra("id", id);

                startActivity(modify_intent);
            }
        });
        //setContentView(R.layout.activity_main);

        language_dialog = (TextView)findViewById(R.id.dialog_language);
        language_dialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String[] Language = {"ENGLISH", "SPANISH"};
                final int checkedItem;
                if(lang_selected)
                {
                    checkedItem=0;
                }else
                {
                    checkedItem=1;
                }
                final AlertDialog.Builder builder = new AlertDialog.Builder(CountryListActivity.this);
                builder.setTitle("Select a Language...")
                        .setSingleChoiceItems(Language, checkedItem, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Toast.makeText(CountryListActivity.this,""+which,Toast.LENGTH_SHORT).show();
                                language_dialog.setText(Language[which]);
                                lang_selected= Language[which].equals("ENGLISH");
                                //if user select prefered language as English then
                                if(Language[which].equals("ENGLISH"))
                                {
                                    context = LocaleHelper.setLocale(CountryListActivity.this, "en");
                                    resources = context.getResources();
                                    //text1.setText(resources.getString(R.string.language));

                                }
                                //if user select prefered language as Hindi then
                                if(Language[which].equals("SPANISH"))
                                {
                                    context = LocaleHelper.setLocale(CountryListActivity.this, "es");
                                    resources = context.getResources();
                                    //text1.setText(resources.getString(R.string.language));
                                }
                            }
                        })
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });
                builder.create().show();
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
        if (id == R.id.add_record) {

            Intent add_mem = new Intent(this, AddCountryActivity.class);
            startActivity(add_mem);

        }
        return super.onOptionsItemSelected(item);
    }

}