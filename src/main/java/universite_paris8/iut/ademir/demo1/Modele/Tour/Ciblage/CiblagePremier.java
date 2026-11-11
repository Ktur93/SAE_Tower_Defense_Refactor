package universite_paris8.iut.ademir.demo1.Modele.Tour;

import javafx.collections.ObservableList;
import universite_paris8.iut.ademir.demo1.Modele.Monstres.Monstre;

public class CiblagePremier implements StrategieCiblage {

    @Override
    public Monstre choisirCible(ObservableList<Monstre> monstres, Tour tour) {
        Monstre cible = null;
        int i = 0;

        while (i < monstres.size() && cible == null) {
            Monstre monstre = monstres.get(i);

            if (tour.estAPorter(monstre)) {
                cible = monstre;
            }

            i++;
        }

        return cible;
    }

    @Override
    public String getNom() {
        return "Premier";
    }

    @Override
    public StrategieCiblage strategieSuivante() {
        return new CiblagePlusFaible();
    }
}