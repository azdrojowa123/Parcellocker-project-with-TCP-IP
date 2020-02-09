package Central;

import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;

class Sender{
	public void send(String message, String host, int port) throws UnknownHostException{
		Socket s;
		try {
			s = new Socket(host,port);
			OutputStream out = s.getOutputStream();
			PrintWriter pw = new PrintWriter(out, false);
			pw.println(message);
			pw.flush();
			pw.close();
			s.close();
		} catch (IOException e) {
			e.printStackTrace();
		} 
	}
}
public class CSender extends JPanel {
	private JTextField txtMessage;
	private JTextField txtHost;
	private JTextField txtPort;
	private JLabel lblMessage;


	public CSender(String host, int port) {
		setLayout(null);
		
		txtMessage = new JTextField();
		txtMessage.setBounds(12, 77, 216, 22);
		add(txtMessage);
	
		
		JButton btnSend = new JButton("Send");
		btnSend.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
             try {
				new Sender().send(txtMessage.getText(),txtHost.getText(),Integer.parseInt(txtPort.getText()));
				}
             catch(NumberFormatException | UnknownHostException e){
            	 e.printStackTrace();
             }}});
		btnSend.setBounds(268, 76, 78, 25);
		add(btnSend);
		
		txtHost = new JTextField();
		txtHost.setBounds(51, 13, 149, 22);
		add(txtHost);
		
		txtPort = new JTextField();
		txtPort.setBounds(268, 13, 78, 22);
		add(txtPort);
		
		JLabel lblHost = new JLabel("host:");
		lblHost.setBounds(12, 16, 35, 16);
		add(lblHost);
		
		JLabel lblPort = new JLabel("port:");
		lblPort.setBounds(231, 16, 35, 16);
		add(lblPort);
		
		lblMessage = new JLabel("message");
		lblMessage.setBounds(12, 58, 56, 16);
		add(lblMessage);

	}
}