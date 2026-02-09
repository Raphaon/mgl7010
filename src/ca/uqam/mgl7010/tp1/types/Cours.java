package ca.uqam.mgl7010.tp1.types;

/**
 * Cette interface représente les cours tels qu'on les trouve dans le
 * catalogue de cours, par exemple, sur le site https://etudier.uqam.ca
 */
public interface Cours {

    /**
     * retourne le sigle du cours
     * @return
     */
    public String getSigle() ;

    /**
     * retourne le titre du cours
     * @return
     */
    public String getTitre();

    /**
     * retourne la description du cours
     * @return
     */
    public String getDescription() ;

    /**
     * retourne le nombre de crédits du cours
     * @return
     */
    public int getNombreCredits() ;

    /**
     * modifie la description du cours
     * @param description
     */
    public void setDescription(String description);
}
