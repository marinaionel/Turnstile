package turnstile.statePattern.classic;

public class LockedState implements ITurnstileState {
	@Override
	public void coinInsertedEvent(Turnstile turnstile) {
		turnstile.doUnlock();
		turnstile.setState(new OpenState());
	}

	@Override
	public void passedEvent(Turnstile turnstile) {
		turnstile.doAlarm();
	}
}
