//eta nouveau code
// Chaque etat decide comment la partie avance et si le joueur peut agir.
package universite_paris8.iut.ademir.demo1.Modele.Jeu.Etats;

import universite_paris8.iut.ademir.demo1.Modele.Jeu.Partie;

public interface EtatPartie {
    void mettreAJour(Partie partie);

    default boolean peutInteragir() {
        return false;
    }
}
