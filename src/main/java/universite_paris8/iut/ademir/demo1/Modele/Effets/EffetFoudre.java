package universite_paris8.iut.ademir.demo1.Modele.Effets;

import universite_paris8.iut.ademir.demo1.Modele.Monstres.Monstre;

public class EffetFoudre extends Effet {

    private static final int DUREE_FOUDRE = 60;

    @Override
    public void activer() {
        super.activer();
        // Un nouveau projectile relance la duree
        compteur = 0;
    }

    @Override
    protected void appliquerEffet(Monstre monstre) {
        if (compteur >= DUREE_FOUDRE) {
            arreter(monstre);
        }
    }
    @Override
    public void arreter(Monstre monstre) {
        desactiver();
        monstre.setFoudre(false);
    }
}