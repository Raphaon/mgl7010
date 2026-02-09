package ca.uqam.mgl7010.tp1.implementations;

import ca.uqam.mgl7010.tp1.types.CoursSession;
import ca.uqam.mgl7010.tp1.types.Diplome;
import ca.uqam.mgl7010.tp1.types.DiplomeObtenu;
import ca.uqam.mgl7010.tp1.types.ExceptionNonAdmissible;
import ca.uqam.mgl7010.tp1.types.InscriptionCours;
import ca.uqam.mgl7010.tp1.types.InscriptionProgramme;
import ca.uqam.mgl7010.tp1.types.Note;
import ca.uqam.mgl7010.tp1.types.PersonneEtudiante;
import ca.uqam.mgl7010.tp1.types.Programme;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class PersonneEtudiantImpl extends PersonneImpl implements PersonneEtudiante {
    private final String codePermanent;
    private final List<DiplomeObtenu> diplomesObtenus = new ArrayList<>();
    private InscriptionProgramme inscriptionProgrammeActuel;

    public PersonneEtudiantImpl(String nom, String prenom, String nas, String codePermanent) {
        super(nom, prenom, nas);
        this.codePermanent = codePermanent;
    }

    @Override
    public String getCodePermanent() {
        return codePermanent;
    }

    @Override
    public Instant getDateInscriptionProgrammeActuel() {
        if (inscriptionProgrammeActuel == null) {
            return null;
        }
        return inscriptionProgrammeActuel.getDateInscription();
    }

    @Override
    public InscriptionProgramme getInscriptionProgrammeActuel() {
        return inscriptionProgrammeActuel;
    }

    @Override
    public InscriptionProgramme inscrireAuProgramme(Programme programme) throws ExceptionNonAdmissible {
        if (!possedePrerequis(programme)) {
            throw new ExceptionNonAdmissible(this, programme);
        }
        inscriptionProgrammeActuel = new InscriptionProgrammeImpl(this, programme);
        return inscriptionProgrammeActuel;
    }

    @Override
    public boolean possedePrerequis(Programme programme) {
        Iterator<Diplome> prerequis = programme.getPrerequis();
        if (!prerequis.hasNext()) {
            return true;
        }

        List<Diplome> prerequisProgramme = new ArrayList<>();
        while (prerequis.hasNext()) {
            prerequisProgramme.add(prerequis.next());
        }

        for (DiplomeObtenu diplomeObtenu : diplomesObtenus) {
            if (prerequisProgramme.contains(diplomeObtenu.diplome())) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Iterator<DiplomeObtenu> getDiplomesObtenus() {
        return diplomesObtenus.iterator();
    }

    @Override
    public void ajouteDiplomeObtenu(DiplomeObtenu diplome) {
        if (diplome != null) {
            diplomesObtenus.add(diplome);
        }
    }

    @Override
    public DiplomeObtenu diplomerProgrammeActuel() {
        if (inscriptionProgrammeActuel == null || !peutDiplomerProgrammeActuel()) {
            return null;
        }
        Instant maintenant = Instant.now();
        inscriptionProgrammeActuel.setDateCompletion(maintenant);
        DiplomeObtenu diplome = new DiplomeObtenu(this,
                inscriptionProgrammeActuel.getProgramme().getDiplome(),
                inscriptionProgrammeActuel.getDateInscription(),
                maintenant);
        diplomesObtenus.add(diplome);
        return diplome;
    }

    @Override
    public InscriptionCours inscrireCours(CoursSession coursSession) {
        if (inscriptionProgrammeActuel == null) {
            return null;
        }
        return inscriptionProgrammeActuel.inscrireCours(coursSession);
    }

    @Override
    public InscriptionCours annulerInscriptionCours(CoursSession coursSession) {
        if (inscriptionProgrammeActuel == null) {
            return null;
        }
        return inscriptionProgrammeActuel.annulerInscriptionCours(coursSession);
    }

    @Override
    public void attribuerNotePourCours(CoursSession coursSession, Note note) {
        if (inscriptionProgrammeActuel == null) {
            return;
        }
        inscriptionProgrammeActuel.attribuerNotePourCours(coursSession, note);
    }

    @Override
    public boolean peutDiplomerProgrammeActuel() {
        return inscriptionProgrammeActuel != null && inscriptionProgrammeActuel.exigencesDuProgrammeCompletees();
    }

    @Override
    public boolean coursObligatoiresCompletes() {
        return inscriptionProgrammeActuel != null && inscriptionProgrammeActuel.coursObligatoiresCompletes();
    }
}
