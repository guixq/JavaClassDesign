package Client.Login;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.*;

public class Login extends JFrame implements MouseListener{

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    public static final String LOG_TITLE="登录界面设计";
    public static final int WINDOW_WIDTH=430;
    public static final int WINDOW_HEIGHT=330;


    Icon icon01,icon02,icon03,CIcon01,CIcon02,CIcon03;
    JLabel min=null;
    JLabel close=null;
    JLabel Front=null;
    public Login(){
        this.setTitle(LOG_TITLE);
        this.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        this.setLocationRelativeTo(null);
        this.setUndecorated(true);   //设置frame边框不可见
        this.setResizable(false);    //禁止改变窗口大小
        BorderLayout border_layout=new BorderLayout();
        this.setLayout(border_layout);
        ImageIcon im=new ImageIcon("./images/sao.png");
        JLabel jb=new JLabel(im);
        this.getLayeredPane().add(jb,new Integer(Integer.MIN_VALUE));
        jb.setBounds(0,0,im.getIconWidth(),im.getIconHeight());
        ((JPanel)this.getContentPane()).setOpaque(false);

        //北部面板
        icon01=new ImageIcon("images/min1.png");
        icon02=new ImageIcon("images/min2.png");
        icon03=new ImageIcon("images/min3.png");
        CIcon01=new ImageIcon("images/close01.gif");
        CIcon02=new ImageIcon("images/close02.png");
        CIcon03=new ImageIcon("images/close03.png");
        JPanel panelNorth=new JPanel();
        panelNorth.setOpaque(false);
        panelNorth.setLayout(null);
        panelNorth.setPreferredSize(new Dimension(430, 180));
        Front=new JLabel();
        min=new JLabel(icon01);
        min.setBounds(380,0,25,14);
        min.addMouseListener(this);
        panelNorth.add(min);
        close=new JLabel(CIcon01);
        close.setBounds(405,0,25,14);
        close.addMouseListener(this);
        panelNorth.add(close);
        this.add(panelNorth,BorderLayout.NORTH);

        //南部面板
        JPanel panelSouth=new JPanel();
        panelSouth.setOpaque(false);
        panelSouth.setLayout(null);
        panelSouth.setPreferredSize(new Dimension(420, 40));
        MyLineBorder myLineBorder = new MyLineBorder(new Color(192, 192, 192), 1 , true);

        /**
         * 登录按钮
         */
        ImageIcon image=new ImageIcon("images/Login.png");
        JButton btn=new JButton(image);
        btn.setBounds(128,0,image.getIconWidth()-10,image.getIconHeight()-10);
        btn.setBorder(myLineBorder);
        panelSouth.add(btn);
        this.add(panelSouth,BorderLayout.SOUTH);

        //中部面板
        JPanel panelCenter=new JPanel();
        panelCenter.setOpaque(false);
        panelCenter.setLayout(null);
        panelCenter.setPreferredSize(new Dimension(420, 160));
        JLabel JLUserName = new JLabel("用户名:");
        JLabel JLUserPaw = new JLabel("密  码:");
        JLUserName.setBounds(65, 20, 80, 20);
        JLUserName.setForeground(new Color(100,0,0));
        JLUserName.setFont(new Font("楷体",0,16));
        JLUserPaw.setBounds(65, 50, 60, 20);
        JLUserPaw.setForeground(new Color(0,0,0));
        JLUserPaw.setFont(new Font("楷体",0,16));
        /**
         * 用户名框
         */
        JTextField username=new JTextField();
        username.setBounds(130, 15, 175, 30);
        username.setBorder(myLineBorder);

        /**
         * 密码框
         */
        JPasswordField password=new JPasswordField(JPasswordField.LEFT);
        password.setBounds(130, 44, 175, 30);
        password.setBorder(myLineBorder);

        /**
         * 注册
         */
        JLabel regeist=new JLabel("注册");
        regeist.setForeground(new Color(100,149,238));
        regeist.setBounds(310, 20, 30, 20);
        regeist.setFont(new Font("宋体",0,14));

        /**
         * 注册
         */
        JLabel reset=new JLabel("重置");
        reset.setForeground(new Color(100,149,238));
        reset.setBounds(310, 50, 30, 20);
        reset.setFont(new Font("宋体",0,14));


        panelCenter.add(JLUserName);
        panelCenter.add(JLUserPaw);
        panelCenter.add(username);
        panelCenter.add(password);
        panelCenter.add(regeist);
        panelCenter.add(reset);
        this.add(panelCenter,BorderLayout.CENTER);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    public static void main(String[] args) {
        new Login();
    }

        @Override
        public void mouseClicked(MouseEvent e) {
            if(e.getSource()==min){
               this.setExtendedState(JFrame.ICONIFIED);
            }
            if(e.getSource()==close){
                System.exit(0);
            }
        }

        @Override
        public void mousePressed(MouseEvent e) {
            if(e.getSource()==min){
                min.setIcon(icon03);
            }
            if(e.getSource()==close){
                close.setIcon(CIcon03);
            }
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            if(e.getSource()==min){
                min.setIcon(icon02);
            }
            if(e.getSource()==close){
                close.setIcon(CIcon02);
            }
        }

        @Override
        public void mouseEntered(MouseEvent e) {
            if(e.getSource()==min){
                min.setIcon(icon02);
            }
            if(e.getSource()==close){
                close.setIcon(CIcon02);
            }
        }

        @Override
        public void mouseExited(MouseEvent e) {
            if(e.getSource()==min){
                min.setIcon(icon01);
            }
            if(e.getSource()==close){
                close.setIcon(CIcon01);
            }
        }
}
