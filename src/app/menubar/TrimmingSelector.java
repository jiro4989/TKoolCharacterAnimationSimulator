package app.menubar;

import app.Main;

import java.io.*;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.stage.*;

class TrimmingSelector extends Stage {

  TrimmingSelector(File file) {//{{{

    FXMLLoader loader = new FXMLLoader(getClass().getResource("trimming_selector.fxml"));

    try {

      GridPane root = (GridPane) loader.load();
      TrimmingSelectorController controller = (TrimmingSelectorController) loader.getController();
      Scene scene = new Scene(root);
      scene.getStylesheets().add(getClass().getResource(Main.BASIC_CSS).toExternalForm());
      setScene(scene);

      initStyle(StageStyle.UTILITY);
      initModality(Modality.APPLICATION_MODAL);
      sizeToScene();

      controller.setImage(file);

    } catch (IOException e) {
      e.printStackTrace();
    }

  }//}}}

}
