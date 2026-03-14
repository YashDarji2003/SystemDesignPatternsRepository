public class CommandPattern {
    public static void main(String[] args) {
        Light light = new Light();
        Fan fan = new Fan();

        Command lightCommand = new LightCommand(light);
        Command fanCommand = new FanCommand(fan);

        RemoteController remote = new RemoteController();

        remote.setCommand(0, lightCommand);
        remote.setCommand(1, fanCommand);

        remote.pressButton(0); // Light On
        remote.pressButton(0); // Light Off

        remote.pressButton(1); // Fan On
        remote.pressButton(1); //
        remote.pressButton(4); //   
    }
}

abstract class Command {

    abstract void execute();

    abstract void undo();
}

class Light {
    void on() {
        System.out.println("Light On");
    }

    void off() {
        System.out.println("Light Off");
    }
}

class Fan {
    void on() {
        System.out.println("Fan On");
    }

    void off() {
        System.out.println("Fan Off");
    }
}

class LightCommand extends Command {

    Light l;

    LightCommand(Light l) {
        this.l = l;
    }

    @Override
    public void execute() {
        l.on();
    }

    @Override
    public void undo() {
        l.off();
    }

}

class FanCommand extends Command {

    Fan f;

    FanCommand(Fan f) {
        this.f = f;
    }

    @Override
    public void execute() {
        f.on();
    }

    @Override
    public void undo() {
        f.off();
    }

}

class RemoteController {
    final int numButtons = 4;
    Command[] button = new Command[numButtons];
    boolean[] buttonPressed = new boolean[numButtons];

    RemoteController() {
        for (int i = 0; i < numButtons; i++) {
            button[i] = null;
            buttonPressed[i] = false;
        }
    }

    void setCommand(int idx, Command cmd) {
        if (idx >= 0 && idx < numButtons) {
            if (button[idx] != null) {
                button[idx] = null;
            }
            button[idx] = cmd;
            buttonPressed[idx] = false;
        }
    }

    void pressButton(int idx) {
        if (idx >= 0 && idx < numButtons && button[idx] != null) {
            if (buttonPressed[idx] == false) {
                button[idx].execute();
            } else {
                button[idx].undo();
            }
            buttonPressed[idx] = !buttonPressed[idx];
        } else {
            System.out.println("no command assign " + idx);
        }
    }

}