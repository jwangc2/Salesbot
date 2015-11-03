package Salesbot;

public abstract class Salesbot {
    protected Neighborhood mHood;
    protected Vector2 mPos;
    
    public Salesbot(Neighborhood neighborhood) {
        mHood = neighborhood;
        mPos = new Vector2(0, 0);
    }

    public abstract House[] findPath();

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
