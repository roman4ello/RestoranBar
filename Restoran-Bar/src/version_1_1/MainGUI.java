package version_1_1;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.BevelBorder;
 
public class MainGUI extends JFrame {

	// Для Парсинга------------------------------------------------------------

	// Вектор имен горизонтального меню
	final public Vector<String> namesOfButtonsGorizontMenus = new Vector<String>();

	//основные блюда Меню Ресторана, TreeSet для автоматической сортировки по алфавиту
	final public TreeSet<String> mainMenuBluda = new TreeSet();	 

	// Меп блюд принадлежащих к одноименным группам (ключ = название подменю, value = список элентов данного подменю)
	final public Map<String,Vector<Menu>> mapGruppMenuBluda = new TreeMap<>();

	//основные пункты меню СТОЛЫ,TreeSet для автоматической сортировки по алфавиту
	final public TreeSet<String> mainMenuStoli = new TreeSet();	 
	
	// Меп (ключ = название подменю, value = список элентов данного подменю)
	//Vector<String> -чтобы порядок добавления сохранялся и у пользователя была возможность 
	//самому влиять на порядок доавления
	final public Map<String,Vector<String>> mapGruppMenuStoli = new TreeMap<>();
	
	
	
	// Для отрисовки ГУИ--------------------------------------------------
	// Запомнить размер экрана 
	final int x_screenSize = (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth();
	final int y_screenSize = (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight();

	// размер окна
	int x_size = 800; int y_size = 400;

	//конструктор JFrame
	public MainGUI(String pathToFileGorizontMenuNames, String pathTOspisok_menu,String pathTOstoli_menu)
			throws ClassNotFoundException, InstantiationException,
			IllegalAccessException, UnsupportedLookAndFeelException {
 		UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());

		setTitle("Меню системы РЕСТОРАН-БАР");
		setDefaultCloseOperation(EXIT_ON_CLOSE); // Диспоуз - закрыть прогу после закрытия ПОСЛЕДНЕГО ОКНА

		setLayout(new FlowLayout()); // включить ФлоуЛейаут() - простейший, размещает компоненты слева направо
		setSize(x_size, y_size);
		//размещение окна ГУИ по центру экрана
		setLocation((x_screenSize - x_size) / 2, (y_screenSize - y_size) / 2);
		drawJMenuButtonsInGUI(pathToFileGorizontMenuNames,
				        pathTOspisok_menu,pathTOstoli_menu);// нарисовать все компоненты из файла "filePath"

		setVisible(true);
 		  
		
	}// constructor

	public static void main(String[] args) throws ClassNotFoundException,
			InstantiationException, IllegalAccessException,
			UnsupportedLookAndFeelException {

		new MainGUI("d:\\MyTeamProject\\IO\\GUI_main_menu.ini",
					"d:\\MyTeamProject\\IO\\menu_restorana.ini",
					"d:\\MyTeamProject\\IO\\stoli_menu.ini");
	}
	
	//парсер горизонтального меню в namesOfButtonsGorizontMenus
	public Vector<String> parserReadMainButtonsMenu(String pathToFileGorizontMenuNames) throws IOException {
		// читаем БуфРидером и накапливаем необходимую инфу
		BufferedReader buffReader = new BufferedReader(new FileReader(pathToFileGorizontMenuNames));
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
			else if (str.contains("ГоризонтМеню:")) {
				str = str.substring("ГоризонтМеню:".length()).trim();
				StringTokenizer tokzer = new StringTokenizer(str, ",");

				// заносим в коллекцию namesGorizontMenus пункты главного меню
				while (tokzer.countTokens() > 0) {
					String str_temp = (String) tokzer.nextElement().toString()
							.trim();
					// System.out.print ("|" + str_temp );
					namesOfButtonsGorizontMenus.add(str_temp);
				}
				// System.out.println( );

			}// else горизонт меню
 
		}// for цикд чтения всего файла
		buffReader.close();
		return namesOfButtonsGorizontMenus;
	}
	
