package app;

import jiro.java.util.MyProperties;

import app.menubar.MyMenuBar;

import java.io.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class Main extends Application {

  private static MyMenuBar myMenuBar;

  public static final String TITLE         = "MV Character Chip Simulator";
  public static final String VERSION       = "ver 1.0.0";
  public static final String TITLE_VERSION = TITLE + " - " + VERSION;

  private static final String BASIC_CSS = "/app/res/css/basic.css";
  private static final String APP_ICON  = "/app/res/img/app_icon.png";

  @Override
  public void start(Stage primaryStage) {

    myMenuBar = new MyMenuBar(this);

    VBox root = new VBox();
    root.getChildren().add(myMenuBar);

    Scene scene = new Scene(root, 200, 300);
    scene.getStylesheets().add(BASIC_CSS);
    primaryStage.setScene(scene);
    primaryStage.setTitle(TITLE_VERSION);
    primaryStage.getIcons().add(new Image(APP_ICON));

    primaryStage.show();

  }

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

  public void drawImage(File file) {

    System.out.println("drawImage.");

  }

}
