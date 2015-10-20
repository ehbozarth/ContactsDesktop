package sample;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import jodd.json.JsonSerializer;


import java.io.File;
import java.io.FileWriter;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable, ChangeListener {

    ObservableList<Contact> contacts = FXCollections.observableArrayList();

    @FXML
    TextField nameField;
    @FXML
    TextField phoneField;
    @FXML
    TextField emailField;
    @FXML
    ListView listView;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        listView.setItems(contacts);
    }//End of Initialize Method

    public void onKeyPressed(KeyEvent event){
        if(event.getCode() == KeyCode.ENTER){
            addContact();
        }//End of if
    }//End of onKeyPressed

    public void addContact(){
        if(!nameField.getText().equals("") && !phoneField.getText().equals("") && !emailField.getText().equals("")){
            Contact newContact = new Contact(nameField.getText(), phoneField.getText(), emailField.getText());
            contacts.add(newContact);
            //Resetting window value blank after entry
            nameField.setText("");
            phoneField.setText("");
            emailField.setText("");
            saveContacts(contacts);
        }//End of if statement
        else{
            nameField.setText("");
            phoneField.setText("");
            emailField.setText("");
        }

    }//End of AddItem method

    public void removeContact(){
        Contact oldContact = (Contact) listView.getSelectionModel().getSelectedItem();
        contacts.remove(oldContact);
        nameField.setText("");
        phoneField.setText("");
        emailField.setText("");

        saveContacts(contacts);

    }//End of removeContact Method

    static void saveContacts(ObservableList<Contact> contacts) {
        File f = new File("Contacts.json");
        JsonSerializer serializer = new JsonSerializer();
        String contentToSave = serializer.serialize(contacts);
        try {
            FileWriter fw = new FileWriter(f);
            fw.write(contentToSave);
            fw.close();
        }//End of try
        catch (Exception e) {

        }//End of catch
    }//End of saveContacts

    @Override
    public void changed(ObservableValue observable, Object oldValue, Object newValue) {

    }//End of Changed Method

}//End of Controller Class
