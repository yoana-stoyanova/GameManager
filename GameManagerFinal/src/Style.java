import javax.swing.*;
import java.awt.*;

public class Style {
    Style(){};

    public void addStyle(){
        UIManager.put("Label.foreground", Color.GRAY);

        UIManager.put("TextArea.background", Color.BLACK);
        UIManager.put("TextArea.foreground", Color.GRAY);

        UIManager.put("Panel.background", Color.BLACK);
        UIManager.put("Panel.foreground", Color.GRAY);

        UIManager.put("Button.background", Color.GRAY);
        UIManager.put("Button.foreground", Color.BLACK);
        UIManager.put("Button.border", BorderFactory.createLineBorder(Color.BLACK, 2));
    }

}
