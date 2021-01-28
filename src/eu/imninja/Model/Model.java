package eu.imninja.Model;

import eu.imninja.GUI.JFrames.MessageGUI;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Model {

    private List<File> files = new ArrayList<>();
    private ObservableList<String> data;

    public void addToList(File f) {
        if(!files.contains(f)) {
            files.add(f);
        }
    }

    public int move(String item,String code) {
        Optional<File> f = files.stream().filter(file -> file.getName().equals(item)).findFirst();
        boolean type = code.equals("UP");
        int oldIndex = files.indexOf(f.get());
        int newindex;
        if(type ){
         newindex = Math.max(oldIndex - 1, 0);

        } else {

        newindex = Math.min(oldIndex + 1, files.size() - 1);
        }
        File oldFile = files.get(oldIndex);
        File newFile = files.get(newindex);
        files.set(newindex,oldFile);
        files.set(oldIndex,newFile);
        return newindex;
    }
    public ObservableList<String> getModel() {
        data = FXCollections.observableArrayList();
        files.forEach(file -> data.add(file.getName()));
        return  data;
    }

    public boolean renameAllFiles(String format) {
        String formatting = (format.equals(""))? format: "NoName $ep";
        try {
            files.forEach(file -> {

                String path = file.getPath();
                String name = file.getName();
                String newname = formatting.replace("$ep",""+(files.indexOf(file)+1));
                String newPath = path.replace(name,newname);
                file.renameTo(new File(newPath));
            });
            files.clear();
        } catch (Exception e){
            new MessageGUI(e.getLocalizedMessage());
            return false;
        }
        return true;


    }

    public void deleteSelected(String item) {
        Optional<File> f = files.stream().filter(file -> file.getName().equals(item)).findFirst();
        files.remove(f.get());
    }




}
