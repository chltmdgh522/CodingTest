package test.mentech;

public class Main {
    public static void main(String[] args) {

        BattleService battleService = new BattleServiceImpl();

        System.out.println(battleService.fight(new Fight("Lew", 10, 2), new Fight("Harry", 5, 4), "Lew"));

    }

}
