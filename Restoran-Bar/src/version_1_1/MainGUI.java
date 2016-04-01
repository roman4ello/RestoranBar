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

	// ��� ��������------------------------------------------------------------

	// ������ ���� ��������������� ����
	final public Vector<String> namesOfButtonsGorizontMenus = new Vector<String>();

	//�������� ����� ���� ���������, TreeSet ��� �������������� ���������� �� ��������
	final public TreeSet<String> mainMenuBluda = new TreeSet();	 

	// ��� ���� ������������� � ����������� ������� (���� = �������� �������, value = ������ ������� ������� �������)
	final public Map<String,Vector<Menu>> mapGruppMenuBluda = new TreeMap<>();

	//�������� ������ ���� �����,TreeSet ��� �������������� ���������� �� ��������
	final public TreeSet<String> mainMenuStoli = new TreeSet();	 
	
	// ��� (���� = �������� �������, value = ������ ������� ������� �������)
	//Vector<String> -����� ������� ���������� ���������� � � ������������ ���� ����������� 
	//������ ������ �� ������� ���������
	final public Map<String,Vector<String>> mapGruppMenuStoli = new TreeMap<>();
	
	
	
	// ��� ��������� ���--------------------------------------------------
	// ��������� ������ ������ 
	final int x_screenSize = (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth();
	final int y_screenSize = (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight();

	// ������ ����
	int x_size = 800; int y_size = 400;

	//����������� JFrame
	public MainGUI(String pathToFileGorizontMenuNames, String pathTOspisok_menu,String pathTOstoli_menu)
			throws ClassNotFoundException, InstantiationException,
			IllegalAccessException, UnsupportedLookAndFeelException {
 		UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());

		setTitle("���� ������� ��������-���");
		setDefaultCloseOperation(EXIT_ON_CLOSE); // ������� - ������� ����� ����� �������� ���������� ����

		setLayout(new FlowLayout()); // �������� ����������() - ����������, ��������� ���������� ����� �������
		setSize(x_size, y_size);
		//���������� ���� ��� �� ������ ������
		setLocation((x_screenSize - x_size) / 2, (y_screenSize - y_size) / 2);
		drawJMenuButtonsInGUI(pathToFileGorizontMenuNames,
				        pathTOspisok_menu,pathTOstoli_menu);// ���������� ��� ���������� �� ����� "filePath"

		setVisible(true);
 		  
		
	}// constructor

	public static void main(String[] args) throws ClassNotFoundException,
			InstantiationException, IllegalAccessException,
			UnsupportedLookAndFeelException {

		new MainGUI("d:\\MyTeamProject\\IO\\GUI_main_menu.ini",
					"d:\\MyTeamProject\\IO\\menu_restorana.ini",
					"d:\\MyTeamProject\\IO\\stoli_menu.ini");
	}
	
	//������ ��������������� ���� � namesOfButtonsGorizontMenus
	public Vector<String> parserReadMainButtonsMenu(String pathToFileGorizontMenuNames) throws IOException {
		// ������ ���������� � ����������� ����������� ����
		BufferedReader buffReader = new BufferedReader(new FileReader(pathToFileGorizontMenuNames));
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
			else if (str.contains("������������:")) {
				str = str.substring("������������:".length()).trim();
				StringTokenizer tokzer = new StringTokenizer(str, ",");

				// ������� � ��������� namesGorizontMenus ������ �������� ����
				while (tokzer.countTokens() > 0) {
					String str_temp = (String) tokzer.nextElement().toString()
							.trim();
					// System.out.print ("|" + str_temp );
					namesOfButtonsGorizontMenus.add(str_temp);
				}
				// System.out.println( );

			}// else �������� ����
 
		}// for ���� ������ ����� �����
		buffReader.close();
		return namesOfButtonsGorizontMenus;
	}
	
	//������ ���� ��������� � ��� mapGruppMenu 
	public  Map<String,Vector<Menu>> parserReadPointsOfMenuBluds(String pathTOspisok_menu) throws IOException{
/*
		���� ���������� �� ���������� ���������
		 1. ### - ��������� ���������� �����
		 2. #�������� ������� ����: ������������ ��������
		 3. #������_"�������� ������ ����" (��� ����� #������_ -������� ������ ������ �������� ������ ����, 
		    ������� ������ ��������� � ����� �� ������� �������� ����!
		 4. #�����_�����! - ������������ ������, ������� ��������� �� ��������� �����
		 5. ������ "�������������� ������ = 200(������)/100(�����)"  ���������, ���:
		     1. ����� �������� ����� ������ ������ ������ "="
		     2. ������ ���������� ����� ��������� ����� � �������
		     3. ������ ��� ����� � �������
		     4. ��� ����� ������ �����
		     5. ������ ������ ����� "/" ����������,
		     6. ����� ������ ������ �����. ��������: "280(������)/420(�����)"
		 6. ������ "//" - �������� ������������ ������ ������ � �������� ���� 
*/
	
	
		//������ INI ����� ��� ���������� ���� � ����
			// ������ ���������� � ����������� ����������� ����
		BufferedReader buffReader = new BufferedReader(new FileReader(pathTOspisok_menu));
		String str = "";

		// ������ �� ��� ��� ���� ������ �� �������
		for (; (str = buffReader.readLine()) != null;) {
			
			// ������ ������ �� �����
			if (str.trim().equals(""))	continue;			
			if (str.startsWith("//"))	continue;
			
			// ���� ������� �������� �����_����� - ���������� ������
			if (str.contains("#�����_�����!")) 	break;
	 			
			if (str.contains("#�������� ������� ����:")) {
 				
				while(!(str= buffReader.readLine()).contains("###") ){
					System.out.println(str);
					if (str.contains("#�����_�����!")) 	break;
					if (str.trim().equals(""))	continue;
					if (str.startsWith("//"))	continue;

					mainMenuBluda.add(str.trim());
				}//while
			}//if
//			System.out.println("str  ="+str);
	
			if (str.contains("#������_")) {
 				String key = str.substring("#������_".length(), str.indexOf(":"));
 				Vector<String> vectorTemp = new Vector<String>();
 				Vector<Menu> vectorMenu = new Vector< >();
				while(!(str= buffReader.readLine()).contains("###") ){
//					System.out.println(str);
					if (str.contains("#�����_�����!")) 	break;
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

						System.out.println("�� ��� ���� ���������");
					}
					
					Menu.count_uniq++;//����������� ���������� ��� �����
					vectorMenu.add(new Menu(nameOfElementOfMenu, costOfElementOfMenu, weightOfElementOfMenu));
					vectorTemp.add(str.trim());
				}//while
				mapGruppMenuBluda.put(key, vectorMenu);
			}//if
						
		}// for ���� ������ ����� �����
	
 		buffReader.close();
		return mapGruppMenuBluda;
	}//parserReadPointsOfMenu
			
 	//������ ���� ����� � ��� mapGruppMenuStoli 
	public  Map<String,Vector<Menu>> parserReadStolPoints(String pathTOstoli_menu) throws IOException{
 	
		//������ INI ����� ��� ���������� ���� � ����
			// ������ ���������� � ����������� ����������� ����
		BufferedReader buffReader = new BufferedReader(new FileReader(pathTOstoli_menu));
		String str = "";

		// ������ �� ��� ��� ���� ������ �� �������
		for (; (str = buffReader.readLine()) != null;) {
			
			// ������ ������ �� �����
			if (str.trim().equals(""))	continue;			
			if (str.startsWith("//"))	continue;
			
			// ���� ������� �������� �����_����� - ���������� ������
			if (str.contains("#�����_�����!")) 	break;
	 			
			if (str.contains("#�����:")) {
 				
				while(!(str= buffReader.readLine()).contains("###") ){
					System.out.println(str);
					if (str.contains("#�����_�����!")) 	break;
					if (str.trim().equals(""))	continue;
					if (str.startsWith("//"))	continue;

					mainMenuStoli.add(str.trim());
				}//while
			}//if
//			System.out.println("str  ="+str);
	
			if (str.contains("#������_")) {
 				String key = str.substring("#������_".length(), str.indexOf(":"));
 				Vector<String> vectorTemp = new Vector();
 				while(!(str= buffReader.readLine()).contains("###") ){
//					System.out.println(str);
					if (str.contains("#�����_�����!")) 	break;
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
//						System.out.println("�� ��� ���� ���������");
//					}
					
//					Menu.count_uniq++;//����������� ���������� ��� �����
 					vectorTemp.add(str.trim());
				}//while
				mapGruppMenuStoli.put(key, vectorTemp);
			}//if
						
		}// for ���� ������ ����� �����
	
 		buffReader.close();
		return mapGruppMenuBluda;
	}//parserReadPointsOfMenu	//----------------------------
		 
	// ����� ������� ������ ����������� ������ �� ���� ���������� ����� ��������
	public void drawJMenuButtonsInGUI(	String pathToFileGorizontMenuNames,
										String pathTOspisok_menu,
										String pathTOstoli_menu) {
		 
			// ��������� ����
			try { parserReadMainButtonsMenu(pathToFileGorizontMenuNames);} 
			catch (IOException e1) {JOptionPane.showMessageDialog(null,
						"� ��������� ���������� INI ����� ��� ������������ �������� ������ ���� ���!");}
			try { 	parserReadPointsOfMenuBluds(pathTOspisok_menu);	} 
			catch (IOException e) {JOptionPane.showMessageDialog(null,
					"� ��������� ������ INI ����� ���� ��������� ���!");} 
			
			try { 	parserReadStolPoints( pathTOstoli_menu);}
			catch (IOException e) {JOptionPane.showMessageDialog(null, 
					"� ��������� ������ INI ����� ������ ����� ���!");} 
			

			// ������� �������������� ���� - ������� ���� - �������
			JMenuBar menuBar = new JMenuBar();

			// ���� ���� ���� ���� ����� ����� ����
			if (! namesOfButtonsGorizontMenus.isEmpty()) {

				// ������������ ������ �������� � ���� �������������
				setJMenuBar(menuBar);

				// ���� �������� �������������� �������
				for (String elem_goriz_menu :  namesOfButtonsGorizontMenus) {

					//
					JMenu menuGorizontalnoe = new JMenu(elem_goriz_menu);
					menuGorizontalnoe.setFocusPainted(false);
					menuGorizontalnoe.setPreferredSize(new Dimension(x_size/namesOfButtonsGorizontMenus.size(), 1));
					menuGorizontalnoe.setBorder(new BevelBorder(BevelBorder.RAISED));
					// ���������� �����������
					menuBar.add(new JSeparator(SwingConstants.VERTICAL));
					menuBar.add(menuGorizontalnoe);

					//===============================================================
				
 					// ��� ���� ���������
					if (mainMenuBluda.size()>0 & elem_goriz_menu.equals("���� ���������") )  {
						// ���� �������� ������������ �������
						for (String elem_vertik_menu : mainMenuBluda) {
							// System.out.println("elem_namesVertik_menu = "
							// +elem_vertik_menu);

							if (mapGruppMenuBluda.get(elem_vertik_menu).size() != 0) {

								menuGorizontalnoe.add(new JSeparator());

								JMenu subMenu = new JMenu(elem_vertik_menu);

								//��� ��� ��������� ������ ������ ������ 
								for (Menu elem_podmenu : mapGruppMenuBluda.get(elem_vertik_menu)) {
 									
									subMenu.add(elem_podmenu.nameOfElementOfMenu+ " - ("+
											elem_podmenu.costOfElementOfMenu+" ������ " +"/"+
											elem_podmenu.weightOfElementOfMenu+ " �����)");

								}// foreach �� �����������

								menuGorizontalnoe.add(subMenu);

							} else {
								JMenuItem menuItem = new JMenuItem(elem_vertik_menu);
								menuGorizontalnoe.add(new JSeparator());
								menuGorizontalnoe.add(menuItem);

							}

							
						}// foreach �������� ������������ �������

					}// if (ParserReadMenuFromFile.namesVertikMenus.get(elem)!=null)
					//===============================================================
				
					
					// ��� ���� �����
					else if (mainMenuStoli.size()>0 & elem_goriz_menu.equals("�����") )  {
						// ���� �������� ������������ �������
						for (String elem_vertik_stoli_menu : mainMenuStoli) {
							  System.out.println("elem_vertik_stoli_menu = "
							  +elem_vertik_stoli_menu);

							  //���� ������� ������ ���� ���� ���������
							if (mapGruppMenuStoli.get(elem_vertik_stoli_menu).size() != 0) {

								menuGorizontalnoe.add(new JSeparator());

								JMenu subMenu = new JMenu(elem_vertik_stoli_menu);

								//��� ��� ��������� ������ ������ ������ 
								for (String elem_podmenu : mapGruppMenuStoli.get(elem_vertik_stoli_menu)) {
									
									subMenu.add(elem_podmenu).addActionListener(new MyActionListener()); ;

								}// foreach �� �����������

								
								if (subMenu.getSize()!=null) {
									
									menuGorizontalnoe.add(subMenu);

								}//if

							} 
							
							//���� � ������ ���������� ���
							else {
								JMenuItem menuItem = new JMenuItem(elem_vertik_stoli_menu);
								menuGorizontalnoe.add(new JSeparator());
								menuGorizontalnoe.add(menuItem);

							}

							
						}// foreach �������� ������������ �������

					}// if (ParserReadMenuFromFile.namesVertikMenus.get(elem)!=null)
					//===============================================================
					// ����� � ������ ����� ���� ��� ��������
					else {
						;
						 System.out.println("������");
					}// else

				}// foreach �������� �������������� �������

			}// if
			else {
				JOptionPane.showMessageDialog(null,
								"� ��������� �� �� ������� �� ������ ������ �������� ����!");
				System.exit(1);
			}
	 
	 
			// ��������� ������� - setPreferredSize
			menuBar.setPreferredSize(new Dimension(1, 50)); // ������ ��������, � ����� ��� - �����
																// ��������� ��������� ��� ����
	 				 
		 
		}// drawJMenuButtonsInGUI
		
		
	
	class MyActionListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
 	
					
			System.out.println(e.getActionCommand());
			
			if(e.getActionCommand().equals("������� ����� �����")){
							new GUI_zakaz("�������� ������ ������");
			}
				
			 
		}//actionPerformed
		
	}//class MyActionListener
	
	
	
	
}//public class
