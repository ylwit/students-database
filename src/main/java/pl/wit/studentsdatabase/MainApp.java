package pl.wit.studentsdatabase;

import javax.swing.SwingUtilities;

import pl.wit.studentsdatabase.view.MainView;

/**
 * Główna klasa do uruchamiania aplikacji.
 * 
 * 
 * @author Yuliia Loianich
 * 
 */
public class MainApp {

	public static void main(String[] args) {
        // Uruchomienie aplikacji Swing w kontekście oddzielnego wątku
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                // Tworzenie instancji głównego okna aplikacji
                MainView app = new MainView();
                // Ustawienie okna jako widoczne
                app.setVisible(true);
            }
        });
	}

}
