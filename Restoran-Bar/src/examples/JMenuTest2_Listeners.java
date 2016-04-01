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

//�������� ������ ��  ����
//������ ����� ���� - ��� �� ���� Button

//������������� ��� � ������������ ��� ��������� ��������� ��������� ������� ������� ������� -
//��������� � ������� �������� ������������ ������ ����� ����������� ��������������
//�.�. ����� ������ ����� ��������� -����������� ������������ ������-�� �������� 

public class JMenuTest2_Listeners extends JFrame implements ActionListener {

	

	public JMenuTest2_Listeners(String title) throws HeadlessException {
		super(title);
		// 
		setLocation(300, 300);
		setSize(500, 300);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE); //������� - ������� ��������� ����� �������� ���������� ����

		// setLayout(null); //�������� ����� ���������, �� �� ��� �� �����
		setLayout(new FlowLayout()); // �������� ����������() - ����������,
                 										// ��������� ���������� ����� �������
		
		
		//������:  ������� ����
		//�������                       ���������                                           HELP
		//��������                        ��������                                                  About
		//��������                        ��������
		//���������                     ��������� 
		//������ �� �/�����          ���/���
		//������ �� ������          ��������� �������
		                                           //����� ������� �������������
		
		//1. ������� ������� �������������� ������� ���� - �������
		JMenuBar  menuBar = new JMenuBar();  //�������� ������
					//������������ ������ �������� � ���� �������������
		this.setJMenuBar(menuBar);
		setJMenuBar(menuBar);
				//� ���� ����� ���� � ���� ����������� - �.�. ��� �� ���� JMenuBar - ��������� ������ 
		
		//1.2  ��������� ������� ����������
		JMenu  menuVertik1 = new JMenu("�������");
				//�������� � ��������
		menuBar.add(  menuVertik1 );
				//�� �� ����� ��������� ��������� - �� ������ ������� �� �������� �������, 
				//���� ���� ����� ���-�� � ������������ ���� ���������
		menuBar.add(   new JMenu("�������2") );
		
		//2. �������� � ������������� ���� ������� ��� �������-���
		JMenuItem menuItem = new JMenuItem("�������� �������");
		menuVertik1.add( menuItem );
		menuItem.addActionListener(   this );  //�������� ������� � ���������� � ���� ������ �����, 
																		//���� ���� � ��� ������ menuItem ������������ ��� �������� ���� ������� ����
		
										//���� ������ menuItem ������������ ��� ���� �������
										//�� ����� ���������� �������� � ���� ���� ���� ����� �� ��� ��,
										//�.�. ����� ��� ������� ����� ������, ���� ���� menuItem ��� ���� ����.
							menuItem = new JMenuItem("��������� �������");
		menuVertik1.add( menuItem );
		menuItem.addActionListener( /*����� ������ ��� ������ � ������������ ��������*/ new menuListenerChangeClientNotMy () );
		
							menuItem = new JMenuItem("�������� �������");
		menuVertik1.add( menuItem );
		menuItem.addActionListener( new menuListenerDeleteClientNotMy() );  //���� ���� ����� ����� ��������� � ������ ������, �� 
																							//���������� ���������� ��� � ����� ���� � 1� ��������� ������� ������������� 
																							//��� �������������
		
		menuItem = new JMenuItem("������ �� �/�����");
		menuVertik1.add( menuItem );
		menuItem = new JMenuItem("������ �� ���������");
		menuVertik1.add( menuItem );
		
		//�������� 2-� ������������ ����
		JMenu  menuVertik2 = new JMenu("BANKOMATS");
		//�������� � ��������
		menuBar.add(  menuVertik2 );
		
					//����� ������������ ��� �� menuItem
					//�� ��������� new � ��������� ������ ������������ ������, 
					//�.�. ����� ���� ����� ��������� ������� � ������� ������ ����
		menuItem = new JMenuItem("�������� ���������");
		menuVertik2.add( menuItem );
		
		menuItem = new JMenuItem("��������� ���������");
		menuVertik2.add( menuItem );
		
		menuItem = new JMenuItem("�������� ���������");
		menuVertik2.add( menuItem );
		
		menuItem = new JMenuItem("����/���");
		menuVertik2.add( menuItem );
		
		menuItem = new JMenuItem("��������� �������");
		menuVertik2.add( menuItem );
		
