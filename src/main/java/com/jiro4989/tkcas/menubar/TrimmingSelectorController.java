package com.jiro4989.tkcas.menubar;

import com.jiro4989.tkcas.standard.Standards;
import java.io.File;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.*;
import javafx.scene.input.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class TrimmingSelectorController {

  public static int baseX = 0;
  public static int baseY = 0;

  private int blockWidth = 0;
  private int blockHeight = 0;

  

  @FXML private TitledPane titledPane;
  @FXML private ImageView imageView;
  @FXML private GridPane focusGridPane;
  @FXML private GridPane selectedGridPane;

  @FXML private Button okButton;
  @FXML private Button cancelButton;

  

  // 初期化

  @FXML
  private void initialize() { 
  } 

  // イベントメソッド

  @FXML
  private void imageViewOnMouseMoved(MouseEvent e) { 

    int x = (int) e.getX();
    int y = (int) e.getY();
    int gridX = x / blockWidth * blockWidth;
    int gridY = y / blockHeight * blockHeight;

    int imgWidth = (int) imageView.getFitWidth();
    int imgHeight = (int) imageView.getFitHeight();

    if ((gridX + blockWidth) <= imgWidth && (gridY + blockHeight) <= imgHeight) {

      focusGridPane.setLayoutX(gridX);
      focusGridPane.setLayoutY(gridY);
    }
  } 

  @FXML
  private void selectedGridPaneOnMouseMoved(MouseEvent e) { 

    int x = (int) (e.getX() + selectedGridPane.getLayoutX());
    int y = (int) (e.getY() + selectedGridPane.getLayoutY());
    int gridX = x / blockWidth * blockWidth;
    int gridY = y / blockHeight * blockHeight;

    int imgWidth = (int) imageView.getFitWidth();
    int imgHeight = (int) imageView.getFitHeight();

    if ((gridX + blockWidth) <= imgWidth && (gridY + blockHeight) <= imgHeight) {

      focusGridPane.setLayoutX(gridX);
      focusGridPane.setLayoutY(gridY);
    }
  } 

  @FXML
  private void selectedGridPaneOnMouseClicked(MouseEvent e) { 

    if (2 <= e.getClickCount()) {

      okButtonOnAction();
    }
  } 

  @FXML
  private void focusGridPaneOnMouseClicked(MouseEvent e) { 

    int x = (int) focusGridPane.getLayoutX();
    int y = (int) focusGridPane.getLayoutY();

    selectedGridPane.setLayoutX(x);
    selectedGridPane.setLayoutY(y);
  } 

  @FXML
  private void okButtonOnAction() { 

    baseX = (int) selectedGridPane.getLayoutX();
    baseY = (int) selectedGridPane.getLayoutY();
    getStage().hide();
  } 

  @FXML
  private void cancelButtonOnAction() { 

    getStage().hide();
  } 

  private Stage getStage() { 

    return (Stage) okButton.getScene().getWindow();
  } 

  // Setter

  void setImage(File file) { 

    Image image = new Image("file:" + file.getPath());
    double width = image.getWidth();
    double height = image.getHeight();

    imageView.setImage(image);
    imageView.setFitWidth(width);
    imageView.setFitHeight(height);
  } 

  void setStandards(Standards std) { 

    int width = std.width;
    int height = std.height;
    int row = std.row;
    int column = std.column;

    blockWidth = width * column;
    blockHeight = height * row;

    selectedGridPane.setPrefWidth(width * column);
    selectedGridPane.setPrefHeight(height * row);
    focusGridPane.setPrefWidth(width * column);
    focusGridPane.setPrefHeight(height * row);
  } 
}
