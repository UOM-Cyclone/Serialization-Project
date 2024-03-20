import java.util.Date;
import java.util.Vector;

public class Bill {
    private static int count = 0;
    private static Vector<Bill> closedBills = new Vector<>();

    final private String BRANCH = "Katubedda";
    
    private Date closingTime;
    private int cashierId;
    private int customerId;
    private int id;
    private boolean isClosed=false;
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

        String customer = customerId==0 ? "Unregistered Customer" : String.valueOf(customerId);
        String head = String.format("""
                    Super Saving
                ---------------------
                Branch : %s
                Cashier Id : %d
                Customer : %s
                """,this.BRANCH, this.cashierId, customer);
        System.out.println(head+"\n");
        this.printProcessingBill();
        System.out.println("Thank you for choosing us.");
    }

    public void close(){
        closedBills.add(this);
    }

    public int getListLength(){return this.listItems.size();}
    public int getId(){return this.id;}
}