	//чтение меню ресторана в Меп mapGruppMenu 
	public  Map<String,Vector<Menu>> parserReadPointsOfMenuBluds(String pathTOspisok_menu) throws IOException{
/*
		МИНИ ИНСТРУКЦИЯ ПО ОФОРМЛЕНИЮ ДОКУМЕНТА
		 1. ### - указатель завершения блока
		 2. #Основные позиции меню: неизменяемое название
		 3. #Группа_"НАЗВАНИЕ ГРУППЫ МЕНЮ" (тут после #Группа_ -следует писать точное название группы меню, 
		    которое должно совпадать с одним из пунктов основого меню!
		 4. #КОНЕЦ_ФАЙЛА! - неизменяемая запись, которая указывает на окончание файла
		 5. Пример "АНТЛАНТИЧЕСКАЯ СЕЛЬДЬ = 200(гривен)/100(грамм)"  указывает, что:
		     1. после названия блюда должно всегда стоять "="
		     2. первым указвается число стоимости блюда в гривнах
		     3. вторым вес блюда в граммах
		     4. все числа только целые
		     5. формат записи через "/" неизменный,
		     6. можно менять только числа. Например: "280(гривен)/420(грамм)"
		 6. символ "//" - означает игнорировать данную запись в машинном коде 
*/
	
	
		//Парсер INI файла для порлучений Инфы о меню
			// читаем БуфРидером и накапливаем необходимую инфу
		BufferedReader buffReader = new BufferedReader(new FileReader(pathTOspisok_menu));
		String str = "";

		// чтение до тех пор пока строка не нулевая
		for (; (str = buffReader.readLine()) != null;) {
			
			// пустую строку не обраб
			if (str.trim().equals(""))	continue;			
			if (str.startsWith("//"))	continue;
			
			// если сстрока содержит КОНЕЦ_ФАЙЛА - прекратить чтение
			if (str.contains("#КОНЕЦ_ФАЙЛА!")) 	break;
	 			
			if (str.contains("#Основные позиции меню:")) {
 				
				while(!(str= buffReader.readLine()).contains("###") ){
					System.out.println(str);
					if (str.contains("#КОНЕЦ_ФАЙЛА!")) 	break;
					if (str.trim().equals(""))	continue;
					if (str.startsWith("//"))	continue;

					mainMenuBluda.add(str.trim());
				}//while
			}//if
//			System.out.println("str  ="+str);
	
			if (str.contains("#Группа_")) {
 				String key = str.substring("#Группа_".length(), str.indexOf(":"));
 				Vector<String> vectorTemp = new Vector<String>();
 				Vector<Menu> vectorMenu = new Vector< >();
				while(!(str= buffReader.readLine()).contains("###") ){
//					System.out.println(str);
					if (str.contains("#КОНЕЦ_ФАЙЛА!")) 	break;
					if (str.trim().equals(""))	continue;
					if (str.startsWith("//"))	continue;
					 
					String nameOfElementOfMenu = null;
					Integer costOfElementOfMenu = null;
					Integer weightOfElementOfMenu = null;
					try {
						nameOfElementOfMenu = str.substring(0,str.indexOf("=")).trim();
						costOfElementOfMenu = Integer.valueOf((str.substring( str.indexOf("=")+1, str.indexOf("(") ).trim()));
						weightOfElementOfMenu = Integer.valueOf((str.substring(str.indexOf("/")+1,str.lastIndexOf("(")).trim()));
					} catch (java.lang.StringIndexOutOfBoundsException e) {

						System.out.println("Не все поля заполнены");
					}
					
					Menu.count_uniq++;//нарасчиваем уникальный код блюда
					vectorMenu.add(new Menu(nameOfElementOfMenu, costOfElementOfMenu, weightOfElementOfMenu));
					vectorTemp.add(str.trim());
				}//while
				mapGruppMenuBluda.put(key, vectorMenu);
			}//if
						
		}// for цикд чтения всего файла
	
 		buffReader.close();
		return mapGruppMenuBluda;
	}//parserReadPointsOfMenu
			
