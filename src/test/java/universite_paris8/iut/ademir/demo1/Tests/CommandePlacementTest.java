package universite_paris8.iut.ademir.demo1.Tests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import universite_paris8.iut.ademir.demo1.Modele.Cartes.Carte;
import universite_paris8.iut.ademir.demo1.Modele.Cartes.Position;
import universite_paris8.iut.ademir.demo1.Modele.Jeu.Commandes.CommandePlacementTour;
import universite_paris8.iut.ademir.demo1.Modele.Jeu.GestionnaireTour;
import universite_paris8.iut.ademir.demo1.Modele.Jeu.Partie;
import universite_paris8.iut.ademir.demo1.Modele.Tour.TourArcher;
import universite_paris8.iut.ademir.demo1.Modele.Tour.TourCanon;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class CommandePlacementTest {
    private Partie partie;
    private Position premiere;
    private Position seconde;

    @BeforeEach
    public void preparer() {
        Carte.getInstance().caseBloquer();
        ArrayList<Position> chemin = new ArrayList<>();
        chemin.add(new Position(0, 0));
        chemin.add(new Position(1, 0));
        partie = new Partie(chemin, chemin, chemin);
        premiere = new Position(14, 2);
        seconde = new Position(14, 6);
    }

    private void terminerVague() {
        for (int i = 0; i < 500 && partie.getVagueEnCours(); i++) {
            partie.getMonstres().forEach(monstre -> monstre.setPv(0));
            partie.setCompteurPlusPlus();
            partie.mettreAJour();
        }
        assertFalse(partie.getVagueEnCours());
    }

    @Test
    public void annulationSuitLOrdreInverseEtConserveLesCasesAchetees() {
        partie.acheterCase(premiere);
        partie.acheterCase(seconde);
        TourCanon canon = new TourCanon(premiere);
        TourArcher archer = new TourArcher(seconde);
        assertTrue(partie.placerTour(canon));
        assertTrue(partie.placerTour(archer));
        assertEquals(150, partie.getRubis());

        assertTrue(partie.annulerDernierPlacement());
        assertEquals(200, partie.getRubis());
        assertEquals(1, partie.getTours().size());
        assertSame(canon, partie.getTours().get(0));
        assertTrue(partie.annulerDernierPlacement());
        assertEquals(325, partie.getRubis());
        assertTrue(partie.getTours().isEmpty());
        assertFalse(partie.annulerDernierPlacement());
        assertTrue(Carte.getInstance().estCaseTour(14, 2));
        assertEquals(75, partie.getPrixCase());
    }

    @Test
    public void placementRefuseNeRemplacePasLaDerniereCommandeValide() {
        partie.acheterCase(premiere);
        TourCanon canon = new TourCanon(premiere);
        assertTrue(partie.placerTour(canon));
        assertFalse(partie.placerTour(new TourCanon(new Position(0, 0))));

        assertTrue(partie.annulerDernierPlacement());
        assertEquals(375, partie.getRubis());
        assertTrue(partie.getTours().isEmpty());
    }

    @Test
    public void remboursementUtiliseLePrixPayeEtNonLePrixModifieEnsuite() {
        partie.acheterCase(premiere);
        TourCanon canon = new TourCanon(premiere);
        assertTrue(partie.placerTour(canon));
        canon.setPrix(999);

        assertTrue(partie.annulerDernierPlacement());
        assertEquals(375, partie.getRubis());
    }

    @Test
    public void lancementDeVagueEffaceDefinitivementLesAnciensPlacements() {
        partie.acheterCase(premiere);
        assertTrue(partie.placerTour(new TourCanon(premiere)));
        assertTrue(partie.peutAnnulerPlacement());
        partie.lancerProchaineVague();

        assertFalse(partie.peutAnnulerPlacement());
        assertFalse(partie.annulerDernierPlacement());
        terminerVague();
        assertFalse(partie.annulerDernierPlacement());
        assertEquals(1, partie.getTours().size());
    }

    @Test
    public void nouveauPlacementEntreDeuxVaguesPeutEtreAnnule() {
        partie.lancerProchaineVague();
        terminerVague();
        partie.acheterCase(premiere);
        int soldeAvant = partie.getRubis();
        assertTrue(partie.placerTour(new TourArcher(premiere)));

        assertTrue(partie.annulerDernierPlacement());
        assertEquals(soldeAvant, partie.getRubis());
    }

    @Test
    public void placementPendantUneVagueNeDevientPasAnnulableApresCelleCi() {
        partie.acheterCase(premiere);
        partie.lancerProchaineVague();
        assertTrue(partie.placerTour(new TourArcher(premiere)));
        assertFalse(partie.annulerDernierPlacement());
        terminerVague();
        assertFalse(partie.annulerDernierPlacement());
    }

    @Test
    public void ameliorationReussieInvalideLHistoriqueMaisPasUnRefus() {
        partie.acheterCase(premiere);
        TourCanon tour = new TourCanon(premiere);
        assertTrue(partie.placerTour(tour));
        partie.retirerRubis(200);
        partie.faireAmeliorerTours(tour);
        assertTrue(partie.peutAnnulerPlacement());
        partie.ajouterRubis(100);
        partie.faireAmeliorerTours(tour);

        assertEquals(2, tour.getNivT());
        assertFalse(partie.peutAnnulerPlacement());
        assertFalse(partie.annulerDernierPlacement());
    }

    @Test
    public void redemarrageEffaceLesCommandesEtLaDisponibiliteObservable() {
        partie.acheterCase(premiere);
        assertTrue(partie.placerTour(new TourCanon(premiere)));
        assertTrue(partie.annulationPossibleProperty().get());
        partie.recommnencer();

        assertFalse(partie.annulationPossibleProperty().get());
        assertFalse(partie.annulerDernierPlacement());
        assertEquals(400, partie.getRubis());
    }

    @Test
    public void commandeNePeutNiPlacerNiRembourserDeuxFois() {
        Carte.getInstance().caseDebloquer(premiere);
        GestionnaireTour gestionnaire = new GestionnaireTour();
        CommandePlacementTour commande = new CommandePlacementTour(partie, gestionnaire, new TourCanon(premiere));
        assertTrue(commande.executer());
        assertFalse(commande.executer());
        assertEquals(275, partie.getRubis());
        assertEquals(1, gestionnaire.getTours().size());
        assertTrue(commande.annuler());
        assertFalse(commande.annuler());
        assertEquals(400, partie.getRubis());
    }
}
