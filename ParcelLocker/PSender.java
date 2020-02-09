package ParcelLocker;

import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import Central.CReceiver;
import java.awt.SystemColor;

class Sender{
	public void send(String message, String host, int port){
		Socket s;
		try {
			s = new Socket(host,port);
			OutputStream out = s.getOutputStream();
			PrintWriter pw = new PrintWriter(out, false);
			pw.println(message);
			pw.flush();
			pw.close();
			s.close();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}		
	}
	public void send_package(Parcel p,String host,int port) {
		Socket sp;
		try {
			sp=new Socket(host,port);
			ObjectOutputStream outparcel=new ObjectOutputStream(sp.getOutputStream());
			outparcel.writeObject(p);
		}catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}		
	}
}
public class PSender extends JPanel {
	private JTextField txtMessage;
	private JTextField txtHost;
	private JTextField txtPort;
	private JLabel lblMessage;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JLabel lblPortNadawczy;
	private JTextField textField_5;
	private JLabel lblUnikalnyNumer;
	private JTextField id;


	public PSender(String host, int port) {
		setBackground(SystemColor.activeCaption);
		setLayout(null);
		
		txtMessage = new JTextField();
		txtMessage.setBounds(198, 168, 207, 16);
		add(txtMessage);
		
		
		JButton btnSend = new JButton("Send");
		btnSend.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
             try {
            	BufferedReader br=new BufferedReader(new FileReader("Information.txt"));
         		String line=br.readLine();
         		String f=null;
         		while(line!=null) {
         			if(line.equals(txtPort.getText())) {
         				f=line;
         				line=null;
         				break;
         			}
         			line=br.readLine();
         		}
         		if(f!=null) {
         			Parcel pp=new Parcel(textField.getText(),textField_2.getText(),textField_3.getText(),txtMessage.getText(),txtPort.getText(), textField_5.getText(),id.getText());
         			new Sender().send_package(pp,txtHost.getText(),Integer.parseInt(txtPort.getText()));
         			new Sender().send("Package was sent to Parcellocker with number "+txtPort.getText(), "localhost", 7777);
         		}
         		else {
         			
         			new Sender().send("Parcel cannot be sent to ParcelLocker with number "+txtPort.getText(), "localhost", 7777);
         		}
         		br.close();
            	
				}
             catch(NumberFormatException | FileNotFoundException e){
            	 e.printStackTrace();
             } catch (IOException e) {
				e.printStackTrace();
			}}});
		btnSend.setBounds(294, 244, 111, 25);
		add(btnSend);
		
		txtHost = new JTextField();
		txtHost.setBounds(51, 13, 78, 22);
		add(txtHost);
	
		
		txtPort = new JTextField();
		txtPort.setBounds(190, 13, 68, 22);
		add(txtPort);
		
		
		JLabel lblHost = new JLabel("host:");
		lblHost.setBounds(12, 16, 35, 16);
		add(lblHost);
		
		JLabel lblPort = new JLabel("port:");
		lblPort.setBounds(145, 15, 35, 16);
		add(lblPort);
		
		lblMessage = new JLabel("message");
		lblMessage.setBounds(13, 164, 77, 16);
		add(lblMessage);
		
		JLabel Imie = new JLabel("Imie nadawcy");
		Imie.setBounds(12, 45, 96, 13);
		add(Imie);
		
		textField = new JTextField();
		textField.setBounds(198, 45, 207, 16);
		add(textField);
		textField.setColumns(10);
		
		JLabel Nazwisko = new JLabel("Nazwisko nadawcy'");
		Nazwisko.setBounds(12, 74, 150, 13);
		add(Nazwisko);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(198, 71, 207, 19);
		add(textField_1);
		
		JLabel lblNewLabel = new JLabel("Numer telefonu nadawcy");
		lblNewLabel.setBounds(12, 97, 168, 13);
		add(lblNewLabel);
		
		JLabel lblImieOdbiorcy = new JLabel("Imie odbiorcy");
		lblImieOdbiorcy.setBounds(12, 120, 117, 13);
		add(lblImieOdbiorcy);
		
		JLabel lblNazwiskoOdbiorcy = new JLabel("Nazwisko odbiorcy");
		lblNazwiskoOdbiorcy.setBounds(12, 140, 150, 13);
		add(lblNazwiskoOdbiorcy);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(198, 94, 207, 19);
		add(textField_2);
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(198, 117, 207, 19);
		add(textField_3);
		
		textField_4 = new JTextField();
		textField_4.setColumns(10);
		textField_4.setBounds(198, 142, 207, 16);
		add(textField_4);
		
		lblPortNadawczy = new JLabel("port nadawczy: ");
		lblPortNadawczy.setBounds(268, 15, 92, 16);
		add(lblPortNadawczy);
		
		textField_5 = new JTextField();
		textField_5.setBounds(359, 13, 81, 22);
		add(textField_5);
		
		lblUnikalnyNumer = new JLabel("Unikalny numer: ");
		lblUnikalnyNumer.setBounds(12, 196, 150, 16);
		add(lblUnikalnyNumer);
		
		id = new JTextField();
		id.setBounds(198, 194, 207, 22);
		add(id);

	}
}
