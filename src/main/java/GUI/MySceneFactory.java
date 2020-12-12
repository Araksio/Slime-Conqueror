package GUI;

import java.io.IOException;

import com.almasb.fxgl.app.scene.FXGLMenu;
import com.almasb.fxgl.app.scene.MenuType;
import com.almasb.fxgl.app.scene.SceneFactory;

/**
 * MySceneFactory permets de retourner le menu et ainsi de l'afficher
 * 
 * @author Gaël
 *
 */
public class MySceneFactory extends SceneFactory {
	/**
	 * Retourne le menu principal
	 * 
	 * @author Gaël
	 */
	@Override
	public FXGLMenu newMainMenu() {
		try {
			return new MyMenu(MenuType.MAIN_MENU);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * Retourne le menu de pause
	 * 
	 * @author Gaël
	 */
	@Override
	public FXGLMenu newGameMenu() {
		try {
			return new MyMenu(MenuType.GAME_MENU);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
