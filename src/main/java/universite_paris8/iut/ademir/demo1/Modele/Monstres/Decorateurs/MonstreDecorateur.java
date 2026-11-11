//mpd nouveaucode
package universite_paris8.iut.ademir.demo1.Modele.Monstres.Decorateurs;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.collections.ObservableList;
import universite_paris8.iut.ademir.demo1.Modele.Cartes.Position;
import universite_paris8.iut.ademir.demo1.Modele.Monstres.Monstre;
import universite_paris8.iut.ademir.demo1.Modele.Monstres.VarianteMonstre;
import universite_paris8.iut.ademir.demo1.Modele.Monstres.Deplacements.StrategieDeplacement;


public abstract class MonstreDecorateur implements Monstre {

    protected final Monstre monstreDecore;


    protected MonstreDecorateur(Monstre monstreDecore) {
        this.monstreDecore = monstreDecore;
    }


    @Override
    public void avancer(Monstre monstreActif) {
        monstreDecore.avancer(monstreActif);
    }

    @Override
    public void setDeplacement(StrategieDeplacement deplacement) {
        monstreDecore.setDeplacement(deplacement);
    }


    @Override
    public boolean estADestination() {
        return monstreDecore.estADestination();
    }

    @Override
    public Position getPosition() {
        return monstreDecore.getPosition();
    }

    @Override
    public double getDernierePositionX() {
        return monstreDecore.getDernierePositionX();
    }

     @Override
    public double getDernierePositionY() {
        return monstreDecore.getDernierePositionY();
    }


    @Override
    public double getX() {
        return monstreDecore.getX();
    }


    @Override
    public double getY() {
        return monstreDecore.getY();
    }


    @Override
    public void setX(double x) {
        monstreDecore.setX(x);
    }


    @Override
    public void setY(double y) {
        monstreDecore.setY(y);
    }


    @Override
    public DoubleProperty xProperty() {
        return monstreDecore.xProperty();
    }


    @Override
    public DoubleProperty yProperty() {
        return monstreDecore.yProperty();
    }


    @Override
    public int getPv() {
        return monstreDecore.getPv();
    }

    @Override
    public void setPv(int pv) {
        monstreDecore.setPv(pv);
    }


    @Override
    public int getPvMax() {
        return monstreDecore.getPvMax();
    }


    @Override
    public IntegerProperty pvProperty() {
        return monstreDecore.pvProperty();
    }


    @Override
    public boolean estMort() {
        return monstreDecore.estMort();
    }


    @Override
    public double getVitesse() {
        return monstreDecore.getVitesse();
    }


    @Override
    public void setVitesse(double vitesse) {
        monstreDecore.setVitesse(vitesse);
    }


    @Override
    public double getVitesseNormal() {
        return monstreDecore.getVitesseNormal();
    }

    @Override
    public int getRecompense() {
        return monstreDecore.getRecompense();
    }


    @Override
    public void setRecompense(int recompense) {
        monstreDecore.setRecompense(recompense);
    }


    @Override
    public int getDegat() {
        return monstreDecore.getDegat();
    }


    @Override
    public void recevoirDegats(int degats) {
        monstreDecore.recevoirDegats(degats);
    }


    @Override
    public void recevoirDegatsGlace(int degats) {
        monstreDecore.recevoirDegatsGlace(degats);
    }


    @Override
    public void recevoirDegatsPoison(int degats) {
        monstreDecore.recevoirDegatsPoison(degats);
    }



    @Override
    public void recevoirDegatsFeu(int degats, Monstre monstreActif) {
        monstreDecore.recevoirDegatsFeu(degats, monstreActif);
    }


    @Override
    public boolean estGlace() {
        return monstreDecore.estGlace();
    }




    @Override
    public void setGlace(boolean glace) {
        monstreDecore.setGlace(glace);
    }


    @Override
    public BooleanProperty glaceProperty() {
        return monstreDecore.glaceProperty();
    }


    @Override
    public boolean estEnFeu() {
        return monstreDecore.estEnFeu();
    }


    @Override
    public void setFeu(boolean feu) {
        monstreDecore.setFeu(feu);
    }


    @Override
    public BooleanProperty feuProperty() {
        return monstreDecore.feuProperty();
    }


    @Override
    public String getMonstreID() {
        return monstreDecore.getMonstreID();
    }


    @Override
    public Monstre getMonstreDeBase() {
        return monstreDecore.getMonstreDeBase();
    }


    @Override
    public VarianteMonstre getVariante() {
        return monstreDecore.getVariante();
    }


    @Override
    public void agir(ObservableList<Monstre> monstres) {
        monstreDecore.agir(monstres);
    }
}
