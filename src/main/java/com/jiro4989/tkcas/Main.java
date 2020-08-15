package com.jiro4989.tkcas;

import static com.jiro4989.tkcas.util.Texts.*;

import com.jiro4989.tkcas.util.MyProperties;
import com.jiro4989.tkcas.util.PresetsUtils;
import com.jiro4989.tkcas.util.ResourceBundleWithUtf8;
import java.io.*;
import java.net.URL;
import java.util.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class Main extends Application {

  static MyProperties mainMp = new MyProperties(PROP_DIR + "/main.xml");
  private MainController controller;

  @Override
  public void start(Stage primaryStage) {

    changeLanguages();
    PresetsUtils.mkInitDirs();
    PresetsUtils.mkInitPresets();

    URL location = getClass().getResource("main.fxml");
    ResourceBundle resources =
        ResourceBundle.getBundle(
            "com.jiro4989.tkcas.dict",
            Locale.getDefault(),
            ResourceBundleWithUtf8.UTF8_ENCODING_CONTROL);
    FXMLLoader loader = new FXMLLoader(location, resources);

    try {

      VBox root = (VBox) loader.load();
      controller = (MainController) loader.getController();
      Scene scene = new Scene(root, 350, 140);
      scene.getStylesheets().add(BASIC_CSS);

      primaryStage.setScene(scene);
      primaryStage.setTitle(TITLE_VERSION);
      primaryStage.getIcons().add(new Image(APP_ICON));
      primaryStage.setMinWidth(80.0);
      primaryStage.setMinHeight(140.0);

      if (mainMp.load()) mainMp.customStage(primaryStage);

      // 設定ウィンドウの追従リスナー
      primaryStage.xProperty().addListener((obs, o, n) -> controller.resizeConfigStage());
      primaryStage.yProperty().addListener((obs, o, n) -> controller.resizeConfigStage());
      primaryStage.widthProperty().addListener((obs, o, n) -> controller.resizeConfigStage());
      primaryStage.heightProperty().addListener((obs, o, n) -> controller.resizeConfigStage());

      primaryStage.setOnCloseRequest(e -> controller.closeRequest());

      final Delta delta = new Delta();

      root.setOnMousePressed(
          e -> {
            delta.x = primaryStage.getX() - e.getScreenX();
            delta.y = primaryStage.getY() - e.getScreenY();
          });

      root.setOnMouseDragged(
          e -> {
            primaryStage.setX(e.getScreenX() + delta.x);
            primaryStage.setY(e.getScreenY() + delta.y);
          });

      root.setOnScroll(e -> controller.updateZoomRate(e));

      controller.setConfigStageInstance();
      controller.setInitAlwaysOnTop();

      // フォントサイズの変更
      final MyProperties preferences = new MyProperties(PREFERENCES_FILE);
      preferences.load();

      // プリセットの変更
      String walk = preferences.getProperty(KEY_WALK_PRESET).orElse(WALK_PREST);
      String sideView = preferences.getProperty(KEY_SIDE_VIEW_PRESET).orElse(SIDE_VIEW_PREST);
      controller.setWalkStandard(new File(walk));
      controller.setSideViewStandard(new File(sideView));

      primaryStage.show();

    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public static void main(String... args) {
    System.out.println("--------------------------------------------");
    System.out.println("application_name: " + TITLE);
    System.out.println("version: " + Version.version);
    System.out.println("commit_hash: " + Version.commitHash);
    System.out.println("document: README.txt");
    System.out.println("author: 次郎 (jiro)");
    System.out.println("contact: https://twitter.com/jiro_saburomaru");
    System.out.println("--------------------------------------------");
    launch(args);
  }

  private void changeLanguages() {

    MyProperties preferences = new MyProperties(PREFERENCES_FILE);
    if (preferences.load()) {

      String ja = Locale.JAPAN.getLanguage();
      String langs = preferences.getProperty(KEY_LANGS).orElse(ja);
      if (!langs.equals(ja)) {

        Locale.setDefault(Locale.ENGLISH);
      }
    }
  }

  private class Delta {
    double x;
    double y;
  }
}
