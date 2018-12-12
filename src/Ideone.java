/* package whatever; // don't place package name! */
 
import java.util.*;
import java.lang.*;
import java.io.*;
import java.util.regex.Pattern;
 
/* Name of the class has to be "Main" only if the class is public. */
class Ideone
{
	public static void main (String[] args) throws java.lang.Exception
	{
		String strFileContent = "Server1, Database, MySQL, 5.5\nServer2, Database, MySQL, 5.1\nServer3, OS, Ubuntu, 10.04\nServer1, Language, Python, 3.4\nServer1, OS, Ubuntu, 12.04\nServer2, OS, Ubuntu, 12.04\nServer3, Language, Python, 2.6.3";
		HashMap<String,HashMap<String,String>> serverInfo = new HashMap<String,HashMap<String,String>>();
		HashMap<String,String> latestVersion = new HashMap<String,String>();
		String[] arrInputFileRows = strFileContent.split("\n");
		Scanner sc1 = new Scanner("serverList.txt");
		Scanner sc;
		
		try {
			sc = new Scanner(new FileInputStream("serverList.txt"));
			
			while (sc.hasNextLine()) {
				String strRow = sc.nextLine();
				System.out.println(strRow);
				
		
		
		
      		String[] arrRowData = strRow.split(",");
      		String strKey = arrRowData[1].trim().toLowerCase()+'_'+arrRowData[2].trim().toLowerCase();
      		if (serverInfo.containsKey(arrRowData[0])){
      			serverInfo.get(arrRowData[0]).put(strKey,arrRowData[3].trim());
      		}
      		else{
      			HashMap<String,String> swInfo = new HashMap<String,String>();
      			swInfo.put(strKey,arrRowData[3].trim());
      			serverInfo.put(arrRowData[0],swInfo);
      		}
      		//version comparison to get latest software version 
      		if (latestVersion.containsKey(strKey) && isGreaterVersion(arrRowData[3],latestVersion.get(strKey))){
      			latestVersion.put(strKey,arrRowData[3].trim());
      		}
      		else if (!latestVersion.containsKey(strKey)){
      			latestVersion.put(strKey,arrRowData[3].trim());
      		}
      	}
      	System.out.println("Server Info: " + serverInfo + "\n\nLatest Version: "+latestVersion);
 
      	
		
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
 		//TODO: iterate over map and output servers having lower version software
 
	}
 
	private static Boolean isGreaterVersion(String v1, String v2) {
        String s1 = normalisedVersion(v1);
        String s2 = normalisedVersion(v2);
        int cmp = s1.compareTo(s2);
        Boolean result = cmp > 0 ? true : false;
        //System.out.printf("'%s' %s '%s'%n", v1, result, v2);
        return result;
    }
 
    public static String normalisedVersion(String version) {
        String[] split = Pattern.compile(".", Pattern.LITERAL).split(version);
        StringBuilder sb = new StringBuilder();
        for (String s : split) {
            sb.append(String.format("%3s", s));
        }
        return sb.toString();
    }
}