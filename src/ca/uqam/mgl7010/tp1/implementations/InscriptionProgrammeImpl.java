package ca.uqam.mgl7010.tp1.implementations;

import ca.uqam.mgl7010.tp1.types.Cours;
import ca.uqam.mgl7010.tp1.types.CoursSession;
import ca.uqam.mgl7010.tp1.types.InscriptionCours;
import ca.uqam.mgl7010.tp1.types.InscriptionProgramme;
import ca.uqam.mgl7010.tp1.types.Note;
import ca.uqam.mgl7010.tp1.types.PersonneEtudiante;
import ca.uqam.mgl7010.tp1.types.Programme;

import java.time.Instant;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class InscriptionProgrammeImpl implements InscriptionProgramme {
    private final PersonneEtudiante personneEtudiante;
    private final Programme programme;
    private final Instant dateInscription;
    private Instant dateCompletion;
    private final Map<String, InscriptionCours> inscriptionsParSigleCours = new HashMap<>();

    public InscriptionProgrammeImpl(PersonneEtudiante personneEtudiante, Programme programme) {
        this.personneEtudiante = personneEtudiante;
        this.programme = programme;
        this.dateInscription = Instant.now();
    }

    @Override
    public PersonneEtudiante getPersonneEtudiante() {
        return personneEtudiante;
    }

    @Override
    public Instant getDateInscription() {
        return dateInscription;
    }

    @Override
    public Programme getProgramme() {
        return programme;
    }

    @Override
    public Instant getDateCompletion() {
        return dateCompletion;
    }

    @Override
    public void setDateCompletion(Instant now) {
        this.dateCompletion = now;
    }

    @Override
    public int getCreditsCompletes() {
        int credits = 0;
        for (InscriptionCours inscription : inscriptionsParSigleCours.values()) {
            if (inscription.getNote() == null) {
                continue;
            }
            Cours cours = inscription.getCoursSession().cours();
            if (coursDansProgramme(cours) && inscription.getNote().noteLettree().equivalentNumerique() > 0f) {
                credits += cours.getNombreCredits();
            }
        }
        return credits;
    }

    @Override
    public float getMoyenneCumulative() {
        float somme = 0f;
        int totalCours = 0;
        for (InscriptionCours inscription : inscriptionsParSigleCours.values()) {
            if (inscription.getNote() == null) {
                continue;
            }
            Cours cours = inscription.getCoursSession().cours();
            if (coursDansProgramme(cours)) {
                somme += inscription.getNote().noteLettree().equivalentNumerique();
                totalCours++;
            }
        }
        if (totalCours == 0) {
            return 0f;
        }
        return somme / totalCours;
    }

    @Override
    public InscriptionCours inscrireCours(CoursSession coursSession) {
        if (coursSession == null || coursSession.cours() == null) {
            return null;
        }
        InscriptionCours inscription = new InscriptionCoursImpl(personneEtudiante, coursSession);
        inscriptionsParSigleCours.put(coursSession.cours().getSigle(), inscription);
        return inscription;
    }

    @Override
    public InscriptionCours annulerInscriptionCours(CoursSession coursSession) {
        if (coursSession == null || coursSession.cours() == null) {
            return null;
        }
        return inscriptionsParSigleCours.remove(coursSession.cours().getSigle());
    }

    @Override
    public Iterator<InscriptionCours> getInscriptionsCours() {
        return inscriptionsParSigleCours.values().iterator();
    }

    @Override
    public void attribuerNotePourCours(CoursSession courseSession, Note note) {
        if (courseSession == null || courseSession.cours() == null) {
            return;
        }
        InscriptionCours inscription = inscriptionsParSigleCours.get(courseSession.cours().getSigle());
        if (inscription != null) {
            inscription.setNote(note);
        }
    }

    @Override
    public boolean exigencesDuProgrammeCompletees() {
        return coursObligatoiresCompletes()
                && getCreditsCompletes() >= programme.getNombreDeCredits()
                && getMoyenneCumulative() >= programme.getSeuilMoyenne();
    }

    @Override
    public boolean coursObligatoiresCompletes() {
        Iterator<Cours> obligatoires = programme.getCoursObligatoires();
        while (obligatoires.hasNext()) {
            Cours coursObligatoire = obligatoires.next();
            if (coursObligatoire == null) {
                continue;
            }
            InscriptionCours inscription = inscriptionsParSigleCours.get(coursObligatoire.getSigle());
            if (inscription == null || inscription.getNote() == null
                    || inscription.getNote().noteLettree().equivalentNumerique() <= 0f) {
                return false;
            }
        }
        return true;
    }

    private boolean coursDansProgramme(Cours cours) {
        return programme.estUnCoursObligatoire(cours) || programme.estUnCoursOptionnel(cours);
    }
}
