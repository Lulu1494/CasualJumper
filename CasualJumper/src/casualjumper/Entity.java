package casualjumper;

public class Entity {

    private boolean enabled = false;

    public void enable() {
	enabled = true;
    }

    public void disable() {
	enabled = false;
    }

    public boolean isEnabled() {
	return enabled;
    }

    public void update() {
    }
}
