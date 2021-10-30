package turnstile.statePattern.singleton;

public interface ITurnstileController {
	void doUnlock();
	void doLock();
	void doAlarm();
	void doReturnCoin();
}
