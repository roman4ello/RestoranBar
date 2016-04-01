package examples;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.ButtonGroup;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JSeparator;
import javax.swing.KeyStroke;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.BevelBorder;

//Обрабока кликов по  Меню
//Каждый пункт меню - это по сути Button

//Использование ООП и наследования для написания различных Лисенеров другими членами команды -
//позволяет в крупных проектах распределить работу между НЕСКОЛЬКИМИ программистами
//т.о. можно делать много лисенеров -наследников стандартного какого-то Лисенера 

public class JMenuTest2_Listeners extends JFrame implements ActionListener {

	

	public JMenuTest2_Listeners(String title) throws HeadlessException {
		super(title);
		// 
		setLocation(300, 300);
		setSize(500, 300);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE); //Диспоуз - закрыть программу после закрытия ПОСЛЕДНЕГО ОКНА

		// setLayout(null); //включить режим координат, но он нам не нужен
		setLayout(new FlowLayout()); // включить ФлоуЛейаут() - простейший,
                 										// размещает компоненты слева направо
		
		
		//ЗАДАЧА:  создать меню
		//КЛИЕНТЫ                       БАНКОМАТЫ                                           HELP
		//Создание                        Создание                                                  About
		//Удаление                        Удаление
		//Изменение                     Изменение 
		//Оплата по р/счету          Отк/Вкл
		//Оплата на БКарту          Занесение налички
		                                           //Забор налички инкассаторами
		
		//1. Создать ВЕРХНЕЕ ГОРИЗОНТАЛЬНОЕ полоску меню - менюБар
		JMenuBar  menuBar = new JMenuBar();  //параметр пустой
					//присоединить объект МенюБара к окну ДжФреймовские
		this.setJMenuBar(menuBar);
		setJMenuBar(menuBar);
				//в этом месте Меню в окне отсутствует - т.к. сам по себе JMenuBar - невидимый объект 
		
		//1.2  Наполнить МенюБар содержимым
		JMenu  menuVertik1 = new JMenu("КЛИЕНТЫ");
				//добавить к МенюБару
		menuBar.add(  menuVertik1 );
				//то же самое анонимным вариантом - НО ктакой вариант НЕ удобендл яработы, 
				//ЕСЛИ надо будет что-то в Вертикальное меню добавлять
		menuBar.add(   new JMenu("КЛИЕНТЫ2") );
		
		//2. Добавить К ВЕРТИКАЛЬНОМУ меню Клиенты его ПОДМЕНЮ-шки
		JMenuItem menuItem = new JMenuItem("Создание Клиента");
		menuVertik1.add( menuItem );
		menuItem.addActionListener(   this );  //Добавить Лисенер к менюАйтему я могу только здесь, 
																		//если один и тот ОБЪЕКТ menuItem используется для создания ВСЕХ пунктов меню
		
										//ОДИН объект menuItem используется для ВСЕХ менюшек
										//НО тогда подключать Лисенеры к этим меню НАДО СРАЗУ ЖЕ ТУТ ЖЕ,
										//т.к. потом это сделать будет трудно, если один menuItem для всем меню.
							menuItem = new JMenuItem("Изменение Клиента");
		menuVertik1.add( menuItem );
		menuItem.addActionListener( /*ранее писали имя класса с обработчиком Лисенера*/ new menuListenerChangeClientNotMy () );
		
							menuItem = new JMenuItem("Удаление Клиента");
		menuVertik1.add( menuItem );
		menuItem.addActionListener( new menuListenerDeleteClientNotMy() );  //если ЭТОТ чужой класс находится в другом пакете, то 
																							//компилятор подчеркнет его и тогда надо в 1й подсказке желтого подсказочника 
																							//его ИМПОРТИРОВАТЬ
		
		menuItem = new JMenuItem("Оплата по р/счету");
		menuVertik1.add( menuItem );
		menuItem = new JMenuItem("Оплата на БанкКарту");
		menuVertik1.add( menuItem );
		
		//Создадим 2-е вертикальное меню
		JMenu  menuVertik2 = new JMenu("BANKOMATS");
		//добавить к МенюБару
		menuBar.add(  menuVertik2 );
		
					//можно использовать тот же menuItem
					//НО анонимный new в параметре аддера использовать нельзя, 
					//т.к. потом надо будет приаддить Лисенер к КАЖДОМУ пункту меню
		menuItem = new JMenuItem("Создание Банкомата");
		menuVertik2.add( menuItem );
		
		menuItem = new JMenuItem("Изменение Банкомата");
		menuVertik2.add( menuItem );
		
		menuItem = new JMenuItem("Удаление Банкомата");
		menuVertik2.add( menuItem );
		
		menuItem = new JMenuItem("Откл/вкл");
		menuVertik2.add( menuItem );
		
		menuItem = new JMenuItem("Занесение налички");
		menuVertik2.add( menuItem );
		
