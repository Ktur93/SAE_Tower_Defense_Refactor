package universite_paris8.iut.ademir.demo1.Modele.Effets;

import universite_paris8.iut.ademir.demo1.Modele.Monstres.Monstre;

public class EffetFoudre extends Effet {

    @Override
    public void activer() {
        super.activer();
        // Un nouveau projectile relance la duree
        compteur = 0;
    }

    @Override
    protected void appliquerEffet(Monstre monstre) {
        // 60 maj ca fait environ une seconde
        if (compteur >= 60) {
            arreter(monstre);
        }
    }

    @Override
    public void arreter(Monstre monstre) {
        desactiver();
        monstre.setFoudre(false);
    }
}