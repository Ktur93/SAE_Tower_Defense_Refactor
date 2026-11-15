package universite_paris8.iut.ademir.demo1.Tests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import universite_paris8.iut.ademir.demo1.Modele.Cartes.Carte;
import universite_paris8.iut.ademir.demo1.Modele.Cartes.Position;
import universite_paris8.iut.ademir.demo1.Modele.Jeu.GestionnaireScore;
import universite_paris8.iut.ademir.demo1.Modele.Jeu.GestionnaireTour;
import universite_paris8.iut.ademir.demo1.Modele.Jeu.Operations.OperationPayante;
import universite_paris8.iut.ademir.demo1.Modele.Jeu.Partie;
import universite_paris8.iut.ademir.demo1.Modele.Tour.Tour;
import universite_paris8.iut.ademir.demo1.Modele.Tour.TourCanon;
import universite_paris8.iut.ademir.demo1.Modele.Tour.TourFactory;
import universite_paris8.iut.ademir.demo1.Modele.Tour.TypeTour;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class OperationPayanteTest {

    private Carte carte;
    private Partie partie;
    private Position emplacement;

    @BeforeEach
    public void preparerPartie() {
        carte = Carte.getInstance();
        carte.caseBloquer();
        partie = creerPartie();
        emplacement = new Position(14, 2);
    }

    private Partie creerPartie() {
        ArrayList<Position> chemin = new ArrayList<>();
        chemin.add(new Position(0, 0));
        chemin.add(new Position(1, 0));
        return new Partie(chemin, chemin, chemin);
    }

    @Test
    public void achatPlacementEtAmeliorationPreleventChaquePrixUneSeuleFois() {
        partie.acheterCase(emplacement);
        assertEquals(375, partie.getRubis());
        assertEquals(50, partie.getPrixCase());
        assertTrue(carte.estCaseTour(14, 2));

        TourCanon tour = new TourCanon(emplacement);
        assertTrue(partie.placerTour(tour));
        assertEquals(250, partie.getRubis());
        assertSame(tour, partie.trouverTour(emplacement));

        partie.faireAmeliorerTours(tour);
        assertEquals(175, partie.getRubis());
        assertEquals(2, tour.getNivT());
        assertEquals(50, tour.getAtk());
        assertEquals(4, tour.getPorter());
        assertEquals(59, tour.getCadence());
    }

    @Test
    public void deuxCasesUtilisentLePrixAvantSonAugmentation() {
        partie.acheterCase(emplacement);
        partie.acheterCase(new Position(14, 6));

        assertTrue(carte.estCaseTour(14, 6));
        assertEquals(325, partie.getRubis());
        assertEquals(75, partie.getPrixCase());
    }

    @Test
    public void racheterUneCaseDebloqueeNePreleveRienEtNeChangePasLePrix() {
        partie.acheterCase(emplacement);
        partie.acheterCase(emplacement);

        assertEquals(375, partie.getRubis());
        assertEquals(50, partie.getPrixCase());
    }

    @Test
    public void achatRefuseLesPositionsInvalidesSansModifierLeBudget() {
        partie.acheterCase(null);
        partie.acheterCase(new Position(-1, 2));
        partie.acheterCase(new Position(carte.getLargeur(), 2));
        partie.acheterCase(new Position(0, 0));

        assertEquals(400, partie.getRubis());
        assertEquals(25, partie.getPrixCase());
        assertEquals(Carte.CASE_BLOQUEE, carte.Tuile(14, 2));
    }

    @Test
    public void achatTropCherLaisseLaCaseBloqueeEtLePrixInchange() {
        partie.retirerRubis(376);
        partie.acheterCase(emplacement);

        assertEquals(24, partie.getRubis());
        assertEquals(25, partie.getPrixCase());
        assertEquals(Carte.CASE_BLOQUEE, carte.Tuile(14, 2));
    }

    @Test
    public void placementTropCherNeCreePasDeTour() {
        carte.caseDebloquer(emplacement);
        partie.retirerRubis(276);

        assertFalse(partie.placerTour(new TourCanon(emplacement)));
        assertEquals(124, partie.getRubis());
        assertTrue(partie.getTours().isEmpty());
    }

    @Test
    public void placementInvalideNePrelevePasDeRubis() {
        assertFalse(partie.placerTour(null));
        assertFalse(partie.placerTour(new TourCanon(emplacement)));
        assertFalse(partie.placerTour(new TourCanon(new Position(-1, 0))));

        assertEquals(400, partie.getRubis());
        assertTrue(partie.getTours().isEmpty());
    }

    @Test
    public void prixNegatifNePermetPasDeGagnerDesRubisEnPlacantUneTour() {
        carte.caseDebloquer(emplacement);
        TourCanon tour = new TourCanon(emplacement);
        tour.setPrix(-10);

        assertFalse(partie.placerTour(tour));
        assertEquals(400, partie.getRubis());
        assertTrue(partie.getTours().isEmpty());
    }

    @Test
    public void ameliorationTropChereConserveLeNiveauEtLesStatistiques() {
        TourCanon tour = new TourCanon(emplacement);
        partie.retirerRubis(326);
        partie.faireAmeliorerTours(tour);

        assertEquals(74, partie.getRubis());
        assertEquals(1, tour.getNivT());
        assertEquals(40, tour.getAtk());
        assertEquals(3, tour.getPorter());
        assertEquals(60, tour.getCadence());
    }

    @Test
    public void ameliorationAuNiveauMaximalNeModifieRien() {
        TourCanon tour = new TourCanon(emplacement);
        tour.setNivT(tour.getNivMax());
        partie.faireAmeliorerTours(tour);

        assertEquals(400, partie.getRubis());
        assertEquals(tour.getNivMax(), tour.getNivT());
        assertEquals(40, tour.getAtk());
    }

    @Test
    public void ameliorationSansTourNePreleveRien() {
        partie.faireAmeliorerTours(null);
        assertEquals(400, partie.getRubis());
    }

    @Test
    public void toutesLesToursConserventLeurAmeliorationSpecialisee() {
        TourFactory factory = new TourFactory();
        carte.caseDebloquer(emplacement);

        for (TypeTour type : TypeTour.values()) {
            Partie autrePartie = creerPartie();
            Tour tour = factory.creerTour(type, emplacement);
            int attaque = tour.getAtk();
            int portee = tour.getPorter();
            long cadence = tour.getCadence();

            assertTrue(autrePartie.placerTour(tour), type.name());
            autrePartie.faireAmeliorerTours(tour);

            assertEquals(400 - tour.getPrix() - 75, autrePartie.getRubis(), type.name());
            assertEquals(2, tour.getNivT(), type.name());
            assertTrue(tour.getAtk() > attaque, type.name());
            assertTrue(tour.getPorter() > portee, type.name());
            assertTrue(tour.getCadence() < cadence, type.name());
        }
    }

    @Test
    public void ameliorationUtiliseLePrixActuelDuGestionnaire() {
        GestionnaireTour gestionnaire = new GestionnaireTour();
        gestionnaire.augmenterPrixAmelioration();
        TourCanon tour = new TourCanon(emplacement);

        gestionnaire.faireAmeliorerTours(tour, partie);

        assertEquals(250, partie.getRubis());
        assertEquals(2, tour.getNivT());
        assertEquals(150, gestionnaire.getPrixAmelioration());
    }

    @Test
    public void recommencerRetablitLesPrixEtLeBudgetApresLesOperations() {
        partie.acheterCase(emplacement);
        TourCanon tour = new TourCanon(emplacement);
        assertTrue(partie.placerTour(tour));
        partie.faireAmeliorerTours(tour);

        partie.recommnencer();

        assertEquals(400, partie.getRubis());
        assertEquals(25, partie.getPrixCase());
        assertEquals(75, partie.getPrixAmelioration());
        assertTrue(partie.getTours().isEmpty());
    }

    @Test
    public void actionQuiRefuseNePrelevePasSonPrix() {
        OperationPayante operation = new OperationPayante(partie) {
            @Override
            protected boolean conditionsRespectees() {
                return true;
            }

            @Override
            protected int obtenirPrix() {
                return 25;
            }

            @Override
            protected boolean appliquer() {
                return false;
            }
        };

        assertFalse(operation.executer());
        assertEquals(400, partie.getRubis());
    }

    @Test
    public void prixControleEstConserveQuandLActionChangeLePrixSuivant() {
        OperationPayante operation = new OperationPayante(partie) {
            private int prix = 25;

            @Override
            protected boolean conditionsRespectees() {
                return true;
            }

            @Override
            protected int obtenirPrix() {
                return prix;
            }

            @Override
            protected boolean appliquer() {
                prix = 50;
                return true;
            }
        };

        assertTrue(operation.executer());
        assertEquals(375, partie.getRubis());
    }

    @Test
    public void gestionnaireScoreRefuseLesMontantsInvalides() {
        GestionnaireScore score = new GestionnaireScore();

        score.ajouterRubis(-50);
        assertEquals(400, score.getRubis());
        score.retirerRubis(-50);
        assertEquals(400, score.getRubis());
        score.retirerRubis(401);
        assertEquals(400, score.getRubis());

        score.retirerRubis(400);
        assertEquals(0, score.getRubis());
    }

    @Test
    public void budgetExactPermetUnAchatEtAtteintZero() {
        partie.retirerRubis(375);
        partie.acheterCase(emplacement);

        assertTrue(carte.estCaseTour(14, 2));
        assertEquals(0, partie.getRubis());
        assertEquals(50, partie.getPrixCase());
    }
}
