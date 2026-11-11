package universite_paris8.iut.ademir.demo1.Modele.Jeu;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import universite_paris8.iut.ademir.demo1.Modele.Cartes.Carte;
import universite_paris8.iut.ademir.demo1.Modele.Cartes.Position;
import universite_paris8.iut.ademir.demo1.Modele.Monstres.Monstre;
import universite_paris8.iut.ademir.demo1.Modele.Projectile.ProjectileExplosion;
import universite_paris8.iut.ademir.demo1.Modele.Tour.Tour;
import universite_paris8.iut.ademir.demo1.Modele.Projectile.Projectile;

import java.util.ArrayList;

public class Partie {

    private ObservableList<Monstre> monstres;
    private ObservableList<Projectile> projectiles;

    private ArrayList<Position> chemin;
    private ArrayList<Position> chemin2;
    private ArrayList<Position> chemin3;

    private int pvPortail;
    private int prixCase;

    private IntegerProperty pvPortailIntegerProperty;
    private int compteur;
    private int compteurDefaite;

    private BooleanProperty toutesLesVaguesTermine;
    private BooleanProperty portailMort;
    private BooleanProperty victoire;
    private BooleanProperty defaite;

    private boolean victoireBoucleLanceUneFois;
    private boolean defaiteBoucleLanceUneFois;

    private final GestionnaireTour gestionnaireTours;
    private final GestionnaireMonstre gestionnaireMonstres;
    private final GestionnaireVague gestionnaireVagues;
    private final GestionnaireScore gestionnaireScore;

    public Partie(ArrayList<Position> chemin, ArrayList<Position> chemin2, ArrayList<Position> chemin3) {
        this.monstres = FXCollections.observableArrayList();
        this.projectiles = FXCollections.observableArrayList();

        this.chemin = chemin;
        this.chemin2 = chemin2;
        this.chemin3 = chemin3;

        this.pvPortail = 10;
        this.prixCase = 25;

        this.pvPortailIntegerProperty = new SimpleIntegerProperty(3);

        this.compteur = 0;
        this.compteurDefaite = 0;

        this.toutesLesVaguesTermine = new SimpleBooleanProperty(false);
        this.portailMort = new SimpleBooleanProperty(false);
        this.victoire = new SimpleBooleanProperty(false);
        this.defaite = new SimpleBooleanProperty(false);
        this.victoireBoucleLanceUneFois = false;
        this.defaiteBoucleLanceUneFois = false;

        this.gestionnaireTours = new GestionnaireTour();
        this.gestionnaireMonstres = new GestionnaireMonstre();
        this.gestionnaireVagues = new GestionnaireVague(chemin, chemin2, chemin3);
        this.gestionnaireScore = new GestionnaireScore();
        creeVagues();
    }

    public ArrayList<Position> getChemin() {
        return chemin;
    }

    public void mettreAJour() {
        // Faire avancer les modeles bougeables
        faireAvancerMonstres();
        faireAttaquerTours(compteur);
        supprimerMonstresMorts();
        projectilePourMettreAJour();

        boolean vagueTerminee = gestionnaireVagues.mettreAJour(
                compteur,
                monstres
        );

        if (vagueTerminee) {
            gestionnaireTours.augmenterPrixAmelioration();
        }

        // partie Victoire
        if (getIndiceVague() > (getVagues().size() - 1) && !(victoireBoucleLanceUneFois) && !(portailMort())) {
            this.victoireBoucleLanceUneFois = true;
            this.victoire.set(true);
        }

        // partie defaite
        if (portailMort() && !defaiteBoucleLanceUneFois) {
            if (!(getVagueEnCours())) {
                defaite.set(true);
                this.compteurDefaite = compteur;
                defaiteBoucleLanceUneFois = true;
            }
        }

        if (!(portailMort())) {
            defaite.set(false);
        }
    }

    public void faireAvancerMonstres() {
        gestionnaireMonstres.faireAvancerMonstres(monstres, this);
    }

    public void supprimerMonstresMorts() {
        gestionnaireMonstres.supprimerMonstresMorts(monstres, this);
    }

    public void acheterCase(Position position, Carte carte) {
        if (getRubis() >= prixCase) {
            if (carte.caseDebloquer(position)) {
                retirerRubis(prixCase);
                prixCase += 25;
            }
        }
    }

