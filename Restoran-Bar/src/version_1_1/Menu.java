package version_1_1;

import java.util.Map;
import java.util.TreeMap;
import java.util.Vector;
 
public class Menu {
	
	//���������� ����� ����
	int uniqueIdOfElementOfMenu; 	// ����. ����� �����
 	String nameOfElementOfMenu; 	// �������� �����
	Integer costOfElementOfMenu; 	// ��������� �����
	Integer weightOfElementOfMenu; 	// ��� �����
	static int count_uniq = 0; // ����������� ���� ��� �����, ������� ����� ��� ������ ���������� ����� �������������
	
	//���, � ������� �������� ��� ����� �� ����������.
	//�.� ������ ��� - ��� ���� = �������� �����, Value = ������ � ������ ����� ����� 
	Map<String,Vector<String>> mapOfmenu =  new TreeMap<String, Vector<String>>();

	
	//����������� ����������� �����
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
