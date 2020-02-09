package Central;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.Box;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.List;
import java.awt.Color;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JToggleButton;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.JTextComponent;
import javax.swing.JList;
import javax.swing.UIManager;
import java.awt.SystemColor;
import javax.swing.JTable;

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
		CReceiver.help_list.add(s);
	}

}

public class CReceiver extends JPanel implements MyListener {

	private Receiver r = null;
	public static  ArrayList<String> help_list=new ArrayList<>();
	public JTable table=new JTable();
	DefaultTableModel dtm=new DefaultTableModel(0,0);
	
	
	

	public CReceiver() {
	
		this.setBackground(SystemColor.activeCaptionBorder);
		this.setForeground(Color.WHITE);

		setLayout(null);

		JLabel llbll = new JLabel("6666");
		llbll.setFont(UIManager.getFont("FileChooser.listFont"));
		llbll.setBounds(646, 39, 62, 22);
		add(llbll);


		JLabel lblPort = new JLabel("Listenning on port:");
		lblPort.setFont(UIManager.getFont("FileChooser.listFont"));
		lblPort.setBounds(530, 42, 106, 16);
		add(lblPort);
	
			
	
		JToggleButton btnListen = new JToggleButton("Listen");
		btnListen.setFont(UIManager.getFont("FileChooser.listFont"));
		btnListen.setForeground(new Color(0, 0, 0));
		btnListen.setBackground(new Color(65, 105, 225));

		btnListen.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (btnListen.isSelected()) {
					r = new Receiver(6666);
					r.addMyListener(CReceiver.this);
					r.start();
					
					
				} else {
					r.stop();
				}
			}
		});
		btnListen.setBounds(629, 82, 79, 25);
		add(btnListen);
		
		String header[]=new String[] {"Registered ParcelLockers with ID"};
		dtm.setColumnIdentifiers(header);
		table.setModel(dtm);
	    table.getTableHeader().setFont(UIManager.getFont("FileChooser.listFont"));
		JScrollPane scrollPane= new  JScrollPane(table);
		scrollPane.setBounds(10, 13, 493, 265);
		add(scrollPane);
		


	}



	@Override
	public void messageReceived(String s) {
			dtm.addRow(new Object[] {s});
	}



	@Override
	public void messageReceived(Socket s) {
		
	}

	
}
