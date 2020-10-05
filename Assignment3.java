import java.util.*;

public class Assignment3 {
    public static void main(String[] args) {

    }
}

public class PlayerList<Player> {
    private ArrayList<Player> playerList;

    public PlayerList() {
        playerList = new ArrayList<Player>();
    }

    public void add(Player newPlayer) {
        playerList.add(newPlayer);
    }

    public Player get(int index) {
        return playerList.get(index);
    }
}

public class Player {
    protected int hitPoints;
    protected String type;
    protected int UID;
    protected boolean alive;

    public Player(int UID) {
        this.alive = true;
        this.UID = UID;
    }

    protected void voteOut() {

    }

}

public class Mafia extends Player {

}

public class Detective extends Player {

}

public class Healer extends Player {

}

public class Commoner extends Player {

}
