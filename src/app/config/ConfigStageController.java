package app.config;

import javafx.fxml.FXML;
import javafx.scene.control.*;

public class ConfigStageController {

  // FXMLコンポーネント//{{{

  @FXML private Label opacityLabel;
  @FXML private Label zoomRateLabel;
  @FXML private Label animationSpeedLabel;

  @FXML private Slider opacitySlider;
  @FXML private Slider zoomRateSlider;
  @FXML private Slider animationSpeedSlider;

//}}}

  @FXML private void initialize() {
  }

}
