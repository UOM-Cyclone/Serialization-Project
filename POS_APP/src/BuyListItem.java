import java.io.Serializable;

public class BuyListItem implements Serializable {
    private int itemCode;
    private String itemName;
    private int quantity;
    private double discount;
    private double amount;
    private double unitPrice;

    BuyListItem(int itemCode, String itemName, double unitPrice, int quantity, double discount, double amount){
        this.itemCode = itemCode;
        this.itemName = itemName;
        this.unitPrice = unitPrice;
        this.quantity = quantity;
        this.discount = discount;
        this.amount = amount;
    }

    public String toString(int index){
        String strOut = String.format("""
                %d) %3d : %s
                %.2f %d %.2f % %.2f
                """,index, this.itemCode, this.itemName, this.unitPrice, this.quantity, this.discount, this.amount);
        return strOut;
    }

}
