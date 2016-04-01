package examples;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.io.File;

import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

//1. ������ � ������� �� �����-��������
//2. ����������� � ������ ������ � ������� �����������

public class File_JList_FileFiltr extends JFrame {
		//������� �������� � ������������ ���� ��� ��������� � ������� �����
	
	//��� ������  � �������  ����� 
	//������� ����, ������� � ����� ����� ����� ����� ������ ������ �����, � � ������ ����� - ���������� ���������� �����
				//������ � ����� ����� ����
	JList  diskList = new JList<>(File.listRoots() );  //������� ��� ������ � ������� ���� ������ ������ �����
			   //������ � ������ ����� ����
	JList  dirList = new JList();

	public File_JList_FileFiltr() {
		//super();
		
		setLocation(100, 300);
		setSize(500, 300);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); 
		setVisible(true);
		
		setLayout( new FlowLayout() );
		
		diskList.setPreferredSize( new Dimension(30, 170)) ;
		add(diskList);
		
		dirList.setPreferredSize( new Dimension(100, 170)) ;
		add(dirList);
		
		//������: ������� ���, ����� ��� ����� �� 1-�� ������ ������ , 
		//�� 2� ������ ���������� ����� � ����� ����� �����
		diskList.addListSelectionListener(  new  ListSelectionListener() {
			
			@Override
			public void valueChanged(ListSelectionEvent evt) {
				// TODO Auto-generated method stub
				//1. ���������� ����, �� �������� ��� ����
				 File  disk = (File) diskList.getSelectedValue() ;  //��������� �� �����, �.�. � ������ ���������� ������ ���� File [ ] 
				//2. ������� �� 2� ������ ���������� ���������� �����
				//File   dir.  = new File("");
				 String [ ]  arrDiskContent = disk.list();  //���������� ������ ���� ������-����� ������ ����� -��������� ������
				 
				 for (String currStr : arrDiskContent) {
					System.out.println( currStr );  //������� ��� ����� ������� �� �������
				}
				//3. ������� ������ ����� �� 2� ��� ������ � ���� ������
				 dirList.setListData( arrDiskContent );
				 
			}
		}) ;
		
		
		// TODO Auto-generated constructor stub
	}




	public static void main(String[] args) {
		// TODO Auto-generated method stub

		new   File_JList_FileFiltr();
		
	} //main

}
