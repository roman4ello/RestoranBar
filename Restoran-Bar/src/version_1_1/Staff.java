package version_1_1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;
import java.util.Vector;

 
public class Staff {

	String surName; 
	String name;
	String secondName;
	int uniqueIdStaff;
	static int count=0;//
	
	//constructor for new Staff
	public Staff(String surName, String name, String secondName) {
		super();
		this.surName = surName;
		this.name = name;
		this.secondName = secondName;
		count++;
		uniqueIdStaff= count;
	}
	public Staff( ) {
		super();
		 
	}
	
	static Vector<Staff> vectorStaff =  new Vector();// objects  = <Staff>
	static Vector<String> temp =  new Vector();//String names of oficiants for cBox_officiant

 	
	public static Vector<Staff> parserCreateStaffFromFile(String pathToFileStaff) throws IOException{
		BufferedReader buffReader = new BufferedReader(new FileReader(pathToFileStaff));
		String str = "";

 		for (; (str = buffReader.readLine()) != null;) {
 			if (str.trim().equals(""))	continue;
 			if (str.contains("#КОНЕЦ_ФАЙЛА!")) {
				break;
			}// if

		 		StringTokenizer tokzer = new StringTokenizer(str, " ");
		 		Staff temp_staff = new Staff("", "", "");
 				String str_temp = (String) tokzer.nextElement().toString().trim();
					temp_staff.surName = str_temp;  
					temp_staff.name = (String) tokzer.nextElement().toString().trim();  
					temp_staff.secondName = (String) tokzer.nextElement().toString().trim();  
  				vectorStaff.add(temp_staff);

		}// for 
 		 
		buffReader.close();		
		return vectorStaff;
	}
	
	
	public  static  Vector<String> doNamesOfficiantBox(String pathToFileStaff) throws IOException{
		Vector<Staff> tempStaff =parserCreateStaffFromFile(pathToFileStaff);
		
		 for (Staff elem : tempStaff) {
			  temp.add(elem.uniqueIdStaff+ " "+elem.surName+" "+ elem.name + " "+ elem.secondName);

		}//foreach
		
		return temp;
		
	}
	
	
	public static void main(String[] args) throws IOException {
 
		
		new Staff().doNamesOfficiantBox("d:\\MyTeamProject\\IO\\staff_officiant.ini");
	
System.out.println(temp);	
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "ФИО:"+ surName + " "+ name + " " + secondName +" Уник.ID " + uniqueIdStaff + " ";
	}

	
	
	
}//class
