package app.image;

import app.standard.Standards;
import app.charachip.CharaChipGridPane;

import java.nio.IntBuffer;
import java.util.*;
import static java.util.stream.IntStream.range;
import javafx.scene.image.*;

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

    // ************************************************************
    // コンストラクタ
    // ************************************************************

    public Builder(MyImage mi)  { this(mi.getImage()); }
    public Builder(String path) { this(new Image(path)); }

    /**
     * ラッピングする新しい画像。またはトリミング対象とする画像。
     *
     * @param img 画像
     */
    public Builder(Image img) {//{{{

      image  = img;
      width  = (int) image.getWidth();
      height = (int) image.getHeight();

    }//}}}

    // ************************************************************
    // Setter
    // ************************************************************


    /**
     * 元になった画像をトリミングする座標を指定する。
     *
     * @param x 座標
     * @return Builderインスタンス
     */
    public Builder x(int x) {//{{{

      if (x < 0)
        throw new IllegalArgumentException("座標xに0未満の値をセットできません。");

      this.x = x;
      return this;

    }//}}}

    /**
     * 元になった画像をトリミングする座標を指定する。
     *
     * @param y 座標
     * @return Builderインスタンス
     */
    public Builder y(int y) {//{{{

      if (y < 0)
        throw new IllegalArgumentException("座標yに0未満の値をセットできません。");


      this.y = y;
      return this;

    }//}}}

    /**
     * 元になった画像をトリミングする幅を指定する。
     *
     * @param width 横幅
     * @return Builderインスタンス
     */
    public Builder width(int width) {//{{{

      if (this.width < width)
        throw new IllegalArgumentException("オプションのwidthに読み込む画像のwidthより大きな値をセットできません。");

      this.width = width;
      return this;

    }//}}}

    /**
     * 元になった画像をトリミングする高さを指定する。
     *
     * @param height 高さ
     * @return Builderインスタンス
     */
    public Builder height(int height) {//{{{

      if (this.height < height)
        throw new IllegalArgumentException("オプションのheightに読み込む画像のheightより大きな値をセットできません。");

      this.height = height;
      return this;

    }//}}}

    public MyImage build() {//{{{

      int w = (int) image.getWidth();
      int h = (int) image.getHeight();

      if (w < x)
        throw new IllegalArgumentException("座標xは画像幅widthの値以下でなければいけません。");

      if (h < y)
        throw new IllegalArgumentException("座標yは画像幅heightの値以下でなければいけません。");

      return new MyImage(this);

    }//}}}

    @Override
    public String toString() {
      return String.format("MyImage.Builder: { x: %04d, y: %04d, width: %04d, height:%04d }", x, y, width, height);
    }

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

      WritableImage wImage = new WritableImage(bldWidth, bldWidth);
      PixelWriter writer = wImage.getPixelWriter();
      writer.setPixels(0, 0, bldWidth, bldHeight, FORMAT, pixels, 0, bldWidth);

      img = wImage;

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

  /**
   * 歩行グラフィックのキャラチップを作成する。
   */
  public List<CharaChipGridPane> createWarkChips(Standards standards) {//{{{

    int row        = standards.row;
    int column     = standards.column;
    int width      = standards.width;
    int height     = standards.height;
    int frameCount = standards.frameCount;

    List<CharaChipGridPane> ccgpList = new ArrayList<>(row);

    range(0, row).forEach(r -> {

      int x = 0;
      int y = r * height;
      CharaChipGridPane ccgp = new CharaChipGridPane.Builder(this, width, height)
        .x(x).y(y).frameCount(frameCount)
        .build();
      ccgpList.add(ccgp);

    });

    return ccgpList;

  }//}}}

  /*
     public List<CharaChipGridPane> createSideViewChips(Standards standards) {//{{{

     int row    = standards.animation.row;
     int column = standards.animation.column;
     int line   = 3;

     List<CharaChipGridPane> ccgpList = new ArrayList<>();
     range(0, line).forEach(l -> {
     ccgpList.addAll(createLineList(row, column, line));
     });

     return ccgpList;

     }//}}}
     */

  /*
  private List<CharaChipGridPane> createLineList(int row, int column, int line) {//{{{

    List<CharaChipGridPane> lineList = new ArrayList<>();
    range(0, row).forEach(r -> {
      //lineList.addAll();
    });

  }//}}}
  */

  private int[] getPixels() {//{{{

    PixelReader reader = image.getPixelReader();
    int[] pixels       = new int[width * height];
    reader.getPixels(0, 0, width, height, FORMAT, pixels, 0, width);

    return pixels;

  }//}}}

  // Getter
  public Image getImage() { return image; }
  public int getWidth()  { return (int) image.getWidth();}
  public int getHeight() { return (int) image.getHeight();}

}
