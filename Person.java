package hotel;
public class Person {
    private String name;
    private String contact;
    
    public Person(String name,String contact){
        this.name=name;
        this.contact=contact;
    }
    public Person(){}
 
    public void setName(String newName){this.name=newName;}
    
    public void setContact(String newContact){this.contact=newContact;}
    
    public String getName(){return this.name;}
    
    public String getContact(){return this.contact;}
    
    @Override
    public String toString(){return String.format("Nome: %s%nContato: %s",
            this.name,this.contact);};
}
