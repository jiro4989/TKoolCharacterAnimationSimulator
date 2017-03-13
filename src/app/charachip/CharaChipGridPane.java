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

  /** 画像切り替えタイマー */
  private Timeline animationTimeline;

  /** アニメーションするコマ割り画像 */
  private final List<MyImage> imageList;

  private final double imageWidth;
  private final double imageHeight;

  @FXML private ImageView imageView;

  public CharaChipGridPane(List<MyImage> imgs) {//{{{

    imageList = imgs;

    FXMLLoader loader = new FXMLLoader(getClass().getResource("chara_chip_grid_pane.fxml"));
    loader.setRoot(this);
    loader.setController(this);

    Image img   = imageList.get(0).getImage();
    imageWidth  = img.getWidth();
    imageHeight = img.getHeight();

    try { loader.load();

      setSize(imageWidth, imageHeight);
      imageView.setImage(img);

    } catch (IOException e) {
      e.printStackTrace();
    }

  }//}}}

  private void setSize(double width, double height) {//{{{

    setPrefWidth(width);
    setPrefHeight(height);
    imageView.setFitWidth(width);
    imageView.setFitHeight(height);

  }//}}}

  /**
   * 画像を切り替えてアニメーションする。
   */
  public void animate(int duration) {//{{{

    if (animationTimeline != null)
      animationTimeline.stop();
    animationTimeline = createTimeline(duration);
    animationTimeline.setCycleCount(Timeline.INDEFINITE);
    animationTimeline.play();

  }//}}}

  public void setScale(double rate) {//{{{

    rate = rate / 100;
    double scaledWidth = imageWidth * rate;
    double scaledHeight = imageHeight * rate;

    setSize(scaledWidth, scaledHeight);

  }//}}}

  private Timeline createTimeline(int duration) {//{{{

    final int max = imageList.size();
    AtomicInteger count = new AtomicInteger(0);
    Timeline timeline = new Timeline(new KeyFrame(Duration.millis(duration), e -> {

      int i = count.getAndIncrement();

      if (max <= i) {
        count.set(0);
        i = 0;
      }

      Image img = imageList.get(i).getImage();
      imageView.setImage(img);

    }));

    return timeline;

  }//}}}

}
