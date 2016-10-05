package base;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.Serializable;
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

		System.out.println("title: " + title);
		
		if (pathFolder.length() > 0) {
			pathFolder = pathFolder + File.separator;
		}
			
		File file = new File(pathFolder + title + ".txt");
		System.out.println("file: " + file);

		try{
			FileWriter writer = new FileWriter(file);
			writer.write(content);
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
