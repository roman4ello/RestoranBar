package version_1_1;

import java.util.Map;
import java.util.TreeMap;
import java.util.Vector;

public class CopyZakaz {
	
	int id_zakaz; 
	Summ suma_zakaza; 
	Integer priceZakaz;
	boolean payed_zakaz;  
	Map<Integer,Vector<Menu>> mapMenusInZakaz = new TreeMap();  
	String timeReception; 
	String timeDelivery;  
	Staff officiant; 
	Stol stol= new Stol();
	static int counter = 0;
	Vector<CopyZakaz> vectorOfZakaz = new Vector();  

	public enum OrderStatus { ACCEPTED, IN_PROCESSING, ISSUED, CANCELED }
	
	
		
	
	
	public CopyZakaz(Vector<Menu> allMenusInZakazPar) {
		super();
		this.id_zakaz = ++counter;
		this.priceZakaz = priceZakaz;
		this.payed_zakaz = payed_zakaz;
		this.mapMenusInZakaz.put(this.id_zakaz,allMenusInZakazPar);
		this.timeReception = timeReception;
		this.timeDelivery = timeDelivery;
		this.officiant = officiant;
		this.vectorOfZakaz = vectorOfZakaz;
	}
	public CopyZakaz() {
		super();
		 
	}




	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}


	 
String strMenuTemp="";
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		if (mapMenusInZakaz.get(id_zakaz).size()>1) {
			Vector<Menu> tempMenu = mapMenusInZakaz.get(id_zakaz);
			
			for (Menu menu : tempMenu) {
				strMenuTemp =strMenuTemp +menu.toString();
 			}
		}
		else{
			strMenuTemp=mapMenusInZakaz.get(id_zakaz).get(0).toString();
		}
 		 		
		return "официант: "+officiant +"Заказ № " + id_zakaz + ", Сумма "+ priceZakaz +" оплачен-" + payed_zakaz +"\n"+
				
					strMenuTemp +  stol.uniqueNumberOfStol+"\n";
		
	}
	
	
	

}
