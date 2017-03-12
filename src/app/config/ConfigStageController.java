package app.config;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.*;

public class ConfigStageController {

  // FXMLコンポーネント//{{{

  @FXML private Label opacityLabel;
  @FXML private Label zoomRateLabel;
  @FXML private Label animationSpeedLabel;

  @FXML private Slider opacitySlider;
  @FXML private Slider zoomRateSlider;
  @FXML private Slider animationSpeedSlider;

  //}}}

  @FXML private void initialize() {//{{{

    opacitySlider.setOnScroll(e -> changeValue(e, opacitySlider));
    zoomRateSlider.setOnScroll(e -> changeValue(e, zoomRateSlider));
    animationSpeedSlider.setOnScroll(e -> changeValue(e, animationSpeedSlider));

  }//}}}

  @FXML private void changeValue(ScrollEvent e, Slider s) {//{{{

    double value = s.getValue();
    double add = s.getBlockIncrement();

    value = e.getDeltaY() < 0 ? value + add : value - add;
    s.setValue(value);

    System.out.println("value: " + value);
    System.out.println("block: " + add);
    System.out.println("delta: " + e.getDeltaY());

  }//}}}

}
