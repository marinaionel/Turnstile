package SWE1.turnstile.nested_switch_case;

public class Turnstile {	
	private EState currentState = EState.LOCKED;
	private ITurnstilecontroller controller;
	
	public Turnstile(ITurnstilecontroller controller) {
		this.controller = controller;
		controller.doLock();
	}
	
	public void event(ETurnstileEvent event) {
		switch (currentState) {
		case LOCKED:
			switch (event) {
			case COIN:
				controller.doUnlock();
				currentState = EState.OPEN;
				break;
				
			case PASS:
				controller.doAlarm();
				break;
			}
			break;
			
		case OPEN:
			switch (event) {
			case COIN:
				controller.doReturnCoin();
				break;
				
			case PASS:
				controller.doLock();
				currentState = EState.LOCKED;
				break;
			}
			
			break;
		}
	}
	
	private enum EState {
		OPEN,
		LOCKED
	}
}
