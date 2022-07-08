package selient;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Color;

public class Dialog extends JDialog {

	public JLabel lblMessage;
	
	/**
	 * Create the dialog.
	 */
	public Dialog() {
		setTitle("Space for Title");
		getContentPane().setBackground(Color.WHITE);
		setBounds(50, 50, 450, 300);
		
		lblMessage = new JLabel("Space for Message");
		lblMessage.setBackground(Color.PINK);
		lblMessage.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblMessage.setHorizontalAlignment(SwingConstants.CENTER);
		getContentPane().add(lblMessage, BorderLayout.CENTER);
//		{
//			JPanel buttonPane = new JPanel();
//			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
//			getContentPane().add(buttonPane, BorderLayout.SOUTH);
//			{
//				JButton okButton = new JButton("OK");
//				okButton.setActionCommand("OK");
//				buttonPane.add(okButton);
//				getRootPane().setDefaultButton(okButton);
//			}
//			{
//				JButton cancelButton = new JButton("Cancel");
//				cancelButton.setActionCommand("Cancel");
//				buttonPane.add(cancelButton);
//			}
//		}
	}

}
