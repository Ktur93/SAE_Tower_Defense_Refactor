package universite_paris8.iut.ademir.demo1.Tests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import universite_paris8.iut.ademir.demo1.Modele.Cartes.Carte;
import universite_paris8.iut.ademir.demo1.Modele.Cartes.Position;
import universite_paris8.iut.ademir.demo1.Modele.Jeu.Etats.*;
import universite_paris8.iut.ademir.demo1.Modele.Jeu.Partie;
import universite_paris8.iut.ademir.demo1.Modele.Monstres.Zombie;
import universite_paris8.iut.ademir.demo1.Modele.Tour.TourArcher;
import universite_paris8.iut.ademir.demo1.Modele.Tour.TourCanon;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import static org.junit.jupiter.api.Assertions.*;

public class EtatPartieTest {
    private Partie partie;
    private ArrayList<Position> chemin;

    @BeforeEach
    public void preparer() {
        Carte.getInstance().caseBloquer();
        chemin = new ArrayList<>();
        chemin.add(new Position(0, 0));
        chemin.add(new Position(1, 0));
        partie = new Partie(chemin, chemin, chemin);
    }

    @Test
    public void debutDePartieEstJouableAvecUneSeuleSourceDeVie() {
        assertInstanceOf(EtatEnCours.class, partie.getEtatPartie());
        assertTrue(partie.getEtatPartie().peutInteragir());
        assertEquals(3, partie.getPvPortail());
        assertEquals(partie.getPvPortailIntegerProperty(), partie.getPvPortail());
        assertFalse(partie.victoireProperty().get());
        assertFalse(partie.defaiteProperty().get());
    }

    @Test
    public void victoireNeSeDeclencheQuUneFoisEtArreteLaSimulation() {
        AtomicInteger transitions = new AtomicInteger();
        partie.etatPartieProperty().addListener((obs, ancien, actuel) -> transitions.incrementAndGet());
        partie.setIndiceVague(partie.getVagues().size());
        partie.mettreAJour();
        Zombie zombie = new Zombie(chemin);
        partie.getMonstres().add(zombie);
        partie.mettreAJour();

        assertInstanceOf(EtatVictoire.class, partie.getEtatPartie());
        assertTrue(partie.victoireProperty().get());
        assertFalse(partie.defaiteProperty().get());
        assertEquals(1, transitions.get());
        assertEquals(0, zombie.getX());
    }

    @Test
    public void portailMortRestePrioritaireSurLaVictoire() {
        partie.setIndiceVague(partie.getVagues().size());
        partie.recevoirDegatPortail(3);
        partie.mettreAJour();

        assertInstanceOf(EtatDefaite.class, partie.getEtatPartie());
        assertFalse(partie.victoireProperty().get());
        assertTrue(partie.defaiteProperty().get());
    }

    @Test
    public void portailDetruitAttendLaFinDeLaVagueDejaLancee() {
        partie.lancerProchaineVague();
        partie.recevoirDegatPortail(3);
        partie.mettreAJour();

        assertInstanceOf(EtatPortailDetruit.class, partie.getEtatPartie());
        assertFalse(partie.defaiteProperty().get());
        assertTrue(partie.getVagueEnCours());
        for (int i = 0; i < 500 && !(partie.getEtatPartie() instanceof EtatDefaite); i++) {
            partie.getMonstres().forEach(monstre -> monstre.setPv(0));
            partie.setCompteurPlusPlus();
            partie.mettreAJour();
        }

        assertInstanceOf(EtatDefaite.class, partie.getEtatPartie());
        assertFalse(partie.getVagueEnCours());
        assertTrue(partie.defaiteProperty().get());
    }

    @Test
    public void defaiteAttendLeDelaiPuisRedemarreUneSeuleFois() {
        partie.acheterCase(new Position(14, 2));
        partie.recevoirDegatPortail(3);
        partie.mettreAJour();
        for (int i = 0; i < 175; i++) {
            partie.setCompteurPlusPlus();
        }
        partie.mettreAJour();
        assertInstanceOf(EtatDefaite.class, partie.getEtatPartie());
        partie.setCompteurPlusPlus();
        partie.mettreAJour();

        assertInstanceOf(EtatEnCours.class, partie.getEtatPartie());
        assertEquals(400, partie.getRubis());
        assertEquals(3, partie.getPvPortail());
        assertEquals(0, partie.getIndiceVague());
        assertFalse(partie.defaiteProperty().get());
        assertFalse(partie.portailMortProperty().get());
        assertEquals(Carte.CASE_BLOQUEE, Carte.getInstance().Tuile(14, 2));
        partie.ajouterRubis(10);
        partie.mettreAJour();
        assertEquals(410, partie.getRubis());
    }

    @Test
    public void etatTerminalBloqueLesActionsDuJoueur() {
        Position position = new Position(14, 2);
        partie.acheterCase(position);
        TourCanon tour = new TourCanon(position);
        assertTrue(partie.placerTour(tour));
        partie.setIndiceVague(partie.getVagues().size());
        partie.mettreAJour();
        int solde = partie.getRubis();

        partie.acheterCase(new Position(14, 6));
        partie.faireAmeliorerTours(tour);
        assertFalse(partie.placerTour(new TourArcher(position)));
        assertFalse(partie.annulerDernierPlacement());
        partie.lancerProchaineVague();
        partie.changerNiveau();

        assertEquals(solde, partie.getRubis());
        assertEquals(1, tour.getNivT());
        assertEquals(1, partie.getTours().size());
        assertFalse(partie.getVagueEnCours());
        assertEquals(1, partie.getNiveau());
        assertEquals(Carte.CASE_BLOQUEE, Carte.getInstance().Tuile(14, 6));
    }

    @Test
    public void redemarrageApresVictoireRetablitTousLesIndicateurs() {
        partie.setIndiceVague(partie.getVagues().size());
        partie.mettreAJour();
        assertTrue(partie.toutesLesVaguesTermineProperty().get());
        partie.recommnencer();

        assertInstanceOf(EtatEnCours.class, partie.getEtatPartie());
        assertFalse(partie.victoireProperty().get());
        assertFalse(partie.defaiteProperty().get());
        assertFalse(partie.toutesLesVaguesTermineProperty().get());
        assertFalse(partie.portailMortProperty().get());
        assertEquals(0, partie.getCompteur());
    }

    @Test
    public void proprieteDuPortailSuitLesDegatsEtLeRedemarrage() {
        List<Boolean> valeurs = new ArrayList<>();
        partie.portailMortProperty().addListener((obs, ancien, actuel) -> valeurs.add(actuel));
        partie.recevoirDegatPortail(2);
        assertFalse(partie.portailMortProperty().get());
        partie.recevoirDegatPortail(1);
        assertTrue(partie.portailMortProperty().get());
        partie.recommnencer();
        assertEquals(List.of(true, false), valeurs);
    }
}
