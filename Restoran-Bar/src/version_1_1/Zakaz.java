package version_1_1;

import java.util.Vector;

public class Zakaz {
	
	int id_zakaz; 
	Summ suma_zakaza; 
	boolean payed_zakaz;  
	Vector<Menu> vectorRegistratedZakaz = new Vector();  
	String timeReception; 
	String timeDelivery;  
	Staff waiter;  
	
	public enum OrderStatus { ACCEPTED, IN_PROCESSING, ISSUED, CANCELED }
	
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
