package ca.uqam.mgl7010.tp1.types;

public interface InscriptionCours {

    /**
     * Attribue une note pour le cours
     * @param note
     */
    public void setNote(Note note) ;

    public CoursSession getCoursSession() ;

    public Note getNote() ;

    public PersonneEtudiante getPersonneEtudiante() ;
    
}
