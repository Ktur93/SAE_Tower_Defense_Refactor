//mpd nouveaucode
package universite_paris8.iut.ademir.demo1.Modele.Monstres;


import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.ObservableList;
import universite_paris8.iut.ademir.demo1.Modele.Cartes.Position;
import java.util.ArrayList;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import universite_paris8.iut.ademir.demo1.Modele.Effets.Effet;
import universite_paris8.iut.ademir.demo1.Modele.Effets.EffetGlace;
import universite_paris8.iut.ademir.demo1.Modele.Effets.EffetFeu;
import universite_paris8.iut.ademir.demo1.Modele.Effets.EffetPoison;
import universite_paris8.iut.ademir.demo1.Modele.Monstres.Deplacements.DeplacementMonstre;
import universite_paris8.iut.ademir.demo1.Modele.Monstres.Deplacements.StrategieDeplacement;

public abstract class MonstreBase implements Monstre {
    private int recompense;
    private StrategieDeplacement deplacement;
    private int degat;

    private Effet effetPoison;
    private Effet effetGlace;
    private Effet effetFeu;
    private ArrayList<Effet> effets;



    private double vitesse;
    private double vitesseNormal;

    private ArrayList<Position> chemin;
    private Position depart;



    private IntegerProperty pv;
    private int pvMax;
    private DoubleProperty x;
    private DoubleProperty y;

    private String monstreID;




    private static int compteurID = 0;

    private BooleanProperty glace;
    private BooleanProperty feu;





    protected MonstreBase(int pv, double vitesse, double vitesseNormal, int recompense, int degat, ArrayList<Position> chemin) {
        this.recompense = recompense;
        this.deplacement = new DeplacementMonstre(chemin);
        this.degat = degat;

        this.monstreID = "monstre" + compteurID;





        this.vitesse = vitesse;
        this.vitesseNormal = vitesseNormal;


        this.chemin = chemin;
        this.depart = chemin.get(0);


        this.pv = new SimpleIntegerProperty(pv);
        this.pvMax = pv;
        this.x = new SimpleDoubleProperty(depart.getX() * 64);
        this.y = new SimpleDoubleProperty(depart.getY() * 64);


        this.monstreID = "monstre" + compteurID;

        this.glace = new SimpleBooleanProperty(false);
        this.feu = new SimpleBooleanProperty(false);

        // les effets
        this.effetPoison = new EffetPoison();
        this.effetGlace = new EffetGlace();
        this.effetFeu = new EffetFeu();
        this.effets = new ArrayList<>();
        this.effets.add(effetPoison);
        this.effets.add(effetGlace);
        this.effets.add(effetFeu);


        compteurID++;
    }



    public void avancer() {
        avancer(this);
    }


    public void avancer(Monstre monstreActif) {
        for (Effet effet : effets) {
            effet.appliquer(monstreActif);
        }

        deplacement.avancer(monstreActif);
    }



    public boolean estArrive() {
        return deplacement.estArrive();
    }

    public Position getPosition() {
        return deplacement.getPosition(this);
    }

    public void setDeplacement(StrategieDeplacement deplacement) {
        this.deplacement = deplacement;
    }


    public int getPv() {
        return this.pv.intValue();
    }

    public void setPv(int nouveauPv) {
        this.pv.setValue(nouveauPv);
    }

    public double getVitesse() {
        return this.vitesse;
    }

    public void setVitesse(double vitesse) {
        this.vitesse = vitesse;
    }

    public int getRecompense() {
        return this.recompense;
    }

    public int getDegat() {
        return this.degat;
    }

    public boolean getMonstreGlacee() {
        return this.estGlace();
    }


    public double getVitesseNormal() {
        return this.vitesseNormal;
    }







    // Conserve la gestion des degats et effets de l ancienne implementation.
    public void recevoirDegats(int degats) {
        pv.setValue(getPv() - degats);
    }

    // Conserve la gestion des degats et effets de l ancienne implementation.
    public void recevoirDegatsGlace(int degats) {
        recevoirDegats(degats);
        if (!estEnFeu()) {
            setGlace(true);
            effetGlace.activer();
        }
    }


    // Conserve l appel existant et transmet le monstre actif a la gestion du feu.
    // Conserve la gestion des degats et effets de l ancienne implementation.
    public void recevoirDegatsFeu(int degats) {
        recevoirDegatsFeu(degats, this);
    }

