package statePattern.classic;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import turnstile.statePattern.classic.ITurnstileController;
import turnstile.statePattern.classic.Turnstile;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class TurnstileTest {
	private class TurnstileControllerMock implements ITurnstileController {
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
		sut.coinInsertedEvent();
		assertTrue(controller.doUnlockCalled);
	}
	
	@Test
	public void testDoAlarmCalledWhenPassWithoutCoinInserted() {
		sut.passedEvent();
		assertTrue(controller.doAlarmCalled);
	}
	
	@Test
	public void testDoReturnCoinCalledWhenTwoCoinsInserted() {
		sut.coinInsertedEvent();
		sut.coinInsertedEvent();
		assertTrue(controller.doReturnCoinCalled);
	}
	
	@Test
	public void testDoLockCalledWhenCoinInsertedAndPass() {
		sut.coinInsertedEvent();
		sut.passedEvent();
		assertTrue(controller.doLockCalled);
	}
}
