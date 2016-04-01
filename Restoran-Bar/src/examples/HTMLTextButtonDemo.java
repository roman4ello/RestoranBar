package examples;

import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

public final class HTMLTextButtonDemo {

    public static void main(String[] args){
        SwingUtilities.invokeLater(new Runnable(){
            @Override
            public void run() {
                createAndShowGUI();             
            }
        });
    }

    private static void createAndShowGUI(){
        final JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new FlowLayout());

        JButton readerStopButton_ = new JButton();
        readerStopButton_.setFocusPainted(false);
        readerStopButton_.setBackground(UIManager.getDefaults().getColor("Button.light"));
        readerStopButton_.setFont(new Font("Geneva", 0, 12)); // NOI18N
        readerStopButton_.setText("<html><center>READER<br>STOP</center></html>\n");
        readerStopButton_.setHorizontalTextPosition(SwingConstants.CENTER);

        frame.add(readerStopButton_);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}