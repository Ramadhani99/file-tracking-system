import java.awt.EventQueue;

import javax.swing.JFrame;
//import javax.swing.JPopupMenu;
//import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
//import java.util.ArrayList;

import javax.swing.JToolBar;
import javax.swing.SwingUtilities;

import java.awt.Color;
import javax.swing.JMenu;
//import java.awt.FlowLayout;
import javax.swing.JMenuBar;
//import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.Font;
//import javax.swing.SwingConstants;
//import javax.swing.JSplitPane;
//import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;
import javax.swing.JSeparator;
//import javax.swing.JEditorPane;
import javax.swing.JLabel;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import Connection.ConnectionManager;

import net.proteanit.sql.DbUtils;

//import javax.swing.JTextField;
//import javax.swing.UIManager;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.JScrollPane;
//import java.awt.SystemColor;
import java.awt.Toolkit;

//import javax.swing.JTextArea;
//import javax.swing.DropMode;
//import java.awt.event.KeyAdapter;
//import java.awt.event.KeyEvent;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;


public class Staff {
	private static Connection conn=ConnectionManager.getInstance().getConnection();
	private JFrame frmFileTrackingSystem;
	public static JTable table;
    private JLabel lbluser;
    private int selectedRowIndex=-1;
   
	private JLabel lbloffice;
    private JLabel lblRole;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		//ManagerFile.printarrayelement();
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Staff window = new Staff();
					window.getFrmFileTrackingSystem().setVisible(true);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	public JTable getTable(){
		return table;
	}

