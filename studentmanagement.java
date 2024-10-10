import java.util.Scanner;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class studentmanagement{
static Scanner sc=new Scanner(System.in);
//---------------------------------------------------------------------------------------buldconnection to database
Connection getConnection() throws SQLException{
return DriverManager.getConnection("jdbc:mysql://localhost:3306/student_management","root","sachin");
}
//---------------------------------------------------------------------------------------connection for displaydata function
ResultSet fatchdata(String query){
ResultSet resultset=null;
try{
Connection con=getConnection();
PreparedStatement pstm=con.prepareStatement(query);
resultset=pstm.executeQuery(query);
}catch(SQLException e){
e.printStackTrace();
}
return resultset;

}
//-----------------------------------------------------------------------------------add student data
void add(){
int rowaffected=0;
System.out.print("enter student name: ");
sc.nextLine();
String name=sc.nextLine();
System.out.print("enter student rollno: ");
int rollno=sc.nextInt();
System.out.print("enter student course: ");
sc.nextLine();
String course=sc.nextLine();
String query="insert into student(name,rollno,course)values(?,?,?)";
try{
Connection con=getConnection();
PreparedStatement pstm=con.prepareStatement(query);
pstm.setString(1,name);
pstm.setInt(2,rollno);
pstm.setString(3,course);
rowaffected=pstm.executeUpdate();
}catch(SQLException e){
e.printStackTrace();
}
if(rowaffected==0){
System.out.print("data not insert");
}
else{
System.out.println("data insert successfully");
}

}
//---------------------------------------------------------------------------------------delete
void delete(){
int roweffect=0;
System.out.print("enter sudent id which student you want to delete:");
int sid=sc.nextInt();
String query="DELETE FROM STUDENT WHERE ROLLNO="+sid;
try{
Connection con=getConnection();
PreparedStatement pstm=con.prepareStatement(query);
roweffect=pstm.executeUpdate();
}catch(SQLException e){
e.printStackTrace();
}
if(roweffect==0){
System.out.println("record not deleted");
}else{
System.out.println("record delete successfuly");
}
}
//------------------------------------------------------------------------------------------display
void display(){
ResultSet result=null;
try{
 result=fatchdata("select * from student");

while(result.next()){
String name=result.getString("name");
int rollno=result.getInt("rollno");
String course=result.getString("course");
System.out.println(name+" "+rollno+" "+course);
}
}catch(SQLException e){
e.printStackTrace();
}
}

//--------------------------------------------------------------------------------------main method

public static void main(String[] args){
studentmanagement sm=new studentmanagement();

System.out.println("helllo students");
while(true){
System.out.println("press 1 for add student");
System.out.println("press 2 for delete student record ");
System.out.println("press 3 for display all student ");
System.out.println("press 0 for exit");
int ch=sc.nextInt();
switch(ch){
case 1:
sm.add();
break;
case 2:
sm.delete();
break;
case 3:
sm.display();
break;
case 0:
System.exit(0);
break;
default:
System.out.println("wrong choice...");
}
}
}}