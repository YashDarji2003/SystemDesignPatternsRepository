
public class StateDesignPattern {
    public static void main(String[] args) {
        System.out.println("========= Burger Vending Machine ======");
        int itemCount = 2;
        int itemPrice = 100;

        VendingMachine machine = new VendingMachine(itemCount, itemPrice);
        machine.printStatus();

        System.out.println("1. Try To select Item without Insert Coin");
        machine.selectItem();
        machine.printStatus();

        System.out.println("2. Inserting Coin");
        machine.insertCoin(80); // State Change to Has Coin
        machine.printStatus();

        System.out.println("3. Selecting Item with Insufficient Funds");
        machine.selectItem(); // Insufficient Funds stay in Has Coin
        machine.printStatus();

        System.out.println("4. Adding More Coins");
        machine.insertCoin(20); // Adding More money stay in Has Coin
        machine.printStatus();

        System.out.println("5. Selecting Item Now");
        machine.selectItem(); // Change to SOLD state
        machine.printStatus();

        System.out.println("6. Dispensing Item");
        machine.dispense(); // State change to No_coin state
        machine.printStatus();

        System.out.println("7. Buying Last item");
        machine.insertCoin(20);
        machine.selectItem();
        machine.dispense();
        machine.printStatus();

        System.out.println("8. trying to sold out machine");
        machine.insertCoin(5);

        System.out.println("8. trying to sold out machine");
        machine.refill(2);
        machine.printStatus();
    }
}

abstract class VendingState {

    abstract VendingState insertCoin(VendingMachine machine, int coin);

    abstract VendingState selectItem(VendingMachine machine);

    abstract VendingState dispense(VendingMachine machine);

    abstract VendingState returnCoin(VendingMachine machine);

    abstract VendingState refill(VendingMachine machine, int quantity);

    abstract String getStateName();
}

class VendingMachine {
    VendingState curr_state;
    int itemCount;
    int itemPrice;
    int insertedCoins;

    VendingState noCoinState;
    VendingState hasCoinState;
    VendingState dispenseState;
    VendingState soldOutState;

    VendingMachine(int itemCount, int itemPrice) {
        this.itemCount = itemCount;
        this.itemPrice = itemPrice;
        noCoinState = new NoCoinState();
        hasCoinState = new HasCoinState();
        dispenseState = new DispenseState();
        soldOutState = new SoldOutState();

        if (itemCount > 0) {
            curr_state = noCoinState;
        } else {
            curr_state = soldOutState;
        }
    }

    void insertCoin(int coin) {
        curr_state = curr_state.insertCoin(this, coin);
    }

    void selectItem() {
        curr_state = curr_state.selectItem(this);
    }

    void dispense() {
        curr_state = curr_state.dispense(this);
    }

    void returnCoin() {
        curr_state = curr_state.returnCoin(this);
    }

    void refill(int quantity) {
        curr_state = curr_state.refill(this, quantity);
    }

    void printStatus() {
        System.out.println("-------------  Vending Machine Status --------------");
        System.out.println("--- Item Remaining " + itemCount);
        System.out.println("Inserted Coins Rs " + insertedCoins);
        System.out.println("Current State " + curr_state.getStateName());
    }

    VendingState getNoCoinState() {
        return noCoinState;
    }

    VendingState getHasCoinState() {
        return hasCoinState;
    }

    VendingState getDispenseState() {
        return dispenseState;
    }

    VendingState getSoldoutState() {
        return soldOutState;
    }

    int getItemCount() {
        return itemCount;
    }

    void decrementItemCount() {
        itemCount--;
    }

    void incrementItemCount(int count) {
        itemCount = count;
    }

    int getInsertedCoin() {
        return insertedCoins;
    }

    void setInsertedCoin(int coin) {
        insertedCoins = coin;
    }

    void addCoin(int coin) {
        insertedCoins += coin;
    }

    int getPrice() {
        return this.itemPrice;
    }

    void setPrice(int itemPrice) {
        this.itemPrice = itemPrice;
    }
}

class NoCoinState extends VendingState {

    @Override
    VendingState insertCoin(VendingMachine machine, int coin) {
        machine.setInsertedCoin(coin);
        System.out.println("Coin Inserted " + coin);
        return machine.getHasCoinState();
    }

