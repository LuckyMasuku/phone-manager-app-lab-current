package ie.gmit.phonemanager;

import java.io.File;
import java.io.Serializable;
import java.io.ObjectOutputStream;
import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class Main extends Application implements Serializable {

    private static final long serialVersionUID = 1L; // Used for serialization
    PhoneManager sm = new PhoneManager(); // Used for managing Phones

    @Override
    public void start(Stage primaryStage) {
        // Create TextArea node for bottom of scene 1
        TextArea taMyOutput = new TextArea();
        taMyOutput.setPrefHeight(100); // sets height of the TextArea to 400 pixels
        taMyOutput.setPrefWidth(100); // sets width of the TextArea to 300 pixels

        // Show total number of Phones
        Button btnShowTotal = new Button("Show Total Phones");
        TextField tfTotalNumberOfPhones = new TextField();

        tfTotalNumberOfPhones.setEditable(false); // This box is not editable. Only displays result.
        tfTotalNumberOfPhones.setPromptText("0");

        btnShowTotal.setOnAction(e -> {

            // Code to run when button is clicked
            tfTotalNumberOfPhones.setText(Integer.toString(pm.findTotalPhones()));

        });

        // Add Phones arrangement
        Button btnAddPhone = new Button("AddPhones");
        TextField tfPhoneID = new TextField();

        tfPhoneID.setPromptText("Enter Phone ID");

        btnAddPhone.setOnAction(e -> {
            if (tfPhoneID.getText().trim().equals("")) { // If text field is empty

                taMyOutput.setText("Invalid");
            } else {

                Phone phone = new Phone(tfPhoneID.getText());
                sm.addPhones(student); // Add Phones to student list
                tfPhonesID.clear();
            }
        });

        // Delete Phone arrangement
        TextField tfPhoneDel = new TextField();
        Button btnDelPhone = new Button("Delete Phone");

        tfPhoneDel.setPromptText("Enter Phone ID");

        btnDelPhone.setOnAction(e -> {

            sm.deletePhoneById(tfPhoneDel.getText());

        });

        // Save to DB
        Button btnSaveDB = new Button("Save Phones to DB");
        btnSaveDB.setOnAction(e -> {
            if (sm.findTotalPhones() > 0) {
                try {
                    File phoneDB = new File("./resources/phonesDB.ser");
                    ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(phoneDB));
                    out.writeObject(sm);
                    out.close();
                    taMyOutput.setText("Phone Database Saved");
                } catch (Exception exception) {
                    System.out.print("[Error] Cannont save DB. Cause: ");
                    exception.printStackTrace();
                    taMyOutput.setText("ERROR: Failed to save Phones DB!");
                }
            } else {
                taMyOutput.setText("No Phones in List to save!");
            }
        });
         // new code 25/11
        // Load from DB
        Button btnLoadDB = new Button("Load Phones from DB");
        TextField tfLoadPhones = new TextField();
        tfLoadPhones.setPromptText("Enter path to DB");

        tfLoadPhones.setPromptText("Please enter DB from path"); 
        btnLoadDB.setOnAction(e -> {
          //this works with new button sm
            try{
                File phoneDB = new File(tfLoadPhones.getText());
                ObjectInputStream in = new ObjectInputStream(new FileInputStream(phoneDB));
                sm = (PhoneManager) in.readObject();
                in.close();
                taMyOutput.setText("Successfully loaded Phones from Database");
            } catch (Exception exception) {
                    System.out.print("[Error] Cannont load DB. Cause: ");
                    exception.printStackTrace();
                    taMyOutput.setText("ERROR: Failed to load Phones DB!");
            }

        });
       



        // Add Quit button
		Button btnQuit = new Button("Quit");	
        btnQuit.setOnAction(e -> 
            Platform.exit()
        );

        // Adding and arranging all the nodes in the grid - add(node, column, row)
        GridPane gridPane1 = new GridPane();
        gridPane1.add(tfPhoneID, 0, 0);
        gridPane1.add(btnAddPhone, 1, 0);
        gridPane1.add(btnShowTotal, 0, 1);
        gridPane1.add(tfTotalNumberOfPhones, 1, 1);
        gridPane1.add(tfPhoneDel, 0, 2);
        gridPane1.add(btnDelPhone, 1, 2);
        gridPane1.add(btnSaveDB, 0, 3);
        gridPane1.add(btnLoadDB, 0, 4);
        gridPane1.add(tfLoadPhones, 1, 4);
        gridPane1.add(taMyOutput, 0, 5, 2, 1);
        gridPane1.add(btnQuit, 0, 6);

        // Preparing the Stage (i.e. the container of any JavaFX application)
        // Create a Scene by passing the root group object, height and width
        Scene scene1 = new Scene(gridPane1, 400, 450);
        // Setting the title to Stage.

        if (getParameters().getRaw().size() == 0) {
            primaryStage.setTitle("Phone Manager Application");
        } else {
            primaryStage.setTitle(getParameters().getRaw().get(0));
        }

        // Setting the scene to Stage
        primaryStage.setScene(scene1);
        // Displaying the stage
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
