package Salesbot;

public class Neighborhood {
    private House[] houses;

    public static final int NumHouses = 12;

    public Neighborhood() {
        houses = new House[Neighborhood.NumHouses];
    }

    public House[] getHouses() {
        return houses;
    }
}
