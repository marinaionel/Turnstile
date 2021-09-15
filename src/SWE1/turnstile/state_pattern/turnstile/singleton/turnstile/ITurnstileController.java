package SWE1.turnstile.state_pattern.turnstile.singleton.turnstile;

public interface ITurnstileController {
	void doUnlock();
	void doLock();
	void doAlarm();
	void doReturnCoin();
}
