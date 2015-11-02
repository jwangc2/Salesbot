package Salesbot;

public abstract class Salesbot {
    private Neighborhood mHood;
    private Vector2 mPos;
    
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
}
