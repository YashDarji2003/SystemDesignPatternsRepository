public class FascadePattern {
    public static void main(String[] args) {
        ComputerFascade computerFascade = new ComputerFascade();
        computerFascade.startComputer();
    }
}

class PowerSupply {
    public void providePower() {
        System.out.println("POwer SUpply Providing POewer");
    }
}

class CoolingSystem {
    public void startFan() {
        System.out.println("Fan is On to Cool : " + " Cooling System");
    }
}

class CPU {
    public void initialize() {
        System.out.println("CPU Started ");
    }
}

class Memory {
    public void selfTest() {
        System.out.println("Memory Self Test");
    }
}

class HardDrive {
    public void spinUp() {
        System.out.println("hard Drve Start");
    }
}

class OS {
    public void OsStart() {
        System.out.println("Os started");
    }
}

class ComputerFascade {
    PowerSupply powerSupply;
    CoolingSystem coolingSystem;
    CPU cpu;
    Memory memory;
    HardDrive hardDrive;
    OS os;

        public ComputerFascade() {
        powerSupply = new PowerSupply();
        coolingSystem = new CoolingSystem();
        cpu = new CPU();
        memory = new Memory();
        hardDrive = new HardDrive();
        os = new OS();
    }

    public void startComputer() {
        System.out.print("Starting Computer");
        powerSupply.providePower();
        coolingSystem.startFan();
        cpu.initialize();
        memory.selfTest();
        hardDrive.spinUp();
        os.OsStart();
        System.out.println("Computer Boot Successfully");
    }
}