		//Создание подменю --------------------------------------------------------------------------------------
		//ЗАДАЧА: создать вертикальное ПОДменю в ВертикМеню1  с пунктами Print, Import, Export  
		//Подменю традиционно ВО ВСЕХ  ФРЕЙМВОРКАХ во всех ОС  помечены СПРАВА треуголной стрелкой вправо
		//Перед подменю поставим СЕПАРАТОР
		menuVertik1.add( new JSeparator() );
		
		JMenu subMenu = new JMenu("Импорт/Экспорт");  //у этого подменю будет справа треугольная стрелочка
		menuVertik1.add(subMenu);  //стрелка справа появляется тогода, когда в аддере() к JMenu аддится JMenu 
		
		//Заполнить новое ПОДменю конкретными пунктами (Айтемами)
				//Добавим к подменю пункты с ЧЕКБОКСОМ
		JCheckBoxMenuItem menuCheck = new JCheckBoxMenuItem("Print");
				//ВНИМАНИЕ - чекбокс появляется только когда кликнуть по пункту меню.
		subMenu.add( menuCheck );
		
				//Добавим две радио-кнопки
		JRadioButtonMenuItem menuRadioButt1 = new JRadioButtonMenuItem("Import");
		JRadioButtonMenuItem menuRadioButt2 = new JRadioButtonMenuItem("Export");
		subMenu.add( menuRadioButt1 );
		subMenu.add( menuRadioButt2 );
		ButtonGroup rbGroup = new ButtonGroup();
		rbGroup.add(menuRadioButt1);
		rbGroup.add(menuRadioButt2);
		
		//Добавить АКСЕЛЕРАТОРЫ
		//Акселераторы - это КЛАВИША, которая нажимается в комбинации с модификатором (Контрол или Алт или Шифтом)
		//при нажатии этой комбинации срабатывает имитация клика по меню
		//ЗАДАЧА:  сопоставить пункту меню Импорт комбинацию клавиш Контрол-I
		menuRadioButt1.setAccelerator( KeyStroke.getKeyStroke( 'I', KeyEvent.CTRL_MASK));
		menuRadioButt2.setAccelerator( KeyStroke.getKeyStroke( 'E', KeyEvent.CTRL_MASK));
		
		//Назначение МНЕМОНИКИ
		//Мнемоника - комбинация Буквы а Алтом
		//Буква для мнемоники ДОЛЖНА содержаться в Слове в названии Меню - 
		//и это буква в меню ПОДЧЕРКИВАЕТСЯ. (подчеркивание иногда не делается, т.к. оно зависит
		//от стиля ОС, и такие мелочи могут конфликтовать или не учитываться).
		//ЗАДАЧА: назначить для мнемоники меню печати букву 'P'
		menuCheck.setMnemonic('P');  //срабатывание мнемоники простых меню требует доп. настройки
		menuVertik2.setMnemonic('B');  //мнемоника у пунктов в верхней Баре меню работает без доп. настроек.
		
		//Оформление меню ------------------------------------------------------------------------------------
		//1. Цвет фона и текста
			//Линейка горизонтальная менюБара
		menuBar.setBackground( Color.RED);  //работает в установках по умолчанию в Джаве машине 
																		//только не на территории пунктов меню вертикальных при установках Винды
		menuBar.setForeground( Color.RED);   //не работает в установках по умолчанию в Джаве машине
																			//работает в установках Винды
				//вертикальное меню - название в сотаве МенюБара
				//обе установки работают в  режиме Windows (устанавливаемый с пом. UIManager) 
		menuVertik1.setBackground(Color.yellow);    //не работает в установках по умолчанию в Джаве машине
		menuVertik1.setForeground(Color.GREEN);  //работает даже  в установках по умолчанию в Джаве машине
				//конкретный пункт меню - всегда работают обе установки
		menuItem.setBackground(Color.RED);
		menuItem.setForeground(Color.GREEN);
		
				//установки размера - setPreferredSize
		menuBar.setPreferredSize( new Dimension(150, 50));  //высота работает, а длина нет - длина МепнюБара считается ВСЕ окно
		menuVertik1.setPreferredSize(new Dimension(100, 70)); //работает только длина, высота нет
		menuItem.setPreferredSize(new Dimension(150, 60));     //работает И высота И ширина
		
				//выпуклость и вжатость пунктов меню - напоминание и краткий метод
		menuVertik1.setBorder( new BevelBorder(BevelBorder.RAISED ));		//выпуклый весь пункт меню
		menuVertik2.setBorder( new BevelBorder(BevelBorder.LOWERED ));  //утопленный весь пункт меню
		
