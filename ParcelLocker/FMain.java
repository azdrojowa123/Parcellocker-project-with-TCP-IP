package ParcelLocker;



import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import java.awt.Color;

public class FMain extends JFrame {

	private JPanel contentPane=null;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FMain frame = new FMain();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public FMain() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 683, 603);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		PReceiver receiver = new PReceiver();
		receiver.setBorder(new LineBorder(new Color(0, 0, 0)));
		receiver.setBounds(35, 13, 582, 161);
		contentPane.add(receiver);
		
		PSender sender = new PSender((String) null, 0);
		sender.setBorder(new LineBorder(new Color(0, 0, 0)));
		sender.setBounds(35, 184, 582, 339);
		contentPane.add(sender);
	}
}
