package universite_paris8.iut.ademir.demo1.Modele.Jeu;

import javafx.beans.binding.Bindings;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ReadOnlyBooleanProperty;
import javafx.beans.property.ReadOnlyIntegerProperty;
import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import universite_paris8.iut.ademir.demo1.Modele.Cartes.Carte;
import universite_paris8.iut.ademir.demo1.Modele.Cartes.Position;
import universite_paris8.iut.ademir.demo1.Modele.Jeu.Operations.AchatCase;
import universite_paris8.iut.ademir.demo1.Modele.Monstres.Monstre;
import universite_paris8.iut.ademir.demo1.Modele.Projectile.ProjectileExplosion;
import universite_paris8.iut.ademir.demo1.Modele.Tour.Tour;
import universite_paris8.iut.ademir.demo1.Modele.Projectile.Projectile;

import universite_paris8.iut.ademir.demo1.Modele.Jeu.Commandes.CommandePlacementTour;
import universite_paris8.iut.ademir.demo1.Modele.Jeu.Commandes.HistoriqueCommandes;
import universite_paris8.iut.ademir.demo1.Modele.Jeu.Etats.*;

import java.util.ArrayList;

public class Partie {

    private ObservableList<Monstre> monstres;
    private ObservableList<Projectile> projectiles;

    private ArrayList<Position> chemin;
    private ArrayList<Position> chemin2;
    private ArrayList<Position> chemin3;

    private int prixCase;

    private IntegerProperty pvPortailIntegerProperty;
    private int compteur;
    private int compteurDefaite;

    private BooleanProperty toutesLesVaguesTermine;
    private BooleanProperty portailMort;
    private BooleanProperty victoire;
    private BooleanProperty defaite;

    private final ReadOnlyObjectWrapper<EtatPartie> etatPartie;
    private final HistoriqueCommandes historiqueCommandes;

    private final GestionnaireTour gestionnaireTours;
    private final GestionnaireMonstre gestionnaireMonstres;
    private final GestionnaireVague gestionnaireVagues;
    private final GestionnaireScore gestionnaireScore;

    //eta nouveau code
    //cmd nouveau code
    // Initialise l etat actif, ses indicateurs derives et l historique des placements.
    public Partie(ArrayList<Position> chemin, ArrayList<Position> chemin2, ArrayList<Position> chemin3) {
        this.monstres = FXCollections.observableArrayList();
        this.projectiles = FXCollections.observableArrayList();

        this.chemin = chemin;
        this.chemin2 = chemin2;
        this.chemin3 = chemin3;

        this.prixCase = 25;

        this.pvPortailIntegerProperty = new SimpleIntegerProperty(3);

        this.compteur = 0;
        this.compteurDefaite = 0;

        this.toutesLesVaguesTermine = new SimpleBooleanProperty(false);
        this.portailMort = new SimpleBooleanProperty(false);
        this.victoire = new SimpleBooleanProperty(false);
        this.defaite = new SimpleBooleanProperty(false);
        this.etatPartie = new ReadOnlyObjectWrapper<>(new EtatEnCours());
        this.historiqueCommandes = new HistoriqueCommandes();
        this.victoire.bind(Bindings.createBooleanBinding(
                () -> etatPartie.get() instanceof EtatVictoire, etatPartie));
        this.defaite.bind(Bindings.createBooleanBinding(
                () -> etatPartie.get() instanceof EtatDefaite, etatPartie));
        this.portailMort.bind(pvPortailIntegerProperty.lessThanOrEqualTo(0));

        this.gestionnaireTours = new GestionnaireTour();
        this.gestionnaireMonstres = new GestionnaireMonstre();
        this.gestionnaireVagues = new GestionnaireVague(chemin, chemin2, chemin3);
        this.gestionnaireScore = new GestionnaireScore();
        creeVagues();
    }

    public ArrayList<Position> getChemin() {
        return chemin;
    }

    //eta nouveau code
    // Delegue chaque mise a jour a l etat courant de la partie.
    public void mettreAJour() {
        etatPartie.get().mettreAJour(this);
    }

    public void faireAvancerMonstres() {
        gestionnaireMonstres.faireAvancerMonstres(monstres, this);
    }

    public void supprimerMonstresMorts() {
        gestionnaireMonstres.supprimerMonstresMorts(monstres, this);
    }

    //CSP nouveau code
    //otp nouveau code
    // Augmente le prix suivant uniquement apres un achat reussi.
    //eta nouveau code
    // Autorise les achats uniquement pendant la phase jouable.
    public void acheterCase(Position position) {
        if (etatPartie.get().peutInteragir()) {
            AchatCase achat = new AchatCase(this, Carte.getInstance(), position, prixCase);
            if (achat.executer()) {
                prixCase += 25;
            }
        }
    }

