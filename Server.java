 
package chattingapplication;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.*;
import java.util.*;
import java.text.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

 
public class Server implements ActionListener{
     JTextField text;
     static JPanel a1;
     static Box vertical= Box.createVerticalBox();
     static JFrame f = new JFrame();
     static DataOutputStream dout;
     
  
     
      String name ="Ramson Mapela";
    
     Server(){
        f.setLayout(null);
        
        JPanel p1=new JPanel();
        p1.setBackground(new Color(7,94,84));
        p1.setBounds(0,0,500,70);
        p1.setLayout(null);
        f.add(p1);
        
        ImageIcon i1=new ImageIcon(ClassLoader.getSystemResource("Icons/arrow.png"));
        Image i2=i1.getImage().getScaledInstance(25, 25, Image.SCALE_DEFAULT);
        ImageIcon i3=new ImageIcon(i2);
        JLabel back=new JLabel(i3);
        back.setBounds(15,20,25,25);
        p1.add(back);
        
        back.addMouseListener(new MouseAdapter(){
            public void mouseClicked(MouseEvent ae){
                System.exit(0);
            }
        });
        
        
        
        ImageIcon i4=new ImageIcon(ClassLoader.getSystemResource("Icons/imageIcon.png"));
        Image i5=i4.getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT);
        ImageIcon i6=new ImageIcon(i5);
        JLabel profile=new JLabel(i6);
        profile.setBounds(70,10,40,40);
        p1.add(profile);
        
        ImageIcon i7=new ImageIcon(ClassLoader.getSystemResource("Icons/videoCall.png"));
        Image i8=i7.getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT);
        ImageIcon i9=new ImageIcon(i8);
        JLabel video=new JLabel(i9);
        video.setBounds(390,10,30,30);
        p1.add(video);
        
        ImageIcon i10=new ImageIcon(ClassLoader.getSystemResource("Icons/video.png"));
        Image i11=i10.getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT);
        ImageIcon i12=new ImageIcon(i11);
        JLabel videoCal=new JLabel(i12);
        videoCal.setBounds(330,10,30,30);
        p1.add(videoCal);
        
        ImageIcon i13=new ImageIcon(ClassLoader.getSystemResource("Icons/three-dots.png"));
        Image i14=i13.getImage().getScaledInstance(10, 40, Image.SCALE_DEFAULT);
        ImageIcon i15=new ImageIcon(i14);
        JLabel dot3=new JLabel(i15);
        dot3.setBounds(450,10,10,40);
        p1.add(dot3);
        
        JLabel name=new JLabel("Mapela");
        name.setBounds(130,10,100,18);
        name.setForeground(Color.WHITE);
        name.setFont(new Font("SAN SERIF",Font.BOLD,14));
        p1.add(name);
        
        JLabel status=new JLabel("Active Now");
        status.setBounds(130,30,140,18);
        status.setForeground(Color.WHITE);
        status.setFont(new Font("SAN SERIF",Font.BOLD,12));
        p1.add(status);
        
        a1=new JPanel();
        a1.setBounds(5,75,500,540);
        f.add(a1);
        
        text=new JTextField();
        text.setBounds(5,620,365,40);
        text.setFont(new Font("SAN SERIF",Font.PLAIN,18));
        f.add(text);
        
        JButton send=new JButton("Send");
        send.setBounds(380,620,100,40);
        send.setFont(new Font("SAN SERIF",Font.BOLD,20));
        send.addActionListener(this);
        send.setBackground(new Color(7,94,84));
        send.setForeground(Color.WHITE);
        f.add(send);
        
        f.setVisible(true);
        f.setResizable(false);
        f.setLocation(10, 10);
        f.getContentPane().setBackground(Color.WHITE);
        f.setSize(500,710);
        
        
        }
    
    public static JPanel formartLabel(String out){
        JPanel panel=new JPanel();
        panel.setLayout(new BoxLayout(panel,BoxLayout.Y_AXIS));
        
       JLabel output=new JLabel("<html><p style=\"width: 150px\">"+out +"</p></html>");
       output.setFont(new Font("Tahoma",Font.PLAIN,16));
       output.setBackground(new Color(37,211,102));
       output.setOpaque(true);
       output.setBorder(new EmptyBorder(15,15,15,50));
       
       panel.add(output);
       
       Calendar cal=Calendar.getInstance();
       SimpleDateFormat sdf=new SimpleDateFormat("HH:mm");
       
       JLabel time =new JLabel();
       time.setText(sdf.format(cal.getTime()));
       panel.add(time);
       
       return panel;
       
    }
    
    
    public void actionPerformed(ActionEvent ae) {
        try{
            String out =text.getText();
            
            JPanel p2= formartLabel(out);
            a1.setLayout(new BorderLayout());
            
            JPanel right=new JPanel(new BorderLayout());
            right.add(p2,BorderLayout.LINE_END);
            vertical.add(right);
            vertical.add(Box.createVerticalStrut(15));
            a1.add(vertical,BorderLayout.PAGE_START);
            
            dout.writeUTF(out);
            
            text.setText("");
            
            f.repaint();
            f.invalidate();
            f.validate();
        }catch(Exception e){
            e.printStackTrace();
        }
        
    }
    
     
    public static void main(String []args){
        new Server();
         
        try{
          ServerSocket skt= new ServerSocket(6001);
          
          while(true){
              Socket s= skt.accept();
              DataInputStream din=new DataInputStream(s.getInputStream());
              dout=new DataOutputStream(s.getOutputStream());
              
              while(true){
                  String msg =din.readUTF();
                  JPanel panel =formartLabel(msg);
                  
                  JPanel left=new JPanel(new BorderLayout());
                  left.add(panel,BorderLayout.LINE_START);
                  vertical.add(left); 
                  f.validate();
            }
              }
          
        }catch(Exception e){
            e.printStackTrace();
        }
    } 

   
}
