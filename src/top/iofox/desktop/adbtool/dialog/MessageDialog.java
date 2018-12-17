package top.iofox.desktop.adbtool.dialog;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

public class MessageDialog {

    public static void show(StackPane stackPane, String title, String message){
        JFXDialog dialog = new JFXDialog();
        VBox box = new VBox();
        Label label = new Label(title);
        label.setPadding(new Insets(10,10,10,10));
        JFXButton btnOk = new JFXButton("确定");

        box.setStyle("-fx-background-color: #336699;");
        box.setPrefHeight(100);
        box.setPrefWidth(100);
        box.getChildren().addAll(label,btnOk);
        dialog.show(stackPane);
    }

}
