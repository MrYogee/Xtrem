
	
	import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.function.BiConsumer;

	public class FindOutdatedServer {
		
		private static Map<String, Object[]> techSoftwareMap = new HashMap<>();
		private static Set<String> setOfServer = new HashSet();
		public static void main(String[] args) {
			readfile();
			
			techSoftwareMap.values().forEach(val -> 
									{List<String> serverList = (List<String>) val[1];
									setOfServer.addAll(serverList);
									});
			
			//PrintResult
			setOfServer.forEach(server -> System.out.println(server));
			
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
			String key = technology.trim()+software.trim();
			Object[] softwareVsVersionMap = techSoftwareMap.get(key);
			if(softwareVsVersionMap == null) {
				softwareVsVersionMap = new Object[2];
			}
			String oldestVersion = (String) softwareVsVersionMap[0];
			List<String> serverList = (List<String>) softwareVsVersionMap[1];
			if(oldestVersion != null) {
				double diff = isNewVersionIsMin(oldestVersion, version);
				if(diff > 0) {
					softwareVsVersionMap[0]= version;
					serverList.clear();
					serverList.add(server);
//					setOfServer.remove(minVersionandServer[1]);
				}
				else if(diff == 0){
					serverList.add(server);
				}
			}else {
				softwareVsVersionMap[0] = version;
				serverList =new ArrayList<>();
				serverList.add(server);
				softwareVsVersionMap[1]= serverList;
			}
			techSoftwareMap.put(key, softwareVsVersionMap);

			
		}
		private static double isNewVersionIsMin(String oldVersion, String newVersion) {
			return Double.parseDouble(oldVersion) - Double.parseDouble(newVersion);
		}
			

	}


