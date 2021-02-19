package models;

public class Motorbike extends Vehicle {
    private int capacity;

    public Motorbike() {
    }

    public Motorbike(String noPlate, String brand, int yearOfProduction, String owner, int capacity) {
        super(noPlate, brand, yearOfProduction, owner);
        this.capacity = capacity;
    }

    public Motorbike(String[] motorbikeInfo) {
        super(motorbikeInfo[0], motorbikeInfo[1], Integer.parseInt(motorbikeInfo[2]), motorbikeInfo[3]);
        this.capacity = Integer.parseInt(motorbikeInfo[4]);
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    @Override
    public String toString() {
        return super.toString() + ',' + capacity;
    }

    @Override
    public void showInfo() {
        System.out.println("Motorbike{" +
                "noPlate='" + super.getNoPlate() + '\'' +
                ", brand='" + super.getBrand() + '\'' +
                ", yearOfProduction=" + super.getYearOfProduction() +
                ", owner='" + super.getOwner() + '\'' +
                ", capacity=" + capacity +
                '}');
    }
}
