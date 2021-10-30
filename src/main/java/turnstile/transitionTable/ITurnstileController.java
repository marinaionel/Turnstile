package turnstile.transitionTable;

public interface ITurnstileController {
	public void doUnlock();
	public void doLock();
	public void doAlarm();
	public void doReturnCoin();
}
