package turnstile.statePattern.classic;

public interface ITurnstileState {
	void coinInsertedEvent(Turnstile turnstile);
	void passedEvent(Turnstile turnstile);
}
