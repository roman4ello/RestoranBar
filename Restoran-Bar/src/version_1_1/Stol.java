package version_1_1;

import java.text.SimpleDateFormat;
 import java.util.Date;
 import java.util.Random;
import java.util.Vector;

 
public class Stol {
	int uniqueNumberOfStol; 
	static int  count;
	int personSizeOfStol;  
	boolean freeInCurTimeStol;    
	boolean reserved;   
	String timeReserve;	 
	Client client;
	Zakaz zakaz;
	
	
	static Vector<Stol> vectroStolov = new Vector();
	static Vector<String> vectroStolovString = new Vector();
	
	public Stol() {
		super();
		this.uniqueNumberOfStol = count++;
		
		int tempRandomCount = new Random().nextInt(6);
		
		this.personSizeOfStol = tempRandomCount > 3 ? tempRandomCount : tempRandomCount+5;
 		this.freeInCurTimeStol = new Random().nextInt(2)>0? true:false ;
		this.reserved = new Random().nextInt(2)>0? false:true;
		
		if (reserved) {
			Date d = new Date(); 
	        SimpleDateFormat format1 = new SimpleDateFormat("dd.MM.yyyy HH:mm");
 		
			this.timeReserve = format1.format(d).toString();

		}//if
		else{
			this.timeReserve = "";

		}
		this.client = new Client("Петров", "Петр", "Петрович");
		this.zakaz = null;

		
	}

	public static void makesStoliInfo(){
		for (int i = 0; i < 20; i++) {
			vectroStolov.add(new Stol());
			vectroStolovString.add("Стол № "+vectroStolov.get(i).uniqueNumberOfStol);
		}//for
	}








	public static void main(String[] args) {
		// TODO Auto-generated method stub
		makesStoliInfo();
		
		for (Stol elem : vectroStolov) {
			System.out.println(elem.uniqueNumberOfStol+"  "+ elem.personSizeOfStol + elem.client.name+
					elem.client.surName+ elem.client.secondName+elem.freeInCurTimeStol+
					elem.timeReserve+elem.reserved);
		}//foreach
	}//main

}//class
