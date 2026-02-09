package ca.uqam.mgl7010.tp1.types;

import java.time.Instant;
import java.util.Iterator;

/**
 * Interface qui représente l'inscription d'une personne étudiante dans un programme.
 *
 * Par exemple, pour chacun-e parmi vous, il y aurait une instance de InscriptionProgramme
 * pour vous, correspondant à votre dossier étudiant (<code>PersonneEtudiante</code>), et
 * à votyre programme (DESS génie logiciel ou Maitrise en Génie Logiciel).
 */
public interface InscriptionProgramme {

    /**
     * la personne étudiante correspondant à cette inscription
     * @return
     */
    public PersonneEtudiante getPersonneEtudiante();

    /**
     * La date d'inscription au programme
     * @return
     */
    public Instant getDateInscription();

    /**
     * le programme en question
     * @return
     */
    public Programme getProgramme();

    /**
     * la date de complétion du programme
     * @return
     */
    public Instant getDateCompletion();

    /**
     * attribuer/modifier la date de completion du programme
     * @param now
     */
    public void setDateCompletion(Instant now);

    /**
     * le nombre de crédits complétés, à date
     * @return
     */
    public int getCreditsCompletes();

    /**
     * la moyenne cumulative de la personne étudiante, à date. Elle est basée sur
     * les notes aux cours obligatoires, et les notes aux cours optionnels.
     *
     * Ici, on regarde tous les cours optionnels, même si la personne étudiante
     * en prend plus qu'il n'en faut. Nous corrigerons cela au TP2
     * @return
     */
    public float getMoyenneCumulative();

    /**
     * Cette méthode inscrit la personne étudiante au <code>coursSession</code>,
     * dans le cadre du programme en question.
     *
     * Pour un programme donné, on gardera une seule inscription par cours, même si la
     * personne refait le cours pour remonter sa note.
     *
     * Si une personne s'inscrit plusieurs fois au même cours, mais à différentes sessions,
     * chaque inscription écrasera la précédente. On révisera cette hypothèse dans le TP2
     * @param coursSession
     * @return
     */
    public InscriptionCours inscrireCours(CoursSession coursSession);

    /**
     * Cette méthode annule l'inscription de la personne étudiante au 
     * <code>coursSession</code>, dans le cadre du programme en question. 
     * Cela revient à retirer l'inscription au cours en question.
     * 
     * Si la personne étudiante n'était pas inscrite, on retourne null
     * 
     * @param coursSession
     * @return
     */
    public InscriptionCours annulerInscriptionCours(CoursSession coursSession);

    /**
     * cette méthode retourne la liste des inscriptions aux cours du programme
     * @return
     */
    public Iterator<InscriptionCours> getInscriptionsCours();

    /**
     * Cette méthode attribue <code>note</code> au <code>coursSession</code> pris
     * par la personne étudiante dans le cadre du programme
     * @param courseSession
     * @param note
     */
    public void attribuerNotePourCours(CoursSession courseSession, Note note);

    /**
     * cette méthode vérifie si la personne étudiante a rempli les exigences académiques
     * du programme. Cela veut dire:
     * 1) tous les cours obligatoires ont été réussis
     * 2) le total du nombre de crédits réussis parmi les cours obligatoires et optionnels
     * est supérieur ou égal au nombre de crédits du programme
     * 3) la moyenne obtenue est supérieure ou égale au seuil minimal exigé
     * @return
     */
    public boolean exigencesDuProgrammeCompletees();

    /**
     * Cette méthode vérifie si la personne étudiante a réussi tous les cours 
     * obligatoires du programme.
     * @return
     */
    public boolean coursObligatoiresCompletes();
}
