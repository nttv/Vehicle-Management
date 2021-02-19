package models;

public class Car extends Vehicle {
    private int noOfSeats;
    private String model;

    public Car() {
    }

    public Car(String noPlate, String brand, int yearOfProduction, String owner, int noOfSeats, String model) {
        super(noPlate, brand, yearOfProduction, owner);
        this.noOfSeats = noOfSeats;
        this.model = model;
    }

    public Car(String[] carInfo) {
        super(carInfo[0], carInfo[1], Integer.parseInt(carInfo[2]), carInfo[3]);
        this.noOfSeats = Integer.parseInt(carInfo[4]);
        this.model = carInfo[5];
    }

    public int getNoOfSeats() {
        return noOfSeats;
    }

    public void setNoOfSeats(int noOfSeats) {
        this.noOfSeats = noOfSeats;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    @Override
    public String toString() {
        return super.toString() + ',' +
                noOfSeats + ',' +
                model;
    }

    @Override
    public void showInfo() {
        System.out.println("Car{" +
                "noPlate='" + super.getNoPlate() + '\'' +
                ", brand='" + super.getBrand() + '\'' +
                ", yearOfProduction=" + super.getYearOfProduction() +
                ", owner='" + super.getOwner() + '\'' +
                ", noOfSeats=" + noOfSeats +
                ", model='" + model + '\'' +
                '}');
    }
}
