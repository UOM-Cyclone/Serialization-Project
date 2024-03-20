import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class Cashier implements Serializable {
    private static int count = 0;
    private static Map<String, Cashier> cashiers = new HashMap<>();

    private final String name;
    private final String userName;
    private final String password;
    private final int id;

    static boolean isExist(String userName){
        Object[] userNames = cashiers.keySet().toArray();
        for(Object username: userNames){
            if (username.toString().equals(userName)){
                return true;
            }
        } 
        return false;
    }

    static Cashier getCashier(String userName){

        Object[] userNames = cashiers.keySet().toArray();
        for(Object username: userNames){
            if (username.toString().equals(userName)){
                return cashiers.get(userName);
            }
        } 

        return null;
    }

    static Cashier getCashier(int userId){

        Object[] userNames = cashiers.keySet().toArray();
        for(Object username: userNames){
            if (cashiers.get(username).getID() == userId){
                return cashiers.get(username);
            }
        } 

        return null;
    }

    public Cashier(String name, String userName, String pwd){
        this.name = name;
        this.userName = userName;
        this.password = pwd;
        this.id = ++count;

        cashiers.put(userName, this); //Store the new instance in the HashMap
    }

    public String getName(){return this.name;}
    public String getUserName(){return this.userName;}
    public String getPassword(){return this.password;}
    public int getID(){return this.id;}

    public static void saveCashiers(){
        try {
            FileOutputStream file = new FileOutputStream("cashiers.cyc");
            ObjectOutputStream out = new ObjectOutputStream(file);
            out.writeObject(cashiers);
            out.close();
            file.close();
        } catch (IOException e) {
            System.out.println("ERROR cashiers data was not saved !");
        }
    }

    public static void loadGCashierData(){
        try {
            FileInputStream file = new FileInputStream("cashiers.cyc");
            ObjectInputStream in = new ObjectInputStream(file);
            cashiers = (Map<String, Cashier>) in.readObject();
            count = cashiers.size();
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
