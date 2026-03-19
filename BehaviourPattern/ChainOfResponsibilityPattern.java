package BehaviourPattern;
public class ChainOfResponsibilityPattern {
    public static void main(String[] args) {
        MoneyHandler thousaMoneyHandler = new ThousandHandler(3);
        MoneyHandler hundredMoneyHandler = new HundredHandler(20);
        MoneyHandler TwoHundredHandler = new TwoHundredHandler(10);

        thousaMoneyHandler.setNextHandler(hundredMoneyHandler);
        hundredMoneyHandler.setNextHandler(TwoHundredHandler);
        System.out.println("Dispensing amt " + 300 + " withdraw");
        thousaMoneyHandler.dispense(300);
    }
}

abstract class MoneyHandler {
    MoneyHandler nextHandler;

    MoneyHandler() {
        this.nextHandler = null;
    }

    void setNextHandler(MoneyHandler next) {
        this.nextHandler = next;
    }

    abstract void dispense(int amt);
}

class ThousandHandler extends MoneyHandler {

    int numNotes;

    ThousandHandler(int no) {
        this.numNotes = no;
    }

    @Override
    void dispense(int amt) {
        int noteNeeded = amt / 1000;

        if (noteNeeded > numNotes) {
            noteNeeded = numNotes;
            numNotes = 0;
        } else {
            numNotes -= noteNeeded;
        }

        if (noteNeeded > 0) {
            System.out.println("Dispensing " + noteNeeded + " 1000 notes ");
        }
        int remaining = amt - (noteNeeded * 1000);
        if (remaining > 0) {
            if (nextHandler != null)
                nextHandler.dispense(remaining);
            else {
                System.out.println("Remaining amt of " + remaining + " cannot be fulfilled (Insufficient)");
            }
        }
    }
}

class HundredHandler extends MoneyHandler {

    int numNotes;

    HundredHandler(int no) {
        this.numNotes = no;
    }

    @Override
    void dispense(int amt) {
        int noteNeeded = amt / 100;

        if (noteNeeded > numNotes) {
            noteNeeded = numNotes;
            numNotes = 0;
        } else {
            numNotes -= noteNeeded;
        }

        if (noteNeeded > 0) {
            System.out.println("Dispensing " + noteNeeded + " 100 notes ");
        }
        int remaining = amt - (noteNeeded * 100);
        if (remaining > 0) {
            if (nextHandler != null)
                nextHandler.dispense(remaining);
            else {
                System.out.println("Remaining amt of " + remaining + " cannot be fulfilled (Insufficient)");
            }
        }
    }
}

class TwoHundredHandler extends MoneyHandler {

    int numNotes;

    TwoHundredHandler(int no) {
        this.numNotes = no;
    }

    @Override
    void dispense(int amt) {
        int noteNeeded = amt / 200;

        if (noteNeeded > numNotes) {
            noteNeeded = numNotes;
            numNotes = 0;
        } else {
            numNotes -= noteNeeded;
        }

        if (noteNeeded > 0) {
            System.out.println("Dispensing " + noteNeeded + " 200 notes ");
        }
        int remaining = amt - (noteNeeded * 200);
        if (remaining > 0) {
            if (nextHandler != null)
                nextHandler.dispense(remaining);
            else {
                System.out.println("Remaining amt of " + remaining + " cannot be fulfilled (Insufficient)");
            }
        }
    }
}
