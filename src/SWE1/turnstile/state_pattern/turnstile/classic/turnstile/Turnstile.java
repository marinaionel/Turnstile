package SWE1.turnstile.state_pattern.turnstile.classic.turnstile;


public class Turnstile {
	private ITurnstileState state = null;
	
	private ITurnstilecontroller controller = null;
	
	public Turnstile(ITurnstilecontroller controller) {
		this.controller = controller;
		setState(new LockedState());
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
