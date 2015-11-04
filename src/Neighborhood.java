package Salesbot;

// A "neighborhood" that contains a number of houses
public class Neighborhood {
    // Private Data Members
    private House[] houses;

    // Public Static Members
    public static final int NumHouses = 12;

    // Constructors
    public Neighborhood() {
        houses = new House[Neighborhood.NumHouses];
    }

    // Mutators
    public void setHouseAt(int index, House newHouse) {
        houses[index] = newHouse;
    }

    // Accessors
    public House getHouseAt(int index) {
        return houses[index];
    }

    public House[] getHouses() {
        return houses;
    }

    public int getNumHouses() {
        return Neighborhood.NumHouses;
    }
}
