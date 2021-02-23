package hotel;
import java.util.Scanner;
import java.util.ArrayList;
public class Hotel {
    public static void main(String[] args) {
        ArrayList<Room> hotelRooms= new ArrayList();
        Scanner input = new Scanner(System.in);
        int totalRooms=0,availableRooms=0,peopleIn;
        String[] mainMenuOpts={"(1) Ocupar quarto","(2) Desocupar quarto",
            "(3) Informações sobre quartos","(4) Sair"},roomsInfoMenu=
        {"(1) Pesquisar quarto","(2) Listar quartos ocupados","(3) Modificar "
                + "quantidade de quartos","(4) Voltar"};
        String name,contact,roomCode;
        boolean mainRun=true,roomsInfoRun=true;
        //Main routine
        while(mainRun){
            switch(menu(mainMenuOpts,"Gerenciador de Hotéis")){        
                case 1:
                    if(totalRooms==0){
                        message("Defina a quantidade de quartos em seu hotel!",100,true,false);
                        break;
                    }
                    if(availableRooms==0){
                        message("Não há quartos disponíveis!",100,true,false);
                        break;
                    }
                    message("Dados sobre o responsável",100,true,true);
                    System.out.print("Nome: ");
                    name=input.nextLine();
                    while(name.isEmpty()){
                        message("Não é permitido deixar em branco!",100,true,true);
                        System.out.print("Nome: ");
                        name=input.nextLine();
                    }
                    System.out.print("Contato: ");
                    contact=input.nextLine();
                    while(contact.isEmpty()){
                        message("Não é permitido deixar em branco!",100,true,true);
                        System.out.print("Contato: ");
                        contact=input.nextLine();
                    }
                    message("Dados sobre o quarto",100,true,true);
                    System.out.print("Quantidade de pessoas (2-4 pessoas):");
                    peopleIn=input.nextInt();
                    while(peopleIn<2||peopleIn>4){
                        message("Verifique se são de 2 a 4 pessoas!",100,true,true);
                        System.out.print("Quantidade de pessoas (2-4 pessoas):");
                        peopleIn=input.nextInt();
                    }
                    Room newRoom=new Room(new Person(name,contact),peopleIn);
                    newRoom.setCode();
                    row(100);
                    System.out.printf("Código gerado para identificação do "
                                + "quarto: %s%n",newRoom.getCode());
                    hotelRooms.add(newRoom);
                    message("Registrado com sucesso!",100,true,false);
                    availableRooms--;
                    break;
                case 2:
                    if(totalRooms==0){
                        message("Defina a quantidade de quartos em seu hotel!",100,true,false);
                        break;
                    }
                    if(hotelRooms.isEmpty()){
                        message("Não há quartos ocupados!",100,true,false);
                        break;
                    }
                    message("Desocupar quarto",100,true,true);
                    System.out.print("Informe o código do quarto: ");
                    roomCode=input.next();
                    if(vacateRoom(hotelRooms,roomCode)) availableRooms++;
                    break;
                case 3:
                    while(roomsInfoRun){
                        roomsInformation(totalRooms,availableRooms,hotelRooms.size());
                        switch(menu(roomsInfoMenu,"Escolha uma das opções abaixo")){
                            case 1:
                                if(hotelRooms.isEmpty()){
                                    message("Sem quartos ocupados!",100,true,false);
                                    break;
                                }
                                searchForRoom(hotelRooms);
                                break;
                            case 2:
                                if(hotelRooms.isEmpty()){
                                    message("Sem quartos ocupados!",100,true,false);
                                    break;
                                }
                                seeRooms(hotelRooms);
                                break;
                            case 3:
                                totalRooms=switchRoomsQuant(totalRooms);
                                availableRooms=totalRooms-hotelRooms.size();
                                break;
                            case 4:
                                roomsInfoRun=false;
                                break;
                        }
                    }
                    roomsInfoRun=true;
                    break;
                case 4:
                    message("Rotina finalizada",100,true,true);
                    mainRun=false;
                    break;
            }
        }
    }
    public static int menu(String[] opts,String mainMsg){
        Scanner input=new Scanner(System.in);
        int counter=0,opt;
        do{
            if(counter>=1) message("Escolha uma opção válida!",100,true,false);
            counter++;
            message(mainMsg,100,true,true);
            for(String str:opts) System.out.printf("%s%n",str);
            row(100);
            opt=input.nextInt();
        }while(opt<1||opt>opts.length);
        return opt;
    }
    public static void roomsInformation(int totRooms,int avaiRooms,int 
            occRooms){
                    message("Informações sobre quartos",100,true,true);
                    System.out.printf("Total de quartos: %d%n",totRooms);
                    System.out.printf("Quartos disponíveis: %d%n",avaiRooms);
                    System.out.printf("Quartos ocupados: %d%n",occRooms);
    }
    public static int switchRoomsQuant(int currentQuant){
        Scanner input=new Scanner(System.in);
        int newQuant;
        row(100);
        System.out.print("Nova quantidade:");
        newQuant=input.nextInt();
        if(newQuant<=0){
            message("Quantidade inválida!",100,true,false);
            return currentQuant;
        }else {
            message("Modificado com sucesso!",100,true,false);
            return newQuant;
        }
    }
    public static void searchForRoom(ArrayList<Room> rooms){
        Scanner input=new Scanner(System.in);
        row(100);
        System.out.print("Insira o código do quarto: ");
        String code=input.nextLine();
        row(100);
        for(Room rm:rooms){
            if(rm.getCode().equals(code)) {
                System.out.println(rm);
                return;
            }
        }
        message("Quarto não encontrado",100,true,false);
    }
    public static void seeRooms(ArrayList<Room> rooms){
        Scanner input=new Scanner(System.in);
        int opt=0,counter=0;
        message("Quartos ocupados",100,true,false);
        for(Room rm:rooms){
            row(100);
            System.out.printf("Código do quarto: %s%n",rm.getCode());
            System.out.printf("Responsável: %s%n",rm.getResponsible().getName());
        }
        row(100);
        do{ 
            if(counter>=1) message("Apenas (1) para voltar",100,true,true);
            counter++;
            System.out.print("(1) Para voltar:");
            opt=input.nextInt();
        }while(opt!=1);
    }
    public static boolean vacateRoom(ArrayList<Room>rooms,String code){
        for(Room rm:rooms){
            if(rm.getCode().equals(code)){
                rooms.remove(rm);
                message("Desocupado com sucesso!",100,true,false);
                return true;
            }
        }
        message("Quarto não encontrado!",100,true,false);
        return false;
    }
    public static void message(String msg,int sizeDashes,
               boolean showTopRow,boolean showBottomRow){
            String dashes="";
            while(dashes.length()<=sizeDashes){dashes+="-";}
            if(showTopRow)System.out.println(dashes);
            System.out.println(fillString(msg,sizeDashes));
            if(showBottomRow)System.out.println(dashes);
    }
    public static void row(int size){
        String dashes="";
        while(dashes.length()<=size){dashes+="-";}
        System.out.println(dashes);
    }
    public static String fillString(String str,int totalSpaces){
        while(str.length()<totalSpaces){
            if(str.length()%2==0) str+=" ";
            else str=" "+str;
        }
        return str;
    }
    public static String fillStringAtRight(String str,int finalSize){
            while(str.length()<finalSize){str+=" ";}
            return str;
    }
}
