import java.io.*;
import java.util.ArrayList;

public class OrgChart {
	public static void main(String[] args) throws IOException
    {
        BufferedReader reader = new BufferedReader(new FileReader("bin/org_chart_sample.in"));
        BufferedWriter writer = new BufferedWriter(new FileWriter("org_chart_sample_solution_java.out"));
        
        int numCases = Integer.parseInt(reader.readLine());
        
        for (int caseNum = 1; caseNum <= numCases; caseNum++)
        {
            String[] employees = reader.readLine().split("--");
            
            
            CreatOrgChart(employees, writer, caseNum);
        			
        }
        
        writer.close();
        reader.close();
    }
	
	private static void CreatOrgChart(String[] employees, BufferedWriter writer, 
			int caseNum) throws IOException {
		int EmployeeFound = 0;
		ArrayList<String> UpLevelEmployee = new ArrayList<String>();
		ArrayList<String> CurLevelEmployee = new ArrayList<String>();
		// search CEO
		for( int i = 0; i < employees.length; i++) {
			String[] parts = employees[i].split(",");
			if( parts[2] == "CEO"  ) {
				UpLevelEmployee.add(parts[0]);
				EmployeeFound++;
				writer.write("Case #" + caseNum + "\n");
		        writer.write(parts[0] + " (CE0) " + parts[3] + "\n");
		        break;	
			}
			
		}
		int level = 1;
		// search under level employee
		while( EmployeeFound != employees.length) {			
			for( int i = 0; i < employees.length; i++) {
				String[] parts = employees[i].split(",");
				if( UpLevelEmployee.contains(parts[1])  ) {
					CurLevelEmployee.add(parts[0]);
					EmployeeFound++;
					for( int j = 0; j < level; j++ )
						writer.write("-");
			        writer.write(parts[0] + "(" + parts[2] + ")"  + parts[3] + "\n");
				}
				
			}
			level++;
			UpLevelEmployee.clear();
			UpLevelEmployee = new ArrayList<String>(CurLevelEmployee);
			CurLevelEmployee.clear();
		}
		
	}
}
