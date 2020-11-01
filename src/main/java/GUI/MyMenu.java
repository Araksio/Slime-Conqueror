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

//Create Menu and Layout
public class MyMenu extends FXGLMenu {
	static AnchorPane layout;
    public MyMenu(MenuType type) throws IOException{
        super(type);
        if(type == MenuType.MAIN_MENU)
        {
        	loadMainMenuLayout();
        }
        else
        {
        	loadGameMenuLayout();
        }
       
       
       
    }
    
    public void loadMainMenuLayout() throws IOException
    {
    	FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("MainMenu.fxml"));
		layout = loader.<AnchorPane>load();
	    getContentRoot().getChildren().add(layout);  	  
    }

    public void loadGameMenuLayout() throws IOException
    {
    	FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("GameMenu.fxml"));
		layout = loader.<AnchorPane>load();
	    getContentRoot().getChildren().add(layout);  	  
    }


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


