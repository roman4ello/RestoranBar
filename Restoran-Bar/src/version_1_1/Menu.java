package version_1_1;

import java.util.Map;
import java.util.TreeMap;
import java.util.Vector;
 
public class Menu {
	
	//порядковый номер меню
	int uniqueIdOfElementOfMenu; 	// уник. номер блюда
 	String nameOfElementOfMenu; 	// названия блюда
	Integer costOfElementOfMenu; 	// стоимость блюда
	Integer weightOfElementOfMenu; 	// вес блюда
	static int count_uniq = 0; // статический уник код блюда, который будет при каждом добавлении блюда нарасчиваться
	
	//Меп, в котором хранятся все блюда со свойствами.
	//Т.е каждый меп - это ключ = названию блюда, Value = вектор с полями этого блюда 
	Map<String,Vector<String>> mapOfmenu =  new TreeMap<String, Vector<String>>();

	
	//коснтруктор конкретного блюда
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
