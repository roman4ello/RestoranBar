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
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.IOException;
import java.util.Vector;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;

public class GUI_zakaz extends JFrame {

	int x_screenSize = (int) Toolkit.getDefaultToolkit().getScreenSize()
			.getWidth(), y_screenSize = (int) Toolkit.getDefaultToolkit()
			.getScreenSize().getHeight();
	int x_sizeOfGUI = 800, y_sizeOfGUI = 400;

	JList dirList = new JList();
	 

	JLabel label_oficiant = new JLabel("Официант"),
			label_number_stol = new JLabel("Номер стола"),
			label_main_spisok_blud = new JLabel("Меню блюд"),
			label_bluda = new JLabel("Блюда"), label_pcs = new JLabel(
					"<html><center>Количество</center></html>"),
			label_info = new JLabel("Информация о текущем заказе:");

	
	{
		try {
			new Staff()
					.doNamesOfficiantBox("d:\\MyTeamProject\\IO\\staff_officiant.ini");
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

	JButton but_pay = new JButton("Оплатить заказ сейчас");
	JButton but_addInOrder = new JButton("Добавить блюдо");
	JButton but_ok = new JButton("Создать заказ");
	JButton but_cancel = new JButton("Отмена");

	final JPanel leftPanel = new JPanel();
	final JPanel rightPanel = new JPanel();
	final JPanel centralPanel = new JPanel();
	final JPanel botomPanel = new JPanel();
	final Font font_info = new Font("Areal", Font.BOLD, 14);

	String str = "";

	final int size_x_cbox = 350, size_y_cbox = 20;

	public GUI_zakaz(String title) throws HeadlessException {
		super(title);
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
		Vector<String> arrDiskContent = new Vector();
		arrDiskContent.add("Заполните данные заказа" );
 		dirList.setListData(arrDiskContent);
		dirList.setPreferredSize(new Dimension(250, 600));
		dirList.setToolTipText("Информация о заказе");
		
		// Right panel
		rightPanel.setBorder(BorderFactory.createEmptyBorder(5, 10, 125, 5));
		// 5 - TOP,10 -left,125-down, 5- right
		rightPanel.setLayout(new BorderLayout(10, 10));
		JScrollPane rightPanelScroll = new JScrollPane(dirList);
		rightPanelScroll.setPreferredSize(new Dimension(200, 800));

		rightPanel.add(label_info, BorderLayout.NORTH);
		but_pay.setPreferredSize(new Dimension(200, 40));
		rightPanel.add(but_pay, BorderLayout.SOUTH);
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

		but_addInOrder.setBounds(150, 215, 200, 40);
		leftPanel.add(but_addInOrder);
 
		// ------------------------------------------

		botomPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 20, 10));
		botomPanel.setLayout(new FlowLayout(1, 140, 2));
		but_ok.setPreferredSize(new Dimension(200, 40));
		but_cancel.setPreferredSize(new Dimension(200, 40));
		botomPanel.add(but_ok);
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

		box_oficianti.setBackground(Color.WHITE);
		box_pcs.setBackground(Color.WHITE);
		box_main_bluda.setBackground(Color.WHITE);
		box_bluda.setBackground(Color.WHITE);
		box_nomera_stolov.setBackground(Color.WHITE);

		box_oficianti.setForeground(Color.BLACK);
		box_bluda.setForeground(Color.BLACK);

//		otobragenie vibora v JList
//		arrDiskContent.clear();
		
		if (box_oficianti.getSelectedItem() == null) {
			arrDiskContent.add("Выберите официанта!");
			dirList.setListData(arrDiskContent);
			box_nomera_stolov.setEnabled(false);
			
			box_main_bluda.setEnabled(false);
			box_bluda.setEnabled(false);
			box_pcs.setEnabled(false);

		}// if

		box_oficianti.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent e) {
				// TODO Auto-generated method stub
				if (box_oficianti.getSelectedItem() != null) {
					arrDiskContent.removeAllElements();
					arrDiskContent.add("Официант: "
							+ box_oficianti.getSelectedItem());
					arrDiskContent.add("Выберите стол заказа!");
					dirList.setListData(arrDiskContent);
					box_nomera_stolov.setEnabled(true);
					;
					box_main_bluda.setEnabled(true);
					box_bluda.setEnabled(true);
					box_pcs.setEnabled(true);

				}// if
				else {
					box_main_bluda.setEnabled(false);
					box_bluda.setEnabled(false);
					box_pcs.setEnabled(false);
				}
			}
		});

		box_nomera_stolov.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent e) {
				// TODO Auto-generated method stub
				if (box_nomera_stolov.getSelectedItem() != null) {
					arrDiskContent.remove(1);
					// arrDiskContent.add("Официант: "+box_oficianti.getSelectedItem());
					arrDiskContent.add("Заказ для стола: "
							+ box_nomera_stolov.getSelectedItem());
					dirList.removeAll();
					
					dirList.setListData(arrDiskContent);
					box_main_bluda.setEnabled(true);
					box_bluda.setEnabled(true);
					box_pcs.setEnabled(true);

				}// if
				else {
					box_main_bluda.setEnabled(false);
					box_bluda.setEnabled(false);
					box_pcs.setEnabled(false);
				}
			}
		});

		// box_main_bluda.addItemListener(new ItemListener() {
		//
		//
		// @Override
		// public void itemStateChanged(ItemEvent e) {
		// // TODO Auto-generated method stub
		// if (box_oficianti.getSelectedItem()!=null) {
		// arrDiskContent.remove(1);
		// // arrDiskContent.add("Официант: "+box_oficianti.getSelectedItem());
		// arrDiskContent.add("Заказ для стола: "+box_nomera_stolov.getSelectedItem()
		// );
		// dirList.removeAll();;
		// dirList.setListData(arrDiskContent);
		// box_main_bluda.setEnabled(true);
		// box_bluda.setEnabled(true);
		// box_pcs.setEnabled(true);
		//
		//
		// }//if
		// }
		// });
		//

		System.out.println("выбрано" + box_oficianti.getSelectedItem());

		// box_oficianti.setSelectedIndex(0);
		// box_nomera_stolov.setSelectedIndex(0);
		box_main_bluda.setSelectedIndex(0);
		box_bluda.setSelectedItem(0);

		Vector<Menu> firstTempGrupMenu = MainGUI.mapGruppMenuBluda
				.get(box_main_bluda.getSelectedItem());

		for (Menu elem : firstTempGrupMenu) {
			box_bluda.addItem(elem.nameOfElementOfMenu);
		}// foreach

		box_main_bluda.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent event) {

				if (event.getStateChange() == ItemEvent.SELECTED) {
					System.out.println(" event.getItem()" + event.getItem());

					Vector<Menu> secondTempGrupMenu = MainGUI.mapGruppMenuBluda
							.get(event.getItem());
					box_bluda.removeAllItems();
					for (Menu elem : secondTempGrupMenu) {
						box_bluda.addItem(elem.nameOfElementOfMenu);
					}// foreach

				}// if
			}
		});

		box_bluda.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent event) {
				// TODO Auto-generated method stub
				if (event.getStateChange() == ItemEvent.SELECTED) {
					box_pcs.setSelectedIndex(0);
				}

			}
		});

		addWindowListener(new WindowListener() {

			@Override
			public void windowOpened(WindowEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void windowIconified(WindowEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void windowDeiconified(WindowEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void windowDeactivated(WindowEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void windowClosing(WindowEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void windowClosed(WindowEvent e) {
				// TODO Auto-generated method stub

//				dirList.removeAll();
//				box_oficianti.removeAll();
//				box_nomera_stolov.removeAll();
//				box_main_bluda.removeAll();
//				box_bluda.removeAll();
//				System.out.println("Закрыто окно");
			}

			@Override
			public void windowActivated(WindowEvent e) {
				// TODO Auto-generated method stub

			}
		});
		setVisible(true);

	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		new GUI_zakaz("Окно создания нового заказа");

	}

}// public class
