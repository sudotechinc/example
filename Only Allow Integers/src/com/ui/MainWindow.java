package com.ui;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.text.PlainDocument;

import com.core.*;

public class MainWindow {
   public static void main(String[] args) {
      JTextField textField = new JTextField(10);

      JPanel panel = new JPanel();
      panel.add(textField);

      PlainDocument doc = (PlainDocument) textField.getDocument();
      doc.setDocumentFilter(new MyIntFilter());


      JOptionPane.showMessageDialog(null, panel);
   }
}

