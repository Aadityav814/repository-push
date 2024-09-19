public class main {




    
    /**
     * @param args
     */
    public static void main(String[] args) {
int password =12;

try {

    Person person = new Person();
    person.setName("John");
    person.setAge(30);

    // Using methods to get the values from the
    // variables
    System.out.println("Name: " + person.getName());
    System.out.println("Age: " + person.getAge(password));
    


    // Person person = new Person();
    // person.setName("John");
    // person.setAge(30);

    // // Using methods to get the values from the
    // // variables
    // System.out.println("Name: " + person.getName());
    // System.out.println("Age: " + person.getAge(128));
    
} catch (Exception e) {



    System.out.println(e.toString());
}

       


drive a=new drive();
        a.setAge(20);

        a.setName("raj");

        a.displayprice();
       
        System.out.println("Name: " + a.getName());
		System.out.println("Age: " + a.getAge(123));


        System.out.println("price: " + a.price);



        man m=new man();
        m.salary();
        
        m.time();
        m.software();
        System.out.println(m);
         
        female f=new female();
        f.salary();
        f.time();
        f.software();
        System.out.println(f);

        
    }
}
