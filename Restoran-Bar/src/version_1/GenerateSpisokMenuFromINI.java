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

	// Меп (ключ = название подменю, value = список элентов данного подменю)
	final static   Map<String, Vector<String>> namesSubMenus_Map = new TreeMap();
		
 
	public static void readPointsOfMenu() throws IOException{
		
		//Парсер INI файла для порлучений Инфы о меню
			// читаем БуфРидером и накапливаем необходимую инфу
		BufferedReader buffReader = new BufferedReader(new FileReader("d:\\MyTeamProject\\spisok_menu.ini"));
		String str = "";

		// чтение до тех пор пока строка не нулевая
		for (; (str = buffReader.readLine()) != null;) {
			// пустую строку не обраб
			if (str.trim().equals(""))
				continue;
			// если сстрока содержит КОНЕЦ_ФАЙЛА - прекратить чтение
			if (str.contains("#КОНЕЦ_ФАЙЛА!")) {
				break;
			}// if

			// МЕНЮ
			// ГОРИЗОНТАЛЬНОЕ===============================================
			else if (str.contains("Основные позиции меню:")) {
				str = str.substring("Основные позиции меню:".length()).trim();
				StringTokenizer tokzer = new StringTokenizer(str, ",");

				// заносим в коллекцию namesGorizontMenus пункты главного меню
				while (tokzer.countTokens() > 0) {
					String str_temp = (String) tokzer.nextElement().toString()
							.trim();
				// System.out.print ("|" + str_temp );
					mainMenuPositions.add(str_temp);
				}
				// System.out.println( );

			}// else горизонт меню

			// = подпункты меню в составе груп===========================
			else if (str.contains("Группа_")) {

				for (String elem : mainMenuPositions) {

					if (str.contains("Группа_" + elem + ":")) {
						str = str.substring( ("Группа_" + elem + ":").length() ).trim();
						StringTokenizer tokzer = new StringTokenizer(str, ",");

						// временный вектор для накопления инфы 
						Vector<String> tempVector = new Vector<String>();

						// заносим в коллекцию пункты текущей группы меню
						while (tokzer.countTokens() > 0) {
							String str_temp = (String) tokzer.nextElement()
									.toString().trim();
							// System.out.print("|" + str_temp );
							tempVector.add(str_temp);

						}
						// занесение текущего вектора группМеню в Меп ГруппМеню
						// менюшек с ключом = пункту Основного меню
						namesSubMenus_Map.put(elem, tempVector);
						// System.out.println("namesVertikMenus ="+namesVertikMenus
						// );
					}

				}// foreach

			}// else if

			 

		}// for цикд чтения всего файла
	
	
		
		
	}
	
	
		void printPointsMenu(){
			System.out.println("основное меню =" + mainMenuPositions);
			System.out.println("\n" + "пукты групп меню:");
			
			for (Map.Entry<String, Vector<String>> elem : namesSubMenus_Map.entrySet()) {
				System.out.println(elem);
			}// foreach

		}
	
	
	public static void main(String[] args) throws ClassNotFoundException,
			InstantiationException, IllegalAccessException,
			UnsupportedLookAndFeelException, IOException {
 
			
			 

		
	}//main

	
	 
}//public class
