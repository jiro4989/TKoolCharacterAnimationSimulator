package com.jiro4989.tkcas.menubar;

import static com.jiro4989.tkcas.util.Texts.*;

import com.jiro4989.tkcas.MainController;
import com.jiro4989.tkcas.preset.PresetEditor;
import com.jiro4989.tkcas.preset.sideview.SideViewEditor;
import com.jiro4989.tkcas.stage.AboutStage;
import com.jiro4989.tkcas.stage.MyFileChooser;
import com.jiro4989.tkcas.standard.Standards;
import com.jiro4989.tkcas.util.DialogUtils;
import com.jiro4989.tkcas.util.ResourceBundleWithUtf8;
import java.io.*;
import java.net.URL;
import java.util.*;
import java.util.stream.Collectors;
import javafx.application.Platform;
import javafx.collections.*;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;

public class MyMenuBar extends VBox {

  private MainController mainController;
  private final MyFileChooser imagefileChooser;
  private final MyFileChooser walkPresetFileChooser;
  private final MyFileChooser sideViewPresetFileChooser;

  // FXMLコンポーネント{{{

  // ファイル
  @FXML private Menu fileMenu;
  @FXML private MenuItem openCharaChipMenuItem;
  @FXML private MenuItem openSideViewMenuItem;
  @FXML private Menu openWalkRecentMenu;
  @FXML private Menu openSideViewRecentMenu;
  @FXML private MenuItem closeMenuItem;
  @FXML private MenuItem currentWalkPresetMenuItem;
  @FXML private MenuItem currentSideViewPresetMenuItem;
  @FXML private MenuItem walkPresetMenuItem;
  @FXML private MenuItem sideViewPresetMenuItem;
  @FXML private MenuItem editWalkPresetMenuItem;
  @FXML private MenuItem editSideViewPresetMenuItem;
  @FXML private MenuItem preferencesMenuItem;
  @FXML private MenuItem quitMenuItem;
  @FXML private MenuItem forcedTerminateMenuItem;

  // 表示
  @FXML private Menu displayMenu;
  @FXML private MenuItem previousMenuItem;
  @FXML private MenuItem nextMenuItem;
  @FXML private MenuItem zoomDownMenuItem;
  @FXML private MenuItem zoomUpMenuItem;
  @FXML private MenuItem durationDownMenuItem;
  @FXML private MenuItem durationUpMenuItem;
  @FXML private MenuItem onlyMenuItem;
  @FXML private CheckMenuItem alwaysOnTopMenuItem;

  // フォントサイズ変更メニュー
  @FXML private Menu fontMenu;
  @FXML private ToggleGroup fontGroup;
  @FXML private RadioMenuItem fontSize8RadioMenuItem;
  @FXML private RadioMenuItem fontSize9RadioMenuItem;
  @FXML private RadioMenuItem fontSize10RadioMenuItem;
  @FXML private RadioMenuItem fontSize11RadioMenuItem;
  @FXML private RadioMenuItem fontSize12RadioMenuItem;

  // 言語変更メニュー
  @FXML private Menu langsMenu;
  @FXML private ToggleGroup langGroup;
  @FXML private RadioMenuItem jpRadioMenuItem;
  @FXML private RadioMenuItem usRadioMenuItem;

  // ヘルプメニュー
  @FXML private Menu helpMenu;
  @FXML private MenuItem aboutMenuItem;

  // }}}

  // コンストラクタ

  public MyMenuBar() { // {{{

    imagefileChooser = new MyFileChooser.Builder("Image Files", "*.png").build();
    walkPresetFileChooser =
        new MyFileChooser.Builder("Preset Files", "*.preset").initDir(WALK_PREST_DIR).build();
    sideViewPresetFileChooser =
        new MyFileChooser.Builder("Preset Files", "*.preset").initDir(SIDE_VIEW_PREST_DIR).build();

    URL location = getClass().getResource("my_menu_bar.fxml");
    ResourceBundle resources =
        ResourceBundle.getBundle(
            "com.jiro4989.tkcas.menubar.dict",
            Locale.getDefault(),
            ResourceBundleWithUtf8.UTF8_ENCODING_CONTROL);
    FXMLLoader loader = new FXMLLoader(location, resources);

    loader.setRoot(this);
    loader.setController(this);

    try {
      loader.load();

      fontSize8RadioMenuItem.setOnAction(e -> setFontSize(fontSize8RadioMenuItem));
      fontSize9RadioMenuItem.setOnAction(e -> setFontSize(fontSize9RadioMenuItem));
      fontSize10RadioMenuItem.setOnAction(e -> setFontSize(fontSize10RadioMenuItem));
      fontSize11RadioMenuItem.setOnAction(e -> setFontSize(fontSize11RadioMenuItem));
      fontSize12RadioMenuItem.setOnAction(e -> setFontSize(fontSize12RadioMenuItem));

      changeSelectedFontMenuItem();

    } catch (IOException e) {
      e.printStackTrace();
    }
  } // }}}
  // FXMLイベント
  // ファイルメニュー
  @FXML
  private void openCharaChipMenuItemOnAction() { // {{{

    imagefileChooser
        .openFile()
        .ifPresent(
            file -> {
              Standards std = mainController.getWalkStandards();
              TrimmingSelector ts = new TrimmingSelector(file, std);
              ts.showAndWait();

              setDisables(false);
              mainController.drawWalkImage(file);

              String path = file.getPath();
              openWalkRecentMenu.getItems().add(createMenuItemHasWalkAction(path));
            });
  } // }}}

