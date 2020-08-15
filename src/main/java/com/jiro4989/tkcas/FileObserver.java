package com.jiro4989.tkcas;

import java.io.*;
import java.util.*;
import javafx.animation.*;
import javafx.util.*;

class FileObserver {

  private Timeline timeline;
  private ModifiedTime mt;

  FileObserver(int duration, File file, MainController controller) {

    mt = new ModifiedTime(file);

    timeline =
        new Timeline(
            new KeyFrame(
                Duration.millis(duration),
                e -> {
                  long time = file.lastModified();
                  if (mt.isChanged(time)) {
                    controller.updateImages(file);
                  }
                }));

    timeline.setCycleCount(Timeline.INDEFINITE);
    timeline.play();
  }

  void stop() {
    timeline.stop();
  }

  class ModifiedTime {

    private long time;

    ModifiedTime(File file) {
      this.time = file.lastModified();
    }

    boolean isChanged(long t) {
      boolean changed = time < t;
      if (changed) time = t;
      return changed;
    }
  }
}
