//cmd nouveau code
// Une commande expose son execution et son annulation.
package universite_paris8.iut.ademir.demo1.Modele.Jeu.Commandes;

public interface Commande {
    boolean executer();

    boolean annuler();
}
