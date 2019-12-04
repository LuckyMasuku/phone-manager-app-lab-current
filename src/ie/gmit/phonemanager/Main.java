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
    PhoneManager pm = new PhoneManager(); // Used for managing Phones

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
        Button btnAddPhone = new Button("Add Phones");
        TextField tfPhoneID = new TextField();
        TextField tfPhoneModel = new TextField();
        TextField tfPhoneType = new TextField();
        
          // If text field is empty
        tfPhoneID.setPromptText("Enter Phone ID");
        tfPhoneModel.setPromptText("Model");
        tfPhoneType.setPromptText("Type");
        btnAddPhone.setOnAction(e -> {
            if (tfPhoneID.getText().trim().equals("")) {
                taMyOutput.setText("Invalid");
            } else {
                Phone phone = new Phone(tfPhoneID.getText(), tfPhoneModel.getText(), tfPhoneType.getText());
               // Add Phones to student list
                pm.addPhone(phone); 
                tfPhoneID.clear();
                tfPhoneModel.clear();
                tfPhoneType.clear();
            }
        });

        // Delete Phone arrangement
        TextField tfPhoneDel = new TextField();
        Button btnDelPhone = new Button("Delete Phone");

        tfPhoneDel.setPromptText("Enter Phone ID");

        btnDelPhone.setOnAction(e -> {

            pm.deletePhoneById(tfPhoneDel.getText());

        });

        // Search for Student by ID
        TextField tfPhoneSearch = new TextField();
        Button btnPhoneSearch = new Button("Search By ID");

        tfPhoneSearch.setPromptText("Enter Phone ID");

        
        btnPhoneSearch.setOnAction(e -> {

           Phone phoneObj = pm.findPhoneById(tfPhoneSearch.getText());
            taMyOutput.setText(phoneObj.getModel() + " " + phoneObj.getType());

        });

        // Save to DB
        Button btnSaveDB = new Button("Save Phones to DB");
        btnSaveDB.setOnAction(e -> {
            if (pm.findTotalPhones() > 0) {
                try {
                    File phoneDB = new File("./resources/phonesDB.ser");
                    ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(phoneDB));
                    out.writeObject(pm);
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
                pm = (PhoneManager) in.readObject();
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
        gridPane1.add(tfPhoneModel, 1, 0);
        gridPane1.add(tfPhoneType, 2, 0);
        gridPane1.add(btnAddPhone, 3, 0);

        gridPane1.add(btnShowTotal, 0, 1);
        gridPane1.add(tfTotalNumberOfPhones, 1, 1);
        gridPane1.add(tfPhoneDel, 0, 2);
        gridPane1.add(btnDelPhone, 1, 2);

        gridPane1.add(tfPhoneSearch, 0, 3);
        gridPane1.add(btnPhoneSearch, 1, 3);

        gridPane1.add(btnSaveDB, 0, 4);
        gridPane1.add(btnLoadDB, 0, 5);
        gridPane1.add(tfLoadPhones, 1, 5);
        gridPane1.add(taMyOutput, 0, 6, 2, 1);
        gridPane1.add(btnQuit, 0, 7);

        // Preparing the Stage (i.e. the container of any JavaFX application)
        // Create a Scene by passing the root group object, height and width
        Scene scene1 = new Scene(gridPane1, 700, 450);
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
