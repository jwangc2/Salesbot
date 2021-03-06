package Salesbot;

import java.util.ArrayList;
import java.util.Arrays;

// A vertical implementation of the Salesbot
public class SalesbotVert extends Salesbot {

    // Constructor
    public SalesbotVert(Neighborhood n) {
        super(n);
    }

    // Algorithm sorts from bottom to top
    @Override
    public House[] findPath() {
        int size = mHood.getNumHouses();
        House[] path = mHood.getHouses();

        for (int n = 0; n < size; n ++) {

            boolean swapped = false;
            for (int i = 1; i < size; i ++) {
                if (path[i].getPosition().y < path[i - 1].getPosition().y) {
                    // swap
                    House save = path[i - 1];
                    path[i - 1] = path[i];
                    path[i] = save;
                    swapped = true;
                }
            }

            // Check for solved solution (ie: no swaps made)
            if (!swapped)
                break;
        }

        return path;
    }

    @Override
    public String toString() {
        return "Vertical Salesbot";
    }
}
