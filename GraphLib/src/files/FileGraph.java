package files;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.LinkedList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FileGraph {
	

	public int graphSize;
	
	public LinkedList<EntryFile> graphRead(String path) {
		BufferedReader br = null;
		 
		LinkedList<EntryFile> list = new LinkedList<EntryFile>();
		try {
 
			String sCurrentLine;
 
			br = new BufferedReader(new FileReader(path));
 
			while ((sCurrentLine = br.readLine()) != null) {
				sCurrentLine = sCurrentLine.replace(" ", "");
//				System.out.println(sCurrentLine);
				String [] s = sCurrentLine.split(";");
				EntryFile entry = new EntryFile(Integer.parseInt(s[0]),Integer.parseInt(s[1]),Integer.parseInt(s[2]));
				list.add(entry);
			
			}
		this.graphSize = list.size();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (br != null)br.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
		return list;
	}

}
