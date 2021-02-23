package hotel;
import java.util.ArrayList;
import java.util.Random;
public class Room {
    Random rand = new Random();
    private static ArrayList<String> allCodes=new ArrayList();
    
    private String code;
    private Person responsible;
    private int peopleIn;
    
    public Room(){}
    
    public Room(Person responsible,int peopleIn){
        this.responsible=responsible;
        this.peopleIn=peopleIn;
    }
    
    public String getCode(){return this.code;};
    
    public int getPeopleIn(){return this.peopleIn;};
    
    public Person getResponsible(){return this.responsible;};
    
    public void setCode(){
        char []letters={'A','B','C','D','E','F','G','H','I','J','K','L','M',
            'N','O','P','Q','R','S','T','U','V','W','X','Y','Z'};
        String supposedCode=Character.toString(letters[rand.nextInt(26)])+Integer.toString
        (1+rand.nextInt(1001));
        while(isItAlreadyBeingUsed(supposedCode)){
            supposedCode=Character.toString(letters[rand.nextInt(26)])+Integer.toString
        (1+rand.nextInt(1001));
        }
        this.code=supposedCode;
    }
    public void setPeopleIn(int peopleIn){this.peopleIn=peopleIn;};
    
    public void setResponsible(Person responsible){this.responsible=responsible;};
    
    private static boolean isItAlreadyBeingUsed(String code){
        for(String str:allCodes)if(str.equals(code)) return true;
        return false;
    }
    
    @Override
    public String toString(){return String.format("Código do quarto: %s%n"
            + "Quantidade de pessoas: %d%nNome do responsável: %s%n"
            + "Contato: %s",this.code,
            this.peopleIn,this.responsible.getName(),this.responsible.getContact());
    };
}
