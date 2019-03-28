import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Hashtable;
import java.util.Map;

public class Manager {
	
	/**
	 * reading all lines of input file and adding to an arraylist
	 * 
	 * @throws IOException  If an input exception occoured
	 * @see java.io.IOException
	 * @see java.nio.file.Files
	 * @see java.nio.file.Paths
	 * 
	 * @param path		string that holds the path of target input file
	 * @return			returning an arraylist that holds lines of the input file
	 */
	
	public static String[] readFile(String path){
		try{
			int i = 0;
			int lenght = Files.readAllLines(Paths.get(path)).size();
			String[] results = new String[lenght];
			
			for (String line:Files.readAllLines(Paths.get(path))) {
				results[i++] = line;
			}
			
			return results;
			
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static PrintWriter openFile(String outFile_name){
		File output = new File(outFile_name);
		try{
			PrintWriter outWriter = new PrintWriter(output);
			return outWriter;
		}catch(IOException e){
			e.printStackTrace();
			return null;
		}
	}
	
	public static void printingHashMap (Map<String, node> node_Map){
		for (Map.Entry<String,node> entry : node_Map.entrySet()) {
			  String key = entry.getKey();
			  String value = entry.getValue().getWord();
			  System.out.println(value + " ----- " + key);
			}
	}
	
	public static void printingHashTable(Hashtable<String, Integer> table){
		for (Map.Entry<String, Integer> entry : table.entrySet()) {
		    System.out.println(entry.getKey()+" : "+entry.getValue());
		}
	}
	
		
	public static void preparingCorpus(File[] file_List,Map<String, node> node_Map,Hashtable<String, Integer> posTag_Table){
		try{
			
			for(File single_file : file_List){
				if(single_file.isFile()){
					String line = null;
					BufferedReader buff = new BufferedReader(new FileReader(single_file));
					while( (line = buff.readLine()) != null){
						if( line.length() > 1 ){
							
							String token = line.substring(0, 1);				/*That code checks if there is a space or tab*/	
							if( token.equals("	"))	{							/*if yes, then extracting that space or tab from corpus*/
								line = line.substring(1, line.length());
							}
														
							String[] splittedLine = line.split(" ");					/*I put all characters to string array*/

							
							String word_Group, word, pos_Tag = null;
							for(int iter=0; iter<splittedLine.length-1;iter++){	
								
									word_Group = splittedLine[iter].replaceAll("\\/", " \\/ ");
									String[] node_Parts = word_Group.split(" ");
																		
									if(node_Parts.length == 3){
										word = node_Parts[0];
										pos_Tag = node_Parts[2];
										
										Integer count = posTag_Table.get(pos_Tag);
										posTag_Table.put(pos_Tag, (count == null) ? 1 : count + 1);
										
										node current_Node = new node(word, pos_Tag);	
										node_Map.put(word, current_Node);	
									}
							}								
						}
					}
					buff.close();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void task3 (Map<String, node> node_Map,Hashtable<String, Integer> posTag_Table,String[] inputTokens,PrintWriter outWriter){
		for(String input_Line : inputTokens){
			String[] line_Parts = input_Line.split(" ");
			for(String linePart : line_Parts){
				node current_node = node_Map.get(linePart);
				int tag_Count = posTag_Table.get(current_node.getPos_Tag());
				System.out.print(current_node.getPos_Tag() + ":" + tag_Count + "  ");
				outWriter.print(current_node.getWord() + "/" + current_node.getPos_Tag() + "     ");
			}
		}
	}
	
}