	/**
	 * Create the application.
	 * @throws SQLException 
	 * @wbp.parser.entryPoint
	 */
	public Staff() throws SQLException {
		initialize();
		
	}

	
	private  void initialize() throws SQLException  {
		
		setFrmFileTrackingSystem(new JFrame());
		getFrmFileTrackingSystem().getContentPane().setBackground(new Color(255, 255, 255));
		getFrmFileTrackingSystem().setTitle("FILE TRACKING SYSTEM");
		getFrmFileTrackingSystem().setBounds(100, 100, 1129, 711);
		getFrmFileTrackingSystem().setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getFrmFileTrackingSystem().setExtendedState(JFrame.MAXIMIZED_BOTH);
		getFrmFileTrackingSystem().getContentPane().setLayout(null);
	
		
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setForeground(new Color(255, 255, 255));
		menuBar.setBackground(new Color(0, 0, 51));
		menuBar.setBounds(6, 0, 1348, 58);
		getFrmFileTrackingSystem().getContentPane().add(menuBar);
		
		JMenu mnNewMenu = new JMenu("FILE MENU");
		mnNewMenu.setForeground(new Color(255, 255, 255));
		mnNewMenu.setBackground(new Color(204, 204, 255));
		mnNewMenu.setFont(new Font("Times New Roman", Font.BOLD, 16));
		mnNewMenu.setIcon(new ImageIcon(Admin.class.getResource("/img/document-archive-icon.png")));
		menuBar.add(mnNewMenu);
		getFrmFileTrackingSystem().setEnabled(true);
		
		
		
		JPanel headpanel = new JPanel();
		headpanel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		headpanel.setBackground(new Color(255, 255, 255));
		headpanel.setBounds(3, 59, 1351, 71);
		getFrmFileTrackingSystem().getContentPane().add(headpanel);
		headpanel.setLayout(null);
		
		JToolBar toolBar = new JToolBar();
		toolBar.setBounds(10, 11, 1348, 60);
		headpanel.add(toolBar);
		toolBar.setBackground(new Color(255, 255, 255));
		
		JButton btnNewButton_1 = new JButton("VIEW FILE LOCATION");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(selectedRowIndex==-1){
					Toolkit.getDefaultToolkit().beep();
					JOptionPane.showMessageDialog(null, "No file slected");
				}
				else{
					ManagerFile fileloc=new ManagerFile();
					if(fileloc.filelocation()){
						FileLocation view =new FileLocation();
						view.setVisible(true);
					}	
				}
			}
		});
		btnNewButton_1.setForeground(new Color(255, 255, 255));
		btnNewButton_1.setBackground(new Color(51, 204, 204));
		btnNewButton_1.setIcon(new ImageIcon(Admin.class.getResource("/img/Folders-icon.png")));
		btnNewButton_1.setFont(new Font("Times New Roman", Font.BOLD, 12));
		toolBar.add(btnNewButton_1);
		
		JButton btnForwardFile = new JButton("FORWARD FILE");
		btnForwardFile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//ManagerFile check=new ManagerFile();
				if(selectedRowIndex==-1){
					Toolkit.getDefaultToolkit().beep();
					JOptionPane.showMessageDialog(null, "No file slected");
				}
				else if(ManagerFile.confirmfile(ManagerFile.getFilecode(), ManagerFile.getCurrentoffice())){
					JOptionPane.showMessageDialog(null, "Your have already send this file");
			}
				else{
					
				Forwardfile file=new Forwardfile();
				file.setVisible(true);
				}
			}
		});
		btnForwardFile.setIcon(new ImageIcon(Admin.class.getResource("/img/Save-icon.png")));
		btnForwardFile.setForeground(Color.WHITE);
		btnForwardFile.setFont(new Font("Times New Roman", Font.BOLD, 12));
		btnForwardFile.setBackground(new Color(51, 204, 204));
		toolBar.add(btnForwardFile);
		//disable update and delete button when there is no in textfield
				
		//end of adding item in combobox
		JScrollPane tablepanel = new JScrollPane();
		
		tablepanel.setBackground(new Color(255, 255, 255));
		tablepanel.setBounds(35, 204, 1062, 490);
		getFrmFileTrackingSystem().getContentPane().add(tablepanel);
		//TABLE BEGIN
		table = new JTable();
		table.setFont(new Font("Tahoma", Font.BOLD, 14));
		
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				//get selected row from the table
				
				DefaultTableModel model=(DefaultTableModel)table.getModel();
				selectedRowIndex=table.getSelectedRow();
				//barcode.setText(model.getValueAt(selectedRowIndex, 0).toString());
				//filename.setText(model.getValueAt(selectedRowIndex,1).toString());
			   //comment.setText(model.getValueAt(selectedRowIndex, 2).toString());
			   //set the current file barcode and name for transfer
			   ManagerFile.setFile(model.getValueAt(selectedRowIndex,1).toString());
			   ManagerFile.setFilecode(model.getValueAt(selectedRowIndex,0).toString());
			}
			
				@Override
				public void mouseReleased(MouseEvent arg0) {
					 if(SwingUtilities.isRightMouseButton(arg0)){
					        //my code
						
					 }
					
					//jpopmenu.addPopup();
					 }
			
		});
		table.setBackground(new Color(255, 255, 255));
		tablepanel.setViewportView(table);
		//Insert the data from file to the table
		String sql="select *from fileoperation where file_to=?";
		
		ResultSet rs=null;
	      
	      try(
	     		
	     		   PreparedStatement stmt=conn.prepareStatement(sql);
	     		 ){
	      	stmt.setString(1, StaffManager.getOffice());
	      	rs=stmt.executeQuery();
	      	table.setModel(DbUtils.resultSetToTableModel(rs));
	      } catch (SQLException e) {
			// TODO Auto-generated catch block
			System.err.println(e.getMessage());
			
			
		} 
		//inserting complete
	      //END OF TABLE
		JPanel userpanel = new JPanel();
		userpanel.setBackground(new Color(255, 255, 255));
		userpanel.setBorder(new TitledBorder(new BevelBorder(BevelBorder.LOWERED, new Color(102, 102, 0), null, null, null), "USER", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(153, 51, 0)));
		userpanel.setBounds(1132, 141, 212, 553);
		getFrmFileTrackingSystem().getContentPane().add(userpanel);
		userpanel.setLayout(null);
		//create object to set label value
		
		JLabel lblNewLabel = new JLabel("USERNAME");
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 12));
		lblNewLabel.setBounds(23, 30, 106, 19);
		userpanel.add(lblNewLabel);
		//System.out.println(StaffManager.getSname());
	    lbluser = new JLabel(StaffManager.getSname());
		lbluser.setBounds(101, 30, 101, 14);
		userpanel.add(lbluser);
		
		JLabel lblNewLabel_2 = new JLabel("OFFICE");
		lblNewLabel_2.setFont(new Font("Times New Roman", Font.BOLD, 12));
		lblNewLabel_2.setBounds(23, 68, 106, 19);
		userpanel.add(lblNewLabel_2);
		
		lbloffice = new JLabel(StaffManager.getOffice());
		lbloffice.setBounds(101, 73, 101, 14);
		userpanel.add(lbloffice);
		
		lblRole = new JLabel("ROLE");
		lblRole.setFont(new Font("Times New Roman", Font.BOLD, 12));
		lblRole.setBounds(23, 120, 106, 19);
		userpanel.add(lblRole);
		
		JLabel lblrole = new JLabel(StaffManager.getStype());
		lblrole.setBounds(101, 125, 101, 14);
		userpanel.add(lblrole);
		
		JButton btnNewButton_6 = new JButton("LOGOUT");
		btnNewButton_6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frmFileTrackingSystem.setVisible(false);
				loginForm logout;
				try {
					logout = new loginForm();
					logout.frame.setVisible(true);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		btnNewButton_6.setForeground(new Color(240, 255, 240));
		btnNewButton_6.setBackground(new Color(255, 0, 0));
		btnNewButton_6.setIcon(new ImageIcon(Admin.class.getResource("/img/Logout-icon.png")));
		btnNewButton_6.setFont(new Font("Adobe Garamond Pro Bold", Font.BOLD, 25));
		btnNewButton_6.setBounds(10, 221, 192, 36);
		userpanel.add(btnNewButton_6);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(10, 198, 295, 62);
		userpanel.add(separator);
		
		
		
		JPanel searchpanel = new JPanel();
		searchpanel.setBackground(new Color(248, 248, 255));
		searchpanel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		searchpanel.setBounds(35, 141, 1062, 58);
		getFrmFileTrackingSystem().getContentPane().add(searchpanel);
		searchpanel.setLayout(null);
		
		//conn.close();
	}
	public JFrame getFrmFileTrackingSystem() {
		return frmFileTrackingSystem;
	}
	public void setFrmFileTrackingSystem(JFrame frmFileTrackingSystem) {
		this.frmFileTrackingSystem = frmFileTrackingSystem;
		frmFileTrackingSystem.addWindowListener(new WindowAdapter() {
			
			@Override
			public void windowOpened(WindowEvent arg0) {
				//ManagerFile receievedfile=new ManagerFile();
             if(ManagerFile.receivedfile()){
					for(String p:ManagerFile.pendingfile){
					//	JOptionPane.showMessageDialog(null, p);
					int userpick=JOptionPane.showConfirmDialog(null,"DO YOU RECEIVE THIS FILE  "
				+ p, 
							"Confirmation massage",JOptionPane.YES_NO_OPTION);
					
					if (userpick==JOptionPane.YES_OPTION) {	
						//ArrayList<String> file=ManagerFile.getPendingfile();
					
				     ManagerFile movedfile=new ManagerFile();
				   
				    if( movedfile.updatmovedfile(movedfile.getbarcode(p))){
				    	  movedfile.updatreceivedfile(movedfile.getbarcode(p));
				     
				     String sql="select *from fileoperation where file_to=?";						
						ResultSet rs=null;
					      
					      try(
					     		
					     		   PreparedStatement stmt=conn.prepareStatement(sql);
					     		 ){
					    	  stmt.setString(1, StaffManager.getOffice());
					      	rs=stmt.executeQuery();
					      	table.setModel(DbUtils.resultSetToTableModel(rs));
					      } catch (SQLException e) {
							// TODO Auto-generated catch block
							System.err.println(e.getMessage());
							
							
						} 
				    }
					}
					}
					
				}
			}
		});
	}
	
}
