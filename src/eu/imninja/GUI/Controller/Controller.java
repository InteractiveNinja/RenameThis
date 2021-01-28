package eu.imninja.GUI.Controller;

import eu.imninja.GUI.JFrames.MessageGUI;
import eu.imninja.Model.Model;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import javax.swing.filechooser.FileSystemView;
import java.io.File;
import java.util.List;

public class Controller {

    private Model model;

    @FXML
    TextField formatting;
    @FXML
    ListView FileList;
    public void renameFiles() {
        if(model.renameAllFiles(formatting.getText())) {
        new MessageGUI("all Good");
        formatting.clear();
        fillList();

        }
    }

    public Controller() {
        model = new Model();
    }

    public void loadFiles() {

        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select Episodes");
        fileChooser.setInitialDirectory(FileSystemView.getFileSystemView().getHomeDirectory());
        List<File> f = fileChooser.showOpenMultipleDialog(new Stage());
        if(f != null) f.forEach(file -> model.addToList(file));
        fillList();
    }

    private void fillList() {
        FileList.setItems(model.getModel());
    }
    public void keyPressed(){
        FileList.setOnKeyPressed((e) ->{
            String selected = FileList.getSelectionModel().getSelectedItem().toString();
            System.out.println(e.getText());
            int index = model.move(selected,e.getCode().toString());
            fillList();
            FileList.getSelectionModel().select(index);
        });

    }






}
