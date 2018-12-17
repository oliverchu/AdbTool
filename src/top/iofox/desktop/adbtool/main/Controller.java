package top.iofox.desktop.adbtool.main;

import com.jfoenix.controls.JFXAlert;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXListView;
import com.sun.javafx.binding.BidirectionalBinding;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.StackPane;
import javafx.stage.DirectoryChooser;
import top.iofox.desktop.adbtool.dialog.MessageDialog;
import top.iofox.lib.adb.core.Adb;
import top.iofox.lib.adb.core.Target;

import java.io.File;

public class Controller {

    @FXML
    JFXComboBox<String> cbSdkPath;

    @FXML
    JFXListView<String> lvDevices;

    @FXML
    TitledPane tpSdkPath,tpDeviceList;

    @FXML
    StackPane rootPane;


    @FXML
    public void onAddPathClick(ActionEvent event){
        DirectoryChooser chooser = new DirectoryChooser();
        chooser.setTitle("选择Android SDK");
        chooser.setInitialDirectory(new File(System.getProperty("user.home")));
        File file = chooser.showDialog(Main.getPrimaryStage());
        if(file!=null){
            File adb = new File(file,"platform-tools"+File.separator+"adb");
            cbSdkPath.getItems().add(adb.getAbsolutePath());
        }
    }


    public void init() {
        System.getenv().forEach((k,v)->{
            if(k.equalsIgnoreCase("android_home") || k.equalsIgnoreCase("android_sdk_home")){
                cbSdkPath.getItems().add(new File(v,"platform-tools"+File.separator+"adb").toString());

            }
        });
        cbSdkPath.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            Adb.setAdbPath(newValue);
            Target[] targets = Adb.getTargets();
            for(Target t:targets){
                lvDevices.getItems().addAll(t.getDevice().getSerial());
            }
            tpSdkPath.setText(newValue);
        });
        lvDevices.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                tpDeviceList.setText("当前设备："+newValue);
//                MessageDialog.show(rootPane,"标题","文本内容");
            }
        });
    }
}
