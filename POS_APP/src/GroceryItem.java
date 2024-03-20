import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;

public class GroceryItem implements Serializable {
    private static HashMap<Integer,GroceryItem> items = new HashMap<>();
    private static int count = 0;
    private final String name;
    private final int id;
    private int price;
    private final String size;
    private final Date DOM;
    private final Date DOE;

    public GroceryItem(String name, String size, Date dom, Date doe) {
        this.name = name;
        this.id = ++count;
        this.size = size;
        DOM = dom;
        DOE = doe;
    }

    public String getName(){return name;}
    public String getSize(){return size;}
    public int getId(){return id;}
    public int getPrice(){return price;}
    public Date getDOM(){return DOM;}
    public Date getDOE(){return DOE;}
    public static boolean isExist(Integer itemCode){return items.containsKey(itemCode);}
    public static GroceryItem getItem(Integer itemCode){
        if(isExist(itemCode)){
            return items.get(itemCode);
        } else {
            return null;
        }
    }
}
