package GUI;

import java.io.IOException;

import com.almasb.fxgl.app.scene.FXGLMenu;
import com.almasb.fxgl.app.scene.MenuType;
import com.almasb.fxgl.app.scene.SceneFactory;

public class MySceneFactory extends SceneFactory {
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