 	//чтение меню Столы в Меп mapGruppMenuStoli 
	public  Map<String,Vector<Menu>> parserReadStolPoints(String pathTOstoli_menu) throws IOException{
 	
		//Парсер INI файла для порлучений Инфы о меню
			// читаем БуфРидером и накапливаем необходимую инфу
		BufferedReader buffReader = new BufferedReader(new FileReader(pathTOstoli_menu));
		String str = "";

		// чтение до тех пор пока строка не нулевая
		for (; (str = buffReader.readLine()) != null;) {
			
			// пустую строку не обраб
			if (str.trim().equals(""))	continue;			
			if (str.startsWith("//"))	continue;
			
			// если сстрока содержит КОНЕЦ_ФАЙЛА - прекратить чтение
			if (str.contains("#КОНЕЦ_ФАЙЛА!")) 	break;
	 			
			if (str.contains("#Столы:")) {
 				
				while(!(str= buffReader.readLine()).contains("###") ){
					System.out.println(str);
					if (str.contains("#КОНЕЦ_ФАЙЛА!")) 	break;
					if (str.trim().equals(""))	continue;
					if (str.startsWith("//"))	continue;

					mainMenuStoli.add(str.trim());
				}//while
			}//if
//			System.out.println("str  ="+str);
	
			if (str.contains("#Группа_")) {
 				String key = str.substring("#Группа_".length(), str.indexOf(":"));
 				Vector<String> vectorTemp = new Vector();
 				while(!(str= buffReader.readLine()).contains("###") ){
//					System.out.println(str);
					if (str.contains("#КОНЕЦ_ФАЙЛА!")) 	break;
					if (str.trim().equals(""))	continue;
					if (str.startsWith("//"))	continue;
					 
//					String nameOfElementOfMenu = null;
//					Integer costOfElementOfMenu = null;
//					Integer weightOfElementOfMenu = null;
//					try {
//						nameOfElementOfMenu = str.substring(0,str.indexOf("=")).trim();
//						costOfElementOfMenu = Integer.valueOf((str.substring( str.indexOf("=")+1, str.indexOf("(") ).trim()));
//						weightOfElementOfMenu = Integer.valueOf((str.substring(str.indexOf("/")+1,str.lastIndexOf("(")).trim()));
//					} catch (java.lang.StringIndexOutOfBoundsException e) {
//
//						System.out.println("Не все поля заполнены");
//					}
					
//					Menu.count_uniq++;//нарасчиваем уникальный код блюда
 					vectorTemp.add(str.trim());
				}//while
				mapGruppMenuStoli.put(key, vectorTemp);
			}//if
						
		}// for цикд чтения всего файла
	
 		buffReader.close();
		return mapGruppMenuBluda;
	}//parserReadPointsOfMenu	//----------------------------
		 
