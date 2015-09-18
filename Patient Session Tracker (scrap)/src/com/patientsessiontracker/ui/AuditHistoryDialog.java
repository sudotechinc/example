package com.patientsessiontracker.ui;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import com.patientsessiontracker.core.AuditHistory;
import com.patientsessiontracker.core.Patient;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AuditHistoryDialog extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private final JPanel contentPanel = new JPanel();
	private JTable table;
	private JLabel patientAuditHistoryLabel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			AuditHistoryDialog dialog = new AuditHistoryDialog();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void populate(Patient tempPatient, List<AuditHistory> auditHistoryList) {
		
		patientAuditHistoryLabel.setText(tempPatient.getFirstName() + " " + tempPatient.getLastName());
		
		AuditHistoryTableModel model = new AuditHistoryTableModel(auditHistoryList);
		
		table.setModel(model);
		
		// set up table renderer for date
		TableCellRenderer tableCellRenderer = new DateTimeCellRenderer();
		table.getColumnModel().getColumn(AuditHistoryTableModel.DATE_TIME_COL).setCellRenderer(tableCellRenderer);		
	}
	
	/**
	 * Create the dialog.
	 */
	public AuditHistoryDialog() {
		setTitle("Audit History");
		setModal(true);
		setBounds(100, 100, 651, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		{
			JPanel panel = new JPanel();
			contentPanel.add(panel, BorderLayout.NORTH);
			panel.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
			{
				JLabel lblAuditHistoryFor = new JLabel("Audit History for Patient:");
				panel.add(lblAuditHistoryFor);
			}
			{
				patientAuditHistoryLabel = new JLabel("New label");
				panel.add(patientAuditHistoryLabel);
			}
		}
		{
			JScrollPane scrollPane = new JScrollPane();
			contentPanel.add(scrollPane, BorderLayout.CENTER);
			{
				table = new JTable();
				scrollPane.setViewportView(table);
			}
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						setVisible(false);
						dispose();
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
		}		
	}

	private final class DateTimeCellRenderer extends DefaultTableCellRenderer {

		private static final long serialVersionUID = 1L;
		
		SimpleDateFormat f = new SimpleDateFormat("MM/dd/yy hh:mm:ss");

		public Component getTableCellRendererComponent(JTable table,
		        Object value, boolean isSelected, boolean hasFocus,
		        int row, int column) {
		    if( value instanceof Date) {
		        value = f.format(value);
		    }
		    return super.getTableCellRendererComponent(table, value, isSelected,
		            hasFocus, row, column);
		}
	}
	
}