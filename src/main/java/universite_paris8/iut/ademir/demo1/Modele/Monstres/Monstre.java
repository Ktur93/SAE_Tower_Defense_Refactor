//mpd nouveaucode
package universite_paris8.iut.ademir.demo1.Modele.Monstres;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.collections.ObservableList;
import universite_paris8.iut.ademir.demo1.Modele.Cartes.Position;
import universite_paris8.iut.ademir.demo1.Modele.Monstres.Deplacements.StrategieDeplacement;
public interface Monstre {

    void avancer();

    void avancer(Monstre monstreActif);

    boolean estArrive();

    Position getPosition();

    void setDeplacement(StrategieDeplacement deplacement);

    int getPv();

    void setPv(int nouveauPv);

    double getVitesse();

    void setVitesse(double vitesse);

    int getRecompense();

    int getDegat();

    boolean getMonstreGlacee();

    double getVitesseNormal();

    void recevoirDegats(int degats);

    void recevoirDegatsGlace(int degats);

    void recevoirDegatsFeu(int degats);

    void recevoirDegatsFeu(int degats, Monstre monstreActif);

    void recevoirDegatsPoison(int degats);

    double getX();

    double getY();

    void setX(double newX);

    void setY(double newY);

    void setRecompense(int recompense);

    DoubleProperty xProperty();

    DoubleProperty yProperty();

    IntegerProperty pvProperty();

    int getPvMax();

    String getMonstreID();

    double getDernierePositionX();

    double getDernierePositionY();

    boolean estMort();

    boolean estADestination();

    BooleanProperty feuProperty();

    boolean estEnFeu();

    void setFeu(boolean enFeu);

    BooleanProperty glaceProperty();

    boolean estGlace();

    void setGlace(boolean estGlaceOuPas);

    void agir(ObservableList<Monstre> monstres);

    Monstre getMonstreDeBase();

    VarianteMonstre getVariante();
}
