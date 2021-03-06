package Salesbot;

// A "house" for the salesbot to travel to, a semantic encapsulation of a position
public class House {
    // Private Data Members
    private Vector2 pos;

    // Constructors
    public House(int x, int y) {
        this.pos = new Vector2(x, y);
    }

    public House(Vector2 pos) {
        this.pos = pos;
    }

    public House(House h) {
        this.pos = h.getPosition();
    }

    // Public Member Methods
    public Vector2 getPosition() {
        return new Vector2(pos);
    }

    public void relocate(Vector2 newPos) {
        this.pos = newPos;
    }
}
