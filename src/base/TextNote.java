package base;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class TextNote extends Note implements Serializable {

	private String content;
	private static final long serialVersionUID = 1L;
	
	public TextNote(String title){
		super(title);
	}
	
	public TextNote(String title, String content){
		super(title);
		this.content = content;
	}
	
	public String getContent() {
		return this.content;
	}
	
	public TextNote(File f){
		super(f.getName());
		this.content = getTextFromFile(f.getAbsolutePath());
	}
	
	private String getTextFromFile(String absolutePath) {
		String result = "";
		
		Scanner s = null;

        try {
            s = new Scanner(new BufferedReader(new FileReader(absolutePath)));
            while (s.hasNext()) {
                result = result + s.nextLine();
            }
        } catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
            if (s != null) {
                s.close();
            }
        }
		return result;
	}
	
	public void exportTextToFile(String pathFolder) {
		String title = this.getTitle();
		title = title.replaceAll(" ", "_");

		if (pathFolder.length() > 0) {
			pathFolder = pathFolder + File.separator;
		}

		File file = new File(pathFolder + title + ".txt");

		try{
			FileWriter writer = new FileWriter(file);
			writer.write(content);
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public Character countLetters(){
		HashMap<Character,Integer> count = new HashMap<Character,Integer>();
		String a = this.getTitle() + this.getContent();
		int b = 0;
		Character r = ' ';
		for (int i = 0; i < a.length(); i++) {
			Character c = a.charAt(i);
			if (c <= 'Z' && c >= 'A' || c <= 'z' && c >= 'a') {
				if (!count.containsKey(c)) {
					count.put(c, 1);
				} else {
					count.put(c, count.get(c) + 1);
					if (count.get(c) > b) {
						b = count.get(c);
						r = c;
					}
				}
			}
		}
		return r;
	}

}
