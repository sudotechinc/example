package com.patientsessiontracker.core;

import java.awt.Component;
import javax.swing.JOptionPane;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.DocumentFilter;

public class IntegerFilter extends DocumentFilter {
	
	private Component integerFilter;
	
	@Override
	public void insertString(FilterBypass fb, int offset, String str, AttributeSet attr) throws BadLocationException {
		
		Document doc = fb.getDocument();
		StringBuilder sb = new StringBuilder();
		sb.append(doc.getText(0,  doc.getLength()));
		sb.insert(offset, str);
		
		if(test(sb.toString())) {
			super.insertString(fb, offset, str, attr);
		} else {
			JOptionPane.showMessageDialog(integerFilter, "Only digits 0 through 9 are allowed!");
		}
	}
	
	private boolean test(String text) {
		try {
			Integer.parseInt(text);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}
	
	@Override
	public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException {
		
		Document doc = fb.getDocument();
		StringBuilder sb = new StringBuilder();
		sb.append(doc.getText(0, doc.getLength()));
		sb.replace(offset, offset + length, text);
		
		if(test(sb.toString())) {
			super.replace(fb, offset, length, text, attrs);
		} else {
			JOptionPane.showMessageDialog(integerFilter, "Only digits 0 through 9 are allowed!");
		}
	}
	
	@Override
	public void remove(FilterBypass fb, int offset, int length) throws BadLocationException {
		
		Document doc = fb.getDocument();
		StringBuilder sb = new StringBuilder();
		sb.append(doc.getText(0, doc.getLength()));
		sb.delete(offset, offset + length);
		
		if(test(sb.toString())) {
			super.remove(fb, offset, length);
		} else {
			JOptionPane.showMessageDialog(integerFilter, "Only digits 0 through 9 are allowed!");
		}
	}
}
