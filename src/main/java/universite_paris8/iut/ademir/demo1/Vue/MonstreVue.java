package universite_paris8.iut.ademir.demo1.Vue;

import javafx.collections.ListChangeListener;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import universite_paris8.iut.ademir.demo1.Main;
import universite_paris8.iut.ademir.demo1.Modele.Jeu.Partie;
import universite_paris8.iut.ademir.demo1.Modele.Monstres.*;
import javafx.scene.shape.Rectangle;
import universite_paris8.iut.ademir.demo1.Modele.Monstres.MonstresRapides.*;
import universite_paris8.iut.ademir.demo1.Modele.Monstres.MonstresResistants.*;
import universite_paris8.iut.ademir.demo1.Modele.Monstres.MonstresSoigneurs.*;

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


    private Image imageMonstreEnFeu(Monstre monstre) {
        Image image = null;
        if (monstre instanceof ZombieRapide) {
            image = new Image(Main.class.getResourceAsStream("Monstres/zombieRapideFeu.png"));
        } else if (monstre instanceof AraigneeRapide) {
            image = new Image(Main.class.getResourceAsStream("Monstres/araigneeRapideFeu.png"));
        } else if (monstre instanceof SqueletteRapide) {
            image = new Image(Main.class.getResourceAsStream("Monstres/squeletteRapideFeu.png"));
        } else if (monstre instanceof PillagerRapide) {
            image = new Image(Main.class.getResourceAsStream("Monstres/pillagerRapideFeu.png"));
        } else if (monstre instanceof ChauveSourisRapide) {
            image = new Image(Main.class.getResourceAsStream("Monstres/chauveSourisRapideFeu.png"));
        } else if (monstre instanceof ZombieSoigneur) {
            image = new Image(Main.class.getResourceAsStream("Monstres/zombieSoigneurFeu.png"));
        } else if (monstre instanceof AraigneeSoigneuse) {
            image = new Image(Main.class.getResourceAsStream("Monstres/araigneeSoigneuseFeu.png"));
        } else if (monstre instanceof SqueletteSoigneur) {
            image = new Image(Main.class.getResourceAsStream("Monstres/squeletteSoigneurFeu.png"));
        } else if (monstre instanceof PillagerSoigneur) {
            image = new Image(Main.class.getResourceAsStream("Monstres/pillagerSoigneurFeu.png"));
        } else if (monstre instanceof ChauveSourisSoigneuse) {
            image = new Image(Main.class.getResourceAsStream("Monstres/chauveSourisSoigneuseFeu.png"));
        } else if (monstre instanceof ZombieResistant) {
            image = new Image(Main.class.getResourceAsStream("Monstres/zombieResistantFeu.png"));
        } else if (monstre instanceof AraigneeResistante) {
            image = new Image(Main.class.getResourceAsStream("Monstres/araigneeResistanteFeu.png"));
        } else if (monstre instanceof SqueletteResistant) {
            image = new Image(Main.class.getResourceAsStream("Monstres/squeletteResistantFeu.png"));
        } else if (monstre instanceof PillagerResistant) {
            image = new Image(Main.class.getResourceAsStream("Monstres/pillagerResistantFeu.png"));
        } else if (monstre instanceof ChauveSourisResistante) {
            image = new Image(Main.class.getResourceAsStream("Monstres/chauveSourisResistanteFeu.png"));
        } else if (monstre instanceof Zombie) {
            image = new Image(Main.class.getResourceAsStream("Monstres/zombieFeu.png"));
        } else if (monstre instanceof Araignee) {
            image = new Image(Main.class.getResourceAsStream("Monstres/araigneeFeu.png"));
        } else if (monstre instanceof Squelette) {
            image = new Image(Main.class.getResourceAsStream("Monstres/squeletteFeu.png"));
        } else if (monstre instanceof Pillager) {
            image = new Image(Main.class.getResourceAsStream("Monstres/pillagerFeu.png"));
        } else if (monstre instanceof Boss) {
            image = new Image(Main.class.getResourceAsStream("Monstres/bossFeu.png"));
        } else if (monstre instanceof ChauveSouris) {
            image = new Image(Main.class.getResourceAsStream("Monstres/chauveSourisFeu.png"));
        }
        return image;
    }

    public Image imageMonstre(Monstre monstre) {

        if (monstre.estEnFeu()) {
            return imageMonstreEnFeu(monstre);
        }

        Image image = null;


        if (monstre instanceof ZombieRapide) {
            if (monstre.getMonstreGlacee()) {
                image = new Image(Main.class.getResourceAsStream("Monstres/zombieRapideGlace.png"));
            } else {
                image = new Image(Main.class.getResourceAsStream("Monstres/zombieRapide.png"));
            }
        } else if (monstre instanceof AraigneeRapide) {
            if (monstre.getMonstreGlacee()) {
                image = new Image(Main.class.getResourceAsStream("Monstres/araigneeRapideGlace.png"));
            } else {
                image = new Image(Main.class.getResourceAsStream("Monstres/araigneeRapide.png"));
            }
        } else if (monstre instanceof SqueletteRapide) {
            if (monstre.getMonstreGlacee()) {
                image = new Image(Main.class.getResourceAsStream("Monstres/squeletteRapideGlace.png"));
            } else {
                image = new Image(Main.class.getResourceAsStream("Monstres/squeletteRapide.png"));
            }
        } else if (monstre instanceof PillagerRapide) {
            if (monstre.getMonstreGlacee()) {
                image = new Image(Main.class.getResourceAsStream("Monstres/pillagerRapideGlace.png"));
            } else {
                image = new Image(Main.class.getResourceAsStream("Monstres/pillagerRapide.png"));
            }
        } else if (monstre instanceof ChauveSourisRapide) {
            if (monstre.getMonstreGlacee()) {
                image = new Image(Main.class.getResourceAsStream("Monstres/chauveSourisRapideGlace.png"));
            } else {
                image = new Image(Main.class.getResourceAsStream("Monstres/chauveSourisRapide.png"));
            }
        } else if (monstre instanceof ZombieSoigneur) {
            if (monstre.getMonstreGlacee()) {
                image = new Image(Main.class.getResourceAsStream("Monstres/zombieSoigneurGlace.png"));
            } else {
                image = new Image(Main.class.getResourceAsStream("Monstres/zombieSoigneur.png"));
            }
        } else if (monstre instanceof AraigneeSoigneuse) {
            if (monstre.getMonstreGlacee()) {
                image = new Image(Main.class.getResourceAsStream("Monstres/araigneeSoigneuseGlace.png"));
            } else {
                image = new Image(Main.class.getResourceAsStream("Monstres/araigneeSoigneuse.png"));
            }
        } else if (monstre instanceof SqueletteSoigneur) {
            if (monstre.getMonstreGlacee()) {
                image = new Image(Main.class.getResourceAsStream("Monstres/squeletteSoigneurGlace.png"));
            } else {
                image = new Image(Main.class.getResourceAsStream("Monstres/squeletteSoigneur.png"));
            }
        } else if (monstre instanceof PillagerSoigneur) {
            if (monstre.getMonstreGlacee()) {
                image = new Image(Main.class.getResourceAsStream("Monstres/pillagerSoigneurGlace.png"));
            } else {
                image = new Image(Main.class.getResourceAsStream("Monstres/pillagerSoigneur.png"));
            }
        } else if (monstre instanceof ChauveSourisSoigneuse) {
            if (monstre.getMonstreGlacee()) {
                image = new Image(Main.class.getResourceAsStream("Monstres/chauveSourisSoigneuseGlace.png"));
            } else {
                image = new Image(Main.class.getResourceAsStream("Monstres/chauveSourisSoigneuse.png"));
            }
        } else if (monstre instanceof ZombieResistant) {
            image = new Image(Main.class.getResourceAsStream("Monstres/zombieResistant.png"));
        } else if (monstre instanceof AraigneeResistante) {
            image = new Image(Main.class.getResourceAsStream("Monstres/araigneeResistante.png"));
        } else if (monstre instanceof SqueletteResistant) {
            image = new Image(Main.class.getResourceAsStream("Monstres/squeletteResistant.png"));
        } else if (monstre instanceof PillagerResistant) {
            image = new Image(Main.class.getResourceAsStream("Monstres/pillagerResistant.png"));
        } else if (monstre instanceof ChauveSourisResistante) {
            image = new Image(Main.class.getResourceAsStream("Monstres/ChauveSourisResistante.png"));
        } else if (monstre instanceof Zombie) {
            if (monstre.getMonstreGlacee()) {
                image = new Image(Main.class.getResourceAsStream("Monstres/zombieGlace.png"));
            } else {
                image = new Image(Main.class.getResourceAsStream("Monstres/zombie.png"));
            }
        } else if (monstre instanceof Araignee) {
            if (monstre.getMonstreGlacee()) {
                image = new Image(Main.class.getResourceAsStream("Monstres/araigneeGlace.png"));
            } else {
                image = new Image(Main.class.getResourceAsStream("Monstres/araignee.png"));
            }
        } else if (monstre instanceof Squelette) {
            if (monstre.getMonstreGlacee()) {
                image = new Image(Main.class.getResourceAsStream("Monstres/squeletteGlace.png"));
            } else {
                image = new Image(Main.class.getResourceAsStream("Monstres/squelette.png"));
            }
        } else if (monstre instanceof Pillager) {
            if (monstre.getMonstreGlacee()) {
                image = new Image(Main.class.getResourceAsStream("Monstres/pillagerGlace.png"));
            } else {
                image = new Image(Main.class.getResourceAsStream("Monstres/pillager.png"));
            }
        } else if (monstre instanceof Boss) {
            if (monstre.getMonstreGlacee()) {
                image = new Image(Main.class.getResourceAsStream("Monstres/bossGlace.png"));
            } else {
                image = new Image(Main.class.getResourceAsStream("Monstres/boss.png"));
            }
        } else if (monstre instanceof ChauveSouris) {
            if (monstre.getMonstreGlacee()) {
                image = new Image(Main.class.getResourceAsStream("Monstres/ChauveSourisGlace.png"));
            } else {
                image = new Image(Main.class.getResourceAsStream("Monstres/ChauveSouris.png"));
            }

        }
        return image;
    }
}