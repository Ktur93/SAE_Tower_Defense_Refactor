package universite_paris8.iut.ademir.demo1.Tests;

import javafx.beans.property.ReadOnlyIntegerProperty;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import universite_paris8.iut.ademir.demo1.Modele.Cartes.Carte;
import universite_paris8.iut.ademir.demo1.Modele.Cartes.Position;
import universite_paris8.iut.ademir.demo1.Modele.Jeu.Partie;
import universite_paris8.iut.ademir.demo1.Modele.Tour.TourCanon;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class RubisObservablesTest {
    private Partie partie;

    @BeforeEach
    public void preparer() {
        Carte.getInstance().caseBloquer();
        ArrayList<Position> chemin = new ArrayList<>();
        chemin.add(new Position(0, 0));
        chemin.add(new Position(1, 0));
        partie = new Partie(chemin, chemin, chemin);
    }

    @Test
    public void notificationsSuiventLesAjoutsRetraitsEtLeRedemarrage() {
        List<Integer> valeurs = new ArrayList<>();
        partie.rubisProperty().addListener((obs, ancien, actuel) -> valeurs.add(actuel.intValue()));

        partie.ajouterRubis(50);
        partie.retirerRubis(125);
        partie.recommnencer();

        assertEquals(List.of(450, 325, 400), valeurs);
        assertEquals(partie.getRubis(), partie.rubisProperty().get());
    }

    @Test
    public void montantRefuseNeNotifiePasUneModificationDuSolde() {
        List<Integer> valeurs = new ArrayList<>();
        partie.rubisProperty().addListener((obs, ancien, actuel) -> valeurs.add(actuel.intValue()));

        partie.ajouterRubis(-1);
        partie.retirerRubis(-1);
        partie.retirerRubis(401);

        assertEquals(400, partie.getRubis());
        assertTrue(valeurs.isEmpty());
    }

    @Test
    public void proprieteResteLaMemeApresUnRedemarrage() {
        ReadOnlyIntegerProperty propriete = partie.rubisProperty();
        partie.retirerRubis(10);
        partie.recommnencer();

        assertSame(propriete, partie.rubisProperty());
        assertEquals(400, propriete.get());
        partie.ajouterRubis(5);
        assertEquals(405, propriete.get());
    }

    @Test
    public void achatPlacementEtAnnulationNotifientLeurMontantExact() {
        List<Integer> valeurs = new ArrayList<>();
        partie.rubisProperty().addListener((obs, ancien, actuel) -> valeurs.add(actuel.intValue()));
        Position position = new Position(14, 2);

        partie.acheterCase(position);
        assertTrue(partie.placerTour(new TourCanon(position)));
        assertTrue(partie.annulerDernierPlacement());

        assertEquals(List.of(375, 250, 375), valeurs);
    }
}
