package ca.uqam.mgl7010.tp1.types;

import java.util.Iterator;

public interface Programme {

    /**
     * retourne le nom du programme
     * @return
     */
    public String getNom();

    /**
     * retourne la description du programme
     * @return
     */
    public String getDescription();

    /**
     * modifie la description du programme
     * @param description
     */
    public void setDescription(String description);

    /**
     * retourne le diplôme obtenu à l'issue du
     * programme (par ex 'Maitrise en génie logiciel')
     * @return
     */
    public Diplome getDiplome();

    /**
     * retourne la liste des prérequis du programme. C'est supposé
     * être une liste de OU
     * @return
     */
    public Iterator<Diplome> getPrerequis();

    /**
     * Ajoute un diplôme comme un prérequis
     * potentiel. Les prérequis sont "OR"ed
     * @param prerequis
     */
    public void ajoutePrerequis(Diplome prerequis);

    /**
     * Enlever le diplome <code>prerequis</code> de
     * la liste des prérequis
     * @param prerequis
     * @return
     */
    public Diplome enleverPrerequis(Diplome prerequis);

    /**
     * Cette méthode vérifie si le programme accepte le 
     * <code>diplome</code> comme prérequis, càd
     * elle vérifie si <code>diplome</code> fait partie
     * des prerequis
     * @param diplome
     * @return
     */
    public boolean aPrerequis(Diplome diplome);

    /**
     * Retounr le nombre de crédits du programme
     * @return
     */
    public int getNombreDeCredits();

    /**
     * Modifier le nombre de crédits du programme
     * @param nombreCredits
     */
    public void setNombreDeCredits(int nombreCredits);

    /**
     * retourne le seuil minimal de moyenne pour réussir le programme.
     * Par défaut, ça retourne 2.0. Les programmes des cycles supérieurs
     * (DESS, Maitrise, Doctorat) peuvent exiger 3.0 ou plus
     * @return
     */
    public float getSeuilMoyenne();

    /**
     * modifie le seuil minimal de moyenne pour réussir le
     * programme
     * @param seuil
     */
    public void setSeuilMoyenne(float seuil);

    /**
     * retourne la liste de cours obligatoires
     * @return
     */
    public Iterator<Cours> getCoursObligatoires();

    /**
     * ajoute un cours à la liste des cours
     * obligatoires
     * @param unCours
     */
    public void ajouteCoursObligatoire(Cours unCours);

    /**
     * retirer un cours de la liste de cours obligatoires
     * @param unCours
     * @return
     */
    public Cours retirerCoursObligatoire(Cours unCours);

    /**
     * retourner la liste de cours optionnels
     * @return
     */
    public Iterator<Cours> getCoursOptionnels();

    /**
     * Ajouter <code>unCours</code> à la liste de cours
     * optionnels
     * @param unCours
     */
    public void ajouteCoursOptionnel(Cours unCours);

    /**
     * retirer <code>unCours</code> de la liste de 
     * cours optionnels
     * @param unCours
     * @return
     */
    public Cours retirerCoursOptionnel(Cours unCours);

    /**
     * retourne le total du nombre de credits obligatoires
     * @return
     */
    public int getNombreCreditsObligatoires();

    /**
     * retourne le total du nombre de crédits optionnels
     * @return
     */
    public int getNombreCreditsOptionnels();

    /**
     * Cette méthode vérifie si <code>cours</code> est yn cours obligatoire
     * du programme
     * @param cours
     * @return
     */
    public boolean estUnCoursObligatoire(Cours cours);

    /**
     * cette méthode vérifie sur <code>cours</code> est un cours optionnel
     * du programme
     * @param cours
     * @return
     */
    public boolean estUnCoursOptionnel(Cours cours);
}
