package app.charachip;

import app.Main;
import app.image.MyImage;
import app.layout.PositionsFlowPane;

import java.io.*;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.image.*;
import javafx.scene.input.*;
import javafx.scene.layout.GridPane;
import javafx.util.Duration;
import static java.util.stream.IntStream.range;

public class CharaChipGridPane extends GridPane {

  // 画像切り替えタイマー
  private Timeline animationTimeline;

  // アニメーションするコマ画像
  private final List<MyImage> imageList;

  private final int imageWidth;
  private final int imageHeight;

  // このインスタンスを設置している親パネル
  private final PositionsFlowPane parent;

  // インスタンス生成時に渡された情報を保持するビルダ
  private final Builder builder;

  @FXML private ImageView imageView;

  // Builderクラス

  public static class Builder {//{{{

    private final MyImage image;
    private final int width;
    private final int height;
    private final PositionsFlowPane parent;

    private int x          = 0;
    private int y          = 0;
    private int frameCount = 1;

    public Builder(MyImage image, int width, int height, PositionsFlowPane parent) {//{{{
      this.image   = image;
      this.width   = width;
      this.height  = height;
      this.parent  = parent;
    }//}}}

    public Builder x(int x)                   { this.x = x;                   return this; }
    public Builder y(int y)                   { this.y = y;                   return this; }
    public Builder frameCount(int frameCount) { this.frameCount = frameCount; return this; }

    public CharaChipGridPane build() {//{{{

      if (x          < 0) new IllegalArgumentException("xに負の数を指定することはできません。");
      if (y          < 0) new IllegalArgumentException("yに負の数を指定することはできません。");
      if (frameCount < 0) new IllegalArgumentException("frameCountに負の数を指定することはできません。");
      checkTrimmingWidth();
      checkTrimmingHeight();

      return new CharaChipGridPane(this);

    }//}}}

    private void checkTrimmingWidth() {//{{{

      int imgWidth   = image.getWidth();
      int trimmWidth = x + frameCount * width;
      if (imgWidth < trimmWidth)
        new IllegalArgumentException(
            String.format("トリミングする横幅がソース画像の領域を超えています"
              + " - x: %d, imgWidth: %d, frameCount: %d, trimmWidth: %d"
              , x , imgWidth , frameCount , trimmWidth)
            );

    }//}}}

    private void checkTrimmingHeight() {//{{{

      int imgHeight   = image.getHeight();
      int trimmHeight = y + height;
      if (imgHeight < trimmHeight)
        new IllegalArgumentException(
            String.format("トリミングする縦幅がソース画像の領域を超えています"
              + " - y: %d, imgHeight: %d, trimmHeight: %d"
              , y , imgHeight , trimmHeight)
            );

    }//}}}

  }//}}}

  // private コンストラクタ

  private CharaChipGridPane(Builder aBuilder) {//{{{

    builder              = aBuilder;
    final MyImage src    = aBuilder.image;
    this.imageWidth      = aBuilder.width;
    this.imageHeight     = aBuilder.height;
    final int x          = aBuilder.x;
    final int y          = aBuilder.y;
    final int frameCount = aBuilder.frameCount;
    this.parent          = aBuilder.parent;

    imageList = createFrameImages(src, x, y, imageWidth, imageHeight, frameCount);

    FXMLLoader loader = new FXMLLoader(getClass().getResource("chara_chip_grid_pane.fxml"));
    loader.setRoot(this);
    loader.setController(this);

    setOnMouseClicked(e -> switchPanesVisibles(e));

    try { loader.load();

      setSize(imageWidth, imageHeight);
      Image img = imageList.get(0).getImage();
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

      int i = count.incrementAndGet();

      if (max <= i) {
        count.set(0);
        i = 0;
      }

      Image img = imageList.get(i).getImage();
      imageView.setImage(img);

    }));

    return timeline;

  }//}}}

  /**
   * アニメーションのフレーム画像を生成する。
   * 
   * @param src トリミング元になる画像
   * @param x トリミングする座標計算の基準になる座標
   * @param y トリミングする座標
   * @param width トリミングするタイルの幅
   * @param height トリミングするタイルの幅
   * @param frameCount アニメーションのフレーム数
   */
  private List<MyImage> createFrameImages(MyImage src, int x, int y, int width, int height, int frameCount) {//{{{

    List<MyImage> frameImages = new ArrayList<>();
    range(0, frameCount).forEach(column -> {

      int trimmX = x + column * width;

      MyImage trimmedImage = new MyImage.Builder(src)
        .x(trimmX) .y(y)
        .width(width) .height(height)
        .build();

      frameImages.add(trimmedImage);

    });

    // アニメーション開始画像とのつなぎを自然にするための追加
    MyImage last = frameImages.get(1);
    frameImages.add(last);

    return frameImages;

  }//}}}

  private void switchPanesVisibles(MouseEvent e) {//{{{

    if (e.getClickCount() == 2) {
      parent.switchViewerMode(this);
    }

  }//}}}

}
