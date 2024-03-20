import java.util.AbstractMap;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class POS {
    Scanner scan = new Scanner(System.in);
    Map<String, String> cashiers = new HashMap<>();

    void start() {
        do {
            System.out.print("Username : ");
            String username = scan.nextLine();
            System.out.print("Password : ");
            String password = scan.nextLine();

            if (password.equals(cashiers.get(username))) {
                System.out.println("Login successful!");
                mainMenu();
                break;
            } else {
                System.out.print("Wrong password.Try again.\n");
            }
        } while (true);


    }

     void mainMenu() {
        do {
            System.out.println("Select the option : ");
            System.out.println("""
                    1) New Bill
                    2) Pending Bills
                    3) Register New Customer
                    4) Logout""");
            String userInput = scan.nextLine();
            switch (userInput) {
                case "1":
                    newBill();
                    break;
                case "2":
                    pendingBills();
                    break;
                case "3":
                    RegisterNewCustomer();
                    break;
                case "4":
                    return;
                default:
                    System.out.println("Please enter a valid input.\n");
                    break;
            }
        } while (true);
    }

    void newBill() {
        do {
            System.out.println("Enter Customer ID or 0 to skip : ");
            String customerID = scan.nextLine();
            if (customerID.equals("0")) {
                buyProduct();
                return;
            } else if (customerID.isExit()) {
                buyProduct();
                return;
            } else {
                System.out.println("Invalid customer ID ");
            }
        }while (true);
    }

    void pendingBills() {
        do {
            System.out.println("Pending bills");
            //display bills

            System.out.print("Enter a bil number : ");
            String pendingBillNumber = scan.nextLine();
            if(pendingBillNumber.isExit()){
                buyProduct();
                return;
            }
            else {
                System.out.println("Invalid Bill Number ");
            }

        }while (true);
    }

    void buyProduct() {
        do{

        }while (true);
    }

    void RegisterNewCustomer() {
    }


}
