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

//�������� ������������  ���� - ������� ���������� ��� ����� ������ ������� ���� (���)


public class MenuTest3_PopUp_ContextMenu extends JFrame{

	

	public MenuTest3_PopUp_ContextMenu(String title) throws HeadlessException {
		super(title);
		// 
		setLocation(300, 300);
		setSize(500, 300);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE); //������� - ������� ��������� ����� �������� ���������� ����

		// setLayout(null); //�������� ����� ���������, �� �� ��� �� �����
		setLayout(new FlowLayout()); // �������� ����������() - ����������,
                 										// ��������� ���������� ����� �������
		

		//------------------------------------------------------------------------���� 41 ---------------------------------------------------------------
	//�������� ������������ ����.
	//1. �������� ���� ��� - ��������� ���� MouseListener
		//�������  ��������� ������ ��� ������ � ������� - ������� 
		//�������� ��� �� ����� ����� ������������ - ��. ����� �����
 //2. ������������ ����� ������� � ������ ���������� - � ������ ������ ������ � ����
		this.addMouseListener( new  mouseListenerForPopUpContextMenu() ) ;
 //3. ��� ��������� ������������ ���� ���� ��� �������	- ��. ������ ����������� ��� � ����� �����	
		
		
		setVisible(true);
		
	}//constructor

	public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException {
		// TODO Auto-generated method stub
		UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		
		new MenuTest3_PopUp_ContextMenu("���� � ����");
		
	} //main()

}  //public class


//����� ������ �����������  ������������
//������� �����_����� - ������� �������� ������ ��� ����� ����������
class mouseListenerForPopUpContextMenu  extends  MouseAdapter { //���������� ��� �������� ����� MouseAdapter
																//��� ���� implements ���� �������� �� extends,
																//�.�. ����� implements ����� ���� ������ ��� ���������� � ���������� ������� � ��������������
																//����� extends ����� ���� ����� ���� �����, �.�. � ����� ��������� ������������� ������������

	@Override
	public void mouseClicked(MouseEvent evt) {
		// TODO Auto-generated method stub
		//�������� ���� ���
		if ( evt.getModifiers() == evt.BUTTON3_MASK) {
		//	if ( evt.getModifiersEx() == evt.BUTTON3_DOWN_MASK) {	
		//	if ( evt.getButton() == evt.BUTTON3) {	
			System.out.println("���� ������ ��� � ��� ���������,  � ������� ��������� ���� ������� ");
			//���� ������� ����� ��������� � ���������� �����������, �� �������� ������ ������������ ��� 
			//if (evt.getSource()  ��� evt.getCommand() ) � �.�
			
			//����� � ���� ����� ����� ���������
			
			//3. ��� ��������� ������������ ���� ���� ��� �������	.
			//����� ����� ��� ������� ������ ����������� ����
			JPopupMenu popUpContextMenu = new JPopupMenu();  //��� ������ JMenu - ������������ ����
			//��������� � ������������ ����� ���� ���������� ������ ���� - ����������
//JMenuItem menuItem = new JMenuItem("����������");
//popUpContextMenu.add(menuItem);
					//������ ���� 2� ������� ����� � ���� ������ 
			JMenuItem menuItemCopy = popUpContextMenu.add( new JMenuItem("����������") );   //.addActionListener( );
			popUpContextMenu.addSeparator();
			JMenuItem menuItemInsert = popUpContextMenu.add( new JMenuItem("��������") );   //.addActionListener( );
			
			//4. �������� ����������� ����� ����
			popUpContextMenu.show(evt.getComponent(), 70, 70);  //2� 3� ��������� - ���������� �������� ������ ���� ����� ������� ����
																					//1� �������� - ��� ����������, �� �������� ������ ���� ������� ������������ ���������� ���������� ����
																		//������ 1� �������� - ��� �� ����������, �� ������� ��� ���� ���
					//����� ������� ����� ���� ���, ����� ��� ������� ����� ���� �������� � �������� �������
			popUpContextMenu.show(evt.getComponent(), evt.getX(), evt.getY()); 
				//JPopupMenu.setVisible ()  �� ����� ��������� � ��������� - ������� ������� ��������� ������ � ������� ����� ���� ����������
			
			
			//5. �������� � ������� ����� ���� �������� � �������������
			menuItemCopy.addActionListener( new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					System.out.println("��� ���� �� ���� ����������  � ����������� ����� ����");
				}
			}) ;
			
			menuItemInsert.addActionListener( new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					System.out.println("��� ���� �� ���� ��������  � ����������� ����� ����");
				}
			}) ;
			
		}//if
	} //���������� ����� ���

}


