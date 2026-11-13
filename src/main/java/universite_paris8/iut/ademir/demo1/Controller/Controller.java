package universite_paris8.iut.ademir.demo1.Controller;

import javafx.animation.AnimationTimer;
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
import universite_paris8.iut.ademir.demo1.Modele.Tour.*;
import universite_paris8.iut.ademir.demo1.Vue.*;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    private static final int TAILLE_TUILE = 64;

    // Pane
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

    private Carte carte;
    private Partie partie;
    private CarteVue carteVue;

    private TypeTour tourSelectionne;
    private TourFactory tourFactory = new TourFactory();
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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        carte = new Carte();
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

    }

    private void initialiserVues() {
        rubisVue = new RubisVue(partie, labelRubis);
        rubisVue.afficherRubis();

        MonstreVue monstreVue = new MonstreVue(partie, paneSprites);
        toursVue = new ToursVue(partie, paneSprites);
        ProjectileVue projectileVue = new ProjectileVue(partie, paneSprites);
        coeurVue = new CoeurVue(partie, paneCoeurs);
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

    private void initialiserGameLoop() {
        AnimationTimer gameLoop = new AnimationTimer() {
            long dernierTemps = 0;
            long ticke = 1_000_000_000 / 60;

            @Override
            public void handle(long tempActuel) {
                if (tempActuel - dernierTemps >= ticke) {
                    partie.mettreAJour();
                    rubisVue.afficherRubis();

                    btnAcheterCase.setText("Acheter case - " + partie.getPrixCase());
                    btnAmeliorer.setText("Amelioration - " + partie.getPrixAmelioration());

                    if (partie.defaiteProperty().get()) {
                        int tempsDefaite = partie.getCompteur() - partie.getCompteurDefaite();
                        carteVue.timerRecommencer(tempsDefaite);

                        if (tempsDefaite > 175) {
                            partie.setDefaiteProperty(false);
                        }
                    }

                    partie.setCompteurPlusPlus();
                    dernierTemps = tempActuel;
                }
            }
        };

        gameLoop.start();
    }

    public void initialiserListeners() {
        partie.vagueEnCoursProperty().addListener((obs, old, now) -> {
            mettreAjourBoutonsPartie();
        });

        partie.toutesLesVaguesTermineProperty().addListener((obs, old, now) -> {
            mettreAjourBoutonsPartie();
        });

        partie.portailMortProperty().addListener((obs, old, now) -> {
            mettreAjourBoutonsPartie();
        });

        partie.victoireProperty().addListener((obs, old, now) -> {
            desactiverToutLesBoutons();
            carteVue.ajouterEcranVictoire();
        });

        partie.defaiteProperty().addListener((obs, old, now) -> {
            if (now) {
                desactiverToutLesBoutons();
                carteVue.ajouterEcranDefaite();
            } else {
                carteVue.retirerEcranDefaite();
                recommencer();
                activerToutLesBoutons();
                mettreAJourBoutonVague();
            }
        });
    }


    private void acheterCase(Position position) {
        partie.acheterCase(position, carte);
        carteVue.viderCarte();
        carteVue.dessinerCarte();
        rubisVue.afficherRubis();

        strategieClic = new StrategiePlacement();
    }

    private void placerTour(Position position) {
        Tour tour = creerTourSelectionnee(tourSelectionne, position);
        boolean placeDisponible = partie.placerTour(tour, carte);
        if (placeDisponible) {
            rubisVue.afficherRubis();
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
        return tourFactory.creerTour(tourSelectionnee, position);
    }



    private void mettreAjourBoutonsPartie() {
        mettreAJourBoutonRecommencer();
        mettreAJourBoutonVague();
    }

    public void mettreAJourBoutonVague() {
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

    public void mettreAJourBoutonRecommencer() {
        if (partie.getVagueEnCours()) {
            btnRecommencer.setDisable(true);
        } else if (this.defaiteLance) {
            btnRecommencer.setDisable(true);
        } else {
            btnRecommencer.setDisable(false);
        }
    }

    public void recommencer() {
        partie.recommnencer();
        carte.caseBloquer();
        carteVue.viderCarte();
        carteVue.dessinerCarte();
        paneSprites.getChildren().clear();
        coeurVue.mettreAJourPvPortail(3);
        btnVague.setText("Lancer vague " + partie.getIndiceVaguePlusUn());


        strategieClic = new StrategiePlacement();
        if (labelCiblage != null) {
            labelCiblage.setText("Ciblage : aucune tour");
        }
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
        if (btnCiblage != null) {
            btnCiblage.setDisable(desactiver);
        }
        this.defaiteLance = desactiver;
    }
}
