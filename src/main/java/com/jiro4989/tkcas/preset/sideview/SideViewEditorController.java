package com.jiro4989.tkcas.preset.sideview;

import static com.jiro4989.tkcas.util.Texts.*;

import com.jiro4989.tkcas.stage.MyFileChooser;
import com.jiro4989.tkcas.util.JavaFXCustomizeUtils;
import com.jiro4989.tkcas.util.MyProperties;
import java.io.File;
import java.util.Objects;
import java.util.stream.*;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.*;
import javafx.scene.layout.*;

public class SideViewEditorController {

  private File presetFile;
  private MyFileChooser mfc = new MyFileChooser.Builder("Image Files", "*.png").build();

  

  

  @FXML private TitledPane customizeTitledPane;

  // 行
  @FXML private Label rowLabel;
  @FXML private Button rowDownButton;
  @FXML private TextField rowTextField;
  @FXML private Button rowUpButton;

  // 列
  @FXML private Label columnLabel;
  @FXML private Button columnDownButton;
  @FXML private TextField columnTextField;
  @FXML private Button columnUpButton;

  // フレーム数
  @FXML private Label frameCountLabel;
  @FXML private Button frameCountDownButton;
  @FXML private TextField frameCountTextField;
  @FXML private Button frameCountUpButton;

  // プレビュー画像選択
  @FXML private Button fileChooserButton;
  @FXML private TextField imageFileTextField;

  // プレビュー画像の横幅
  @FXML private Label previewImageWidthLabel;
  @FXML private TextField previewImageWidthTextField;

  // プレビュー画像の縦幅
  @FXML private Label previewImageHeightLabel;
  @FXML private TextField previewImageHeightTextField;

  

  @FXML private TitledPane previewTitledPane;

  // 画像描画
  @FXML private AnchorPane anchorPane;
  @FXML private ImageView imageView;

  // 終了ボタン
  @FXML private Button okButton;
  @FXML private Button cancelButton;

  

  // initialize

  @FXML
  private void initialize() { 

    

    rowUpButton.setOnAction(e -> incrementValueOf(rowTextField, 1));
    columnUpButton.setOnAction(e -> incrementValueOf(columnTextField, 1));
    frameCountUpButton.setOnAction(e -> incrementValueOf(frameCountTextField, 1));

    rowDownButton.setOnAction(e -> incrementValueOf(rowTextField, -1));
    columnDownButton.setOnAction(e -> incrementValueOf(columnTextField, -1));
    frameCountDownButton.setOnAction(e -> incrementValueOf(frameCountTextField, -1));

    customizeTextField(rowTextField);
    customizeTextField(columnTextField);
    customizeTextField(frameCountTextField);

    

    changeGridCells();
  } 

  // event methods

  @FXML
  private void okButtonOnAction() { 

    closeRequest();
    cancelButton.getScene().getWindow().hide();
    storePreset();
  } 

  @FXML
  private void cancelButtonOnAction() { 

    closeRequest();
    cancelButton.getScene().getWindow().hide();
  } 

  @FXML
  private void fileChooserButtonOnAction() { 

    mfc.openFile()
        .ifPresent(
            file -> {
              setPreviewImage(file);
              changeGridCells();
            });
  } 

  // package methods

  void closeRequest() { 

    MyProperties mp = new MyProperties(PROP_DIR + "/preset_editor.fxml");
    mp.setProperty(okButton);
    mp.store();
  } 

  void setPresetFile(File file) { 

    presetFile = file;

    MyProperties mp = new MyProperties(presetFile);
    if (mp.load()) {

      rowTextField.setText(mp.getProperty(KEY_ROW).orElse(WALK_PREST_DEFAULT_VALUE_ROW));
      columnTextField.setText(mp.getProperty(KEY_COLUMN).orElse(WALK_PREST_DEFAULT_VALUE_COLUMN));
      frameCountTextField.setText(
          mp.getProperty(KEY_FRAME_COUNT).orElse(WALK_PREST_DEFAULT_VALUE_FRAME_COUNT));
    }
  } 

  void setPreviewImage(File file) { 

    Image image = new Image("file:" + file.getPath());
    int width = (int) image.getWidth();
    int height = (int) image.getHeight();
    imageView.setFitWidth(width);
    imageView.setFitHeight(height);
    imageView.setImage(image);

    imageFileTextField.setText(file.getName());
    previewImageWidthTextField.setText("" + width);
    previewImageHeightTextField.setText("" + height);
  } 

  // private methods

  private void customizeTextField(TextField textField, int min, int max) { 

    textField
        .textProperty()
        .addListener(
            (obs, oldVal, newVal) -> {
              JavaFXCustomizeUtils.setNumberOnly(textField, oldVal, newVal);

              String r = rowTextField.getText();
              String c = columnTextField.getText();

              if (!Objects.equals("", r) && !Objects.equals("", c)) {

                changeGridCells();
              }
            });

    JavaFXCustomizeUtils.setMaxDigitOption(textField, min, max);
    JavaFXCustomizeUtils.setScrollValueOption(textField, 5, 10);
  } 

  private void customizeTextField(TextField textField) { 
    customizeTextField(textField, 1, 100);
  } 

  private void changeGridCells() { 

    clearGridPane();

    GridPane gridPane = new GridPane();
    gridPane.setGridLinesVisible(true);
    AnchorPane.setTopAnchor(gridPane, 0.0);
    AnchorPane.setLeftAnchor(gridPane, 0.0);

    int row = Integer.parseInt(rowTextField.getText());
    int column = Integer.parseInt(columnTextField.getText());
    int width = (int) (imageView.getFitWidth() / column);
    int height = (int) (imageView.getFitHeight() / row);

    IntStream.range(0, row)
        .forEach(
            r -> {
              IntStream.range(0, column)
                  .forEach(
                      c -> {
                        Pane pane = createEmptyPane(width, height);
                        gridPane.add(pane, c, r);
                      });
            });

    anchorPane.getChildren().add(gridPane);
  } 

  private void clearGridPane() { 

    int size = anchorPane.getChildren().size();
    if (2 <= size) {
      anchorPane.getChildren().remove(1, 2);
    }
  } 

  private void incrementValueOf(TextField textField, int gain) { 
    String text = textField.getText();
    int value = Integer.parseInt(text);
    value += gain;
    textField.setText("" + value);
  } 

  private Pane createEmptyPane(int width, int height) { 
    Pane pane = new Pane();
    pane.setPrefWidth(width);
    pane.setPrefHeight(height);
    return pane;
  } 

  private void storePreset() { 

    MyProperties preset = new MyProperties(presetFile);

    preset.setProperty(KEY_ROW, rowTextField.getText());
    preset.setProperty(KEY_COLUMN, columnTextField.getText());

    int column = Integer.parseInt(columnTextField.getText());
    int frameCount = Integer.parseInt(frameCountTextField.getText());
    if (column < frameCount) frameCount = column;
    if (column % frameCount != 0) frameCount = column / frameCount;
    preset.setProperty(KEY_FRAME_COUNT, "" + frameCount);

    preset.store();
  } 
}
