package universite_paris8.iut.ademir.demo1.Modele.Tour.Ciblage;

import javafx.collections.ObservableList;
import universite_paris8.iut.ademir.demo1.Modele.Monstres.Monstre;
import universite_paris8.iut.ademir.demo1.Modele.Tour.Tour;

public class CiblagePlusFort implements StrategieCiblage {

    @Override
    public Monstre choisirCible(ObservableList<Monstre> monstres, Tour tour) {
        Monstre cible = null;

        for (Monstre monstre : monstres) {
            if (tour.estAPorter(monstre)) {
                if (cible == null || monstre.getPv() > cible.getPv()) {
                    cible = monstre;
                }
            }
        }

        return cible;
    }

    @Override
    public TypeCiblage getType() {
        return TypeCiblage.PLUS_FORT;
    }

    @Override
    public StrategieCiblage strategieSuivante() {
        return new CiblagePremier();
    }
}