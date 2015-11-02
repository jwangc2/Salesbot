package Salesbot;

public class House {
    private Vector2 pos;

    public House(int x, int y) {
        this.pos = new Vector2(x, y);
    }

    public House(Vector2 pos) {
        this.pos = pos;
    }

    public Vector2 getPosition() {
        return new Vector2(pos);
    }

    public void relocate(Vector2 newPos) {
        this.pos = newPos;
    }
}
