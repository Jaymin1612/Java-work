
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

class client1 extends JFrame implements ActionListener{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JButton b;
	JTextArea ta,ta1;
	JScrollPane scrollPane;
	client1() throws Exception{
	
		setLayout(null);
		ta = new JTextArea(5,20);
		ta1 = new JTextArea(20,30);
		scrollPane = new JScrollPane(ta1);
		ta1.setEditable(false);
		ta.setBounds(30,325,300,100);
		ta1.setBounds(30,30,400,280);
		b = new JButton("Send");
		b.setBounds(350,350,100,50);
		
		add(ta);
		add(ta1);
		add(b);
		
		b.addActionListener(this);
		
	}
	
	public void actionPerformed(ActionEvent ae){
		// TODO Auto-generated method stub
		if(ae.getSource()==b){
			String s = ta.getText();
			ta1.append("You: "+s);
			try{
				DatagramSocket ds = new DatagramSocket(2001);
				byte[] b= s.getBytes();
				DatagramPacket dp = new DatagramPacket(b,0,b.length,InetAddress.getLocalHost(),1000);
				ds.send(dp);
				ds.close();
			}
			catch(Exception e){
				System.out.print(e);
			}
		}
	}
	
	public static void main(String[] arg) throws Exception{
		client1 c = new client1();
		c.setTitle("Chat Between clients");
		c.setSize(500, 500);
		c.setDefaultCloseOperation(EXIT_ON_CLOSE);
		c.setVisible(true);
		
/*		DatagramSocket ds = new DatagramSocket(20000);
		byte[] b =;
		DatagramPacket dp = new DatagramPacket(b,0,1024);
		ds.receive(dp);
		int l = dp.getLength();
		String str = new String(b,0,l);
		System.out.println(str);
		
		ds.close();*/
	}

	

}