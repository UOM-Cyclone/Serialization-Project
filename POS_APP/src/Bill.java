import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Vector;
import java.io.Serializable;

public class Bill implements Serializable{
    private static int count = 0;
    private static Vector<Bill> closedBills = new Vector<>();

    final private String BRANCH = "Katubedda";
    
    private LocalDateTime closingTime;
    private int cashierId;
    private int customerId;
    private int id;
    private boolean isClosed = false;
    private Vector<BuyListItem> listItems = new Vector<>();
    private double totalDiscount = 0;
    private double totalPrice = 0;

    Bill(int cashID, int custID){
        this.cashierId = cashID;
        this.customerId = custID;
        this.id = ++count;
    }

    public void addGroceryItem(GroceryItem item, int quantity, double discount){
        double discountValue = item.getPrice() * quantity * discount/100;
        double amount = item.getPrice() * quantity - discountValue;

        this.totalDiscount += discountValue;
        this.totalPrice += amount;

        BuyListItem temp = new BuyListItem(item.getId(), item.getName(), item.getPrice(), quantity, discount, amount);
        listItems.add(temp);
    }

    public void removeGroceryItem(int index){
        listItems.remove(index);
    }

    public void printProcessingBill(){
        BuyListItem temp;
        System.out.println("--------------------------------------\n");
        System.out.println("Item    Price Quantity Discount Amount");
        System.out.println("--------------------------------------\n");
        for(int i=0; i<listItems.size(); i++){
            temp = listItems.get(i);
            System.out.println(temp.toString(i+1));
        }

        String footer = String.format("""
                --------------------------
                Total discount : Rs. %.2f
                Total price    : Rs. %.2f
                --------------------------
                """,this.totalDiscount, this.totalPrice);

        System.out.println(footer);
    }

    public void printClosedBill(){
        // Cashier cashier = Cashier.getCashier(BRANCH)

        String customer = customerId==0 ? "Unregistered Customer" : Customer.getCustomer(customerId).getName();
        String cashier = Cashier.getCashier(this.cashierId).getName();
        String head = String.format("""
                    Super Saving
                ---------------------
                Branch : %s
                Cashier : %s
                Customer : %s
                """,this.BRANCH, cashier, customer);
        System.out.println(head+"\n");
        this.printProcessingBill();
        DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        String formattedDate = this.closingTime.format(myFormatObj);
        System.out.println("--------------------------");
        System.out.println("Printed : " + formattedDate);
        System.out.println("**Thank you for choosing us.**");
    }

    public void close(){
        closedBills.add(this);
        this.closingTime = LocalDateTime.now();
        this.isClosed = true;
    }

    public int getListLength(){return this.listItems.size();}
    public int getId(){return this.id;}
}
