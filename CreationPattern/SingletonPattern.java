package CreationPattern;

public class SingletonPattern {
    public static void main(String[] args) {
        One o1 = One.getInstance();
        One o2 = One.getInstance();
        boolean check = o1 == o2;
        System.out.println(check);
    }
}

class One {
    static One o = null;

    private One() {
        System.out.println("One Constructor called");
    }

    public static One getInstance() {
        if (o == null) {
            o = new One();
        }
        return o;
    }
}
