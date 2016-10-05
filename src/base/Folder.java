package base;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Folder implements Comparable<Folder>, Serializable{

	private static final long serialVersionUID = 1L;
	
	private ArrayList<Note> notes;
	private String name;
	
	public Folder(String name) {
		this.name = name;
		notes = new ArrayList<Note>();
	}
	
	public void addNote(Note note) {
		notes.add(note);
	}
	
	public String getName() {
		return this.name;
	}
	
	public ArrayList<Note> getNotes() {
		return this.notes;
	}

	@Override
	public String toString() {
		int nText = 0;
		int nImage = 0;
		
		for (Note note: this.notes) {
			if (note instanceof ImageNote) {
				nImage++;
			} else if (note instanceof TextNote) {
				nText++;
			}
		}
		
		return this.name + ":" + nText + ":" + nImage;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Folder other = (Folder) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
	
	@Override
	public int compareTo(Folder o) {
		return this.name.compareTo(o.name);
	}
	
	public void sortNotes() {
		Collections.sort(notes);
	}
	
	public List<Note> searchNotes(String keywords) {
		
		// Process Keywords
		String[] keywordsArray = keywords.split(" ");
		ArrayList<String> processedKeywordsArray = new ArrayList<String>();
		
		String tempString = "";
		boolean orIndicator = false;
		for (String keyword: keywordsArray) {
			if (keyword.equalsIgnoreCase("or")) {
				orIndicator = true;
			} else {
				if (orIndicator) {
					tempString = tempString + " " + keyword;
					orIndicator = false;
				} else {
					if (!tempString.equals("")) {
						processedKeywordsArray.add(tempString.toLowerCase());
					}
					tempString = keyword;
				}
			}
		}
		processedKeywordsArray.add(tempString.toLowerCase());
		
		// Find Notes That Contains the Keywords
		ArrayList<Note> matchedNotes = new ArrayList<Note>();
	
		for (Note note: notes) {
			if (note instanceof ImageNote) {
				if (isStringContentContainsKeywords(note.getTitle(), processedKeywordsArray)) {
					matchedNotes.add(note);
				}
			} else if (note instanceof TextNote) {
				if (isStringContentContainsKeywords(note.getTitle(), processedKeywordsArray) || isStringContentContainsKeywords(((TextNote)note).getContent(), processedKeywordsArray)) {
					matchedNotes.add(note);
				}
			}
		}
		
		// Return Result
		return matchedNotes;
	}
	
	private boolean isStringContentContainsKeywords(String content, ArrayList<String> keywords) {
		
		content = content.toLowerCase();
		
		boolean result = true;
		
		for (String keyword: keywords) {
			if (keyword.contains(" ")) {
				String[] keywordsArray = keyword.split(" ");
				
				result = false;
				for (String orKeyword: keywordsArray) {
					if (content.contains(orKeyword)) {
						result = true;
					}
				}
				
			} else {
				result = content.contains(keyword);
			}
			
			if (!result) {
				break;
			}
		}
		
		return result;
	}
}
