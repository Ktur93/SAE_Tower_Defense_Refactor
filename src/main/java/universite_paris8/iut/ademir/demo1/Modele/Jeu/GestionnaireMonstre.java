
package universite_paris8.iut.ademir.demo1.Modele.Jeu;

import javafx.collections.ObservableList;
import universite_paris8.iut.ademir.demo1.Modele.Monstres.Monstre;

public class GestionnaireMonstre {

    public void faireAvancerMonstres(
            ObservableList<Monstre> monstres,
            Partie partie) {

        for (Monstre monstre : monstres) {
            monstre.avancer();

            if (monstre.estADestination()) {
                partie.recevoirDegatPortail(monstre.getDegat());
                monstre.setRecompense(0);
                monstre.setPv(0);
            }
        }
    }

    public void supprimerMonstresMorts(
            ObservableList<Monstre> monstres,
            Partie partie) {

        monstres.removeIf(monstre -> {
            if (monstre.estMort()) {
                partie.ajouterRubis(monstre.getRecompense());
                return true;
            }
            return false;
        });
    }
}
