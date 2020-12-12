package GUI;

import java.io.IOException;

import com.almasb.fxgl.app.scene.FXGLMenu;
import com.almasb.fxgl.app.scene.MenuType;
import javafx.beans.binding.StringBinding;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

/**
 * Cette classe permet de créer les menus du jeu Elle est composé de son layout,
 * un AnchorPane
 * 
 * @author Gaël
 *
 */
public class MyMenu extends FXGLMenu {
	static AnchorPane layout;

	/**
	 * Constructeur permettant de récupérer le type du menu et de créer le menu
	 * selon le type récupérer.
	 * 
	 * @author Gaël
	 * @param type Le type du menu
	 * @throws IOException : Si il y a un problème pour charger notre FXML
	 *                     (graphique)
	 */
	public MyMenu(MenuType type) throws IOException {
		super(type);
		if (type == MenuType.MAIN_MENU) {
			loadMainMenuLayout();

		} else {
			loadGameMenuLayout();
		}

	}

	/**
	 * Initialise le menu principal
	 * 
	 * @throws IOException : Si il y a un problème pour charger notre FXML
	 *                     (graphique)
	 * @author Gaël
	 */
	public void loadMainMenuLayout() throws IOException {
		FXMLLoader loader = new FXMLLoader(); // FXMLLoader nous permet de charger notre FXML
		loader.setLocation(getClass().getResource("MainMenu.fxml")); // On charge notre FXML
		layout = loader.<AnchorPane>load(); // On set le layout à ce FXML
		getContentRoot().getChildren().add(layout); // Puis on ajoute ce layout au jeu pour afficher le menu
	}

	/**
	 * Initialise le menu pause du jeu
	 * 
	 * @throws IOException : Si il y a un problème pour charger notre FXML
	 *                     (graphique)
	 * @author Gaël
	 */
	public void loadGameMenuLayout() throws IOException {
		FXMLLoader loader = new FXMLLoader();// FXMLLoader nous permet de charger notre FXML
		loader.setLocation(getClass().getResource("GameMenu.fxml"));// On charge notre FXML
		layout = loader.<AnchorPane>load();// On set le layout à ce FXML
		getContentRoot().getChildren().add(layout); // Puis on ajoute ce layout au jeu pour afficher le menu
	}

	/*
	 * Nous n'utilisons pas ces méthodes mais elles sont obligatoirement présente
	 * pour faire fonctionner le extends
	 */
	@Override
	protected Button createActionButton(String name, Runnable action) {
		return new Button(name);
	}

	@Override
	protected Button createActionButton(StringBinding name, Runnable action) {
		return new Button(name.get());
	}

	@Override
	protected Node createBackground(double width, double height) {
		return new Rectangle(width, height, Color.BLACK);
	}

	protected Node createTitleView(String title) {
		return new Text(title);
	}

	@Override
	protected Node createVersionView(String version) {
		return new Text(version);
	}

	@Override
	protected Node createProfileView(String profileName) {
		return new Text(profileName);
	}

}
