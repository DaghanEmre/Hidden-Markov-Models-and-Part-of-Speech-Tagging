import java.io.File;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

public class Main {

	public static void main(String[] args) {
		Map<String, node> node_Map = new HashMap<String, node>();
		Hashtable<String, Integer> posTag_Table = new Hashtable<String,Integer>();
		
		try {
			File folder = new File(args[0]);		/*You need to write "brown" as a program argument*/
			String[] inputTokens = Manager.readFile(args[1]);
			PrintWriter outWriter = Manager.openFile("output_tokens.txt");
			File[] file_List = folder.listFiles();
			Manager.preparingCorpus(file_List,node_Map,posTag_Table);
			Manager.printingHashMap(node_Map);
			Manager.task3(node_Map, posTag_Table, inputTokens, outWriter);
			
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
