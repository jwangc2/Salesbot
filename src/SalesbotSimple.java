package Salesbot;

public class SalesbotSimple extends Salesbot {
    public SalesbotSimple(Neighborhood neighborhood) {
        super(neighborhood);
    }

    @Override
    public House[] findPath() {
        return mHood.getHouses();
    }
}