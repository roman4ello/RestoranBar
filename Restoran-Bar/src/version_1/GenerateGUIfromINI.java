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

	// ��� ��������------------------------------------------------------------

	// ������ ���� ��������������� ����
	final   public Vector<String> namesGorizontMenus = new Vector<String>();

	// ��� (���� = �������� ����, value = ������ ������� ������� ����) -
	// ����� ���� ������ ������ ������ ������� ����, ���-� � ���� ���������
	final   Map<String, Vector<String>> namesVertikMenus = new TreeMap();

	// ��� (���� = �������� �������, value = ������ ������� ������� �������)
	final   Map<String, Vector<String>> namesSubMenus_Map = new TreeMap();

	
	
	// ��� ��������� ���--------------------------------------------------
	// ������ ������
	final int x_screenSize = (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth();
	final int y_screenSize = (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight();

	// ������ ����
	int x_size = 800; int y_size = 400;

	//����������� JFrame
	public GenerateGUIfromINI(String filePath)
			throws ClassNotFoundException, InstantiationException,
			IllegalAccessException, UnsupportedLookAndFeelException {
 		UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());

		setTitle("���� ������� ��������-���");
		setDefaultCloseOperation(DISPOSE_ON_CLOSE); // ������� - ������� ����� ����� �������� ���������� ����
		setLayout(new FlowLayout()); // �������� ����������() - ����������, ��������� ���������� ����� �������
		setSize(x_size, y_size);
		//���������� ���� ��� �� ������ ������
		setLocation((x_screenSize - x_size) / 2, (y_screenSize - y_size) / 2);
		drawJMenuButtonsInGUI(filePath);// ���������� ��� ���������� �� ����� "filePath"
		setVisible(true);

	}// constructor

	public static void main(String[] args) throws ClassNotFoundException,
			InstantiationException, IllegalAccessException,
			UnsupportedLookAndFeelException {

		new GenerateGUIfromINI("d:\\MyTeamProject\\menu.ini");
	}

	
	//������ INI ����� ��� ���������� ���� � ����
	public void parserFromIni(String fileName) throws IOException {
		// ������ ���������� � ����������� ����������� ����
		BufferedReader buffReader = new BufferedReader(new FileReader(fileName));
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
					namesGorizontMenus.add(str_temp);
				}
				// System.out.println( );

			}// else �������� ����

			// = ���� ������������===========================
			else if (str.contains("����������")) {

				for (String elem : namesGorizontMenus) {

					if (str.contains("����������_" + elem + ":")) {
						str = str.substring(
								("����������_" + elem + ":").length()).trim();
						StringTokenizer tokzer = new StringTokenizer(str, ",");

						// ��������� ������ ��� ���������� ���� � ����������
						Vector<String> tempVector = new Vector<String>();

						// ������� � ��������� namesVertikMenus ������ ��������
						// ����������
						while (tokzer.countTokens() > 0) {
							String str_temp = (String) tokzer.nextElement()
									.toString().trim();
							// System.out.print("|" + str_temp );
							tempVector.add(str_temp);

						}
						// ��������� �������� ���������� � ��� ������������
						// ������� � ������ = ������ �����.����
						namesVertikMenus.put(elem, tempVector);
						// System.out.println("namesVertikMenus ="+namesVertikMenus
						// );
					}

				}// foreach

			}// else if

			// �������=======================================================
			else if (str.contains("�������_")) {

				// ���� �� �������� ������������ ����, ��� �������� ��������
				// �������
				for (Vector<String> elem_vector : namesVertikMenus.values()) {

					// ���� �� ����� ��������� �������
					for (String elem : elem_vector) {

						// ���� ������ �������� �������_"�������� �����������
						// ������ ����
						// �� ������ ������� ������������� ����"
						if (str.contains("�������_" + elem + ":")) {

							// ������ ������ ����� ����� ��������
							str = str.substring(
									("�������_" + elem + ":").length()).trim();
							StringTokenizer tokzer = new StringTokenizer(str,
									",");

							// ��������� ������ ��� ���������� ���� � �������
							Vector<String> tempVector = new Vector<String>();

							// ������� � ������ tempVector ������ ��������
							// �������
							while (tokzer.countTokens() > 0) {

								String str_temp = (String) tokzer.nextElement()
										.toString().trim();
								// System.out.print("|" + str_temp );
								tempVector.add(str_temp);

							}// while

							// ��������� �������� ������� � 2-������ ������ ����
							// �������
							// System.out.println( );
							namesSubMenus_Map.put(elem, tempVector);
						}//

					}// foreach String elem

				}// foreach Vector<String> elem_vector

			}// else if

		}// for ���� ������ ����� �����

	}

	//����� �� ������� ����������� ��������� � ��������� ����
	public void printCollections() {

		System.out.println("�������������� ���� =" + namesGorizontMenus);

		System.out.println("\n" + "������������ ����:");
		for (Map.Entry<String, Vector<String>> elem : namesVertikMenus
				.entrySet()) {
			System.out.println(elem);

		}// foreach

		System.out.println("\n" + "�������:");

		for (Map.Entry<String, Vector<String>> elem : namesSubMenus_Map
				.entrySet()) {
			System.out.println(elem);

		}// foreach

	}

	// ����� ������� ������ ����������� ������ �� ���� ���������� ����� ��������
	public void drawJMenuButtonsInGUI(String filePath) {
		// "d:\\MyTeamProject\\menu.ini"
		// ��������� ����
		try { parserFromIni(filePath);} 
		catch (IOException e1) {JOptionPane.showMessageDialog(null,
					"� ��������� ���������� INI ����� ���!");}
		 

		// ������� �������������� ���� - ������� ���� - �������
		JMenuBar menuBar = new JMenuBar();

		// ���� ���� ���� ���� ����� ����� ����
		if (! namesGorizontMenus.isEmpty()) {

			// ������������ ������ �������� � ���� �������������
			setJMenuBar(menuBar);

			// ���� �������� �������������� �������
			for (String elem_goriz_menu :  namesGorizontMenus) {

				//
				JMenu menuGorizontalnoe = new JMenu(elem_goriz_menu);
				menuGorizontalnoe.setFocusPainted(false);
				menuGorizontalnoe.setPreferredSize(new Dimension(x_size/namesGorizontMenus.size(), 1));
				menuGorizontalnoe.setBorder(new BevelBorder(BevelBorder.RAISED));
				// ���������� �����������
				menuBar.add(new JSeparator(SwingConstants.VERTICAL));
				menuBar.add(menuGorizontalnoe);

				// ���� � �������� �������� ���� ���� ������ ������������� ����
				if (namesVertikMenus.get(elem_goriz_menu) != null) {

					// ���� �������� ������������ �������
					for (String elem_vertik_menu : namesVertikMenus.get(elem_goriz_menu)) {
						// System.out.println("elem_namesVertik_menu = "
						// +elem_vertik_menu);

						if (namesSubMenus_Map.get(elem_vertik_menu) != null) {

							menuGorizontalnoe.add(new JSeparator());

							JMenu subMenu = new JMenu(elem_vertik_menu);

							for (String elem_podmenu : namesSubMenus_Map.get(elem_vertik_menu)) {

								subMenu.add(elem_podmenu);

							}// foreach �� �����������

							menuGorizontalnoe.add(subMenu);

						} else {
							JMenuItem menuItem = new JMenuItem(elem_vertik_menu);
							menuGorizontalnoe.add(menuItem);

						}

						if ( namesSubMenus_Map.get(elem_vertik_menu) != null) {
							// ���� �� �����������

						}// if
						else {
							;
						}

					}// foreach �������� ������������ �������

				}// if (ParserReadMenuFromFile.namesVertikMenus.get(elem)!=null)

				// ����� � ������ ����� ���� ��� ��������
				else {
					;
					// System.out.println("������");
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

}//public class
