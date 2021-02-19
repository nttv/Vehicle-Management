package controllers;

import commons.NotFoundVehicleException;
import commons.Validation;
import models.Car;
import models.Motorbike;
import models.Truck;
import models.Vehicle;
import services.BrandManager;
import services.CarManager;
import services.MotorbikeManager;
import services.TruckManager;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MainController {
    private static final int VEHICLE_TRUCK = 1;
    private static final int VEHICLE_CAR = 2;
    private static final int VEHICLE_MOTORBIKE = 3;

    Scanner sc = new Scanner(System.in);
    TruckManager truckManager = new TruckManager();
    CarManager carManager = new CarManager();
    MotorbikeManager motorbikeManager = new MotorbikeManager();

    public static void main(String[] args) {
        MainController mainController = new MainController();
        mainController.displayMenu();
    }

    public void displayMenu() {
        while (true) {
            System.out.println("\nCHƯƠNG TRÌNH QUẢN LÝ PHƯƠNG TIỆN GIAO THÔNG\n"
                    + "CHỌN CHỨC NĂNG:\n"
                    + "1. Thêm mới phương tiện\n"
                    + "2. Hiển thị phương tiện\n"
                    + "3. Chỉnh sửa phương tiện\n"
                    + "4. Xóa phương tiện\n"
                    + "5. Thoát");
            String choice = sc.nextLine();
            switch (choice) {
                case "1":
                    addNewVehicle();
                    break;
                case "2":
                    showAllVehicles();
                    break;
                case "3":
                    updateVehicleInfo();
                    break;
                case "4":
                    removeVehicle();
                    break;
                case "5":
                    System.exit(0);
                default:
                    System.out.println("VUI LÒNG CHỌN MỘT CHỨC NĂNG TRONG DANH SÁCH");
            }
        }
    }

    public void addNewVehicle() {
        while (true) {
            System.out.println("\nCHỌN MỘT CHỨC NĂNG TRONG DANH SÁCH SAU:\n"
                    + "1. Thêm xe tải\n"
                    + "2. Thêm ô tô\n"
                    + "3. Thêm xe máy\n"
                    + "4. Trở về menu trước\n"
                    + "5. Thoát");
            String choice = sc.nextLine();
            switch (choice) {
                case "1":
                    addNewTruck();
                    break;
                case "2":
                    addNewCar();
                    break;
                case "3":
                    addNewMotorbike();
                    break;
                case "4":
                    displayMenu();
                    break;
                case "5":
                    System.exit(0);
                default:
                    System.out.println("VUI LÒNG CHỌN MỘT CHỨC NĂNG TRONG DANH SÁCH");
            }
        }
    }

    public List<String> addVehicle(int vehicleType) {
        System.out.println("\n==== THÊM PHƯƠNG TIỆN MỚI ====");
        List<String> list = new ArrayList<>();
        String noPlate;
        do {
            System.out.print("NHẬP SỐ BIỂN KIỂM SOÁT: ");
            noPlate = sc.nextLine();
        } while (!Validation.validateNoPlate(noPlate, vehicleType));
        list.add(noPlate);

        List<String[]> listBrands = BrandManager.findAllBrand();
        int brandIndex;
        while (true) {
            System.out.println("CHỌN HÃNG SẢN XUẤT TRONG DANH SÁCH SAU:");
            BrandManager.showAllBrand();
            while (true) {
                try {
                    brandIndex = Integer.parseInt(sc.nextLine());
                    break;
                } catch (NumberFormatException e) {
                    System.out.println("VUI LÒNG NHẬP CHỈ MỤC CỦA HÃNG SẢN XUẤT");
                }
            }
            if (brandIndex > 0 && brandIndex <= listBrands.size()) {
                break;
            } else {
                System.out.println("CHỈ MỤC BẠN NHẬP KHÔNG ĐÚNG");
            }
        }
        String brand = listBrands.get(brandIndex - 1)[1];
        list.add(brand);

        int yearOfProduction;
        while (true) {
            System.out.print("NHẬP NĂM SẢN XUẤT: ");
            try {
                yearOfProduction = Integer.parseInt(sc.nextLine());
                break;
            } catch (NumberFormatException e) {
                System.out.println("NĂM SẢN XUẤT PHẢI LÀ ĐỊNH DẠNG SỐ");
            }
        }
        list.add(yearOfProduction + "");

        String owner;
        do {
            System.out.print("NHẬP TÊN CHỦ SỞ HỮU: ");
            owner = sc.nextLine();
        } while (Validation.isEmpty(owner));
        list.add(owner);

        return list;
    }

    public void addNewTruck() {
        List<String> list = addVehicle(VEHICLE_TRUCK);

        int payload;
        while (true) {
            System.out.print("NHẬP TẢI TRỌNG: ");
            try {
                payload = Integer.parseInt(sc.nextLine());
                break;
            } catch (NumberFormatException e) {
                System.out.println("TẢI TRỌNG PHẢI LÀ ĐỊNH DẠNG SỐ");
            }
        }
        list.add(payload + "");

        String[] truckInfo = list.toArray(new String[0]);
        Truck truck = new Truck(truckInfo);
        truckManager.add(truck);
    }

    public void addNewCar() {
        List<String> list = addVehicle(VEHICLE_CAR);

        int noOfSeats;
        while (true) {
            System.out.print("NHẬP SỐ CHỖ NGỒI: ");
            try {
                noOfSeats = Integer.parseInt(sc.nextLine());
                break;
            } catch (NumberFormatException e) {
                System.out.println("SỐ CHỖ NGỒI PHẢI LÀ ĐỊNH DẠNG SỐ");
            }
        }
        list.add(noOfSeats + "");

        String model;
        do {
            System.out.print("NHẬP KIỂU XE: ");
            model = sc.nextLine();
        } while (Validation.isEmpty(model));
        list.add(model);

        String[] carInfo = list.toArray(new String[0]);
        Car car = new Car(carInfo);
        carManager.add(car);
    }

    public void addNewMotorbike() {
        List<String> list = addVehicle(VEHICLE_MOTORBIKE);

        int capacity;
        while (true) {
            System.out.print("NHẬP CÔNG SUẤT: ");
            try {
                capacity = Integer.parseInt(sc.nextLine());
                break;
            } catch (NumberFormatException e) {
                System.out.println("CÔNG SUẤT PHẢI LÀ ĐỊNH DẠNG SỐ");
            }
        }
        list.add(capacity + "");

        String[] motorbikeInfo = list.toArray(new String[0]);
        Motorbike motorbike = new Motorbike(motorbikeInfo);
        motorbikeManager.add(motorbike);
    }

    public void showAllVehicles() {
        while (true) {
            System.out.println("\nCHỌN MỘT CHỨC NĂNG TRONG DANH SÁCH SAU:\n"
                    + "1. Hiển thị xe tải\n"
                    + "2. Hiển thị ô tô\n"
                    + "3. Hiển thị xe máy\n"
                    + "4. Trở về menu trước\n"
                    + "5. Thoát");
            String choice = sc.nextLine();
            System.out.println();
            switch (choice) {
                case "1":
                    showAllTrucks();
                    break;
                case "2":
                    showAllCars();
                    break;
                case "3":
                    showAllMotorbikes();
                    break;
                case "4":
                    displayMenu();
                    break;
                case "5":
                    System.exit(0);
            }
        }
    }

    public void showAllTrucks() {
        System.out.println("\n==== DANH SÁCH XE TẢI ====");
        truckManager.show();
    }

    public void showAllCars() {
        System.out.println("\n==== DANH SÁCH Ô TÔ ====");
        carManager.show();
    }

    public void showAllMotorbikes() {
        System.out.println("\n==== DANH SÁCH XE MÁY ====");
        motorbikeManager.show();
    }

    public void updateVehicleInfo() {
        System.out.println("\n==== CHỈNH SỬA THÔNG TIN PHƯƠNG TIỆN ====");
        Vehicle vehicle;
        int vehicleType = 0;
        while (true) {
            System.out.print("NHẬP SỐ BIỂN KIỂM SOÁT CỦA PHƯƠNG TIỆN MUỐN SỬA: ");
            String noPlate = sc.nextLine();
            if ((vehicle = truckManager.findById(noPlate)) != null) {
                vehicleType = 1;
            } else if ((vehicle = carManager.findById(noPlate)) != null) {
                vehicleType = 2;
            } else if ((vehicle = motorbikeManager.findById(noPlate)) != null) {
                vehicleType = 3;
            }
            try {
                if (vehicleType == 0) {
                    throw new NotFoundVehicleException();
                }
                break;
            } catch (NotFoundVehicleException e) {
                System.out.println(e.getMessage());
                while (true) {
                    System.out.println("BẠN CÓ MUỐN THỬ LẠI? (1.CÓ | 2.KHÔNG)");
                    String choice = sc.nextLine();
                    if (choice.equals("1")) {
                        break;
                    } else if (choice.equals("2")) {
                        return;
                    }
                }
            }
        }
        String noPlate = vehicle.getNoPlate();
        String owner;
        do {
            System.out.print("NHẬP TÊN CHỦ SỞ HỮU MỚI: ");
            owner = sc.nextLine();
        } while (Validation.isEmpty(owner));
        vehicle.setOwner(owner);

        while (true) {
            System.out.println("XÁC NHẬN LƯU THAY ĐỔI CHO PHƯƠNG TIỆN? (1.CÓ | 2.KHÔNG)");
            String choice = sc.nextLine();
            if (choice.equals("1")) {
                switch (vehicleType) {
                    case 1:
                        truckManager.edit((Truck) vehicle, noPlate);
                        break;
                    case 2:
                        carManager.edit((Car) vehicle, noPlate);
                        break;
                    case 3:
                        motorbikeManager.edit((Motorbike) vehicle, noPlate);
                }
                System.out.println("LƯU CHỈNH SỬA THÀNH CÔNG");
                return;
            } else if (choice.equals("2")) {
                return;
            }
        }
    }

    public void removeVehicle() {
        System.out.println("\n==== XÓA PHƯƠNG TIỆN ====");
        String noPlate;
        int vehicleType = 0;
        while (true) {
            System.out.print("NHẬP SỐ BIỂN KIỂM SOÁT CỦA PHƯƠNG TIỆN MUỐN XÓA: ");
            noPlate = sc.nextLine();
            if (truckManager.findById(noPlate) != null) {
                vehicleType = 1;
            } else if (carManager.findById(noPlate) != null) {
                vehicleType = 2;
            } else if (motorbikeManager.findById(noPlate) != null) {
                vehicleType = 3;
            }
            try {
                if (vehicleType == 0) {
                    throw new NotFoundVehicleException();
                }
                break;
            } catch (NotFoundVehicleException e) {
                System.out.println(e.getMessage());
                while (true) {
                    System.out.println("BẠN CÓ MUỐN THỬ LẠI? (1.CÓ | 2.KHÔNG)");
                    String choice = sc.nextLine();
                    if (choice.equals("1")) {
                        break;
                    } else if (choice.equals("2")) {
                        return;
                    }
                }
            }
        }
        while (true) {
            System.out.println("XÁC NHẬN XÓA PHƯƠNG TIỆN VỚI BIỂN KIỂM SOÁT " + noPlate + "? (1.CÓ | 2.KHÔNG)");
            String choice = sc.nextLine();
            if (choice.equals("1")) {
                switch (vehicleType) {
                    case 1:
                        truckManager.delete(noPlate);
                        break;
                    case 2:
                        carManager.delete(noPlate);
                        break;
                    case 3:
                        motorbikeManager.delete(noPlate);
                }
                System.out.println("ĐÃ XÓA THÀNH CÔNG");
                return;
            } else if (choice.equals("2")) {
                return;
            }
        }
    }
}
