package universite_paris8.iut.ademir.demo1.Vue;

import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;
import javafx.scene.Node;
import java.util.ArrayList;
import java.util.List;

import universite_paris8.iut.ademir.demo1.Main;
import universite_paris8.iut.ademir.demo1.Modele.Cartes.Carte;

public class CarteVue {

    private Carte carte;
    private TilePane paneCarte;
    private Pane paneDecoration;
    private Label labelDefaite;
    private Label labelVictoire;
    private int niveau = 1;
    private final List<Node> decorationsAffichees = new ArrayList<>();

    public CarteVue(Carte carte, TilePane paneCarte , Pane paneDecoration) {
        this.carte = carte;
        this.paneCarte = paneCarte;
        this.paneDecoration = paneDecoration;
        this.labelDefaite = new Label("Vous êtes mort");
        labelDefaite.setLayoutX(600);
        labelDefaite.setLayoutY(425);
        labelDefaite.setStyle("-fx-font-size: 75px;");

        this.labelVictoire = new Label("VICTOIRE !");
        labelVictoire.setLayoutX(550);
        labelVictoire.setLayoutY(400);
        labelVictoire.setStyle("-fx-font-size: 150px;");
    }

    public void changerDecor(int nouveauNiveau) {
        niveau = nouveauNiveau;
        viderCarte();
        dessinerCarte();
    }

    private Image chargerImage(String nom) {
        return new Image(Main.class.getResourceAsStream("Tuiles/Bloc/" + nom));
    }

