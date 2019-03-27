package Test;

import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.RoundRectangle2D;

import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRootPane;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CopyQQ extends JFrame implements MouseListener{
    JLabel close;//定义关闭标签
    JLabel min;//定义最小化按钮
    JLabel head,name;//定义头像标签
    Icon icon,icon2,icon3;//定义关闭图标
    Icon minIcon1,minIcon2,minIcon3;//定义最小化图标
    Icon headIcon,headIcon1;//定义头像图标
    Point pre,dra;
    JTextArea inputText;
    JTextArea content;//定义消息框，输入框
    JScrollPane jspsr,jspxx;//定义滚动面板
    JButton closeButton,sendButton;
    JTextField inputip;//定义ip输入框
    DatagramSocket d;//定义数据报端口
    String text;//定义接收到的消息
    String userIofo;//定义聊天对象信息
    String ip,hostName;
    newPanel np;//定义背景面板
    String now;//定义当前时间
    @SuppressWarnings("restriction")
    public CopyQQ(){
        super("JFrame Testing");
        //Container c=getContentPane();
        np=new newPanel();//实例化内部类面板对象
        np.setLayout(null);//设置np面板的布局
        /*最小化标签按钮*/
        minIcon1=new ImageIcon("./src/Test/img/min1.png");
        minIcon2=new ImageIcon("./src/Test/img/min2.png");
        minIcon3=new ImageIcon("./src/Test/img/min3.png");
        min=new JLabel();
        min.setBounds(533, 0, 28,18);
        min.setIcon(minIcon1);
        min.addMouseListener(this);
        np.add(min);
        /*close标签按钮*/
        icon=new ImageIcon("./src/Test/img/btn_close_normal.gif");//close标签图案
        icon2=new ImageIcon("./src/Test/img/btn_close_highlight.png");//close标签图案
        icon3=new ImageIcon("./src/Test/img/btn_close_down.png");//close标签图案
        close=new JLabel();
        close.setBounds(561,0,39,18);
        close.setIcon(icon);
        np.add(close);//将close加入到np面板
        /*设置头像*/
        headIcon=new ImageIcon("./src/Test/img/head.jpg");
        headIcon1=new ImageIcon("./src/Test/img/head1.jpg");
        head=new JLabel(headIcon);
        head.setBounds(10,10,61,60);
        head.addMouseListener(this);
        np.add(head);
        //获取本机信息
        try{
            InetAddress addr=InetAddress.getLocalHost();
            ip=addr.getHostAddress().toString();
            hostName=addr.getHostName().toString();
        }catch(Exception e){
            JOptionPane.showMessageDialog(this,"获取本机IP失败！");
        }
        /*设置网名*/
        name=new JLabel("<HTML>"+hostName+"</HTML>");
        name.setOpaque(false);
        name.setFont(new Font("仿宋",Font.PLAIN,20));
        name.setBounds(80,30,180,25);
        name.addMouseListener(this);
        np.add(name);
        //创建消息显示、输入面板
        JPanel np1=new JPanel();
        np1.setLayout(null);
        /*创建消息框*/
        content=new JTextArea();
        content.setBorder(BorderFactory.createLineBorder(Color.BLACK,0));
        jspxx=new JScrollPane(content);
        jspxx.setBorder(BorderFactory.createEmptyBorder(0, 0, 0,0));
        content.setOpaque(false);
        content.setLineWrap(true);
        //content.setEnabled(false);

        /*创建输入框*/
        inputText=new JTextArea();
        inputText.setOpaque(false);
        inputText.setLineWrap(true);
        inputText.setFont(new Font("楷体",Font.PLAIN,15));
        jspsr=new JScrollPane(inputText);

        jspsr.setBorder(BorderFactory.createEmptyBorder(0, 0, 0,0));

        //设置分割面板
        np1.setBounds(0,100,450,350);
        JSplitPane jsp=new JSplitPane(JSplitPane.VERTICAL_SPLIT,jspxx,jspsr);
        jsp.setPreferredSize(new Dimension(450,350));
        jsp.setBounds(0,-1,450,350);
        jsp.setContinuousLayout(true);
        jsp.setDividerLocation(230);
        jsp.setDividerSize(20);
        np1.add(jsp);
        np.add(np1);
        //关闭、发送按钮
        closeButton=new JButton("关闭(C)");
        closeButton.setMnemonic('C');
        closeButton.setBounds(260,460,80,25);
        closeButton.setBorder(BorderFactory.createLineBorder(Color.cyan));
        closeButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        np.add(closeButton);
        sendButton=new JButton("发送(s)");
        sendButton.setMnemonic('S');
        sendButton.setBounds(370,460,80,25);
        sendButton.setBorder(BorderFactory.createLineBorder(Color.cyan));

        np.add(sendButton);
        //qq秀
        JLabel qqshow=new JLabel();
        Icon show=new ImageIcon("./src/Test/img/qqshow.jpg");
        qqshow.setIcon(show);
        qqshow.setBounds(455,100,140,226);
        np.add(qqshow);
        //输入对方ip框
        JLabel iptext=new JLabel();
        iptext.setText("您的聊天对象是：");
        iptext.setBounds(10,75,100,20);
        //iptext.setOpaque(true);
        np.add(iptext);
        inputip=new JTextField(10);
        inputip.setBounds(110,75,100,20);
        inputip.setText("请输入对方的IP");
        inputip.setEnabled(false);
        inputip.addMouseListener(this);
        np.add(inputip);
        //接收消息
        try {
            d=new DatagramSocket(80);
        } catch (SocketException e1) {
            e1.printStackTrace();
        }

        new Thread(new Runnable(){
            @SuppressWarnings("deprecation")
            public void run(){
                byte[] buf=new byte[1024];//定义数组缓冲区，用来接收字节
                DatagramPacket p=new DatagramPacket(buf,1024);
                while(true){
                    try{
                        d.receive(p);
                        Date dt=new Date();
                        DateFormat df=new SimpleDateFormat("yy/MM/dd HH:mm:ss");
                        now=df.format(dt);
                        userIofo=p.getAddress().getHostName()+"  "+now ;
                        text=userIofo+"\n"+new String(buf,0,p.getLength())+"\n";
                        content.append(text);
                    }catch(Exception e2){

                    }
                }
            }
        }).start();
        //发送消息
        sendButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                byte[] buf;//定义存放消息的数组缓冲区
                buf=inputText.getText().getBytes();
                try{
                    DatagramPacket p=new DatagramPacket(buf,buf.length,InetAddress.getByName(inputip.getText()),80);
                    d.send(p);
                    DateFormat df=new SimpleDateFormat("yy/MM/dd HH:mm:ss");
                    Date dt=new Date();
                    now=df.format(dt);
                    userIofo=p.getAddress().getHostName()+"  "+now ;
                    content.append(hostName+"  "+now+"\n"+inputText.getText()+"\n");
                    inputText.setText("");
                }catch(Exception e1){
                    e1.printStackTrace();
                }
            }
        });
        //聊天对象信息
        JLabel myIofo=new JLabel("我的信息：");
        JLabel myIofo1=new JLabel("IP:"+ip);
        JLabel myIofo2=new JLabel("主机名:"+hostName);
        JLabel myIofo3=new JLabel("操作系统为:"+System.getProperty("os.name"));
        JLabel myIofo4=new JLabel("用户名为:"+System.getProperty("user.name"));
        JLabel myIofo5=new JLabel("武汉软件工程职业学院:");
        JLabel myIofo6=new JLabel("—————路人甲");
        myIofo.setFont(new Font("仿宋",Font.BOLD,12));
        myIofo1.setFont(new Font("仿宋",Font.BOLD,12));
        myIofo2.setFont(new Font("仿宋",Font.BOLD,12));
        myIofo3.setFont(new Font("仿宋",Font.BOLD,12));
        myIofo4.setFont(new Font("仿宋",Font.BOLD,12));
        myIofo5.setFont(new Font("仿宋",Font.BOLD,12));
        myIofo6.setFont(new Font("仿宋",Font.BOLD,12));
        myIofo.setBounds(455,330,140,20);
        myIofo1.setBounds(455,350,140,20);
        myIofo2.setBounds(455,370,140,20);
        myIofo3.setBounds(455,390,140,20);
        myIofo4.setBounds(455,410,140,20);
        myIofo5.setBounds(455,430,140,20);
        myIofo6.setBounds(455,450,140,20);
        np.add(myIofo);
        np.add(myIofo1);
        np.add(myIofo2);
        np.add(myIofo3);
        np.add(myIofo4);
        np.add(myIofo5);
        np.add(myIofo6);

        //对close注册监听
        close.addMouseListener(this);
        np.addMouseListener(new MouseAdapter(){
            public void mousePressed(MouseEvent e){
                pre=new Point(e.getX(),e.getY());
            }
        });
        np.addMouseMotionListener(new MouseAdapter(){
            public void mouseDragged(MouseEvent e){
                dra=new Point(getLocation().x+e.getX()-pre.x,getLocation().y+e.getY()-pre.y);
                setLocation(dra);
            }
        });
        np.setBorder(BorderFactory.createLineBorder(Color.black));

        this.add(np);//将面板对象加入到容器里
        this.setUndecorated(true);//设置窗体为未加修饰的



        this.setSize(600,500);
        this.setLocation((Toolkit.getDefaultToolkit().getScreenSize().width-600)/2,(Toolkit.getDefaultToolkit().getScreenSize().height-400)/2);//设置窗体相对位置
        com.sun.awt.AWTUtilities.setWindowOpacity(this,0.99f);//设置透明窗体
        com.sun.awt.AWTUtilities.setWindowShape(this,new RoundRectangle2D.Double(0,0,this.getWidth(),this.getHeight(),6,6));//窗体形状
        this.setVisible(true);//窗体可视
        this.setDefaultCloseOperation(3);//设置窗体关闭方式
    }
    /*重写鼠标事件方法*/
    public void mouseEntered(MouseEvent e){
        if(e.getSource()==min){
            min.setIcon(minIcon2);
        }
        if(e.getSource()==close){
            close.setIcon(icon2);
        }
        if(e.getSource()==head){
            head.setIcon(headIcon1);
        }
        if(e.getSource()==name){
            name.setText("<HTML><U>"+hostName+"</U></HTML>");
        }
        if(e.getSource()==inputip){
            setCursor(new Cursor(Cursor.TEXT_CURSOR));
        }
    }
    public void mouseExited(MouseEvent e){
        if(e.getSource()==min){
            min.setIcon(minIcon1);
        }
        if(e.getSource()==close){
            close.setIcon(icon);
        }
        if(e.getSource()==head){
            head.setIcon(headIcon);
        }
        if(e.getSource()==name){
            name.setText("<HTML>"+hostName+"</HTML>");
        }
        if(e.getSource()==inputip){
            setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
        }
    }
    public void mouseClicked(MouseEvent e){
        if(e.getSource()==min){
            this.setExtendedState(ICONIFIED);
        }
        if(e.getSource()==close){
            System.exit(0);
        }
        if(e.getSource()==inputip){
            inputip.setEnabled(true);
            inputip.setText("");
        }


    }
    public void mousePressed(MouseEvent e){
        if(e.getSource()==min){
            min.setIcon(minIcon3);
        }
        if(e.getSource()==close){
            close.setIcon(icon3);
        }

    }
    public void mouseReleased(MouseEvent e){

        if(e.getSource()==min){
            min.setIcon(minIcon2);
        }
        if(e.getSource()==close){
            close.setIcon(icon2);
        }
    }

    //主方法
    public static void main(String[] args) {
        try{
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        }catch(Exception e){}
        new CopyQQ();



    }
    //定义面板的类
    class newPanel extends JPanel{
        public newPanel(){}
        public void paintComponent(Graphics g){

            ImageIcon image=new ImageIcon("./src/Test/img/1.jpg");
            g.drawImage(image.getImage(),0,0,getSize().width,getSize().height,this);
        }
    }

}

