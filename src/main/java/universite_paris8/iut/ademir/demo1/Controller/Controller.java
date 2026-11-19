package universite_paris8.iut.ademir.demo1.Controller;

import javafx.animation.AnimationTimer;
import javafx.beans.binding.Bindings;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;

import universite_paris8.iut.ademir.demo1.Modele.Algorithmes.BFS;
import universite_paris8.iut.ademir.demo1.Modele.Cartes.Carte;
import universite_paris8.iut.ademir.demo1.Modele.Cartes.Position;
import universite_paris8.iut.ademir.demo1.Modele.Jeu.Partie;
import universite_paris8.iut.ademir.demo1.Modele.Jeu.Etats.*;
import universite_paris8.iut.ademir.demo1.Modele.Sorts.Sort;
import universite_paris8.iut.ademir.demo1.Modele.Tour.*;
import universite_paris8.iut.ademir.demo1.Vue.*;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    private static final int TAILLE_TUILE = 64;

    // Pane
    @FXML
    private Button btnBouleFeu;
    @FXML
    private Button btnBouleNeige;
    @FXML
    private TilePane paneCarte;
    @FXML
    private Pane paneSprites;
    @FXML
    private Pane paneCoeurs;
    @FXML
    private Pane paneDecoration;
    // Label
    @FXML
    private Label labelRubis;
    @FXML
    private Label labelCiblage;
    // Boutons
    @FXML
    private Button btnRecommencer;
    @FXML
    private Button btnCanon;
    @FXML
    private Button btnArcher;
    @FXML
    private Button btnGlace;
    @FXML
    private Button btnPoison;
    @FXML
    private Button btnFeu;
    @FXML
    private Button btnFoudre;
    @FXML
    private Button btnExplosive;
    @FXML
    private Button btnVague;
    @FXML
    private Button btnAcheterCase;
    @FXML
    private Button btnAmeliorer;
    @FXML
    private Button btnCiblage;
    @FXML
    private Button btnNiveau;
    @FXML
    private Button btnTDE;

    @FXML
    private Button btnAnnuler;

    private EffetSortVue effetSortVue;
    private Carte carte;
    private Partie partie;
    private CarteVue carteVue;
    private TypeTour tourSelectionne;
    private FabriqueTour fabriqueTour = new FabriqueTour();
    private boolean defaiteLance;
    private CoeurVue coeurVue;
    private RubisVue rubisVue;
    private ToursVue toursVue;
    private StrategieClic strategieClic = new StrategiePlacement();

    private interface StrategieClic {
        void executer(Position position);
    }

    private class StrategiePlacement implements StrategieClic {
        @Override
        public void executer(Position position) {
            placerTour(position);
        }
    }

    private class StrategieAchat implements StrategieClic {
        @Override
        public void executer(Position position) {
            acheterCase(position);
        }
    }

    private class StrategieAmelioration implements StrategieClic {
        @Override
        public void executer(Position position) {
            ameliorerTourCliquee(position);
        }
    }

    private class StrategieChangerCiblage implements StrategieClic {
        @Override
        public void executer(Position position) {
            Tour tour = partie.trouverTour(position);
            if(tour != null){
                tour.changerStrategieCiblage();
                labelCiblage.setText("Tour (" + position.getX() + ", " + position.getY() + ") : " + tour.getNomStrategieCiblage());
                strategieClic = new StrategiePlacement();
            }
        }
    }

    private class StrategieSort implements StrategieClic {
        private final Sort sort;
        public StrategieSort(Sort sort) {
            this.sort = sort;
        }
        @Override
        public void executer(Position position) {
            if (!partie.peutLancerSort(sort)) {
                strategieClic = new StrategiePlacement();
                return;
            }
            double x = position.getX() * TAILLE_TUILE + 32;
            double y = position.getY() * TAILLE_TUILE + 32;

            // Retour au mode normal après le clic.
            strategieClic = new StrategiePlacement();

            effetSortVue.lancerAnimation(sort, x, y, () -> partie.lancerSort(sort, x, y)
            );
        }
    }

    //CSP nouveau code
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        carte = Carte.getInstance();
        this.carteVue = new CarteVue(carte, paneCarte, paneDecoration);
        carteVue.dessinerCarte();


        initialiserPartie();
        initialiserBoutons();
        initialiserVues();
        initialiserListeners();
        initialiserClicsSouris();
        initialiserGameLoop();
    }

    private void initialiserPartie() {
        // Positions
        Position depart = new Position(0, 7);
        Position depart2 = new Position(12, 0);
        Position depart3 = new Position(8, 15);
        Position arrivee = carte.trouverArrivee();

        // BFS
        BFS bfs = new BFS(carte, depart);
        BFS bfs2 = new BFS(carte, depart2);
        BFS bfs3 = new BFS(carte, depart3);

        // Chemins
        ArrayList<Position> chemin = bfs.cheminDeSourceVersCible(arrivee);
        ArrayList<Position> chemin2 = bfs3.cheminDeSourceVersCible(arrivee);
        ArrayList<Position> chemin3 = bfs2.cheminDeSourceVersCible(arrivee);

        // Creation de la partie
        partie = new Partie(chemin, chemin2, chemin3);
    }

    //cmd nouveau code
    // Relie le bouton d annulation a la commande et a sa disponibilite observable.
    private void initialiserBoutons() {
        btnVague.setOnAction(actionEvent -> {
            partie.lancerProchaineVague();
        });

        btnRecommencer.setOnAction(actionEvent -> {
            recommencer();
        });

        btnCanon.setOnAction(actionEvent -> {
            tourSelectionne = TypeTour.CANON;
            strategieClic = new StrategiePlacement();
        });

        btnArcher.setOnAction(actionEvent -> {
            tourSelectionne = TypeTour.ARCHER;
            strategieClic = new StrategiePlacement();
        });

        btnGlace.setOnAction(actionEvent -> {
            tourSelectionne = TypeTour.GLACE;
            strategieClic = new StrategiePlacement();
        });

        btnPoison.setOnAction(actionEvent -> {
            tourSelectionne = TypeTour.POISON;
            strategieClic = new StrategiePlacement();
        });

        btnFeu.setOnAction(actionEvent -> {
            tourSelectionne = TypeTour.FEU;
            strategieClic = new StrategiePlacement();
        });

        btnFoudre.setOnAction(actionEvent -> {
            tourSelectionne = TypeTour.FOUDRE;
            strategieClic = new StrategiePlacement();
        });

        btnExplosive.setOnAction(actionEvent -> {
            tourSelectionne = TypeTour.EXPLOSIVE;
            strategieClic = new StrategiePlacement();
        });

        btnTDE.setOnAction(actionEvent -> {
            tourSelectionne = TypeTour.TDE;
            strategieClic = new StrategiePlacement();
        });

        btnAcheterCase.setOnAction(actionEvent -> {
            strategieClic = new StrategieAchat();
        });

        btnAmeliorer.setOnAction(actionEvent -> {
            strategieClic = new StrategieAmelioration();
        });

        btnCiblage.setOnAction(actionEvent -> {
            strategieClic = new StrategieChangerCiblage();
            labelCiblage.setText("Clique sur une tour");
        });

        btnNiveau.setOnAction(actionEvent -> {
            partie.changerNiveau();
            btnNiveau.setText("Niveau : " + partie.getNiveau());
            carteVue.changerDecor(partie.getNiveau());
        });

        btnBouleFeu.setOnAction(event -> {
            strategieClic = new StrategieSort(Sort.BOULE_FEU);
        });

        btnBouleNeige.setOnAction(event -> {
            strategieClic = new StrategieSort(Sort.BOULE_NEIGE);
        });

        btnAnnuler.setOnAction(actionEvent -> {
            partie.annulerDernierPlacement();
        });
        btnAnnuler.disableProperty().bind(Bindings.createBooleanBinding(
                () -> !partie.peutAnnulerPlacement(),
                partie.annulationPossibleProperty(), partie.vagueEnCoursProperty(),
                partie.etatPartieProperty()));
    }

    //obs nouveau code
    // Le constructeur de RubisVue installe la liaison automatique du label.
    private void initialiserVues() {
        rubisVue = new RubisVue(partie, labelRubis);

        MonstreVue monstreVue = new MonstreVue(partie, paneSprites);
        toursVue = new ToursVue(partie, paneSprites);
        ProjectileVue projectileVue = new ProjectileVue(partie, paneSprites);
        coeurVue = new CoeurVue(partie, paneCoeurs);
        effetSortVue = new EffetSortVue(paneDecoration);
    }

    private void initialiserClicsSouris() {
        paneCarte.setOnMouseClicked(event -> {
            Position position = calculerPositionClic(event.getX(), event.getY());
            gererClicCarte(position);
        });

        paneSprites.setOnMouseClicked(event -> {
            Position position = calculerPositionClic(event.getX(), event.getY());
            gererClicCarte(position);
        });
    }

    //obs nouveau code
    //eta nouveau code
    // L affichage des rubis observe le modele et l etat de defaite gere son propre delai.
    private void initialiserGameLoop() {
        AnimationTimer gameLoop = new AnimationTimer() {
            long dernierTemps = 0;
            long ticke = 1_000_000_000 / 60;

            @Override
            public void handle(long tempActuel) {
                if (tempActuel - dernierTemps >= ticke) {
                    partie.mettreAJour();

                    btnAcheterCase.setText("Acheter case - " + partie.getPrixCase());
                    btnAmeliorer.setText("Amelioration - " + partie.getPrixAmelioration());

                    if (partie.defaiteProperty().get()) {
                        int tempsDefaite = partie.getCompteur() - partie.getCompteurDefaite();
                        carteVue.timerRecommencer(tempsDefaite);

                    }

                    partie.setCompteurPlusPlus();
                    dernierTemps = tempActuel;
                }
            }
        };

        gameLoop.start();
    }

    //eta nouveau code
    // L interface reagit aux transitions du modele sans declencher un second redemarrage.
    public void initialiserListeners() {
        partie.vagueEnCoursProperty().addListener((obs, old, now) -> mettreAjourBoutonsPartie());
        partie.toutesLesVaguesTermineProperty().addListener((obs, old, now) -> mettreAjourBoutonsPartie());
        partie.portailMortProperty().addListener((obs, old, now) -> mettreAjourBoutonsPartie());

        partie.etatPartieProperty().addListener((obs, ancien, actuel) -> {
            if (actuel instanceof EtatEnCours) {
                reinitialiserAffichage();
                activerToutLesBoutons();
            } else {
                desactiverToutLesBoutons();
                if (actuel instanceof EtatVictoire) {
                    carteVue.ajouterEcranVictoire();
                } else if (actuel instanceof EtatDefaite) {
                    carteVue.ajouterEcranDefaite();
                }
            }
            mettreAjourBoutonsPartie();
        });
    }


    //obs nouveau code
    // La modification du solde met a jour le label sans appel manuel.
    private void acheterCase(Position position) {
        partie.acheterCase(position);
        carteVue.viderCarte();
        carteVue.dessinerCarte();

        strategieClic = new StrategiePlacement();
    }

    //obs nouveau code
    // La modification du solde met a jour le label sans appel manuel.
    private void placerTour(Position position) {
        Tour tour = creerTourSelectionnee(tourSelectionne, position);
        boolean placeDisponible = partie.placerTour(tour);
        if (placeDisponible) {
            System.out.println("placement");
        } else {
            System.out.println("null");
        }
    }

    private void gererClicCarte(Position position) {
        strategieClic.executer(position);
    }

    private Position calculerPositionClic(double x, double y) {
        int colonne = (int) (x / TAILLE_TUILE);
        int ligne = (int) (y / TAILLE_TUILE);

        return new Position(colonne, ligne);
    }

    private void ameliorerTourCliquee(Position position) {
        Tour tour = partie.trouverTour(position);

        if (tour != null) {
            partie.faireAmeliorerTours(tour);
            toursVue.MiseAjourImage(tour);
        }

        strategieClic = new StrategiePlacement();
    }

    public Tour creerTourSelectionnee(TypeTour tourSelectionnee, Position position) {
        return fabriqueTour.creerTour(tourSelectionnee, position);
    }



    private void mettreAjourBoutonsPartie() {
        mettreAJourBoutonRecommencer();
        mettreAJourBoutonVague();
    }

    public void mettreAJourBoutonVague() {
        if (partie.getVagueEnCours() || partie.getIndiceVague() != 0 || defaiteLance) {
            btnNiveau.setDisable(true);
        } else {
            btnNiveau.setDisable(false);
        }
        if (partie.getToutesLesVaguesTerminees()) {
            btnVague.setDisable(true);
            btnVague.setText("Vagues terminées");
        } else if (partie.getVagueEnCours()) {
            btnVague.setDisable(true);
            btnVague.setText("Vague " + partie.getIndiceVaguePlusUn() + " en cours");
        } else if (this.defaiteLance) {
            btnVague.setDisable(true);
        } else {
            btnVague.setDisable(false);
            btnVague.setText("Lancer vague " + partie.getIndiceVaguePlusUn());
        }
    }

    //eta nouveau code
    // Le redemarrage manuel reste disponible apres une victoire.
    public void mettreAJourBoutonRecommencer() {
        btnRecommencer.setDisable(partie.getVagueEnCours()
                || partie.getEtatPartie() instanceof EtatPortailDetruit
                || partie.getEtatPartie() instanceof EtatDefaite);
    }

    //eta nouveau code
    // Demande le redemarrage au modele ; le changement d etat actualise la vue.
    public void recommencer() {
        partie.recommnencer();
    }

    public void desactiverToutLesBoutons() {
        changerEtatBoutons(true);
    }

    public void activerToutLesBoutons() {
        changerEtatBoutons(false);
    }

    public void changerEtatBoutons(boolean desactiver) {
        btnRecommencer.setDisable(desactiver);
        btnVague.setDisable(desactiver);
        btnArcher.setDisable(desactiver);
        btnCanon.setDisable(desactiver);
        btnAcheterCase.setDisable(desactiver);
        btnGlace.setDisable(desactiver);
        btnPoison.setDisable(desactiver);
        btnFeu.setDisable(desactiver);
        btnAmeliorer.setDisable(desactiver);
        btnBouleFeu.setDisable(desactiver);
        btnBouleNeige.setDisable(desactiver);
        if (btnCiblage != null) {
            btnCiblage.setDisable(desactiver);
        }
        if (btnFoudre != null) {
            btnFoudre.setDisable(desactiver);
        }
        if (btnExplosive != null) {
            btnExplosive.setDisable(desactiver);
        }
        if (btnTDE != null) {
            btnTDE.setDisable(desactiver);
        }
        this.defaiteLance = desactiver;
        btnNiveau.setDisable(desactiver);
    }

    //eta nouveau code
    // Reconstruit l affichage seulement apres la reinitialisation complete du modele.
    private void reinitialiserAffichage() {
        carteVue.retirerEcranDefaite();
        carteVue.retirerEcranVictoire();
        carteVue.viderCarte();
        carteVue.dessinerCarte();
        paneSprites.getChildren().clear();
        coeurVue.mettreAJourPvPortail(partie.getPvPortail());
        btnNiveau.setText("Niveau : " + partie.getNiveau());
        carteVue.changerDecor(partie.getNiveau());
        strategieClic = new StrategiePlacement();
        labelCiblage.setText("Ciblage : aucune tour");
    }
}
