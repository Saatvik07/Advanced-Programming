
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
            Collections.sort(playerList, new PlayerVotesComparator());
            for (int i = 0; i < playerList.size(); i++) {
                if (playerList.get(i).equals(new Mafia())) {
                    mafiaList.add((Mafia) playerList.get(i));
                } else if (playerList.get(i).equals(new Detective())) {
                    detectiveList.add((Detective) playerList.get(i));
                } else if (playerList.get(i).equals(new Healer())) {
                    healerList.add((Healer) playerList.get(i));
                } else if (playerList.get(i).equals(new Commoner())) {
                    commonerList.add((Commoner) playerList.get(i));
                }
            }
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
        Collections.sort(playerList, new PlayerUIDComparator());
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
        System.out.println();
    }

    static boolean checkTargetValid(int target, TypeList<Mafia> mafiaList, ArrayList<Player> playerList) {
        if (target == -1) {
            return true;
        } else {
            for (int i = 0; i < mafiaList.size(); i++) {
                if (mafiaList.get(i).getUID() == target) {
                    System.out.println("You cannot choose a mafia for killing");
                    return true;
                }
            }
            for (int i = 0; i < playerList.size(); i++) {
                if (playerList.get(i).getUID() == target && !playerList.get(i).getAlive()) {
                    System.out.println("You cannot choose a dead person to kill");
                    return true;
                }
            }
            return false;
        }
    }

    static int dealDamageAuxiliary(int HPDecrease, int residue, Mafia mafiaObj) {
        if (mafiaObj.getHP() >= HPDecrease) {
            mafiaObj.setHP(mafiaObj.getHP() - (HPDecrease + residue));
            return 0;
        } else {
            int residueHP = HPDecrease - mafiaObj.getHP();
            mafiaObj.setHP(0);
            return residueHP;
        }
    }

    static void dealDamage(int HPTarget, Player Target, TypeList<Mafia> mafiaList) {
        int HPMafia = 0;
        for (int i = 0; i < mafiaList.size(); i++) {
            HPMafia += mafiaList.get(i).getHP();
        }
        if (HPMafia >= Target.getHP()) {
            int residue = 0;
            for (int i = 0; i < mafiaList.size(); i++) {
                residue = dealDamageAuxiliary(HPTarget / mafiaList.size(), residue, (Mafia) mafiaList.get(i));
            }
            Target.setHP(0);
        } else {
            for (int i = 0; i < mafiaList.size(); i++) {
                mafiaList.get(i).setHP(0);
            }
            Target.setHP(Target.getHP() - HPMafia);
        }

    }

    static void removePlayer(ArrayList<Player> playerList, TypeList<Mafia> mafiaList, TypeList<Detective> detectiveList,
            TypeList<Healer> healerList, TypeList<Commoner> commonerList, int targetPosition) {
        int removePosition = -1;
        if (targetPosition == -1) {
            for (int i = 0; i < playerList.size(); i++) {
                if (playerList.get(i).getHP() == 0 && playerList.get(i).getAlive()) {
                    removePosition = i;
                    System.out.println(playerList.get(i).toString() + "has died");
                }
            }
            if (removePosition == -1) {
                System.out.println("No one has died");
            } else {
                Player toBeVotedOut = playerList.get(removePosition);
                if (toBeVotedOut.equals(new Mafia())) {
                    mafiaList.remove((Mafia) toBeVotedOut);
                } else if (toBeVotedOut.equals(new Detective())) {
                    detectiveList.remove((Detective) toBeVotedOut);
                } else if (toBeVotedOut.equals(new Healer())) {
                    healerList.remove((Healer) toBeVotedOut);
                } else if (toBeVotedOut.equals(new Healer())) {
                    commonerList.remove((Commoner) toBeVotedOut);
                }
                playerList.get(removePosition).setAlive();
            }

        } else {
            System.out.println(playerList.get(targetPosition).toString() + " has been voted out");
            Player toBeVotedOut = playerList.get(targetPosition);
            if (toBeVotedOut.equals(new Mafia())) {
                mafiaList.remove((Mafia) toBeVotedOut);
            } else if (toBeVotedOut.equals(new Detective())) {
                detectiveList.remove((Detective) toBeVotedOut);
            } else if (toBeVotedOut.equals(new Healer())) {
                healerList.remove((Healer) toBeVotedOut);
            } else if (toBeVotedOut.equals(new Healer())) {
                commonerList.remove((Commoner) toBeVotedOut);
            }
            playerList.get(targetPosition).setAlive();
        }
    }

    static boolean userAlive(ArrayList<Player> playerList) {
        for (int i = 0; i < playerList.size(); i++) {
            if (playerList.get(i).getUser() && playerList.get(i).getAlive()) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        TypeList<Mafia> mafiaList = new TypeList<Mafia>();
        TypeList<Detective> detectiveList = new TypeList<Detective>();
        TypeList<Healer> healerList = new TypeList<Healer>();
        TypeList<Commoner> commonerList = new TypeList<Commoner>();
        ArrayList<Player> playerList = new ArrayList<Player>();
        ArrayList<Integer> playersTested = new ArrayList<Integer>();
        Scanner input = new Scanner(System.in);
        System.out.println("Welcome to Mafia");
        System.out.println("Enter the number of players");
        Player votedPlayer = new Player();
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
            ArrayList<Integer> selectedPlayer;
            System.out.println("Round " + roundNumber + ":");
            roundNumber++;
            displayRemaining(playerList);
            if (characterChoice == 1) {
                int target = -1;
                if (mafiaList.size() != 0 && userAlive(playerList)) {
                    while (checkTargetValid(target, mafiaList, playerList)) {
                        System.out.println("Choose a target: ");
                        target = input.nextInt();

                    }
                    for (int i = 0; i < playerList.size(); i++) {
                        if (playerList.get(i).getUID() == target && playerList.get(i).getAlive()) {
                            dealDamage(playerList.get(i).getHP(), playerList.get(i), mafiaList);
                        }
                    }
                } else if (mafiaList.size() != 0) {
                    selectedPlayer = mafiaList.specialAutomatic(mafiaList, detectiveList, healerList, commonerList);
                    if (selectedPlayer.get(0) == 0) {
                        Detective selectedDetective = detectiveList.get(selectedPlayer.get(1));
                        dealDamage(selectedDetective.getHP(), selectedDetective, mafiaList);
                    } else if (selectedPlayer.get(0) == 1) {
                        Healer selectedHealer = healerList.get(selectedPlayer.get(1));
                        dealDamage(selectedHealer.getHP(), selectedHealer, mafiaList);

                    } else if (selectedPlayer.get(0) == 2) {
                        Commoner selectedCommoner = commonerList.get(selectedPlayer.get(1));
                        dealDamage(selectedCommoner.getHP(), selectedCommoner, mafiaList);
                    }
                    System.out.println("Mafias have chosen their target");
                }

                int playerToTestUID = -1;
                if (detectiveList.size() != 0) {
                    while (playersTested.contains(playerToTestUID) || playerToTestUID == -1) {
                        selectedPlayer = detectiveList.specialAutomatic(mafiaList, detectiveList, healerList,
                                commonerList);
                        if (selectedPlayer.get(0) == 0) {
                            Mafia selectedMafia = mafiaList.get(selectedPlayer.get(1));
                            playerToTestUID = selectedMafia.getUID();
                            votedPlayer = selectedMafia;
                        } else if (selectedPlayer.get(0) == 1) {
                            Healer selectedHealer = healerList.get(selectedPlayer.get(1));
                            playerToTestUID = selectedHealer.getUID();
                        } else if (selectedPlayer.get(0) == 2) {
                            Commoner selectedCommoner = commonerList.get(selectedPlayer.get(1));
                            playerToTestUID = selectedCommoner.getUID();
                        }
                    }
                    playersTested.add(playerToTestUID);
                }
                System.out.println("Detectives have chosen a player to test");
                if (healerList.size() != 0) {
                    selectedPlayer = healerList.specialAutomatic(mafiaList, detectiveList, healerList, commonerList);
                    if (selectedPlayer.get(0) == 0) {
                        Detective selectedDetective = detectiveList.get(selectedPlayer.get(1));
                        selectedDetective.setHP(selectedDetective.getHP() + 500);
                    } else if (selectedPlayer.get(0) == 1) {
                        Mafia selectedMafia = mafiaList.get(selectedPlayer.get(1));
                        selectedMafia.setHP(selectedMafia.getHP() + 500);
                    } else if (selectedPlayer.get(0) == 2) {
                        Healer selectedHealer = healerList.get(selectedPlayer.get(1));
                        selectedHealer.setHP(selectedHealer.getHP() + 500);
                    } else if (selectedPlayer.get(0) == 3) {
                        Commoner selectedCommoner = commonerList.get(selectedPlayer.get(1));
                        selectedCommoner.setHP(selectedCommoner.getHP() + 500);
                    }
                }
                System.out.println("Healers have chosen a player to heal");
                removePlayer(playerList, mafiaList, detectiveList, healerList, commonerList, -1);
                if (gameEnded(playerList, mafiaList, detectiveList, healerList, commonerList)) {
                    break;
                }
                if (votedPlayer.equals(new Mafia())) {
                    mafiaList.remove((Mafia) votedPlayer);
                    for (int i = 0; i < playerList.size(); i++) {
                        if (playerList.get(i).getUID() == votedPlayer.getUID()) {
                            playerList.get(i).setAlive();
                            System.out.println(playerList.get(i).toString() + "has been voted out");
                        }
                    }
                } else {
                    while (true) {
                        for (int i = 0; i < playerList.size(); i++) {
                            if (!playerList.get(i).user) {
                                int individualVote = playerList.get(i).voteOut(playerList.size(), playerList);
                                playerList.get(individualVote).setVote();
                            } else {
                                boolean exists = false;
                                while (true) {
                                    System.out.println("Enter the player you want to vote: ");
                                    int userVotedPlayer = input.nextInt();
                                    for (int j = 0; j < playerList.size(); j++) {
                                        if (playerList.get(j).getUID() == userVotedPlayer) {
                                            playerList.get(j).setVote();
                                            exists = true;
                                        }
                                    }
                                    if (exists)
                                        break;
                                    else {
                                        System.out.println("The player you voted does not exist or is already dead");
                                    }
                                }
                            }
                        }
                        Collections.sort(playerList, new PlayerVotesComparator());
                        if (playerList.get(playerList.size() - 1) != playerList.get(playerList.size() - 2)) {
                            removePlayer(playerList, mafiaList, detectiveList, healerList, commonerList,
                                    playerList.size() - 1);
                            break;
                        } else {
                            System.out.println("The last voting led to a draw, voting again.....");
                        }
                    }

                }

            } else if (characterChoice == 2) {
                selectedPlayer = mafiaList.specialAutomatic(mafiaList, detectiveList, healerList, commonerList);
                if (selectedPlayer.get(0) == 0) {
                    Detective selectedDetective = detectiveList.get(selectedPlayer.get(1));
                    dealDamage(selectedDetective.getHP(), selectedDetective, mafiaList);
                } else if (selectedPlayer.get(0) == 1) {
                    Healer selectedHealer = healerList.get(selectedPlayer.get(1));
                    dealDamage(selectedHealer.getHP(), selectedHealer, mafiaList);
                } else if (selectedPlayer.get(0) == 2) {
                    Commoner selectedCommoner = commonerList.get(selectedPlayer.get(1));
                    dealDamage(selectedCommoner.getHP(), selectedCommoner, mafiaList);
                }

            }
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

    public void remove(Player playerToRemove) {
        this.typeList.remove(playerToRemove);
    }

    public ArrayList<Integer> specialAutomatic(TypeList<Mafia> mafiaList, TypeList<Detective> detectiveList,
            TypeList<Healer> healerList, TypeList<Commoner> commonerList) {
        Random r = new Random();

        if (this.typeList.get(0) instanceof Mafia) {
            while (true) {
                ArrayList<Integer> selected = new ArrayList<Integer>();
                int randomTypeList = r.nextInt(3);
                int randomPlayerID;
                selected.add(randomTypeList);
                if (randomTypeList == 0 && detectiveList.size() != 0) {
                    randomPlayerID = r.nextInt(detectiveList.size());
                    selected.add(randomPlayerID);
                    return selected;
                } else if (randomTypeList == 1 && healerList.size() != 0) {
                    randomPlayerID = r.nextInt(healerList.size());
                    selected.add(randomPlayerID);
                    return selected;
                } else if (randomTypeList == 2 && commonerList.size() != 0) {
                    randomPlayerID = r.nextInt(commonerList.size());
                    selected.add(randomPlayerID);
                    return selected;
                }
            }
        } else if (this.typeList.get(0) instanceof Detective) {
            while (true) {
                ArrayList<Integer> selected = new ArrayList<Integer>();
                int randomTypeList = r.nextInt(3);
                int randomPlayerID;
                selected.add(randomTypeList);
                if (randomTypeList == 0 && mafiaList.size() != 0) {
                    randomPlayerID = r.nextInt(mafiaList.size());
                    selected.add(randomPlayerID);
                    return selected;
                } else if (randomTypeList == 1 && healerList.size() != 0) {
                    randomPlayerID = r.nextInt(healerList.size());
                    selected.add(randomPlayerID);
                    return selected;
                } else if (randomTypeList == 2 && commonerList.size() != 0) {
                    randomPlayerID = r.nextInt(commonerList.size());
                    selected.add(randomPlayerID);
                    return selected;
                }
            }
        } else if (this.typeList.get(0) instanceof Healer) {
            while (true) {
                ArrayList<Integer> selected = new ArrayList<Integer>();
                int randomTypeList = r.nextInt(4);
                int randomPlayerID;
                selected.add(randomTypeList);
                if (randomTypeList == 0 && detectiveList.size() != 0) {
                    randomPlayerID = r.nextInt(detectiveList.size());
                    selected.add(randomPlayerID);
                    return selected;
                } else if (randomTypeList == 1 && mafiaList.size() != 0) {
                    randomPlayerID = r.nextInt(mafiaList.size());
                    selected.add(randomPlayerID);
                    return selected;
                } else if (randomTypeList == 2 && healerList.size() != 0) {
                    randomPlayerID = r.nextInt(healerList.size());
                    selected.add(randomPlayerID);
                    return selected;
                } else if (randomTypeList == 3 && commonerList.size() != 0) {
                    randomPlayerID = r.nextInt(commonerList.size());
                    selected.add(randomPlayerID);
                    return selected;
                }
            }
        }
        return new ArrayList<>();
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
    protected int votes;

    public Player() {

    }

    public Player(int UID, boolean user) {
        this.votes = 0;
        this.alive = true;
        this.user = user;
        this.UID = UID;
    }

    protected int voteOut(int size, ArrayList<Player> playerList) {
        int votePlayer = -1;
        Random num = new Random();
        while (votePlayer == this.UID || votePlayer == -1 || !playerList.get(votePlayer).getAlive()) {
            votePlayer = num.nextInt(size);
        }
        return votePlayer;
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

    public boolean getUser() {
        return this.user;
    }

    public void setAlive() {
        this.alive = false;
    }

    public void setHP(int HP) {
        if (HP < 0) {
            this.hitPoints = 0;
        } else {
            this.hitPoints = HP;
        }

    }

    public int getHP() {
        return this.hitPoints;
    }

    public void setVote() {
        this.votes += 1;
    }

    public int getVotes() {
        return votes;
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

class PlayerVotesComparator implements Comparator<Player> {
    @Override
    public int compare(Player p1, Player p2) {
        return p1.getVotes() - p2.getVotes();
    }
}