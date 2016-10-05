package base;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class TextNote extends Note {

	private String content;
	
	public TextNote(String title){
		super(title);
	}
	
	public TextNote(String title, String content){
		super(title);
		this.content = content;
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
		title = title.replace(" ", "_");
		
		File file = new File(pathFolder + File.pathSeparator + title + ".txt");
		PrintWriter out = null;
		
		try{
			out = new PrintWriter(file);
		    out.println(this.content);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			out.close();
		}
	}
}
