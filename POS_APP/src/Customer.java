import java.util.Vector;

public class Customer {
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

    public int getId(){return this.id;}
    public String getPhone(){return this.phone;}
    public String getName(){return this.name;}
}
