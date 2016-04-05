package version_1_1;

import java.util.Map;
import java.util.TreeMap;
import java.util.Vector;
 
public class Menu {
	
	//совйства = поля меню
	int uniqueIdOfElementOfMenu; 	// уник номер меню
 	String nameOfElementOfMenu; 	// название меню
	Integer costOfElementOfMenu; 	// стоимость
	Integer weightOfElementOfMenu; 	// вес
	static int count_uniq = 0; //  счетчик
	
	//Меп меню
	//Ключ - одно из названий группы меню, Value = подменю этой группы 
	Map<String,Vector<String>> mapOfmenu =  new TreeMap<String, Vector<String>>();

	
	//Конструктор меню
	public Menu(  String nameOfElementOfMenu, Integer costOfElementOfMenu, 
			      Integer weightOfElementOfMenu    ) {
		super();
		this.nameOfElementOfMenu = nameOfElementOfMenu;
		this.costOfElementOfMenu = costOfElementOfMenu;
		this.weightOfElementOfMenu = weightOfElementOfMenu;
		this.uniqueIdOfElementOfMenu = +count_uniq;

	}
	
	
	
	
 	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		
	}//main

}//public class