    @Override
    VendingState selectItem(VendingMachine machine) {
        System.out.println("Please Insert Coin First");
        return machine.getNoCoinState();
    }

    @Override
    VendingState dispense(VendingMachine machine) {
        System.out.println("Please Insert Coin and select Item First");
        return machine.getNoCoinState();
    }

    @Override
    VendingState returnCoin(VendingMachine machine) {
        System.out.println("No Coin To return");
        return machine.getNoCoinState();
    }

    @Override
    VendingState refill(VendingMachine machine, int quantity) {
        System.out.println("Item Refill");
        machine.incrementItemCount(quantity);
        return machine.getNoCoinState();
    }

    @Override
    String getStateName() {
        return "NO COIN STATE";
    }

}

class HasCoinState extends VendingState {

    @Override
    VendingState insertCoin(VendingMachine machine, int coin) {
        machine.addCoin(coin);
        System.out.println("Additional Coin Inserted Balance : Rs" + machine.getInsertedCoin());
        return machine.getHasCoinState();
    }

    @Override
    VendingState selectItem(VendingMachine machine) {
        if (machine.getInsertedCoin() >= machine.getPrice()) {
            System.out.println("Item Selected Dispensing ");
            int change = machine.getInsertedCoin() - machine.getPrice();

            if (change > 0) {
                System.out.println("Change Return is " + change);
            }

            machine.setInsertedCoin(0);
            return machine.getDispenseState(); // FIX
        } else {
            int needed = machine.getPrice() - machine.getInsertedCoin();
            System.out.println("Insufficient Funds " + needed + " more");
            return machine.getHasCoinState();
        }
    }

    @Override
    VendingState dispense(VendingMachine machine) {
        System.out.println("Please Select an Item First");
        return machine.getHasCoinState();
    }

    @Override
    VendingState returnCoin(VendingMachine machine) {
        System.out.println("Coin Returned Rs" + machine.getInsertedCoin());
        machine.setInsertedCoin(0);
        return machine.getNoCoinState();
    }

    @Override
    VendingState refill(VendingMachine machine, int quantity) {
        System.out.println("Can Refill in This State");
        return machine.getHasCoinState();
    }

    @Override
    String getStateName() {
        return "HAS COIN STATE";
    }

}

class DispenseState extends VendingState {

    @Override
    VendingState insertCoin(VendingMachine machine, int coin) {
        System.out.println("Please Wait ALready Dispensing Item ,Coin Returned Rs" + coin);
        return machine.getDispenseState();
    }

    @Override
    VendingState selectItem(VendingMachine machine) {
        System.out.println("Already Dispensing Item Please Wait ");
        return machine.getDispenseState();
    }

    @Override
    VendingState dispense(VendingMachine machine) {
        System.out.println("Item Dispensed !");
        machine.decrementItemCount();
        if (machine.getItemCount() > 0) {
            return machine.getNoCoinState();
        } else {
            return machine.getSoldoutState();
        }
    }

    @Override
    VendingState returnCoin(VendingMachine machine) {
        System.out.println("Cannot Return coin while dispensing Item");
        return machine.getDispenseState();
    }

    @Override
    VendingState refill(VendingMachine machine, int quantity) {
        System.out.println("Can Refill in This State");
        return machine.getDispenseState();
    }

    @Override
    String getStateName() {
        return "DISPENSE STATE";
    }

}

class SoldOutState extends VendingState {

    @Override
    VendingState insertCoin(VendingMachine machine, int coin) {
        System.out.println("Machine is Sold out ");
        return machine.getSoldoutState();
    }

    @Override
    VendingState selectItem(VendingMachine machine) {
        System.out.println("Machine is Sold out ");
        return machine.getSoldoutState();
    }

    @Override
    VendingState dispense(VendingMachine machine) {
        System.out.println("Machine is Sold out ");
        return machine.getSoldoutState();
    }

    @Override
    VendingState returnCoin(VendingMachine machine) {
        System.out.println("Machine is Sold out ,No Coin Inserted ");
        return machine.getSoldoutState();
    }

    @Override
    VendingState refill(VendingMachine machine, int quantity) {
        System.out.println("Item Refilling ");
        machine.incrementItemCount(quantity);
        return machine.getNoCoinState();
    }

    @Override
    String getStateName() {
        return "SOLD OUT";
    }

}