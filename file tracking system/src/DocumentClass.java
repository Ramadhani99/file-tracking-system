import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;


import java.awt.Font;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JTextField;


import Connection.ConnectionManager;

import javax.swing.border.BevelBorder;
import java.awt.Color;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class DocumentClass extends JDialog {

	/**
	 * 
	 */
	private static Connection conn=ConnectionManager.getInstance().getConnection();
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField nameinput;
	private JTextField ownerinput;
    private JComboBox<String> filesubject; 
    private JComboBox<String> sfile;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			DocumentClass dialog = new DocumentClass();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 * @throws SQLException 
	 */
	public DocumentClass() throws SQLException {
		setBounds(100, 100, 626, 598);
		setUndecorated(true);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		panel.setBounds(10, 11, 610, 285);
		contentPanel.add(panel);
		panel.setLayout(null);
		
		JLabel DName = new JLabel("DOCUMENT NAME");
		DName.setBounds(6, 16, 211, 42);
		panel.add(DName);
		DName.setFont(new Font("Arial Black", Font.BOLD, 18));
		
		JLabel Downer = new JLabel("DOCUMENT OWNER");
		Downer.setBounds(6, 80, 211, 42);
		panel.add(Downer);
		Downer.setFont(new Font("Arial Black", Font.BOLD, 18));
		
		JLabel fileinput = new JLabel("STORAGE FILE");
		fileinput.setBounds(6, 216, 211, 42);
		panel.add(fileinput);
		fileinput.setFont(new Font("Arial Black", Font.BOLD, 18));
		
		nameinput = new JTextField();
		nameinput.setFont(new Font("Castellar", Font.PLAIN, 15));
		nameinput.setBounds(282, 19, 299, 42);
		panel.add(nameinput);
		nameinput.setColumns(10);
		
		ownerinput = new JTextField();
		ownerinput.setFont(new Font("Castellar", Font.PLAIN, 15));
		ownerinput.setBounds(282, 72, 299, 42);
		panel.add(ownerinput);
		ownerinput.setColumns(10);
		
		JLabel lblFileSubject = new JLabel("FILE SUBJECT");
		lblFileSubject.setFont(new Font("Arial Black", Font.BOLD, 18));
		lblFileSubject.setBounds(6, 148, 211, 42);
		panel.add(lblFileSubject);
		
		filesubject = new JComboBox<>();
		filesubject.setBounds(282, 148, 299, 42);
		panel.add(filesubject);
		//add item to combobox
				
				String sql1="select  *from fileSubject";
				
				ResultSet rset=null;
			      
			      try(
			     		
			     		   PreparedStatement stmt=conn.prepareStatement(sql1);
			     		 ){
			      	
			      	rset=stmt.executeQuery();
			      	while (rset.next()) {
			      		filesubject.setSelectedItem(null);
						filesubject.addItem(rset.getString("Sname"));
					}
			      	
			      } catch (SQLException e) {
					// TODO Auto-generated catch block
					System.err.println(e.getMessage());
					
					
				}
			      finally{
			    	  rset.close();
			    	  
			    	  
			      }
				
				//End of comboBox
		sfile = new JComboBox<>();
		sfile.setBounds(282, 216, 299, 42);
		panel.add(sfile);
		//adding item
		filesubject.addItemListener(new ItemListener() {
  			public void itemStateChanged(ItemEvent arg0) {
  				ManagerFile pattern =new ManagerFile((String)filesubject.getSelectedItem());
  				pattern.getcodepattern();
  				sfile.removeAllItems();


                String sql2="select  *from file where subject=?";
				
				ResultSet rset1=null;
			      
			      try(
			     		
			     		   
			    		  PreparedStatement stmt1=conn.prepareStatement(sql2);
			     		 ){
			    	  stmt1.setInt(1,pattern.getSid());
			    	  rset1=stmt1.executeQuery();
			      	while (rset1.next()) {
			      		sfile.addItem(rset1.getString("file_name"));
			      		
			      		
					}
			      	
			      } catch (SQLException e) {
					// TODO Auto-generated catch block
					System.err.println(e.getMessage());
					
					
				}
			      finally{
			    	  try {
						rset1.close();
						//rset1.close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			    	  
			    	  
			      }
  			
  			}
  		});
		//end
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(DocumentClass.class.getResource("/img/ft.jpg")));
		lblNewLabel.setBounds(31, 218, 569, 308);
		contentPanel.add(lblNewLabel);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("SAVE");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						
						//JOptionPane.showMessageDialog(null,(String)sfile.getSelectedItem() );	
						
							try {
								ManagerDocument newdoc=new ManagerDocument
										((String)sfile.getSelectedItem(),nameinput.getText(),ownerinput.getText());
								newdoc.barcodeget();
								newdoc.InsertDocumenttoFile();
							} catch (SQLException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						
						
					}
				});
				okButton.setBackground(new Color(0, 128, 0));
				okButton.setForeground(new Color(255, 255, 255));
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Close");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						
						
				
							
							
							 dispose();
							 //new Admin().frmFileTrackingSystem.setEnabled(true);
						
						
					}
				});
				cancelButton.setBackground(new Color(255, 0, 0));
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
}
