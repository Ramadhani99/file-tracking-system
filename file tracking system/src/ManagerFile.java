import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JOptionPane;

import Connection.ConnectionManager;
//import net.proteanit.sql.DbUtils;



public class ManagerFile {
private static Connection conn=ConnectionManager.getInstance().getConnection();
private String barcode;
private static String officeDestination;

public String filename;
private String filetype="Subject";
private static String comment;
private Date d_created;
private String subject;
private static int sid;
private static String file;
private static String filecode;
private static int currentoffice;
private static String destination;
public static ArrayList<String> pendingfile;
private static Date d_moved;
public static Date getD_moved() {
	return d_moved;
}
public static void setD_moved(Date d_moved) {
	ManagerFile.d_moved = d_moved;
}
public static ArrayList<String> getPendingfile() {
	return pendingfile;
}
public static void setPendingfile(ArrayList<String> pendingfile) {
	ManagerFile.pendingfile = pendingfile;
}
public static String getOfficeDestination() {
	return officeDestination;
}
public static void setOfficeDestination(String filemoving) {
	ManagerFile.officeDestination = filemoving;
}
public static String getDestination() {
	return destination;
}
public static void setDestination(String destination) {
	ManagerFile.destination = destination;
}
public static String getFilecode() {
	return filecode;
}
public static void setFilecode(String filecode) {
	ManagerFile.filecode = filecode;
}


public static int getCurrentoffice() {
	return currentoffice;
}
public static void setCurrentoffice(int currentoffice) {
	ManagerFile.currentoffice = currentoffice;
}
public static String getFile() {
	return file;
}
public static void setFile(String file) {
	ManagerFile.file = file;
}
public int getSid() {
	return sid;
}
public void setSid(int sid) {
	ManagerFile.sid = sid;
}
public ManagerFile(){
	
}
public ManagerFile(String subject){
	this.subject=subject;
}
public ManagerFile(String barcode,String filename){
	this.barcode=barcode;
	this.filename=filename;
}


public ManagerFile(String barcode,String filename,String filetype,String comment,Date d_created){
	this.barcode=barcode;
	this.filename=filename;
	this.filetype=filetype;
	//this.setComment(comment);
	this.d_created=d_created;
	
}
public ManagerFile(String barcode,String filename,String comment){
	this.barcode=barcode;
	this.filename=filename;
	//this.setComment(comment);
	
}
public static String getComment() {
	return comment;
}
public static void printarrayelement(){
	for ( String p : pendingfile) {
		 System.out.println(p);
	}
}
public static void setComment(String comment) {
	ManagerFile.comment = comment;
}
public boolean insertfile(){
	d_created=new Date();
	java.sql.Date sqldate=new java.sql.Date(d_created.getTime());
	String sql="insert into file values(?,?,?,?,?,?)";
	
	
      
      try(
     		
     		   PreparedStatement stmt=conn.prepareStatement(sql);
     		 ){
    	stmt.setString(1,barcode);
  		stmt.setString(2,filename);
  		stmt.setString(3,filetype);
		stmt.setInt(4,sid);
		stmt.setString(5,"active");
  		//stmt.setString(5, comment);
  		stmt.setDate(6,sqldate);
  		
  		int affected=stmt.executeUpdate();
  		if (affected==1) {
  			//JOptionPane.showMessageDialog(null, "one row inserted");
  			return true;
  		} else {
  			JOptionPane.showMessageDialog(null, "Error");
  			return false;
  		}
      	
      	
      	
      } catch (SQLException e) {
		// TODO Auto-generated catch block
    	  JOptionPane.showMessageDialog(null,barcode + " " + filename + " " + filetype  + " "+ sid  + " "+ sqldate );
		System.err.println(e.getMessage());
		//JOptionPane.showMessageDialog(null, "Error"+sid);
		
		
	}
      return false;
}
public ResultSet table(){
	String sql="select *from file";
	
	ResultSet rs=null;
      
      try(
     		// Connection conn=Connector.getconnection(DBtype.MYSQL);
     		   PreparedStatement stmt=conn.prepareStatement(sql);
     		 ){
      	
      	rs=stmt.executeQuery();
      
      return rs;
      
      } catch (SQLException e) {
		// TODO Auto-generated catch block
		System.err.println(e.getMessage());
		return null;
		
	}
	
}
public boolean deletefile(){
	
	
	String sql="delete from file where barcode=?";
	
	
      
      try(
     		
     		   PreparedStatement stmt=conn.prepareStatement(sql);
     		 ){
    	stmt.setString(1,barcode);
  		
  		
  		int affected=stmt.executeUpdate();
  		if (affected==1) {
  			JOptionPane.showMessageDialog(null, "The file " + filename +" have been deleted");
  			return true;
  		} else {
  			JOptionPane.showMessageDialog(null, "Error");
  			return false;
  		}
      	
      	
      	
      } catch (SQLException e) {
		// TODO Auto-generated catch block
		System.err.println(e.getMessage());
		//JOptionPane.showMessageDialog(null, "Error "+e.getMessage());
		
		
	}
      return false;
}
public boolean search() throws SQLException{
	
	
	String sql="SELECT *FROM file WHERE barcode LIKE ?";
	
	ResultSet rs=null;
      
      try(
     		
     		   PreparedStatement stmt=conn.prepareStatement(sql);
     		 ){
    	stmt.setString(1,"%"+barcode+"%");
    	//stmt.setString(2,filename);
  		
  		
  		rs=stmt.executeQuery();
  		while(rs.next()){
  			//setModel(DbUtils.resultSetToTableModel(rs));
  			return true;
  		}
      	
      	
      } catch (SQLException e) {
		// TODO Auto-generated catch block
		System.err.println(e.getMessage());
		//JOptionPane.showMessageDialog(null, "Error "+e.getMessage());
		 
		
	}
      finally{
    	  rs.close();
      }
      return false; 
}
public boolean updatefile(){
	String sql="update file set barcode=?,file_name=? where  barcode=?";
	
	
    
    try(
   		
   		   PreparedStatement stmt=conn.prepareStatement(sql);
   		 ){
  	stmt.setString(1,barcode);
		stmt.setString(2,filename);
		
		//stmt.setString(3, comment);
		stmt.setString(4, barcode);
		
		
		int affected=stmt.executeUpdate();
		if (affected==1) {
			//JOptionPane.showMessageDialog(null, "one row inserted");
			return true;
		} else {
			JOptionPane.showMessageDialog(null, "Error");
			return false;
		}
    	
    	
    	
    } catch (SQLException e) {
		// TODO Auto-generated catch block
		System.err.println(e.getMessage());
		JOptionPane.showMessageDialog(null, "Error");
		
		
	}
    return false;
	
}
public String getcodepattern(){
String sql="SELECT *FROM fileSubject WHERE Sname=?";
	
	ResultSet rs=null;
      
      try(
     		
     		   PreparedStatement stmt=conn.prepareStatement(sql);
     		 ){
    	stmt.setString(1,subject);
    	//stmt.setString(2,filename);
  		
  		
  		rs=stmt.executeQuery();
  		
  		while (rs.next()) {
  			String res=rs.getString("codePattern");
  			setSid(rs.getInt("SId"));
  			//JOptionPane.showMessageDialog(null, sid);
  	  		return res;
		}
  		
  	   	
      	
      } catch (SQLException e) {
		// TODO Auto-generated catch block
		System.err.println(e.getMessage());
		//JOptionPane.showMessageDialog(null, "Error "+e.getMessage());
		
		
	}
      return null;
}
public String getbarcode(String filename){
String sql="SELECT *FROM file WHERE file_name=?";
	
	ResultSet rs=null;
      
      try(
     		
     		   PreparedStatement stmt=conn.prepareStatement(sql);
     		 ){
    	stmt.setString(1,filename);
    	//stmt.setString(2,filename);
  		
  		
  		rs=stmt.executeQuery();
  		
  		while (rs.next()) {
  			String res=rs.getString("barcode");
  		
  			//JOptionPane.showMessageDialog(null, sid);
  	  		return res;
		}
  		
  	   	
      	
      } catch (SQLException e) {
		// TODO Auto-generated catch block
		System.err.println(e.getMessage());
		//JOptionPane.showMessageDialog(null, "Error "+e.getMessage());
		
		
	}
      return null;
}
public boolean forwardfile(int destinationId){
	d_created=new Date();
	java.sql.Date sqldate=new java.sql.Date(d_created.getTime());
	String sql="insert into filemovement (file_sender,file_destination,filemoving,date_moved,comment)"
			+ " values(?,?,?,?,?)";
	
	
      
      try(
     		
     		   PreparedStatement stmt=conn.prepareStatement(sql);
     		 ){
    	stmt.setInt(1,currentoffice);
  		stmt.setInt(2,destinationId);
  		stmt.setString(3,filecode);
		stmt.setDate(4,sqldate);
		stmt.setString(5,getComment());
  		
  		int affected=stmt.executeUpdate();
  		if (affected==1) {
  			//JOptionPane.showMessageDialog(null, "one row inserted");
  			return true;
  		} else {
  			JOptionPane.showMessageDialog(null, "Error");
  			return false;
  		}
      	
      	
      	
      } catch (SQLException e) {
		// TODO Auto-generated catch block
		System.err.println(e.getMessage());
		//JOptionPane.showMessageDialog(null, "Error"+sid);
		
		
	}
      return false;
	
} 
public int getdestinationoffice(){
String sql="SELECT *FROM office WHERE officeName=?";
	
	ResultSet rs=null;
      
      try(
     		
     		   PreparedStatement stmt=conn.prepareStatement(sql);
     		 ){
    	stmt.setString(1,destination);
    	//stmt.setString(2,filename);
  		
  		
  		rs=stmt.executeQuery();
  		
  		while (rs.next()) {
  			
  			//JOptionPane.showMessageDialog(null, sid);
  	  		return rs.getInt("offId");
		}
  		
  	   	
      	
      } catch (SQLException e) {
		// TODO Auto-generated catch block
		System.err.println(e.getMessage());
		//JOptionPane.showMessageDialog(null, "Error "+e.getMessage());
		
		
	}
      return 0;
}
public boolean updatefilestatus(String filename,String status){
String sql="update file set status=? where  file_name=? or barcode=?";
	
	
    
    try(
   		
   		   PreparedStatement stmt=conn.prepareStatement(sql);
   		 ){
  	stmt.setString(1,status);
  	stmt.setString(2,filename);
  	stmt.setString(3,filename);
		
		
		
		int affected=stmt.executeUpdate();
		if (affected==1) {
			//JOptionPane.showMessageDialog(null, "one row inserted");
			return true;
		} else {
			JOptionPane.showMessageDialog(null, "Error");
			return false;
		}
    	
    	
    	
    } catch (SQLException e) {
		// TODO Auto-generated catch block
		System.err.println(e.getMessage());
		
		
		
	}
    return false;
}
public boolean aftersend(String barcode){
String sql="update filemovement set status='moving' where "
		+ " filemoving=? and file_destination=? and status='received'";
	
	
    
    try(
   		
   		   PreparedStatement stmt=conn.prepareStatement(sql);
   		 ){
  	stmt.setString(1,barcode);
	stmt.setInt(2,currentoffice);	
		
		
		int affected=stmt.executeUpdate();
		if (affected==1) {
			//JOptionPane.showMessageDialog(null, "one row inserted");
			return true;
		} else {
			//JOptionPane.showMessageDialog(null, "Error");
			return false;
		}
    	
    	
    	
    } catch (SQLException e) {
		// TODO Auto-generated catch block
		System.err.println(e.getMessage());
		
		
		
	}
    return false;
}
public boolean updatreceivedfile(String barcode){
String sql="update filemovement set status='moved' where "
		+ " filemoving=? and status='moving'";
	
	
    
    try(
   		
   		   PreparedStatement stmt=conn.prepareStatement(sql);
   		 ){
  	stmt.setString(1,barcode);
	//stmt.setInt(2,currentoffice);	
		
		
		int affected=stmt.executeUpdate();
		if (affected==1) {
			//JOptionPane.showMessageDialog(null, "one row inserted");
			return true;
		} else {
			//JOptionPane.showMessageDialog(null, "Error");
			return false;
		}
    	
    	
    	
    } catch (SQLException e) {
		// TODO Auto-generated catch block
		System.err.println(e.getMessage());
		
		
		
	}
    return false;
}
public boolean updatmovedfile(String barcode){
String sql="update filemovement set status='received' where "
		+ "  status='pending' and filemoving=?";
	
	
    
    try(
   		
   		   PreparedStatement stmt=conn.prepareStatement(sql);
   		 ){
  	stmt.setString(1,barcode);
		
		
		
		int affected=stmt.executeUpdate();
		if (affected==1) {
			//JOptionPane.showMessageDialog(null, "one row inserted");
			return true;
		} else {
			JOptionPane.showMessageDialog(null, barcode);
			return false;
		}
    	
    	
    	
    } catch (SQLException e) {
		// TODO Auto-generated catch block
		System.err.println(e.getMessage());
		
		
		
	}
    return false;
}
public boolean checkFileStatus(String filename){
String sql="SELECT *FROM file WHERE file_name=?";
	
	ResultSet rs=null;
      
      try(
     		
     		   PreparedStatement stmt=conn.prepareStatement(sql);
     		 ){
    	stmt.setString(1,filename);
    	//stmt.setString(2,filename);
  		
  		
  		rs=stmt.executeQuery();
  		
  		while (rs.next()) {
  			
  			//JOptionPane.showMessageDialog(null, sid);
  	  		if(rs.getString("status").equals("inactive")){
  	  			
  	  			return true;
  	  		}
		}
  		
  	   	
      	
      } catch (SQLException e) {
		// TODO Auto-generated catch block
		System.err.println(e.getMessage());
		//JOptionPane.showMessageDialog(null, "Error "+e.getMessage());
		
		
	}
      return false;
}
public boolean checkmovedFileStatus(String filename){
String sql="SELECT *FROM filemovement "
		+ " join file on filemoving=barcode WHERE file_name=?";
	
	ResultSet rs=null;
      
      try(
     		
     		   PreparedStatement stmt=conn.prepareStatement(sql);
     		 ){
    	stmt.setString(1,filename);
    	//stmt.setString(2,filename);
  		
  		
  		rs=stmt.executeQuery();
  		
  		while (rs.next()) {
  			
  			//JOptionPane.showMessageDialog(null, sid);
  	  		if(rs.getString("status").equals("pending")){
  	  			
  	  			return true;
  	  		}
		}
  		
  	   	
      	
      } catch (SQLException e) {
		// TODO Auto-generated catch block
		System.err.println(e.getMessage());
		//JOptionPane.showMessageDialog(null, "Error "+e.getMessage());
		
		
	}
      return false;
}
public static boolean receivedfile(){
String sql="SELECT filemoving,file.file_name "
		+ "FROM filemovement join file on "
		+ "filemovement.filemoving=file.barcode "
		+ "WHERE "
		+ "filemovement.status='pending'"
		+ " AND file_destination=?";
	
	ResultSet rs=null;
      int exc=0;
      try(
     		
     		   PreparedStatement stmt=conn.prepareStatement(sql);
     		 ){
    	stmt.setInt(1,currentoffice);
    	//stmt.setString(2,filename);
  		
  		
  		rs=stmt.executeQuery();
  		pendingfile=new ArrayList<>();
  		while (rs.next()) {
  			
  			pendingfile.add(rs.getString("file_name"));
  			
  	  		exc=1;
  	  	
		}
  		if(exc==1){
  			return true;
  		}
  	   	
      	
      } catch (SQLException e) {
		// TODO Auto-generated catch block
		System.err.println(e.getMessage());
		//JOptionPane.showMessageDialog(null, "Error "+e.getMessage());
		
		
	}
      return false;
      
}
public boolean filelocation(){
	   String sql="SELECT filemoving,date_moved,officeName"
	
		+ " FROM filemovement"
		
		+ " join office on filemovement.file_destination=office.offId"
		+ " WHERE filemoving=? and status='received' ";
	
	ResultSet rs=null;
      
      try(
     		
     		   PreparedStatement stmt=conn.prepareStatement(sql);
     		 ){
    	stmt.setString(1,getFilecode());
    	//stmt.setString(2,filename);
  		
    	
  		rs=stmt.executeQuery();
  		
  		while (rs.next()) {
  			setOfficeDestination(rs.getString("officeName"));
  			setD_moved(rs.getDate("date_moved"));
  			return true;
  			
  			
		}
  		
		  		
		  		
      } catch (SQLException e) {
		// TODO Auto-generated catch block
		System.err.println(e.getMessage());
		//JOptionPane.showMessageDialog(null, "Error "+e.getMessage());
		
		
	}
      JOptionPane.showMessageDialog(null, "The file is in your office ");
      return false;
	
}
public static boolean confirmfile(String mfile,int offid){
	   String sql="SELECT "
	
		+ " *FROM filemovement"
		
		+ " WHERE filemoving=? and file_destination=? ";
	
	ResultSet rs=null;
   
   try(
  		
  		   PreparedStatement stmt=conn.prepareStatement(sql);
  		 ){
 	stmt.setString(1,mfile);
 	stmt.setInt(2, offid);
 	//stmt.setString(2,filename);
		
 	
		rs=stmt.executeQuery();
		
		while (rs.next()) {
			if (rs.getString("status").equals("received")) {
				  
			return false;
			}
			
			else{
				return true;
			}
			
		}
		
		  		
		  		
   } catch (SQLException e) {
		// TODO Auto-generated catch block
		System.err.println(e.getMessage());
		//JOptionPane.showMessageDialog(null, "Error "+e.getMessage());
		
		
	}
   
   return true;
	
}

}
