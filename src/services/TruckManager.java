package services;

import commons.FuncReadAndWrite;
import models.Truck;

import java.util.ArrayList;
import java.util.List;

public class TruckManager implements IService<Truck> {
    private static FuncReadAndWrite<Truck> funcReadAndWrite = new FuncReadAndWrite<>();

    @Override
    public List<Truck> findAll() {
        List<String[]> list = FuncReadAndWrite.readFile("xeTai.csv");
        List<Truck> listTrucks = new ArrayList<>();
        for (String[] line : list) {
            Truck truck = new Truck(line);
            listTrucks.add(truck);
        }
        return listTrucks;
    }

    @Override
    public Truck findById(String noPlate) {
        List<Truck> list = findAll();
        for (Truck truck : list) {
            if (truck.getNoPlate().equals(noPlate)) {
                return truck;
            }
        }
        return null;
    }

    public void show() {
        List<Truck> list = findAll();
        if (list.size() == 0) {
            System.out.println("KHÔNG CÓ PHƯƠNG TIỆN XE TẢI NÀO ĐƯỢC LƯU TRONG DANH SÁCH");
            return;
        }
        int i = 1;
        for (Truck truck : list) {
            System.out.print(i + ". ");
            truck.showInfo();
            i++;
        }
    }

    @Override
    public void add(Truck truck) {
        List<Truck> list = new ArrayList<>();
        list.add(truck);
        funcReadAndWrite.writeFile("xeTai.csv", list, true);
    }

    @Override
    public void edit(Truck truck, String noPlate) {
        List<Truck> list = findAll();
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getNoPlate().equals(noPlate)) {
                list.remove(i);
                list.add(i, truck);
                funcReadAndWrite.writeFile("xeTai.csv", list, false);
                return;
            }
        }
    }

    @Override
    public void delete(String noPlate) {
        List<Truck> list = findAll();
        for (Truck truck : list) {
            if (truck.getNoPlate().equals(noPlate)) {
                list.remove(truck);
                funcReadAndWrite.writeFile("xeTai.csv", list, false);
                return;
            }
        }
    }
}
