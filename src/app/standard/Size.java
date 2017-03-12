package app.standard;

/**
 * 1タイルあたりの幅クラス。
 */
class Size {

  final int width;
  final int height;

  Size(int width, int height) {//{{{

    this.width  = width;
    this.height = height;

  }//}}}

  @Override
  public String toString() {
    return String.format("Size: { width: %d, height: %d }", width, height);
  }

}
