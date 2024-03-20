import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.text.NumberFormat;
import java.util.*;

public class POS implements Serializable {
    Scanner scan = new Scanner(System.in);
    Map<String, String> cashiers = new HashMap<>();
    Vector<Bill> pendingBills = new Vector<Bill>();
    Cashier currentCashier;

    void start() throws IOException {
        String username;
        String password;
        while (true) {
            while (true) {
                System.out.print("Username : ");
                username = scan.nextLine();
                if (Cashier.isExist(username)) {
                    break;
                } else {
                    System.out.println("Enter valid input");
                }
            }

            while (true) {
                System.out.print("Password : ");
                password = scan.nextLine();
                if (password.equals(Cashier.getCashier(username).getPassword())) {
                    currentCashier = Cashier.getCashier(username);
                    System.out.println("Login successful!");
                    mainMenu();
                    break;
                } else {
                    System.out.println("Please enter valid password");
                }
            }
        }
    }

     void mainMenu() throws IOException {
        do {
            System.out.println("""
                    1) New Bill
                    2) Pending Bills
                    3) Register New Customer
                    4) Logout""");
            System.out.print("Select the option : ");
            String userInput = scan.nextLine();
            switch (userInput) {
                case "1":
                    newBill();
                    break;
                case "2":
                    pendingBills();
                    break;
                case "3":
//                    RegisterNewCustomer();
                    break;
                case "4":
                    return;
                default:
                    System.out.println("Please enter a valid input.\n");
                    break;
            }
        } while (true);
    }

    private void pendingBills() {
    }

    void newBill() throws IOException {

            while (true) {

                System.out.println("Enter Customer phone or 0 to skip : ");
                String customerID = scan.nextLine();
                if (customerID.equals("0")) {
                    Bill newBill = new Bill(currentCashier.getID(), 0);
                    handleBill(newBill);
                    return;
                } else if (customerID.length() == 0 && Customer.isExist(customerID)) {
                    Bill newBill = new Bill(currentCashier.getID(), currentCashier.getID());
                    handleBill(newBill);
                    return;
                } else {
                    System.out.println("Enter valid input");
                }

            }

    }


    public void handleBill(Bill currentBill) throws IOException {
        String inst = """
                1) Add item
                2) Remove item
                3) Hold Bill
                4) Close Bill
                """;

        while (true){
            System.out.println("Added items");
            currentBill.printProcessingBill();
            System.out.println("\n" + inst);
            System.out.print("Enter number : ");
            String userInput = scan.nextLine();
            switch (userInput){
                case "1":
                    GroceryItem item;
                    do{
                        item = getItemDetails();
                    }while (item == null);
                    int qty;
                    double dis;
                    while (true){
                        try {
                            System.out.println("Enter the quantity : ");
                            qty = scan.nextInt();
                            System.out.println("Enter the discount : ");
                            dis = scan.nextDouble();
                            break;
                        } catch (Exception e){
                            System.out.println("Enter valid input");
                        }
                    }
                    currentBill.addGroceryItem(item,qty,dis);
                    break;
                case "2":
                    int removeIndex;
                    while (true) {
                        try {
                            System.out.println("Enter number you want to remove : ");
                            removeIndex = scan.nextInt();
                            break;
                        } catch (Exception e) {
                            System.out.println("Enter valid input");
                        }
                    }
                    currentBill.removeGroceryItem(removeIndex-1);
                    break;
                case "3":
                    System.out.println("Your bill is held");
                    return;
                case "4":
                    currentBill.close();
                    currentBill.printClosedBill();
                    for (int i = 0; i < pendingBills.size(); i++){
                        if (pendingBills.get(i) == currentBill){
                            pendingBills.remove(pendingBills.get(i));
                        }
                    }
                    return;
                default:
                    System.out.println("Enter valid input");
            }
        }
    }

    public GroceryItem getItemDetails() throws IOException {
        InputStreamReader r = null;
        BufferedReader br = null;
        GroceryItem item = null;
        try {
            r = new InputStreamReader(System.in);
            br = new BufferedReader(r);
            System.out.print("Enter the item code : ");

            String item_code = br.readLine();
            if(GroceryItem.isExist(Integer.parseInt(item_code))) {
                item = GroceryItem.getItem(Integer.parseInt(item_code));
            } else {
                throw new ItemCodeNotFound();
            }

        } catch (ItemCodeNotFound e) {
            System.out.println("Item not founded");
        } catch (NumberFormatException e) {
            System.out.println("Enter valid input");
        }catch (IOException e) {
            System.out.println("Enter valid");;
        } finally {
            if(br != null) {
                br.close();
            }
            if(r != null) {
                r.close();
            }
        }
        return item;
    }




}

