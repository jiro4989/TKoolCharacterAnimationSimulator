package com.jiro4989.tkcas;

import static com.jiro4989.tkcas.util.Texts.*;
import static java.util.stream.IntStream.range;

import com.jiro4989.tkcas.config.ConfigStage;
import com.jiro4989.tkcas.layout.PositionsFlowPane;
import com.jiro4989.tkcas.menubar.MyMenuBar;
import com.jiro4989.tkcas.menubar.TrimmingSelector;
import com.jiro4989.tkcas.standard.Standards;
import com.jiro4989.tkcas.util.DialogUtils;
import com.jiro4989.tkcas.util.MyProperties;
import com.jiro4989.tkcas.util.OpenRecentFilesUtils;
import java.io.*;
import java.util.*;
import java.util.regex.*;
import javafx.fxml.FXML;
import javafx.scene.input.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class MainController {

  // 画像描画戦略
  private DrawImageStrategy strategy;

  // 画像の規格
  private Standards walkStandard;
  private Standards sideViewStandard;

  // 設定変更画面
  private Optional<ConfigStage> configStageOpt = Optional.empty();

  // ファイルの更新時間監視
  private static FileObserver fileObserver;

  // 設定ファイル
  private final MyProperties preferences = new MyProperties(PREFERENCES_FILE);

  // 最近開いたファイル
  private final MyProperties walkLog = new MyProperties(LOG_FILE_WALK);
  private final MyProperties sideViewLog = new MyProperties(LOG_FILE_SIDE_VIEW);

  @FXML private MyMenuBar myMenuBar;
  @FXML private PositionsFlowPane positionsFlowPane;

  // 初期化

  @FXML
  private void initialize() {

    preferences.load();
    myMenuBar.setMainController(this);

    // TODO 一時的な設定
    String walk = WALK_PREST;
    String sideView = SIDE_VIEW_PREST;

    walkStandard = WalkGraphicsStrategy.createStandard(walk);
    sideViewStandard = SideViewStrategy.createStandard(sideView);
  }

  // イベントメソッド

  @FXML
  private void rootOnDragOver(DragEvent e) {

    Dragboard board = e.getDragboard();
    if (board.hasFiles()) {

      e.acceptTransferModes(TransferMode.COPY);
    }
  }

  @FXML
  private void rootOnDragDropped(DragEvent e) {

    Dragboard board = e.getDragboard();
    if (board.hasFiles()) {

      Pattern p = Pattern.compile("^.*\\.((?i)png)");
      board
          .getFiles()
          .stream()
          .filter(f -> p.matcher(f.getName()).matches())
          .forEach(
              file -> {
                myMenuBar.setDisables(false);

                String result = DialogUtils.showChoiseDialog();
                if (result.equals("w")) {

                  TrimmingSelector ts = new TrimmingSelector(file, walkStandard);
                  ts.showAndWait();

                  drawWalkImage(file);
                  myMenuBar.addRecentWalkFile(file);
                  return;
                }
                drawSideViewImage(file);
                myMenuBar.addRecentSideViewFile(file);
              });
    }
  }

  // メソッド

  public void updateImages(File file) {
    strategy.drawImage(file);
  }

  public void drawWalkImage(File file) {

    if (fileObserver != null) fileObserver.stop();

    fileObserver = new FileObserver(200, file, this);
    strategy = new WalkGraphicsStrategy(this);

    positionsFlowPane.drawWalkImage(file.getPath(), walkStandard);

    configStageOpt.ifPresent(
        cs -> {
          cs.applyZoomRate();
          cs.applyAnimationSpeed();
        });
  }

  public void drawSideViewImage(File file) {

    if (fileObserver != null) fileObserver.stop();

    fileObserver = new FileObserver(200, file, this);
    strategy = new SideViewStrategy(this);

    positionsFlowPane.drawSideViewImage(file.getPath(), sideViewStandard);

    configStageOpt.ifPresent(
        cs -> {
          cs.applyZoomRate();
          cs.applyAnimationSpeed();
        });
  }

  public void clearImages() {
    positionsFlowPane.clearImages();
  }

  public void showConfigStage() {

    configStageOpt.ifPresent(
        c -> {
          if (c.isShowing()) c.hide();
          else c.show();
          getStage().requestFocus();
        });
  }

  public void updateZoomRate(double zoom) {

    positionsFlowPane.updateZoomRate(zoom);
  }

  public void updateZoomRate(ScrollEvent e) {

    configStageOpt.ifPresent(
        cs -> {
          cs.changeZoomRate(e);
        });
  }

  public void updateAnimationSpeed(int duration) {

    positionsFlowPane.updateAnimationSpeed(duration);
  }

  public void changeAlwaysOnTop() {

    Stage stage = getStage();
    boolean alwaysOnTop = getStage().isAlwaysOnTop();
    stage.setAlwaysOnTop(!alwaysOnTop);
    preferences.setProperty(KEY_ALWAYS_ON_TOP, "" + alwaysOnTop);
  }

  public void showPreviousImage() {
    positionsFlowPane.showPreviousImage();
  }

  public void showNextImage() {
    positionsFlowPane.showNextImage();
  }

  public void zoomDownImages() {
    configStageOpt.ifPresent(cs -> cs.zoomDown());
  }

  public void zoomUpImages() {
    configStageOpt.ifPresent(cs -> cs.zoomUp());
  }

  public void durationDown() {
    configStageOpt.ifPresent(cs -> cs.durationDown());
  }

  public void durationUp() {
    configStageOpt.ifPresent(cs -> cs.durationUp());
  }

  public void showOneImage() {
    positionsFlowPane.switchViewerMode();
  }

  public void closeRequest() {

    // 最近開いた画像を保存
    List<String> openedWalkFiles = myMenuBar.getRecentOpenedWalkFiles();
    List<String> openedSideViewFiles = myMenuBar.getRecentOpenedSideViewFiles();
    storeRecentFile(walkLog, openedWalkFiles);
    storeRecentFile(sideViewLog, openedSideViewFiles);

    configStageOpt.ifPresent(
        cs -> {
          double rate = cs.getZoomRate();
          double duration = cs.getDuration();
          preferences.setProperty(KEY_ZOOM_RATE, "" + rate);
          preferences.setProperty(KEY_DURATION, "" + duration);
        });

    boolean alwaysOnTop = getStage().isAlwaysOnTop();
    preferences.setProperty(KEY_ALWAYS_ON_TOP, "" + alwaysOnTop);
    preferences.store();

    Main.mainMp.setProperty(myMenuBar);
    Main.mainMp.store();
  }

  // package methods

  void resizeConfigStage() {

    configStageOpt.ifPresent(
        c -> {
          c.resize(positionsFlowPane);
        });
  }

  // private methods

  private void storeRecentFile(MyProperties log, List<String> openedList) {

    List<String> newList = OpenRecentFilesUtils.createContainsNullList(openedList);
    range(0, OpenRecentFilesUtils.MAX)
        .forEach(
            i -> {
              String key = KEY_LOG + i;
              String value = newList.get(i);
              log.setProperty(key, value);
            });

    log.store();
  }

  private Stage getStage() {

    return (Stage) positionsFlowPane.getScene().getWindow();
  }

  private void setConfigs() {

    configStageOpt.ifPresent(
        cs -> {
          String r = preferences.getProperty(KEY_ZOOM_RATE).orElse(DEFAULT_VALUE_ZOOM_RATE);
          String d = preferences.getProperty(KEY_DURATION).orElse(DEFAULT_VALUE_DURATION);
          double rate = Double.parseDouble(r);
          double duration = Double.parseDouble(d);

          cs.setZoomRate(rate);
          cs.setDuration(duration);
        });
  }

  private List<String> createRecentListWith(MyProperties log) {

    log.load();

    String empty = OpenRecentFilesUtils.EMPTY;
    int max = OpenRecentFilesUtils.MAX;

    List<String> list = new ArrayList<>(max);
    for (int i = 0; i < max; i++) {

      String path = log.getProperty(KEY_LOG + i).orElse(empty);
      if (path.equals(empty)) break;
      list.add(path);
    }

    return list;
  }

  // Getter

  public Standards getWalkStandards() {
    return walkStandard;
  }

  // Setter

  public void setFontSize(String fontSize) {

    VBox root = (VBox) positionsFlowPane.getScene().lookup("#root");
    root.setStyle("-fx-font-size:" + fontSize + "pt;");
    preferences.setProperty(KEY_FONT_SIZE, fontSize);
  }

  public void setLanguages(String languages) {

    preferences.setProperty(KEY_LANGS, languages);
  }

  public void setWalkStandard(File file) {

    myMenuBar.setCurrentWalkPreset(file);

    String path = file.getPath();
    walkStandard = WalkGraphicsStrategy.createStandard(path);

    preferences.setProperty(KEY_WALK_PRESET, path);

    if (fileObserver != null) fileObserver.stop();
    clearImages();
  }

  public void setSideViewStandard(File file) {

    myMenuBar.setCurrentSideViewPreset(file);

    String path = file.getPath();
    sideViewStandard = SideViewStrategy.createStandard(path);

    preferences.setProperty(KEY_SIDE_VIEW_PRESET, path);

    if (fileObserver != null) fileObserver.stop();
    clearImages();
  }

  void setConfigStageInstance() {
    ConfigStage cs = new ConfigStage(positionsFlowPane, this);
    configStageOpt = Optional.ofNullable(cs);
    setConfigs();
  }

  void setInitAlwaysOnTop() {

    String a = preferences.getProperty(KEY_ALWAYS_ON_TOP).orElse("false");
    boolean alwaysOnTop = Boolean.valueOf(a);
    getStage().setAlwaysOnTop(alwaysOnTop);
    myMenuBar.setAlwaysOnTop(alwaysOnTop);
  }

  void setFontSizeOfMenuBar(String fontSize) {

    myMenuBar.setFontSizeOfMenuBar(fontSize);
  }

  void setRecentFiles() {

    List<String> walkList = createRecentListWith(walkLog);
    myMenuBar.setRecentWalkFilePaths(walkList);

    List<String> sideViewList = createRecentListWith(sideViewLog);
    myMenuBar.setRecentSideViewFilePaths(sideViewList);
  }
}
