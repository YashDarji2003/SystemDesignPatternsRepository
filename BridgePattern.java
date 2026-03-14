class BridgePattern {
    public static void main(String[] args) {
Engine diesEngine=new DieselEngine();
Engine petrolEngine = new PetrolEngine();

Car suCar=new SuzukiCars(diesEngine);
Car thCar=new TharCar(petrolEngine);

suCar.start();
thCar.start();
    }
}

abstract class Engine {
    abstract void drive();
}

class DieselEngine extends Engine {

    @Override
    void drive() {
        System.out.println("Diesel Engine");
    }
}

class PetrolEngine extends Engine {

    @Override
    void drive() {
        System.out.println("Petrol Engine");
    }
}

abstract class Car {
    Engine e;

    Car(Engine e) {
        this.e = e;
    }

    abstract void start();
}

class SuzukiCars extends Car {

    SuzukiCars(Engine e) {
        super(e);
    }

    @Override
    void start() {
        e.drive();
    }
}

class TharCar extends Car {
    TharCar(Engine e) {
        super(e);
    }

    @Override
    void start() {
        e.drive();
    }
}
