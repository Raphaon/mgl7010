package ca.uqam.mgl7010.tp1.tests;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.Instant;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

import ca.uqam.mgl7010.tp1.types.Cours;
import ca.uqam.mgl7010.tp1.types.CoursSession;
import ca.uqam.mgl7010.tp1.types.Diplome;
import ca.uqam.mgl7010.tp1.types.DiplomeObtenu;
import ca.uqam.mgl7010.tp1.types.ExceptionNonAdmissible;
import ca.uqam.mgl7010.tp1.types.Grade;
import ca.uqam.mgl7010.tp1.types.Note;
import ca.uqam.mgl7010.tp1.types.NoteLettree;
import ca.uqam.mgl7010.tp1.types.PersonneEtudiante;
import ca.uqam.mgl7010.tp1.types.Programme;
import ca.uqam.mgl7010.tp1.types.Session;
import ca.uqam.mgl7010.tp1.types.Specialite;

public class TesterCreationPersonnesEtudiantes {

    @BeforeAll
    public static void initialiserDonnees() {
        CreationDonneesReferenceTests.creerDonneesReference();
    }

    @Test
    public void testerCreationCours() {

        Cours unCours = CreationDonneesReferenceTests.getCoursParSigle("MGL7030");
        assertNotNull(unCours);
        assertEquals("MGL7030", unCours.getSigle(),"Le cours n'a pas le bon sigle");
        assertEquals(3, unCours.getNombreCredits(),"Le cours n'a pas le bon nombre de crédits");
    }

    @Test
    public void testerCreationCoursSession() {
        CoursSession mgl7010_automne2024 = CreationDonneesReferenceTests.getCoursSessionParSigle("243-MGL7010");
        assertNotNull(mgl7010_automne2024);
        assertEquals(Session.Automne2024, mgl7010_automne2024.session(), "Le coursSession n'a pas la bonne session");
        assertEquals(CreationDonneesReferenceTests.getCoursParSigle("MGL7010"), mgl7010_automne2024.cours(), "le cours session n'a pas le bon cours");
    }

    @Test
    public void testerCreationProgrammes() {
        Programme mgl = CreationDonneesReferenceTests.getProgrammeParNom(CreationDonneesReferenceTests.nomMGL);
        assertNotNull(mgl);
        assertEquals(CreationDonneesReferenceTests.nomMGL, mgl.getNom(),"Le programme n'a pas le nom attendu (" + CreationDonneesReferenceTests.nomMGL + ")");
        assertEquals(30, mgl.getNombreDeCredits());
        assertEquals(18, mgl.getNombreCreditsObligatoires());
        assertTrue(mgl.estUnCoursObligatoire(CreationDonneesReferenceTests.getCoursParSigle("MGL7460")));
    }

    @Test
    public void testerCreationPersonneEtudiante() {
        PersonneEtudiante lamia = CreationDonneesReferenceTests.getPersonneEtudianteParCodePermanent("SOLL11223344");
        assertNotNull(lamia);

        DiplomeObtenu diplomeObtenuParLamia = lamia.getDiplomesObtenus().next();
        assertEquals(new Diplome(Grade.Baccalaureat,Specialite.GénieBiomédical), diplomeObtenuParLamia.diplome());
        System.out.println(lamia);
    }

    @Test
    public void testerInscriptionProgrammeReussie() {
        // 1.   Chercher lamia
        PersonneEtudiante lamia = CreationDonneesReferenceTests.getPersonneEtudianteParCodePermanent("SOLL11223344");

        // 2.   L'inscrire à la MGL
        Programme mgl = CreationDonneesReferenceTests.getProgrammeParNom(CreationDonneesReferenceTests.nomMGL);
        try {
            lamia.inscrireAuProgramme(mgl);
        } catch (ExceptionNonAdmissible e) {
            throw new RuntimeException(e);
        }

        assertEquals(mgl, lamia.getInscriptionProgrammeActuel().getProgramme());
    }

    @Test
    public void testerInscriptionProgrammeEchoue() {
        PersonneEtudiante hafedh = CreationDonneesReferenceTests.getPersonneEtudianteParCodePermanent("MILH11223344");
        Programme mgl = CreationDonneesReferenceTests.getProgrammeParNom(CreationDonneesReferenceTests.nomMGL);
        assertThrows(ExceptionNonAdmissible.class, () -> {hafedh.inscrireAuProgramme(mgl);});
    }

