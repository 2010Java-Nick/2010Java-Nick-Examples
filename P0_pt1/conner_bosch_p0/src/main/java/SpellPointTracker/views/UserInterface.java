package SpellPointTracker.views;

/**
 * Handles communication between the user and the controller
 */
public interface UserInterface {
    
    public boolean startInterface();

    public boolean promptAction();

    public void endInterface();
}
