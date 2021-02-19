package commons;

public class NotFoundVehicleException extends Exception {
    public NotFoundVehicleException() {
    }

    @Override
    public String getMessage() {
        return "BIỂN KIỂM SOÁT KHÔNG TỒN TẠI";
    }
}
