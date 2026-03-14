public class DecoratorPattern {
    public static void main(String[] args) {
        Character mario = new Mario();
        mario = new HeightUp(mario);
        System.out.println(mario.getAbilities());

        mario = new StarPowerUp(mario);
        System.out.println(mario.getAbilities());

    }
}

abstract class Character {
    public abstract String getAbilities();

    Character() {

    }
}

class Mario extends Character {
    @Override
    public String getAbilities() {
        return "Mario";
    }
}

abstract class CharacterDecorator extends Character {
    Character ch;

    CharacterDecorator(Character ch) {
        this.ch = ch;
    }
}

class HeightUp extends CharacterDecorator {

    HeightUp(Character ch) {
        super(ch);
    }

    @Override
    public String getAbilities() {
        return ch.getAbilities() + "with Height Up";
    }
}

class StarPowerUp extends CharacterDecorator {

    StarPowerUp(Character ch) {
        super(ch);
    }

    @Override
    public String getAbilities() {
        return ch.getAbilities() + "with star Up";
    }
}