    // Le feu arrete le gel en restaurant la vitesse du monstre decore.
    // Conserve la gestion des degats et effets de l ancienne implementation.
    public void recevoirDegatsFeu(int degats, Monstre monstreActif) {
        monstreActif.recevoirDegats(degats);

        if (monstreActif.estGlace()) {
            effetGlace.arreter(monstreActif);
        }

        effetFeu.activer();
        monstreActif.setFeu(true);
    }


    // Conserve la gestion des degats et effets de l ancienne implementation.
    public void recevoirDegatsPoison(int degats) {
        recevoirDegats(degats); // Degats du projectile +
        effetPoison.activer();  // activation du poison
    }


    // Expose les donnees du monstre de base via le contrat Monstre.
    public double getX () {
        return this.x.doubleValue();
    }

    // Expose les donnees du monstre de base via le contrat Monstre.
    public double getY() {
        return this.y.doubleValue();
    }

    // Met a jour l etat du monstre de base, utilise par toutes ses enveloppes.
    public void setX (double newX) {
        this.x.setValue(newX);
    }

    // Met a jour l etat du monstre de base, utilise par toutes ses enveloppes.
    public void setY (double newY) {
        this.y.setValue(newY);
    }

    // Met a jour l etat du monstre de base, utilise par toutes ses enveloppes.
    public void setRecompense(int recompense) {
        this.recompense = recompense;
    }

    // Expose la propriete JavaFX reelle pour les liaisons de la vue.
    public DoubleProperty xProperty() {
        return x;
    }

    // Expose la propriete JavaFX reelle pour les liaisons de la vue.
    public DoubleProperty yProperty() {
        return y;
    }

    // Expose la propriete JavaFX reelle pour les liaisons de la vue.
    public IntegerProperty pvProperty(){
        return pv;
    }

    // Expose les donnees du monstre de base via le contrat Monstre.
    public int getPvMax() {
        return pvMax;
    }

    // Expose les donnees du monstre de base via le contrat Monstre.
    public String getMonstreID() {
        return monstreID;
    }

    // Expose les donnees du monstre de base via le contrat Monstre.
    public double getDernierePositionX() {
        return this.chemin.get(this.chemin.size() - 1).getX();
    }

    // Expose les donnees du monstre de base via le contrat Monstre.
    public double getDernierePositionY(){
        return this.chemin.get(this.chemin.size() - 1).getY();
    }

    // Calcule cet etat a partir des donnees reelles du monstre.
    public boolean estMort() {
        return this.pv.intValue() <= 0;
    }

    // Calcule cet etat a partir des donnees reelles du monstre.
    public boolean estADestination() {
        return deplacement.estArrive();
    }

    // Expose la propriete JavaFX reelle pour les liaisons de la vue.
    public BooleanProperty feuProperty() {
        return feu;
    }

    // Calcule cet etat a partir des donnees reelles du monstre.
    public boolean estEnFeu() {
        return feu.get();
    }

    // Met a jour l etat du monstre de base, utilise par toutes ses enveloppes.
    public void setFeu(boolean enFeu) {
        feu.set(enFeu);
    }

    // Expose la propriete JavaFX reelle pour les liaisons de la vue.
    public BooleanProperty glaceProperty() {
        return glace;
    }

    // Calcule cet etat a partir des donnees reelles du monstre.
    public boolean estGlace() {
        return glace.get();
    }

    // Met a jour l etat du monstre de base, utilise par toutes ses enveloppes.
    public void setGlace(boolean estGlaceOuPas) {
        glace.set(estGlaceOuPas);
    }

    // Action par defaut sans capacite speciale ; les decorateurs l enrichissent.
    public void agir(ObservableList<Monstre> monstres) {
        // de base, un monstre n'a pas d'action particulière ca va se repercuter sur les extends.
    }



    // Identifie le meme monstre a travers les couches de decorateurs.
    // Expose les donnees du monstre de base via le contrat Monstre.
    public Monstre getMonstreDeBase() {
        return this;
    }

    // Un monstre sans decorateur utilise son apparence normale.
    // Expose les donnees du monstre de base via le contrat Monstre.
    public VarianteMonstre getVariante() {
        return VarianteMonstre.NORMALE;
    }
}
