public class Main {
    public static void main(String[] args) {


        System.out.println("Server Users: " + GamePlayer.getServerStatus()); // Output: 0


        GamePlayer p1 = new GamePlayer("Nikan");
        GamePlayer p2 = new GamePlayer("EnemyBot");

        p1.takeDamage(20);

        System.out.println("Nikan HP: " + p1.getHealth());
        System.out.println("Enemy HP: " + p2.getHealth());
        System.out.println("Total Online: " + GamePlayer.getServerStatus());
    }
}