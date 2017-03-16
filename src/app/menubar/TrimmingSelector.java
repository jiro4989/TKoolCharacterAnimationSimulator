package app.menubar;

import app.standard.Standards;
import static util.Texts.*;

import java.io.*;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.stage.*;

class TrimmingSelector extends Stage {

  TrimmingSelector(File file, Standards std) {//{{{

    FXMLLoader loader = new FXMLLoader(getClass().getResource("trimming_selector.fxml"));

    try {

      GridPane root = (GridPane) loader.load();
      TrimmingSelectorController controller = (TrimmingSelectorController) loader.getController();
      Scene scene = new Scene(root);
      scene.getStylesheets().add(getClass().getResource(BASIC_CSS).toExternalForm());
      setScene(scene);

      initStyle(StageStyle.UTILITY);
      initModality(Modality.APPLICATION_MODAL);
      sizeToScene();

      controller.setImage(file);
      controller.setStandards(std);

    } catch (IOException e) {
      e.printStackTrace();
    }

  }//}}}

}
