package ca.uqam.mgl7010.tp1.tests;

import ca.uqam.mgl7010.tp1.implementations.CoursImpl;
import ca.uqam.mgl7010.tp1.implementations.PersonneEtudiantImpl;
import ca.uqam.mgl7010.tp1.implementations.ProgrammeImpl;
import ca.uqam.mgl7010.tp1.types.*;

import java.time.Instant;
import java.util.HashMap;

public class CreationDonneesReferenceTests {

    private static HashMap<String, PersonneEtudiante> personnesEtudiantes = new HashMap<>();

    private static HashMap<String, Programme> catalogueProgrammes = new HashMap<>();

    private static HashMap<String, Cours> catalogueCours = new HashMap<>();

    private static HashMap<String,CoursSession> offreCours = new HashMap<>();

    public static String titreMGL7010 = "Programmation et Conception Détaillée Orientées Objet";
    public static String titreMGL7020 = "Fondements et notations du génie logiciel";
    public static String titreMGL7030 = "Développement d'applications Web trois tiers";
    public static String titreMGL7130 = "Développement d'applications mobiles";
    public static String titreMGL7230 = "Tests logiciels";
    public static String titreMGL7260 = "Exigences et spécifications de systèmes logiciels";
    public static String titreMGL7315 = "Gestion de projet en génie logiciel";
    public static String titreMGL7320 = "Ingénierie logicielle de systèmes d'intelligence artificielle";
    public static String titreMGL7361 = "Principes et applications de la conception de logiciels";
    public static String titreMGL7460 = "Réalisation et maintenance de logiciels";
    public static String titreMGL7560 = "Vérification et assurance qualité de logiciels";
    public static String titreMGL7760 = "Qualité et productivité des outils logiciels";

    public static String nomDESSGL = "DESS en Génie Logiciel";
    public static String nomMGL = "Maitrise en Génie Logiciel";

    public static String prefixeDescription = "C'est un cours de ";


    private static void creerPersonnesEtudiantes() {
        PersonneEtudiante lamia = new PersonneEtudiantImpl("Soltani", "Lamia", "123456789","SOLL11223344");
        DiplomeObtenu diplomeObtenuLamia = new DiplomeObtenu(lamia, new Diplome(Grade.Baccalaureat,Specialite.GénieBiomédical), Instant.parse("2021-09-04T00:00:00.00Z"),Instant.parse("2025-05-31T00:00:00.00Z"));
        lamia.ajouteDiplomeObtenu(diplomeObtenuLamia);
        personnesEtudiantes.put(lamia.getCodePermanent(), lamia);

        PersonneEtudiante mouhamadou = new PersonneEtudiantImpl("Bempa", "Mouhamadou", "223456789", "BEMM11223344");
        DiplomeObtenu diplomeObtenuMouhamadou = new DiplomeObtenu(mouhamadou,new Diplome(Grade.Baccalaureat,Specialite.GénieLogiciel), Instant.parse("2020-09-04T00:00:00.00Z"),Instant.parse("2024-04-30T00:00:00.00Z"));
        mouhamadou.ajouteDiplomeObtenu(diplomeObtenuMouhamadou);
        personnesEtudiantes.put(mouhamadou.getCodePermanent(), mouhamadou);

        PersonneEtudiante hafedh = new PersonneEtudiantImpl("Mili","Hafedh", "323456789","MILH11223344");
        DiplomeObtenu diplomeObtenuHafedh = new DiplomeObtenu(hafedh, new Diplome(Grade.Baccalaureat, Specialite.MathématiquesAppliquées),Instant.parse("1981-09-04T00:00:00.00Z"),Instant.parse("1984-06-30T00:00:00.00Z"));
        hafedh.ajouteDiplomeObtenu(diplomeObtenuHafedh);
        personnesEtudiantes.put(hafedh.getCodePermanent(), hafedh);

    }

