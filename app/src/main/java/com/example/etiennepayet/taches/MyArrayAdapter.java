package com.example.etiennepayet.taches;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by etiennepayet on 27/04/2017.
 */

public class MyArrayAdapter extends ArrayAdapter<Tache> {

    private final Context context;

    public MyArrayAdapter(Context context, ArrayList<Tache> values) {
        super(context, R.layout.cell_layout, values);
        this.context = context;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        View cellView = convertView;

        if (cellView == null) {
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            cellView = inflater.inflate(R.layout.cell_layout, parent, false);
        }

        TextView titreTextView = (TextView) cellView.findViewById(R.id.titre);
        TextView prioriteTextView = (TextView) cellView.findViewById(R.id.priorite);
        ImageView imageView = (ImageView) cellView.findViewById(R.id.image);

        Tache t = getItem(position);
        int p = t.getPriorite();
        titreTextView.setText(t.getTitre());
        prioriteTextView.setText(context.getString(R.string.priorite) + " " + p);

        switch (p) {
            case 0:
                imageView.setImageResource(R.drawable.prio0);
                break;
            case 1:
                imageView.setImageResource(R.drawable.prio1);
                break;
            case 2:
                imageView.setImageResource(R.drawable.prio2);
                break;
            case 3:
                imageView.setImageResource(R.drawable.prio3);
                break;
            default:
                imageView.setImageResource(R.drawable.prio4);
        }

        return cellView;
    }
}
