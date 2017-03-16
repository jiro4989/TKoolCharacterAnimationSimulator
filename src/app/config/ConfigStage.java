package app.config;

import app.MainController;
import util.ResourceBundleWithUtf8;

import static util.Texts.*;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Locale;
import javafx.fxml.FXMLLoader;
import javafx.scene.*;
import javafx.scene.input.*;
import javafx.scene.layout.*;
import javafx.stage.*;

public class ConfigStage extends Stage {

  private ConfigStageController controller = null;

  public ConfigStage(Node node, MainController aMain) {//{{{

    this((Stage) node.getScene().getWindow(), aMain);

  }//}}}

  public ConfigStage(Stage stage, MainController aMain) {//{{{

    URL location = getClass().getResource("config.fxml");
    ResourceBundle resources = ResourceBundle.getBundle(
        "app.config.dict"
        , Locale.getDefault()
        , ResourceBundleWithUtf8.UTF8_ENCODING_CONTROL
        );
    FXMLLoader loader = new FXMLLoader(location, resources);

    try {

      AnchorPane root = (AnchorPane) loader.load();
      controller = (ConfigStageController) loader.getController();
      controller.setMainController(aMain);
      Scene scene = new Scene(root);
      scene.getStylesheets().add(BASIC_CSS);

      resize(stage);

      setScene(scene);
      setTitle("表示設定");

      initStyle(StageStyle.UTILITY);
      initOwner(stage);

    } catch (IOException e) {
      e.printStackTrace();
    }

  }//}}}

  public void resize(Node node) {//{{{

    Stage stage = (Stage) node.getScene().getWindow();
    resize(stage);

  }//}}}

  public void resize(Stage stage) {//{{{

    double x      = stage.getX();
    double y      = stage.getY();
    double width  = stage.getWidth();
    double height = stage.getHeight();

    setX(x);
    setY(y + height + 20.0);
    setWidth(width);

  }//}}}

  public void applyAnimationSpeed()         { controller.applyAnimationSpeed() ; }
  public void applyZoomRate()               { controller.applyZoomRate()       ; }
  public void changeZoomRate(ScrollEvent e) { controller.changeZoomRate(e)     ; }
  public void durationDown()                { controller.durationDown()        ; }
  public void durationUp()                  { controller.durationUp()          ; }
  public void zoomDown()                    { controller.zoomDown()            ; }
  public void zoomUp()                      { controller.zoomUp()              ; }

  // Setter

  public void setZoomRate(double rate)     { controller.setZoomRate(rate); }
  public void setDuration(double duration) { controller.setDuration(duration); }

}
