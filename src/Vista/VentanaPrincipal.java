package Vista;
import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;

public class VentanaPrincipal {

	public JFrame frame;

	
	/**
	 * Create the application.
	 */
	public VentanaPrincipal() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 720, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JButton btnIniciarMonitoreo = new JButton("Iniciar Monitoreo");
		btnIniciarMonitoreo.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				
				/*VentanaMonitoreo ventana = new VentanaMonitoreo();
				ventana.setVisible(true);
				*/
			}
		});
		btnIniciarMonitoreo.setBounds(80, 93, 139, 53);
		frame.getContentPane().add(btnIniciarMonitoreo);
		
		JDesktopPane desktopPane = new JDesktopPane();
		desktopPane.setBounds(10, 394, 522, -382);
		frame.getContentPane().add(desktopPane);
	}
}
