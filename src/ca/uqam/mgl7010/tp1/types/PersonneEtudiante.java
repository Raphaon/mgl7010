package ca.uqam.mgl7010.tp1.types;

import java.time.Instant;
import java.util.Iterator;

public interface PersonneEtudiante extends Personne {


    /**
     * retourne le code permanent de la personne
     * @return
     */
    public String getCodePermanent();
    
    /**
     * return la date de sa première inscription
     * @return
     */
    public Instant getDateInscriptionProgrammeActuel() ;

    /**
     * retourne le programme actuel de la personne étudiante
     * @return
     */
    public InscriptionProgramme getInscriptionProgrammeActuel() ;

    /**
     * retourne l'état de compte de la personne étudiante
     * @return
     */
    // public EtatCompte getEtatCompte() ;

    /**
     * inscris la personne étudiante tialise/modifie le programme auquel est actuellement inscrite la 
     * personne étudiante
     * @param programme
     */
    public InscriptionProgramme inscrireAuProgramme(Programme programme) throws ExceptionNonAdmissible;

    /**
     * modifie l'état de compte
     * @param etatCompte
     */
    //public void setEtatCompte(EtatCompte etatCompte) ;
    
    /**
     * Vérifies sur la personne étudiante possède les prérequis pour 
     * le programme passé en paramètre
     * @param programme
     * @return
     */
    public boolean possedePrerequis(Programme programme);


    /**
     * retourne les diplômes détenus par la personne 
     * étudiante
     * @return
     */
    public Iterator<DiplomeObtenu> getDiplomesObtenus();

    /**
     * ajoute un diplôme à la liste des diplômes de la
     * personne étudiante
     * @param diplome
     */
    public void ajouteDiplomeObtenu(DiplomeObtenu diplome);

    /**
     * cette méthode "diplome", du verbe "diplomer",
     * la personne étudiante pour le programme 
     * <code>programme</code>. 
     * @return
     */
    public DiplomeObtenu diplomerProgrammeActuel();

    /**
     * inscrire la personne étudiante au <code>coursSession</code>
     * et retourne l'inscription créée
     * @param coursSession
     * @return
     */
    public InscriptionCours inscrireCours(CoursSession coursSession);

    /**
     * cette méthode annule l'inscription de la personne étudiante
     * au <code>coursSession</code> passé en parametres, et retourne
     * l'inscription correspondante. Si la personne étudiante n'était PAS
     * inscrite au cours, on retourne null
     * @param coursSession
     * @return
     */
    public InscriptionCours annulerInscriptionCours(CoursSession coursSession);

    /**
     * Attribuer <code>note</code> pour le <code>coursSession</code>
     * @param note
     * @param coursSession
     */
    public void attribuerNotePourCours(CoursSession coursSession, Note note);

    /**
     * cette méthode vérifie si la personne étudiante peut diplômer dans son
     * programme actuel, en tenant compte des exigences du programme et des
     * des cours déjà pris par la personne étudiante dans le programme
     * @return
     */
    public boolean peutDiplomerProgrammeActuel();

    /**
     * cette méthode vérifie si la personne étudiante a complété (réussi) tous
     * les cours obligatoires du programme auquel elle est inscrite actuellement
     * @return
     */
    public boolean coursObligatoiresCompletes();
}
