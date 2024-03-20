import java.io.*;
import java.util.Map;
import java.util.Vector;

public class Customer implements Serializable {
    private static Vector<Customer> customers = new Vector<>();
    private final String name;
    private static int count = 0;
    private final int id;
    private final String phone;


    public Customer(String name, String phone) {
        this.name = name;
        this.id = ++count;
        this.phone = phone;
        customers.add(this);
    }
    public static Customer getCustomer(int id){
        Vector<Customer> result = new Vector<>(
                customers.stream().filter(customer -> customer.getId() == id).toList()
        );

        if (result.isEmpty()){
            return null;
        }else {
            return result.get(0);
        }
    }
    public static Customer getCustomer(String phone){
        Vector<Customer> result = new Vector<>(
                customers.stream().filter(customer -> customer.getPhone().equals(phone)).toList()
        );

        if (result.isEmpty()){
            return null;
        }else {
            return result.get(0);
        }
    }
    public static boolean isExist(String phone){return getCustomer(phone) != null;}
    public int getId(){return this.id;}
    public String getPhone(){return this.phone;}
    public String getName(){return this.name;}
    public static void saveCustomers(){
        try {
            FileOutputStream file = new FileOutputStream("customers.cyc");
            ObjectOutputStream out = new ObjectOutputStream(file);
            out.writeObject(customers);
            out.close();
            file.close();
        } catch (IOException e) {
            System.out.println("ERROR customers data was not saved !");
        }
    }

    public static void loadGCustomersData(){
        try {
            FileInputStream file = new FileInputStream("customers.cyc");
            ObjectInputStream in = new ObjectInputStream(file);
            customers = (Vector<Customer>) in.readObject();
            count = customers.size();
            in.close();
            file.close();
        } catch (FileNotFoundException e) {
            System.out.println("customers data not loaded");
        } catch (IOException e) {
            System.out.println("ERROR! Saved data not loaded");
        } catch (ClassNotFoundException e) {
            System.out.println("This is invalid file");
        }
    }
}