    public void dessinerCarte() {
        // Supprime uniquement les anciennes decorations, sans toucher aux labels.
        paneDecoration.getChildren().removeAll(decorationsAffichees);
        decorationsAffichees.clear();
        paneCarte.getChildren().clear();

        Image herbe;
        Image eau;
        Image arbre;
        Image pierre;

        switch (niveau) {
            case 2:
                herbe = chargerImage("desert_sable.png");
                eau = chargerImage("desert_sable_mouvant.gif");
                arbre = chargerImage("desert_cactus.png");
                pierre = arbre;
                break;
            case 3:
                herbe = chargerImage("neige_glace.png");
                eau = chargerImage("glace_eau.gif");
                arbre = chargerImage("arbre_glace.png");
                pierre = arbre;
                break;
            case 4:
                herbe = chargerImage("terre_feu.png");
                eau = chargerImage("lave_feu.gif");
                arbre = chargerImage("arbre_feu.png");
                pierre = arbre;
                break;
            case 5:
                herbe = chargerImage("corrompu_sol.png");
                eau = chargerImage("corrompu_eau.gif");
                arbre = chargerImage("corrompu_arbre.png");
                pierre = arbre;
                break;
            default:
                herbe = chargerImage("herbe.png");
                eau = chargerImage("eau/Uberren.gif");
                arbre = chargerImage("pixel-arbre.png");
                pierre = eau;
                break;
        }

        Image cheminD = new Image(Main.class.getResourceAsStream("Tuiles/Bloc/SensChemin-D/chemin-D.png"));
        Image cheminD90 = new Image(Main.class.getResourceAsStream("Tuiles/Bloc/SensChemin-D/chemin-D90.png"));

        Image cheminL = new Image(Main.class.getResourceAsStream("Tuiles/Bloc/SensChemin-L/chemin-L.png"));
        Image cheminL45 = new Image(Main.class.getResourceAsStream("Tuiles/Bloc/SensChemin-L/chemin-L45.png"));
        Image cheminL90 = new Image(Main.class.getResourceAsStream("Tuiles/Bloc/SensChemin-L/chemin-L90.png"));
        Image cheminL180 = new Image(Main.class.getResourceAsStream("Tuiles/Bloc/SensChemin-L/chemin-L180.png"));

        Image cheminT = new Image(Main.class.getResourceAsStream("Tuiles/Bloc/SensChemin-T/chemin-T.png"));
        Image cheminT45 = new Image(Main.class.getResourceAsStream("Tuiles/Bloc/SensChemin-T/chemin-T45.png"));
        Image cheminT90 = new Image(Main.class.getResourceAsStream("Tuiles/Bloc/SensChemin-T/chemin-T90.png"));
        Image cheminT180 = new Image(Main.class.getResourceAsStream("Tuiles/Bloc/SensChemin-T/chemin-T180.png"));

        Image cheminArriver = new Image(Main.class.getResourceAsStream("Tuiles/Bloc/SensChemin-Arriver/chemin-Arriver.png"));
        Image cheminArriver45 = new Image(Main.class.getResourceAsStream("Tuiles/Bloc/SensChemin-Arriver/chemin-Arriver45.png"));
        Image cheminArriver90 = new Image(Main.class.getResourceAsStream("Tuiles/Bloc/SensChemin-Arriver/chemin-Arriver90.png"));
        Image cheminArriver180 = new Image(Main.class.getResourceAsStream("Tuiles/Bloc/SensChemin-Arriver/chemin-Arriver180.png"));

        Image cheminDepart = new Image(Main.class.getResourceAsStream("Tuiles/Bloc/SensChemin-Depart/chemin-Depart.png"));
        Image cheminDepart45 = new Image(Main.class.getResourceAsStream("Tuiles/Bloc/SensChemin-Depart/chemin-Depart45.png"));
        Image cheminDepart90 = new Image(Main.class.getResourceAsStream("Tuiles/Bloc/SensChemin-Depart/chemin-Depart90.png"));
        Image cheminDepart180 = new Image(Main.class.getResourceAsStream("Tuiles/Bloc/SensChemin-Depart/chemin-Depart180.png"));

        Image EmplacementToursDebloquer = new Image(Main.class.getResourceAsStream("Tuiles/Bloc/EmplacementTours/TDebloquer.png"));
        Image EmplacementToursBloquer = new Image(Main.class.getResourceAsStream("Tuiles/Bloc/EmplacementTours/TBloquer.png"));

        int tailleTuile = 64;

        for (int ligne = 0; ligne < carte.getHauteur(); ligne++) {

            for (int col = 0; col < carte.getLargeur(); col++) {

                Image image = null;

                switch (carte.Tuile(col, ligne)) {

                    case 0:
                        image = herbe;
                        break;

                    case 1:
                        image = cheminD;
                        break;
                    case 2:
                        image = cheminD90;
                        break;

                    case 3:
                        image = cheminL;
                        break;

                    case 4:
                        image = cheminL45;
                        break;

                    case 5:
                        image = cheminL90;
                        break;

                    case 6:
                        image = cheminL180;
                        break;

                    case 7:
                        image = cheminT;
                        break;

                    case 8:
                        image = cheminT45;
                        break;

                    case 9:
                        image = cheminT90;
                        break;

                    case 10:
                        image = cheminT180;
                        break;

                    case 11:
                        image = cheminArriver;
                        break;

                    case 12:
                        image = cheminArriver45;
                        break;

                    case 13:
                        image = cheminArriver90;
                        break;

                    case 14:
                        image = cheminArriver180;
                        break;

                    case 15:
                        image = cheminDepart;
                        break;

                    case 16:
                        image = cheminDepart45;
                        break;

                    case 17:
                        image = cheminDepart90;
                        break;

                    case 18:
                        image = cheminDepart180;
                        break;

                    case 19:
                        image = EmplacementToursDebloquer;
                        break;

                    case 20:
                        image = EmplacementToursBloquer;
                        break;

                    case 21:
                        image = eau;
                        break;

                    case 22:
                        image = arbre;
                        break;

                    case 23:
                        image = pierre;
                        break;

                }

                ImageView tuile = new ImageView(image);
                tuile.setFitWidth(tailleTuile);
                tuile.setFitHeight(tailleTuile);
                paneCarte.getChildren().add(tuile);
            }
        }

        for (int ligne = 0; ligne < carte.getHauteurDecoration(); ligne++) {

            for (int col = 0; col < carte.getLargeurDecoration(); col++) {

                Image image = null;

                switch (carte.TuileDecorations(col, ligne)) {

                    case 22:
                        image = arbre;
                        break;

                    case 23:
                        image = pierre;
                        break;
                }

                if (image == null) {
                    continue;
                }

                ImageView tuileDecoration = new ImageView(image);
                tuileDecoration.setLayoutX(col * 64);
                tuileDecoration.setLayoutY(ligne * 60);
                tuileDecoration.setFitWidth(64);
                tuileDecoration.setFitHeight(90);

                paneDecoration.getChildren().add(tuileDecoration);
                decorationsAffichees.add(tuileDecoration);
            }
        }
    }

    public void timerRecommencer (long tempsQuiReste) {
        labelDefaite.setText("Vous êtes mort - " + (5 -(tempsQuiReste / 33))); // 33 car je trouve que c le mieux pour que ca parte pile au bon moment,
    }

    public void ajouterEcranDefaite() {
        if (!paneDecoration.getChildren().contains(labelDefaite)) {
            paneDecoration.getChildren().add(labelDefaite);
        }
    }

    public void ajouterEcranVictoire() {
        if (!paneDecoration.getChildren().contains(labelVictoire)) {
            paneDecoration.getChildren().add(labelVictoire);
        }
    }

    public void retirerEcranDefaite() {
        paneDecoration.getChildren().remove(this.labelDefaite);
    }

    public void viderCarte() {
        paneCarte.getChildren().clear();
    }

    public void affichageViePortail(){

    }

    //eta nouveau code
    // Retire aussi l ecran de victoire lors du redemarrage.
    public void retirerEcranVictoire() {
        paneDecoration.getChildren().remove(labelVictoire);
    }
}