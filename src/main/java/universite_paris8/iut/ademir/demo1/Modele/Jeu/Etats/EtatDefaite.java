//eta nouveau code
// Attend le delai de l ecran de defaite puis reinitialise la partie.
package universite_paris8.iut.ademir.demo1.Modele.Jeu.Etats;

import universite_paris8.iut.ademir.demo1.Modele.Jeu.Partie;

public class EtatDefaite implements EtatPartie {

    @Override
    public void mettreAJour(Partie partie) {
        if (partie.getCompteur() - partie.getCompteurDefaite() > 175) {
            partie.recommnencer();
        }
    }
}