    private static void creerCours() {
        // 1. Créér les cours
        Cours mgl1010 = new CoursImpl("MGL7010",titreMGL7010,3,prefixeDescription + titreMGL7010);
        Cours mgl7020 = new CoursImpl("MGL7020",titreMGL7020,3,prefixeDescription + titreMGL7020);
        Cours mgl7030 = new CoursImpl("MGL7030",titreMGL7030,3,prefixeDescription + titreMGL7030);
        Cours mgl7130 = new CoursImpl("MGL7130",titreMGL7130,3,prefixeDescription + titreMGL7130);
        Cours mgl7230 = new CoursImpl("MGL7230",titreMGL7230,3,prefixeDescription + titreMGL7230);
        Cours mgl7260 = new CoursImpl("MGL7260",titreMGL7260,3,prefixeDescription + titreMGL7260);
        Cours mgl7315 = new CoursImpl("MGL7315",titreMGL7315,3,prefixeDescription + titreMGL7315);
        Cours mgl7320 = new CoursImpl("MGL7320",titreMGL7320,3,prefixeDescription + titreMGL7320);
        Cours mgl7361 = new CoursImpl("MGL7361",titreMGL7361,3,prefixeDescription + titreMGL7361);
        Cours mgl7460 = new CoursImpl("MGL7460",titreMGL7460,3,prefixeDescription + titreMGL7460);
        Cours mgl7560 = new CoursImpl("MGL7560",titreMGL7560,3,prefixeDescription + titreMGL7560);
        Cours mgl7760 = new CoursImpl("MGL7760",titreMGL7760,3,prefixeDescription + titreMGL7760);

        // 2. Les ajouter au catalogue
        catalogueCours.put(mgl1010.getSigle(),mgl1010);
        catalogueCours.put(mgl7020.getSigle(),mgl7020);
        catalogueCours.put(mgl7030.getSigle(),mgl7030);
        catalogueCours.put(mgl7130.getSigle(),mgl7130);
        catalogueCours.put(mgl7230.getSigle(),mgl7230);
        catalogueCours.put(mgl7260.getSigle(),mgl7260);
        catalogueCours.put(mgl7315.getSigle(),mgl7315);
        catalogueCours.put(mgl7320.getSigle(),mgl7320);
        catalogueCours.put(mgl7361.getSigle(),mgl7361);
        catalogueCours.put(mgl7460.getSigle(),mgl7460);
        catalogueCours.put(mgl7560.getSigle(),mgl7560);
        catalogueCours.put(mgl7760.getSigle(),mgl7760);
    }

