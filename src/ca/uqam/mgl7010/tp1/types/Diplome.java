package ca.uqam.mgl7010.tp1.types;

public record Diplome(Grade grade,Specialite specialite) {

    /**
     * imprimer un diplôme de façon intuitive
     */
    public String toString() {
        StringBuilder sBuilder = new StringBuilder();

        sBuilder.append(grade).append("(").append(specialite).append(")");

        return sBuilder.toString();
    }
    
}
