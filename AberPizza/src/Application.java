import javax.swing.UIManager;
import uk.ac.aber.dcs.cs12420.aberpizza.gui.*;

/**
 * Contains main() method, makes application runnable.
 * @author Stanislaw Klimaszewski
 *
 */
public class Application {
	public static void main(String[] args) {
		try{
		UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
		}catch (Exception e){
		
		}
		new MainWindow();
	}
}
