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
        new MessageGUI("Dateien wurden unbenannt");
        formatting.clear();
        fillList();

        }
    }

    public Controller() {
        model = new Model();
    }

    public void loadFiles() {

        FileChooser fileChooser = new FileChooser();
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
            String keypress = e.getCode().toString();
            String selected = (FileList.getSelectionModel().getSelectedItem() != null)? FileList.getSelectionModel().getSelectedItem().toString() : "";
            if(keypress.equals("UP") || keypress.equals("DOWN") ) {

                int index = model.move(selected,e.getCode().toString());
                FileList.getSelectionModel().select(index);
            }
            if(keypress.equals("DELETE")) {
                    if(!selected.equals(""))model.deleteSelected(selected);
            }


            fillList();
        });

    }






}
