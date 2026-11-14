
package universite_paris8.iut.ademir.demo1.Modele.Projectile.DegatsSansEffet;

import universite_paris8.iut.ademir.demo1.Modele.Monstres.Monstre;

public class DegatsLazer {

    private Monstre cibleActuelle;
    private int degatsActuels = 10;

    public void appliquer(Monstre cible) {

        if (cible == null || cible.estMort()) {
            cibleActuelle = null;
            degatsActuels = 10;
            return;
        }

        // Si le laser change de cible, les dégâts recommencent à 10
        if (cibleActuelle != cible) {
            cibleActuelle = cible;
            degatsActuels = 10;
        }
        cible.recevoirDegats(degatsActuels);
        // Augmentation des dégâts pour le prochain impact
        degatsActuels += 5;
    }

    public void reinitialiser() {
        cibleActuelle = null;
        degatsActuels = 10;
    }
}
