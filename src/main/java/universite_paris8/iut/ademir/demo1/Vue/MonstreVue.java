package universite_paris8.iut.ademir.demo1.Vue;

import java.net.URL;

import javafx.collections.ListChangeListener;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import universite_paris8.iut.ademir.demo1.Main;
import universite_paris8.iut.ademir.demo1.Modele.Jeu.Partie;
import universite_paris8.iut.ademir.demo1.Modele.Monstres.*;
import javafx.scene.shape.Rectangle;

public class MonstreVue {

    private static final int TAILLE_TUILE = 64;

    private Partie partie;
    private Pane paneSprites;


    public MonstreVue(Partie partie, Pane paneSprites) {
        this.partie = partie;
        this.paneSprites = paneSprites;
        this.partie.getMonstres().addListener(listener);
    }

    ListChangeListener<Monstre> listener = new ListChangeListener<Monstre>() {

        @Override
        public void onChanged(Change<? extends Monstre> c) {
            while (c.next()) {

                if (c.wasAdded()) {
                    // Cree l'affichage de chaque monstre ajoute et dans le for each il ya un array list de tout les monstres ajouté
                    for (Monstre monstre : c.getAddedSubList()) {
                        creerSprite(monstre);
                    }
                }

                if (c.wasRemoved()) {
                    // Retirer l'affichage de chaque monstre supprime et dans le for each il ya un array list de tout les monstres supprimé
                    for (Monstre monstre : c.getRemoved()) {
                        supprimerAffichageMonstre(monstre);
                    }
                }
            }
        }
    };


    public void supprimerAffichageMonstre(Monstre m) {
        for (int i = paneSprites.getChildren().size() - 1; i >= 0; i--) {

            String id = paneSprites.getChildren().get(i).getId();

            if (id != null && (id.equals(m.getMonstreID()) || id.equals(m.getMonstreID() + "_fondVie") || id.equals(m.getMonstreID() + "_barreVie"))) {
                paneSprites.getChildren().remove(i);
            }
        }
    }


    public void creerSprite(Monstre monstre) {

        Image image = imageMonstre(monstre);

        if (image == null) {
            return;
        }

        ImageView sprite = new ImageView(image);
        sprite.setId(monstre.getMonstreID());

        // quand le monstre devient glace ou n est plus glace on change son image une seule fois
        monstre.glaceProperty().addListener((obs, old, now) -> {
            sprite.setImage(imageMonstre(monstre));
        });
        monstre.feuProperty().addListener((obs, old, now) -> {
            sprite.setImage(imageMonstre(monstre));
        });

        sprite.setFitWidth(TAILLE_TUILE);
        sprite.setFitHeight(TAILLE_TUILE);

        sprite.layoutXProperty().bind(monstre.xProperty());
        sprite.layoutYProperty().bind(monstre.yProperty());
        //affichage barre de vie:
        Rectangle fondVie = new Rectangle(40, 5);
        fondVie.setId(monstre.getMonstreID() + "_fondVie");
        fondVie.setFill(Color.BLACK);

        Rectangle barreVie = new Rectangle(40, 5);
        barreVie.setId(monstre.getMonstreID() + "_barreVie");
        barreVie.setFill(Color.RED);

        fondVie.layoutXProperty().bind(monstre.xProperty());
        fondVie.layoutYProperty().bind(monstre.yProperty());
        barreVie.layoutXProperty().bind(monstre.xProperty());
        barreVie.layoutYProperty().bind(monstre.yProperty());

        fondVie.setTranslateX(11);
        fondVie.setTranslateY(-8);
        barreVie.setTranslateX(11);
        barreVie.setTranslateY(-8);

        barreVie.widthProperty().bind(monstre.pvProperty().multiply(40.0 / monstre.getPvMax()));

        paneSprites.getChildren().add(fondVie);
        paneSprites.getChildren().add(barreVie);
        paneSprites.getChildren().add(sprite);
    }



    // Choisit l espece reelle, la variante exterieure et l etat feu/gel.
    //mpd nouveaucode
    public Image imageMonstre(Monstre monstre) {
        String espece = getNomEspece(monstre.getMonstreDeBase());
        if (espece == null) {
            System.out.printf("Image introuvable : espece de monstre inconnue.%n");
            return null;
        }
        String variante = getNomVariante(espece, monstre.getVariante());
        String chemin = getCheminImage(espece, variante, getNomEtat(monstre));
        URL ressource = Main.class.getResource(chemin);

        if (ressource == null) {
            System.out.printf("Image absente : %s ; utilisation de l image normale.%n", chemin);
            chemin = getCheminImage(espece, "", "");
            ressource = Main.class.getResource(chemin);
        }
        if (ressource == null) {
            System.out.printf("Image de remplacement absente : %s%n", chemin);
            return null;
        }
        return new Image(ressource.toExternalForm());
    }

    //mpd nouveaucode
    private String getNomEspece(Monstre monstre) {
        if (monstre instanceof Zombie) return "zombie";
        if (monstre instanceof Araignee) return "araignee";
        if (monstre instanceof Squelette) return "squelette";
        if (monstre instanceof Pillager) return "pillager";
        if (monstre instanceof ChauveSouris) return "chauveSouris";
        if (monstre instanceof Boss) return "boss";
        return null;
    }

    //mpd nouveaucode
    private String getNomVariante(String espece, VarianteMonstre variante) {
        boolean feminin = espece.equals("araignee") || espece.equals("chauveSouris");
        switch (variante) {
            case RAPIDE: return "Rapide";
            case RESISTANT: return feminin ? "Resistante" : "Resistant";
            case SOIGNEUR: return feminin ? "Soigneuse" : "Soigneur";
            default: return "";
        }
    }

    //mpd nouveaucode
    private String getNomEtat(Monstre monstre) {
        if (monstre.estEnFeu()) return "Feu";
        if (monstre.estGlace()) return "Glace";
        return "";
    }

    //mpd nouveaucode
    private String getCheminImage(String espece, String variante, String etat) {
        String nom = espece;
        if (espece.equals("chauveSouris")) {
            boolean imageNormale = variante.isEmpty() && !etat.equals("Feu");
            boolean imageResistante = variante.equals("Resistante") && etat.isEmpty();
            if (imageNormale || imageResistante) {
                nom = "ChauveSouris";
            }
        }
        return "Monstres/" + nom + variante + etat + ".png";
    }
}
