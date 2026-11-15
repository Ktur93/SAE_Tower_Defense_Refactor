package universite_paris8.iut.ademir.demo1.Modele.Effets;

import universite_paris8.iut.ademir.demo1.Modele.Monstres.Monstre;

public class EffetFeu extends Effet {

    private static final int DELAI_DEGATS = 60;
    private static final int DEGATS_FEU = 5;
    private static final int DUREE_FEU = 300;

    private int compteurDegats;

    @Override
    public void activer() {
        // Si le monstre ne brule pas encore on demarre un nv compteur
        if (!actif) {
            compteurDegats = 0;
        }

        super.activer();

        // On remet la duree du feu à zero à chaque projectile qu'il recoit
        compteur = 0;
    }

    @Override
    protected void appliquerEffet(Monstre monstre) {
        compteurDegats++;

        // le monstre perd des pv regulierement
        if (compteurDegats >= DELAI_DEGATS) {
            monstre.recevoirDegats(DEGATS_FEU);
            compteurDegats = 0;
        }

        // le feu s'arrete apres sa duree
        if (compteur >= DUREE_FEU) {
            arreter(monstre);
        }
    }

    @Override
    public void arreter(Monstre monstre) {
        actif = false;
        monstre.setFeu(false);
    }
}