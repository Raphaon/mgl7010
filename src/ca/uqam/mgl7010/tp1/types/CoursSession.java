package ca.uqam.mgl7010.tp1.types;

public record CoursSession(Cours cours, Session session) {

    public String toString() {
        return session.symbole() + "-" + cours.getSigle() ;
    }

}
