package app.standard;

/**
 * 規格管理クラス。
 */
public class Standards {

  public final Point point;
  public final Size size;
  public final AnimationStandard animation;

  // Builder クラス

  public static class Builder {//{{{

    private final int width;
    private final int height;

    private int x      = 0;
    private int y      = 0;
    private int row    = 1;
    private int column = 1;

    public Builder(int width, int height) {//{{{

      this.width = width;
      this.height = height;

    }//}}}

    public Builder x(int x)           { this.x = x;           return this;}
    public Builder y(int y)           { this.y = y;           return this;}
    public Builder row(int row)       { this.row = row;       return this;}
    public Builder column(int column) { this.column = column; return this;}

    public Standards build() {//{{{

      return new Standards(this);

    }//}}}

  }//}}}

  private Standards(Builder builder) {//{{{

    point     = new Point(             builder .x,     builder .y);
    size      = new Size(              builder .width, builder .height);
    animation = new AnimationStandard( builder .row,   builder .column);

  }//}}}

  @Override
  public String toString() {
    return point + " " + size + " " + animation;
  }

}
