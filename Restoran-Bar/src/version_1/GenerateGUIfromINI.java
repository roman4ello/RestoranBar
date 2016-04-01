package version_1;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.TreeMap;
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
 
public class GenerateGUIfromINI extends JFrame {

	// Для Парсинга------------------------------------------------------------

	// Вектор имен горизонтального меню
	final   public Vector<String> namesGorizontMenus = new Vector<String>();

	// МЕП (ключ = название меню, value = список элентов данного меню) -
	// чтобы было удобно искать список пунктов меню, кот-е к нему относятся
	final   Map<String, Vector<String>> namesVertikMenus = new TreeMap();

	// Меп (ключ = название подменю, value = список элентов данного подменю)
	final   Map<String, Vector<String>> namesSubMenus_Map = new TreeMap();

	
	
	// Для отрисовки ГУИ--------------------------------------------------
	// размер экрана
	final int x_screenSize = (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth();
	final int y_screenSize = (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight();

	// размер окна
	int x_size = 800; int y_size = 400;

	//конструктор JFrame
	public GenerateGUIfromINI(String filePath)
			throws ClassNotFoundException, InstantiationException,
			IllegalAccessException, UnsupportedLookAndFeelException {
 		UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());

		setTitle("Меню системы РЕСТОРАН-БАР");
		setDefaultCloseOperation(DISPOSE_ON_CLOSE); // Диспоуз - закрыть прогу после закрытия ПОСЛЕДНЕГО ОКНА
		setLayout(new FlowLayout()); // включить ФлоуЛейаут() - простейший, размещает компоненты слева направо
		setSize(x_size, y_size);
		//размещение окна ГУИ по центру экрана
		setLocation((x_screenSize - x_size) / 2, (y_screenSize - y_size) / 2);
		drawJMenuButtonsInGUI(filePath);// нарисовать все компоненты из файла "filePath"
		setVisible(true);

	}// constructor

	public static void main(String[] args) throws ClassNotFoundException,
			InstantiationException, IllegalAccessException,
			UnsupportedLookAndFeelException {

		new GenerateGUIfromINI("d:\\MyTeamProject\\menu.ini");
	}

	
	//Парсер INI файла для порлучений Инфы о меню
	public void parserFromIni(String fileName) throws IOException {
		// читаем БуфРидером и накапливаем необходимую инфу
		BufferedReader buffReader = new BufferedReader(new FileReader(fileName));
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
					namesGorizontMenus.add(str_temp);
				}
				// System.out.println( );

			}// else горизонт меню

			// = МЕНЮ ВЕРТИКАЛЬНОЕ===========================
			else if (str.contains("МенюВертик")) {

				for (String elem : namesGorizontMenus) {

					if (str.contains("МенюВертик_" + elem + ":")) {
						str = str.substring(
								("МенюВертик_" + elem + ":").length()).trim();
						StringTokenizer tokzer = new StringTokenizer(str, ",");

						// временный вектор для накопления инфы о ВертикМеню
						Vector<String> tempVector = new Vector<String>();

						// заносим в коллекцию namesVertikMenus пункты текущего
						// ВертикМеню
						while (tokzer.countTokens() > 0) {
							String str_temp = (String) tokzer.nextElement()
									.toString().trim();
							// System.out.print("|" + str_temp );
							tempVector.add(str_temp);

						}
						// занесение текущего ВертикМеню в Меп Вертикальных
						// менюшек с ключом = пункту Гориз.меню
						namesVertikMenus.put(elem, tempVector);
						// System.out.println("namesVertikMenus ="+namesVertikMenus
						// );
					}

				}// foreach

			}// else if

