import java.util.*;

public class Assignment1 {
    public static ArrayList<Patient> PatientArray = new ArrayList<Patient>();
    public static ArrayList<HCI> HCIArray = new ArrayList<HCI>();

    static void removeAllAdmitted() {
        System.out.println("Account ID removed of admitted patients");
        for (int i = 0; i < Assignment1.HCIArray.size(); i++) {
            HCIArray.get(i).removeAdmittedPatients();
        }
    }

    static void removeAllHCI() {
        System.out.println("Accounts removed of institute whose admission is closed");
        for (int i = 0; i < HCIArray.size(); i++) {
            if (!HCIArray.get(i).getStatus()) {
                System.out.println(HCIArray.get(i).getName());
                HCIArray.remove(HCIArray.get(i));
            }
        }
    }

    static void openHCI() {
        int count = 0;
        for (int i = 0; i < HCIArray.size(); i++) {
            if (HCIArray.get(i).getStatus()) {
                count++;
            }
        }
        System.out.println(count + " institutes are admitting patients currently");
    }

    static void getUnadmittedPatients() {
        int count = 0;
        for (int i = 0; i < PatientArray.size(); i++) {
            if (!PatientArray.get(i).getAdmissionStatus()) {
                count++;
            }
        }
        System.out.println(count + " patients");
    }

    static boolean notEmpty() {
        int count = 0;
        for (int i = 0; i < PatientArray.size(); i++) {
            if (PatientArray.get(i).getAdmissionStatus()) {
                count++;
            }
        }
        if (count == PatientArray.size()) {
            return false;
        }
        return true;
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int numberPatients = input.nextInt();
        input.nextLine();
        for (int i = 0; i < numberPatients; i++) {
            String[] vals = input.nextLine().split(" ");
            Patient newPatient = new Patient(vals[0], Integer.parseInt(vals[3]), Integer.parseInt(vals[2]),
                    Float.parseFloat(vals[1]));
            PatientArray.add(newPatient);
        }
        // for (int i = 0; i < PatientArray.size(); i++) {
        // System.out.println(PatientArray.get(i).getUID() + " " +
        // PatientArray.get(i).getName());
        // }
        while (notEmpty()) {
            String[] values = input.nextLine().split(" ");
            int choice = Integer.parseInt(values[0]);
            int id = 1;
            String name = "";
            if (values.length > 1) {
                try {
                    id = Integer.parseInt(values[1]);
                } catch (Exception e) {
                    name = values[1];
                }
            }
            switch (choice) {
                case 1:
                    String instituteName = input.nextLine();
                    System.out.println("Temperature Criteria - ");
                    float bodTemp = input.nextFloat();
                    System.out.println("Oxygen levels - ");
                    int oxyLevel = input.nextInt();
                    System.out.println("Number of Available beds - ");
                    int beds = input.nextInt();
                    input.nextLine();
                    HCI newInstitute = new HCI(instituteName, bodTemp, oxyLevel, beds);
                    HCIArray.add(newInstitute);
                    break;
                case 2:
                    removeAllAdmitted();
                    break;
                case 3:
                    removeAllHCI();
                    break;
                case 4:
                    getUnadmittedPatients();
                    break;
                case 5:
                    openHCI();
                    break;
                case 6:
                    for (int i = 0; i < HCIArray.size(); i++) {
                        if (HCIArray.get(i).getName().equals(name)) {
                            HCIArray.get(i).getHCIDetails();
                        }
                    }
                    break;
                case 7:
                    for (int i = 0; i < PatientArray.size(); i++) {
                        if (PatientArray.get(i).getUID() == id) {
                            if (!PatientArray.get(i).getDeleted()) {
                                PatientArray.get(i).getDetails();
                            } else {
                                System.out.println("Sorry this record was removed");
                            }
                        }
                    }
                    break;
                case 8:
                    for (int i = 0; i < PatientArray.size(); i++) {
                        System.out.println(PatientArray.get(i).getUID() + " " + PatientArray.get(i).getName());
                    }
                    break;
                case 9:
                    for (int i = 0; i < HCIArray.size(); i++) {
                        if (HCIArray.get(i).getName().equals(name)) {
                            ArrayList<Patient> admittedPatients = HCIArray.get(i).getPatients();
                            for (int j = 0; j < admittedPatients.size(); j++) {
                                System.out.println(admittedPatients.get(j).getName() + ", recovery time is "
                                        + admittedPatients.get(j).getRecoveryDays() + " days");
                            }
                        }
                    }
                    break;
                default:
                    break;
            }

        }
    }
}