		//�������� ������� --------------------------------------------------------------------------------------
		//������: ������� ������������ ������� � ����������1  � �������� Print, Import, Export  
		//������� ����������� �� ����  ����������� �� ���� ��  �������� ������ ���������� �������� ������
		//����� ������� �������� ���������
		menuVertik1.add( new JSeparator() );
		
		JMenu subMenu = new JMenu("������/�������");  //� ����� ������� ����� ������ ����������� ���������
		menuVertik1.add(subMenu);  //������� ������ ���������� ������, ����� � ������() � JMenu ������� JMenu 
		
		//��������� ����� ������� ����������� �������� (��������)
				//������� � ������� ������ � ���������
		JCheckBoxMenuItem menuCheck = new JCheckBoxMenuItem("Print");
				//�������� - ������� ���������� ������ ����� �������� �� ������ ����.
		subMenu.add( menuCheck );
		
				//������� ��� �����-������
		JRadioButtonMenuItem menuRadioButt1 = new JRadioButtonMenuItem("Import");
		JRadioButtonMenuItem menuRadioButt2 = new JRadioButtonMenuItem("Export");
		subMenu.add( menuRadioButt1 );
		subMenu.add( menuRadioButt2 );
		ButtonGroup rbGroup = new ButtonGroup();
		rbGroup.add(menuRadioButt1);
		rbGroup.add(menuRadioButt2);
		
		//�������� ������������
		//������������ - ��� �������, ������� ���������� � ���������� � ������������� (������� ��� ��� ��� ������)
		//��� ������� ���� ���������� ����������� �������� ����� �� ����
		//������:  ����������� ������ ���� ������ ���������� ������ �������-I
		menuRadioButt1.setAccelerator( KeyStroke.getKeyStroke( 'I', KeyEvent.CTRL_MASK));
		menuRadioButt2.setAccelerator( KeyStroke.getKeyStroke( 'E', KeyEvent.CTRL_MASK));
		
		//���������� ���������
		//��������� - ���������� ����� � �����
		//����� ��� ��������� ������ ����������� � ����� � �������� ���� - 
		//� ��� ����� � ���� ��������������. (������������� ������ �� ��������, �.�. ��� �������
		//�� ����� ��, � ����� ������ ����� ������������� ��� �� �����������).
		//������: ��������� ��� ��������� ���� ������ ����� 'P'
		menuCheck.setMnemonic('P');  //������������ ��������� ������� ���� ������� ���. ���������
		menuVertik2.setMnemonic('B');  //��������� � ������� � ������� ���� ���� �������� ��� ���. ��������.
		
		//���������� ���� ------------------------------------------------------------------------------------
		//1. ���� ���� � ������
			//������� �������������� ��������
		menuBar.setBackground( Color.RED);  //�������� � ���������� �� ��������� � ����� ������ 
																		//������ �� �� ���������� ������� ���� ������������ ��� ���������� �����
		menuBar.setForeground( Color.RED);   //�� �������� � ���������� �� ��������� � ����� ������
																			//�������� � ���������� �����
				//������������ ���� - �������� � ������ ��������
				//��� ��������� �������� �  ������ Windows (��������������� � ���. UIManager) 
		menuVertik1.setBackground(Color.yellow);    //�� �������� � ���������� �� ��������� � ����� ������
		menuVertik1.setForeground(Color.GREEN);  //�������� ����  � ���������� �� ��������� � ����� ������
				//���������� ����� ���� - ������ �������� ��� ���������
		menuItem.setBackground(Color.RED);
		menuItem.setForeground(Color.GREEN);
		
				//��������� ������� - setPreferredSize
		menuBar.setPreferredSize( new Dimension(150, 50));  //������ ��������, � ����� ��� - ����� ��������� ��������� ��� ����
		menuVertik1.setPreferredSize(new Dimension(100, 70)); //�������� ������ �����, ������ ���
		menuItem.setPreferredSize(new Dimension(150, 60));     //�������� � ������ � ������
		
				//���������� � �������� ������� ���� - ����������� � ������� �����
		menuVertik1.setBorder( new BevelBorder(BevelBorder.RAISED ));		//�������� ���� ����� ����
		menuVertik2.setBorder( new BevelBorder(BevelBorder.LOWERED ));  //���������� ���� ����� ����
		
