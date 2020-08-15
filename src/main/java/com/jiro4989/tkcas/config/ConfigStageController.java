package com.jiro4989.tkcas.config;

import com.jiro4989.tkcas.MainController;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.*;

public class ConfigStageController {

  private MainController mainController;

  @FXML private Label zoomRateLabel;
  @FXML private Label animationSpeedLabel;

  @FXML private Slider zoomRateSlider;
  @FXML private Slider animationSpeedSlider;

  @FXML
  private void initialize() {

    zoomRateSlider.setOnScroll(e -> changeValue(e, zoomRateSlider));
    animationSpeedSlider.setOnScroll(e -> changeValue(e, animationSpeedSlider));

    zoomRateSlider
        .valueProperty()
        .addListener(
            (obs, o, n) -> {
              mainController.updateZoomRate(n.doubleValue());
            });
    animationSpeedSlider
        .valueProperty()
        .addListener(
            (obs, o, n) -> {
              mainController.updateAnimationSpeed(n.intValue());
            });
  }

  private void changeValue(ScrollEvent e, Slider s) {

    double value = s.getValue();
    double add = s.getBlockIncrement();
    double min = s.getMin();
    double max = s.getMax();

    value = 0 < e.getDeltaY() ? value + add : value - add;
    value = Math.min(max, value);
    value = Math.max(min, value);
    s.setValue(value);
  }

  public void changeZoomRate(ScrollEvent e) {

    changeValue(e, zoomRateSlider);
  }

  public void zoomDown() {

    Slider s = zoomRateSlider;
    double value = s.getValue();
    double add = s.getBlockIncrement();

    value = value - add;
    changeZoomValue(value, s);
  }

  public void zoomUp() {

    Slider s = zoomRateSlider;
    double value = s.getValue();
    double add = s.getBlockIncrement();

    value = value + add;
    changeZoomValue(value, s);
  }

  public void durationDown() {

    Slider s = animationSpeedSlider;
    double value = s.getValue();
    double add = s.getBlockIncrement();

    value = value - add;
    changeZoomValue(value, s);
  }

  public void durationUp() {

    Slider s = animationSpeedSlider;
    double value = s.getValue();
    double add = s.getBlockIncrement();

    value = value + add;
    changeZoomValue(value, s);
  }

  public void applyZoomRate() {

    Slider s = zoomRateSlider;
    double value = s.getValue();
    mainController.updateZoomRate(value);
  }

  public void applyAnimationSpeed() {

    Slider s = animationSpeedSlider;
    double value = s.getValue();
    mainController.updateAnimationSpeed((int) value);
  }

  private void changeZoomValue(double value, Slider s) {

    double min = s.getMin();
    double max = s.getMax();

    value = Math.min(max, value);
    value = Math.max(min, value);
    s.setValue(value);
  }

  // Getter

  public double getZoomRate() {
    return zoomRateSlider.getValue();
  }

  public double getDuration() {
    return animationSpeedSlider.getValue();
  }

  // Setter

  public void setMainController(MainController aMain) {
    mainController = aMain;
  }

  public void setZoomRate(double value) {
    zoomRateSlider.setValue(value);
  }

  public void setDuration(double value) {
    animationSpeedSlider.setValue(value);
  }
}
