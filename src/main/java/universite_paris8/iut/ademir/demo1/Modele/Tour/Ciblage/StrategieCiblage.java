package universite_paris8.iut.ademir.demo1.Modele.Tour;

import javafx.collections.ObservableList;
import universite_paris8.iut.ademir.demo1.Modele.Monstres.Monstre;

public interface StrategieCiblage {
    Monstre choisirCible(ObservableList<Monstre> monstres, Tour tour);

    String getNom();

    StrategieCiblage strategieSuivante();
}
