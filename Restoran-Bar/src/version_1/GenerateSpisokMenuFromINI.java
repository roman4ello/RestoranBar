package version_1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.TreeMap;
import java.util.Vector;
import javax.swing.UnsupportedLookAndFeelException;


public class GenerateSpisokMenuFromINI     {

	
	String positionMenuName;
	Integer suma;
	Integer weight;
		 
	final static   Vector<String> mainMenuPositions = new Vector<String>();

	// ��� (���� = �������� �������, value = ������ ������� ������� �������)
	final static   Map<String, Vector<String>> namesSubMenus_Map = new TreeMap();
		
 
	public static void readPointsOfMenu() throws IOException{
		
		//������ INI ����� ��� ���������� ���� � ����
			// ������ ���������� � ����������� ����������� ����
		BufferedReader buffReader = new BufferedReader(new FileReader("d:\\MyTeamProject\\spisok_menu.ini"));
		String str = "";

		// ������ �� ��� ��� ���� ������ �� �������
		for (; (str = buffReader.readLine()) != null;) {
			// ������ ������ �� �����
			if (str.trim().equals(""))
				continue;
			// ���� ������� �������� �����_����� - ���������� ������
			if (str.contains("#�����_�����!")) {
				break;
			}// if

			// ����
			// ��������������===============================================
			else if (str.contains("�������� ������� ����:")) {
				str = str.substring("�������� ������� ����:".length()).trim();
				StringTokenizer tokzer = new StringTokenizer(str, ",");

				// ������� � ��������� namesGorizontMenus ������ �������� ����
				while (tokzer.countTokens() > 0) {
					String str_temp = (String) tokzer.nextElement().toString()
							.trim();
				// System.out.print ("|" + str_temp );
					mainMenuPositions.add(str_temp);
				}
				// System.out.println( );

			}// else �������� ����

			// = ��������� ���� � ������� ����===========================
			else if (str.contains("������_")) {

				for (String elem : mainMenuPositions) {

					if (str.contains("������_" + elem + ":")) {
						str = str.substring( ("������_" + elem + ":").length() ).trim();
						StringTokenizer tokzer = new StringTokenizer(str, ",");

						// ��������� ������ ��� ���������� ���� 
						Vector<String> tempVector = new Vector<String>();

						// ������� � ��������� ������ ������� ������ ����
						while (tokzer.countTokens() > 0) {
							String str_temp = (String) tokzer.nextElement()
									.toString().trim();
							// System.out.print("|" + str_temp );
							tempVector.add(str_temp);

						}
						// ��������� �������� ������� ��������� � ��� ���������
						// ������� � ������ = ������ ��������� ����
						namesSubMenus_Map.put(elem, tempVector);
						// System.out.println("namesVertikMenus ="+namesVertikMenus
						// );
					}

				}// foreach

			}// else if

			 

		}// for ���� ������ ����� �����
	
	
		
		
	}
	
	
		void printPointsMenu(){
			System.out.println("�������� ���� =" + mainMenuPositions);
			System.out.println("\n" + "����� ����� ����:");
			
			for (Map.Entry<String, Vector<String>> elem : namesSubMenus_Map.entrySet()) {
				System.out.println(elem);
			}// foreach

		}
	
	
	public static void main(String[] args) throws ClassNotFoundException,
			InstantiationException, IllegalAccessException,
			UnsupportedLookAndFeelException, IOException {
 
			
			 

		
	}//main

	
	 
}//public class
