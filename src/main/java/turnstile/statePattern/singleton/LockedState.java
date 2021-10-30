package turnstile.statePattern.singleton;

public class LockedState implements ITurnstileState {
	private static final LockedState INSTANCE = new LockedState();
	
	private LockedState() {}
	
	@Override
	public void coinInsertedEvent(Turnstile turnstile) {
		turnstile.doUnlock();
		turnstile.setState(OpenState.getInstance());
	}

	@Override
	public void passedEvent(Turnstile turnstile) {
		turnstile.doAlarm();
	}

	public static ITurnstileState getInstance() {
		return INSTANCE;
	}
}
