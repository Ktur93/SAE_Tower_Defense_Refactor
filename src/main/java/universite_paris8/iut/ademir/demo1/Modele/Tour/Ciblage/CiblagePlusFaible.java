package universite_paris8.iut.ademir.demo1.Modele.Tour;

import javafx.collections.ObservableList;
import universite_paris8.iut.ademir.demo1.Modele.Monstres.Monstre;

public class CiblagePlusFaible implements StrategieCiblage {

    @Override
    public Monstre choisirCible(ObservableList<Monstre> monstres, Tour tour) {
        Monstre cible = null;

        for (Monstre monstre : monstres) {
            if (tour.estAPorter(monstre)) {
                if (cible == null || monstre.getPv() < cible.getPv()) {
                    cible = monstre;
                }
            }
        }

        return cible;
    }
}