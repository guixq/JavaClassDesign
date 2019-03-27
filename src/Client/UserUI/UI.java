package Client.UserUI;

import javax.swing.*;

public class UI extends JFrame {
    public UI(){
        super("test");
        this.setBounds(500,500,400,300);
        JPanel jPanel=new JPanel();
        ImageIcon imageIcon=new ImageIcon("E:\\高清美女\\01.png");
        JLabel jLabel=new JLabel(imageIcon);
        jPanel.add(jLabel);
        this.add(jPanel);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }


}