    @Test
    public void testerInscriptionsCoursDuProgramme() {
        // 1.   Lamia
        PersonneEtudiante lamia = CreationDonneesReferenceTests.getPersonneEtudianteParCodePermanent("SOLL11223344");

        // 2.   L'inscrire à la MGL
        try {
            lamia.inscrireAuProgramme(CreationDonneesReferenceTests.getProgrammeParNom(CreationDonneesReferenceTests.nomMGL));
        } catch (ExceptionNonAdmissible e) {
            throw new RuntimeException(e);
        }

        // 3.   L'inscrire à des cours
        CoursSession mgl7010_automne2024 = CreationDonneesReferenceTests.getCoursSessionParSigle("243-MGL7010");

        CoursSession mgl7020_automne2024 = CreationDonneesReferenceTests.getCoursSessionParSigle("243-MGL7020");

        lamia.inscrireCours(mgl7010_automne2024);
        lamia.attribuerNotePourCours(mgl7010_automne2024, new Note(95f, NoteLettree.A_plus));

        lamia.inscrireCours(mgl7020_automne2024);
        lamia.attribuerNotePourCours(mgl7020_automne2024, new Note(82f, NoteLettree.A_moins));

        // 4.   Obtenir credits complétés
        assertEquals(6, lamia.getInscriptionProgrammeActuel().getCreditsCompletes());

        // 5.   Obtenir moyenne cumulative
        assertEquals(4f, lamia.getInscriptionProgrammeActuel().getMoyenneCumulative());
    }
    
    @Test
    public void testerAnnulerInscriptionsCoursDuProgramme() {
        // 1.   Lamia
        PersonneEtudiante lamia = CreationDonneesReferenceTests.getPersonneEtudianteParCodePermanent("SOLL11223344");

        // 2.   L'inscrire à la MGL
        try {
            lamia.inscrireAuProgramme(CreationDonneesReferenceTests.getProgrammeParNom(CreationDonneesReferenceTests.nomMGL));
        } catch (ExceptionNonAdmissible e) {
            throw new RuntimeException(e);
        }

        // 3.   L'inscrire à des cours
        CoursSession mgl7010_automne2024 = CreationDonneesReferenceTests.getCoursSessionParSigle("243-MGL7010");

        CoursSession mgl7020_automne2024 = CreationDonneesReferenceTests.getCoursSessionParSigle("243-MGL7020");

        lamia.inscrireCours(mgl7010_automne2024);
        lamia.attribuerNotePourCours(mgl7010_automne2024, new Note(85f, NoteLettree.A));

        lamia.inscrireCours(mgl7020_automne2024);
        lamia.attribuerNotePourCours(mgl7020_automne2024, new Note(42f, NoteLettree.E));

        // 4.   Obtenir credits complétés
        assertEquals(3, lamia.getInscriptionProgrammeActuel().getCreditsCompletes());

        // 5.   Obtenir moyenne cumulative
        assertEquals(2f, lamia.getInscriptionProgrammeActuel().getMoyenneCumulative());

        // 6.   Annuler cours
        lamia.annulerInscriptionCours(mgl7020_automne2024);
        
        // 7.   Vérifier credits completes
        assertEquals(3, lamia.getInscriptionProgrammeActuel().getCreditsCompletes());

        // 8.   Vérifier moyenne
        assertEquals(4f, lamia.getInscriptionProgrammeActuel().getMoyenneCumulative());
    }

