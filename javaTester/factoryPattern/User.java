package factoryPattern;

public class User {
    public static void main(String[] args) {
        Car carFactory;
        carFactory= getCar("Honda");
        carFactory.viewCar("Civic 2024");
        carFactory.driveCar();
        carFactory.restartCar();

        carFactory = getCar("Huyndai");
        carFactory.viewCar("Santafe");
        carFactory.driveCar();
    }

    // Quan ly viec khoi tao cac loai Car
    public static Car getCar(String carType) {
        Car car = null;
        if (carType.equals("Honda")) {
            car = new Honda();
        } else if (carType.equals("Huyndai")) {
            car = new Huyndai();
        } else {
            new IllegalArgumentException("Car Type is not valid");
        }
        return car;
    }
}
