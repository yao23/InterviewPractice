
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

public class EmployeeTree {

	public static void main(String[] args) {
		try {
			BufferedReader reader = new BufferedReader(new FileReader(
					"testcase/testcase.in"));
			BufferedWriter writer = new BufferedWriter(new FileWriter(
					"testcase/testcase.out"));

			int numCases = Integer.parseInt(reader.readLine());

			for (int i = 1; i <= numCases; i++) {
				writer.write("Case #" + i + "\n");
				String str = reader.readLine();
				if (str.charAt(0) == '#')
					continue;

				String[] employees = str.split("--");
				Map<String, Employee> all = new HashMap<String, Employee>();
				String ceo = "";
				// process the string
				for (int j = 0; j < employees.length; j++) {
					// employees[i].replace(" ", "");
					String[] tokens = employees[j].split(",");

					if (all.containsKey(tokens[0])) {
						all.get(tokens[0]).title = tokens[2];
						all.get(tokens[0]).year = Integer.parseInt(tokens[3]);
					} else {
						Employee tmp = new Employee(tokens[0], tokens[2],
								Integer.parseInt(tokens[3]));
						all.put(tokens[0], tmp);
					}

					if (all.containsKey(tokens[1])) {
						all.get(tokens[0]).boss = all.get(tokens[1]);
						all.get(tokens[1]).subodinary.add(all.get(tokens[0]));
					} else {
						if (tokens[1].equals("NULL")) {
							ceo = tokens[0];

							Employee boss = new Employee(tokens[1]);
							boss.boss = null;
							boss.subodinary.add(all.get(tokens[0]));
							all.put(tokens[1], boss);
						} else {
							Employee boss = new Employee(tokens[1]);
							all.get(tokens[0]).boss = boss;
							boss.subodinary.add(all.get(tokens[0]));
							all.put(tokens[1], boss);
						}
					}
				}

				// traverse recursively
				Employee root = all.get(ceo);
				printEmployee(writer, root);
			}

			writer.close();
			reader.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	
	public static void printEmployee(BufferedWriter writer, Employee e) throws IOException{
		int numberDash = e.getLevel();
		for (int z = 0; z < numberDash; z++) {
			writer.write("-");
		}
		writer.write(e.name + " (" + e.title + ") " + e.year+ "\n");
		if (e.subodinary.size() > 0) {
			
			Collections.sort(e.subodinary, new Comparator<Employee>() {

				@Override
				public int compare(Employee o1, Employee o2) {
					return o1.name.compareTo(o2.name);
				}
			});
			
			for(int a=0; a<e.subodinary.size(); a++){
				printEmployee(writer, e.subodinary.get(a));
			}
		}
	}
}

class Employee {
	/* package */String name;
	/* package */String title;
	/* package */int year;
	/* package */Employee boss;
	/* package */List<Employee> subodinary;

	public Employee() {
		this.name = "";
		this.title = "";
		this.year = 0;
		this.boss = null;
		this.subodinary = new ArrayList<Employee>();
	}

	public Employee(String name) {
		this.name = name;
		this.title = "";
		this.year = 0;
		this.boss = null;
		this.subodinary = new ArrayList<Employee>();
	}

	public Employee(String name, String title, int year) {
		this.name = name;
		this.title = title;
		this.year = year;
		this.boss = null;
		this.subodinary = new ArrayList<Employee>();
	}

	public Employee(String name, String title, int year, Employee boss) {
		this.name = name;
		this.title = title;
		this.year = year;
		this.boss = boss;
		this.subodinary = new ArrayList<Employee>();
	}

	public int getLevel() {
		if (boss == null)
			return 0;
		else
			return boss.getLevel() + 1;
	}

	public String toString() {
		return name + "," + title + "," + year;
	}
}
