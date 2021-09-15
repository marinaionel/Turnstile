package SWE1.turnstile.state_pattern.turnstile.classic.turnstile;

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
