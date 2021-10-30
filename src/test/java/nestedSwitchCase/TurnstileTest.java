package nestedSwitchCase;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import turnstile.nestedSwitchCase.ETurnstileEvent;
import turnstile.nestedSwitchCase.Turnstile;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class TurnstileTest {
	private class TurnstileControllerMock implements turnstile.nestedSwitchCase.ITurnstileController {
		boolean doUnlockCalled;
		boolean doLockCalled;
		boolean doAlarmCalled;
		boolean doReturnCoinCalled;
		
		@Override
		public void doUnlock() {
			doUnlockCalled = true;
		}

		@Override
		public void doLock() {
			doLockCalled = true;
		}

		@Override
		public void doAlarm() {
			doAlarmCalled = true;
		}

		@Override
		public void doReturnCoin() {
			doReturnCoinCalled = true;
		}
	}

	private TurnstileControllerMock controller;
	private Turnstile sut;
	
	@BeforeAll
	public void setUp() throws Exception {
		controller = new TurnstileControllerMock();
		sut = new Turnstile(controller);
	}

	@Test
	public void testDoLockCalledWhenCreated() {
		assertTrue(controller.doLockCalled);
	}

	@Test
	public void testDoUnlockCalledWhenCoinInserted() {
		sut.event(ETurnstileEvent.COIN);
		assertTrue(controller.doUnlockCalled);
	}
	
	@Test
	public void testDoAlarmCalledWhenPassWithoutCoinInserted() {
		sut.event(ETurnstileEvent.PASS);
		assertTrue(controller.doAlarmCalled);
	}
	
	@Test
	public void testDoReturnCoinCalledWhenTwoCoinsInserted() {
		sut.event(ETurnstileEvent.COIN);
		sut.event(ETurnstileEvent.COIN);
		assertTrue(controller.doReturnCoinCalled);
	}
	
	@Test
	public void testDoLockCalledWhenCoinInsertedAndPass() {
		sut.event(ETurnstileEvent.COIN);
		sut.event(ETurnstileEvent.PASS);
		assertTrue(controller.doLockCalled);
	}
}
