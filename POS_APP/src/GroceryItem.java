import java.io.*;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

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

    public static void saveItems(){
        try {
            FileOutputStream file = new FileOutputStream("items.cyc");
            ObjectOutputStream out = new ObjectOutputStream(file);
            out.writeObject(items);
            out.close();
            file.close();
        } catch (IOException e) {
            System.out.println("ERROR cashiers data was not saved !");
        }
    }

    public static void loadItemData(){
        try {
            FileInputStream file = new FileInputStream("items.cyc");
            ObjectInputStream in = new ObjectInputStream(file);
            items = (HashMap<Integer, GroceryItem>) in.readObject();
            count = items.size();
            in.close();
            file.close();
        } catch (FileNotFoundException e) {
            System.out.println("Cashier data not loaded");
        } catch (IOException e) {
            System.out.println("ERROR! Saved data not loaded");
        } catch (ClassNotFoundException e) {
            System.out.println("This is invalid file");
        }
    }
}
