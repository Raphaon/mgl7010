package ca.uqam.mgl7010.tp1.types;

public record Adresse(String numeroPorte, String numeroRue, String nomRue, String ville, String codePostal, ProvinceOuTerritoire provinceOuTerritoire) {

    public String toString() {
        StringBuilder builder = new StringBuilder();
        if (numeroPorte != null) {
            builder.append("Porte " + numeroPorte + ", ");
        }
        
        builder.append(numeroRue + ", " + nomRue);

        return builder.toString();
    }

}
