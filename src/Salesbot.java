package Salesbot;

// The basic template for a salesbot
// > Has a neighborhood
// > Has a position
// > Has a name (implemented via toString)
// > MUST determine his own path (returned as House[])
public abstract class Salesbot {
    // Protected Data Members
    protected Neighborhood mHood;   // my neighborhood
    protected Vector2 mPos;         // my position
    
    // Constructor
    public Salesbot(Neighborhood neighborhood) {
        mHood = neighborhood;
        mPos = new Vector2(0, 0);
    }

    // Public Abstract method to implement
    public abstract House[] findPath();

    // Mutators
    public void setPosition(Vector2 newPos) {
        mPos = newPos;
    }

    // Accessors
    public Neighborhood getHood() {
        return mHood;
    }

    public Vector2 getPosition() {
        return mPos;
    }

    @Override
    public String toString() {
        return "Salesbot";
    }
}
