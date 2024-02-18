package entities;


public class User{
    private int id;
    private String name;
    private String surname;
    private boolean gender;
    private String password;

    private  int balance;




    public User(int id, String name, String surname, String password, int balance){

    }


    public User(String name  , String surname , boolean gender, String password , int balance ){
        setName(name);
        setSurname(surname);
        setGender(gender);
        setPassword(password);
        setBalance(balance);


    }
    public User(int id, String name, String surname, boolean gender , String password , int balance) {
        this(name, surname, gender , password , balance);
        setId(id);

    }



    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setGender(boolean gender) {
        this.gender = gender;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public boolean getGender() {
        return gender;
    }
    public  void setPassword(String password){this.password = password;}
    public String getPassword(){return password;}

    public void setBalance(int balance) {
        this.balance = balance;
    }
    public int getBalance(){return balance;}

    @Override
    public String toString() {
        return id + ". " + name +" " + surname + "-" + (gender ? "male" : "female") + "your balance = " + balance;
    }
}
