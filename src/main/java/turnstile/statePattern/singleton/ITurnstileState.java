package turnstile.statePattern.singleton;

public interface ITurnstileState {
	void coinInsertedEvent(Turnstile turnstile);
	void passedEvent(Turnstile turnstile);
}