    private static void creerCoursSessions() {

        // Automne 2024
        CoursSession _243_MGL7010 = new CoursSession(CreationDonneesReferenceTests.getCoursParSigle("MGL7010"),Session.Automne2024);
        offreCours.put(_243_MGL7010.toString(),_243_MGL7010);

        CoursSession _243_MGL7020 = new CoursSession(CreationDonneesReferenceTests.getCoursParSigle("MGL7020"),Session.Automne2024);
        offreCours.put(_243_MGL7020.toString(),_243_MGL7020);

        CoursSession _243_MGL7030 = new CoursSession(CreationDonneesReferenceTests.getCoursParSigle("MGL7030"),Session.Automne2024);
        offreCours.put(_243_MGL7030.toString(),_243_MGL7030);

        CoursSession _243_MGL7361 = new CoursSession(CreationDonneesReferenceTests.getCoursParSigle("MGL7361"),Session.Automne2024);
        offreCours.put(_243_MGL7361.toString(),_243_MGL7361);

        CoursSession _243_MGL7315 = new CoursSession(CreationDonneesReferenceTests.getCoursParSigle("MGL7315"),Session.Automne2024);
        offreCours.put(_243_MGL7315.toString(),_243_MGL7315);

        CoursSession _243_MGL7320 = new CoursSession(CreationDonneesReferenceTests.getCoursParSigle("MGL7320"),Session.Automne2024);
        offreCours.put(_243_MGL7320.toString(),_243_MGL7320);

        CoursSession _243_MGL7460 = new CoursSession(CreationDonneesReferenceTests.getCoursParSigle("MGL7460"),Session.Automne2024);
        offreCours.put(_243_MGL7460.toString(),_243_MGL7460);

        // Hiver 2025
        CoursSession _251_MGL7010 = new CoursSession(CreationDonneesReferenceTests.getCoursParSigle("MGL7010"),Session.Hiver2025);
        offreCours.put(_251_MGL7010.toString(),_251_MGL7010);

        CoursSession _251_MGL7020 = new CoursSession(CreationDonneesReferenceTests.getCoursParSigle("MGL7020"),Session.Hiver2025);
        offreCours.put(_251_MGL7020.toString(),_251_MGL7020);

        CoursSession _251_MGL7030 = new CoursSession(CreationDonneesReferenceTests.getCoursParSigle("MGL7030"),Session.Hiver2025);
        offreCours.put(_251_MGL7030.toString(),_251_MGL7030);

        CoursSession _251_MGL7130 = new CoursSession(CreationDonneesReferenceTests.getCoursParSigle("MGL7130"),Session.Hiver2025);
        offreCours.put(_251_MGL7130.toString(),_251_MGL7130);

        CoursSession _251_MGL7230 = new CoursSession(CreationDonneesReferenceTests.getCoursParSigle("MGL7230"),Session.Hiver2025);
        offreCours.put(_251_MGL7230.toString(),_251_MGL7230);

        CoursSession _251_MGL7260 = new CoursSession(CreationDonneesReferenceTests.getCoursParSigle("MGL7260"),Session.Hiver2025);
        offreCours.put(_251_MGL7260.toString(),_251_MGL7260);

        CoursSession _251_MGL7315 = new CoursSession(CreationDonneesReferenceTests.getCoursParSigle("MGL7315"),Session.Hiver2025);
        offreCours.put(_251_MGL7315.toString(),_251_MGL7315);

        CoursSession _251_MGL7560 = new CoursSession(CreationDonneesReferenceTests.getCoursParSigle("MGL7560"),Session.Hiver2025);
        offreCours.put(_251_MGL7560.toString(),_251_MGL7560);

        // Été 2025
        CoursSession _252_MGL7020 = new CoursSession(CreationDonneesReferenceTests.getCoursParSigle("MGL7020"),Session.Ete2025);
        offreCours.put(_252_MGL7020.toString(),_252_MGL7020);

        CoursSession _252_MGL7315 = new CoursSession(CreationDonneesReferenceTests.getCoursParSigle("MGL7315"),Session.Ete2025);
        offreCours.put(_252_MGL7315.toString(),_252_MGL7315);

        CoursSession _252_MGL7560 = new CoursSession(CreationDonneesReferenceTests.getCoursParSigle("MGL7560"),Session.Ete2025);
        offreCours.put(_252_MGL7560.toString(),_252_MGL7560);

        // Automne 2025
        CoursSession _253_MGL7010 = new CoursSession(CreationDonneesReferenceTests.getCoursParSigle("MGL7010"),Session.Automne2025);
        offreCours.put(_253_MGL7010.toString(),_253_MGL7010);

        CoursSession _253_MGL7020 = new CoursSession(CreationDonneesReferenceTests.getCoursParSigle("MGL7020"),Session.Automne2025);
        offreCours.put(_253_MGL7020.toString(),_253_MGL7020);

        CoursSession _253_MGL7030 = new CoursSession(CreationDonneesReferenceTests.getCoursParSigle("MGL7030"),Session.Automne2025);
        offreCours.put(_253_MGL7030.toString(),_253_MGL7030);

        CoursSession _253_MGL7320 = new CoursSession(CreationDonneesReferenceTests.getCoursParSigle("MGL7320"),Session.Automne2025);
        offreCours.put(_253_MGL7320.toString(),_253_MGL7320);

        CoursSession _253_MGL7361 = new CoursSession(CreationDonneesReferenceTests.getCoursParSigle("MGL7361"),Session.Automne2025);
        offreCours.put(_253_MGL7361.toString(),_253_MGL7361);

        CoursSession _253_MGL7315 = new CoursSession(CreationDonneesReferenceTests.getCoursParSigle("MGL7315"),Session.Automne2025);
        offreCours.put(_253_MGL7315.toString(),_253_MGL7315);

        CoursSession _253_MGL7460 = new CoursSession(CreationDonneesReferenceTests.getCoursParSigle("MGL7460"),Session.Automne2025);
        offreCours.put(_253_MGL7460.toString(),_253_MGL7460);

        // Hiver 2026
        CoursSession _261_MGL7010 = new CoursSession(CreationDonneesReferenceTests.getCoursParSigle("MGL7010"),Session.Hiver2026);
        offreCours.put(_261_MGL7010.toString(),_261_MGL7010);

        CoursSession _261_MGL7020 = new CoursSession(CreationDonneesReferenceTests.getCoursParSigle("MGL7020"),Session.Hiver2026);
        offreCours.put(_261_MGL7020.toString(),_261_MGL7020);

        CoursSession _261_MGL7030 = new CoursSession(CreationDonneesReferenceTests.getCoursParSigle("MGL7030"),Session.Hiver2026);
        offreCours.put(_261_MGL7030.toString(),_261_MGL7030);

        CoursSession _261_MGL7130 = new CoursSession(CreationDonneesReferenceTests.getCoursParSigle("MGL7130"),Session.Hiver2026);
        offreCours.put(_261_MGL7130.toString(),_261_MGL7130);

        CoursSession _261_MGL7230 = new CoursSession(CreationDonneesReferenceTests.getCoursParSigle("MGL7230"),Session.Hiver2026);
        offreCours.put(_261_MGL7230.toString(),_261_MGL7230);

        CoursSession _261_MGL7260 = new CoursSession(CreationDonneesReferenceTests.getCoursParSigle("MGL7260"),Session.Hiver2026);
        offreCours.put(_261_MGL7260.toString(),_261_MGL7260);

        CoursSession _261_MGL7315 = new CoursSession(CreationDonneesReferenceTests.getCoursParSigle("MGL7315"),Session.Hiver2026);
        offreCours.put(_261_MGL7315.toString(),_261_MGL7315);

        CoursSession _261_MGL7560 = new CoursSession(CreationDonneesReferenceTests.getCoursParSigle("MGL7560"),Session.Hiver2026);
        offreCours.put(_261_MGL7560.toString(),_261_MGL7560);
    }

