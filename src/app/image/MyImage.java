package app.image;
import javafx.scene.image.*;
import java.nio.IntBuffer;

/**
 * 拡張Imageクラス。このクラスは不変クラスである。
 * 自身のインスタンスを返すメソッドの戻り値はすべて新しく生成されたインスタンス
 * であり、メソッド呼び出しの副作用で元のインスタンスのピクセルデータが変更され
 * ることはない。
 */
public class MyImage {

  private final Image image;
  private final int width;
  private final int height;

  // 画像処理の書式(ARGB)
  private static final WritablePixelFormat<IntBuffer> FORMAT = WritablePixelFormat.getIntArgbInstance();

  public MyImage(String filePath) {//{{{

    this(new Image(filePath));

  }//}}}

  public MyImage(Image img) {//{{{

    image = img;
    width  = (int) image.getWidth();
    height = (int) image.getHeight();

  }//}}}

  /**
   * 画像を左右反転させた新しいインスタンスを返す。
   */
  public MyImage newFlipHorizontalImage() {//{{{

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

    return new MyImage(wImage);

  }//}}}

  private int[] getPixels() {//{{{

    PixelReader reader = image.getPixelReader();
    int[] pixels       = new int[width * height];
    reader.getPixels(0, 0, width, height, FORMAT, pixels, 0, width);
    
    return pixels;

  }//}}}

}
