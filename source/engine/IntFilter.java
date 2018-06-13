package engine;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.JFormattedTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;

    public class IntFilter extends DocumentFilter {

	@Override
	public void insertString(DocumentFilter.FilterBypass fb,int offset,String text,AttributeSet attr) throws BadLocationException{
		super.insertString(fb, offset, text, attr);
	}
	
	public void replace(DocumentFilter.FilterBypass fb,int offset,int length,String text,AttributeSet attrs)throws BadLocationException {
		String[] chars = text.split("");
		for(int i = 0; i < chars.length; i++) {
		if(!chars[i].matches("[1234567890]")){
			System.out.println("Invalid Input!");
			return;
		}	
		}
		
		int val;
		if(text.length() == 1) {
		StringBuilder sb = new StringBuilder(fb.getDocument().getText(0, fb.getDocument().getLength()));
		sb.insert(offset, text);
			val =  Integer.parseInt(sb.toString());
		}
		else {
			val = Integer.parseInt(text);
		}
		
		if(val > (int)fb.getDocument().getProperty("max")){
			System.out.println("Invalid value!");
			return;
		}
		super.replace(fb, offset, length, text, attrs);
	}	
	
	public void remove(DocumentFilter.FilterBypass fb,int offset,int length)throws BadLocationException{
//		StringBuilder sb = new StringBuilder(fb.getDocument().getText(0, fb.getDocument().getLength()));
//		sb.deleteCharAt(offset);
//		int val = Integer.parseInt(sb.toString());
//		if(val > (int)fb.getDocument().getProperty("max") || val < (int)fb.getDocument().getProperty("min")){
//			System.out.println("Invalid value!");
//			return;
//		}

		super.remove(fb, offset, length);
	}
	
}
