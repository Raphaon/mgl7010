package ca.uqam.mgl7010.tp1.types;

import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public record DiplomeObtenu(PersonneEtudiante etudiante,Diplome diplome, Instant dateDebut, Instant dateObtention) {
    /**
     * imprimer un diplôme de façon intuitive
     */
    public String toString() {
        String STYLE="dd.mm.yyyy";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(STYLE).withZone(ZoneId.systemDefault());
        StringBuilder sBuilder = new StringBuilder();
        sBuilder.append(((Personne)etudiante).toString());
        sBuilder.append(", diplômé-e du programme "+ diplome.toString()).append("), commencé le ");
        sBuilder.append(formatter.format(dateDebut)).append(", obtenu le ");
        sBuilder.append(formatter.format(dateObtention));

        return sBuilder.toString();
    }
        
}
