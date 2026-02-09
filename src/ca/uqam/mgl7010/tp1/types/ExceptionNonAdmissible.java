package ca.uqam.mgl7010.tp1.types;

/**
 * Cette exception est levée quand on essaie d'inscrire une personne étudiante
 * à un programme, et qu'elle n'est pas admissible. Généralement, cela relève de
 * la satisfaction ou non des prérequis, en termes de diplômes obtenus.
 */
public class ExceptionNonAdmissible extends Exception{

    private PersonneEtudiante personneEtudiante;

    private Programme programme;

    private String message;

    public ExceptionNonAdmissible(PersonneEtudiante pe, Programme prog, String message) {
        this(pe,prog);
        this.message = message;
    }

    public ExceptionNonAdmissible(PersonneEtudiante pe, Programme prog) {
        this.personneEtudiante = pe;
        this.programme = prog;
    }

    /**
     * retourne le programme pour lequel
     * une personne étudiante n'est pas
     * admissible
     * @return
     */
    public Programme getProgramme() {
        return programme;
    }

    public PersonneEtudiante getPersonneEtudiante() {
        return personneEtudiante;
    }

    /**
     * retourne le message d'erreur
     */
    public String getMessage() {
        return message;
    }

    /**
     * Modifie le message d'erreur
     * @param message
     */
    public void setMessage(String message) {
        this.message = message;
    };
}
