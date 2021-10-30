package transitionTable;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import turnstile.transitionTable.ETurnstileEvent;
import turnstile.transitionTable.ITurnstileController;
import turnstile.transitionTable.Turnstile;

import static org.junit.jupiter.api.Assertions.assertTrue;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
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
