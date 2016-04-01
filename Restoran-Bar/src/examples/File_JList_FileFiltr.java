package examples;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.io.File;

import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

//1. Работа с файлами из Джава-программ
//2. Показывание в списке файлов с помощью ФайлФильтра

public class File_JList_FileFiltr extends JFrame {
		//Удобнее работать с компонентами если они объявлены в области полей
	
	//ГУИ список  с ДИСКАМИ  компа 
	//Сделать окно, которое в левой части будет иметь список дисков компа, а в правой части - содержимое кликнутого диска
				//список в левой части окна
	JList  diskList = new JList<>(File.listRoots() );  //создали ГУИ список и занесли туда список ДИСКОВ компа
			   //список в правой части окна
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
		
		//ЗАДАЧА: СДЕЛАТЬ ТАК, ЧТОБЫ при клике по 1-му списку дисков , 
		//во 2м списке появлялись файлы и папки этого диска
		diskList.addListSelectionListener(  new  ListSelectionListener() {
			
			@Override
			public void valueChanged(ListSelectionEvent evt) {
				// TODO Auto-generated method stub
				//1. Определить диск, по которому был клик
				 File  disk = (File) diskList.getSelectedValue() ;  //эксепшина не будет, т.к. в Списке содержится массив типа File [ ] 
				//2. Вывести во 2м списке содержимое кликнутого диска
				//File   dir.  = new File("");
				 String [ ]  arrDiskContent = disk.list();  //возвращает список ИМЕН файлов-папок ВНУТРИ папки -владельце метода
				 
				 for (String currStr : arrDiskContent) {
					System.out.println( currStr );  //текущее имя файла вывести на консоль
				}
				//3. Занести список строк во 2й ГУИ список в окне справа
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
