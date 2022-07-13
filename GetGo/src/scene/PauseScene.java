package scene;

import javax.swing.JPanel;
import java.awt.GridLayout;
import javax.swing.JButton;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.FlowLayout;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.layout.FormSpecs;
import javax.swing.JLabel;

public class PauseScene extends JPanel {
	
	public Container container;
	/**
	 * Create the panel.
	 */
	public PauseScene(Container container) {
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0};
		gridBagLayout.rowHeights = new int[]{0};
		gridBagLayout.columnWeights = new double[]{Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{Double.MIN_VALUE};
		setLayout(gridBagLayout);
		setUp();
	}
	
	public void setUp() {
		
//		JButton resume = new JButton("Resume");
//		GridBagConstraints gbc_resume = new GridBagConstraints();
//		gbc_resume.fill = GridBagConstraints.BOTH;
//		gbc_resume.insets = new Insets(0, 0, 0, 5);
//		gbc_resume.gridx = 3;
//		gbc_resume.gridy = 0;
//		add(resume, gbc_resume);
//		
//		JButton home = new JButton("Home");
//		GridBagConstraints gbc_home = new GridBagConstraints();
//		gbc_home.insets = new Insets(0, 0, 0, 5);
//		gbc_home.fill = GridBagConstraints.BOTH;
//		gbc_home.gridx = 5;
//		gbc_home.gridy = 0;
//		add(home, gbc_home);

	}

}
