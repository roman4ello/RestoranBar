package version_1_1;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.HeadlessException;
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
import javax.swing.SwingConstants;
 
public class GUI_zakaz extends JFrame{

	  int x_screenSize = (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth(),
		  y_screenSize = (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight();
	  int x_sizeOfGUI = 800, y_sizeOfGUI = 400;

	
	  static JLabel label_oficiant = new JLabel("Официант"),
			  		label_number_stol = new JLabel("Номер стола"),
			  		label_main_spisok_blud = new JLabel("Меню блюд"),
			  		label_bluda = new JLabel("Блюда"),
			  		label_pcs = new JLabel("<html><center>Количество</center></html>"),
			  		label_info = new JLabel("Информация о текущем заказе:");
	
	  static JComboBox<String> box_oficianti = new JComboBox() {
	
//pereopredelenie 4tobi combox ne rastagivalsa v visotu
	        @Override
	        public Dimension getMaximumSize() {
	            Dimension max = super.getMaximumSize();
	            max.height = getPreferredSize().height;
	            return max;
	        }

	    };
	   	
	  static JComboBox<String> box_nomera_stolov = new JComboBox(){
		 @Override
	        public Dimension getMaximumSize() {
	            Dimension max = super.getMaximumSize();
	            max.height = getPreferredSize().height;
	            return max;
	        }
	};
	
	  static JComboBox<String> box_main_bluda = new JComboBox(){
		 @Override
	        public Dimension getMaximumSize() {
	            Dimension max = super.getMaximumSize();
	            max.height = getPreferredSize().height;
	            return max;
	        }
	};
	  static String[]  blud = {"sdsda","asdasd","asdasd"};

	  static JComboBox<String> box_bluda = new JComboBox(blud){
		 @Override
	        public Dimension getMaximumSize() {
	            Dimension max = super.getMaximumSize();
	            max.height = getPreferredSize().height;
	            return max;
	        }
	};
	
	  static Integer [] numbers ={0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30};
		
	  static JComboBox<Integer> box_pcs = new JComboBox(numbers){
		 @Override
	        public Dimension getMaximumSize() {
	            Dimension max = super.getMaximumSize();
	            max.height = getPreferredSize().height;
	            max.width = getPreferredSize().width;
	            return max;
	        }

	};
	
	
 	
	
	
	
	  static JButton but_pay = new JButton("Оплатить заказ сейчас");
	  static JButton but_ok = new JButton("Создать заказ");
	  static JButton but_cancel = new JButton("Отмена");
	{
		but_ok.setPreferredSize(new Dimension(150,30));
		but_cancel.setPreferredSize(new Dimension(150,30));
		but_pay.setPreferredSize(new Dimension(300,40));
	}
	 	

	   static boolean shouldFill = true; 
	   static boolean shouldWeightX = true; 
 	
	 final static JPanel leftPanel = new JPanel();
	 final static JPanel rightPanel = new JPanel();
	 final static JPanel centralPanel = new JPanel();
	 final static JPanel botomPanel = new JPanel();
	 final static Font font_info =  new Font("Areal",Font.BOLD,14);
 	 
	 String str = "";
	 
	 final static int size_x_cbox = 350, size_y_cbox = 20;

	 
	 public static void addComponentsToPane(Container pane) { 
 		 	// Right panel
		 	rightPanel.setBorder(BorderFactory.createEmptyBorder(5,10, 65, 5));
		 	rightPanel.setLayout(new BorderLayout());
		 	rightPanel.add(label_info,BorderLayout.NORTH);
		 	label_info.setFont(font_info);
		 	label_bluda.setFont(font_info);
		 	label_main_spisok_blud.setFont(font_info);
		 	label_number_stol.setFont(font_info);
		 	label_oficiant.setFont(font_info);
		 	label_pcs.setFont(font_info);
		 	 
		 	label_info.setHorizontalAlignment(SwingConstants.CENTER);
			   JList  dirList = new JList(File.listRoots());
			   dirList.setForeground(Color.black);
   		 	
			   String [ ]  arrDiskContent = {"sadasd","34534aeee","saasdasda24","sadasd"};
			   dirList.setListData(arrDiskContent);
			   dirList.setToolTipText("Информация о заказе");
			
			   rightPanel.add(dirList,BorderLayout.CENTER);
		 	//---------------------------------------------------------------
 		        leftPanel.setLayout(null);
  
   		       	label_oficiant.setBounds(20, 0, 100, 20); leftPanel.add(label_oficiant);
  		       	box_oficianti.setBounds(10, 20, size_x_cbox, size_y_cbox); leftPanel.add(box_oficianti);
  		       	
  		       	label_number_stol.setBounds(20, 50, 100, 20); leftPanel.add(label_number_stol);
  		       	box_nomera_stolov.setBounds(10, 70, size_x_cbox, size_y_cbox); leftPanel.add(box_nomera_stolov);
  		       	
  		       	label_main_spisok_blud.setBounds(20, 100, 100, 20); leftPanel.add(label_main_spisok_blud);
  		       	box_main_bluda.setBounds(10, 120, size_x_cbox, size_y_cbox); leftPanel.add(box_main_bluda);
  		       	
  		       	label_bluda.setBounds(20, 155, 100, 20); leftPanel.add(label_bluda);
  		       	box_bluda.setBounds(10, 175, size_x_cbox-100, size_y_cbox); leftPanel.add(box_bluda);

  		       	label_pcs.setBounds(size_x_cbox-100+40, 145, 100, 40); leftPanel.add(label_pcs);
  		       	box_pcs.setBounds((size_x_cbox-40), 175, 50, 20); leftPanel.add(box_pcs);

  		       	but_pay.setBounds(120, 225, 200, 30); leftPanel.add(but_pay);
   		        
 		         
 
  		         
			//---------------------------------------------------------------

			//  ------------------------------------------
			   
				botomPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
				botomPanel.setLayout(new FlowLayout(1,200,2)); 
			 	botomPanel.add(but_ok);
			 	botomPanel.add(but_cancel);
			 	
			 	centralPanel.setLayout(new GridBagLayout());
		        GridBagConstraints c = new GridBagConstraints(); 
	  
		        c.fill = GridBagConstraints.HORIZONTAL; 
		        c.weightx = 0.8; 
		        c.gridx = 0; 
		        c.gridy = 0; 
		        c.ipady= 255;
		        
		        centralPanel.add(leftPanel,c);
		        
		        c = new GridBagConstraints();
			 	c.fill = GridBagConstraints.HORIZONTAL; 
			    c.weightx = 0.2;  
			    c.gridx = 1;  
			    c.gridy = 0;  
		        c.ipady= 255;
		        centralPanel.add(rightPanel,c);
			
			//=-------------------------------------------------------   
 				 	
			 	pane.setLayout(new BorderLayout(45, 1));
			 	pane.add(centralPanel,BorderLayout.CENTER);
			 	pane.add(botomPanel,BorderLayout.SOUTH);
			   

			 	centralPanel.setBorder(BorderFactory.createEmptyBorder(0, 5, 10, 5));

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
		setSize(x_sizeOfGUI, y_sizeOfGUI);
		setLocation((x_screenSize - x_sizeOfGUI)/2, (y_screenSize - y_sizeOfGUI)/2);
 		
 
 
          setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); 
          
         // 
         addComponentsToPane(getContentPane()); 
  
            setVisible(true); 
           
           
 	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		new GUI_zakaz("Окно создания нового заказа");
		 	        
 	    } 
	
	
	

	}//public class
