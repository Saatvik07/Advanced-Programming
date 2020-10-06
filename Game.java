import java.util.*;

public class Game {
    static int setPlayerList(TypeList<Mafia> mafiaList, TypeList<Detective> detectiveList, TypeList<Healer> healerList,
            TypeList<Commoner> commonerList, ArrayList<Player> playerList, int totalNumber, int characterChoice) {
        ArrayList<Integer> indices = new ArrayList<Integer>();
        Random num = new Random();
        int UID = 0;
        int numMafia = totalNumber / 5;
        int numDetective = totalNumber / 5;
        int numHealer = totalNumber / 10;
        int numCommoner = totalNumber - (2 * (totalNumber / 5) + numHealer);
        if (characterChoice == 1) {
            UID = num.nextInt(totalNumber);
            indices.add(UID);
            Mafia m = new Mafia(UID, true);
            mafiaList.add(m);
            playerList.add(m);
            numMafia--;
        } else if (characterChoice == 2) {
            UID = num.nextInt(totalNumber);
            indices.add(UID);
            Detective d = new Detective(UID, true);
            detectiveList.add(d);
            playerList.add(d);
            numDetective--;
        } else if (characterChoice == 3) {
            UID = num.nextInt(totalNumber);
            indices.add(UID);
            Healer h = new Healer(UID, true);
            healerList.add(h);
            playerList.add(h);
            numHealer--;
        } else if (characterChoice == 4) {
            UID = num.nextInt(totalNumber);
            indices.add(UID);
            Commoner c = new Commoner(UID, true);
            commonerList.add(c);
            playerList.add(c);
            numCommoner--;
        } else {
            UID = num.nextInt(totalNumber);
            indices.add(UID);
            int randomCharacterType = num.nextInt(4);
            switch (randomCharacterType) {
                case 0:
                    Mafia m = new Mafia(UID, true);
                    mafiaList.add(m);
                    playerList.add(m);
                    numMafia--;
                    characterChoice = 1;
                    break;
                case 1:
                    Detective d = new Detective(UID, true);
                    detectiveList.add(d);
                    playerList.add(d);
                    numDetective--;
                    characterChoice = 2;
                    break;
                case 2:
                    Healer h = new Healer(UID, true);
                    healerList.add(h);
                    playerList.add(h);
                    numHealer--;
                    characterChoice = 3;
                    break;
                case 3:
                    Commoner c = new Commoner(UID, true);
                    commonerList.add(c);
                    playerList.add(c);
                    numCommoner--;
                    characterChoice = 4;
                    break;
                default:
                    break;

            }
        }
        System.out.println("You are Player" + indices.get(0));
        while (indices.size() < totalNumber) {
            UID = num.nextInt(totalNumber);
            if (!indices.contains(UID)) {
                indices.add(UID);
                if (numMafia > 0) {
                    numMafia--;
                    Mafia m = new Mafia(UID, false);
                    mafiaList.add(m);
                    playerList.add(m);
                } else if (numDetective > 0) {
                    numDetective--;
                    Detective d = new Detective(UID, false);
                    detectiveList.add(d);
                    playerList.add(d);
                } else if (numHealer > 0) {
                    numHealer--;
                    Healer h = new Healer(UID, false);
                    healerList.add(h);
                    playerList.add(h);
                } else if (numCommoner > 0) {
                    numCommoner--;
                    Commoner c = new Commoner(UID, false);
                    commonerList.add(c);
                    playerList.add(c);
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
        Collections.sort(playerList, new PlayerUIDComparator());
        return characterChoice;
    }

    static boolean gameEnded(ArrayList<Player> playerList, TypeList<Mafia> mafiaList, TypeList<Detective> detectiveList,
            TypeList<Healer> healerList, TypeList<Commoner> commonerList) {
        int mafiaLeft = 0, othersLeft = 0;
        for (int i = 0; i < playerList.size(); i++) {
            if (playerList.get(i).equals(new Mafia()) && playerList.get(i).getAlive()) {
                mafiaLeft++;
            } else if (playerList.get(i).getAlive()) {
                othersLeft++;
            }
        }
        if (mafiaLeft == 0 || mafiaLeft == othersLeft) {
            if (mafiaLeft == 0) {
                System.out.println("The mafias have lost");

            } else {
                System.out.println("The mafias have won");
            }
            mafiaList.printList();
            System.out.println("were the mafias");
            detectiveList.printList();
            System.out.println("were the detectives");
            healerList.printList();
            System.out.println("were healers");
            commonerList.printList();
            System.out.println("were commoners");
            return true;
        }
        return false;
    }

    static void displayRemaining(ArrayList<Player> playerList) {
        ArrayList<String> playersLeft = new ArrayList<String>();
        int numberLeft = 0;
        for (int i = 0; i < playerList.size(); i++) {
            if (playerList.get(i).getAlive()) {
                numberLeft++;
                playersLeft.add(playerList.get(i).toString());
            }
        }
        System.out.println(numberLeft + " players are remaining:");
        for (int i = 0; i < playersLeft.size(); i++) {
            System.out.print(playersLeft.get(i));
        }
    }

    public static void main(String[] args) {
        TypeList<Mafia> mafiaList = new TypeList<Mafia>();
        TypeList<Detective> detectiveList = new TypeList<Detective>();
        TypeList<Healer> healerList = new TypeList<Healer>();
        TypeList<Commoner> commonerList = new TypeList<Commoner>();
        ArrayList<Player> playerList = new ArrayList<Player>();
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
        characterChoice = setPlayerList(mafiaList, detectiveList, healerList, commonerList, playerList, totalNumber,
                characterChoice);
        int roundNumber = 1;
        while (!gameEnded(playerList, mafiaList, detectiveList, healerList, commonerList)) {
            System.out.println("Round " + roundNumber + ":");
            displayRemaining(playerList);
            if(characterChoice)
        }
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

    public void special() {

    }

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

    public boolean getAlive() {
        return this.alive;
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