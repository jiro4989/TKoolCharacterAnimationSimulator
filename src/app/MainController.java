package app;

import app.menubar.MyMenuBar;
import app.layout.PositionsFlowPane;

import java.io.*;
import javafx.fxml.FXML;

public class MainController {

  @FXML private MyMenuBar myMenuBar;
  @FXML private PositionsFlowPane positionsFlowPane;

  @FXML private void initialize() {

    myMenuBar.setMainController(this);

  }

  public void drawImage(File file) {
    System.out.println(file.toString());
  }

}
