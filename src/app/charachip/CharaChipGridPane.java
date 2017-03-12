package app.charachip;

import app.Main;
import app.image.MyImage;

import java.io.IOException;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.image.*;
import javafx.scene.layout.GridPane;
import javafx.util.Duration;

public class CharaChipGridPane extends GridPane {

  /** アニメーションするコマ割り画像 */
  private final List<MyImage> imageList;

  @FXML private ImageView imageView;

  public CharaChipGridPane(List<MyImage> imgs) {//{{{

    imageList = imgs;

    FXMLLoader loader = new FXMLLoader(getClass().getResource("chara_chip_grid_pane.fxml"));
    loader.setRoot(this);
    loader.setController(this);

    try { loader.load();
      // TODO
      Image img = imageList.get(0).getImage();
      double width = img.getWidth();
      double height = img.getHeight();
      setPrefWidth(width);
      setPrefHeight(height);
      imageView.setFitWidth(width);
      imageView.setFitHeight(height);

      imageView.setImage(img);

    } catch (IOException e) {
      e.printStackTrace();
    }

  }//}}}

  public void setImage(MyImage image) {//{{{

    imageView.setImage(image.getImage());

  }//}}}

  /**
   * 画像を切り替えてアニメーションする。
   */
  public void animate() {//{{{

    final int max = imageList.size();
    AtomicInteger count = new AtomicInteger(0);
    Timeline t = new Timeline(new KeyFrame(Duration.millis(100), e -> {

      int i = count.getAndIncrement();

      if (max <= i) {
        count.set(0);
        i = 0;
      }

      Image img = imageList.get(i).getImage();
      imageView.setImage(img);

    }));

    t.setCycleCount(Timeline.INDEFINITE);
    t.play();

  }//}}}

}
