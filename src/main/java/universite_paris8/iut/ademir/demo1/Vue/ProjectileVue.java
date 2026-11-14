package universite_paris8.iut.ademir.demo1.Vue;

import javafx.animation.AnimationTimer;
import javafx.collections.ListChangeListener;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.transform.Rotate;
import universite_paris8.iut.ademir.demo1.Main;
import universite_paris8.iut.ademir.demo1.Modele.Jeu.Partie;
import universite_paris8.iut.ademir.demo1.Modele.Projectile.*;
import javafx.scene.shape.Rectangle;
import javafx.scene.paint.Color;
import universite_paris8.iut.ademir.demo1.Modele.Monstres.Monstre;
import universite_paris8.iut.ademir.demo1.Modele.Tour.Tour;
import universite_paris8.iut.ademir.demo1.Modele.Tour.TourTDE;

import java.util.IdentityHashMap;
import java.util.Map;

public class ProjectileVue {

    private static final int TAILLE_PROJECTILE = 32;
    private static final int TAILLE_LASER = 6;
    // Associer chaque projectile à son sprite
    private Map<Projectile, ImageView> sprites = new IdentityHashMap<>();
    // Éviter plusieurs lasers sur la même cible
    private Map<Monstre, AnimationTimer> lasers = new IdentityHashMap<>();

    private Partie partie;
    private Pane paneSprites;

    public ProjectileVue(Partie partie, Pane paneSprites) {
        this.partie = partie;
        this.paneSprites = paneSprites;
        this.partie.getProjectiles().addListener(listener);
    }

    private ListChangeListener<Projectile> listener = new ListChangeListener<Projectile>() {

        @Override
        public void onChanged(Change<? extends Projectile> c) {

            while (c.next()) {

                if (c.wasAdded()) {
                    for (int i = 0; i < c.getAddedSubList().size(); i++) {
                        Projectile projectile = c.getAddedSubList().get(i);
                        creerSpriteProjectile(projectile);
                    }
                }

                if (c.wasRemoved()) {
                    supprimerSpritesProjectiles();
                    afficherProjectiles();
                }
            }
        }
    };

    public void creerSpriteProjectile(Projectile projectile) {
        if (projectile instanceof ProjectileLazer) {
            creerSpriteLazer((ProjectileLazer) projectile);
            return;
        }
        Image image = imageProjectile(projectile);
        if (image == null) {
            return;
        }
        ImageView sprite = new ImageView(image);
        sprite.setId("projectile");
        sprite.setFitWidth(TAILLE_PROJECTILE);
        sprite.setFitHeight(TAILLE_PROJECTILE);
        sprite.layoutXProperty().bind(projectile.xProperty());
        sprite.layoutYProperty().bind(projectile.yProperty());
        paneSprites.getChildren().add(sprite);
    }

    public Image imageProjectile(Projectile projectile) {

        Image image = null;

        if (projectile instanceof ProjectileBoulet) {
            image = new Image(Main.class.getResourceAsStream("Projectiles/boulet.png"));
        }
        if (projectile instanceof ProjectilePoison) {
            image = new Image(Main.class.getResourceAsStream("Projectiles/poison.png"));
        }
        if (projectile instanceof ProjectileGlace) {
            image = new Image(Main.class.getResourceAsStream("Projectiles/glace.png"));
        }
        if (projectile instanceof ProjectileFleche) {
            image = new Image(Main.class.getResourceAsStream("Projectiles/fleche.png"));
        }
        if (projectile instanceof ProjectileFeu) {
            image = new Image(Main.class.getResourceAsStream("Projectiles/feu.png")); // a modifié avec un projectileFeu
        }
        if (projectile instanceof ProjectileFoudre) {
            image = new Image(Main.class.getResourceAsStream("Projectiles/foudre.png")); // a modifié avec un projectileFoudre
        }
        if (projectile instanceof ProjectileExplosion) {
            image = new Image(Main.class.getResourceAsStream("Projectiles/bombe.png"));
        }

        return image;
    }


    public void supprimerSpritesProjectiles() {

        for (int i = paneSprites.getChildren().size() - 1; i >= 0; i--) {

            if ("projectile".equals(paneSprites.getChildren().get(i).getId())) {
                paneSprites.getChildren().remove(i);
            }
        }

        sprites.clear();
    }

    public void afficherProjectiles() {
        for (int i = 0; i < partie.getProjectiles().size(); i++) {
            Projectile projectile = partie.getProjectiles().get(i);
            creerSpriteProjectile(projectile);
        }
    }


    private void creerSpriteLazer(ProjectileLazer projectile) {

        Monstre cible = projectile.getCible();

        if (cible == null || cible.estMort()) {
            return;
        }

        // Si un laser suit déjà cette cible, on ne le recrée pas
        if (lasers.containsKey(cible)) {
            return;
        }

        Rectangle laser = new Rectangle();

        laser.setId("laser");
        laser.setWidth(0);
        laser.setHeight(TAILLE_LASER);
        laser.setFill(Color.RED);
        laser.setOpacity(0.85);

        // Rotation autour du point de départ
        Rotate rotation = new Rotate(0, 0, 0);
        laser.getTransforms().add(rotation);

        // Position initiale de la tour
        // Le projectile vient d'être créé : ses coordonnées
        // correspondent encore à celles de la tour.
        double x1 = projectile.getX() + 32;
        double y1 = projectile.getY() + 32;

        laser.setX(0);
        laser.setY(-TAILLE_LASER / 2.0);

        laser.setLayoutX(x1);
        laser.setLayoutY(y1);

        paneSprites.getChildren().add(laser);

        // Actualisation du laser
        AnimationTimer animation = new AnimationTimer() {

            @Override
            public void handle(long now) {

                // Le monstre est mort ou n'existe plus
                if (cible.estMort()
                        || !partie.getMonstres().contains(cible)) {

                    paneSprites.getChildren().remove(laser);
                    lasers.remove(cible);
                    stop();

                    return;
                }

                // Position actuelle du centre du monstre
                double x2 = cible.getX() + 32;
                double y2 = cible.getY() + 32;

                // Distance entre la tour et le monstre
                double dx = x2 - x1;
                double dy = y2 - y1;

                double distance = Math.sqrt(dx * dx + dy * dy);

                // Le rectangle s'allonge ou se raccourcit
                laser.setWidth(distance);

                // Le rectangle pivote vers le monstre
                double angle = Math.toDegrees(Math.atan2(dy, dx));

                rotation.setAngle(angle);
            }
        };

        lasers.put(cible, animation);

        animation.start();
    }


}