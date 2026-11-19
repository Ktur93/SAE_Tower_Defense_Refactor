package universite_paris8.iut.ademir.demo1.Modele.Effets;

import universite_paris8.iut.ademir.demo1.Modele.Monstres.Monstre;

public class EffetFoudre extends EffetAvecDuree {

    private static final int DUREE_FOUDRE = 60;

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