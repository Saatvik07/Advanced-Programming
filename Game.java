import java.util.*;

public class Game {
    static void setPlayerList(PlayerList<Player> listObject, int totalNumber, int characterChoice) {
        ArrayList<Integer> indices = new ArrayList<Integer>();
        Random num = new Random();
        int userUID, UID = 0;
        int numMafia = totalNumber / 5;
        int numDetective = totalNumber / 5;
        int numHealer = totalNumber / 10;
        int numCommoner = totalNumber - (2 * (totalNumber / 5) + numHealer);
        if (characterChoice == 1) {
            UID = num.nextInt(totalNumber);
            indices.add(UID);
            Mafia m = new Mafia(UID);
            listObject.add(m);
            numMafia--;
        } else if (characterChoice == 2) {
            UID = num.nextInt(totalNumber);
            indices.add(UID);
            Detective d = new Detective(UID);
            listObject.add(d);
            numDetective--;
        } else if (characterChoice == 3) {
            UID = num.nextInt(totalNumber);
            indices.add(UID);
            Healer h = new Healer(UID);
            listObject.add(h);
            numHealer--;
        } else if (characterChoice == 4) {
            UID = num.nextInt(totalNumber);
            indices.add(UID);
            Commoner c = new Commoner(UID);
            listObject.add(c);
            numCommoner--;
        } else {
            UID = num.nextInt(totalNumber);
            indices.add(UID);
            int randomCharacterType = num.nextInt(4);
            switch (randomCharacterType) {
                case 0:
                    Mafia m = new Mafia(UID);
                    listObject.add(m);
                    numMafia--;
                    break;
                case 1:
                    Detective d = new Detective(UID);
                    listObject.add(d);
                    numDetective--;
                    break;
                case 2:
                    Healer h = new Healer(UID);
                    listObject.add(h);
                    numHealer--;
                    break;
                case 3:
                    Commoner c = new Commoner(UID);
                    listObject.add(c);
                    numMafia--;
                    break;
                default:
                    break;

            }
        }
        userUID = UID;
        System.out.println("You are Player" + indices.get(0));
        while (indices.size() < totalNumber) {
            UID = num.nextInt(totalNumber);
            if (!indices.contains(UID)) {
                indices.add(UID);
                if (numMafia > 0) {
                    numMafia--;
                    listObject.add(new Mafia(UID));
                } else if (numDetective > 0) {
                    numDetective--;
                    listObject.add(new Detective(UID));
                } else if (numHealer > 0) {
                    numHealer--;
                    listObject.add(new Healer(UID));
                } else if (numCommoner > 0) {
                    numCommoner--;
                    listObject.add(new Commoner(UID));
                }
            }
        }
        Collections.sort(listObject.getList(), new PlayerUIDComparator());
        if (characterChoice == 1) {
            System.out.print("You are a mafia. Other mafias are");
            for (int i = 0; i < listObject.size(); i++) {
                if (listObject.get(i).equals(new Mafia()) && userUID != listObject.get(i).getUID()) {
                    System.out.print("Player" + listObject.get(i).UID + " ");
                }
            }
        } else if (characterChoice == 2) {
            System.out.print("You are a detective. Other detectives are");
            for (int i = 0; i < listObject.size(); i++) {
                if (listObject.get(i).equals(new Detective()) && userUID != listObject.get(i).getUID()) {
                    System.out.print("Player" + listObject.get(i).UID + " ");
                }
            }
        } else if (characterChoice == 3) {
            System.out.print("You are a healer . Other healers are");
            for (int i = 0; i < listObject.size(); i++) {
                if (listObject.get(i).equals(new Healer()) && userUID != listObject.get(i).getUID()) {
                    System.out.print("Player" + listObject.get(i).UID + " ");
                }
            }
        } else if (characterChoice == 4) {
            System.out.print("You are a commoner. Other commoners are");
            for (int i = 0; i < listObject.size(); i++) {
                if (listObject.get(i).equals(new Commoner()) && userUID != listObject.get(i).getUID()) {
                    System.out.print("Player" + listObject.get(i).UID + " ");
                }
            }
        }
        System.out.println();
    }

    public static void main(String[] args) {
        PlayerList<Player> listObject = new PlayerList<Player>();
        Scanner input = new Scanner(System.in);
        System.out.println("Welcome to Mafia");
        System.out.println("Enter the number of players");
        int totalNumber = input.nextInt();
        while (totalNumber < 6) {
            System.out.println("The number of players cannot be less than 6");
            totalNumber = input.nextInt();
        }
        System.out.println("Choose a character");
        System.out.println("1) Mafia");
        System.out.println("2) Detective");
        System.out.println("3) Healer");
        System.out.println("4) Commoner");
        System.out.println("5) Assign Randomly");
        int characterChoice = input.nextInt();
        setPlayerList(listObject, totalNumber, characterChoice);
    }
}

class PlayerList<Player> {
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

    public void sortByUID() {

    }

    public ArrayList<Player> getList() {
        return this.playerList;
    }

    public int size() {
        return playerList.size();
    }
}

class Player {
    protected int hitPoints;
    protected int UID;
    protected boolean alive;

    public Player() {

    }

    public Player(int UID) {
        this.alive = true;
        this.UID = UID;
    }

    protected int voteOut(PlayerList<Player> playerList) {
        ArrayList<Integer> UIDLeft = new ArrayList<Integer>();
        for (int i = 0; i < playerList.size(); i++) {
            if (playerList.get(i).alive) {
                UIDLeft.add(playerList.get(i).UID);
            }
        }
        Random num = new Random();
        int randomIndex = num.nextInt(UIDLeft.size());
        return randomIndex;
    }

    protected void showDetails() {
        System.out.println("Player" + this.UID);
        System.out.println(this.getClass());
        System.out.println();
    }

    public int getUID() {
        return this.UID;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj != null && getClass() == obj.getClass()) {
            return true;
        } else {
            return false;
        }
    }

}

class Mafia extends Player {
    public Mafia(int UID) {
        super(UID);
        this.hitPoints = 2500;
    }

    public Mafia() {
        super();
    }

}

class Detective extends Player {
    public Detective(int UID) {
        super(UID);
        this.hitPoints = 800;
    }

    public Detective() {
        super();
    }
}

class Healer extends Player {
    public Healer(int UID) {
        super(UID);
        this.hitPoints = 800;
    }

    public Healer() {
        super();
    }

}

class Commoner extends Player {
    public Commoner(int UID) {
        super(UID);
        this.hitPoints = 1000;
    }

    public Commoner() {
        super();
    }
}

class PlayerUIDComparator implements Comparator<Player> {
    @Override
    public int compare(Player p1, Player p2) {
        return p1.getUID() - p2.getUID();
    }
}