package Salesbot;

public class Vector2 {
    public int x;
    public int y;

    public static double distanceSq(Vector2 a, Vector2 b) {
        return ( Math.pow(a.y - b.y, 2) + Math.pow(a.x - b.x, 2) );
    }

    public Vector2(int x, int y) {
        this.x = x;
        this.y = y;    
    }

    public Vector2(Vector2 orig) {
        this(orig.x, orig.y);
    }

    public Vector2 sub(Vector2 other) {
        return new Vector2(this.x - other.x, this.y - other.y);
    }

    public Vector2 add(Vector2 other) {
        return new Vector2(this.x + other.x, this.y + other.y);
    }

    public Vector2 scale(int c) {
        return new Vector2(this.x * c, this.y * c);
    }
}
