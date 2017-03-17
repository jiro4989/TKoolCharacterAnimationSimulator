package app.menubar;

import app.standard.Standards;
import util.ResourceBundleWithUtf8;
import static util.Texts.*;

import java.io.*;
import java.util.ResourceBundle;
import java.util.Locale;
import java.net.URL;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.stage.*;

class TrimmingSelector extends Stage {

  TrimmingSelector(File file, Standards std) {//{{{

    URL location = getClass().getResource("trimming_selector.fxml");
    ResourceBundle resources = ResourceBundle.getBundle(
        "app.menubar.dict"
        , Locale.getDefault()
        , ResourceBundleWithUtf8.UTF8_ENCODING_CONTROL
        );
    FXMLLoader loader = new FXMLLoader(location, resources);

    try {

      GridPane root = (GridPane) loader.load();
      TrimmingSelectorController controller = (TrimmingSelectorController) loader.getController();
      Scene scene = new Scene(root);
      scene.getStylesheets().add(getClass().getResource(BASIC_CSS).toExternalForm());
      setScene(scene);

      initStyle(StageStyle.UTILITY);
      initModality(Modality.APPLICATION_MODAL);
      setTitle(resources.getString("selector.title"));
      sizeToScene();

      controller.setImage(file);
      controller.setStandards(std);

    } catch (IOException e) {
      e.printStackTrace();
    }

  }//}}}

}
