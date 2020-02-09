package Informator;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.Box;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.List;
import java.awt.Color;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JToggleButton;
import javax.swing.UIManager;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.JTextComponent;
import javax.swing.JList;
import java.awt.SystemColor;

interface MyListener {
	void messageReceived(String theLine);

	void messageReceived(Socket s);
}

class Receiver {
	
	private List<MyListener> ml = new ArrayList<MyListener>();
	private Thread t = null;
	private int port = 0;
	private ServerSocket s = null;

	public void stop() {
			t.interrupt();
			try {
				s.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
	}

	public void start() {
		boolean end = false;
		t = new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					s = new ServerSocket(port);
					while (true) {
							Socket sc = s.accept();
							InputStream is = sc.getInputStream();
							InputStreamReader isr = new InputStreamReader(is);
							BufferedReader br = new BufferedReader(isr);
							String theLine = br.readLine();
							ml.forEach((item) -> item.messageReceived(theLine));
							sc.close();
						}
				} catch(SocketException e){
					
				}
				catch (IOException e) {
					e.printStackTrace();
				}
			}
		});
		t.start();
	}

	public void addMyListener(MyListener m) {
		ml.add(m);
	}

	public void removeMyListener(MyListener m) {
		ml.remove(m);
	}

	Receiver(int port) {
		this.port = port;

	}
	public void add_to_helplist(String s ) {
		IReceiver.help_list.add(s);
	}

}

public class IReceiver extends JPanel implements MyListener {

	private Receiver r = null;
	public static  ArrayList<String> help_list=new ArrayList<>();
	DefaultTableModel dtm=new DefaultTableModel(0,0);
	private JTable table_1=new JTable();
	
	
	

	public IReceiver() {
		setBackground(SystemColor.activeCaptionBorder);

		setLayout(null);

		JLabel llbll = new JLabel("7777");
		llbll.setFont(UIManager.getFont("FileChooser.listFont"));
		llbll.setBounds(578, 39, 62, 22);
		add(llbll);


		JLabel lblPort = new JLabel("port:");
		lblPort.setFont(UIManager.getFont("FileChooser.listFont"));
		lblPort.setBounds(533, 42, 35, 16);
		add(lblPort);
	
			
	
		JToggleButton btnListen = new JToggleButton("Listen");
		btnListen.setFont(UIManager.getFont("FileChooser.listFont"));


		btnListen.addActionListener(new ActionListener()
		{
			@Override
			
			public void actionPerformed(ActionEvent arg0) {
				if (btnListen.isSelected()) {
					r = new Receiver(7777);
					r.addMyListener(IReceiver.this);
					r.start();
					
					
				} else {
					r.stop();
				}
			}
		});
		btnListen.setBounds(538, 71, 79, 25);
		add(btnListen);
		
		
		String header[]=new String[] {"Parcel with ID was sent "};
		dtm.setColumnIdentifiers(header);
		table_1.setModel(dtm);
		table_1.getTableHeader().setFont(UIManager.getFont("FileChooser.listFont"));
		JScrollPane scrollPane= new  JScrollPane(table_1);
		scrollPane.setBounds(10, 13, 493, 265);
		add(scrollPane);
		table_1 = new JTable();
		table_1.setBounds(23, 25, 436, 233);
		add(table_1);
		


	}



	@Override
	public void messageReceived(String s) {
		dtm.addRow(new Object[] {s});
			
	}
	@Override
	public void messageReceived(Socket s) {
		
	}


	
}