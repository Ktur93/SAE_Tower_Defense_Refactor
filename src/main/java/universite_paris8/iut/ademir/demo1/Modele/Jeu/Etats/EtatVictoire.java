//eta nouveau code
// La simulation reste arretee jusqu au prochain redemarrage.
package universite_paris8.iut.ademir.demo1.Modele.Jeu.Etats;

import universite_paris8.iut.ademir.demo1.Modele.Jeu.Partie;

public class EtatVictoire implements EtatPartie {

    @Override
    public void mettreAJour(Partie partie) {
        // Aucun mouvement ni tir apres la victoire.
    }
}
