package com.example.etiennepayet.taches;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

/**
 * Created by etiennepayet on 27/04/2017.
 */

public class EditActivity extends AppCompatActivity {

    private EditText titreEditText;
    private EditText prioriteEditText;
    private int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_layout);

        titreEditText    = (EditText) findViewById(R.id.title_edittext);
        prioriteEditText = (EditText) findViewById(R.id.priority_edittext);

        Intent data = getIntent();
        CharSequence titre = data.getExtras().getCharSequence("titre", "");
        int priorite = data.getExtras().getInt("priorite", -1);
        position = data.getExtras().getInt("position", -1);

        titreEditText.setText(titre);
        prioriteEditText.setText(priorite < 0 ? "" : String.valueOf(priorite));
    }

    public void okClick(View v) {
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("titre", titreEditText.getText());
        intent.putExtra("priorite", prioriteEditText.getText());
        intent.putExtra("position", position);
        setResult(RESULT_OK, intent);
        finish();
    }

    public void cancelClick(View v) {
        setResult(RESULT_CANCELED);
        finish();
    }
}