    @Test
    public void testerInscriptionsCoursHorsProgramme() {
        // 1.   Mouhamadou
        PersonneEtudiante mouhamadou = CreationDonneesReferenceTests.getPersonneEtudianteParCodePermanent("BEMM11223344");

        // 2.   L'inscrire à la MGL
        try {
            mouhamadou.inscrireAuProgramme(CreationDonneesReferenceTests.getProgrammeParNom(CreationDonneesReferenceTests.nomDESSGL));
        } catch (ExceptionNonAdmissible e) {
            throw new RuntimeException(e);
        }

        // 3.   L'inscrire à des cours
        CoursSession mgl7010_automne2024 = CreationDonneesReferenceTests.getCoursSessionParSigle("243-MGL7010");
        assertNotNull(mgl7010_automne2024);

        CoursSession mgl7020_automne2024 = CreationDonneesReferenceTests.getCoursSessionParSigle("243-MGL7020");
        assertNotNull(mgl7020_automne2024);

        CoursSession mgl7320_automne2024 = CreationDonneesReferenceTests.getCoursSessionParSigle("243-MGL7320");
        assertNotNull(mgl7320_automne2024);

        CoursSession mgl7315_hiver2025 = CreationDonneesReferenceTests.getCoursSessionParSigle("251-MGL7315");
        assertNotNull(mgl7315_hiver2025);

        CoursSession mgl7230_hiver2025 = CreationDonneesReferenceTests.getCoursSessionParSigle("251-MGL7230");
        assertNotNull(mgl7230_hiver2025);

        CoursSession mgl7560_hiver2025 = CreationDonneesReferenceTests.getCoursSessionParSigle("251-MGL7560");
        assertNotNull(mgl7560_hiver2025);

        mouhamadou.inscrireCours(mgl7010_automne2024);
        mouhamadou.attribuerNotePourCours(mgl7010_automne2024, new Note(95f, NoteLettree.A_plus));

        mouhamadou.inscrireCours(mgl7020_automne2024);
        mouhamadou.attribuerNotePourCours(mgl7020_automne2024, new Note(87f, NoteLettree.A));

        mouhamadou.inscrireCours(mgl7320_automne2024);
        mouhamadou.attribuerNotePourCours(mgl7320_automne2024, new Note(81f, NoteLettree.A_moins));

        mouhamadou.inscrireCours(mgl7230_hiver2025);
        mouhamadou.attribuerNotePourCours(mgl7230_hiver2025, new Note(79f, NoteLettree.B_plus));

        mouhamadou.inscrireCours(mgl7315_hiver2025);
        mouhamadou.attribuerNotePourCours(mgl7315_hiver2025, new Note(74f, NoteLettree.B));
        
        mouhamadou.inscrireCours(mgl7560_hiver2025);
        mouhamadou.attribuerNotePourCours(mgl7560_hiver2025, new Note(69.5f, NoteLettree.B_moins));

        assertEquals(15, mouhamadou.getInscriptionProgrammeActuel().getCreditsCompletes());
    }

    @Test
    public void testerPeutDiplomerOui() {
        // 1.   Mouhamadou
        PersonneEtudiante mouhamadou = CreationDonneesReferenceTests.getPersonneEtudianteParCodePermanent("BEMM11223344");

        // 2.   L'inscrire à la MGL
        try {
            mouhamadou.inscrireAuProgramme(CreationDonneesReferenceTests.getProgrammeParNom(CreationDonneesReferenceTests.nomDESSGL));
        } catch (ExceptionNonAdmissible e) {
            throw new RuntimeException(e);
        }

        // 3.   L'inscrire à des cours
        // 3.a. Cours obligatoires
        // 3.a.1    MGL7010
        CoursSession mgl7010_automne2024 = CreationDonneesReferenceTests.getCoursSessionParSigle("243-MGL7010");
        assertNotNull(mgl7010_automne2024);
        mouhamadou.inscrireCours(mgl7010_automne2024);
        mouhamadou.attribuerNotePourCours(mgl7010_automne2024, new Note(95f, NoteLettree.A_plus));

        // 3.a.2    MGL7260
        CoursSession mgl7260_hiver2025 = CreationDonneesReferenceTests.getCoursSessionParSigle("251-MGL7260");
        assertNotNull(mgl7260_hiver2025);
        mouhamadou.inscrireCours(mgl7260_hiver2025);
        mouhamadou.attribuerNotePourCours(mgl7260_hiver2025, new Note(89f,NoteLettree.A));

        // 3.a.3    MGL7315
        CoursSession mgl7315_hiver2025 = CreationDonneesReferenceTests.getCoursSessionParSigle("251-MGL7315");
        assertNotNull(mgl7315_hiver2025);
        mouhamadou.inscrireCours(mgl7315_hiver2025);
        mouhamadou.attribuerNotePourCours(mgl7315_hiver2025, new Note(74f, NoteLettree.B));

        // 3.a.4    MGL7361
        CoursSession mgl7361_automne2025 = CreationDonneesReferenceTests.getCoursSessionParSigle("253-MGL7361");
        assertNotNull(mgl7361_automne2025);
        mouhamadou.inscrireCours(mgl7361_automne2025);
        mouhamadou.attribuerNotePourCours(mgl7361_automne2025, new Note(79f,NoteLettree.B_plus));
       
        // 3.a.5    MGL7460
        CoursSession mgl7460_automne2025 = CreationDonneesReferenceTests.getCoursSessionParSigle("253-MGL7460");
        assertNotNull(mgl7460_automne2025);
        mouhamadou.inscrireCours(mgl7460_automne2025);
        mouhamadou.attribuerNotePourCours(mgl7460_automne2025, new Note(86.5f,NoteLettree.A));

        // 3.b  Cours optionnels
        // 3.b.1    MGL7020
        CoursSession mgl7020_automne2024 = CreationDonneesReferenceTests.getCoursSessionParSigle("243-MGL7020");
        assertNotNull(mgl7020_automne2024);
        mouhamadou.inscrireCours(mgl7020_automne2024);
        mouhamadou.attribuerNotePourCours(mgl7020_automne2024, new Note(92f,NoteLettree.A_moins));

        // 3.b.2    MGL7030
        CoursSession mgl7030_automne2024 = CreationDonneesReferenceTests.getCoursSessionParSigle("243-MGL7030");
        assertNotNull(mgl7030_automne2024);
        mouhamadou.inscrireCours(mgl7030_automne2024);
        mouhamadou.attribuerNotePourCours(mgl7030_automne2024, new Note(81f, NoteLettree.A_moins));

        // 3.b.3    MGL7560
        CoursSession mgl7560_hiver2025 = CreationDonneesReferenceTests.getCoursSessionParSigle("251-MGL7560");
        assertNotNull(mgl7560_hiver2025);
        mouhamadou.inscrireCours(mgl7560_hiver2025);
        mouhamadou.attribuerNotePourCours(mgl7560_hiver2025, new Note(69.5f, NoteLettree.B_moins));

        assertEquals(24, mouhamadou.getInscriptionProgrammeActuel().getCreditsCompletes());

        assertTrue(mouhamadou.peutDiplomerProgrammeActuel());
    }

