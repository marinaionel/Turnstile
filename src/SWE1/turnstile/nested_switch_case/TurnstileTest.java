package SWE1.turnstile.nested_switch_case;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TurnstileTest {
	private class TurnstileControllerMock implements ITurnstilecontroller {
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
	
	@Before
	public void setUp() throws Exception {
		controller = new TurnstileControllerMock();
		sut = new Turnstile(controller);
	}

	@After
	public void tearDown() throws Exception {
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