    private static void creerProgrammes() {
        Programme dessGL = new ProgrammeImpl(nomDESSGL, new Diplome(Grade.DESS,Specialite.GénieLogiciel));
        dessGL.setNombreDeCredits(24);
        dessGL.ajouteCoursObligatoire(catalogueCours.get("MGL7010"));
        dessGL.ajouteCoursObligatoire(catalogueCours.get("MGL7260"));
        dessGL.ajouteCoursObligatoire(catalogueCours.get("MGL7315"));
        dessGL.ajouteCoursObligatoire(catalogueCours.get("MGL7361"));
        dessGL.ajouteCoursObligatoire(catalogueCours.get("MGL7460"));

        dessGL.ajouteCoursOptionnel(catalogueCours.get("MGL7020"));
        dessGL.ajouteCoursOptionnel(catalogueCours.get("MGL7030"));
        dessGL.ajouteCoursOptionnel(catalogueCours.get("MGL7230"));
        dessGL.ajouteCoursOptionnel(catalogueCours.get("MGL7560"));

        dessGL.ajoutePrerequis(new Diplome(Grade.Baccalaureat,Specialite.GénieLogiciel));
        dessGL.ajoutePrerequis(new Diplome(Grade.Baccalaureat,Specialite.GénieBiomédical));
        dessGL.ajoutePrerequis(new Diplome(Grade.Baccalaureat,Specialite.Informatique));

        catalogueProgrammes.put(dessGL.getNom(),dessGL);

        Programme maitriseGL = new ProgrammeImpl(nomMGL, new Diplome(Grade.Maitrise,Specialite.GénieLogiciel));
        maitriseGL.setNombreDeCredits(30);

        maitriseGL.ajouteCoursObligatoire(catalogueCours.get("MGL7010"));
        maitriseGL.ajouteCoursObligatoire(catalogueCours.get("MGL7030"));
        maitriseGL.ajouteCoursObligatoire(catalogueCours.get("MGL7260"));
        maitriseGL.ajouteCoursObligatoire(catalogueCours.get("MGL7315"));
        maitriseGL.ajouteCoursObligatoire(catalogueCours.get("MGL7361"));
        maitriseGL.ajouteCoursObligatoire(catalogueCours.get("MGL7460"));

        maitriseGL.ajouteCoursOptionnel(catalogueCours.get("MGL7020"));
        maitriseGL.ajouteCoursOptionnel(catalogueCours.get("MGL7230"));
        maitriseGL.ajouteCoursOptionnel(catalogueCours.get("MGL7120"));
        maitriseGL.ajouteCoursOptionnel(catalogueCours.get("MGL7760"));
        maitriseGL.ajouteCoursOptionnel(catalogueCours.get("MGL7560"));

        maitriseGL.ajoutePrerequis(new Diplome(Grade.Baccalaureat,Specialite.GénieLogiciel));
        maitriseGL.ajoutePrerequis(new Diplome(Grade.Baccalaureat,Specialite.GénieBiomédical));
        maitriseGL.ajoutePrerequis(new Diplome(Grade.Baccalaureat,Specialite.Informatique));

        catalogueProgrammes.put(maitriseGL.getNom(),maitriseGL);

    }

