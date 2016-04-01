package version_1_1;
import java.awt.*; 
import javax.swing.JButton; 
import javax.swing.JFrame; 
 
public class GridBagLayoutDemo { 
    final static boolean shouldFill = true; 
    final static boolean shouldWeightX = true; 
    final static boolean RIGHT_TO_LEFT = false; 
 
    public static void addComponentsToPane(Container pane) { 
        if (RIGHT_TO_LEFT) { 
            pane.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT); 
        } 
 
        JButton button; 
        pane.setLayout(new GridBagLayout()); 
        GridBagConstraints c = new GridBagConstraints(); 
         
        if (shouldFill) { 
            // ����������� ������, ������������ ������ 
            c.fill = GridBagConstraints.HORIZONTAL; 
        } 
 
        button = new JButton("Button 1"); 
 
        if (shouldWeightX) { 
            c.weightx = 0.5; 
        } 
 
        c.fill = GridBagConstraints.HORIZONTAL; 
        c.gridx = 0; 
        c.gridy = 0; 
        pane.add(button, c); 
 
        button = new JButton("Button 2"); 
        c.fill = GridBagConstraints.HORIZONTAL; 
        c.weightx = 0.5; 
        c.gridx = 1; 
        c.gridy = 0; 
        pane.add(button, c); 
         
        button = new JButton("Button 3"); 
        c.fill = GridBagConstraints.HORIZONTAL; 
        c.weightx = 0.8; 
        c.gridx = 2; 
        c.gridy = 0; 
        pane.add(button, c); 
 
        button = new JButton("Long-Named Button 4"); 
        c.fill = GridBagConstraints.HORIZONTAL; 
        c.ipady = 40;      // ������� ��� ������ ������� 
        c.weightx = 0.0; 
        c.gridwidth = 3; 
        c.gridx = 0; 
        c.gridy = 4; 
        pane.add(button, c); 
 
        button = new JButton("5"); 
        c.fill = GridBagConstraints.HORIZONTAL; 
        c.ipady = 0;       // ���������� �������������� ������ ������ 
        c.weighty = 1.0;   // ���������� ������ 
        c.anchor = GridBagConstraints.PAGE_END; // ���������� ������ � ����� ���� 
        c.insets = new Insets(0, 0, 0, 0);  // ��������� �������� 
        c.gridx = 1;       // ��������� ��������� �� Button 2 
        c.gridwidth = 2;   // ���������� � 2 ������� 
        c.gridy = 2;       // � 3 ������� 
        pane.add(button, c); 
 
    } 
 
    private static void createAndShowGUI() { 
        // �������� ���� 
        JFrame frame = new JFrame("GridBagLayoutDemo"); 
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
         
        // ���������� ������ ���������� 
        addComponentsToPane(frame.getContentPane()); 
 
        // �������� ���� 
        frame.pack(); 
        frame.setVisible(true); 
    } 
 
    public static void main(String[] args) { 
        javax.swing.SwingUtilities.invokeLater(new Runnable() { 
            public void run() { 
                createAndShowGUI(); 
            } 
        }); 
    } 
} 