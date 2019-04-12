package com.example.etiennepayet.taches;

/**
 * Created by etiennepayet on 27/04/2017.
 */

public class Tache {

    private String titre;
    private int priorite;

    public Tache(String titre, int priorite) {
        this.titre = titre;
        this.priorite = priorite;
    }

    public String getTitre() { return titre; }

    public int getPriorite() { return priorite; }

    public void setTitre(String titre) { this.titre = titre; }

    public void setPriorite(int priorite) { this.priorite = priorite; }
}
