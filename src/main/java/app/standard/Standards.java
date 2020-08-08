package app.standard;

import static java.lang.String.format;

/**
 * 規格管理クラス。
 */
public class Standards {

  public final int width;
  public final int height;
  public final int row;
  public final int column;
  public final int frameCount;

  // Builder クラス

  public static class Builder {//{{{

    private final int width;
    private final int height;

    private int row    = 1;
    private int column = 1;
    private int frameCount = 0;

    public Builder(int width, int height) {//{{{

      this.width = width;
      this.height = height;

    }//}}}

    public Builder row(int row)               { this.row = row;               return this;}
    public Builder column(int column)         { this.column = column;         return this;}
    public Builder frameCount(int frameCount) { this.frameCount = frameCount; return this;}

    public Standards build() {//{{{

      if (frameCount == 0)
        frameCount = column;

      // f == String#format
      if (width      <= 0 ) throw new IllegalArgumentException(format("widthに0以下の値を設定できません。 - width: %d."           , width      )) ;
      if (height     <= 0 ) throw new IllegalArgumentException(format("heightに0以下の値を設定できません。 - height: %d."         , height     )) ;
      if (column     <= 0 ) throw new IllegalArgumentException(format("columnに0以下の値を設定できません。 - column: %d."         , column     )) ;
      if (row        <= 0 ) throw new IllegalArgumentException(format("rowに0以下の値を設定できません。 - row: %d."               , row        )) ;
      if (frameCount <= 0 ) throw new IllegalArgumentException(format("frameCountに0以下の値を設定できません。 - frameCount: %d." , frameCount )) ;

      return new Standards(this);

    }//}}}

  }//}}}

  private Standards(Builder builder) {//{{{

    width      = builder.width;
    height     = builder.height;
    row        = builder.row;
    column     = builder.column;
    frameCount = builder.frameCount;

  }//}}}

  @Override
  public String toString() {
    return String.format("Standards: { width: %d, height: %d, row: %d, column: %d, frameCount: %d }"
       , width, height, row, column, frameCount);
  }

}
