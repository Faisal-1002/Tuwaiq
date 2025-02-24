public class Account {

    private String id;
    private String name;
    private int balance = 0;

    public Account(String id, String name){
        this.id = id;
        this.name = name;
    }
    public Account(String id, String name, int balance){
        this.id = id;
        this.name = name;
        this.balance = balance;
    }

    public void setId(String id){
        this.id = id;
    }
    public void setName(String name){
        this.name = name;
    }
    public void setBalance(int balance){
        this.balance =  balance;
    }
    public String getId(){
        return id;
    }
    public String getName(){
        return name;
    }
    public int getBalance(){
        return balance;
    }
    public int credit(int amount){
        balance+= amount;
        return amount;
    }
    public int debit(int amount){
        balance-= amount;
        return amount;
    }
    public int transferTo(Account another, int amount){
        if (amount>this.balance){
            System.out.println("Insufficient balance, transfer interrupted");
            return getBalance();
        } else {
            System.out.println("Transfer is done successfully");
            another.credit(this.debit(amount));
            return getBalance();
        }
    }
    public String toString(){
        return ("ID : " + id + ", Name : " + name + ", Balance : " + balance);
    }
}
