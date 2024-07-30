import java.util.*;

public class ATM {
    private static HashMap<String,String> userList;
    private static int balance;
    private static ArrayList<String > transactionList;
    static HashMap<String,String > months;
    static Date date;
    static Scanner sc = new Scanner(System.in);
    public static void main(String args[]){
        userList = new HashMap<>();
        userList.put("21R21A1292","1234");
        transactionList = new ArrayList<>();
        transactionList.add("For A/C 1234567890, Rs. 250000 is credited on 02/7/2024 at 10:14:12");
        transactionList.add("For A/C 1234567890, Rs. 5000 is credited on 25/7/2024 at 17:04:50");
        transactionList.add("For A/C 1234567890, Rs. 10000 is debited on 29/7/2024 at 13:54:45");
        balance = 235000;
        months = new HashMap<>();
        String s[] = {"Jan","Feb","Mar","Apr","May","Jun","Jul","Aug","Sep","Oct","Nov","Dec"};
        for(int i=0;i<12;i++){
            months.put(s[i],(i+1)+"");
        }
        date = new Date();
        loginPage();
    }
    public static void loginPage(){
        System.out.println( "+-----------------------------------------------------------------------+" +"\n"+
                            "+                           ATM INTERFACE                               +"+"\n"+
                            "+-----------------------------------------------------------------------+"+"\n"+
                            "              Please login to your account before use.                  ");
        System.out.print("Enter your User Id : ");
        String userId = sc.nextLine();
        System.out.print("Enter your Pin : ");
        String pin = sc.nextLine();
        if(userList.containsKey(userId)){
            if(userList.get(userId).compareTo(pin)==0) {
                System.out.println("----------------------Successfully Logged In------------------------\n\n\n");
                menuPage();
            }
            else{
                System.out.println("----------------------Incorrect Pin------------------------"+"\n"+
                                   "  *-------------*    Please Try Again      *-------------*  \n");
                loginPage();
            }
        }else{
            System.out.println("----------------------Incorrect UserId------------------------"+"\n"+
                               "  *-------------*     Please Try Again       *-------------*  \n");
            loginPage();
        }
    }

    public static void menuPage(){
        System.out.print("+-----------------------------------------------------------------------+" +"\n"+
                           "+                           USER INTERFACE                              +"+"\n"+
                           "+-----------------------------------------------------------------------+"+"\n"+
                           "+                       1. Transactions History                         +"+"\n"+
                           "+                       2. Withdraw                                     +"+"\n"+
                           "+                       3. Deposit                                      +"+"\n"+
                           "+                       4. Transfer                                     +"+"\n"+
                           "+                       5. Balance Check                                +"+"\n"+
                           "+                       6. Quit                                         +"+"\n"+
                           "+-----------------------------------------------------------------------+"+"\n"+
                           "               Enter appropriate number according to your choice        "+"\n"+
                           "Enter your choice :  ");
        int choice = sc.nextInt(), amount;
        sc.nextLine();
        switch (choice){
            case 1: showTransactions();
                    break;
            case 2:
                System.out.print("Enter Amount to be withdrawn : ");
                amount = sc.nextInt();
                sc.nextLine();
                if(amount>balance){
                    System.out.println("Transaction not possible. Because of Insufficient Balance");
                }else {
                    System.out.println(" Rs. " + amount + " is withdrawn Successfully");
                    balance-= amount;
                    transactionList.add(transaction(amount,false));
                }
                break;
            case 3:
                System.out.print("Enter Amount to be Deposited : ");
                amount = sc.nextInt();
                sc.nextLine();
                balance+=amount;
                transactionList.add(transaction(amount,true));
                System.out.println(" Rs. "+amount+" is deposited Successfully");
                break;
            case 4:
                System.out.print("Enter Amount to be Transferred : ");
                amount = sc.nextInt();
                sc.nextLine();
                System.out.println("Enter recipient Bank Account Number : ");
                String ac = sc.nextLine();
                System.out.println(" Rs. "+amount+" is Transferred to A/C "+ac+" Successfully");
                break;
            case 5:
                System.out.println("\nYour Account Balance is : "+balance+" in Rupees.\n");

        }
        System.out.println(" Thank you for using our Bank ATM Service. Have a nice day\n\n\n");
        loginPage();
    }

    public static void showTransactions(){
        for(String s: transactionList){
            System.out.println(s);
        }
        System.out.println("Press anything to Quit.        ");
        String s = sc.nextLine();
    }

    private static String transaction(int bal, boolean flag){
        String action;
        if(flag){
            action = "credited";
        }else{
            action = "debited";
        }
        String d = date.toString();
        String day = d.substring(8,10);
        String month = months.get(d.substring(4,7));
        String year = d.substring(24);
        String time = d.substring(11,19);
        return "For A/C 1234567890, Rs. "+bal+" is "+action+" on "+day+"/"+month+"/"+year+" at "+time;
    }
}
