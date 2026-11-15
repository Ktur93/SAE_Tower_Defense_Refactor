package universite_paris8.iut.ademir.demo1.Tests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import universite_paris8.iut.ademir.demo1.Modele.Algorithmes.BFS;
import universite_paris8.iut.ademir.demo1.Modele.Cartes.Carte;
import universite_paris8.iut.ademir.demo1.Modele.Cartes.Position;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class BFSTest {

    @BeforeEach
    public void preparerCarte() {
        Carte.getInstance().caseBloquer();
    }

    @Test
    public void cheminDepuisDepartVersArriveeExiste() {
        // On recupere la carte partagee et les positions principales
        Carte carte = Carte.getInstance();
        Position depart = new Position(0, 7);
        Position arrivee = carte.trouverArrivee();

        // On lance le BFS depuis le depart
        BFS bfs = new BFS(carte, depart);
        ArrayList<Position> chemin = bfs.cheminDeSourceVersCible(arrivee);

        // le chemin doit exister
        assertFalse(chemin.isEmpty());
    }

    @Test
    public void cheminCommenceParLeDepart() {

        // On recupere la carte partagee, le depart et l arrivee
        Carte carte = Carte.getInstance();
        Position depart = new Position(0, 7);
        Position arrivee = carte.trouverArrivee();

        // On calcul le chemin
        BFS bfs = new BFS(carte, depart);
        ArrayList<Position> chemin = bfs.cheminDeSourceVersCible(arrivee);

        // La premiere case du chemin doit etre le depart
        assertEquals(depart, chemin.get(0));
    }

    @Test
    public void cheminSeTermineParArrivee() {
        // On recupere la carte partagee le depart et l'arrivee
        Carte carte = Carte.getInstance();
        Position depart = new Position(0, 7);
        Position arrivee = carte.trouverArrivee();

        // On calcule le chemin
        BFS bfs = new BFS(carte, depart);
        ArrayList<Position> chemin = bfs.cheminDeSourceVersCible(arrivee);

        // La derniere case du chemin doit etre l'arrivee
        assertEquals(arrivee, chemin.get(chemin.size() - 1));
    }

    @Test
    public void toutesLesCasesDuCheminSontMarchables() {
        // On recupere la carte partagee, le depart et l'arrivee
        Carte carte = Carte.getInstance();
        Position depart = new Position(0, 7);
        Position arrivee = carte.trouverArrivee();

        // On calcule le chemin
        BFS bfs = new BFS(carte, depart);
        ArrayList<Position> chemin = bfs.cheminDeSourceVersCible(arrivee);

        //toutes les cases du chemin doivent etre marchables
        for (Position p : chemin) {
            assertTrue(carte.estMarchable(p.getX(), p.getY()));
        }
    }

    @Test
    public void parcoursBFSNestPasVide() {
        // On recupere la carte partagee et un depart
        Carte carte = Carte.getInstance();
        Position depart = new Position(0, 7);

        // le BFS parcourt les cases accessibles
        BFS bfs = new BFS(carte, depart);

        // Le parcours ne doit pas etre vide,
        assertFalse(bfs.getParcours().isEmpty());
    }
}