package com.example.etiennepayet.taches;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    // Constantes pour gérer les activités d'ajout
    // et de suppression de tâches.
    private final static int ADD_ACTIVITY  = 0;
    private final static int EDIT_ACTIVITY = 1;

    private ListView mListView;
    private MyArrayAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mListView = (ListView) findViewById(R.id.list);
        registerForContextMenu(mListView);

        ArrayList<Tache> taches = new ArrayList<Tache>();
        taches.add(new Tache("Cours de programmation", 4));
        taches.add(new Tache("Entrainement de natation", 3));
        taches.add(new Tache("Faire une sieste", 2));
        taches.add(new Tache("Prendre une douche", 0));
        taches.add(new Tache("Ecouter les infos", 1));

        adapter = new MyArrayAdapter(this, taches);
        mListView.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) {
            case R.id.action_add:
                Intent intent = new Intent(this, EditActivity.class);
                intent.putExtra("titre", "");
                intent.putExtra("priorite", -1);
                intent.putExtra("position", -1);
                startActivityForResult(intent, ADD_ACTIVITY);
                return true;
            case R.id.action_quit:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater().inflate(R.menu.context, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo menuInfo = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        switch(item.getItemId()) {
            case R.id.action_edit:
                Tache t = adapter.getItem(menuInfo.position);
                Intent intent = new Intent(this, EditActivity.class);
                intent.putExtra("titre", t.getTitre());
                intent.putExtra("priorite", t.getPriorite());
                intent.putExtra("position", menuInfo.position);
                startActivityForResult(intent, EDIT_ACTIVITY);
                return true;
            case R.id.action_delete:
                adapter.remove(adapter.getItem(menuInfo.position));
                return true;
        }
        return super.onContextItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch(requestCode) {
            case ADD_ACTIVITY:
                switch(resultCode) {
                    case RESULT_OK:
                        String titre = data.getExtras().getCharSequence("titre").toString();
                        String priorite = data.getExtras().getCharSequence("priorite").toString();
                        adapter.add(new Tache(titre, Integer.parseInt(priorite)));
                        break;
                    case RESULT_CANCELED:
                        break;
                }
                break;
            case EDIT_ACTIVITY:
                switch(resultCode) {
                    case RESULT_OK:
                        String titre = data.getExtras().getCharSequence("titre").toString();
                        String priorite = data.getExtras().getCharSequence("priorite").toString();
                        int position = data.getExtras().getInt("position");
                        Tache t = adapter.getItem(position);
                        t.setTitre(titre);
                        t.setPriorite(Integer.parseInt(priorite));
                        adapter.notifyDataSetChanged();
                        break;
                    case RESULT_CANCELED:
                        break;
                }
                break;
            default:
                break;
        }
    }
}
