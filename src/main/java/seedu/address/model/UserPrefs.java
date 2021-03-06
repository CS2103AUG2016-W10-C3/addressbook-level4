package seedu.address.model;

import seedu.address.commons.core.GuiSettings;

import java.awt.GraphicsEnvironment;
import java.awt.Rectangle;
import java.util.Objects;

//@@author A0135767U
/**
 * Represents User's preferences.
 */
public class UserPrefs {
	
    private static final Rectangle primaryScreenBounds = GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds();
    private static final double WINDOW_WIDTH = primaryScreenBounds.getWidth() / 5;
    private static final double WINDOW_HEIGHT = primaryScreenBounds.getHeight();

    public GuiSettings guiSettings;

    public GuiSettings getGuiSettings() {
        return guiSettings == null ? new GuiSettings() : guiSettings;
    }

    public void updateLastUsedGuiSetting(GuiSettings guiSettings) {
        this.guiSettings = guiSettings;
    }

    public UserPrefs(){
    	int x = (int) (primaryScreenBounds.getMinX() + primaryScreenBounds.getMaxX() - WINDOW_WIDTH);
        int y = (int) (primaryScreenBounds.getMinY() + primaryScreenBounds.getMaxY() - WINDOW_HEIGHT);
                
        this.setGuiSettings(WINDOW_WIDTH, WINDOW_HEIGHT, x, y);
    }

    public void setGuiSettings(double width, double height, int x, int y) {
        guiSettings = new GuiSettings(width, height, x, y);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this){
            return true;
        }
        if (!(other instanceof UserPrefs)){ //this handles null as well.
            return false;
        }

        UserPrefs o = (UserPrefs)other;

        return Objects.equals(guiSettings, o.guiSettings);
    }

    @Override
    public int hashCode() {
        return Objects.hash(guiSettings);
    }

    @Override
    public String toString(){
        return guiSettings.toString();
    }

}
