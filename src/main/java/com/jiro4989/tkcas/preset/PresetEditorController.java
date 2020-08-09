package com.jiro4989.tkcas.preset;

import static com.jiro4989.tkcas.util.Texts.*;

import java.io.File;
import java.util.Objects;
import java.util.stream.*;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.*;
import javafx.scene.layout.*;
import com.jiro4989.tkcas.util.MyProperties;
import com.jiro4989.tkcas.stage.MyFileChooser;
import com.jiro4989.tkcas.util.JavaFXCustomizeUtils;

public class PresetEditorController {

  private File presetFile;
  private MyFileChooser mfc = new MyFileChooser.Builder("Image Files", "*.png").build();

  // FXMLコンポーネント{{{

  // 設定入力//{{{

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

  // 横幅
  @FXML private Label widthLabel;
  @FXML private Button widthDownButton;
  @FXML private TextField widthTextField;
  @FXML private Button widthUpButton;

  // 高さ
  @FXML private Label heightLabel;
  @FXML private Button heightDownButton;
  @FXML private TextField heightTextField;
  @FXML private Button heightUpButton;

  // フレーム数
  @FXML private Label frameCountLabel;
  @FXML private Button frameCountDownButton;
  @FXML private TextField frameCountTextField;
  @FXML private Button frameCountUpButton;

  // 画像の横幅
  @FXML private Label imageWidthLabel;
  @FXML private TextField imageWidthTextField;

  // 画像の縦幅
  @FXML private Label imageHeightLabel;
  @FXML private TextField imageHeightTextField;

  // プレビュー画像選択
  @FXML private Button fileChooserButton;
  @FXML private TextField imageFileTextField;

  // プレビュー画像の横幅
  @FXML private Label previewImageWidthLabel;
  @FXML private TextField previewImageWidthTextField;

  // プレビュー画像の縦幅
  @FXML private Label previewImageHeightLabel;
  @FXML private TextField previewImageHeightTextField;

  // }}}

  @FXML private TitledPane previewTitledPane;

  // 画像描画
  @FXML private AnchorPane anchorPane;
  @FXML private ImageView imageView;

  // 終了ボタン
  @FXML private Button okButton;
  @FXML private Button cancelButton;

  // }}}

  @FXML
  private void initialize() { // {{{

    // イベント登録//{{{

    rowUpButton.setOnAction(e -> incrementValueOf(rowTextField, 1));
    columnUpButton.setOnAction(e -> incrementValueOf(columnTextField, 1));
    widthUpButton.setOnAction(e -> incrementValueOf(widthTextField, 1));
    heightUpButton.setOnAction(e -> incrementValueOf(heightTextField, 1));
    frameCountUpButton.setOnAction(e -> incrementValueOf(frameCountTextField, 1));

    rowDownButton.setOnAction(e -> incrementValueOf(rowTextField, -1));
    columnDownButton.setOnAction(e -> incrementValueOf(columnTextField, -1));
    widthDownButton.setOnAction(e -> incrementValueOf(widthTextField, -1));
    heightDownButton.setOnAction(e -> incrementValueOf(heightTextField, -1));
    frameCountDownButton.setOnAction(e -> incrementValueOf(frameCountTextField, -1));

    customizeTextField(rowTextField);
    customizeTextField(columnTextField);
    customizeTextField(widthTextField, 1, 1000);
    customizeTextField(heightTextField, 1, 1000);
    customizeTextField(frameCountTextField);

    // }}}

    changeGridCells();
    setImageWidth();
    setImageHeight();
  } // }}}

  private void customizeTextField(TextField textField, int min, int max) { // {{{

    textField
        .textProperty()
        .addListener(
            (obs, oldVal, newVal) -> {
              JavaFXCustomizeUtils.setNumberOnly(textField, oldVal, newVal);

              String r = rowTextField.getText();
              String c = columnTextField.getText();
              String w = widthTextField.getText();
              String h = heightTextField.getText();

              if (!Objects.equals("", r)
                  && !Objects.equals("", c)
                  && !Objects.equals("", w)
                  && !Objects.equals("", h)) {

                changeGridCells();
                setImageWidth();
                setImageHeight();
              }
            });

    JavaFXCustomizeUtils.setMaxDigitOption(textField, min, max);
    JavaFXCustomizeUtils.setScrollValueOption(textField, 5, 10);
  } // }}}

  private void customizeTextField(TextField textField) { // {{{
    customizeTextField(textField, 1, 100);
  } // }}}

