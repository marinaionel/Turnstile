package SWE1.turnstile.state_pattern.turnstile.classic.turnstile;

public interface ITurnstileState {
	public void coinInsertedEvent(Turnstile turnstile);
	public void passedEvent(Turnstile turnstile);
}
