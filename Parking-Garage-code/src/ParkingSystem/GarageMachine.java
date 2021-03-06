package ParkingSystem;

import java.util.Scanner;

public class GarageMachine {
    public Scanner s;

    //This function is to take the client's car's info
    public void enterInfoButton() {
        s = new Scanner(System.in);
        System.out.println("Enter model, carId, mYear, width and depth");
        String m, cid, pmethod;
        int y;
        double w, d;
        System.out.println("Enter the parking method:");
        System.out.println("First come first served enter FC || Best fit Method enter BF: ");
        pmethod = s.nextLine();

        //To check which parking methos to use
        ParkingMethod method; //used polymorphism in method to apply the suitable park function
        if(pmethod.equals("FC")){
            method = new FirstComeMethod();
        }else if(pmethod.equals("BF")){
            method = new BestFitMethod();
        }else{
            System.out.println("Invalid input.");
            System.out.println("Will use the default method which is Best fit Method.");
            method = new BestFitMethod();
        }

        System.out.println("Enter model");
        m = s.nextLine();
        System.out.println("Enter carId");
        cid = s.nextLine();
        System.out.println("Enter mYear");
        y = s.nextInt();
        System.out.println("Enter width");
        w = s.nextDouble();
        System.out.println("Enter depth");
        d = s.nextDouble();

        String sid = MachineController.getInstance().sentInfo(m, cid, y, w, d, method);
        if (sid == "No") {
            System.out.println("Sorry No Avaliable Slot");
        } else {

            System.out.println("Your slot Id = " + sid);

        }

    }
    public void leaveButton () {
        // take id of slot from client
        s = new Scanner(System.in);
        String sid;
        System.out.println("Enter your slot Id");
        sid = s.nextLine();
        int fees = MachineController.getInstance().leave(sid);
        pay(fees);
    }
    private void pay (int cost)
    {
        System.out.println("please enter "+cost+" egp to leave");
        System.out.println("Enter money");
        double money =s.nextDouble();
        //while the money isn't enough repeate the process
        while(!MachineController.getInstance().check(money,cost)){
            System.out.println("please enter "+cost+" egp to leave");
            System.out.println("Enter money");
            money =s.nextDouble();
        }
    }
    public void  returnChange (double change)
    {
        System.out.println("tack "+change);
        System.out.println("thank you for use our garage :)");
    }
    public void  returnMoney (double money)
    {
        System.out.println("Not Enough Money.. Please Enter the requested Money");
    }
}
