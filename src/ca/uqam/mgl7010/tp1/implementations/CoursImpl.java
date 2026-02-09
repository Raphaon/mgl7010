package ca.uqam.mgl7010.tp1.implementations;

import ca.uqam.mgl7010.tp1.types.Cours;

public class CoursImpl implements Cours {
    private final String sigle;
    private final String titre;
    private final int nombreCredits;
    private String description;

    public CoursImpl(String sigle, String titre, int nombreCredits, String description) {
        this.sigle = sigle;
        this.titre = titre;
        this.nombreCredits = nombreCredits;
        this.description = description;
    }

    @Override
    public String getSigle() {
        return sigle;
    }

    @Override
    public String getTitre() {
        return titre;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public int getNombreCredits() {
        return nombreCredits;
    }

    @Override
    public void setDescription(String description) {
        this.description = description;
    }
}
