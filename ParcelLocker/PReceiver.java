package ParcelLocker;


import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.awt.Color;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JToggleButton;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;



interface MyListener {
	
	void messageReceived(String theLine);
	
}

class Receiver {
	
	protected static Object r;
	private List<MyListener> ml = new ArrayList<MyListener>();
	public   ArrayList<Parcel>parcels=new ArrayList<Parcel>();
	private Thread t = null;
	private int port = 0;
	private ServerSocket s = null;
	private boolean end = false;
	public static ControllPackage pp;


	public void stop() {
			t.interrupt();
			try {
				s.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
	}

	public void start() {
		end = false;
		t = new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					s = new ServerSocket(port);
					while (true) {
						Socket sc = s.accept();
						ObjectInputStream inStream=new ObjectInputStream(sc.getInputStream());
						Parcel pnew=(Parcel)inStream.readObject();
						parcels.add(pnew);
						sc.close();
						}
				} catch(SocketException e){
					
				}
				catch (IOException e) {
					e.printStackTrace();
				} catch (ClassNotFoundException e) {
					
					e.printStackTrace();
				}
			}
		});
		t.start();
	}

	public void addMyListener(MyListener m) {
		ml.add(m);
	}
	public void addparcel(MyListener m,Parcel p) {
		parcels.add(p);
	}

	public void removeMyListener(MyListener m) {
		ml.remove(m);
	}

	Receiver(int port) {
		this.port = port;
		this.pp=new ControllPackage();

	}

}


public class PReceiver extends JPanel implements MyListener {

	private JTextField txtPort;
	public  static  Receiver r = null;
	public Receiver rc=null;
	private JTextField txtMessage;
	private JTextField idprzesylki;
	public static ArrayList<Parcel>elp=new ArrayList<Parcel>();
	

	public PReceiver() {

		setLayout(null);
		
		
		txtPort = new JTextField();
		txtPort.setBounds(42, 13, 62, 22);
		add(txtPort);
		JLabel lblPort = new JLabel("port:");
		lblPort.setBounds(12, 15, 35, 16);
		add(lblPort);
		JLabel lblMessage = new JLabel("Tre\u015B\u0107 przesy\u0142ki: ");
		lblMessage.setBounds(12, 91, 105, 16);
		add(lblMessage);
		txtMessage = new JTextField();
		txtMessage.setBounds(12, 117, 262, 16);
		add(txtMessage);
		JLabel lblNewLabel = new JLabel("ODBIERANIE PRZESY\u0141KI ");
		lblNewLabel.setBounds(12, 45, 158, 13);
		add(lblNewLabel);
		JLabel lblNewLabel_1 = new JLabel("ID przesy\u0142ki kt\u00F3r\u0105 chcesz odebra\u0107: ");
		lblNewLabel_1.setBounds(12, 68, 210, 13);
		add(lblNewLabel_1);
		idprzesylki = new JTextField();
		idprzesylki.setBounds(232, 66, 62, 16);
		add(idprzesylki);
		

		JToggleButton btnListen = new JToggleButton("Listen");

		btnListen.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (btnListen.isSelected()) {
					r = new Receiver(Integer.parseInt(txtPort.getText()));
					r.addMyListener(PReceiver.this);
					r.start();
					new Sender().send(txtPort.getText(),"localhost", 6666);
					try {
						FileWriter fw=new FileWriter("Information.txt",true);
						BufferedWriter out=new BufferedWriter(fw);
						out.write(txtPort.getText());
						out.newLine();
						out.close();
					
						//String ip=txtPort.getText();
						//BufferedWriter out=new BufferedWriter(new FileWriter());
						//out.write(ip);
						//out.close();
					} catch (IOException e) {
						
						e.printStackTrace();
					}
					
				} else {
					r.stop();
				}
			}
		});btnListen.setBounds(114, 11, 79, 25);
		add(btnListen);

		
		JButton odbierzprzesylke = new JButton("Odbierz");
		odbierzprzesylke.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int i=0;
				boolean zmienna=true;
				 do{
					String id=r.parcels.get(i).uniquenumber;
					txtMessage.setText("kk");
					if(id.equals(idprzesylki.getText().trim())) {
						
						txtMessage.setText(r.parcels.get(i).wiadomosc);
						new Sender().send("Package with number "+idprzesylki.getText()+ " was received ", "localhost", 7777);
						zmienna=false;
					}
					i++;
				}while(zmienna==true);
				
			}
		});
		odbierzprzesylke.setBounds(318, 63, 96, 22);
		add(odbierzprzesylke);
		

	}

	@Override
	public void messageReceived(String theLine) {
		txtMessage.setText(theLine);

	}

	
	
}
