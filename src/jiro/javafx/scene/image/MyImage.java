package jiro.javafx.scene.image;

import java.io.*;
import javafx.scene.image.*;

/**
 * イメージ画像を編集しやすくするための便利メソッドを持つ拡張クラス。
 * 不変クラスなので、戻り値はすべて新しく生成されたインスタンスを返却する。
 */
public class MyImage extends Image {

  public MyImage(String url) { super(url); }

}
