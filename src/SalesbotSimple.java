package Salesbot;

// A simple implementation of the salesbot
public class SalesbotSimple extends Salesbot {

    // Constructor
    public SalesbotSimple(Neighborhood n) {
        super(n);
    }

    // Simply traverse in the same order given (no processing)
    @Override
    public House[] findPath() {
        return mHood.getHouses();
    }

    @Override
    public String toString() {
        return "Simple Salesbot";
    }
}
