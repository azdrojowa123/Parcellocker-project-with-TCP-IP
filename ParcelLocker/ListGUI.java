package ParcelLocker;

import java.awt.Color;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTable;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.EmptyBorder;

public class ListGUI extends JFrame {
	
	private JTable table;
	private JPanel contentPane;
	
	public static void main(String[] args) {}
	
	
	public ListGUI(List<ParcelLocker>list) {
		
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(0, 1));
		
		
		
		DefaultTableModel dtm=new DefaultTableModel(0,0);
        String[] header=new String[] {"id","localhost"};
        dtm.setColumnIdentifiers(header);
        
        table =new JTable(dtm);
        table.setBounds(25, 13, 397, 205);
		add(table);

        int count = 0; 		
        while (list.size() > count) {
        	dtm.addRow(new Object[] {list.get(count).getId(),list.get(count).getLocalhost()});
        	count++;
        	System.out.println("HERE");
        }
	            
	             
	          
	           JScrollPane scroll=new JScrollPane(table);
	           int v=ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS;
	           int h=ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS;

	           JScrollPane scrollPane = new JScrollPane(table,v,h);
	           scrollPane.setEnabled(true);
	           scrollPane.setBounds(21, 153, 452, 402);
	          
	         contentPane.add(scrollPane);
		
	}
}
