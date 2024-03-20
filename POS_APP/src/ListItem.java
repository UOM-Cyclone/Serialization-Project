public class ListItem {
    private int itemCode;
    private String itemName;
    private int quantity;
    private float discount;
    private float amount;
    private float unitPrice;

    ListItem(int itemCode, String itemName, float unitPrice, int quantity, float discount, float amount){
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
                %.2f %d %.2f %.2f
                """,index, this.itemCode, this.itemName, this.unitPrice, this.quantity, this.discount, this.amount);
        return strOut;
    }

}
