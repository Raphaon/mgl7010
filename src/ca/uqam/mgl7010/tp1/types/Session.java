package ca.uqam.mgl7010.tp1.types;

public enum Session {
    Hiver2024("241"), Ete2024("242"), Automne2024("243"), Hiver2025("251"), Ete2025("252"), Automne2025("253"), Hiver2026("261"),Ete2026("262"),Automne2026("263");

    private String symbole;

    Session(String symb){
        symbole = symb;
    }

    public String symbole() { return symbole;}
}
