import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class chatNew extends JFrame{

	private static final long serialVersionUID = 1L;
	
	JLabel l1,l2;
	JTextField tf;
	JPasswordField pf;
	Container c;
	JButton b1,b2,b3;
	JSplitPane spH,spV;
	JPanel panel1,panel2,panel3;
	CardLayout card;
	
	public chatNew() {
		
		c = getContentPane();
		card = new CardLayout(40,30);
		c.setLayout(card);
		
		l1 = new JLabel("Username");
		l1.setBounds(50, 50, 70, 30);
		tf = new JTextField(20);
		tf.setBounds(130, 50, 200, 30);
		l2 = new JLabel("Password");
		l2.setBounds(50, 100, 70, 30);
		pf = new JPasswordField(20);
		pf.setBounds(130, 100, 100, 30);
		b1 = new JButton("Submit");
		b1.setBounds(95, 150, 100, 30);
		
		final String s1 = String.valueOf(pf.getPassword());
		final String s2 = tf.getText()+"123";
		
		createPanel1();
		createPanel2();
		createPanel3();
		
		
		
		JPanel card1 = new JPanel();
		card1.add(l1);
		card1.add(tf);
		card1.add(l2);
		card1.add(pf);
		card1.add(b1);
		
		final JPanel card2 = new JPanel();
		spV = new JSplitPane( JSplitPane.VERTICAL_SPLIT );
		card2.add( spV, BorderLayout.CENTER );

		spH = new JSplitPane( JSplitPane.HORIZONTAL_SPLIT );
		spH.setLeftComponent( panel1 );
		spH.setRightComponent( panel2 );

		spV.setLeftComponent( spH );
		spV.setRightComponent( panel3 );

		
		c.add(card1);
		c.add(card2);
		b1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				if(s1.equals(s2)){
					card.next(card2);
				}
			}
		});
	}
	
	public void createPanel1()
	{
		panel1 = new JPanel();
		panel1.setLayout( new BorderLayout() );

		// Add some buttons
		panel1.add( new JButton( "North" ), BorderLayout.NORTH );
		panel1.add( new JButton( "South" ), BorderLayout.SOUTH );
		panel1.add( new JButton( "East" ), BorderLayout.EAST );
		panel1.add( new JButton( "West" ), BorderLayout.WEST );
		panel1.add( new JButton( "Center" ), BorderLayout.CENTER );

	}

	public void createPanel2()
	{
		panel2 = new JPanel();
		panel2.setLayout( new FlowLayout() );
		JTextArea ta = new JTextArea(20, 20);
		ta.setEditable(false);
		panel2.add(ta);
	}

	public void createPanel3()
	{
		panel3 = new JPanel();
		panel3.setLayout( new BorderLayout() );
        panel3.setPreferredSize( new Dimension( 400, 100 ) );
        panel3.setMinimumSize( new Dimension( 100, 50 ) );
        
		panel3.add( new JLabel( "Notes:" ), BorderLayout.NORTH );
		panel3.add( new JTextArea(), BorderLayout.CENTER );
	}

	public static void main(String[] args) throws Exception{
		// TODO Auto-generated method stub
		chatNew cn = new chatNew();
		cn.setTitle("Chat");
		cn.setSize(800, 500);
		cn.setDefaultCloseOperation(EXIT_ON_CLOSE);
		cn.setVisible(true);
	}

}
