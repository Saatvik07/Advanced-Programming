import java.util.*;

public class Game {
    static void setPlayerList(TypeList<Mafia> mafiaList, TypeList<Detective> detectiveList, TypeList<Healer> healerList,
            TypeList<Commoner> commonerList, int totalNumber, int characterChoice) {
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
            Mafia m = new Mafia(UID, true);
            mafiaList.add(m);
            numMafia--;
        } else if (characterChoice == 2) {
            UID = num.nextInt(totalNumber);
            indices.add(UID);
            Detective d = new Detective(UID, true);
            detectiveList.add(d);
            numDetective--;
        } else if (characterChoice == 3) {
            UID = num.nextInt(totalNumber);
            indices.add(UID);
            Healer h = new Healer(UID, true);
            healerList.add(h);
            numHealer--;
        } else if (characterChoice == 4) {
            UID = num.nextInt(totalNumber);
            indices.add(UID);
            Commoner c = new Commoner(UID, true);
            commonerList.add(c);
            numCommoner--;
        } else {
            UID = num.nextInt(totalNumber);
            indices.add(UID);
            int randomCharacterType = num.nextInt(4);
            switch (randomCharacterType) {
                case 0:
                    Mafia m = new Mafia(UID, true);
                    mafiaList.add(m);
                    numMafia--;
                    randomCharacterType = 1;
                    break;
                case 1:
                    Detective d = new Detective(UID, true);
                    detectiveList.add(d);
                    numDetective--;
                    randomCharacterType = 2;
                    break;
                case 2:
                    Healer h = new Healer(UID, true);
                    healerList.add(h);
                    numHealer--;
                    randomCharacterType = 3;
                    break;
                case 3:
                    Commoner c = new Commoner(UID, true);
                    commonerList.add(c);
                    numCommoner--;
                    randomCharacterType = 4;
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
                    mafiaList.add(new Mafia(UID, false));
                } else if (numDetective > 0) {
                    numDetective--;
                    detectiveList.add(new Detective(UID, false));
                } else if (numHealer > 0) {
                    numHealer--;
                    healerList.add(new Healer(UID, false));
                } else if (numCommoner > 0) {
                    numCommoner--;
                    commonerList.add(new Commoner(UID, false));
                }
            }
        }
        if (characterChoice == 1) {
            System.out.print("You are a mafia. Other mafias are ");
            mafiaList.printList();
        } else if (characterChoice == 2) {
            System.out.print("You are a detective. Other detectives are ");
            detectiveList.printList();
        } else if (characterChoice == 3) {
            System.out.print("You are a healer . Other healers are ");
            healerList.printList();
        } else if (characterChoice == 4) {
            System.out.print("You are a commoner. Other commoners are ");
            commonerList.printList();
        }
        System.out.println();
    }

    public static void main(String[] args) {
        TypeList<Mafia> mafiaList = new TypeList<Mafia>();
        TypeList<Detective> detectiveList = new TypeList<Detective>();
        TypeList<Healer> healerList = new TypeList<Healer>();
        TypeList<Commoner> commonerList = new TypeList<Commoner>();
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
        setPlayerList(mafiaList, detectiveList, healerList, commonerList, totalNumber, characterChoice);
        int roundNumber = 1;
    }
}

class TypeList<Player> {
    private ArrayList<Player> typeList;

    public TypeList() {
        typeList = new ArrayList<Player>();
    }

    public void add(Player newPlayer) {
        typeList.add(newPlayer);
    }

    public Player get(int index) {
        return typeList.get(index);
    }

    public void printList() {
        for (int i = 0; i < this.typeList.size(); i++) {
            System.out.print(this.typeList.get(i).toString());
        }
    }
    // public boolean gameEnded() {
    // int mafiaLeft = 0, otherLeft = 0;
    // for (int i = 0; i < this.playerList.size(); i++) {
    // if (this.playerList.get(i).equals(new Mafia())) {
    // mafiaLeft++;
    // } else {
    // otherLeft++;
    // }
    // }
    // if (mafiaLeft == 0) {
    // System.out.println("Game Over");
    // System.out.println("The Mafias have lost");

    // }

    // }

    // public ArrayList<Player> getList() {
    // return this.typeList;
    // }

    public int size() {
        return typeList.size();
    }
}

class Player {
    protected int hitPoints;
    protected int UID;
    protected boolean alive;
    protected boolean user;

    public Player() {

    }

    public Player(int UID, boolean user) {
        this.alive = true;
        this.user = user;
        this.UID = UID;
    }

    protected int voteOut(TypeList<Player> playerList) {
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

    @Override
    public String toString() {
        if (!this.user)
            return "Player" + this.UID + " ";
        return "Player" + this.UID + "[User] ";
    }

}

class Mafia extends Player {
    public Mafia(int UID, boolean user) {
        super(UID, user);
        this.hitPoints = 2500;
    }

    public Mafia() {
        super();
    }

}

class Detective extends Player {
    public Detective(int UID, boolean user) {
        super(UID, user);
        this.hitPoints = 800;
    }

    public Detective() {
        super();
    }
}

class Healer extends Player {
    public Healer(int UID, boolean user) {
        super(UID, user);
        this.hitPoints = 800;
    }

    public Healer() {
        super();
    }

}

class Commoner extends Player {
    public Commoner(int UID, boolean user) {
        super(UID, user);
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