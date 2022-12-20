package cegepst.engine.entities;

public class Dimension {

    private int width;
    private int height;

    public Dimension(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public Dimension(int side) {
        this.width = side;
        this.height = side;
    }

    public void setDimension(int width, int height) {
        setHeight(height);
        setWidth(width);
    }

    public void setDimension(Dimension dimension) {
        this.height = dimension.getHeight();
        this.width = dimension.getWidth();
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
