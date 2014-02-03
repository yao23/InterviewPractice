import java.io.*;

public class Sum
{
    public static void main(String[] args) throws IOException
    {
        BufferedReader reader = new BufferedReader(new FileReader("bin/sum.in"));
        BufferedWriter writer = new BufferedWriter(new FileWriter("sum_final_solution_java.out"));
        
        int numCases = Integer.parseInt(reader.readLine());
        
        for (int caseNum = 1; caseNum <= numCases; caseNum++)
        {
            String[] toks = reader.readLine().split(" ");
            int x = Integer.parseInt(toks[0]);
            int y = Integer.parseInt(toks[1]);
            int sum = x + y;
            
            writer.write("Case #" + caseNum + "\n");
            writer.write(sum + "\n");
        }
        
        writer.close();
        reader.close();
    }
}