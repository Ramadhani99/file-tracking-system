import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import Connection.ConnectionManager;
import net.proteanit.sql.DbUtils;

import java.awt.Color;
import java.awt.CardLayout;
import javax.swing.JLabel;
import java.awt.Font;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.MessageFormat;

import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.print.PrinterException;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JToolBar;
import javax.swing.border.BevelBorder;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import com.toedter.calendar.JDateChooser;
import javax.swing.border.EtchedBorder;
import java.awt.SystemColor;
import javax.swing.border.SoftBevelBorder;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JSeparator;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Component;

public class Report {
	private static Connection conn=ConnectionManager.getInstance().getConnection();
	public JFrame frame;
	private JButton report;
	private JButton view;
	private JPanel panel_1;
	private JPanel panel_3;
	private JPanel panel_2;
	private JButton btnprint;
	private JTable table_1;
	private JTable table_2;
	private JScrollPane scrollPane;
	private JComboBox<String> cbxfile;
	private JDateChooser dfrom;
	private JDateChooser tdate;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Report window = new Report();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 * @throws SQLException 
	 */
	public Report() throws SQLException {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 * @throws SQLException 
	 */
	private void initialize() throws SQLException {
		frame = new JFrame();
		frame.setBounds(100, 100, 1062, 553);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		JPanel panel = new JPanel();
		panel.setBorder(new EtchedBorder(EtchedBorder.RAISED, null, null));
		panel.setBounds(10, 54, 209, 613);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		report = new JButton("FILE REPORT");
		report.setBackground(SystemColor.menu);
		report.setForeground(SystemColor.activeCaptionText);
		report.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				panel_1.removeAll();
				panel_1.repaint();
				panel_1.revalidate();
				panel_1.add(panel_3);
				
			}
		});
		report.setFont(new Font("Tahoma", Font.BOLD, 20));
		report.setBounds(10, 11, 189, 76);
		panel.add(report);
		
		JButton btnLogFiles = new JButton("LOG FILES");
		btnLogFiles.setBackground(SystemColor.menu);
		btnLogFiles.setForeground(SystemColor.activeCaptionText);
		btnLogFiles.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnLogFiles.setBounds(10, 206, 189, 76);
		panel.add(btnLogFiles);
		
		view = new JButton("USER REPORT");
		view.setBackground(SystemColor.menu);
		view.setForeground(SystemColor.activeCaptionText);
		view.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				panel_1.removeAll();
				panel_1.repaint();
				panel_1.revalidate();
				panel_1.add(panel_2);
			}
		});
		view.setFont(new Font("Tahoma", Font.BOLD, 20));
		view.setBounds(10, 109, 189, 76);
		panel.add(view);
		
		panel_1 = new JPanel();
		panel_1.setBorder(new LineBorder(new Color(0, 51, 204)));
		panel_1.setBounds(219, 54, 1135, 613);
		frame.getContentPane().add(panel_1);
		panel_1.setLayout(new CardLayout(0, 0));
		
		panel_3 = new JPanel();
		panel_3.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		panel_3.setBackground(SystemColor.menu);
		panel_1.add(panel_3, "name_38811361105475");
		panel_3.setLayout(null);
		
		JLabel lblMonthlyReport = new JLabel("Select file for the  report");
		lblMonthlyReport.setForeground(new Color(51, 51, 102));
		lblMonthlyReport.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 45));
		lblMonthlyReport.setBounds(359, 11, 495, 53);
		panel_3.add(lblMonthlyReport);
		
		JLabel lblNewLabel = new JLabel("FROM");
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 25));
		lblNewLabel.setBounds(459, 78, 85, 40);
		panel_3.add(lblNewLabel);
		
		JLabel lblTo = new JLabel("TO");
		lblTo.setFont(new Font("Times New Roman", Font.BOLD, 25));
		lblTo.setBounds(765, 78, 35, 40);
		panel_3.add(lblTo);
		
		JLabel lblSelecetFile = new JLabel("SELECET FILE");
		lblSelecetFile.setFont(new Font("Times New Roman", Font.BOLD, 25));
		lblSelecetFile.setBounds(42, 78, 190, 40);
		panel_3.add(lblSelecetFile);
		
		cbxfile = new JComboBox<String>();
		cbxfile.setBounds(242, 78, 207, 31);
		panel_3.add(cbxfile);
        String sql1="select  *from file";
		
		ResultSet rset=null;
	      
	      try(
	     		
	     		   PreparedStatement stmt=conn.prepareStatement(sql1);
	     		 ){
	      	
	      	rset=stmt.executeQuery();
	      	while (rset.next()) {
	      		cbxfile.setSelectedItem(null);
	      		cbxfile.addItem(rset.getString("file_name"));
			}
	      	
	      } catch (SQLException e) {
			// TODO Auto-generated catch block
			System.err.println(e.getMessage());
			
			
		}
	      finally{
	    	  rset.close();
	    	  
	    	  
	      }
		
		btnprint = new JButton("PRINT");
		btnprint.setBorder(new BevelBorder(BevelBorder.RAISED, new Color(0, 51, 0), null, null, null));
		btnprint.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnprint.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				MessageFormat header =new MessageFormat("All Movement of the file");
      			MessageFormat footer =new MessageFormat("copyright");
      			try {
					table_2.print(JTable.PrintMode.FIT_WIDTH,header,footer);
				} catch (PrinterException e) {
					// TODO Auto-generated catch block
					e.getMessage();
				}
			}
		});
		btnprint.setForeground(Color.WHITE);
		btnprint.setBackground(new Color(255, 51, 51));
		btnprint.setBounds(103, 543, 133, 57);
		panel_3.add(btnprint);
		
		JButton btnview = new JButton("VIEW IN PDF");
		btnview.setBorder(new BevelBorder(BevelBorder.RAISED, new Color(0, 51, 0), null, null, null));
		btnview.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Document doc = new Document();
				try {
					PdfWriter writer= PdfWriter.getInstance(doc, new FileOutputStream("rama2.pdf"));
					doc.open();
					doc.add(new Paragraph("Wellcome To The file system",FontFactory.getFont(FontFactory.COURIER_BOLDOBLIQUE,18,BaseColor.ORANGE)));
					doc.close();
					writer.close();
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (DocumentException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} 
				
			}
		});
		btnview.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnview.setBackground(new Color(255, 51, 102));
		btnview.setForeground(Color.WHITE);
		btnview.setBounds(300, 547, 144, 53);
		panel_3.add(btnview);
		
		table_1 = new JTable();
		table_1.setBounds(249, 280, 1, 1);
		panel_3.add(table_1);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setForeground(SystemColor.textText);
		scrollPane_1.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		scrollPane_1.setBackground(new Color(0, 0, 128));
		scrollPane_1.setBounds(52, 129, 1041, 378);
		panel_3.add(scrollPane_1);
		
		table_2 = new JTable();
		scrollPane_1.setViewportView(table_2);
		
		dfrom = new JDateChooser();
		dfrom.setBounds(567, 75, 171, 34);
		panel_3.add(dfrom);
		
		tdate = new JDateChooser();
		tdate.setBounds(810, 75, 171, 34);
		panel_3.add(tdate);
		
		JButton btnNewButton_1 = new JButton("SHOW");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String reports="select *from fileoperation "
						+ "where file=? AND date_moved between ? and ?";
				ResultSet qry=null;
				try(PreparedStatement sqlstmt=conn.prepareStatement(reports);
						) {
				
				
				java.sql.Date sqldate=new  java.sql.Date(dfrom.getDate().getTime());
					java.sql.Date sqldate1= new  java.sql.Date(tdate.getDate().getTime());

//					JOptionPane.showMessageDialog(null, (String)cbxfile.getSelectedItem()  + "date");
//				JOptionPane.showMessageDialog(null, sqldate  + "date");
//				JOptionPane.showMessageDialog(null, dfrom.getDate()  + "date");
		sqlstmt.setString(1,(String)cbxfile.getSelectedItem());		
		sqlstmt.setDate(2,sqldate);
			sqlstmt.setDate(3,sqldate1);
			qry=sqlstmt.executeQuery();
			table_2.setModel(DbUtils.resultSetToTableModel(qry));
			
				} catch (Exception e) {
					// TODO: handle exception
					
				}
				
			}
		});
		btnNewButton_1.setBorder(new BevelBorder(BevelBorder.RAISED, new Color(0, 51, 102), null, null, null));
		btnNewButton_1.setForeground(new Color(255, 255, 255));
		btnNewButton_1.setBackground(new Color(0, 153, 51));
		btnNewButton_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnNewButton_1.setBounds(991, 75, 89, 34);
		panel_3.add(btnNewButton_1);
		
		panel_2 = new JPanel();
		panel_2.setBackground(Color.WHITE);
		panel_1.add(panel_2, "name_38746297672064");
		panel_2.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("ALL USER OF THE SYSTEM");
		lblNewLabel_1.setForeground(new Color(255, 140, 0));
		lblNewLabel_1.setFont(new Font("Tekton Pro", Font.BOLD, 20));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(524, 28, 259, 38);
		panel_2.add(lblNewLabel_1);
		
		scrollPane = new JScrollPane();
		scrollPane.setViewportBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		scrollPane.setBackground(Color.white);
		scrollPane.setBounds(10, 86, 1113, 406);
		panel_2.add(scrollPane);
		
		JTable table = new JTable();
		table.setBackground(new Color(255, 255, 255));
		table.setSelectionForeground(new Color(240, 255, 255));
		table.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		scrollPane.setViewportView(table);
		//add item to the table
        String sql="select *from allstaff";
		
		ResultSet rs=null;
	      
	      try(
	     		
	     		   PreparedStatement stmt=conn.prepareStatement(sql);
	     		 ){
	      	//stmt.setString(1, StaffManager.getOffice());
	      	rs=stmt.executeQuery();
	      	table.setModel(DbUtils.resultSetToTableModel(rs));
	      	
	      	JButton btnNewButton = new JButton("Print");
	      	btnNewButton.addActionListener(new ActionListener() {
	      		public void actionPerformed(ActionEvent arg0) {
	      			MessageFormat header =new MessageFormat("All user of the system");
	      			MessageFormat footer =new MessageFormat("copyright");
	      			try {
						table_1.print(JTable.PrintMode.FIT_WIDTH,header,footer);
					} catch (PrinterException e) {
						// TODO Auto-generated catch block
						e.getMessage();
					}
	      			
	      		}
	      	});
	      	btnNewButton.setBackground(new Color(255, 51, 0));
	      	btnNewButton.setFont(new Font("Times New Roman", Font.PLAIN, 18));
	      	btnNewButton.setBounds(10, 546, 118, 38);
	      	panel_2.add(btnNewButton);
	      	
	      	JMenuBar menuBar = new JMenuBar();
	      	menuBar.setAlignmentX(Component.LEFT_ALIGNMENT);
	      	menuBar.setFont(new Font("Times New Roman", Font.PLAIN, 12));
	      	menuBar.setBackground(new Color(0, 51, 102));
	      	menuBar.setBounds(5, 0, 1334, 35);
	      	frame.getContentPane().add(menuBar);
	      	
	      	JMenu mnFile = new JMenu("File");
	      	mnFile.setFont(new Font("Times New Roman", Font.BOLD, 13));
	      	mnFile.setForeground(SystemColor.text);
	      	mnFile.setBackground(SystemColor.textHighlightText);
	      	menuBar.add(mnFile);
	      	
	      	JMenu mnClose = new JMenu("Close");
	      	mnClose.setFont(new Font("Segoe UI Black", Font.BOLD, 13));
	      	mnClose.addMouseListener(new MouseAdapter() {
	      		@Override
	      		public void mouseClicked(MouseEvent arg0) {
	      			try {
						new Admin().frmFileTrackingSystem.setVisible(true);
						frame.dispose();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
	      		}
	      	});
	      	mnFile.add(mnClose);
	      	
	      	JSeparator separator = new JSeparator();
	      	mnFile.add(separator);
	      	
	      	JMenu mnExit = new JMenu("Exit");
	      	mnExit.setFont(new Font("Segoe UI Black", Font.BOLD, 13));
	      	mnFile.add(mnExit);
	      } catch (SQLException e) {
			// TODO Auto-generated catch block
			System.err.println(e.getMessage());
			
			
		} 
	}
}