  private void changeGridCells() { // {{{

    clearGridPane();

    GridPane gridPane = new GridPane();
    gridPane.setGridLinesVisible(true);
    AnchorPane.setTopAnchor(gridPane, 0.0);
    AnchorPane.setLeftAnchor(gridPane, 0.0);

    int row = Integer.parseInt(rowTextField.getText());
    int column = Integer.parseInt(columnTextField.getText());
    int width = Integer.parseInt(widthTextField.getText());
    int height = Integer.parseInt(heightTextField.getText());

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
  } // }}}

  private void clearGridPane() { // {{{

    int size = anchorPane.getChildren().size();
    if (2 <= size) {
      anchorPane.getChildren().remove(1, 2);
    }
  } // }}}

  private void setImageWidth() { // {{{

    int column = Integer.parseInt(columnTextField.getText());
    int size = Integer.parseInt(widthTextField.getText());
    int value = column * size;
    imageWidthTextField.setText("" + value);
  } // }}}

  private void setImageHeight() { // {{{

    int row = Integer.parseInt(rowTextField.getText());
    int size = Integer.parseInt(heightTextField.getText());
    int value = row * size;
    imageHeightTextField.setText("" + value);
  } // }}}

  private void incrementValueOf(TextField textField, int gain) { // {{{
    String text = textField.getText();
    int value = Integer.parseInt(text);
    value += gain;
    textField.setText("" + value);
  } // }}}

  private Pane createEmptyPane(int width, int height) { // {{{
    Pane pane = new Pane();
    pane.setPrefWidth(width);
    pane.setPrefHeight(height);
    return pane;
  } // }}}

  void closeRequest() { // {{{

    MyProperties mp = new MyProperties(PROP_DIR + "/preset_editor.fxml");
    mp.setProperty(okButton);
    mp.store();
  } // }}}

  private void storePreset() { // {{{

    MyProperties preset = new MyProperties(presetFile);

    preset.setProperty(KEY_ROW, rowTextField.getText());
    preset.setProperty(KEY_COLUMN, columnTextField.getText());
    preset.setProperty(KEY_CHARA_WIDTH, widthTextField.getText());
    preset.setProperty(KEY_CHARA_HEIGHT, heightTextField.getText());

    int column = Integer.parseInt(columnTextField.getText());
    int frameCount = Integer.parseInt(frameCountTextField.getText());
    if (column < frameCount) frameCount = column;
    preset.setProperty(KEY_FRAME_COUNT, "" + frameCount);

    preset.store();
  } // }}}

  void setPresetFile(File file) { // {{{

    presetFile = file;

    MyProperties mp = new MyProperties(presetFile);
    if (mp.load()) {

      rowTextField.setText(mp.getProperty(KEY_ROW).orElse(WALK_PREST_DEFAULT_VALUE_ROW));
      columnTextField.setText(mp.getProperty(KEY_COLUMN).orElse(WALK_PREST_DEFAULT_VALUE_COLUMN));
      widthTextField.setText(
          mp.getProperty(KEY_CHARA_WIDTH).orElse(WALK_PREST_DEFAULT_VALUE_CHARA_WIDTH));
      heightTextField.setText(
          mp.getProperty(KEY_CHARA_HEIGHT).orElse(WALK_PREST_DEFAULT_VALUE_CHARA_HEIGHT));
      frameCountTextField.setText(
          mp.getProperty(KEY_FRAME_COUNT).orElse(WALK_PREST_DEFAULT_VALUE_FRAME_COUNT));
    }
  } // }}}

  @FXML
  private void fileChooserButtonOnAction() { // {{{

    mfc.openFile()
        .ifPresent(
            file -> {
              setPreviewImage(file);
            });
  } // }}}

  void setPreviewImage(File file) { // {{{

    Image image = new Image("file:" + file.getPath());
    int width = (int) image.getWidth();
    int height = (int) image.getHeight();
    imageView.setFitWidth(width);
    imageView.setFitHeight(height);
    imageView.setImage(image);

    imageFileTextField.setText(file.getName());
    previewImageWidthTextField.setText("" + width);
    previewImageHeightTextField.setText("" + height);
  } // }}}

  @FXML
  private void okButtonOnAction() { // {{{

    closeRequest();
    cancelButton.getScene().getWindow().hide();
    storePreset();
  } // }}}

  @FXML
  private void cancelButtonOnAction() { // {{{

    closeRequest();
    cancelButton.getScene().getWindow().hide();
  } // }}}
}
