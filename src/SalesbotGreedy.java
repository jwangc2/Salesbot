package Salesbot;

import java.util.ArrayList;
import java.util.Arrays;

// TODO: Simplify algorithm (use basic sorting)

// A greedy implementation of the Salesbot
public class SalesbotGreedy extends Salesbot {

    // Constructor
    public SalesbotGreedy(Neighborhood n) {
        super(n);
    }

    // The algorithm that this salesbot implements
    @Override
    public House[] findPath() {
        int size = mHood.getNumHouses();
        House[] path = new House[size];
        ArrayList<House> houseList = new ArrayList<House>(Arrays.asList(mHood.getHouses()));
        
        Vector2 currPos = mPos;
        for (int i = 0; i < size; i ++) {
            double sDist = 10000000.0;
            int sIndex = 0;
            for (int ndx = 0; ndx < houseList.size(); ndx ++) {
                double dist = Vector2.distanceSq(currPos, houseList.get(ndx).getPosition());
                if (dist < sDist) {
                    sIndex = ndx;
                    sDist = dist;
                }
            }
            path[i] = houseList.get(sIndex);
            currPos = path[i].getPosition();
            houseList.remove(sIndex);
        }

        return path;        
    }

    @Override
    public String toString() {
        return "Greedy Salesbot";
    }
}
