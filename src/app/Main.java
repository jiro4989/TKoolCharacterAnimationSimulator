package app;

import jiro.java.util.MyProperties;

import java.io.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Main extends Application {

  private static final String PROP_DIR = "./properties/mvccs";
  private MyProperties window = new MyProperties(PROP_DIR + "/main.xml");
  private MainController controller;

  // 定数{{{
  public static final String TITLE         = "TKool Character Chip Simulator";
  public static final String VERSION       = "ver 1.0.0";
  public static final String TITLE_VERSION = TITLE + " - " + VERSION;

  public static final String BASIC_CSS = "/app/res/css/basic.css";
  public static final String APP_ICON  = "/app/res/img/app_icon.png";
  //}}}

  @Override
  public void start(Stage primaryStage) {//{{{

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

      // 設定ウィンドウの追従リスナー
      primaryStage.xProperty      ( ).addListener ( ( obs, o, n) -> controller.resizeConfigStage ( ) ) ;
      primaryStage.yProperty      ( ).addListener ( ( obs, o, n) -> controller.resizeConfigStage ( ) ) ;
      primaryStage.widthProperty  ( ).addListener ( ( obs, o, n) -> controller.resizeConfigStage ( ) ) ;
      primaryStage.heightProperty ( ).addListener ( ( obs, o, n) -> controller.resizeConfigStage ( ) ) ;

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

  /*
     @Override
     public void start(Stage primaryStage) {//{{{

     changeLanguages();

     URL location = getClass().getResource("main.fxml");
     resources = ResourceBundle.getBundle(
     "app.res.langs.main"
     , Locale.getDefault()
     , ResourceBundleWithUtf8.UTF8_ENCODING_CONTROL
     );
     FXMLLoader loader = new FXMLLoader(location, resources);

     try {

     VBox root = (VBox) loader.load();
     mainController = (MainController) loader.getController();

     Scene scene = new Scene(root, 950, 550);
     scene.getStylesheets().add(getClass().getResource("res/css/basic.css").toExternalForm());

     primaryStage.setScene(scene);
     primaryStage.getIcons().add(new Image(getClass().getResource("res/img/app_icon.png").toExternalForm()));
     primaryStage.setTitle(TITLE);
     primaryStage.setMinWidth(480);
     primaryStage.setMinHeight(270);
     primaryStage.setOnCloseRequest(e -> { mainController.closeRequest(); });

     MyProperties mainMp = new MyProperties("properties/main.xml");
     if (mainMp.load()) mainMp.customStage(primaryStage);

     primaryStage.show();
     mainController.setDividerPosition();

     } catch (IOException e) {
     e.printStackTrace();
     }
     }//}}}
     */

  public static void main(String... args) {//{{{
    launch(args);
  }//}}}

  /*
     private void changeLanguages() {//{{{

     MyProperties preferences = new MyProperties(PropertiesFiles.PREFERENCES.FILE);
     if (preferences.load()) {

     String ja = Locale.JAPAN.getLanguage();
     String langs = preferences.getProperty(PreferencesKeys.LANGS.KEY).orElse(ja);
     if (!langs.equals(ja)) {

     Locale.setDefault(Locale.ENGLISH);

     }

     }

     }//}}}
     */

  private class Delta {//{{{
    double x;
    double y;
  }//}}}

}