			//��������� ������� - ������� � ��� ���������
		
		
		//------------------------------------------------------------------------���� 41 ---------------------------------------------------------------
		//������ ����� ���� - ��� �� ���� Button
		//������:  �������� ����� ���� ����� �1� ������������ ����
		//                  � ���������� � ����� ������ ��������� ������� � ���������� ������� ������ �� �����.
		//1. �������� ����� ����
	//	menuItem = new JMenuItem("�����");
	//	menuVertik1.add(menuItem);
		//2. �������� ��������� ����� �� ���� ����� ���������
		//menuItem.addActionListener( new  ActionListener() {
				//3 ������ ���� ����� �������� ����� �������
		menuVertik1.add(  new JMenuItem("�����")  ) .addActionListener(  new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				System.out.println("��������� ����� �� ���� �����");
				//return;   //��������� ����� �� ����� ������, ���� �� ������� ������ �����()
				
							//�� ������ ����� ������� ������ � �����-���� ���� �� ������ ��� ���� ���������.
				System.exit( 0 );  //� ��������� ��� ��������� ����� - 0 - ��� ��� ���������, �����. ����� - ��� ������
											//������������� ��� ��������� ������ �� ����� ������ ����� exit-� � ���������� ����� ������
				int x =0;  //���� ��� ����� exit()   ������� �� ����� ��������, �� ���������� ������ 
			}
		}) ;
	
			//��� ��������� ������������� ����� ������ ����� �� ����������� ������� �������� � ����������, 
			//�  ����� �������  ������  ����� ���� ����� (� ��� ��� ������) � � ����� ������� ���������� ����� �������� ����� ������� 
	//menuVertik1.add(  new JMenuItem("�����")  ) .addActionListener(  new ActionListener() {
			
			//����� �������� ������ ������� � ����� ���������� - ���� ����� ������-���������� ���� ����������.
			//� ��������� ���������� �������� ������ ������� �� ���������, � ������ ������ ����� ������� � ��������� ����������� ������
		
		//������: ���������� � ������� ������ ����  "��������  �������"  ������� � ������� ����� this
		//�� �.�. ��� ���������� ����������� �� ������������ ���� � ��� ������ menuItem ��� ���� ������� ����,
		//�� � ���� ����� ���� �� ��� �� ����� ������� � ���������� ������ ����. 
		//������� ��� ������ ����� ������ ������  ������, 
		//��� ��� ����������  ����� ����  "��������  �������"  - ��. ������ 67 ���������
		
		
		setVisible(true);
		
	}//constructor

	public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException {
		// TODO Auto-generated method stub
		UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		
		new JMenuTest2_Listeners("���� � ����");
		
	} //main()

	
	//��� ���������� ��� addActionListener(�� ������   this)
	@Override    //���� ���������� ������������ ��� ���� ������� ����, 
						//� ������� ���������� addActionListener(this) -- ������� � ��� �������� ��� �����������
						//��������� ������� ���� ������������ if( event.getSource() == ... �����-�� ����������)
				//�� ���� �� ���� ������� ���� ������������� ���� � ��� menuItem , �� �������� �����
				//�  if( event.getActionCommand().equals( �������� ������� ����� ����  )   )
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		System.out.println("��������� ����� �� ���� ��������   ������� ������� ������ this");
	}

}  //public class

//����� ����� ������ ����� �����  ��� ��������� ������� ������ ���� ��������� �������
//��� �������� ������� ������-���� - ������� ������ ��� ��� ���������� ����� ���� ��������� ������� - 
//����������� addActionListener(  �����-�� �����   ����� ) 
//����� ���� ����� ���� ��������� ����, � ���� ����� ����� ������� � ���� �� �����, ���� ��� ���� � ����� �����
class  menuListenerChangeClientNotMy implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		System.out.println("����� ����� �������� , �������������� ���� �� ���� ���������� ������� ");
			
	}
}  //class  menuListenerChangeClientNotMy


//�� �� ����� ����� ������� ������� �����-�����
//������: ������� ��������� ����� �� ����  ��������  �������  � ������� ������ ������ ��������
//1. ������� ������� ����� �����
class  menuListenerDeleteClientNotMy implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		System.out.println("����� ����� �������� , �������������� ���� �� ���� ��������  ������� ");
	}
	
}
//2. ����� ��������� �����������, �� ������ ��� ��� ���������� ����� ���� ��������  �������
//���������  ���� ������� � ������ ������ ���� ��������  �������