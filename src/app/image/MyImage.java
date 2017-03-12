package app.image;

import javafx.scene.image.*;
import java.nio.IntBuffer;

/**
 * ラッパーImageクラス。このクラスは不変クラスである。
 * 自身のインスタンスを返すメソッドの戻り値はすべて新しく生成されたインスタンスであり、メソッド呼び出しの副作用で元のインスタンスのピクセルデータが変更されることはない。
 */
public class MyImage {

  private final Image image;
  private final int width;
  private final int height;

  // 画像処理の書式(ARGB)
  private static final WritablePixelFormat<IntBuffer> FORMAT = WritablePixelFormat.getIntArgbInstance();

  public static class Builder {//{{{

    private final Image image;
    private int x = 0;
    private int y = 0;
    private int width = 0;
    private int height = 0;

    public Builder(String path) {//{{{

      this(new Image(path));

    }//}}}

    public Builder(Image img) {//{{{

      image = img;
      width = (int) image.getWidth();
      height = (int) image.getHeight();

    }//}}}

    public Builder x(int x) {//{{{

      if (x < 0)
        throw new IllegalArgumentException("座標xに0未満の値をセットできません。");

      this.x = x;
      return this;

    }//}}}

    public Builder y(int y) {//{{{

      if (y < 0)
        throw new IllegalArgumentException("座標yに0未満の値をセットできません。");


      this.y = y;
      return this;

    }//}}}

    public Builder width(int width) {//{{{

      if (this.width < width)
        throw new IllegalArgumentException("オプションのwidthに読み込む画像のwidthより大きな値をセットできません。");

      this.width = width;
      return this;

    }//}}}

    public Builder height(int height) {//{{{

      if (this.height < height)
        throw new IllegalArgumentException("オプションのheightに読み込む画像のheightより大きな値をセットできません。");

      this.height = height;
      return this;

    }//}}}

    public MyImage build() {//{{{

      if (width < x)
        throw new IllegalArgumentException("座標xは画像幅widthの値以下でなければいけません。");

      if (height < y)
        throw new IllegalArgumentException("座標yは画像幅heightの値以下でなければいけません。");

      return new MyImage(this);

    }//}}}

  }//}}}

  // private コンストラクタ

  private MyImage(Builder builder) {//{{{

    Image img     = builder.image;
    int imgWidth  = (int) img.getWidth();
    int imgHeight = (int) img.getHeight();
    int bldWidth  = builder.width;
    int bldHeight = builder.height;

    // 読み込んだ画像と、ビルダにセットした値が異なる場合、
    // トリミングした新たな画像を生成してセットする。
    if (
           imgWidth  != bldWidth
        || imgHeight != bldHeight
       )
    {

      int x = builder.x;
      int y = builder.y;

      PixelReader reader = img.getPixelReader();
      int[] pixels       = new int[bldWidth * bldHeight];
      reader.getPixels(x, y, bldWidth, bldHeight, FORMAT, pixels, 0, bldWidth);

      img = new WritableImage(reader, bldWidth, bldHeight);

    }

    image = img;
    this.width  = (int) image.getWidth();
    this.height = (int) image.getHeight();

  }//}}}

  // メソッド

  /**
   * 画像を左右反転させた新しいインスタンスを返す。
   */
  public MyImage newFlipHorizontalInstance() {//{{{

    int[] pixels = getPixels();

    // 左右反転したピクセルを生成する
    int[] newPixels = new int[pixels.length];
    for (int i=0; i<pixels.length; i++) {

      int a = (i + width) / width * width;
      int b = i / width * width;
      int reverseIndex = a + b - i - 1;
      newPixels[reverseIndex] = pixels[i];

    }

    WritableImage wImage = new WritableImage(width, height);
    PixelWriter writer = wImage.getPixelWriter();
    writer.setPixels(0, 0, width, height, FORMAT, newPixels, 0, width);

    return new MyImage.Builder(wImage).build();

  }//}}}

  private int[] getPixels() {//{{{

    PixelReader reader = image.getPixelReader();
    int[] pixels       = new int[width * height];
    reader.getPixels(0, 0, width, height, FORMAT, pixels, 0, width);

    return pixels;

  }//}}}

}
