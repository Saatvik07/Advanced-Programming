import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class Swing1 {
    public static Patient[] patient;
    public static SwingClass obj;
    public static ArrayList<Character> towerSearchParams = new ArrayList<>();
    public static void main(String[] args) {
        patient = new Patient[20];
        String[] names = {"Flora","Denys","Jim","Hazel","Caery","David","Kevim","Tom","Bob","Rachel","Thomas","Mary","Smith","Pearson","Andersom","Johnson","Robertz","Julie","Edith","John"};
        int [] ages = {6,24,42,87,72,7,37,67,74,48,21,17,89,47,62,10,50,86,42,95};
        Character[] towers = {'A','B','C','D','A','B','D','D','A','C','C','D','A','B','B','D','A','B','D','D'};
        String[] reportedDates = {"01/04/2020","01/04/2020","18/05/2020","23/06/2020","01/06/2020","14/06/2020","05/06/2020","20/06/2020","04/07/2020","24/07/2020","11/06/2020","21/06/2020","07/08/2020","04/06/2020","27/07/2020","01/08/2020","09/08/2020","02/05/2020","07/06/2020","01/06/2020"};
        for(int i=0;i< names.length;i++){
             patient[i] = new Patient(names[i],towers[i],ages[i],reportedDates[i]);
        }
        obj = new SwingClass();
    }
}
class Patient{
    String name;
    Character tower;
    int age;
    String reportDate;
    String recoverDate;
    Patient(String name, Character tower, int age, String reportDate){
        this.name = name;
        this.tower = tower;
        this.age = age;
        this.reportDate = reportDate;
        this.recoverDate = getRecoverDate(reportDate);
    }
    String getRecoverDate(String reportDate){
        SimpleDateFormat rDate = new SimpleDateFormat("dd/MM/yyyy");
        Calendar cal = Calendar.getInstance();
        try{
            cal.setTime(rDate.parse(reportDate));
        }
        catch( ParseException error){
            System.out.println(error);
        }
        cal.add(Calendar.DAY_OF_MONTH,21);
        return rDate.format(cal.getTime());
    }
}
class SwingClass extends JFrame implements ActionListener{
    public static JFrame frame;
    public static JTextField inputDate;
    public static JTextField inputMonth;
    public static JTextField inputYear;
    public static JCheckBox towerA;
    public static JCheckBox towerB;
    public static JCheckBox towerC;
    public static JCheckBox towerD;
    public static JButton checkButton;
    public static JLabel promptLabel;
    public static JLabel suggestionLabel;
    public static JLabel checkboxLabel;
    public static JTable table = new JTable();
    public static JLabel numberTowerA;
    public static JLabel numberTowerB;
    public static JLabel numberTowerC;
    public static JLabel numberTowerD;
    SwingClass(){
        frame = new JFrame();
        JPanel datePanel = new JPanel();
        JPanel towerPanel = new JPanel();
        JLabel dateLabel = new JLabel("Enter the date");
        numberTowerA = new JLabel();
        numberTowerA.setVisible(false);
        numberTowerB = new JLabel();
        numberTowerB.setVisible(false);
        numberTowerC = new JLabel();
        numberTowerC.setVisible(false);
        numberTowerD = new JLabel();
        numberTowerD.setVisible(false);
        inputDate = new JTextField(10);
        JLabel monthLabel = new JLabel("Enter the month");
        inputMonth = new JTextField(10);
        JLabel yearLabel = new JLabel("Enter the year");
        inputYear = new JTextField(10);
        towerA = new JCheckBox("Tower A");
        towerB = new JCheckBox("Tower B");
        towerC = new JCheckBox("Tower C");
        towerD = new JCheckBox("Tower D");
        checkButton = new JButton("Search");
        towerA.addActionListener(this);
        towerB.addActionListener(this);
        towerC.addActionListener(this);
        towerD.addActionListener(this);
        checkButton.addActionListener(this);
        promptLabel = new JLabel("Invalid date");
        suggestionLabel = new JLabel("Please enter a date that lies within April 2020 and August 2020");
        checkboxLabel = new JLabel("No Check box is selected, please select a checkbox and then search again");
        promptLabel.setVisible(false);
        suggestionLabel.setVisible(false);
        checkboxLabel.setVisible(false);
        frame.add(promptLabel);
        frame.add(suggestionLabel);
        frame.add(checkboxLabel);
        datePanel.add(dateLabel);
        datePanel.add(inputDate);
        datePanel.add(monthLabel);
        datePanel.add(inputMonth);
        datePanel.add(yearLabel);
        datePanel.add(inputYear);
        frame.add(datePanel);
        towerPanel.add(towerA);
        towerPanel.add(towerB);
        towerPanel.add(towerC);
        towerPanel.add(towerD);
       frame. add(towerPanel);
        frame.add(checkButton);
        JPanel numberPanel = new JPanel();
        numberPanel.add(numberTowerA);
        numberPanel.add(numberTowerB);
        numberPanel.add(numberTowerC);
        numberPanel.add(numberTowerD);
        frame.add(numberPanel);
        FlowLayout flowLayout = new FlowLayout();
        frame.setLayout(flowLayout);
        frame.setTitle("COVID-19 Tracker");
        frame.setVisible(true);
        frame.setSize(800,800);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==towerA){
            if(towerA.isSelected()) {
                if (!Swing1.towerSearchParams.contains('A')) {
                    Swing1.towerSearchParams.add('A');
                }
            }
            else{
                Swing1.towerSearchParams.removeIf(c -> c == 'A');
            }
        }
        else if (e.getSource()==towerB){
            if(towerB.isSelected()) {
                if (!Swing1.towerSearchParams.contains('B')) {
                    Swing1.towerSearchParams.add('B');
                }
            }
            else{
                Swing1.towerSearchParams.removeIf(c -> c == 'B');
            }
        }
        else if (e.getSource()==towerC){
            if(towerC.isSelected()) {
                if (!Swing1.towerSearchParams.contains('C')) {
                    Swing1.towerSearchParams.add('C');
                }
            }
            else{
                Swing1.towerSearchParams.removeIf(c -> c == 'C');
            }
        }
        else if (e.getSource()==towerD){
            if(towerA.isSelected()) {
                if (!Swing1.towerSearchParams.contains('D')) {
                    Swing1.towerSearchParams.add('D');
                }
            }
            else{
                Swing1.towerSearchParams.removeIf(c -> c == 'D');
            }
        }
        else if (e.getSource()==checkButton){
            if(checkInputDate(inputDate.getText(),inputMonth.getText(),inputYear.getText())){
                if(Swing1.towerSearchParams.size()>0) {
                    int recA=0,repA=0,recB=0,repB=0,recC=0,repC=0,recD=0,repD=0;
                    checkboxLabel.setVisible(false);
                    promptLabel.setVisible(false);
                    suggestionLabel.setVisible(false);
                    DefaultTableModel dataModel = new DefaultTableModel(0, 0);
                    String[] header = new String[]{"Name", "Age", "Tower", "Reported On", "Recovers On", "Status"};
                    dataModel.setColumnIdentifiers(header);
                    if (frameContainsTable()) {
                        for (int i = 0; i < dataModel.getRowCount(); i++) {
                            dataModel.removeRow(i);
                        }
                    }
                    table.setModel(dataModel);
                    for (int i = 0; i < Swing1.patient.length; i++) {
                        if (Swing1.towerSearchParams.contains(Swing1.patient[i].tower)) {
                            String inDate = inputDate.getText() + "/" + inputMonth.getText() + "/" + inputYear.getText();
                            if (reportedTillInputDate(Swing1.patient[i].reportDate, inDate)) {
                                String status;
                                status = getStatus(Swing1.patient[i].recoverDate, inDate);
                                dataModel.addRow(new Object[]{Swing1.patient[i].name, Swing1.patient[i].age, Swing1.patient[i].tower, Swing1.patient[i].reportDate, Swing1.patient[i].recoverDate, status});
                                switch(Swing1.patient[i].tower){
                                    case 'A':
                                        System.out.println("A");
                                        if(status.equals("Active")){
                                            repA++;
                                        }
                                        else{
                                            recA++;
                                        }
                                        break;
                                    case 'B':
                                        System.out.println("B");
                                        if(status.equals("Active")){
                                            repB++;
                                        }
                                        else{
                                            recB++;
                                        }
                                        break;
                                    case 'C':
                                        System.out.println("C");
                                        if(status.equals("Active")){
                                            repC++;
                                        }
                                        else{
                                            recC++;
                                        }
                                        break;
                                    case 'D':
                                        System.out.println("D");
                                        if(status.equals("Active")){
                                            repD++;
                                        }
                                        else{
                                            recD++;
                                        }
                                        break;
                                }
                            }
                            if(recA>0 || repA>0 && Swing1.towerSearchParams.contains('A')){
                                numberTowerA.setVisible(true);
                                numberTowerA.setText("Tower A: Recovered "+recA+"    Active "+repA+" ");
                            }
                            if (recB>0 || repB>0 && Swing1.towerSearchParams.contains('B')){
                                numberTowerB.setVisible(true);
                                numberTowerB.setText("Tower B: Recovered "+recB+"    Active "+repB+" ");
                            }
                            if (recC>0 || repC>0 && Swing1.towerSearchParams.contains('C')){
                                numberTowerC.setVisible(true);
                                numberTowerC.setText("Tower C: Recovered "+recC+"    Active "+repC+" ");
                            }
                            if(recD>0||repD>0 && Swing1.towerSearchParams.contains('D')){
                                numberTowerD.setVisible(true);
                                numberTowerD.setText("Tower D: Recovered "+recD+"    Active "+repD+" ");
                            }
                            if(!Swing1.towerSearchParams.contains('A') && numberTowerA.isVisible()){
                                numberTowerA.setVisible(false);
                            }
                            if(!Swing1.towerSearchParams.contains('B') && numberTowerB.isVisible()){
                                numberTowerB.setVisible(false);
                            }
                            if(!Swing1.towerSearchParams.contains('C') && numberTowerC.isVisible()){
                                numberTowerC.setVisible(false);
                            }
                            if(!Swing1.towerSearchParams.contains('D') && numberTowerD.isVisible()){
                                numberTowerD.setVisible(false);
                            }
                        }
                    }
                        JPanel a = new JPanel();
                        a.add(table.getTableHeader());
                        JPanel b = new JPanel();
                        b.add(table);
                        frame.add(a);
                        frame.add(b);
                        frame.revalidate();
                }
                else{
                    checkboxLabel.setVisible(true);
                }
            }
            else{
                promptLabel.setVisible(true);
                suggestionLabel.setVisible(true);

            }
        }


    }
    Boolean checkInputDate(String date,String month, String year){
        int d,m,y;
        try{
            d = Integer.parseInt(date);
            m = Integer.parseInt(month);
            y = Integer.parseInt(year);
            if( m < 4 || m > 8 || d<1 || d>30 && (m==4 || m==6) || d>31 || y!=2020){
                return false;
            }
            return true;
        }
        catch(NumberFormatException error){
            JLabel wrongFormatLabel = new JLabel("The input date must be a number");
            frame.add(wrongFormatLabel);
            frame.revalidate();
        }
        return true;

    }
    Boolean frameContainsTable (){
        for(int i=0;i<frame.getComponentCount();i++){
            if(frame.getComponent(i)==table){
                return true;
            }
        }
        return false;
    }
    Boolean reportedTillInputDate(String reportDate, String inputDate) {
        SimpleDateFormat rDate = new SimpleDateFormat("dd/MM/yyyy");
        SimpleDateFormat iDate = new SimpleDateFormat("dd/MM/yyyy");
        Date report,input;
        try{
            report = rDate.parse(reportDate);
            input = iDate.parse(inputDate);
            if(input.after(report)){
                return true;
            }
            return false;
        }
        catch( ParseException error){
            System.out.println(error);
        }
        return false;
    }
    String getStatus (String recoverDate, String inputDate){
        SimpleDateFormat rDate = new SimpleDateFormat("dd/MM/yyyy");
        SimpleDateFormat iDate = new SimpleDateFormat("dd/MM/yyyy");
        Date recovery,input;
        try{
            recovery = rDate.parse(recoverDate);
            input = iDate.parse(inputDate);
            if(input.after(recovery)|| input.equals(recovery)){
                return "Recovered";
            }
            return "Active";
        }
        catch( ParseException error){
            System.out.println(error);
        }
        return "Active";
    }
}