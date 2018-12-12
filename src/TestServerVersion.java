import java.awt.RenderingHints.Key;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class TestServerVersion {
	
	private static Map<String, Map<String, String[]>> techSoftwareMap = new HashMap<>();
	private static Set<String> setOfServer = new HashSet();
	public static void main(String[] args) {
		readfile();
		//PrintResult
		setOfServer.forEach(server -> System.out.println(server));
		/*techSoftwareMap.keySet().forEach(key -> {
										Map<String, String[]> innerMap = techSoftwareMap.get(key);
										innerMap.keySet().forEach(val -> 
										{String[] minVersion = innerMap.get(val);
										System.out.println(minVersion[1]);
										});
		
										});*/
	}
	
	private static void readfile() {
		Scanner sc1 = new Scanner("serverList.txt");
		Scanner sc;
		
		try {
			sc = new Scanner(new FileInputStream("serverList.txt"));
			
			while (sc.hasNextLine()) {
				String line = sc.nextLine();
				System.out.println(line);
				processLine(line);
				
			}
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		

	}
	private static void processLine(String line) {
		String[] splitArray = line.split(",");
		String server = splitArray[0];
		String technology = splitArray[1];
		String software = splitArray[2];
		String version = splitArray[3];

		Map<String, String[]> softwareVsVersionMap = techSoftwareMap.get(technology);
		if(softwareVsVersionMap == null) {
			softwareVsVersionMap = new HashMap<>();
		}
		String[] minVersionandServer = softwareVsVersionMap.get(software);
		if(minVersionandServer != null) {
			double diff = isNewVersionIsMin( minVersionandServer[0], version);
			if(diff > 0) {
				minVersionandServer[0]= version;
				minVersionandServer[1] =server;
				setOfServer.remove(minVersionandServer[1]);
			}
			else if(diff == 0){
				
			}
		}else {
			minVersionandServer = new String[2];
			minVersionandServer[0]= version;
			minVersionandServer[1] =server;
			setOfServer.add(server);
		}
		softwareVsVersionMap.put(software, minVersionandServer);
		techSoftwareMap.put(technology, softwareVsVersionMap);
		
	}
	private static double isNewVersionIsMin(String oldVersion, String newVersion) {
		return Double.parseDouble(oldVersion) - Double.parseDouble(newVersion);
	}
		

}
