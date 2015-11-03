package Salesbot;

public class Neighborhood {
    private House[] houses;

    public static final int NumHouses = 12;

    public Neighborhood() {
        houses = new House[Neighborhood.NumHouses];
    }

    public void setHouseAt(int index, House newHouse) {
        houses[index] = newHouse;
    }

    public House[] getHouses() {
        return houses;
    }

    public int getNumHouses() {
        return Neighborhood.NumHouses;
    }
}
