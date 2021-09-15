package SWE1.turnstile.state_pattern.turnstile.singleton.turnstile;

public interface ITurnstileState {
	void coinInsertedEvent(Turnstile turnstile);
	void passedEvent(Turnstile turnstile);
}
