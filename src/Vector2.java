package Salesbot;

// A simple vector class
public class Vector2 {
    // Public members (exposed for ease)
    public int x;
    public int y;

    // Public Static Methods
    public static double distanceSq(Vector2 a, Vector2 b) {
        return ( Math.pow(a.y - b.y, 2) + Math.pow(a.x - b.x, 2) );
    }

    // Constructors
    public Vector2(int x, int y) {
        this.x = x;
        this.y = y;    
    }

    public Vector2(Vector2 orig) {
        this(orig.x, orig.y);
    }

    // Public Member Methods
    public Vector2 sub(Vector2 other) {
        return new Vector2(this.x - other.x, this.y - other.y);
    }

    public Vector2 add(Vector2 other) {
        return new Vector2(this.x + other.x, this.y + other.y);
    }

    public Vector2 scale(int c) {
        return new Vector2(this.x * c, this.y * c);
    }

    public int dot(Vector2 other) {
        return ( (this.x * other.x) + (this.y * other.y) );
    }

    public double magnitude() {
        return Math.sqrt(Math.pow(y, 2) + Math.pow(x, 2));
    }

    public double angle() {
        return Math.atan2(y, x) * (180.0 / Math.PI);
    }

    public double angleBetween(Vector2 other) {
        double numer = (double) this.dot(other);
        double denom = this.magnitude() * other.magnitude();
        double rad = Math.acos(numer / denom);
        return ( rad * (180.0 / Math.PI) );
    }

    @Override
    public String toString() {
        return "(" + this.x + ", " + this.y + ")";
    }
}
