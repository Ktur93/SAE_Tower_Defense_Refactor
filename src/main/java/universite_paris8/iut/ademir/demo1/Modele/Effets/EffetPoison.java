package universite_paris8.iut.ademir.demo1.Modele.Effets;

import universite_paris8.iut.ademir.demo1.Modele.Monstres.Monstre;

public class EffetPoison extends Effet {

    private static final int DELAI_DEGATS = 45;
    private static final int DEGATS_POISON = 10;

    public EffetPoison () {
        super();
    }

    @Override
    protected void appliquerEffet(Monstre monstre) {
        if (this.compteur % DELAI_DEGATS == 0) {
            monstre.recevoirDegats(DEGATS_POISON);
        }
    }
}