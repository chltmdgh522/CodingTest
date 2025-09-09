package test.mentech;

public class BattleServiceImpl implements BattleService{


    @Override
    public String fight(Fight fight1, Fight fight2, String firstName) {
        StringBuilder sb = new StringBuilder();
        while(true) {
            if (fight1.name.equals(firstName)) {
                if(fight2.health - fight1.damagePerAttack >=1) {

                    fight2.health = fight2.health - fight1.damagePerAttack;
                    int point = fight2.health;
                    System.out.println(fight1.name + " attacks " + fight2.name + ";" +" now has " +point +".");

                }
                else{
                    fight2.health = fight2.health - fight1.damagePerAttack;
                    int point = fight2.health;
                    System.out.println(fight1.name + " attacks " + fight2.name + ";" +" now has " +point +"." + "and is dead. " + fight1.name + " wins.");

                    break;
                }

                firstName = fight2.name;
            }else{
                if(fight1.health - fight2.damagePerAttack >=1) {

                    int point = fight1.health - fight2.damagePerAttack;
                    System.out.println(fight2.name + " attacks " + fight1.name + ";" +" now has " +point +".");

                }
                else{
                    int point = fight1.health - fight2.damagePerAttack;
                    System.out.println(fight2.name + " attacks " + fight1.name + ";" +" now has " +point +"." + "and is dead. " + fight2.name + " wins.");
                    break;
                }
                firstName = fight1.name;
            }
        }

        return sb.toString();

        /**
         *
         * Lew attacks Harry; Harry now has 3.
         * Harry attacks Lew; Lew now has 6.
         * Lew attacks Harry; Harry now has 1.
         * Harry attacks Lew; Lew now has 2.
         * Lew attacks Harry; Harry now has -1 and is dead. Lew wins.
         */

    }
}
