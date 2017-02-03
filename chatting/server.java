import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;


public class server {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		DatagramSocket ds = new DatagramSocket(1000);
		byte[] b1 = new byte[1024];
		DatagramPacket dp = new DatagramPacket(b1,0,1024);
		ds.receive(dp);
		int l = dp.getLength();
		String str = new String(b1,0,l);
		byte[] b2 = str.getBytes();
		DatagramPacket dp2 = new DatagramPacket(b2,0,b2.length,InetAddress.getLocalHost(),1000);
		ds.send(dp2);
		
		ds.close();
	}

}
