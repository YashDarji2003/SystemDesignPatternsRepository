package BehaviourPattern;
import java.util.*;

public class MediatorPattern {
    public static void main(String[] abc) {
        ChatMediator chatMediator = new ChatMediator();
        User user1 = new User("Yash", chatMediator);
        User user2 = new User("Jack", chatMediator);
        User user3 = new User("Jee", chatMediator);

        chatMediator.mute("Yash", "Jee");
        user1.send("Hello I m Yash");
        user3.sendPrivate("Jack", "Hey Jack");
        user1.sendPrivate("Jee", "I M JEE");
    }
}

abstract class Colleague {

    IMediator mediator;

    Colleague(IMediator m) {
        mediator = m;
        mediator.registerColleague(this);
    }

    abstract String getName();

    abstract void send(String msg);

    abstract void sendPrivate(String to, String msg);

    abstract void receive(String from, String msg);
}

abstract class IMediator {
    abstract void registerColleague(Colleague col);

    abstract void send(String from, String msg);

    abstract void sendPrivate(String from, String to, String msg);
}

class ChatMediator extends IMediator {

    List<Colleague> colleagues = new ArrayList<>();
    Map<String, Set<String>> mutes = new HashMap<>();

    @Override
    void registerColleague(Colleague col) {
        colleagues.add(col);
    }

    @Override
    void send(String from, String msg) {
        System.out.println("[  " + from + " Broadcast ] msg " + msg);
        for (Colleague c : colleagues) {
            if (c.getName().equals(from))
                continue;
            boolean isMuted = mutes.containsKey(from) &&
                    mutes.get(from).contains(c.getName());
            if (!isMuted) {
                c.receive(from, msg);
            }
        }

    }

    @Override
    void sendPrivate(String from, String to, String msg) {
        System.out.println("[ From " + from + " To " + to + " ] msg " + msg);

        boolean isMuted = mutes.containsKey(from) &&
                mutes.get(from).contains(to);

        if (isMuted) {
            System.out.println("Message is muted");
            return;
        }

        for (Colleague c : colleagues) {
            if (c.getName().equals(to)) {
                c.receive(from, msg);
                return;
            }
        }

        System.out.println("Mediator user " + to + " not found");
    }

    void mute(String who, String whom) {
        mutes.computeIfAbsent(who, k -> new HashSet<>()).add(whom);
    }
}

class User extends Colleague {

    String name;

    User(String n, IMediator mediator) {
        super(mediator);
        name = n;
    }

    @Override
    String getName() {
        return name;
    }

    @Override
    void send(String msg) {
        mediator.send(name, msg);
    }

    @Override
    void sendPrivate(String to, String msg) {
        mediator.sendPrivate(name, to, msg);
    }

    @Override
    void receive(String from, String msg) {
        System.out.println("    " + name + "  got from " + from + " : " + "msg " + msg);
    }

}