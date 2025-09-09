package test.mentech;

public class Fight {

    String name;

    int health;

    int damagePerAttack;

    public Fight(String name, int health, int damagePerAttack){
        this.name= name;
        this.health= health;
        this.damagePerAttack=  damagePerAttack;
    }

    @Override
    public String toString() {
        return "Fight{" +
                "name=" + name +
                '}';
    }
}
