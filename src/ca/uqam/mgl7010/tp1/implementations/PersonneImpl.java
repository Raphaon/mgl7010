package ca.uqam.mgl7010.tp1.implementations;

import ca.uqam.mgl7010.tp1.types.Adresse;
import ca.uqam.mgl7010.tp1.types.Personne;

public class PersonneImpl implements Personne {
    private final String nom;
    private final String prenom;
    private final String nas;
    private Adresse adresse;
    private String courriel;

    public PersonneImpl(String nom, String prenom, String nas) {
        this.nom = nom;
        this.prenom = prenom;
        this.nas = nas;
    }

    @Override
    public String getNom() {
        return nom;
    }

    @Override
    public String getPrenom() {
        return prenom;
    }

    @Override
    public String getNas() {
        return nas;
    }

    @Override
    public Adresse getAdresse() {
        return adresse;
    }

    @Override
    public String getCourriel() {
        return courriel;
    }

    @Override
    public void setAdresse(Adresse adresse) {
        this.adresse = adresse;
    }

    @Override
    public void setCourriel(String courriel) {
        this.courriel = courriel;
    }

    @Override
    public String toString() {
        return prenom + " " + nom + " (" + nas + ")";
    }
}
