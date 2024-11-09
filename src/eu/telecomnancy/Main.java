package eu.telecomnancy;


import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.stage.Stage;


public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {
        Jeu jeu = new Jeu(4, 2048);
        BorderPane root = new BorderPane();

        //Initialisation des vues
        VuePlateau vuePlateau = new VuePlateau(jeu);
        VueControle controles = new VueControle(jeu);
        VuePanneauGauche panneauGauche = new VuePanneauGauche(jeu);
        VueMenu menu = new VueMenu(jeu);
        VueStats statsPane = new VueStats(jeu);

        //Régions de taille flexible pour centrer les éléments
        Region leftSpacer = new Region();
        Region rightSpacer = new Region();
        Region topSpacer = new Region();
        Region bottomSpacer = new Region();

        HBox.setHgrow(leftSpacer, Priority.ALWAYS);
        HBox.setHgrow(rightSpacer, Priority.ALWAYS);
        VBox.setVgrow(topSpacer, Priority.ALWAYS);
        VBox.setVgrow(bottomSpacer, Priority.ALWAYS);

        // HBox pour centrer le plateau entre les espaces
        HBox centeredBox = new HBox(leftSpacer, vuePlateau, rightSpacer);
        centeredBox.setAlignment(Pos.CENTER);

        // VBox pour centrer le HBox entre les espaces
        VBox centeredVBox = new VBox(topSpacer, centeredBox, bottomSpacer);
        centeredVBox.setAlignment(Pos.CENTER);

        // Encapsuler dans un StackPane et ajuster l'espacement
        StackPane centerPane = new StackPane(centeredVBox);
        centerPane.setPadding(new Insets(20, 20, 20, 20)); // Marge autour du plateau
        root.setCenter(centerPane);

        //Ajout des vues au borderpane
        root.setRight(controles);
        root.setLeft(panneauGauche);
        root.setTop(menu);
        root.setCenter(centerPane);
        root.setBottom(statsPane);
        BorderPane.setAlignment(vuePlateau, Pos.CENTER);

        root.setStyle("-fx-background-color: #F5DEB3;");
        jeu.notifierObservateur();

        //Création de la scene
        Scene mainScene = new Scene(root, 450, 400);

        // Instanciation et configuration de ClavierControleur pour le mapping des touches
        ClavierControleur clavierControleur = new ClavierControleur(jeu);
        clavierControleur.configurerClavier(mainScene);

        //Ajout de la scene au stage
        primaryStage.setScene(mainScene);
        primaryStage.setTitle("MyGame 2048");
        primaryStage.setMaximized(true);

        primaryStage.show();
    }
    public static void main(String[] args){
        launch(args);
    }
}
