
package universite_paris8.iut.ademir.demo1.Modele.Projectile.DegatsSansEffet;

import universite_paris8.iut.ademir.demo1.Modele.Monstres.Monstre;

public class DegatsLazer {

    private Monstre cibleActuelle;
    private int degatsActuels;

    public void appliquer(Monstre cible, int degatsBase) {
        if (cible == null || cible.estMort()) {
            reinitialiser();
            return;
        }
        if (cibleActuelle != cible) {
            cibleActuelle = cible;
            degatsActuels = degatsBase;
        }
        cible.recevoirDegats(degatsActuels);
        degatsActuels = degatsActuels * 2;
    }

    public void reinitialiser() {
        cibleActuelle = null;
        degatsActuels = 0;
    }
}

