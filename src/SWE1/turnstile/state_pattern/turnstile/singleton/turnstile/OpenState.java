package SWE1.turnstile.state_pattern.turnstile.singleton.turnstile;

public class OpenState implements ITurnstileState {
	private static final OpenState INSTANCE = new OpenState();
	
	private OpenState() {}
	
	@Override
	public void coinInsertedEvent(Turnstile turnstile) {
		turnstile.doReturnCoin();
	}

	@Override
	public void passedEvent(Turnstile turnstile) {
		turnstile.doLock();
		turnstile.setState(LockedState.getInstance());
	}

	public static ITurnstileState getInstance() {
		return INSTANCE;
	}

}
