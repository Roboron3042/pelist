package com.pelist.main;

/**
 * Created by anupamchugh on 19/10/15.
 */
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

import db.DBManager;

public class ModifyMovieActivity extends Activity implements OnClickListener {

    private EditText titleText;
    private Button updateBtn, deleteBtn;
    private EditText descText;
    private EditText dirText;
    private EditText yearText;
    private EditText scoreText;
    private EditText dateText;
    private EditText listText;

    private long _id;

    private DBManager dbManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setTitle("Modify Record");

        setContentView(R.layout.activity_modify_movie);

        dbManager = new DBManager(this);
        dbManager.open();

        titleText = (EditText) findViewById(R.id.subject_edittext);
        descText = (EditText) findViewById(R.id.description_edittext);
        dirText = (EditText) findViewById(R.id.director_edittext);
        yearText = (EditText) findViewById(R.id.year_edittext);
        scoreText = (EditText) findViewById(R.id.score_edittext);
        dateText = (EditText) findViewById(R.id.date_edittext);
        listText = (EditText) findViewById(R.id.list_edittext);

        updateBtn = (Button) findViewById(R.id.btn_update);
        deleteBtn = (Button) findViewById(R.id.btn_delete);

        Intent intent = getIntent();
        String id = intent.getStringExtra("id");
        String name = intent.getStringExtra("title");
        String desc = intent.getStringExtra("desc");
        String dir = intent.getStringExtra("dir");
        String year = intent.getStringExtra("year");
        String score = intent.getStringExtra("score");
        String date = intent.getStringExtra("date");
        String list = intent.getStringExtra("list");

        _id = Long.parseLong(id);

        titleText.setText(name);
        descText.setText(desc);
        dirText.setText(dir);
        yearText.setText(year);
        scoreText.setText(score);
        dateText.setText(date);
        listText.setText(list);

        updateBtn.setOnClickListener(this);
        deleteBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_update:
                String title = titleText.getText().toString();
                String desc = descText.getText().toString();
                String dir = dirText.getText().toString();
                Integer year = Integer.parseInt(yearText.getText().toString());
                Integer score = Integer.parseInt(scoreText.getText().toString());
                String date = dateText.getText().toString();
                Integer list = Integer.parseInt(listText.getText().toString());

                dbManager.update_movie(_id, title, desc, dir, year, score, date, list);
                this.returnHome(list.toString());
                break;

            case R.id.btn_delete:
                dbManager.delete_movie(_id);
                this.returnHome(getIntent().getStringExtra("list"));
                break;
        }
    }

    public void returnHome(String list) {
        Intent home_intent = new Intent(getApplicationContext(), MovieListActivity.class)
                .setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        home_intent.putExtra("id", list.toString());
        home_intent.putExtra("title", getIntent().getStringExtra("name"));
        startActivity(home_intent);
    }
}
