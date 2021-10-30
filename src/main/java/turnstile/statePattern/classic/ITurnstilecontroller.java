package turnstile.statePattern.classic;

public interface ITurnstileController {
	void doUnlock();
	void doLock();
	void doAlarm();
	void doReturnCoin();
}
