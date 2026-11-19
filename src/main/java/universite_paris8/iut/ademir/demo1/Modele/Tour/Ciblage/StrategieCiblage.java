package universite_paris8.iut.ademir.demo1.Modele.Tour.Ciblage;

import javafx.collections.ObservableList;
import universite_paris8.iut.ademir.demo1.Modele.Monstres.Monstre;
import universite_paris8.iut.ademir.demo1.Modele.Tour.Tour;

public interface StrategieCiblage {
    Monstre choisirCible(ObservableList<Monstre> monstres, Tour tour);

    TypeCiblage getType();

    StrategieCiblage strategieSuivante();
}
