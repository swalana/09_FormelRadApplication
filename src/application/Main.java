package application;

import java.io.FileInputStream;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;

/**
 * Formelrad Application
 *
 * @author Peter Rutschmann
 * @version 13.09.2018
 */
public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {
        try {
            Pane root = new Pane();

            Label lbleistung = new Label("Leistung:");
            lbleistung.relocate(10, 285);
            lbleistung.setFont(Font.font(15));
            root.getChildren().add(lbleistung);

            TextField txLeistung = new TextField();
            txLeistung.relocate(100, 285);
            txLeistung.setFont(Font.font("Verdana", 15));
            root.getChildren().add(txLeistung);

            Label lblSpannung = new Label("Spannung:");
            lblSpannung.relocate(10, 325);
            lblSpannung.setFont(Font.font(15));
            root.getChildren().add(lblSpannung);

            TextField txSpannung = new TextField();
            txSpannung.relocate(100, 325);
            txSpannung.setFont(Font.font("Verdana", 15));
            root.getChildren().add(txSpannung);

            Label lblStrom = new Label("Strom:");
            lblStrom.relocate(10, 365);
            lblStrom.setFont(Font.font(15));
            root.getChildren().add(lblStrom);

            TextField txStrom = new TextField();
            txStrom.relocate(100, 365);
            txStrom.setFont(Font.font("Verdana", 15));
            root.getChildren().add(txStrom);

            Label lblWiderstand = new Label("Widerstand:");
            lblWiderstand.relocate(10, 405);
            lblWiderstand.setFont(Font.font(15));
            root.getChildren().add(lblWiderstand);

            TextField txWiderstand = new TextField();
            txWiderstand.relocate(100, 405);
            txWiderstand.setFont(Font.font("Verdana", 15));
            root.getChildren().add(txWiderstand);

            Button btnReset = new Button();
            btnReset.relocate(190, 445);
            btnReset.setText("Zurücksetzen");
            root.getChildren().add(btnReset);

            btnReset.setOnAction(e -> {
                txLeistung.setText(Double.toString(0));
                txSpannung.setText(Double.toString(0));
                txStrom.setText(Double.toString(0));
                txWiderstand.setText(Double.toString(0));
                txLeistung.setStyle("-fx-text-fill: black");
                txSpannung.setStyle("-fx-text-fill: black");
                txStrom.setStyle("-fx-text-fill: black");
                txWiderstand.setStyle("-fx-text-fill: black");
            });

            Button btnBerechnen = new Button();
            btnBerechnen.relocate(100, 445);
            btnBerechnen.setText("Berechnen");
            root.getChildren().add(btnBerechnen);

            btnBerechnen.setOnAction(e -> {
                double power = 0.0;
                double tension = 0.0;
                double current = 0.0;
                double resistence = 0.0;
                if (txLeistung.getText().isEmpty() == false) {
                    power = Double.parseDouble(txLeistung.getText());
                    //txLeistung.setStyle("-fx-text-fill: black");
                } else {
                    txLeistung.setStyle("-fx-text-fill: red");
                }
                if (txSpannung.getText().isEmpty() == false) {
                    tension = Double.parseDouble(txSpannung.getText());
                    //txSpannung.setStyle("-fx-text-fill: black");
                } else {
                    txLeistung.setStyle("-fx-text-fill: red");
                }
                if (txStrom.getText().isEmpty() == false) {
                    current = Double.parseDouble(txStrom.getText());
                    //txStrom.setStyle("-fx-text-fill: black");
                } else {
                    txStrom.setStyle("-fx-text-fill: red");
                }
                if (txWiderstand.getText().isEmpty() == false) {
                    resistence = Double.parseDouble(txWiderstand.getText());
                    //txWiderstand.setStyle("-fx-text-fill: black");
                } else {
                    txWiderstand.setStyle("-fx-text-fill: red");
                }
                Calculator myCalculator = new Calculator(
                        power, tension, current, resistence);
                System.out.print("Vorher:  ");
                System.out.println(myCalculator.toString());
                myCalculator.calculate();
                System.out.print("Nachher: ");
                System.out.println(myCalculator.toString());

                txLeistung.setText(Double.toString(myCalculator.getLeistung()));
                txSpannung.setText(Double.toString(myCalculator.getSpannung()));
                txStrom.setText(Double.toString(myCalculator.getStrom()));
                txWiderstand.setText(Double.toString(myCalculator.getWiderstand()));
            });

            Scene scene = new Scene(root, 330, 490);
            scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
            primaryStage.setScene(scene);
            primaryStage.setTitle("Formelrad");
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
