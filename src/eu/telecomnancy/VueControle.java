package eu.telecomnancy;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.control.Button;
import javafx.animation.PauseTransition;
import javafx.util.Duration;

public class VueControle extends VBox implements Observateur{
    private final Jeu jeu;
    private final Button startButton;
    private final Button rightButton;
    private final Button upButton;
    private final Button leftButton;
    private final Button downButton;
    private final Button restartButton;
    private final Region top;
    private final Region bottom;
    private final TextField taillePlateauField;
    private final TextField objectifField;

    public VueControle(Jeu jeu){
        super();
        this.jeu = jeu;
        this.jeu.ajouterObservateur(this);

        startButton = new Button("Débuter Partie");
        rightButton = new Button("Droite");
        upButton = new Button("Haut");
        leftButton = new Button("Gauche");
        downButton = new Button("Bas");
        restartButton = new Button("Recommencer");

        taillePlateauField = new TextField();
        taillePlateauField.setPromptText("Taille plateau (Voir Menu)");
        objectifField = new TextField();
        objectifField.setPromptText("Objectif (Voir Menu)");

        String buttonStyle = "-fx-focus-color: transparent; -fx-faint-focus-color: transparent; -fx-font-size: 24;";
        startButton.setStyle(buttonStyle);
        rightButton.setStyle(buttonStyle);
        upButton.setStyle(buttonStyle);
        leftButton.setStyle(buttonStyle);
        downButton.setStyle(buttonStyle);
        restartButton.setStyle(buttonStyle);

        String textFieldStyle = "-fx-font-size: 14;";
        taillePlateauField.setStyle(textFieldStyle);
        objectifField.setStyle(textFieldStyle);

        //Action des champs de texte
        taillePlateauField.setOnAction(e -> {
            if (!taillePlateauField.getText().isEmpty()) {
                int taille = parseTaille(taillePlateauField.getText());
                jeu.setTaille(taille);
                taillePlateauField.clear();
            }
        });

        objectifField.setOnAction(e -> {
            if (!objectifField.getText().isEmpty()) {
                int objectif = parseObjectif(objectifField.getText());
                jeu.setObjectif(objectif);
                objectifField.clear();
            }
        });

        //Actions des boutons de jeu
        startButton.setOnAction(e->{
            jeu.nouveau();
            showGameButtons();
        });
        startButton.setDisable(true);

        rightButton.setOnAction(e->jeu.deplacer("droite"));
        upButton.setOnAction(e->jeu.deplacer("haut"));
        leftButton.setOnAction(e->jeu.deplacer("gauche"));
        downButton.setOnAction(e-> jeu.deplacer("bas"));
        restartButton.setOnAction(e->jeu.nouveau());

        //Création des régions pour maintenir les boutons au centre verticalement
        top = new Region();
        bottom = new Region();
        VBox.setVgrow(top, Priority.ALWAYS);
        VBox.setVgrow(bottom, Priority.ALWAYS);

        VBox startBox = new VBox(20, startButton, taillePlateauField, objectifField);
        startBox.setAlignment(Pos.CENTER);
        this.getChildren().addAll(top, startBox, bottom);
        this.setPadding(new Insets(0,70,0,30));
        this.setAlignment(Pos.CENTER);
    }

    private void showGameButtons() {
        this.getChildren().clear();
        VBox gameButtonsBox = new VBox(20, upButton, leftButton, downButton, rightButton, restartButton);
        gameButtonsBox.setAlignment(Pos.CENTER);
        this.getChildren().addAll(top, gameButtonsBox, bottom);
        startButton.setDisable(true);
    }

    private void showStartButton(){
        this.getChildren().clear();
        VBox startBox = new VBox(20, startButton, taillePlateauField, objectifField);
        startBox.setAlignment(Pos.CENTER);
        this.getChildren().addAll(top, startBox, bottom);

        PauseTransition delay = new PauseTransition(Duration.seconds(1));
        delay.setOnFinished(event -> startButton.setDisable(false));
        delay.play();
    }

    public void reagir(){
        if (jeu.getDebutee()){
            showGameButtons();
        }
        else{
            showStartButton();
        }
    }

    // Méthode pour limiter la taille du plateau entre 2 et 10
    private int parseTaille(String text) {
        try {
            int taille = Integer.parseInt(text);
            if (taille < 2 || taille > 10) {
                showAlert("Erreur de taille", "Veuillez entrer une taille entre 2 et 10.");
                return jeu.getTaille();
            }
            return taille;
        } catch (NumberFormatException e) {
            showAlert("Erreur de saisie", "Veuillez entrer un nombre valide pour la taille.");
            return jeu.getTaille();
        }
    }

    // Méthode pour limiter l’objectif à la puissance de 2 inférieure la plus proche, inférieure à 131072
    private int parseObjectif(String text) {
        try {
            int objectif = Integer.parseInt(text);
            int maxObjectif = 131072;

            if (objectif < 2) {
                showAlert("Erreur d'objectif", "Veuillez entrer une puissance de 2 valide (minimum 2).");
                return jeu.getObjectif();
            }

            int puissance = 1;
            while (puissance <= objectif && puissance <= maxObjectif) {
                puissance *= 2;
            }
            puissance /= 2;

            if (puissance > maxObjectif) puissance = maxObjectif;
            return puissance;

        } catch (NumberFormatException e) {
            showAlert("Erreur de saisie", "Veuillez entrer un nombre valide pour l'objectif.");
            return jeu.getObjectif();
        }
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}