    @Test
    public void testerPeutDiplomerNonCreditsOKCoursObligatoiresNon() {
        // 1.   Mouhamadou
        PersonneEtudiante mouhamadou = CreationDonneesReferenceTests.getPersonneEtudianteParCodePermanent("BEMM11223344");

        // 2.   L'inscrire à la MGL
        try {
            mouhamadou.inscrireAuProgramme(CreationDonneesReferenceTests.getProgrammeParNom(CreationDonneesReferenceTests.nomDESSGL));
        } catch (ExceptionNonAdmissible e) {
            throw new RuntimeException(e);
        }

        // 3.   L'inscrire à des cours
        // 3.a. Cours obligatoires
        // 3.a.1    MGL7010
        CoursSession mgl7010_automne2024 = CreationDonneesReferenceTests.getCoursSessionParSigle("243-MGL7010");
        assertNotNull(mgl7010_automne2024);
        mouhamadou.inscrireCours(mgl7010_automne2024);
        mouhamadou.attribuerNotePourCours(mgl7010_automne2024, new Note(95f, NoteLettree.A_plus));

        // 3.a.3    MGL7315
        CoursSession mgl7315_hiver2025 = CreationDonneesReferenceTests.getCoursSessionParSigle("251-MGL7315");
        assertNotNull(mgl7315_hiver2025);
        mouhamadou.inscrireCours(mgl7315_hiver2025);
        mouhamadou.attribuerNotePourCours(mgl7315_hiver2025, new Note(74f, NoteLettree.B));

        // 3.a.4    MGL7361
        CoursSession mgl7361_automne2025 = CreationDonneesReferenceTests.getCoursSessionParSigle("253-MGL7361");
        assertNotNull(mgl7361_automne2025);
        mouhamadou.inscrireCours(mgl7361_automne2025);
        mouhamadou.attribuerNotePourCours(mgl7361_automne2025, new Note(79f,NoteLettree.B_plus));
       
        // 3.a.5    MGL7460
        CoursSession mgl7460_automne2025 = CreationDonneesReferenceTests.getCoursSessionParSigle("253-MGL7460");
        assertNotNull(mgl7460_automne2025);
        mouhamadou.inscrireCours(mgl7460_automne2025);
        mouhamadou.attribuerNotePourCours(mgl7460_automne2025, new Note(86.5f,NoteLettree.A));

        // 3.b  Cours optionnels
        // 3.b.1    MGL7020
        CoursSession mgl7020_automne2024 = CreationDonneesReferenceTests.getCoursSessionParSigle("243-MGL7020");
        assertNotNull(mgl7020_automne2024);
        mouhamadou.inscrireCours(mgl7020_automne2024);
        mouhamadou.attribuerNotePourCours(mgl7020_automne2024, new Note(92f,NoteLettree.A_moins));

        // 3.b.2    MGL7030
        CoursSession mgl7030_automne2024 = CreationDonneesReferenceTests.getCoursSessionParSigle("243-MGL7030");
        assertNotNull(mgl7030_automne2024);
        mouhamadou.inscrireCours(mgl7030_automne2024);
        mouhamadou.attribuerNotePourCours(mgl7030_automne2024, new Note(81f, NoteLettree.A_moins));

        // 3.b.3    MGL7230
        CoursSession mgl7230_hiver2025 = CreationDonneesReferenceTests.getCoursSessionParSigle("251-MGL7230");
        assertNotNull(mgl7230_hiver2025);
        mouhamadou.inscrireCours(mgl7230_hiver2025);
        mouhamadou.attribuerNotePourCours(mgl7230_hiver2025, new Note(89f,NoteLettree.A));

        // 3.b.4    MGL7560
        CoursSession mgl7560_hiver2025 = CreationDonneesReferenceTests.getCoursSessionParSigle("251-MGL7560");
        assertNotNull(mgl7560_hiver2025);
        mouhamadou.inscrireCours(mgl7560_hiver2025);
        mouhamadou.attribuerNotePourCours(mgl7560_hiver2025, new Note(69.5f, NoteLettree.B_moins));

        assertEquals(24, mouhamadou.getInscriptionProgrammeActuel().getCreditsCompletes());

        assertFalse(mouhamadou.peutDiplomerProgrammeActuel());
    }

