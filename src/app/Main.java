package app;

import jiro.java.util.MyProperties;

import static util.Texts.*;

import java.io.*;
import java.util.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Main extends Application {

  static MyProperties mainMp = new MyProperties(PROP_DIR + "/main.xml");
  private MainController controller;

  @Override
  public void start(Stage primaryStage) {//{{{

    changeLanguages();

    FXMLLoader loader = new FXMLLoader(getClass().getResource("main.fxml"));

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
      primaryStage.xProperty      ( ).addListener ( ( obs, o, n) -> controller.resizeConfigStage ( ) ) ;
      primaryStage.yProperty      ( ).addListener ( ( obs, o, n) -> controller.resizeConfigStage ( ) ) ;
      primaryStage.widthProperty  ( ).addListener ( ( obs, o, n) -> controller.resizeConfigStage ( ) ) ;
      primaryStage.heightProperty ( ).addListener ( ( obs, o, n) -> controller.resizeConfigStage ( ) ) ;

      primaryStage.setOnCloseRequest(e -> controller.closeRequest());

      // マウスドラッグでウィンドウの位置を変更//{{{

      final Delta delta = new Delta();

      root.setOnMousePressed(e -> {
        delta.x = primaryStage.getX() - e.getScreenX();
        delta.y = primaryStage.getY() - e.getScreenY();
      });

      root.setOnMouseDragged(e -> {
        primaryStage.setX(e.getScreenX() + delta.x);
        primaryStage.setY(e.getScreenY() + delta.y);
      });

      //}}}

      root.setOnScroll(e -> controller.updateZoomRate(e));

      controller.setConfigStageInstance();
      primaryStage.show();

    } catch (IOException e) {
      e.printStackTrace();
    }

  }//}}}

  public static void main(String... args) {//{{{
    launch(args);
  }//}}}

  private void changeLanguages() {//{{{

    MyProperties preferences = new MyProperties(PREFERENCES_FILE);
    if (preferences.load()) {

      String ja = Locale.JAPAN.getLanguage();
      String langs = preferences.getProperty(KEY_LANGS).orElse(ja);
      if (!langs.equals(ja)) {

        Locale.setDefault(Locale.ENGLISH);

      }

    }

  }//}}}

  private class Delta {//{{{
    double x;
    double y;
  }//}}}

}
