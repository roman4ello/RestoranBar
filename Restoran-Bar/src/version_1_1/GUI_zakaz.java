package version_1_1;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.HeadlessException;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.UnsupportedLookAndFeelException;

public class GUI_zakaz extends JFrame {

	int x_screenSize = (int) Toolkit.getDefaultToolkit().getScreenSize()
			.getWidth(), y_screenSize = (int) Toolkit.getDefaultToolkit()
			.getScreenSize().getHeight();
	int x_sizeOfGUI = 800, y_sizeOfGUI = 400;

	JList dirList = new JList();
	Vector<String> vectListContent = new Vector();
	Vector<Zakaz> vectorZakazov = new Vector();
	Vector<Menu> vectorMenu = new Vector();
	static Integer allSummaZakaza = 0;
	Map<Integer,Vector<Menu>> mapTempMenusInZakaz = new TreeMap();  

	String str_offic = "";
	String str_stol = "";
	String str_bluda = "";
	 

	JLabel label_oficiant = new JLabel("Официант"),
			label_number_stol = new JLabel("Номер стола"),
			label_main_spisok_blud = new JLabel("Меню блюд"),
			label_bluda = new JLabel("Блюда"), label_pcs = new JLabel(
					"<html><center>Количество</center></html>"),
			label_info = new JLabel("Информация о текущем заказе:");

 	{
		try {
			new Staff()
					.doNamesOfficiantBox("D:\\workspace\\git\\RestoranBar\\Restoran-Bar\\IO\\staff_officiant.ini");
		}

		catch (IOException e) {
			e.printStackTrace();
		}
	}

	JComboBox<String> box_oficianti = new JComboBox(Staff.temp) {

		// pereopredelenie 4tobi combox ne rastagivalsa v visotu
		@Override
		public Dimension getMaximumSize() {
			Dimension max = super.getMaximumSize();
			max.height = getPreferredSize().height;
			return max;
		}

	};
	{
		new Stol().makesStoliInfo();
	}
	JComboBox<String> box_nomera_stolov = new JComboBox(Stol.vectroStolovString) {
		@Override
		public Dimension getMaximumSize() {
			Dimension max = super.getMaximumSize();
			max.height = getPreferredSize().height;
			return max;
		}
	};
	Vector<String> tempMainBluda = new Vector<>();

	{
		for (String elem : MainGUI.mainMenuBluda) {
			tempMainBluda.add(elem);
		}// foreach
	}
	JComboBox<String> box_main_bluda = new JComboBox(tempMainBluda) {
		@Override
		public Dimension getMaximumSize() {
			Dimension max = super.getMaximumSize();
			max.height = getPreferredSize().height;
			return max;
		}
	};

	JComboBox<String> box_bluda = new JComboBox() {
		@Override
		public Dimension getMaximumSize() {
			Dimension max = super.getMaximumSize();
			max.height = getPreferredSize().height;
			return max;
		}
	};

	Integer[] numbers = { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15,
			16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30 };

	JComboBox<Integer> box_pcs = new JComboBox(numbers) {
		@Override
		public Dimension getMaximumSize() {
			Dimension max = super.getMaximumSize();
			max.height = getPreferredSize().height;
			max.width = getPreferredSize().width;
			return max;
		}

	};

	JButton but_pay = new JButton("Оплатить");
	JButton but_addBludo = new JButton("Добавить блюдо");
	JButton but_createZakaz = new JButton("Создать заказ");
	JButton but_cancel = new JButton("Отмена");
	JButton but_deleteStr = new JButton("Удалить из меню");

	final JPanel leftPanel = new JPanel();
	final JPanel rightPanel = new JPanel();
	final JPanel centralPanel = new JPanel();
	final JPanel botomPanel = new JPanel();
	final Font 	font_info = new Font("Areal", Font.BOLD, 14);

	String str = "";

	final int size_x_cbox = 350, size_y_cbox = 20;

