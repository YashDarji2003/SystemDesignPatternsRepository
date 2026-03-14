package CreationPattern;
public class FactoryDesign {
    public static void main(String[] args) {

        CreateNotificationFactory m = new MobileFactory();
        CreateNotificationFactory t = new TabletFactory();
        Client c = new Client(m);
        Notification n = c.get();
        n.send();

        Client c1 = new Client(t);
        Notification n1 = c1.get();
        n1.send();

    }
}

interface Notification {
    void send();
}

class Mobile implements Notification {

    @Override
    public void send() {
        System.out.println("Mobile Send Notification");
    }
}

class Tablet implements Notification {

    @Override
    public void send() {
        System.out.println("Tablet Send Notification");
    }
}

interface CreateNotificationFactory {
    Notification sendNotification();
}

class MobileFactory implements CreateNotificationFactory {

    @Override
    public Notification sendNotification() {
        return new Mobile();
    }
}

class TabletFactory implements CreateNotificationFactory {

    @Override
    public Notification sendNotification() {
        return new Tablet();
    }
}

class Client {
    private Notification n;

    public Client(CreateNotificationFactory factory) {
        this.n = factory.sendNotification();
    }

    public Notification get() {
        return n;
    }
}