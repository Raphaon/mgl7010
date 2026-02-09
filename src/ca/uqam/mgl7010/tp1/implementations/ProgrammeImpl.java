package ca.uqam.mgl7010.tp1.implementations;

import ca.uqam.mgl7010.tp1.types.Cours;
import ca.uqam.mgl7010.tp1.types.Diplome;
import ca.uqam.mgl7010.tp1.types.Programme;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ProgrammeImpl implements Programme {
    private final String nom;
    private final Diplome diplome;
    private String description;
    private int nombreDeCredits;
    private float seuilMoyenne = 2.0f;

    private final List<Diplome> prerequis = new ArrayList<>();
    private final List<Cours> coursObligatoires = new ArrayList<>();
    private final List<Cours> coursOptionnels = new ArrayList<>();

    public ProgrammeImpl(String nom, Diplome diplome) {
        this.nom = nom;
        this.diplome = diplome;
    }

    @Override
    public String getNom() {
        return nom;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public Diplome getDiplome() {
        return diplome;
    }

    @Override
    public Iterator<Diplome> getPrerequis() {
        return prerequis.iterator();
    }

    @Override
    public void ajoutePrerequis(Diplome prerequis) {
        if (prerequis != null) {
            this.prerequis.add(prerequis);
        }
    }

    @Override
    public Diplome enleverPrerequis(Diplome prerequis) {
        if (this.prerequis.remove(prerequis)) {
            return prerequis;
        }
        return null;
    }

    @Override
    public boolean aPrerequis(Diplome diplome) {
        return prerequis.contains(diplome);
    }

    @Override
    public int getNombreDeCredits() {
        return nombreDeCredits;
    }

    @Override
    public void setNombreDeCredits(int nombreCredits) {
        this.nombreDeCredits = nombreCredits;
    }

    @Override
    public float getSeuilMoyenne() {
        return seuilMoyenne;
    }

    @Override
    public void setSeuilMoyenne(float seuil) {
        this.seuilMoyenne = seuil;
    }

    @Override
    public Iterator<Cours> getCoursObligatoires() {
        return coursObligatoires.iterator();
    }

    @Override
    public void ajouteCoursObligatoire(Cours unCours) {
        if (unCours != null && !coursObligatoires.contains(unCours)) {
            coursObligatoires.add(unCours);
        }
    }

    @Override
    public Cours retirerCoursObligatoire(Cours unCours) {
        if (coursObligatoires.remove(unCours)) {
            return unCours;
        }
        return null;
    }

    @Override
    public Iterator<Cours> getCoursOptionnels() {
        return coursOptionnels.iterator();
    }

    @Override
    public void ajouteCoursOptionnel(Cours unCours) {
        if (unCours != null && !coursOptionnels.contains(unCours)) {
            coursOptionnels.add(unCours);
        }
    }

    @Override
    public Cours retirerCoursOptionnel(Cours unCours) {
        if (coursOptionnels.remove(unCours)) {
            return unCours;
        }
        return null;
    }

    @Override
    public int getNombreCreditsObligatoires() {
        return totalCredits(coursObligatoires);
    }

    @Override
    public int getNombreCreditsOptionnels() {
        return totalCredits(coursOptionnels);
    }

    @Override
    public boolean estUnCoursObligatoire(Cours cours) {
        return coursObligatoires.contains(cours);
    }

    @Override
    public boolean estUnCoursOptionnel(Cours cours) {
        return coursOptionnels.contains(cours);
    }

    private int totalCredits(List<Cours> cours) {
        int total = 0;
        for (Cours unCours : cours) {
            if (unCours != null) {
                total += unCours.getNombreCredits();
            }
        }
        return total;
    }
}
