import java.util.HashMap;
import java.util.Map;

public class Cashier {
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

}
