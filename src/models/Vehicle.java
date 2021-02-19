package models;

public abstract class Vehicle {
    private String noPlate;
    private String brand;
    private int yearOfProduction;
    private String owner;

    public Vehicle() {
    }

    public Vehicle(String noPlate, String brand, int yearOfProduction, String owner) {
        this.noPlate = noPlate;
        this.brand = brand;
        this.yearOfProduction = yearOfProduction;
        this.owner = owner;
    }

    public String getNoPlate() {
        return noPlate;
    }

    public void setNoPlate(String noPlate) {
        this.noPlate = noPlate;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public int getYearOfProduction() {
        return yearOfProduction;
    }

    public void setYearOfProduction(int yearOfProduction) {
        this.yearOfProduction = yearOfProduction;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    @Override
    public String toString() {
        return noPlate + ',' +
                brand + ',' +
                yearOfProduction + ',' +
                owner;
    }

    public abstract void showInfo();
}
