package com.pelist.main;

/**
 * Autores:
 * Roberto Michán Sánchez
 * Tomás Goizueta Díaz-Parreño
 */
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

import db.DBManager;

public class AddMovieActivity extends Activity implements OnClickListener {

    private Button addTodoBtn;
    private EditText subjectEditText;
    private EditText descEditText;
    private EditText dirEditText;
    private EditText yearEditText;
    private EditText scoreEditText;
    private EditText dateEditText;

    private DBManager dbManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setTitle(getString(R.string.add_movie));

        setContentView(R.layout.activity_add_movie);

        subjectEditText = (EditText) findViewById(R.id.subject_edittext);
        descEditText = (EditText) findViewById(R.id.description_edittext);
        dirEditText = (EditText) findViewById(R.id.director_edittext);
        yearEditText = (EditText) findViewById(R.id.year_edittext);
        scoreEditText = (EditText) findViewById(R.id.score_edittext);
        dateEditText = (EditText) findViewById(R.id.date_edittext);

        addTodoBtn = (Button) findViewById(R.id.add_record);

        dbManager = new DBManager(this);
        dbManager.open();
        addTodoBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.add_record:

                final String name = subjectEditText.getText().toString();
                final String desc = descEditText.getText().toString();
                final String dir = dirEditText.getText().toString();
                final Integer year = Integer.parseInt(yearEditText.getText().toString());
                final Integer score = Integer.parseInt(scoreEditText.getText().toString());
                final String date = dateEditText.getText().toString();
                final Integer list = Integer.parseInt(getIntent().getStringExtra("list"));

                dbManager.insert_movie(name, desc, dir, year, score, date, list);

                Intent main = new Intent(AddMovieActivity.this, MovieListActivity.class)
                        .setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                main.putExtra("id", list.toString());
                main.putExtra("title", getIntent().getStringExtra("title"));

                startActivity(main);
                break;
        }
    }

}