			// ПОДМЕНЮ=======================================================
			else if (str.contains("Подменю_")) {

				// цикл по векторам Вертикальных меню, кот содержат названия
				// ПОДМЕНЮ
				for (Vector<String> elem_vector : namesVertikMenus.values()) {

					// цикл по самим названиям ПОДМЕНЮ
					for (String elem : elem_vector) {

						// Если строка содержит Подменю_"НАЗВАНИЕ КОНКРЕТНОГО
						// ПУНКТА Меню
						// из списка пунктов вертикального меню"
						if (str.contains("Подменю_" + elem + ":")) {

							// начать чтение после этого названия
							str = str.substring(
									("Подменю_" + elem + ":").length()).trim();
							StringTokenizer tokzer = new StringTokenizer(str,
									",");

							// временный вектор для накопления инфы о ПодМеню
							Vector<String> tempVector = new Vector<String>();

							// заносим в вектор tempVector пункты текущего
							// подменю
							while (tokzer.countTokens() > 0) {

								String str_temp = (String) tokzer.nextElement()
										.toString().trim();
								// System.out.print("|" + str_temp );
								tempVector.add(str_temp);

							}// while

							// занесение текущего ПодМеню в 2-мерный вектор всех
							// Подменю
							// System.out.println( );
							namesSubMenus_Map.put(elem, tempVector);
						}//

					}// foreach String elem

				}// foreach Vector<String> elem_vector

			}// else if

		}// for цикд чтения всего файла

	}

	//Вывод на консоль содержимого коллекций о полученой Инфе
	public void printCollections() {

		System.out.println("Горизонтальные меню =" + namesGorizontMenus);

		System.out.println("\n" + "Вертикальные меню:");
		for (Map.Entry<String, Vector<String>> elem : namesVertikMenus
				.entrySet()) {
			System.out.println(elem);

		}// foreach

		System.out.println("\n" + "Подменю:");

		for (Map.Entry<String, Vector<String>> elem : namesSubMenus_Map
				.entrySet()) {
			System.out.println(elem);

		}// foreach

	}

	// метод который рисует необходимые кнопки из инфы полученной после парсинга
	public void drawJMenuButtonsInGUI(String filePath) {
		// "d:\\MyTeamProject\\menu.ini"
		// прочитать файл
		try { parserFromIni(filePath);} 
		catch (IOException e1) {JOptionPane.showMessageDialog(null,
					"К сожалению указанного INI файла нет!");}
		 

		// ВЕРХНЕЕ ГОРИЗОНТАЛЬНОЕ МЕНЮ - полоска меню - менюБар
		JMenuBar menuBar = new JMenuBar();

		// если есть хоть один пункт гориз меню
		if (! namesGorizontMenus.isEmpty()) {

			// присоединить объект МенюБара к окну ДжФреймовские
			setJMenuBar(menuBar);

			// цикл создания Горизонтальных Менюшек
			for (String elem_goriz_menu :  namesGorizontMenus) {

				//
				JMenu menuGorizontalnoe = new JMenu(elem_goriz_menu);
				menuGorizontalnoe.setFocusPainted(false);
				menuGorizontalnoe.setPreferredSize(new Dimension(x_size/namesGorizontMenus.size(), 1));
				menuGorizontalnoe.setBorder(new BevelBorder(BevelBorder.RAISED));
				// установить разделитель
				menuBar.add(new JSeparator(SwingConstants.VERTICAL));
				menuBar.add(menuGorizontalnoe);

				// если у текущего горизонт меню есть пункты вертикального меню
				if (namesVertikMenus.get(elem_goriz_menu) != null) {

					// Цикл создания Вертикальных менюшек
					for (String elem_vertik_menu : namesVertikMenus.get(elem_goriz_menu)) {
						// System.out.println("elem_namesVertik_menu = "
						// +elem_vertik_menu);

						if (namesSubMenus_Map.get(elem_vertik_menu) != null) {

							menuGorizontalnoe.add(new JSeparator());

							JMenu subMenu = new JMenu(elem_vertik_menu);

							for (String elem_podmenu : namesSubMenus_Map.get(elem_vertik_menu)) {

								subMenu.add(elem_podmenu);

							}// foreach по подменюшкам

							menuGorizontalnoe.add(subMenu);

						} else {
							JMenuItem menuItem = new JMenuItem(elem_vertik_menu);
							menuGorizontalnoe.add(menuItem);

						}

						if ( namesSubMenus_Map.get(elem_vertik_menu) != null) {
							// цикл по подменюшкам

						}// if
						else {
							;
						}

					}// foreach создания Вертикальных менюшек

				}// if (ParserReadMenuFromFile.namesVertikMenus.get(elem)!=null)

				// когда у пункта гориз меню нет подпукта
				else {
					;
					// System.out.println("пустой");
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

}//public class