	public void decorateGUI(){
		setSize(x_sizeOfGUI, y_sizeOfGUI);
		setLocation((x_screenSize - x_sizeOfGUI) / 2,
				(y_screenSize - y_sizeOfGUI) / 2);

		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		label_info.setFont(font_info);
		label_bluda.setFont(font_info);
		label_main_spisok_blud.setFont(font_info);
		label_number_stol.setFont(font_info);
		label_oficiant.setFont(font_info);
		label_pcs.setFont(font_info);
		label_info.setHorizontalAlignment(SwingConstants.CENTER);

		dirList.setForeground(Color.black);
		vectListContent.add("Заполните данные заказа" );
 		dirList.setListData(vectListContent);
		dirList.setPreferredSize(new Dimension(250, 600));
		dirList.setToolTipText("Информация о заказе");
		
		// Right panel
		rightPanel.setBorder(BorderFactory.createEmptyBorder(5, 10, 100, 5));
		// 5 - TOP,10 -left,125-down, 5- right
		rightPanel.setLayout(new BorderLayout(10, 10));
		JScrollPane rightPanelScroll = new JScrollPane(dirList);
		rightPanelScroll.setPreferredSize(new Dimension(200, 800));

		rightPanel.add(label_info, BorderLayout.NORTH);
		but_deleteStr.setPreferredSize(new Dimension(200, 40));
		rightPanel.add(but_deleteStr, BorderLayout.SOUTH);
		rightPanel.add(rightPanelScroll, BorderLayout.CENTER);
		// ---------------------------------------------------------------
		leftPanel.setLayout(null);

		label_oficiant.setBounds(20, 0, 100, 20);
		leftPanel.add(label_oficiant);
		box_oficianti.setBounds(10, 20, size_x_cbox, size_y_cbox);
		leftPanel.add(box_oficianti);

		label_number_stol.setBounds(20, 50, 100, 20);
		leftPanel.add(label_number_stol);
		box_nomera_stolov.setBounds(10, 70, size_x_cbox, size_y_cbox);
		leftPanel.add(box_nomera_stolov);

		label_main_spisok_blud.setBounds(20, 100, 100, 20);
		leftPanel.add(label_main_spisok_blud);
		box_main_bluda.setBounds(10, 120, size_x_cbox, size_y_cbox);
		leftPanel.add(box_main_bluda);

		label_bluda.setBounds(20, 155, 100, 20);
		leftPanel.add(label_bluda);
		box_bluda.setBounds(10, 175, size_x_cbox - 100, size_y_cbox);
		leftPanel.add(box_bluda);

		label_pcs.setBounds(size_x_cbox - 100 + 40, 145, 100, 40);
		leftPanel.add(label_pcs);
		box_pcs.setBounds((size_x_cbox - 40), 175, 50, 20);
		leftPanel.add(box_pcs);

		but_addBludo.setBounds(40, 215, 200, 40);
		leftPanel.add(but_addBludo);
 
		// ------------------------------------------

		botomPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 20, 10));
		botomPanel.setLayout(new FlowLayout(1, 10, 1));
		but_createZakaz.setPreferredSize(new Dimension(200, 40));
		but_cancel.setPreferredSize(new Dimension(200, 40));
		but_pay.setPreferredSize(new Dimension(200, 40));
		botomPanel.add(but_pay);
		botomPanel.add(Box.createRigidArea(new Dimension(62, 0)));
		botomPanel.add(but_createZakaz);
		botomPanel.add(but_cancel);

		centralPanel.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();

		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 0.8;
		c.gridx = 0;
		c.gridy = 0;
		c.ipady = 255;

		centralPanel.add(leftPanel, c);

		c = new GridBagConstraints();
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 0.2;
		c.gridx = 1;
		c.gridy = 0;
		c.ipady = 255;

		centralPanel.add(rightPanel, c);

		// =-------------------------------------------------------

		setLayout(new BorderLayout(45, 1));
		add(centralPanel, BorderLayout.CENTER);
		add(botomPanel, BorderLayout.SOUTH);

		centralPanel.setBorder(BorderFactory.createEmptyBorder(0, 5, 10, 5));

		// ------------------------------------------
		//Цвета
		box_oficianti.setBackground(Color.WHITE);
		box_pcs.setBackground(Color.WHITE);
		box_main_bluda.setBackground(Color.WHITE);
		box_bluda.setBackground(Color.WHITE);
		box_nomera_stolov.setBackground(Color.WHITE);
		box_oficianti.setForeground(Color.BLACK);
		box_bluda.setForeground(Color.BLACK);
	}
	public void componentsAddActionListener(){

		but_addBludo.addActionListener(new MyActionListener());
		but_cancel.addActionListener(new MyActionListener());
		but_createZakaz.addActionListener(new MyActionListener());
		but_pay.addActionListener(new MyActionListener());
		but_deleteStr.addActionListener(new MyActionListener());
	}
	public void componentsAddItemListener(){
		box_oficianti.addItemListener(new MyItemListener());
		box_nomera_stolov.addItemListener(new MyItemListener()); 
		box_main_bluda.addItemListener(new MyItemListener());
		box_bluda.addItemListener(new MyItemListener());
	}
	
	public void defaultFeautersGUIcomponents(){
//		otobragenie vibora v JList
		vectListContent.clear();
		box_oficianti.insertItemAt(null, 0);;
		box_nomera_stolov.insertItemAt(null, 0);;
		box_oficianti.setSelectedIndex(0);
		box_nomera_stolov.setSelectedIndex(0);
		box_nomera_stolov.setEnabled(false);
		box_main_bluda.setEnabled(false);
		box_bluda.setEnabled(false);
		vectListContent.add("Выберите официанта!");

		box_main_bluda.setSelectedIndex(0);
		box_bluda.setSelectedItem(0);

		Vector<Menu> firstTempGrupMenu = MainGUI.mapGruppMenuBluda
				.get(box_main_bluda.getSelectedItem());

		for (Menu elem : firstTempGrupMenu) {
			box_bluda.addItem(elem.nameOfElementOfMenu);
		}// foreach
	}
	public GUI_zakaz(String title) throws HeadlessException {
		super(title);
		
		decorateGUI();
		defaultFeautersGUIcomponents();
		componentsAddItemListener();
		componentsAddActionListener();
		setVisible(true);
		System.out.println("asdfsadfa");
	}
	

	public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException {
		// TODO Auto-generated method stub
		new MainGUI("D:\\workspace\\git\\RestoranBar\\Restoran-Bar\\IO\\GUI_main_menu.ini",
				"D:\\workspace\\git\\RestoranBar\\Restoran-Bar\\IO\\menu_restorana.ini",
				"D:\\workspace\\git\\RestoranBar\\Restoran-Bar\\IO\\stoli_menu.ini").dispose();
		new GUI_zakaz("Окно создания нового заказа");

//		D:\workspace\git\RestoranBar\Restoran-Bar\
 	}

	
	
	class MyItemListener implements ItemListener{

		
		
		@Override
		public void itemStateChanged(ItemEvent event) {
			// TODO Auto-generated method stub
			
			//если КБокс официантов
			  if (event.getSource() == box_oficianti ) {
				
				if (box_oficianti.getSelectedItem() != null) {
					str_offic = box_oficianti.getSelectedItem().toString();
 					vectListContent.removeAllElements();
					vectListContent.add("Официант: "+str_offic);
					vectListContent.add("Выберите стол заказа!");
					dirList.removeAll();
					dirList.setListData(vectListContent);
					box_nomera_stolov.setEnabled(true);
					box_main_bluda.setEnabled(false);
					box_bluda.setEnabled(false);
					box_pcs.setEnabled(false);

				}// if
				else if (box_oficianti.getSelectedItem() == null) {
					vectListContent.removeAllElements();
					vectListContent.add("Выберите официанта!");
					dirList.removeAll();
					dirList.setListData(vectListContent);
					box_nomera_stolov.setEnabled(false);
					box_main_bluda.setEnabled(false);
					box_bluda.setEnabled(false);
					box_pcs.setEnabled(false);
					
				}// if
 
				
			  }

			  
			//если КБокс номера столов
				else if (event.getSource() == box_nomera_stolov ) {

					allSummaZakaza=0;
					vectorMenu.removeAllElements();

					if (box_nomera_stolov.getSelectedItem() != null) {
						str_stol = box_nomera_stolov.getSelectedItem().toString();
						vectListContent.removeAllElements();
						vectListContent.add("Официант: "+str_offic);
						vectListContent.add(box_nomera_stolov.getSelectedItem().toString());
						vectListContent.add("Блюда: ");
						dirList.removeAll();
						dirList.setListData(vectListContent);
						box_main_bluda.setEnabled(true);
						box_bluda.setEnabled(true);
						box_pcs.setEnabled(true);
						
					}// if
					else  if (box_nomera_stolov.getSelectedItem() == null){
						vectListContent.removeAllElements();
						vectListContent.add("Официант: "+str_offic);
						vectListContent.add("Выберите стол заказа!");
						dirList.removeAll();
						dirList.setListData(vectListContent);
						box_main_bluda.setEnabled(false);
						box_bluda.setEnabled(false);
						box_pcs.setEnabled(false);
					}
					
									
				}//if
			  
			//если КБокс группы блюд
			else if (event.getSource() == box_main_bluda && (event.getStateChange() == ItemEvent.SELECTED)) {
				Vector<Menu> secondTempGrupMenu = MainGUI.mapGruppMenuBluda.get(event.getItem());
				box_bluda.removeAllItems();
				for (Menu elem : secondTempGrupMenu) {
					box_bluda.addItem(elem.nameOfElementOfMenu);
				}// foreach
				
			}//if

			//если КБокс блюда
			else if (event.getSource() == box_bluda && (event.getStateChange() == ItemEvent.SELECTED)) {
				 box_pcs.setSelectedIndex(1);
 							
			}//if
			
			
		}//itemStateChanged
		
	}//class MyItemListener
	
	
	class MyActionListener implements ActionListener {

 
		@Override
		public void actionPerformed(ActionEvent event) {

			
			//НАЖАТА КНОПКА ДОБАВИТЬ БЛЮДО
			if (event.getSource() == but_addBludo) {

				if (box_pcs.getSelectedIndex()==0){
					JOptionPane.showMessageDialog
					(null, "Сколько порций  "+box_bluda.getSelectedItem()+" ?", "Внимание!",JOptionPane.QUESTION_MESSAGE);
					
				}//if
				else{
					
				Integer tempCost = 0;  	
				Integer tempWeight = 0;	
				Vector<Menu> tempBludo = MainGUI.mapGruppMenuBluda.get(box_main_bluda.getSelectedItem());
				for (Menu elem : tempBludo) {
					if (elem.nameOfElementOfMenu==box_bluda.getSelectedItem()) {
						tempCost = elem.costOfElementOfMenu;
						tempWeight = elem.weightOfElementOfMenu;
						break;
					}//if
				}//foreach
				
				
		 				
				vectListContent.add(box_bluda.getSelectedItem()+ " - "+ box_pcs.getSelectedItem() +" шт, " +tempCost*
						(int)box_pcs.getSelectedItem()+ " гривен");

								
				//заполнение вектора меню
				//когда выбрано несколько порций блюда
					if ((int)box_pcs.getSelectedItem()>1) {
					
						for (int i = 0; i < (int)box_pcs.getSelectedItem(); i++) {
							vectorMenu.add(new Menu(box_bluda.getSelectedItem().toString(),tempCost , tempWeight));
						}//for
					}//if
					
					//когда выбрана одна порция блюда
					else{ vectorMenu.add(new Menu(box_bluda.getSelectedItem().toString(),tempCost , tempWeight));}
					
					//убираем лишний раз написанную Сумму заказа
					for (int i = 0; i < vectListContent.size(); i++) {
						if (vectListContent.get(i).startsWith("Сумма заказа")){
							vectListContent.remove(i);
						}
 					}//for
					
					//добавляем сумму заказа в конец списка отображения
					
 					int tempSumma=0;
					for (Menu elem : vectorMenu) {
						tempSumma = tempSumma + elem.costOfElementOfMenu;
					}//foreach
					allSummaZakaza = tempSumma;

					vectListContent.add("Сумма заказа ВСЕГО: " +allSummaZakaza + " гривен");
					dirList.removeAll();				
					dirList.setListData(vectListContent);
					System.out.println("allSummaZakaza"+allSummaZakaza);
 
				}
				
 				
				
				for (String elem : vectListContent) {
//					elem.charAt(index)
					
				}//foreach
								

			 	Map<String,Vector<Menu>> tempMapAllBluda = MainGUI.mapGruppMenuBluda;
			 	
			 	List<String> listBludsOfZakaz = new ArrayList();
			 	
			 	
			}//if			
			
			
			//НАЖАТА КНОПКА ОТМЕНА
			else if (event.getSource()==but_cancel) {
				dispose();
			}
			
			
			//НАЖАТА КНОПКА УДАЛИТЬ ИЗ МЕНЮ
			else if (event.getSource()==but_deleteStr) {
   					
				//если удаляются несколько элементов сразу
				if (dirList.getSelectedValuesList().size()>1) {
					
					List<String> temp = dirList.getSelectedValuesList();
					
					for (String elem : temp) {
						
		 				vectListContent.remove(elem);
					}//foreach
					
				}//if
				
				//если удаляется только один элемент строки
				else {
					String str = dirList.getSelectedValue().toString();
					str = str.substring(str.indexOf("шт,")+3, str.indexOf(" гривен")).trim().replace(" ",	"");
					 System.out.println("вот оно = "+"|"+str+"|");
 					 int temp_Suma = allSummaZakaza -Integer.valueOf(str);
					allSummaZakaza = temp_Suma;
					System.out.println("allSummaZakaza при удалении = "+ allSummaZakaza);
					str = dirList.getSelectedValue().toString();
					str = str.substring(0, str.indexOf("-")).trim();
					
					for (int i = 0; i < vectorMenu.size(); i++) {
 						if (vectorMenu.get(i).nameOfElementOfMenu.equals(str)){
							System.out.println("равны");
							vectorMenu.remove(i);
							break;
						}
					}//for
					
					vectListContent.remove(dirList.getSelectedValue());
					vectListContent.remove(vectListContent.size()-1);
					vectListContent.add("Сумма заказа ВСЕГО: " + allSummaZakaza + " гривен");

				}//else
				
 				dirList.setListData(vectListContent);
 				dirList.repaint();
				dirList.revalidate();

 			}

			
			//НАЖАТА КНОПКА СОЗДАТЬ ЗАКАЗ
			else if (event.getSource()== but_createZakaz) {
				System.out.println("vectorMenu.size() ="+vectorMenu.size());
				Zakaz zakaz =  new Zakaz(vectorMenu);
 				
				try { 
					zakaz.officiant = Staff.vectorStaff.get(box_oficianti.getSelectedIndex()-1);
					zakaz.priceZakaz = allSummaZakaza;
 					zakaz.payed_zakaz = false;
					zakaz.timeReception = String.valueOf(new Date().getTime());
					zakaz.stol.uniqueNumberOfStol=(int) box_nomera_stolov.getSelectedIndex();
					
				} catch (NullPointerException e) {;	}
 				vectorZakazov.addElement(zakaz);
//				mapTempMenusInZakaz.put(zakaz.id_zakaz, value)
				System.out.println("Вектор заказов-----");
				
				for (Zakaz elem : vectorZakazov) {
					System.out.println(elem);
				}//foreach
				System.out.println("------------------");
				System.out.println("vectorZakazov.size() = "+vectorZakazov.size());
				
				System.out.println("После всего!");
				
				System.out.println("После всего!");

				vectorMenu.removeAllElements();
			}//создать заказ
			
			
		}//actionPerformed
		
	}//MyActionListener
	
	
	
	
}// public class
