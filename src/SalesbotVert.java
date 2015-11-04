package Salesbot;

import java.util.ArrayList;
import java.util.Arrays;

// A vertical implementation of the Salesbot
public class SalesbotVert extends Salesbot {

    // Constructor
    public SalesbotVert(Neighborhood n) {
        super(n);
    }

    // Algorithm
    @Override
    public House[] findPath() {
        int size = mHood.getNumHouses();
        House[] path = new House[size];
        ArrayList<House> houseList = new ArrayList<House>(Arrays.asList(mHood.getHouses()));

        for (int i = 0; i < size; i ++) {
            int bPosY = houseList.get(0).getPosition().y;
            int bNdx = 0;

            for (int ndx = 0; ndx < houseList.size(); ndx ++) {
                Vector2 pos = houseList.get(ndx).getPosition();
                if (pos.y > bPosY) {
                    bPosY = pos.y;
                    bNdx = ndx;
                }
            }

            path[i] = houseList.get(bNdx);
            houseList.remove(bNdx);
        }

        return path;
    }

    @Override
    public String toString() {
        return "Vertical Salesbot";
    }
}
