package examples;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ButtonGroup;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JSeparator;
import javax.swing.KeyStroke;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.BevelBorder;

//Создание КОНТЕКСТНОГО  Меню - которое появляется при клике ПРАВОЙ кнопкой мыши (ПКМ)


public class MenuTest3_PopUp_ContextMenu extends JFrame{

	

	public MenuTest3_PopUp_ContextMenu(String title) throws HeadlessException {
		super(title);
		// 
		setLocation(300, 300);
		setSize(500, 300);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE); //Диспоуз - закрыть программу после закрытия ПОСЛЕДНЕГО ОКНА

		// setLayout(null); //включить режим координат, но он нам не нужен
		setLayout(new FlowLayout()); // включить ФлоуЛейаут() - простейший,
                 										// размещает компоненты слева направо
		

		//------------------------------------------------------------------------УРОК 41 ---------------------------------------------------------------
	//Создание контекстного Меню.
	//1. Выловить клик ПКМ - Лисенером Мыши MouseListener
		//Сегодня  применяем методы ООП работы в команде - поэтому 
		//Создадим как бы чужой класс МаусЛисенера - см. конец файла
 //2. Присоединить чужой Лисенер к НУЖНОЙ КОМПОНЕНТЕ - в данном случае просто к окну
		this.addMouseListener( new  mouseListenerForPopUpContextMenu() ) ;
 //3. Для появления Контекстного меню надо его создать	- см. внутри обработчика ПКМ в конце файла	
		
		
		setVisible(true);
		
	}//constructor

	public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException {
		// TODO Auto-generated method stub
		UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		
		new MenuTest3_PopUp_ContextMenu("окно с МЕНЮ");
		
	} //main()

}  //public class


//Класс чужого обработчика  МаусЛисенера
//методом СНИЗУ_ВВЕРХ - сначала создадим собсно сам чужой обработчик
class mouseListenerForPopUpContextMenu  extends  MouseAdapter { //используем для удобства класс MouseAdapter
																//при этом implements надо заменить на extends,
																//т.к. после implements может идти ТОЛЬКО имя Интерфейса с фиолетовым значком в подсказочником
																//после extends может идти ТОЛКО ОДИН класс, т.к. в Джаве запрещено множественное наследование

	@Override
	public void mouseClicked(MouseEvent evt) {
		// TODO Auto-generated method stub
		//Выловить клик ПКМ
		if ( evt.getModifiers() == evt.BUTTON3_MASK) {
		//	if ( evt.getModifiersEx() == evt.BUTTON3_DOWN_MASK) {	
		//	if ( evt.getButton() == evt.BUTTON3) {	
			System.out.println("Была нажата ПКМ в той компонете,  К КОТОРОЙ ПОДКЛЮЧЕН этот Лисенер ");
			//если Лисенер будет подключен к нескольким компонентам, то придется внутри использовать еще 
			//if (evt.getSource()  или evt.getCommand() ) и т.д
			
			//фокус к мыши редко имеет отношение
			
			//3. Для появления Контекстного меню надо его создать	.
			//после клика ПКМ Создать собсно КОНТЕКСТНОЕ МЕНЮ
			JPopupMenu popUpContextMenu = new JPopupMenu();  //это аналог JMenu - вертикальное меню
			//добавляем в вертикальное попап меню конкретные пункты меню - менюАйтемы
//JMenuItem menuItem = new JMenuItem("Копировать");
//popUpContextMenu.add(menuItem);
					//вместо этих 2х строчек можно в одну строку 
			JMenuItem menuItemCopy = popUpContextMenu.add( new JMenuItem("Копировать") );   //.addActionListener( );
			popUpContextMenu.addSeparator();
			JMenuItem menuItemInsert = popUpContextMenu.add( new JMenuItem("Вставить") );   //.addActionListener( );
			
			//4. Показать Контекстное Попап меню
			popUpContextMenu.show(evt.getComponent(), 70, 70);  //2й 3й параметры - координаты верхнего левого угла Попап Контект меню
																					//1й параметр - это компонента, от верхнего левого угла которой отсчитваются координаты размещения меню
																		//Обычно 1й параметр - это та компонента, по которой был клик ПКМ
					//можно вывести Попап меню так, чтобы его верхний левый угол совпадал с позицией курсора
			popUpContextMenu.show(evt.getComponent(), evt.getX(), evt.getY()); 
				//JPopupMenu.setVisible ()  не имеет координат в параметре - поэтому выводит ПопапМеню только в верхнем Левом углу компоненты
			
			
			//5. Добавить к пунктам Попап меню Лисенеры с обработчиками
			menuItemCopy.addActionListener( new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					System.out.println("Был клик по меню КОПИРОВАТЬ  в Контекстном Попап меню");
				}
			}) ;
			
			menuItemInsert.addActionListener( new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					System.out.println("Был клик по меню ВСТАВИТЬ  в Контекстном Попап меню");
				}
			}) ;
			
		}//if
	} //Обработчик клика ПКМ

}


