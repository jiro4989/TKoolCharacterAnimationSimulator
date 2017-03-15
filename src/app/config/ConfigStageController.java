package app.config;

import app.MainController;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.*;

public class ConfigStageController {

  private MainController mainController;

  // FXMLコンポーネント//{{{

  @FXML private Label opacityLabel;
  @FXML private Label zoomRateLabel;
  @FXML private Label animationSpeedLabel;

  @FXML private Slider opacitySlider;
  @FXML private Slider zoomRateSlider;
  @FXML private Slider animationSpeedSlider;

  //}}}

  @FXML private void initialize() {//{{{

    zoomRateSlider       . setOnScroll(e -> changeValue(e, zoomRateSlider));
    animationSpeedSlider . setOnScroll(e -> changeValue(e, animationSpeedSlider));

    zoomRateSlider.valueProperty().addListener((obs, o, n) -> {
      mainController.updateZoomRate(n.doubleValue());
    });
    animationSpeedSlider.valueProperty().addListener((obs, o, n) -> {
      mainController.updateAnimationSpeed(n.intValue());
    });

  }//}}}

  private void changeValue(ScrollEvent e, Slider s) {//{{{

    double value = s.getValue();
    double add = s.getBlockIncrement();
    double min = s.getMin();
    double max = s.getMax();

    value = 0 < e.getDeltaY() ? value + add : value - add;
    value = Math.min(max, value);
    value = Math.max(min, value);
    s.setValue(value);

  }//}}}

  public void changeZoomRate(ScrollEvent e) {//{{{

    changeValue(e, zoomRateSlider);

  }//}}}

  public void zoomDown() {//{{{

    Slider s     = zoomRateSlider;
    double value = s.getValue();
    double add   = s.getBlockIncrement();

    value = value - add;
    changeZoomValue(value, zoomRateSlider);


  }//}}}

  public void zoomUp() {//{{{

    Slider s     = zoomRateSlider;
    double value = s.getValue();
    double add   = s.getBlockIncrement();

    value = value + add;
    changeZoomValue(value, zoomRateSlider);

  }//}}}

  private void changeZoomValue(double value, Slider s) {//{{{

    double min = s.getMin();
    double max = s.getMax();

    value = Math.min(max, value);
    value = Math.max(min, value);
    s.setValue(value);

  }//}}}

  // Setter

  public void setMainController(MainController aMain) {//{{{
    mainController = aMain;

  }//}}}

}