	// метод который рисует необходимые кнопки из инфы полученной после парсинга
	public void drawJMenuButtonsInGUI(	String pathToFileGorizontMenuNames,
										String pathTOspisok_menu,
										String pathTOstoli_menu) {
		 
			// прочитать файл
			try { parserReadMainButtonsMenu(pathToFileGorizontMenuNames);} 
			catch (IOException e1) {JOptionPane.showMessageDialog(null,
						"К сожалению указанного INI файла для формирования основной строки меню нет!");}
			try { 	parserReadPointsOfMenuBluds(pathTOspisok_menu);	} 
			catch (IOException e) {JOptionPane.showMessageDialog(null,
					"К сожалению такого INI файла Меню Ресторана Нет!");} 
			
			try { 	parserReadStolPoints( pathTOstoli_menu);}
			catch (IOException e) {JOptionPane.showMessageDialog(null, 
					"К сожалению таокго INI файла пункта Столы Нет!");} 
			

			// ВЕРХНЕЕ ГОРИЗОНТАЛЬНОЕ МЕНЮ - полоска меню - менюБар
			JMenuBar menuBar = new JMenuBar();

			// если есть хоть один пункт гориз меню
			if (! namesOfButtonsGorizontMenus.isEmpty()) {

				// присоединить объект МенюБара к окну ДжФреймовские
				setJMenuBar(menuBar);

				// цикл создания Горизонтальных Менюшек
				for (String elem_goriz_menu :  namesOfButtonsGorizontMenus) {

					//
					JMenu menuGorizontalnoe = new JMenu(elem_goriz_menu);
					menuGorizontalnoe.setFocusPainted(false);
					menuGorizontalnoe.setPreferredSize(new Dimension(x_size/namesOfButtonsGorizontMenus.size(), 1));
					menuGorizontalnoe.setBorder(new BevelBorder(BevelBorder.RAISED));
					// установить разделитель
					menuBar.add(new JSeparator(SwingConstants.VERTICAL));
					menuBar.add(menuGorizontalnoe);

					//===============================================================
				
 					// для меню Ресторана
					if (mainMenuBluda.size()>0 & elem_goriz_menu.equals("Меню Ресторана") )  {
						// Цикл создания Вертикальных менюшек
						for (String elem_vertik_menu : mainMenuBluda) {
							// System.out.println("elem_namesVertik_menu = "
							// +elem_vertik_menu);

							if (mapGruppMenuBluda.get(elem_vertik_menu).size() != 0) {

								menuGorizontalnoe.add(new JSeparator());

								JMenu subMenu = new JMenu(elem_vertik_menu);

								//вот тут добавляем пункты каждой группы 
								for (Menu elem_podmenu : mapGruppMenuBluda.get(elem_vertik_menu)) {
 									
									subMenu.add(elem_podmenu.nameOfElementOfMenu+ " - ("+
											elem_podmenu.costOfElementOfMenu+" гривен " +"/"+
											elem_podmenu.weightOfElementOfMenu+ " грамм)");

								}// foreach по подменюшкам

								menuGorizontalnoe.add(subMenu);

							} else {
								JMenuItem menuItem = new JMenuItem(elem_vertik_menu);
								menuGorizontalnoe.add(new JSeparator());
								menuGorizontalnoe.add(menuItem);

							}

							
						}// foreach создания Вертикальных менюшек

					}// if (ParserReadMenuFromFile.namesVertikMenus.get(elem)!=null)
					//===============================================================
				
					
					// для меню Столы
					else if (mainMenuStoli.size()>0 & elem_goriz_menu.equals("Столы") )  {
						// Цикл создания Вертикальных менюшек
						for (String elem_vertik_stoli_menu : mainMenuStoli) {
							  System.out.println("elem_vertik_stoli_menu = "
							  +elem_vertik_stoli_menu);

							  //Если уданной группы меню есть подпунткы
							if (mapGruppMenuStoli.get(elem_vertik_stoli_menu).size() != 0) {

								menuGorizontalnoe.add(new JSeparator());

								JMenu subMenu = new JMenu(elem_vertik_stoli_menu);

								//вот тут добавляем пункты каждой группы 
								for (String elem_podmenu : mapGruppMenuStoli.get(elem_vertik_stoli_menu)) {
									
									subMenu.add(elem_podmenu).addActionListener(new MyActionListener()); ;

								}// foreach по подменюшкам

								
								if (subMenu.getSize()!=null) {
									
									menuGorizontalnoe.add(subMenu);

								}//if

							} 
							
							//есди у группы подпунктов нет
							else {
								JMenuItem menuItem = new JMenuItem(elem_vertik_stoli_menu);
								menuGorizontalnoe.add(new JSeparator());
								menuGorizontalnoe.add(menuItem);

							}

							
						}// foreach создания Вертикальных менюшек

					}// if (ParserReadMenuFromFile.namesVertikMenus.get(elem)!=null)
					//===============================================================
					// когда у пункта гориз меню нет подпукта
					else {
						;
						 System.out.println("пустой");
					}// else

				}// foreach создания Горизонтальных Менюшек

			}// if
			else {
				JOptionPane.showMessageDialog(null,
								"К сожалению Вы не создали ни одного пункта главного меню!");
				System.exit(1);
			}
	 
	 
			// установки размера - setPreferredSize
			menuBar.setPreferredSize(new Dimension(1, 50)); // высота работает, а длина нет - длина
																// МепнюБара считается ВСЕ окно
	 				 
		 
		}// drawJMenuButtonsInGUI
		
		
	
	class MyActionListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
 	
					
			System.out.println(e.getActionCommand());
			
			if(e.getActionCommand().equals("создать новый заказ")){
							new GUI_zakaz("создание нового заказа");
			}
				
			 
		}//actionPerformed
		
	}//class MyActionListener
	
	
	
	
}//public class
