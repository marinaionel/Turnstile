package turnstile.nestedSwitchCase;

public class Turnstile {	
	private EState currentState = EState.LOCKED;
	private ITurnstileController controller;
	
	public Turnstile(ITurnstileController controller) {
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
