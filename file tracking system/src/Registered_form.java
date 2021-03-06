
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Font;
import java.awt.HeadlessException;
import java.awt.SystemColor;
import java.awt.Toolkit;

import javax.swing.JToolBar;

import Connection.ConnectionManager;

import javax.swing.JTextField;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.JPasswordField;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Registered_form extends JDialog {
	/**
	 * 
	 */
	private static Connection conn=ConnectionManager.getInstance().getConnection();
	private static final long serialVersionUID = 1L;
	private JTextField staffName;
	private JTextField staffId;
	private JPasswordField password;
	private JPasswordField ConfirmP;
	private String dept;
	private JComboBox<String> department ;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			Registered_form dialog = new Registered_form();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.	setUndecorated(true);
			dialog.setVisible(true);
			dialog.setLocationRelativeTo(new loginForm().frame);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 * @throws SQLException 
	 */
	public Registered_form() throws SQLException {
		
		getContentPane().setBackground(SystemColor.control);
		
		setBounds(100, 100, 555, 465);
		getContentPane().setLayout(null);
		{
			
			
			/*ComboBox which contain dept_names*/
			department = new JComboBox<String>();
			String sql="select * from office";
			try(
			Statement stmt=conn.createStatement();
			ResultSet rs=stmt.executeQuery(sql);
				)
			{
			
			while(rs.next())
				{
				   department.setSelectedItem(null);
				   department.addItem(rs.getString("officeName"));
				}
			}
			catch
			(SQLException e)
			{
				System.err.println(e.getMessage());
			}
			department.addActionListener(new ActionListener(){

				@Override
				public void actionPerformed(ActionEvent event) {
					// TODO Auto-generated method stub
					
				@SuppressWarnings("rawtypes")
				JComboBox department=(JComboBox)event.getSource();
					
					 dept=(String)department.getSelectedItem();
					 //JOptionPane.showMessageDialog(null,dept);
					
				}
				
			}
					);
			 /*Take dept_name in order to get dept_id*/ 
			
			  //dept=(String)department.getSelectedItem();
			  //JOptionPane.showMessageDialog(null,dept);
			
			department.setBackground(new Color(255, 255, 224));
			department.setBounds(261, 189, 253, 33);
			getContentPane().add(department);

			JPanel buttonPane = new JPanel();
			buttonPane.setBackground(SystemColor.inactiveCaption);
			buttonPane.setBounds(0, 370, 550, 60);
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane);
			{
				JButton Register = new JButton("Register");
				Register.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						
						String staffid=staffId.getText();
						String staffname=staffName.getText();
		
						@SuppressWarnings("deprecation")
						String pssword=password.getText();
						@SuppressWarnings("deprecation")
						String pssword1=ConfirmP.getText();
						StaffManager stff=new StaffManager(staffid,staffname,dept,pssword);
					if(staffid.equals("")||staffname.equals("")||pssword.equals("")||department.getSelectedIndex() == -1)
					{
						JOptionPane.showMessageDialog(null, "all fields are mandatory");
					}
					else
					{
					if(pssword.equals(pssword1)){
						
						try {
							if(stff.insert())
							{
								JOptionPane.showMessageDialog(null, "successfully registered ");
								loginForm l = new loginForm();
								l.frame.setVisible(true);
							}
							else
							{
								JOptionPane.showMessageDialog(null, "password match and row was not inserted");
							}
						} catch (HeadlessException | SQLException e) {
							// TODO Auto-generated catch block
							System.err.println(e.getMessage());
						}
						
					}
					else
					{
						JOptionPane.showMessageDialog(null,"password mis match");	
					}
					}
					
					}
				});
				Register.setForeground(new Color(255, 255, 255));
				Register.setBackground(new Color(60, 179, 113));
				Register.setActionCommand("OK");
				buttonPane.add(Register);
				getRootPane().setDefaultButton(Register);
			}
			{
				JButton cancel = new JButton("Cancel");
				cancel.setSize(150, 75);
				cancel.setMaximumSize(getSize());
				cancel.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0)
					{
						
						setVisible(false);
											
					}
				});
				cancel.setForeground(new Color(255, 255, 255));
				cancel.setBackground(new Color(220, 20, 60));
				cancel.setActionCommand("Cancel");
				buttonPane.add(cancel);
			}
		}
		
		JLabel lblNewLabel = new JLabel("     Staff Registered Form");
		lblNewLabel.setBackground(SystemColor.activeCaption);
		lblNewLabel.setFont(new Font("Verdana", Font.BOLD, 12));
		lblNewLabel.setBounds(0, 0, 488, 26);
		getContentPane().add(lblNewLabel);
		
		JToolBar toolBar = new JToolBar();
		toolBar.setBackground(SystemColor.inactiveCaption);
		toolBar.setBounds(0, 31, 550, 40);
		getContentPane().add(toolBar);
		
		JLabel lblStaffId = new JLabel("Staff ID");
		lblStaffId.setForeground(new Color(0, 0, 51));
		lblStaffId.setFont(new Font("Times New Roman", Font.BOLD, 15));
		lblStaffId.setBounds(20, 94, 70, 26);
		getContentPane().add(lblStaffId);
		
		
		JLabel lblStaffName = new JLabel("Staff Name");
		lblStaffName.setFont(new Font("Times New Roman", Font.BOLD, 15));
		lblStaffName.setBounds(20, 145, 70, 26);
		getContentPane().add(lblStaffName);
		
		JLabel lblDepartment = new JLabel("Department");
		lblDepartment.setFont(new Font("Times New Roman", Font.BOLD, 15));
		lblDepartment.setBounds(20, 196, 84, 26);
		getContentPane().add(lblDepartment);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setFont(new Font("Times New Roman", Font.BOLD, 15));
		lblPassword.setBounds(20, 247, 70, 26);
		getContentPane().add(lblPassword);
		
		JLabel lblConfirmPassword = new JLabel("Confirm Password");
		lblConfirmPassword.setFont(new Font("Times New Roman", Font.BOLD, 15));
		lblConfirmPassword.setBounds(20, 295, 129, 26);
		getContentPane().add(lblConfirmPassword);
		
				
		password = new JPasswordField();
		password.setBackground(new Color(255, 255, 224));
		password.setBounds(261, 240, 253, 33);
		getContentPane().add(password);
		
		ConfirmP = new JPasswordField();
		ConfirmP.setBackground(new Color(255, 255, 224));
		ConfirmP.setBounds(261, 288, 253, 33);
		getContentPane().add(ConfirmP);
		
		staffName = new JTextField();
		staffName.setToolTipText("only letter allowed");
		staffName.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				
				char c = e.getKeyChar();
				if (Character.isLetter(c) || Character.isISOControl(c)) {
					//String text= String.valueOf(c);
					//staffName.setText(staffName.getText()+c);
					
				}
				else{
					
					e.consume();
					Toolkit.getDefaultToolkit().beep();
					staffName.getToolTipText();
				}
				
			}
		});
		staffName.setBounds(261, 138, 253, 33);
		getContentPane().add(staffName);
		staffName.setBackground(new Color(255, 255, 224));
		staffName.setColumns(10);
		
		staffId = new JTextField();
		staffId.setBounds(261, 86, 253, 33);
		getContentPane().add(staffId);
		staffId.setFont(new Font("Tahoma", Font.BOLD, 13));
		staffId.setBackground(new Color(255, 255, 224));
		staffId.setColumns(10);
		staffId.setText("IMC/STAFF/");
		/*End of ComcoBox code###*/
		
		
		
		//conn.close();
	}
}



