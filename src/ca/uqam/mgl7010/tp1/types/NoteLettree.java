package ca.uqam.mgl7010.tp1.types;

public enum NoteLettree {
    E(0f), D(1f), C_moins(1.7f), C(2f), C_plus(2.3f), B_moins(2.7f), B(3f), B_plus(3.3f), A_moins(3.7f), A(4f), A_plus(4.3f);
    
    private float equivalentNumerique;
    NoteLettree(float equivalent) {
        equivalentNumerique = equivalent;
    }

    public float equivalentNumerique() { return equivalentNumerique;}
}