    /**
     * retourne la personne étudiante ayant le code permanent <code>codePermanent</code>
     * @param codePermanent
     * @return
     */
    public static PersonneEtudiante getPersonneEtudianteParCodePermanent(String codePermanent) {
        return personnesEtudiantes.get(codePermanent);
    }

    /**
     * retourne le programme ayant pour nom complet <code>nomProgramme</code>
     * 
     * Il ne faudrait pas prendre de chance avec une chaine de caractères spécifiée
     * littéralement. Se fier plus tôt aux variables statiques publiques <code>nomMGL</code>
     * et <code>nomDESSGL</code>
     * @param nomProgramme
     * @return
     */
    public static Programme getProgrammeParNom(String nomProgramme) {
        return catalogueProgrammes.get(nomProgramme);
    }

    /**
     * return le cours ayant pour sigle <code>sigle</code>, dans le style
     * "MGL7010" ou "MGL7361"
     * @param sigle
     * @return
     */
    public static Cours getCoursParSigle(String sigle) {
        return catalogueCours.get(sigle);
    }

    /**
     * get CoursSession par <code>sigle</code>, dans le style "243-MGL7010",
     * qui veut dire le cours MGL7010 offert à la troisième session (donc, 
     * l'automne) de l'année (20)24
     * @param sigle
     * @return
     */
    public static CoursSession getCoursSessionParSigle(String sigle) {
        return offreCours.get(sigle);
    }

    public static void creerDonneesReference() {
        // je dois créer les cours, avant de créer les programmes
        // car ces derniers font référence aux cours
        creerCours();

        // creer les CoursSession
        creerCoursSessions();

        // créer programmes
        creerProgrammes();

        // On aurait pu créer les personnes étudiantes
        // à n'importe quel moment
        creerPersonnesEtudiantes();
    }
}
