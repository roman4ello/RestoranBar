package examples;
//�������� �� �������� � ���������
//���������� � �������� ����� (�������)



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

	static Object[][] data = {  //��� Object - �.�. � �������� ����� ������� ������ �����
											//�.�. � ����� ��������� ��-������������: "��������� ������ �������� ������������� � ��������"
											//������-�� ���� ������� ������ � ����� Object ������, �.�. ��� �������� �����
											//�� ��������, ���� �������� �����������
		{ "������ ������ �����", 1000,                        "2014.03.17", "10:17:40" },
		{ "������ �������",        new Integer(2000), "2014.03.17", "10:15:60" },
		{ "������ ������ �����",new Integer(3000), "2014.03.17", "13:18:30"},
		{ "����� ������� ",         new Integer(4000), "2014.03.17", "14:12:40" },
		{ "������ ������ �����",new Integer(5000), "2014.03.17", "11:15:60" },
		{ "������ � ����-�����",new Integer(6000), "2014.03.17", "09:11:00" },
		{ "������ � ����-����� ",new Integer(7000),"2014.03.17", "15:17:40" }
		};
	
		//������ ����������
	String [ ]   colNames =  {"��� ��������", "�����", "����", "�����"};
	
			//������� ������ �������
	JTable table = new JTable(data, colNames);
	
	Color oldColor = table.getSelectionBackground();  //���� ���� ��� ����������� tableChanged
	
	
	public JTable_RowsCols(String title) throws HeadlessException { // '������� ������� �������� ����
																	
		super(title);

		setLocation(300, 300);
		setSize(500, 300);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE); //������� - ������� ��������� ����� �������� ���������� ����

		// setLayout(null); //�������� ����� ���������, �� �� ��� �� �����
		setLayout(new FlowLayout()); // �������� ����������() - ����������,
                 										// ��������� ���������� ����� �������


		//������� ������ ���������
		JScrollPane  scrollPan = new JScrollPane(table);
		scrollPan.setPreferredSize( new Dimension(400, 100) );
		
		table.setPreferredScrollableViewportSize(new Dimension(400, 100));
		add(scrollPan);
	
//add(table);		//����� ����� ������� ��� ������ ��������� �� ���������� �������� ��������
					
		//------------------------------------------------------���� 40 ------------------------------------------------------------------------------------------------
		setVisible(true);
		
			//1.  ������������  �������
	table.setCellSelectionEnabled( true);  //���. ����� ��������� ������
			//2 ������� - ����� ����  � ����� ������ �������
	table.moveColumn(0, 2);
	table.getColumnModel().moveColumn(0, 3);
			//������ ������������ ����� ���
	//table.getModel().
	
		//2. �������� �������
			//2 �������
	table.removeColumn( /*������ �������*/  table.getColumnModel().getColumn(2)  );
	//table.getColumnModel().removeColumn(   table.getColumnModel().getColumn(2)  );
	
			//3. ������� �������
	//table.addColumn( /* ������ �������*/);  //� ��������� ����� ������ �������, � � ����� ����� ��� ���� ���������
	
	//� ����� ���� ��� ��� ����� ������ ����������� ���������-������� �� ������� , � ������
	//������ ��� ��������  �  � ��������� � �� �������� �������� � �������
	//���������� ������  ���������������������� 
	DefaultTableModel  defTableModel = new DefaultTableModel(data, colNames);
			//��������� ��������� ���� ������ ������ � ����� ��� ����������� table, ������� ���������� � ����
	table.setModel(defTableModel);
			
	//3. �������  �������
	defTableModel.addColumn("5� �������");
	table.moveColumn(4, 2);
			//�������  ������� � �������
	String [ ] arrDataForCol = {"���", "���", "���", "����", "�����������" };
	defTableModel.addColumn("6� �������", arrDataForCol);
	//defTableModel.re
	
			//Drag & Drop - ������ � ������� - ������������� ����� � ������� ����� �������
	//Drag - �������� ���������� ���������� �� ����� ��������� - (������)
	//Drop - ���������� ���������� ������� ����� ���������� - (�������)
	table.setDragEnabled( true);
	
	
	
	} // construcror

	public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException {
		// TODO Auto-generated method stub
		
		//������� ������� ��� ��������� ��� � ��
UIManager.setLookAndFeel(   UIManager.getSystemLookAndFeelClassName());
		
		
		//��� ������ ���������� ������� - �� ���������, �.�. � ���� ��� �����
							//������� ���������� 1 ��� � �����, ������� ��� �� �� �����
		 new JTable_RowsCols("JTable");

	}//main()


}// class
