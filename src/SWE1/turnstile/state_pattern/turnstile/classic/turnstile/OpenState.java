package SWE1.turnstile.state_pattern.turnstile.classic.turnstile;

public class OpenState implements ITurnstileState {
	@Override
	public void coinInsertedEvent(Turnstile turnstile) {
		turnstile.doReturnCoin();
	}

	@Override
	public void passedEvent(Turnstile turnstile) {
		turnstile.doLock();
		turnstile.setState(new LockedState());
	}
}
