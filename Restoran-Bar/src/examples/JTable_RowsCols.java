package examples;
//операции со строками и колонками
//добавление и удаление строк (колонок)



import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.HeadlessException;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;


public class JTable_RowsCols extends JFrame   {

	static Object[][] data = {  //тип Object - т.к. в таблицах часто Объекты РАЗНЫХ ТИПОВ
											//т.к. в Джаве действует КО-вариантность: "Наследник ВСЕГДА СВОБОДНО ПРЕОБРАЗУЕТСЯ В Родителя"
											//Вообще-то этот вариант работы с типом Object плохой, т.к. НЕТ КОНТРОЛЯ типов
											//но применим, если работать внимательно
		{ "Оплата внутри банка", 1000,                        "2014.03.17", "10:17:40" },
		{ "Выдача налички",        new Integer(2000), "2014.03.17", "10:15:60" },
		{ "Оплата внутри банка",new Integer(3000), "2014.03.17", "13:18:30"},
		{ "Прием налички ",         new Integer(4000), "2014.03.17", "14:12:40" },
		{ "Оплата внутри банка",new Integer(5000), "2014.03.17", "11:15:60" },
		{ "Оплата с банк-карты",new Integer(6000), "2014.03.17", "09:11:00" },
		{ "Снятие с банк-карты ",new Integer(7000),"2014.03.17", "15:17:40" }
		};
	
		//массив заголовков
	String [ ]   colNames =  {"Тип операции", "Сумма", "Дата", "Время"};
	
			//создать Объект Таблицы
	JTable table = new JTable(data, colNames);
	
	Color oldColor = table.getSelectionBackground();  //цвет фона для обработчика tableChanged
	
	
	public JTable_RowsCols(String title) throws HeadlessException { // 'Эксепшн событий внешнего мира
																	
		super(title);

		setLocation(300, 300);
		setSize(500, 300);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE); //Диспоуз - закрыть программу после закрытия ПОСЛЕДНЕГО ОКНА

		// setLayout(null); //включить режим координат, но он нам не нужен
		setLayout(new FlowLayout()); // включить ФлоуЛейаут() - простейший,
                 										// размещает компоненты слева направо


		//Сделать полосы прокрутки
		JScrollPane  scrollPan = new JScrollPane(table);
		scrollPan.setPreferredSize( new Dimension(400, 100) );
		
		table.setPreferredScrollableViewportSize(new Dimension(400, 100));
		add(scrollPan);
	
//add(table);		//такой вывод таблицы ьез панели скролинга не показывает названия столбцов
					
		//------------------------------------------------------УРОК 40 ------------------------------------------------------------------------------------------------
		setVisible(true);
		
			//1.  ПЕРЕДВИЖЕНИЕ  КОЛОНОК
	table.setCellSelectionEnabled( true);  //вкл. режим выделения ячейки
			//2 способа - через Тэбл  и через модель колонок
	table.moveColumn(0, 2);
	table.getColumnModel().moveColumn(0, 3);
			//метода передвижения Строк нет
	//table.getModel().
	
		//2. УДАЛЕНИЕ КОЛОНОК
			//2 способа
	table.removeColumn( /*Объект колонки*/  table.getColumnModel().getColumn(2)  );
	//table.getColumnModel().removeColumn(   table.getColumnModel().getColumn(2)  );
	
			//3. ВСТАВКА КОЛОНОК
	//table.addColumn( /* Объект колонки*/);  //в параметре нужен объект колонки, а с новой инфой его надо создавать
	
	//В реале ЧАЩЕ как раз юзеру дается возможность вставлять-удалять НЕ колонки , А СТРОКИ
	//УДОБНО ВСЕ ОПЕРАЦИИ  И  с колонками И со строками делаются с помощью
	//ДЕФОЛТНОГО класса  ДефолтнойМоделиТаблицы 
	DefaultTableModel  defTableModel = new DefaultTableModel(data, colNames);
			//Соединить созданный нами Объект Модели с нашей ГУИ компонентой table, которая изображена в окне
	table.setModel(defTableModel);
			
	//3. ВСТАВКА  КОЛОНКИ
	defTableModel.addColumn("5я колонка");
	table.moveColumn(4, 2);
			//ВСТАВКА  КОЛОНКИ С ДАННЫМИ
	String [ ] arrDataForCol = {"упя", "опс", "вау", "йопт", "охрентипедь" };
	defTableModel.addColumn("6я колонка", arrDataForCol);
	//defTableModel.re
	
			//Drag & Drop - тащить и скинуть - протаскивание мышью с нажатой левой кнопкой
	//Drag - вытащить содержимое компоненты со своей територии - (просто)
	//Drop - разместить содержимое ввнутри своей компоненты - (тяжелее)
	table.setDragEnabled( true);
	
	
	
	} // construcror

	public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException {
		// TODO Auto-generated method stub
		
		//Сделать Внешний вид компонент как в ОС
UIManager.setLookAndFeel(   UIManager.getSystemLookAndFeelClassName());
		
		
		//это пример АНОНИМНОГО ОБЪЕКТа - он анонимный, т.к. у него нет имени
							//Анонимы используют 1 раз в проге, поэтому имя им не нужно
		 new JTable_RowsCols("JTable");

	}//main()


}// class
