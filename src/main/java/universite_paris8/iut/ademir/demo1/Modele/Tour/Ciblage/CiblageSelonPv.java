package universite_paris8.iut.ademir.demo1.Modele.Tour.Ciblage;

import javafx.collections.ObservableList;
import universite_paris8.iut.ademir.demo1.Modele.Monstres.Monstre;
import universite_paris8.iut.ademir.demo1.Modele.Tour.Tour;

public abstract class CiblageSelonPv implements StrategieCiblage {

    @Override
    public Monstre choisirCible(ObservableList<Monstre> monstres, Tour tour) {
        Monstre cible = null;

        for (Monstre monstre : monstres) {
            if (tour.estAPorter(monstre)) {
                if (cible == null || estMeilleureCible(monstre, cible)) {
                    cible = monstre;
                }
            }
        }

        return cible;
    }

    // chaque ciblage choisit comment comparer les pv
    protected abstract boolean estMeilleureCible(Monstre monstre, Monstre cible);
}