package commons;

import models.Car;
import models.Motorbike;
import models.Truck;
import services.CarManager;
import services.MotorbikeManager;
import services.TruckManager;

import java.util.regex.Pattern;

public class Validation {
    private static final int VEHICLE_TRUCK = 1;
    private static final int VEHICLE_CAR = 2;
    private static final int VEHICLE_MOTORBIKE = 3;

    private static final String REQUIRED_STRING_REGEX = "^\\w.*$";
    private static final String NO_PLATE_TRUCK_REGEX = "^\\d{2}C-\\d{3}\\.\\d{2}$";
    private static final String NO_PLATE_CAR_REGEX = "^\\d{2}Y-\\d{3}\\.\\d{2}$";
    private static final String NO_PLATE_MOTORBIKE_REGEX = "^\\d{2}-[A-Z]\\d-\\d{3}\\.\\d{2}$";

    public static boolean isEmpty(String string) {
        if (Pattern.matches(REQUIRED_STRING_REGEX, string)) {
            return false;
        }
        System.out.println("TRƯỜNG NÀY LÀ BẮT BUỘC NHẬP GIÁ TRỊ");
        return true;
    }

    public static boolean validateNoPlate(String noPlate, int vehicleType) {
        boolean result = false;
        switch (vehicleType) {
            case VEHICLE_TRUCK:
                result = validateTruck(noPlate);
                break;
            case VEHICLE_CAR:
                result = validateCar(noPlate);
                break;
            case VEHICLE_MOTORBIKE:
                result = validateMotorbike(noPlate);
        }
        return result;
    }

    public static boolean validateTruck(String noPlate) {
        if (Pattern.matches(NO_PLATE_TRUCK_REGEX, noPlate)) {
            Truck truck = new TruckManager().findById(noPlate);
            if (truck != null) {
                System.out.println("SỐ BIỂN KIỂM SOÁT ĐÃ TỒN TẠI");
                return false;
            }
            return true;
        }
        System.out.println("BIỂN KIỂM SOÁT PHẢI ĐÚNG ĐỊNH DẠNG XXC-XXX.XX (X: 0-9)");
        return false;
    }

    public static boolean validateCar(String noPlate) {
        if (Pattern.matches(NO_PLATE_CAR_REGEX, noPlate)) {
            Car car = new CarManager().findById(noPlate);
            if (car != null) {
                System.out.println("SỐ BIỂN KIỂM SOÁT ĐÃ TỒN TẠI");
                return false;
            }
            return true;
        }
        System.out.println("BIỂN KIỂM SOÁT PHẢI ĐÚNG ĐỊNH DẠNG XXY-XXX.XX (X: 0-9)");
        return false;
    }

    public static boolean validateMotorbike(String noPlate) {
        if (Pattern.matches(NO_PLATE_MOTORBIKE_REGEX, noPlate)) {
            Motorbike motorbike = new MotorbikeManager().findById(noPlate);
            if (motorbike != null) {
                System.out.println("SỐ BIỂN KIỂM SOÁT ĐÃ TỒN TẠI");
                return false;
            }
            return true;
        }
        System.out.println("BIỂN KIỂM SOÁT PHẢI ĐÚNG ĐỊNH DẠNG XX-YZ-XXX.XX (X: 0-9)");
        return false;
    }
}