  @FXML
  private void openSideViewMenuItemOnAction() { // {{{
    imagefileChooser
        .openFile()
        .ifPresent(
            file -> {
              setDisables(false);
              mainController.drawSideViewImage(file);

              String path = file.getPath();
              openSideViewRecentMenu.getItems().add(createMenuItemHasSideViewAction(path));
            });
  } // }}}

  @FXML
  private void openWalkRecentMenuItemOnAction() { // {{{
    imagefileChooser
        .openFile()
        .ifPresent(
            file -> {
              // FIXME NullPointerexception
              mainController.drawWalkImage(file);
            });
  } // }}}

  @FXML
  private void openSideViewRecentMenuItemOnAction() { // {{{
    imagefileChooser
        .openFile()
        .ifPresent(
            file -> {
              // FIXME NullPointerexception
              mainController.drawWalkImage(file);
            });
  } // }}}

  @FXML
  private void closeMenuItemOnAction() { // {{{
    mainController.clearImages();
    setDisables(true);
  } // }}}

  @FXML
  private void walkPresetMenuItemOnAction() { // {{{
    walkPresetFileChooser
        .openFile()
        .ifPresent(
            file -> {
              mainController.setWalkStandard(file);
              setCurrentWalkPreset(file);
            });
  } // }}}

  @FXML
  private void sideViewPresetMenuItemOnAction() { // {{{
    sideViewPresetFileChooser
        .openFile()
        .ifPresent(
            file -> {
              mainController.setSideViewStandard(file);
              setCurrentSideViewPreset(file);
            });
  } // }}}

  @FXML
  private void editWalkPresetMenuItemOnAction() { // {{{
    walkPresetFileChooser
        .saveFile()
        .ifPresent(
            file -> {
              PresetEditor editor = new PresetEditor(file);
              editor.showAndWait();
              mainController.setWalkStandard(file);
              setCurrentWalkPreset(file);
            });
  } // }}}

  @FXML
  private void editSideViewPresetMenuItemOnAction() { // {{{
    sideViewPresetFileChooser
        .saveFile()
        .ifPresent(
            file -> {
              SideViewEditor editor = new SideViewEditor(file);
              editor.showAndWait();
              mainController.setSideViewStandard(file);
              setCurrentSideViewPreset(file);
            });
  } // }}}

  @FXML
  private void preferencesMenuItemOnAction() { // {{{
    mainController.showConfigStage();
  } // }}}

  @FXML
  private void quitMenuItemOnAction() { // {{{
    mainController.closeRequest();
    Platform.exit();
  } // }}}

  @FXML
  private void forcedTerminateMenuItemOnAction() { // {{{
    DialogUtils.showForcedTerminationDialog();
  } // }}}
  // 表示メニュー
  @FXML
  private void alwaysOnTopMenuItemOnAction() { // {{{
    mainController.changeAlwaysOnTop();
  } // }}}

  @FXML
  private void previousMenuItemOnAction() { // {{{
    mainController.showPreviousImage();
  } // }}}

  @FXML
  private void nextMenuItemOnAction() { // {{{
    mainController.showNextImage();
  } // }}}

  @FXML
  private void zoomDownMenuItemOnAction() { // {{{

    mainController.zoomDownImages();
  } // }}}

  @FXML
  private void zoomUpMenuItemOnAction() { // {{{

    mainController.zoomUpImages();
  } // }}}

  @FXML
  private void durationDownMenuItemOnAction() { // {{{
    mainController.durationDown();
  } // }}}

  @FXML
  private void durationUpMenuItemOnAction() { // {{{
    mainController.durationUp();
  } // }}}

  @FXML
  private void onlyMenuItemOnAction() { // {{{

    mainController.showOneImage();
  } // }}}
  // 言語メニュー
  @FXML
  private void jpRadioMenuItemOnAction() { // {{{

    DialogUtils.showLanguageDialog();
    String langs = Locale.JAPAN.getLanguage();
    mainController.setLanguages(langs);
  } // }}}

