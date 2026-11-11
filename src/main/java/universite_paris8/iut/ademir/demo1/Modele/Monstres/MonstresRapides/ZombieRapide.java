// package universite_paris8.iut.ademir.demo1.Modele.Monstres.MonstresRapides;
//
// import java.util.ArrayList;
// import javafx.collections.ObservableList;
// import universite_paris8.iut.ademir.demo1.Modele.Cartes.Position;
// import universite_paris8.iut.ademir.demo1.Modele.Monstres.Monstre;
// import universite_paris8.iut.ademir.demo1.Modele.Monstres.Zombie;
//
// public class ZombieRapide extends Zombie {
//
//     private double vitesseDeBase;
//
//     public ZombieRapide(ArrayList<Position> chemin) {
//         super(chemin);
//         this.vitesseDeBase = getVitesse();
//     }
//
//     @Override
//     public double getVitesseNormal() {
//         if (getPv() <= getPvMax() / 2.0) {
//             return vitesseDeBase * 1.5;
//         }
//         return vitesseDeBase;
//     }
//
//     @Override
//     public void agir(ObservableList<Monstre> monstres) {
//         if (!estGlace()) {
//             setVitesse(getVitesseNormal());
//         }
//     }
// }

// Ancienne classe desactivee et conservee integralement.
// Capacite remplacee par le decorateur commun dans Monstres.Decorateurs.
