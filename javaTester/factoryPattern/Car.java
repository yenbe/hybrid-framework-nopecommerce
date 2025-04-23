package factoryPattern;

public abstract class Car {
    public abstract void viewCar(String carName);
    public abstract void driveCar();
    public void restartCar() {
        System.out.println("Restart Car");
    }
}
