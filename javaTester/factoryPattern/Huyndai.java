package factoryPattern;

public class Huyndai extends Car{
    @Override
    public void viewCar(String carName) {
        System.out.println("View" + carName);
    }

    @Override
    public void driveCar() {
        System.out.println("Drive Huyndai Car");
    }
}