class HCI extends Assignment1 {
    static int count = 1;
    private int UID;
    private String name;
    private float maxBodTemp;
    private int minOxyLevel;
    private int availableBeds;
    // private ArrayList<Integer> recoveryArray = new ArrayList<Integer>();
    private ArrayList<Patient> admittedPatients = new ArrayList<Patient>();
    private boolean open;

    private void admitPatients() {
        for (int i = 0; i < PatientArray.size(); i++) {
            if (PatientArray.get(i).getOxyLevel() >= this.minOxyLevel && this.availableBeds > 0
                    && !PatientArray.get(i).getAdmissionStatus()) {
                this.availableBeds--;
                Patient addedPatient = PatientArray.get(i);
                Scanner input = new Scanner(System.in);
                System.out.print("Recovery days for admitted patient ID" + addedPatient.getUID() + "-");
                addedPatient.setRecoveryDays(input.nextInt());
                addedPatient.setInstitute(this.name);
                this.admittedPatients.add(addedPatient);
            }
        }
        if (this.availableBeds > 0) {
            for (int i = 0; i < PatientArray.size(); i++) {
                if (PatientArray.get(i).getBodTemp() <= this.maxBodTemp && this.availableBeds > 0
                        && !PatientArray.get(i).getAdmissionStatus()) {
                    this.availableBeds--;
                    Patient addedPatient = PatientArray.remove(i);
                    Scanner input = new Scanner(System.in);
                    System.out.print("Recovery days for admitted patient ID" + addedPatient.getUID() + "-");
                    addedPatient.setRecoveryDays(input.nextInt());
                    addedPatient.setInstitute(this.name);
                    this.admittedPatients.add(addedPatient);
                }
            }
        }
        if (this.availableBeds == 0) {
            this.open = false;
        }
    }

    public HCI(String name, float bodTemp, int oxyLevel, int available) {
        this.name = name;
        this.UID = count;
        count++;
        this.maxBodTemp = bodTemp;
        this.minOxyLevel = oxyLevel;
        this.availableBeds = available;
        this.open = true;
        this.getHCIDetails();
        this.admitPatients();
    }

    public void removeAdmittedPatients() {
        for (int i = 0; i < this.admittedPatients.size(); i++) {
            this.admittedPatients.get(i).setDeleted();
            System.out.println(this.admittedPatients.get(i).getUID());
        }
    }

    public void getHCIDetails() {
        try {
            System.out.println(this.name);
            System.out.println("Temperature should be <= " + this.maxBodTemp);
            System.out.println("Oxygen levels should be >= " + this.minOxyLevel);
            System.out.println("Number of available beds- " + this.availableBeds);
            String status = this.open ? "OPEN" : "CLOSED";
            System.out.println("Admission Status- " + status);
        } catch (Exception e) {
            System.out.println("Not a valid UID");
        }
    }

    public String getName() {
        return this.name;
    }

    public boolean getStatus() {
        return this.open;
    }

    public ArrayList<Patient> getPatients() {
        return this.admittedPatients;
    }
}

class Patient {
    static int count = 1;
    private int UID;
    private String name;
    private int age;
    private int oxyLevel;
    private float bodTemp;
    private int recoveryDays = 0;
    private String instituteName = "";
    private boolean admitted = false;
    private boolean deleted = false;

    public Patient(String name, int age, int oxyLevel, float bodTemp) {
        this.UID = count;
        this.name = name;
        this.age = age;
        this.oxyLevel = oxyLevel;
        this.bodTemp = bodTemp;
        count++;
    }

    public void getDetails() {
        System.out.println(this.name);
        System.out.println("Temperature is " + this.bodTemp);
        System.out.println("Oxygen level is " + this.oxyLevel);
        if (this.admitted) {
            System.out.println("Admission Status - Admitted");
            System.out.println("Admitting Institute - " + this.instituteName);
        } else {
            System.out.println("Admission Status - Not Admitted");
        }
    }

    public float getBodTemp() {
        return this.bodTemp;
    }

    public int getOxyLevel() {
        return this.oxyLevel;
    }

    public int getRecoveryDays() {
        return this.recoveryDays;
    }

    public int getUID() {
        return this.UID;
    }

    public String getName() {
        return this.name;
    }

    public boolean getAdmissionStatus() {
        return this.admitted;
    }

    public void setRecoveryDays(int days) {
        this.recoveryDays = days;
        this.admitted = true;
    }

    public void setInstitute(String name) {
        this.instituteName = name;
    }

    public boolean getDeleted() {
        return this.deleted;
    }

    public void setDeleted() {
        this.deleted = true;
    }
}