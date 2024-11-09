package eu.telecomnancy;

import javafx.application.Platform;
import javafx.scene.control.*;
import javafx.scene.input.KeyCombination;

public class VueMenu extends MenuBar implements Observateur{
    private final Jeu jeu;
    private final Label taille;
    private final Label objectif;

    public VueMenu(Jeu jeu) {
        super();
        this.jeu = jeu;
        this.jeu.ajouterObservateur(this);

        Menu menu = new Menu("Menu");
        menu.setStyle("-fx-font-size: 20px;");

        // Option "Nouveau"
        MenuItem nouveau = new MenuItem("Nouveau");
        nouveau.setAccelerator(KeyCombination.keyCombination("Ctrl+N"));
        nouveau.setOnAction(e -> jeu.initializePlateau());

        // Option "Quitter"
        MenuItem quitter = new MenuItem("Quitter");
        quitter.setAccelerator(KeyCombination.keyCombination("Ctrl+Q"));
        quitter.setOnAction(e -> Platform.exit());

        // Affichage Taille et Objectif
        taille = new Label("Taille : " + jeu.getTaille() + "[2-10]");
        objectif = new Label("Objectif : " + jeu.getObjectif() + "(2^x)");

        // CustomMenuItem pour afficher les labels dans le menu
        CustomMenuItem tailleItem = new CustomMenuItem(taille);
        tailleItem.setHideOnClick(false);

        CustomMenuItem objectifItem = new CustomMenuItem(objectif);
        objectifItem.setHideOnClick(false);

        // Ajouter une ligne de séparation entre les options cliquables et les informations
        SeparatorMenuItem separator = new SeparatorMenuItem();
        SeparatorMenuItem separator2 = new SeparatorMenuItem();

        // Ajout des éléments de menu
        menu.getItems().addAll(nouveau, separator, tailleItem, objectifItem, separator2, quitter);
        this.getMenus().add(menu);
    }

    public void reagir(){
        taille.setText("Taille : " + jeu.getTaille() + " [2-10]");
        objectif.setText("Objectif : " + jeu.getObjectif() + " (Puissance de 2)");
    }
}