    @Test
    public void testerPeutDiplomerNon() {
        // 1.   Mouhamadou
        PersonneEtudiante lamia = CreationDonneesReferenceTests.getPersonneEtudianteParCodePermanent("SOLL11223344");

        // 2.   L'inscrire à la MGL
        try {
            lamia.inscrireAuProgramme(CreationDonneesReferenceTests.getProgrammeParNom(CreationDonneesReferenceTests.nomMGL));
        } catch (ExceptionNonAdmissible e) {
            throw new RuntimeException(e);
        }

        // 3.   L'inscrire à des cours
        CoursSession mgl7010_automne2024 = CreationDonneesReferenceTests.getCoursSessionParSigle("243-MGL7010");
        assertNotNull(mgl7010_automne2024);

        CoursSession mgl7020_automne2024 = CreationDonneesReferenceTests.getCoursSessionParSigle("243-MGL7020");
        assertNotNull(mgl7020_automne2024);

        CoursSession mgl7030_automne2024 = CreationDonneesReferenceTests.getCoursSessionParSigle("243-MGL7030");
        assertNotNull(mgl7030_automne2024);

        CoursSession mgl7315_hiver2025 = CreationDonneesReferenceTests.getCoursSessionParSigle("251-MGL7315");
        assertNotNull(mgl7315_hiver2025);

        CoursSession mgl7230_hiver2025 = CreationDonneesReferenceTests.getCoursSessionParSigle("251-MGL7230");
        assertNotNull(mgl7230_hiver2025);

        CoursSession mgl7560_hiver2025 = CreationDonneesReferenceTests.getCoursSessionParSigle("251-MGL7560");
        assertNotNull(mgl7560_hiver2025);

        lamia.inscrireCours(mgl7010_automne2024);
        lamia.attribuerNotePourCours(mgl7010_automne2024, new Note(95f, NoteLettree.A_plus));

        lamia.inscrireCours(mgl7020_automne2024);
        lamia.attribuerNotePourCours(mgl7020_automne2024, new Note(87f, NoteLettree.A));

        lamia.inscrireCours(mgl7030_automne2024);
        lamia.attribuerNotePourCours(mgl7030_automne2024, new Note(81f, NoteLettree.A_moins));

        lamia.inscrireCours(mgl7230_hiver2025);
        lamia.attribuerNotePourCours(mgl7230_hiver2025, new Note(79f, NoteLettree.B_plus));

        lamia.inscrireCours(mgl7315_hiver2025);
        lamia.attribuerNotePourCours(mgl7315_hiver2025, new Note(74f, NoteLettree.B));
        
        lamia.inscrireCours(mgl7560_hiver2025);
        lamia.attribuerNotePourCours(mgl7560_hiver2025, new Note(69.5f, NoteLettree.B_moins));

        assertEquals(18, lamia.getInscriptionProgrammeActuel().getCreditsCompletes());

        assertFalse(lamia.peutDiplomerProgrammeActuel());
    }

}
