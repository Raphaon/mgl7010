package ca.uqam.mgl7010.tp1.types;

public interface Personne {

    /**
     * le nom de la personne
     * @return
     */
    public String getNom();

    /**
     * le prenom de la personne
     * @return
     */
    public String getPrenom();

    /**
     * le NAS de la personne
     * @return
     */
    public String getNas();

    /**
     * L'adresse de la personne
     * @return
     */
    public Adresse getAdresse();

    /**
     * le courriel de la personne
     * @return
     */
    public String getCourriel();

    /**
     * modifier l'adresse de la personne
     * @param adresse
     */
    public void setAdresse(Adresse adresse);

    /**
     * modifier le courriel de la personne
     * @param courriel
     */
    public void setCourriel(String courriel);

}
