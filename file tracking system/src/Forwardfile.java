import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Connection.ConnectionManager;
//import net.proteanit.sql.DbUtils;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.sql.Connection;
//import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JComboBox;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;

public class Forwardfile extends JDialog {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JComboBox<String> destination;
	private final JPanel contentPanel = new JPanel();
	private static Connection conn=ConnectionManager.getInstance().getConnection();
	//private int count=0;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			Forwardfile dialog = new Forwardfile();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public Forwardfile() {
		getContentPane().setBackground(new Color(240, 255, 240));
		setBackground(new Color(255, 255, 240));
		setBounds(100, 100, 848, 376);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("File to:");
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lblNewLabel.setBounds(384, 46, 107, 50);
		contentPanel.add(lblNewLabel);
		
		 destination = new JComboBox<>();
		 destination.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		 //set office to the destination
		 String sql="select * from office";
			try(
			Statement stmt=conn.createStatement();
			ResultSet rs=stmt.executeQuery(sql);
				)
			{
			
			while(rs.next())
				{
				   destination.setSelectedItem(null);
				   destination .addItem(rs.getString("officeName"));
				}
			}
			catch
			(SQLException e)
			{
				System.err.println(e.getMessage());
			}
		destination.setBounds(501, 49, 321, 50);
		contentPanel.add(destination);
		{
			JLabel lblNewLabel_1 = new JLabel("File name  :");
			lblNewLabel_1.setFont(new Font("Times New Roman", Font.BOLD, 20));
			lblNewLabel_1.setBounds(10, 51, 107, 45);
			contentPanel.add(lblNewLabel_1);
		}
		{
			JLabel lblNewLabel_2 = new JLabel(ManagerFile.getFile());
			lblNewLabel_2.setFont(new Font("Times New Roman", Font.BOLD, 20));
			lblNewLabel_2.setBounds(127, 48, 247, 50);
			contentPanel.add(lblNewLabel_2);
		}
		
		JLabel lblComments = new JLabel("comments");
		lblComments.setFont(new Font("Times New Roman", Font.BOLD, 16));
		lblComments.setBounds(384, 181, 79, 37);
		contentPanel.add(lblComments);
		
		JTextArea textArea = new JTextArea();
		textArea.setBounds(501, 160, 321, 133);
		contentPanel.add(textArea);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{   
				JButton okButton = new JButton("SEND");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						String office1=StaffManager.getOffice();
						String office2=(String)destination.getSelectedItem();
						
						ManagerFile office=new ManagerFile();
						if (destination.getSelectedIndex()==-1) {
							JOptionPane.showMessageDialog(null, "you must select the destination office");
						}
						else if(office1.equals(office2)){
							JOptionPane.showMessageDialog(null, "you can not forward file to your own office");
						}
						else if(office.checkFileStatus(ManagerFile.getFile()) && (office.equals("RECORD MANAGEMENT"))){
							JOptionPane.showMessageDialog(null, "The file are not in your office");
						}
//						else if(!(office.equals("RECORD MANAGEMENT")) && (ManagerFile.confirmfile(ManagerFile.getFilecode(), ManagerFile.getCurrentoffice()))){
//							JOptionPane.showMessageDialog(null, "Your have already send this file");
//						}
//						else if(!office.equals("RECORD MANAGEMENT")){
//							JOptionPane.showMessageDialog(null, "The file are not in your office");
//						}
						
						
						else {
							ManagerFile.setDestination(office2);
							ManagerFile.setComment(textArea.getText());
						
							  if(office.forwardfile(office.getdestinationoffice())){
								 
								  if(StaffManager.getStype().equals("Adminstrator")){
								office.updatefilestatus(ManagerFile.getFile(),"inactive");
//									String sql="select barcode,file_name,comment from file "
//											+ "join filemovement on barcode=filemoving "
//											+ " where filemovement.status='moved'";
//									
//									ResultSet rs=null;
//								      
//								      try(
//								     		
//								     		   PreparedStatement stmt=conn.prepareStatement(sql);
//								     		 ){
//								      	stmt.setInt(1, ManagerFile.getCurrentoffice());
//								      	rs=stmt.executeQuery();
//								      	Staff.table.setModel(DbUtils.resultSetToTableModel(rs));
//								      } catch (SQLException e) {
//										// TODO Auto-generated catch block
//										System.err.println(e.getMessage());
//										
//										
//									} 
								
								      
							      JOptionPane.showMessageDialog(null, "sucessfully forward to "+office2);
							      office.aftersend(ManagerFile.getFilecode());
							      setVisible(false);
							  	
							 
							   
								}
								  
								  else{
									  office.aftersend(ManagerFile.getFilecode());
									  
								  JOptionPane.showMessageDialog(null, "sucessfully forward to "+office2);
					
								  //count++;
							      setVisible(false);
							      
								  }
							  	
								}
							else{
								JOptionPane.showMessageDialog(null, "Error");
							}
							}
							
								
							
					
							
					}
				});
				okButton.setForeground(new Color(255, 255, 255));
				okButton.setBackground(new Color(0, 102, 0));
				okButton.setFont(new Font("Times New Roman", Font.BOLD, 12));
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						
						setVisible(false);
					}
				});
				cancelButton.setForeground(new Color(255, 255, 255));
				cancelButton.setBackground(new Color(204, 0, 0));
				cancelButton.setFont(new Font("Times New Roman", Font.BOLD, 12));
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
}
