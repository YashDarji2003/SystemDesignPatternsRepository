package BehaviourPattern;
import java.util.ArrayList;
import java.util.List;

public class ObserverPattern {
    public static void main(String[] args) {
        Channel ch = new Channel("codewithyash");
        Subscriber sub1 = new Subscriber("Yash", ch);
        Subscriber sub2 = new Subscriber("Shubh", ch);
        Subscriber sub3 = new Subscriber("Akhil", ch);
        ch.subscribe(sub1);
        ch.subscribe(sub2);
        ch.subscribe(sub3);

        ch.uploadVideo("System Design Patterns");
        ch.unsubscribe(sub3);

        ch.uploadVideo("Behavioural Pattern upload");
    }
}

// Observer interface subscriber must implement this method
interface ISubscriber {
    public void update();
}

interface IChannel {
    public void subscribe(ISubscriber subscriber);

    public void unsubscribe(ISubscriber subscriber);

    public void notifySubscriber();
}

class Channel implements IChannel {
    List<ISubscriber> subscribers = new ArrayList<>();
    String name;
    String latestVideo;

    Channel(String name) {
        this.name = name;
    }

    @Override
    public void subscribe(ISubscriber subscriber) {
        System.out.println("Subscribe Method called");
        if (!subscribers.contains(subscriber)) {
            subscribers.add(subscriber);
        }
    }

    @Override
    public void unsubscribe(ISubscriber subscriber) {
        System.out.println("UnSubscribe Method called");
        if (subscribers.contains(subscriber)) {
            subscribers.remove(subscriber);
        }
    }

    @Override
    public void notifySubscriber() {
        for (ISubscriber sub : subscribers) {
            sub.update();
        }
    }

    void uploadVideo(String title) {
        latestVideo = title;
        System.out.println("New LAtest is " + latestVideo);
        notifySubscriber();
    }

    String getVideoData() {
        return "get Videos Data" + latestVideo;
    }
}

class Subscriber implements ISubscriber {

    String name;
    Channel channel;

    Subscriber(String name, Channel channel) {
        this.name = name;
        this.channel = channel;
    }

    @Override
    public void update() {
        System.out.println("Hey " + name + ", " + this.channel.getVideoData());
    }

}
