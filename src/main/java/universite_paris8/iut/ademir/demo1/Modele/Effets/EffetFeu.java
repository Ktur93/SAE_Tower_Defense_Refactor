package universite_paris8.iut.ademir.demo1.Modele.Effets;

import universite_paris8.iut.ademir.demo1.Modele.Monstres.Monstre;

public class EffetFeu extends Effet {

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

        // Toutes les 60 maj le monstre perd 5 PV
        if (compteurDegats >= 60) {
            monstre.recevoirDegats(5);
            compteurDegats = 0;
        }

        // Le feu s'arrète 300 mises à jour apres le dernier coup
        if (compteur >= 300) {
            actif = false;
            monstre.setFeu(false);
        }
    }
}