package universite_paris8.iut.ademir.demo1.Modele.Effets;

import universite_paris8.iut.ademir.demo1.Modele.Monstres.Monstre;

public class EffetFeu extends Effet {

    @Override
    public void activer() {
        super.activer();
        compteur = 0;
    }

    @Override
    protected void appliquerEffet(Monstre monstre) {
        // Une fois par seconde, pendant 5 secondes (60 mises à jour par seconde).
        if ((compteur + 1) % 60 == 0) {
            monstre.recevoirDegats(5);
        }

        if (compteur + 1 >= 300) {
            actif = false;
            monstre.setFeu(false);
        }
    }
}