    //CSP nouveau code
    //cmd nouveau code
    //eta nouveau code
    // Memorise les placements annulables entre deux vagues et respecte l etat du jeu.
    public boolean placerTour(Tour tour) {
        boolean placee = false;
        if (etatPartie.get().peutInteragir()) {
            CommandePlacementTour commande = new CommandePlacementTour(this, gestionnaireTours, tour);
            if (getVagueEnCours()) {
                placee = commande.executer();
            } else {
                placee = historiqueCommandes.executer(commande);
            }
        }
        return placee;
    }

    public void faireAttaquerTours(int compteur) {
        gestionnaireTours.faireAttaquerTours(
                compteur,
                monstres,
                projectiles
        );
    }

    //cmd nouveau code
    //eta nouveau code
    // Une amelioration reussie termine la possibilite d annuler les placements precedents.
    public void faireAmeliorerTours(Tour tour) {
        if (etatPartie.get().peutInteragir() && tour != null) {
            int niveauAvant = tour.getNivT();
            gestionnaireTours.faireAmeliorerTours(tour, this);
            if (tour.getNivT() > niveauAvant) {
                historiqueCommandes.vider();
            }
        }
    }

    public void creeVagues() {
        gestionnaireVagues.creerVagues(
                chemin,
                chemin2,
                chemin3
        );
    }

    //cmd nouveau code
    //eta nouveau code
    // Le lancement d une vague valide definitivement les placements precedents.
    public void lancerProchaineVague() {
        if (etatPartie.get().peutInteragir()) {
            gestionnaireVagues.lancerProchaineVague();
            if (getVagueEnCours()) {
                historiqueCommandes.vider();
            }
        }
    }

    //eta nouveau code
    //cmd nouveau code
    // Reinitialise le modele avant de notifier le retour a l etat jouable.
    public void recommnencer() {
        gestionnaireVagues.recommencer(chemin, chemin2, chemin3);
        prixCase = 25;
        pvPortailIntegerProperty.set(3);
        compteur = 0;
        compteurDefaite = 0;
        toutesLesVaguesTermine.set(false);
        gestionnaireTours.recommencer();
        gestionnaireScore.recommencer();
        monstres.clear();
        projectiles.clear();
        historiqueCommandes.vider();
        Carte.getInstance().caseBloquer();
        changerEtat(new EtatEnCours());
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

    //eta nouveau code
    // Utilise la meme source de vie que l affichage et les transitions d etat.
    public int getPvPortail() {
        return pvPortailIntegerProperty.get();
    }

    public int getNiveau() {
        return gestionnaireVagues.getNiveau();
    }

    //eta nouveau code
    // Le choix du niveau reste reserve a une partie jouable.
    public void changerNiveau() {
        if (etatPartie.get().peutInteragir()) {
            gestionnaireVagues.changerNiveau(chemin, chemin2, chemin3);
        }
    }

    //eta nouveau code
    // Execute la simulation seulement lorsque l etat courant le demande.
    public void mettreAJourJeu() {
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
        toutesLesVaguesTermine.set(getToutesLesVaguesTerminees());
    }

    //obs nouveau code
    // Permet aux vues d observer directement la reserve du gestionnaire de score.
    public ReadOnlyIntegerProperty rubisProperty() {
        return gestionnaireScore.rubisProperty();
    }

    //eta nouveau code
    // Lit l etat qui pilote actuellement la simulation.
    public EtatPartie getEtatPartie() {
        return etatPartie.get();
    }

    //eta nouveau code
    // Permet au controleur d observer les changements d etat.
    public ReadOnlyObjectProperty<EtatPartie> etatPartieProperty() {
        return etatPartie.getReadOnlyProperty();
    }

    //eta nouveau code
    // Notifie la transition et memorise le debut du delai de defaite.
    public void changerEtat(EtatPartie nouvelEtat) {
        if (nouvelEtat != null) {
            if (nouvelEtat instanceof EtatDefaite) {
                compteurDefaite = compteur;
            }
            if (!nouvelEtat.peutInteragir()) {
                historiqueCommandes.vider();
            }
            etatPartie.set(nouvelEtat);
        }
    }

    //cmd nouveau code
    // Verifie que l historique est utilisable avant la prochaine vague.
    public boolean peutAnnulerPlacement() {
        return etatPartie.get().peutInteragir()
                && !getVagueEnCours() && historiqueCommandes.peutAnnuler();
    }

    //cmd nouveau code
    // Annule le dernier placement si la phase du jeu le permet.
    public boolean annulerDernierPlacement() {
        return peutAnnulerPlacement() && historiqueCommandes.annulerDerniere();
    }

    //cmd nouveau code
    // Notifie la disponibilite de l historique au bouton d annulation.
    public ReadOnlyBooleanProperty annulationPossibleProperty() {
        return historiqueCommandes.annulationPossibleProperty();
    }
}
