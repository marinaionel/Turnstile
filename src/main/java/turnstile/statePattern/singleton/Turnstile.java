package turnstile.statePattern.singleton;

public class Turnstile {
	private ITurnstileState state = null;
	
	private ITurnstileController controller = null;
	
	public Turnstile(ITurnstileController controller) {
		this.controller = controller;
		setState(LockedState.getInstance());
		doLock();
	}
	
	// Set state
	void setState(ITurnstileState newState) {
		state = newState;
	}
	
	// Handle external events
	public void coinInsertedEvent() {
		state.coinInsertedEvent(this);
	}
	
	public void passedEvent() {
		state.passedEvent(this);
	}
	
	// Actions
	void doLock() {
		controller.doLock();
	}
	
	void doUnlock() {
		controller.doUnlock();
	}	
	
	void doAlarm() {
		controller.doAlarm();
	}	
	
	void doReturnCoin() {
		controller.doReturnCoin();
	}
}