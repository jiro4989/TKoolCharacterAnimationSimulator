package app.config;

import app.Main;

import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.stage.*;

public class ConfigStage extends Stage {

  public ConfigStage(Node node) {//{{{

    this((Stage) node.getScene().getWindow());

  }//}}}

  public ConfigStage(Stage stage) {//{{{

    FXMLLoader loader = new FXMLLoader(getClass().getResource("config.fxml"));
    try {

      AnchorPane root = (AnchorPane) loader.load();
      Scene scene = new Scene(root);
      scene.getStylesheets().add(Main.BASIC_CSS);

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
    setY(y + height);
    setWidth(width);

  }//}}}

}
