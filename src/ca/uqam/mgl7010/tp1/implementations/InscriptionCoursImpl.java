package ca.uqam.mgl7010.tp1.implementations;

import ca.uqam.mgl7010.tp1.types.CoursSession;
import ca.uqam.mgl7010.tp1.types.InscriptionCours;
import ca.uqam.mgl7010.tp1.types.Note;
import ca.uqam.mgl7010.tp1.types.PersonneEtudiante;

public class InscriptionCoursImpl implements InscriptionCours {
    private final PersonneEtudiante personneEtudiante;
    private final CoursSession coursSession;
    private Note note;

    public InscriptionCoursImpl(PersonneEtudiante personneEtudiante, CoursSession coursSession) {
        this.personneEtudiante = personneEtudiante;
        this.coursSession = coursSession;
    }

    @Override
    public void setNote(Note note) {
        this.note = note;
    }

    @Override
    public CoursSession getCoursSession() {
        return coursSession;
    }

    @Override
    public Note getNote() {
        return note;
    }

    @Override
    public PersonneEtudiante getPersonneEtudiante() {
        return personneEtudiante;
    }
}
