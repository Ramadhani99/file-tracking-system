
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

import javax.swing.JPanel;
import javax.swing.border.BevelBorder;
import javax.swing.JSeparator;
//import javax.swing.JEditorPane;
import javax.swing.JLabel;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import Connection.ConnectionManager;
import net.proteanit.sql.DbUtils;

import javax.swing.JTextField;
//import javax.swing.UIManager;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.SystemColor;
import java.awt.Toolkit;

//import javax.swing.JTextArea;
//import javax.swing.DropMode;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JComboBox;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import javax.swing.JMenuItem;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;


public class Test {
	private static Connection conn=ConnectionManager.getInstance().getConnection();
	private JFrame frmFileTrackingSystem;
	private JTextField filename;
	private JTextField barcode;
	private JTable table;
	private JTextField filesearch;
    private JButton delete;
    private JButton update;
    private JPanel fileOperationPanel;
    JComboBox<String> subject;
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
					Admin window = new Admin();
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
	 */
	public Test() throws SQLException {
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
		
		JMenuItem mntmNewMenuItem_2 = new JMenuItem("CLOSE");
		mntmNewMenuItem_2.setIcon(new ImageIcon(Admin.class.getResource("/img/Actions-edit-delete-icon.png")));
		mntmNewMenuItem_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frmFileTrackingSystem.setVisible(false);
				
	            
			}
		});
		mntmNewMenuItem_2.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		mnNewMenu.add(mntmNewMenuItem_2);
		
		JMenuItem mntmNewMenuItem_3 = new JMenuItem("REFRESH");
		mntmNewMenuItem_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				getFrmFileTrackingSystem().revalidate();
				getFrmFileTrackingSystem().repaint();
			}
		});
		mnNewMenu.add(mntmNewMenuItem_3);
		getFrmFileTrackingSystem().setEnabled(true);
		
		JMenu mnNewMenu_1 = new JMenu("MANAGER USER");
		mnNewMenu_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				User usermanager = new User();
				 usermanager.setVisible(true);
	             usermanager.setModal(true);
			}
		});
		mnNewMenu_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				User usermanager = new User();
				 usermanager.setVisible(true);
	             usermanager.setModal(true);
			}
		});
		mnNewMenu_1.setFont(new Font("Times New Roman", Font.BOLD, 16));
		mnNewMenu_1.setIcon(new ImageIcon(Admin.class.getResource("/img/Actions-list-add-user-icon.png")));
		mnNewMenu_1.setForeground(SystemColor.text);
		menuBar.add(mnNewMenu_1);
		
		JMenu mnAddDepartment = new JMenu("OFFICE MENU");
		mnAddDepartment.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				Office Officemanager = new Office();
				 Officemanager.setVisible(true);
				 Officemanager.setModal(true);
			}
		});
		mnAddDepartment.setForeground(new Color(255, 255, 255));
		mnAddDepartment.setFont(new Font("Times New Roman", Font.BOLD, 16));
		mnAddDepartment.setIcon(new ImageIcon(Admin.class.getResource("/img/Professor-icon.png")));
		menuBar.add(mnAddDepartment);
		
		
		
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
		
		JButton btnNewButton = new JButton("INSERT DOCUMENT TO FILE");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				DocumentClass doc;
				try {
					doc = new DocumentClass();
					 doc.setVisible(true);
		             doc.setModal(true);
		            // doc.setLocationRelativeTo(null);
					doc.setAlwaysOnTop(true);
				  //frmFileTrackingSystem
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		btnNewButton.setForeground(new Color(255, 255, 255));
		btnNewButton.setBackground(new Color(51, 204, 204));
		btnNewButton.setIcon(new ImageIcon(Admin.class.getResource("/img/Documents-icon.png")));
		btnNewButton.setFont(new Font("Times New Roman", Font.BOLD, 12));
		toolBar.add(btnNewButton);
		
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
		
		JButton btnNewButton_2 = new JButton("VIEW FILE MOVEMENT");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
			//	ManagerFile receievedfile=new ManagerFile();
				if(ManagerFile.receivedfile()){
					JOptionPane.showMessageDialog(null, "yes");
				}
				if(selectedRowIndex==-1){
					Toolkit.getDefaultToolkit().beep();
					JOptionPane.showMessageDialog(null, "No file slected",null,JOptionPane.INFORMATION_MESSAGE);
				}
				
			}
		});
		btnNewButton_2.setBackground(new Color(51, 204, 204));
		btnNewButton_2.setForeground(new Color(255, 255, 255));
		btnNewButton_2.setIcon(new ImageIcon(Admin.class.getResource("/img/Documents-icon.png")));
		btnNewButton_2.setFont(new Font("Times New Roman", Font.BOLD, 12));
		toolBar.add(btnNewButton_2);
		
		JButton btnForwardFile = new JButton("FORWARD FILE");
		btnForwardFile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ManagerFile check=new ManagerFile();
				if(selectedRowIndex==-1){
					Toolkit.getDefaultToolkit().beep();
					JOptionPane.showMessageDialog(null, "No file slected");
				}
				else if(check.checkFileStatus(filename.getText())){
					JOptionPane.showMessageDialog(null, "The file are not in your office");
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
		
		fileOperationPanel = new JPanel();
		fileOperationPanel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		fileOperationPanel.setBackground(new Color(255, 255, 255));
		fileOperationPanel.setBounds(6, 141, 391, 553);
		getFrmFileTrackingSystem().getContentPane().add(fileOperationPanel);
		fileOperationPanel.setLayout(null);
		
		JLabel lblNewLabel_4 = new JLabel("FILE NAME");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_4.setBounds(19, 86, 78, 28);
		fileOperationPanel.add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("BARCODE");
		lblNewLabel_5.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_5.setBounds(19, 145, 97, 36);
		fileOperationPanel.add(lblNewLabel_5);
		
		filename = new JTextField();
		filename.setBounds(140, 86, 223, 36);
		fileOperationPanel.add(filename);
		filename.setFont(new Font("Tahoma", Font.BOLD, 14));
		filename.setColumns(10);
		
		barcode = new JTextField();
		barcode.setToolTipText("This is the barcode pattern don't delete just enter code number");
		barcode.setBounds(140, 145, 223, 36);
		fileOperationPanel.add(barcode);
		barcode.setFont(new Font("Tahoma", Font.BOLD, 14));
		barcode.setColumns(10);
		
		JPanel panel = new JPanel();
		panel.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		panel.setBounds(4, 368, 387, 57);
		fileOperationPanel.add(panel);
		panel.setLayout(null);
		
		JButton insert = new JButton("CREATE FILE");
		insert.setBounds(10, 12, 141, 41);
		panel.add(insert);
		insert.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String bcode=barcode.getText();
				String fname=filename.getText();
				
				ManagerFile f=new ManagerFile(bcode, fname);
				if (f.insertfile()==true) {
					//barcode.setText(null);
					filename.setText(null);
					
					String sql="select barcode,file_name,comment from file";					
					ResultSet rs=null;
				      
				      try(
				     		// Connection conn=Connector.getconnection(DBtype.MYSQL);
				     		   PreparedStatement stmt=conn.prepareStatement(sql);
				     		 ){
				      	
				      	rs=stmt.executeQuery();
				      	table.setModel(DbUtils.resultSetToTableModel(rs));
				      } catch (SQLException e) {
						// TODO Auto-generated catch block
						System.err.println(e.getMessage());
						
						
					}
				}
				}
		});
		insert.setIcon(new ImageIcon(Admin.class.getResource("/img/Folder-New-icon.png")));
		insert.setFont(new Font("Times New Roman", Font.BOLD, 12));
		update = new JButton("UPDATE");
		update.setBounds(151, 11, 123, 42);
		panel.add(update);
		update.setIcon(new ImageIcon(Admin.class.getResource("/img/update.png")));
		update.setFont(new Font("Times New Roman", Font.BOLD, 12));
		update.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String bcode=barcode.getText();
				String fname=filename.getText();
				
				ManagerFile fileupdate=new ManagerFile(bcode, fname);
				if (fileupdate.updatefile()==true) {
					barcode.setText(null);
					filename.setText(null);
					
					String sql="select barcode,file_name,comment from file";
					
					ResultSet rs=null;
				      
				      try(
				     		// Connection conn=Connector.getconnection(DBtype.MYSQL);
				     		   PreparedStatement stmt=conn.prepareStatement(sql);
				     		 ){
				      	
				      	rs=stmt.executeQuery();
				      	table.setModel(DbUtils.resultSetToTableModel(rs));
				      } catch (SQLException e) {
						// TODO Auto-generated catch block
						System.err.println(e.getMessage());
						
						
					}
				}
				}
		});
		//DELETE BUTTON AND ACTION BEGIN
		
		delete = new JButton("DELETE");
		delete.setBounds(270, 11, 115, 42);
		panel.add(delete);
		//disable update and delete button when there is no in textfield
				if (barcode.getText().equals("")|| filename.getText().equals("")) {
					delete.setEnabled(false);
					update.setEnabled(false);
					
				}

     		delete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				int userpick=JOptionPane.showConfirmDialog(null,"Are you sure you want to delete", "massage box",JOptionPane.YES_NO_OPTION);
				if (userpick==JOptionPane.YES_OPTION) {
					ManagerFile fdelete=new ManagerFile(barcode.getText(), filename.getText());
					if(fdelete.deletefile()==true){
						barcode.setText(null);
						filename.setText(null);
						//comment.setText(null);
						delete.setEnabled(false);
						String sql="select barcode,file_name,comment from file";
						
						ResultSet rs=null;
					      
					      try(
					     		
					     		   PreparedStatement stmt=conn.prepareStatement(sql);
					     		 ){
					      	
					      	rs=stmt.executeQuery();
					      	table.setModel(DbUtils.resultSetToTableModel(rs));
					      } catch (SQLException e) {
							// TODO Auto-generated catch block
							System.err.println(e.getMessage());
							
							
						}
					}
					
				}
				
			}
		});//END OF DELETE OPERATION
		delete.setIcon(new ImageIcon(Admin.class.getResource("/img/edit-delete-icon.png")));
		delete.setFont(new Font("Times New Roman", Font.BOLD, 12));
		
		JLabel lblNewLabel_8 = new JLabel("FILE SUBJECT");
		lblNewLabel_8.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_8.setBounds(19, 42, 97, 26);
		fileOperationPanel.add(lblNewLabel_8);
		
		subject = new JComboBox<>();
		
		subject.setFont(new Font("Tahoma", Font.BOLD, 11));
		subject.setBounds(140, 37, 223, 36);
		fileOperationPanel.add(subject);
		//adding item into combobox
		
		String sql1="select  *from fileSubject";
		
		ResultSet rset=null;
	      
	      try(
	     		
	     		   PreparedStatement stmt=conn.prepareStatement(sql1);
	     		 ){
	      	
	      	rset=stmt.executeQuery();
	      	while (rset.next()) {
	      		subject.setSelectedItem(null);
				subject.addItem(rset.getString("Sname"));
			}
	      	
	      } catch (SQLException e) {
			// TODO Auto-generated catch block
			System.err.println(e.getMessage());
			
			
		}
	      finally{
	    	  rset.close();
	    	  
	    	  
	      }
	      subject.addItemListener(new ItemListener() {
	  			public void itemStateChanged(ItemEvent arg0) {
	  				selectedRowIndex=-1;
	  				ManagerFile pattern =new ManagerFile((String)subject.getSelectedItem());
	  				//pattern.getcodepattern();
	  				//JOptionPane.showMessageDialog(null, "The filesubject " + pattern.getcodepattern() +" have been selected");
	  			barcode.setText(pattern.getcodepattern());
	  			//barcode.setEditable(false);
	  			filename.setText(null);
				//comment.setText(null);
				delete.setEnabled(false);
			    update.setEnabled(false);
			  // forward.setEnabled(false);
	  			}
	  		});
		//end of adding item in combobox
		JScrollPane tablepanel = new JScrollPane();
		
		tablepanel.setBackground(new Color(255, 255, 255));
		tablepanel.setBounds(397, 204, 700, 490);
		getFrmFileTrackingSystem().getContentPane().add(tablepanel);
		//TABLE BEGIN
		table = new JTable();
		table.setFont(new Font("Tahoma", Font.BOLD, 14));
		
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				//get selected row from the table
				delete.setEnabled(true);
				update.setEnabled(true);
				//forward.setEnabled(true);
				DefaultTableModel model=(DefaultTableModel)table.getModel();
				selectedRowIndex=table.getSelectedRow();
				barcode.setText(model.getValueAt(selectedRowIndex, 0).toString());
				filename.setText(model.getValueAt(selectedRowIndex,1).toString());
			 //  comment.setText(model.getValueAt(selectedRowIndex, 2).toString());
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
		table.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent evt) {
			     final int key = evt.getKeyCode();
				if (key == KeyEvent.VK_UP || key == KeyEvent.VK_DOWN
		                || key == KeyEvent.VK_LEFT || key == KeyEvent.VK_RIGHT) {
					DefaultTableModel model=(DefaultTableModel)table.getModel();
					selectedRowIndex=table.getSelectedRow();
					barcode.setText(model.getValueAt(selectedRowIndex, 0).toString());
					filename.setText(model.getValueAt(selectedRowIndex,1).toString());
				 //  comment.setText(model.getValueAt(selectedRowIndex, 2).toString());
				   //set the current file barcode and name for transfer
				   ManagerFile.setFile(model.getValueAt(selectedRowIndex,1).toString());
				   ManagerFile.setFilecode(model.getValueAt(selectedRowIndex,0).toString());
					
				}      
			}
		});
		table.setBackground(new Color(255, 255, 255));
		tablepanel.setViewportView(table);
		//Insert the data from file to the table
		String sql="select barcode,file_name from file";
		
		ResultSet rs=null;
	      
	      try(
	     		
	     		   PreparedStatement stmt=conn.prepareStatement(sql);
	     		 ){
	      	
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
		searchpanel.setBounds(397, 141, 700, 58);
		getFrmFileTrackingSystem().getContentPane().add(searchpanel);
		searchpanel.setLayout(null);
		//FILE SEARCH BEGIN
		filesearch = new JTextField();
		filesearch.setToolTipText("Search by filename or barcode");
		filesearch.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent event) {
				//ManagerFile fsearch =new ManagerFile(filesearch.getText(),filesearch.getText());
				
				//if(fsearch.search()==true){
					//table.setModel(DbUtils.resultSetToTableModel(fsearch.getRs()));
				//}
				String sql="SELECT barcode,file_name FROM file WHERE barcode LIKE ? OR file_name LIKE ?";
				
				ResultSet rs=null;
			      
			      try(
			     		
			     		   PreparedStatement stmt=conn.prepareStatement(sql);
			     		 ){
			    	stmt.setString(1,"%"+filesearch.getText()+"%");
			    	stmt.setString(2,filesearch.getText()+"%");
			    	//stmt.setString(2,filename);
			  		
			  		
			  		rs=stmt.executeQuery();
			  		table.setModel(DbUtils.resultSetToTableModel(rs));
					
			  			
			  		
			      	
			      	
			      } catch (SQLException e) {
					// TODO Auto-generated catch block
					System.err.println(e.getMessage());
					//JOptionPane.showMessageDialog(null, "Error "+e.getMessage());
					
					
				}
			}
		});
		filesearch.setBounds(10, 11, 371, 42);
		searchpanel.add(filesearch);
		filesearch.setFont(new Font("Tahoma", Font.BOLD, 16));
		filesearch.setColumns(10);
		
		JLabel lblNewLabel_7 = new JLabel("   SEARCH FILE");
		lblNewLabel_7.setForeground(new Color(0, 128, 128));
		lblNewLabel_7.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_7.setIcon(new ImageIcon(Admin.class.getResource("/img/Folder-saved-search-icon.png")));
		lblNewLabel_7.setBounds(370, 0, 191, 58);
		searchpanel.add(lblNewLabel_7);
		
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
					int userpick=JOptionPane.showConfirmDialog(null,"DO YOU RECEIVE THIS FILE "+p, 
							"Confirmation massage",JOptionPane.YES_NO_OPTION);
					if (userpick==JOptionPane.YES_OPTION) {	
						//ArrayList<String> file=ManagerFile.getPendingfile();
						
				     ManagerFile movedfile=new ManagerFile();
				    if( movedfile.updatmovedfile(movedfile.getbarcode(p))){
				     movedfile.updatreceivedfile(movedfile.getbarcode(p));
				     movedfile.updatefilestatus(movedfile.getbarcode(p),"active");
				    }
					}

					}
				}
			}
		});
		
	}
	
}
