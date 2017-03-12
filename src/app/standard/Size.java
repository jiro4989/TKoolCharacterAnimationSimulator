package app.standard;

/**
 * 1タイルあたりの幅クラス。
 */
public class Size {

  public final int width;
  public final int height;

  Size(int width, int height) {//{{{

    this.width  = width;
    this.height = height;

  }//}}}

  @Override
  public String toString() {
    return String.format("Size: { width: %d, height: %d }", width, height);
  }

}