    public boolean placerTour(Tour tour, Carte carte) {
        return gestionnaireTours.placerTour(tour, carte, this);
    }

    public void faireAttaquerTours(int compteur) {
        gestionnaireTours.faireAttaquerTours(
                compteur,
                monstres,
                projectiles
        );
    }

    public void faireAmeliorerTours(Tour tour) {
        gestionnaireTours.faireAmeliorerTours(tour, this);
    }

    public void creeVagues() {
        gestionnaireVagues.creerVagues(
                chemin,
                chemin2,
                chemin3
        );
    }

    public void lancerProchaineVague() {
        gestionnaireVagues.lancerProchaineVague();
    }

    public void recommnencer() {
        gestionnaireVagues.recommencer(
                chemin,
                chemin2,
                chemin3
        );

        this.prixCase = 25;
        pvPortailIntegerProperty.set(3);
        this.defaiteBoucleLanceUneFois = false;
        this.victoireBoucleLanceUneFois = false;

        gestionnaireTours.recommencer();
        gestionnaireScore.recommencer();
        monstres.clear();
        projectiles.clear();
    }

    public void projectilePourMettreAJour() {
        int i = 0;

        while (i < projectiles.size()) {
            Projectile projectile = projectiles.get(i);

            if (projectile instanceof ProjectileExplosion) {
                ((ProjectileExplosion) projectile).setMonstres(monstres);
            }

            if (projectile.avancer()) {
                projectiles.remove(i);
                System.out.println("supp");
            } else {
                i++;
            }
        }
    }

    public int getRubis() {
        return gestionnaireScore.getRubis();
    }

    public void ajouterRubis(int montant) {
        gestionnaireScore.ajouterRubis(montant);
    }

    public void retirerRubis(int montant) {
        gestionnaireScore.retirerRubis(montant);
    }

    public ObservableList<Monstre> getMonstres() {
        return monstres;
    }

    public ObservableList<Projectile> getProjectiles() {
        return projectiles;
    }

    public Tour trouverTour(Position position) {
        return gestionnaireTours.trouverTour(position);
    }


    public ObservableList<Tour> getTours() {
        return gestionnaireTours.getTours();
    }

    public int getPrixAmelioration() {
        return gestionnaireTours.getPrixAmelioration();
    }

    public int getIndiceVague() {
        return gestionnaireVagues.getIndiceVague();
    }

    public int getIndiceVaguePlusUn() {
        return gestionnaireVagues.getIndiceVaguePlusUn();
    }

    public void setIndiceVaguePlusUn(int i) {
        gestionnaireVagues.setIndiceVague(i);
    }

    public void setIndiceVague(int i) {
        gestionnaireVagues.setIndiceVague(i);
    }

    public ArrayList<Vague> getVagues() {
        return gestionnaireVagues.getVagues();
    }

    public boolean getVagueEnCours() {
        return gestionnaireVagues.getVagueEnCours();
    }

    public BooleanProperty vagueEnCoursProperty() {
        return gestionnaireVagues.vagueEnCoursProperty();
    }

    public boolean getToutesLesVaguesTerminees() {
        return gestionnaireVagues.getToutesLesVaguesTerminees();
    }

    public int getPrixCase() {
        return this.prixCase;
    }

    public int getCompteur() {
        return this.compteur;
    }

    public IntegerProperty pvPortailIntegerPropertyProperty() {
        return pvPortailIntegerProperty;
    }

    public int getPvPortailIntegerProperty() {
        return pvPortailIntegerProperty.get();
    }

    public int getCompteurDefaite() {
        return this.compteurDefaite;
    }

    public void setDefaiteProperty(boolean defaite) {
        this.defaite.set(defaite);
    }

    public void setCompteurPlusPlus() {
        this.compteur++;
    }

    public void recevoirDegatPortail(int nbDegat) {
        pvPortailIntegerProperty.set(pvPortailIntegerProperty.get() - nbDegat);
    }

    public boolean portailMort() {
        return pvPortailIntegerProperty.get() <= 0;
    }

    public BooleanProperty toutesLesVaguesTermineProperty() {
        return toutesLesVaguesTermine;
    }

    public BooleanProperty portailMortProperty() {
        return portailMort;
    }

    public BooleanProperty defaiteProperty() {
        return defaite;
    }

    public BooleanProperty victoireProperty() {
        return victoire;
    }

    public int getPvPortail() {
        return this.pvPortail;
    }
}
