public class GamePlayer {  

    // Instance Variables
    private String username; 
    private int level;
    private int health;

    // Static Variable
    private static int totalOnlinePlayers = 0;

    // Constructor
    public GamePlayer(String username) { 
        this.username = username;
        this.level = 1;
        this.health = 100;

        totalOnlinePlayers++;
        System.out.println("Player " + this.username + " joined the game.");
    }

    // Methods
    public void takeDamage(int damage) {
        if (damage > 0) {
            this.health -= damage;
            if (this.health < 0) this.health = 0;
        }
    }


    public static int getServerStatus() {
        return totalOnlinePlayers;
    }


    public int getHealth() {
        return this.health;
    }
}
