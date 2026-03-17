package BehaviourPattern;
public class StrategyPattern {
    public static void main(String[] args) {
        Robot robot1 = new WorkerRobot(new NormalWalk(), new NormalTalk(), new NormalFly());

        robot1.walk();
        robot1.talk();
        robot1.fly();
        Robot robot2 = new WorkerRobot(new NoWalk(), new NoTalk(), new NoFly());

        robot2.walk();
        robot2.talk();
    }
}

abstract class WalkableRobot {
    abstract void walk();
}

class NormalWalk extends WalkableRobot {

    @Override
    void walk() {
        System.out.println("Walked Normally . . . . . ");
    }
}

class NoWalk extends WalkableRobot {

    @Override
    void walk() {
        System.out.println("Not Walkable . . . . . ");
    }
}

abstract class TalkableRobot {
    abstract void talk();
}

class NormalTalk extends TalkableRobot {
    @Override
    void talk() {
        System.out.println("Talkable Normally . . . . ");
    }
}

class NoTalk extends TalkableRobot {
    @Override
    void talk() {
        System.out.println("Not Talk . . . . ");
    }
}

abstract class FlyableRobot {
    abstract void fly();
}

class NormalFly extends FlyableRobot {
    @Override
    void fly() {
        System.out.println("Flyable Normally . . . . ");
    }
}

class NoFly extends FlyableRobot {
    @Override
    void fly() {
        System.out.println("Not Fly . . . . ");
    }
}

abstract class Robot {
    WalkableRobot walkableRobot;
    TalkableRobot talkableRobot;
    FlyableRobot flyableRobot;

    public Robot(WalkableRobot walkableRobot, TalkableRobot talkableRobot, FlyableRobot flyableRobot) {
        this.walkableRobot = walkableRobot;
        this.talkableRobot = talkableRobot;
        this.flyableRobot = flyableRobot;
    }

    void talk() {
        talkableRobot.talk();
    }

    void walk() {
        walkableRobot.walk();
    }

    void fly() {
        flyableRobot.fly();
    }

    abstract void projection();
}

class CompanionRobot extends Robot {

    public CompanionRobot(WalkableRobot walkableRobot, TalkableRobot talkableRobot, FlyableRobot flyableRobot) {
        super(walkableRobot, talkableRobot, flyableRobot);
    }

    @Override
    void projection() {
        System.out.println("Displaying Friendly companion featured");
    }
}

class WorkerRobot extends Robot {

    public WorkerRobot(WalkableRobot walkableRobot, TalkableRobot talkableRobot, FlyableRobot flyableRobot) {
        super(walkableRobot, talkableRobot, flyableRobot);
    }

    @Override
    void projection() {
        System.out.println("Displaying Friendly worker featured");
    }
}
