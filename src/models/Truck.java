package models;

public class Truck extends Vehicle {
    private int payload;

    public Truck() {
    }

    public Truck(String noPlate, String brand, int yearOfProduction, String owner, int payload) {
        super(noPlate, brand, yearOfProduction, owner);
        this.payload = payload;
    }

    public Truck(String[] truckInfo) {
        super(truckInfo[0], truckInfo[1], Integer.parseInt(truckInfo[2]), truckInfo[3]);
        this.payload = Integer.parseInt(truckInfo[4]);
    }

    public int getPayload() {
        return payload;
    }

    public void setPayload(int payload) {
        this.payload = payload;
    }

    @Override
    public String toString() {
        return super.toString() + ',' + payload;
    }

    @Override
    public void showInfo() {
        System.out.println("Truck{" +
                "noPlate='" + super.getNoPlate() + '\'' +
                ", brand='" + super.getBrand() + '\'' +
                ", yearOfProduction=" + super.getYearOfProduction() +
                ", owner='" + super.getOwner() + '\'' +
                ", payload=" + payload +
                '}');
    }
}
