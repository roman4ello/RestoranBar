package version_1_1;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.ComponentOrientation;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.Insets;
import java.awt.LayoutManager;
import java.awt.Toolkit;
import java.io.File;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;
import javax.tools.JavaCompiler;

public class GUI_zakaz extends JFrame{

	final int x_screenSize = (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth();
	final int y_screenSize = (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight();
	int x_size = 800; int y_size = 400;

	
	final static JLabel label_oficiant = new JLabel("Официант");
	final static JLabel label_number_stol = new JLabel("Номер стола");
	final static JLabel label_main_spisok_blud = new JLabel("Меню блюд");
	final static JLabel label_bluda = new JLabel("Блюда");
 
	final static JLabel label_info = new JLabel("Информация о заказе:");
	
	final static JComboBox<String> box_oficianti = new JComboBox() {
	
//так поборолся с тем что комб.бокс растягивается в БоксЛейауте
	        @Override
	        public Dimension getMaximumSize() {
	            Dimension max = super.getMaximumSize();
	            max.height = getPreferredSize().height;
	            return max;
	        }

	    };
	   	
	final static JComboBox<String> box_nomera_stolov = new JComboBox(){
		 @Override
	        public Dimension getMaximumSize() {
	            Dimension max = super.getMaximumSize();
	            max.height = getPreferredSize().height;
	            return max;
	        }
	};
	
	final static JComboBox<String> box_main_bluda = new JComboBox(){
		 @Override
	        public Dimension getMaximumSize() {
	            Dimension max = super.getMaximumSize();
	            max.height = getPreferredSize().height;
	            return max;
	        }
	};
	final static String[]  blud = {"sdsda","asdasd","asdasd"};

	final static JComboBox<String> box_bluda = new JComboBox(blud){
		 @Override
	        public Dimension getMaximumSize() {
	            Dimension max = super.getMaximumSize();
	            max.height = getPreferredSize().height;
	            return max;
	        }
	};
	
	final static Integer [] numbers ={0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30};
	
	
	
	final static JComboBox<Integer> box_pcs = new JComboBox(numbers){
		 @Override
	        public Dimension getMaximumSize() {
	            Dimension max = super.getMaximumSize();
	            max.height = getPreferredSize().height;
	            max.width = getPreferredSize().width;
	            return max;
	        }

	};
	
	
 	
	
	
	
	final static JButton but_pay = new JButton("Оплатить заказ сейчас");
	final static JButton but_ok = new JButton("Ок");
	final static JButton but_cancel = new JButton("Отмена");
	{
		but_ok.setPreferredSize(new Dimension(150,30));
		but_cancel.setPreferredSize(new Dimension(150,30));
		but_pay.setPreferredSize(new Dimension(300,40));
	}
	 	

	 final static boolean shouldFill = true; 
	 final static boolean shouldWeightX = true; 
 	
	 static JPanel leftPanel = new JPanel();
	 static JPanel rightPanel = new JPanel();
	 static JPanel centralPanel = new JPanel();
	 static JPanel botomPanel = new JPanel();
	 static Font font_info =  new Font("Areal",Font.BOLD,14);
 	 
	 String str = "";
	 public static void addComponentsToPane(Container pane) { 
 		 	//правая часть
		 	rightPanel.setBorder(BorderFactory.createEmptyBorder(5,10, 65, 5));
		 	rightPanel.setLayout(new BorderLayout());
		 	rightPanel.add(label_info,BorderLayout.NORTH);
		 	label_info.setFont(font_info);
		 	label_bluda.setFont(font_info);
		 	label_main_spisok_blud.setFont(font_info);
		 	label_number_stol.setFont(font_info);
		 	label_oficiant.setFont(font_info);
		 	 
		 	label_info.setHorizontalAlignment(SwingConstants.CENTER);
			   JList  dirList = new JList(File.listRoots());
			   dirList.setForeground(Color.black);
   		 	
			   String [ ]  arrDiskContent = {"sadasd","34534aeee","saasdasda24","sadasd"};
			   dirList.setListData(arrDiskContent);
			   dirList.setToolTipText("sadasd");
			
			   rightPanel.add(dirList,BorderLayout.CENTER);
		 	//---------------------------------------------------------------
			   	leftPanel.setBorder(BorderFactory.createEmptyBorder(0,10, 10, 40));

  		        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
  		        leftPanel.add(Box.createRigidArea(new Dimension(20,10)));

  		        leftPanel.add(label_oficiant);
  		        leftPanel.add(box_oficianti);
  		        leftPanel.add(Box.createRigidArea(new Dimension(0,5)));

  		        leftPanel.add(label_number_stol);
  		        leftPanel.add(box_nomera_stolov);
  		        leftPanel.add(Box.createRigidArea(new Dimension(0,5)));
	  
  		        leftPanel.add(label_main_spisok_blud);
  		        leftPanel.add(box_main_bluda);
  		        leftPanel.add(Box.createRigidArea(new Dimension(0,5)));

  		        
  		        //строка Лейблов названия блюд и шт-------------------------------------
  		         
  		        leftPanel.add(label_bluda);
		        
    		        
  		        
  		        
  		      //строка комбобоксов блюд+шт-------------------------------------------
  		        JPanel panel_blud = new JPanel();
  		        panel_blud.setLayout(new BoxLayout(panel_blud, BoxLayout.X_AXIS));
  		           
  		        panel_blud.add(box_bluda);
  		        panel_blud.add(Box.createRigidArea(new Dimension(30,0)));
  		        panel_blud.add(box_pcs);
  		        
  		        leftPanel.add(panel_blud);
  		        leftPanel.add(Box.createRigidArea(new Dimension(0,30)));
  		        
  		      leftPanel.add(but_pay);

  		         
			//---------------------------------------------------------------

			//нижняя часть ------------------------------------------
			   
				botomPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
				botomPanel.setLayout(new FlowLayout(1,200,2));// (1- размещение по центру,
			 												  //  200 -расстояние между кнопками,
			 												  //  2 - отступ по вертикали)
			 	botomPanel.add(but_ok);
			 	botomPanel.add(but_cancel);
			 	
			 	centralPanel.setLayout(new GridBagLayout());
		        GridBagConstraints c2 = new GridBagConstraints(); 
	  
		        c2.fill = GridBagConstraints.HORIZONTAL; 
		        c2.weightx = 0.7; 
		        c2.gridx = 0; 
		        c2.gridy = 0; 
		        c2.ipady= 255;
		        centralPanel.add(leftPanel,c2);
			 	 
			 	c2.fill = GridBagConstraints.HORIZONTAL; 
			    c2.weightx = 0.3; //cоотношение между компонентами - вес их по оси Х
			    c2.gridx = 1; //размещение по Х - вторым по счету
			    c2.gridy = 0; // по оси У - на ступени 0
		        c2.ipady= 255;
		        centralPanel.add(rightPanel,c2);
			
			//=-------------------------------------------------------   
 				 	
			 	pane.setLayout(new BorderLayout(45, 1));
			 	pane.add(centralPanel,BorderLayout.CENTER);
			 	pane.add(botomPanel,BorderLayout.SOUTH);
			   

			 	centralPanel.setBorder(BorderFactory.createEmptyBorder(5, 10, 10, 5));

			//------------------------------------------
			 	
			 	box_oficianti.setBackground(Color.WHITE);
			 	box_pcs.setBackground(Color.WHITE);
			 	box_main_bluda.setBackground(Color.WHITE);
			 	box_bluda.setBackground(Color.WHITE);
			 	box_nomera_stolov.setBackground(Color.WHITE);
		 
			 	box_oficianti.setForeground(Color.BLACK);
			 	box_bluda.setForeground(Color.BLACK);
	    } 
	
	public GUI_zakaz(String title) throws HeadlessException {
		super(title);
		setSize(x_size, y_size);
		setLocation((x_screenSize - x_size)/2, (y_screenSize - y_size)/2);
 		
		
 
          setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); 
          
         // Установить панель содержания 
         addComponentsToPane(getContentPane()); 
  
         // Показать окно 
           setVisible(true); 
           
           
 	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		new GUI_zakaz("Заказ");
		 	        
 	    } 
	
	
	

	}//public class


