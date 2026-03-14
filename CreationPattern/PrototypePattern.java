package CreationPattern;

public class PrototypePattern {
    public static void main(String[] args) {
        
        NPC alien = new NPC("Alien",30);
        NPC copyAlien = alien.clone();
        copyAlien.describe();
        alien.describe();
      }
}

interface Prototype {
    Prototype clone();
}

class NPC implements Cloneable {

    String name;
    int attack;

    NPC(String name, int attack) {
        this.name = name;
        this.attack = attack;
        System.out.println("Setting values");
    }

    NPC(NPC n) {
        this.name = n.name;
        this.attack = n.attack;
        System.out.println("Setting values Copy");
    }

    @Override
    public NPC clone() {
        return new NPC(this);
    }

   public void describe() {
        System.out.println("Name " + name + " attack " + attack);
    }

}