			//настройка шрифтов - простая и без эксцессов
		
		
		//------------------------------------------------------------------------УРОК 41 ---------------------------------------------------------------
		//Каждый пункт меню - это по сути Button
		//ЗАДАЧА:  добавить пункт меню ВЫХОД в1е вертикальное меню
		//                  и подключить к этому пункту Анонимный Лисенер и собственно процесс выхода из проги.
		//1. Добавить пункт меню
	//	menuItem = new JMenuItem("ВЫХОД");
	//	menuVertik1.add(menuItem);
		//2. Добавить обработку клика по меню ВЫХОД Лисенером
		//menuItem.addActionListener( new  ActionListener() {
				//3 строки выше можно заменить одной строкой
		menuVertik1.add(  new JMenuItem("ВЫХОД")  ) .addActionListener(  new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				System.out.println("Обработка клика по меню ВЫХОД");
				//return;   //позволяет выйти из проги ТОЛЬКО, если он написан внутри майна()
				
							//ДО выхода часто полезно писать в файлы-логи инфу об ошибки или коде окончания.
				System.exit( 0 );  //в параметре код окончания проги - 0 - это все нормально, отриц. число - это ошибка
											//рекомендуется при аварийном выходе из проги писать такие exit-ы с уникальным кодом ошибки
				int x =0;  //ЭТОТ КОД ПОСЛЕ exit()   НИКОГДА не будет выполнен, но компилятор молчит 
			}
		}) ;
	
			//При повторном использовании такой строки будет НЕ подключение второго Лисенера к менюАйтему, 
			//А  будет созданы  ВТОРОЙ  пункт меню ВЫХОД (в чем нет логики) и к этому второму менюАйтему будет добавлен новый Лисенер 
	//menuVertik1.add(  new JMenuItem("ВЫХОД")  ) .addActionListener(  new ActionListener() {
			
			//Чтобы добавить второй Лисенер к любой компоненте - надо иметь ОБЪЕКТ-переменную этой компоненты.
			//к анонимной компоненте добавить второй Лисенер НЕ получится, и ВООБЩЕ ничего ПОТОМ сделать с анонимной компонентой НЕЛЬЗЯ
		
		//ЗАДАЧА: Подключить к первому пункту меню  "СОЗДАНИЕ  КЛИЕНТА"  Лисенер с помощью слова this
		//НО т.к. при добавлении менюАйтемов мы использовали один и тот объект menuItem ДЛЯ ВСЕХ ПУНКТОВ МЕНЮ,
		//то в этом месте прои мы УЖЕ НЕ МОЖЕМ Лисенер к кокретному пункту меню. 
		//ПОЭТОМУ эту задачу можно решать ТОЛЬКО  РАНЬШЕ, 
		//ТАМ ГДЕ СОЗДАВАЛСЯ  пункт меню  "СОЗДАНИЕ  КЛИЕНТА"  - см. строку 67 исходника
		
		
		setVisible(true);
		
	}//constructor

	public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException {
		// TODO Auto-generated method stub
		UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		
		new JMenuTest2_Listeners("окно с МЕНЮ");
		
	} //main()

	
	//Это обработчик для addActionListener(со словом   this)
	@Override    //Этот Обработчик ИСПОЛЬЗУЕТСЯ ДЛЯ ВСЕХ ПУНКТОВ МЕНЮ, 
						//к которым применялся addActionListener(this) -- поэтому в это варианте для определения
						//источника события надо использовать if( event.getSource() == ... какая-то компонента)
				//НО если дл всех пунктов меню использовался один и тот menuItem , то придется юзать
				//в  if( event.getActionCommand().equals( название нужного пунка меню  )   )
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		System.out.println("Обработка клика по меню СОЗДАНИЕ   КЛИЕНТА методом класса this");
	}

}  //public class

//здесь будет создан чужой класс  для обработки другого пункта меню ИЗМЕНЕНИЕ КЛИЕНТА
//при создании методом СВЕРХУ-ВНИЗ - сначала ВВЕРХУ там где создавался пункт меню ИЗМЕНЕНИЕ КЛИЕНТА - 
//добавляется addActionListener(  какой-то ЧУЖОЙ   класс ) 
//чисто чтоб легче было объяснить тему, я ЭТОТ ЧУЖОЙ КЛАСС размещу в этом же файле, чтоб все было в одном файле
class  menuListenerChangeClientNotMy implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		System.out.println("Чужой класс Лисенера , обрабатывающий клик по меню ИЗМЕНЕНЕИЕ КЛИЕНТА ");
			
	}
}  //class  menuListenerChangeClientNotMy


//То же самое можно сделать методом СНИЗУ-ВВЕРХ
//ЗАДАЧА: сделать обработку клика по меню  УДАЛЕНИЕ  КЛИЕНТА  с помощью ЧУЖОГО КЛАССА ЛИСЕНЕРА
//1. Сначала пишется ЧУЖОЙ КЛАСС
class  menuListenerDeleteClientNotMy implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		System.out.println("Чужой класс Лисенера , обрабатывающий клик по меню УДАЛЕНИЕ  КЛИЕНТА ");
	}
	
}
//2. После написания обработчика, мы ВВЕРХУ там где создавался пункт меню УДАЛЕНИЕ  КЛИЕНТА
//ДОБАВЛЯЕМ  этот Лисенер к нашему пункту меню УДАЛЕНИЕ  КЛИЕНТА