  @FXML
  private void usRadioMenuItemOnAction() { // {{{

    DialogUtils.showLanguageDialog();
    String langs = Locale.ENGLISH.getLanguage();
    mainController.setLanguages(langs);
  } // }}}
  // ヘルプ
  @FXML
  private void aboutMenuItemOnAction() { // {{{
    AboutStage about =
        new AboutStage.Builder(TITLE, VERSION)
            .author("次郎 (Jiro)")
            .blog("次ログ")
            .blogUrl("http://jiroron666.hatenablog.com/")
            .css(BASIC_CSS)
            .appIcon(APP_ICON)
            .build();
    about.showAndWait();
  } // }}}
  // private メソッド
  private void setFontSize(RadioMenuItem rmi) { // {{{
    String fontSize = rmi.getText();
    mainController.setFontSize(fontSize);
  } // }}}

  private void setPresetText(MenuItem item, String text) { // {{{

    String current = item.getText();
    String top = current.split(":")[0];
    String newText = top + ": " + text;
    item.setText(newText);
  } // }}}

  private void changeSelectedFontMenuItem() { // {{{
    String defaultLanguage = Locale.getDefault().getLanguage();
    String ja = Locale.JAPAN.getLanguage();

    if (defaultLanguage.equals(ja)) jpRadioMenuItem.setSelected(true);
    else usRadioMenuItem.setSelected(true);
  } // }}}

  private MenuItem createMenuItemHasWalkAction(String path) { // {{{

    MenuItem item = new MenuItem(path);
    item.setOnAction(
        e -> {
          File file = new File(item.getText());

          if (file.exists()) {

            Standards std = mainController.getWalkStandards();
            TrimmingSelector ts = new TrimmingSelector(file, std);
            ts.showAndWait();

            setDisables(false);
            mainController.drawWalkImage(file);
          }
        });

    return item;
  } // }}}

  private MenuItem createMenuItemHasSideViewAction(String path) { // {{{

    MenuItem item = new MenuItem(path);
    item.setOnAction(
        e -> {
          setDisables(false);
          File file = new File(item.getText());
          if (file.exists()) mainController.drawSideViewImage(file);
        });

    return item;
  } // }}}
  // Getter
  public List<String> getRecentOpenedWalkFiles() { // {{{

    return openWalkRecentMenu
        .getItems()
        .stream()
        .map(item -> item.getText())
        .collect(Collectors.toList());
  } // }}}

  public List<String> getRecentOpenedSideViewFiles() { // {{{

    return openSideViewRecentMenu
        .getItems()
        .stream()
        .map(item -> item.getText())
        .collect(Collectors.toList());
  } // }}}
  // Setter
  public void setFontSizeOfMenuBar(String fontSize) { // {{{

    fontGroup
        .getToggles()
        .stream()
        .map(t -> (RadioMenuItem) t)
        .filter(t -> t.getText().equals(fontSize))
        .forEach(t -> t.setSelected(true));
  } // }}}

  public void setRecentWalkFilePaths(List<String> paths) { // {{{

    paths
        .stream()
        .distinct()
        .filter(p -> new File(p).exists())
        .map(this::createMenuItemHasWalkAction)
        .forEach(
            item -> {
              openWalkRecentMenu.getItems().add(item);
            });
  } // }}}

  public void setRecentSideViewFilePaths(List<String> paths) { // {{{

    paths
        .stream()
        .distinct()
        .filter(p -> new File(p).exists())
        .map(this::createMenuItemHasSideViewAction)
        .forEach(
            item -> {
              openSideViewRecentMenu.getItems().add(item);
            });
  } // }}}

  public void setMainController(MainController aMain) {
    mainController = aMain;
  }

  public void setAlwaysOnTop(boolean alwaysOnTop) {
    alwaysOnTopMenuItem.setSelected(alwaysOnTop);
  }

  public void setCurrentWalkPreset(String text) {
    setPresetText(currentWalkPresetMenuItem, text);
  }

  public void setCurrentWalkPreset(File file) {
    setPresetText(currentWalkPresetMenuItem, file.getName());
  }

  public void setCurrentSideViewPreset(String text) {
    setPresetText(currentSideViewPresetMenuItem, text);
  }

  public void setCurrentSideViewPreset(File file) {
    setPresetText(currentSideViewPresetMenuItem, file.getName());
  }

  public void setDisables(boolean disable) { // {{{

    onlyMenuItem.setDisable(disable);
    previousMenuItem.setDisable(disable);
    nextMenuItem.setDisable(disable);
    zoomDownMenuItem.setDisable(disable);
    zoomUpMenuItem.setDisable(disable);
    durationDownMenuItem.setDisable(disable);
    durationUpMenuItem.setDisable(disable);
    closeMenuItem.setDisable(disable);
  } // }}}

  public void addRecentWalkFile(File file) { // {{{
    MenuItem item = createMenuItemHasWalkAction(file.getPath());
    openWalkRecentMenu.getItems().add(item);
  } // }}}

  public void addRecentSideViewFile(File file) { // {{{
    MenuItem item = createMenuItemHasSideViewAction(file.getPath());
    openSideViewRecentMenu.getItems().add(item);
  } // }}}
}
