package turnstile.transitionTable;

import java.util.Vector;

public class Turnstile {
	private EState currentState = EState.LOCKED;
	private ITurnstileController controller;
	
	private Vector<Transition> transitions = new Vector<Transition>();
	
	public Turnstile(ITurnstileController controller) {
		this.controller = controller;
		controller.doLock();
		
		addTransition(EState.LOCKED, ETurnstileEvent.COIN, EState.OPEN, doUnlock());
		addTransition(EState.LOCKED, ETurnstileEvent.PASS, EState.LOCKED, doAlarm());
		addTransition(EState.OPEN, ETurnstileEvent.COIN, EState.OPEN, doReturnCoin());
		addTransition(EState.OPEN, ETurnstileEvent.PASS, EState.LOCKED, doLock());
	}
	
	public void event(ETurnstileEvent event) {
		for (Transition transiton : transitions) {
			if ((currentState == transiton.currentState) && (event == transiton.event)) {
				currentState = transiton.nextState;
				transiton.action.execute();
			}
		}
	}
	
	private void addTransition(EState curentState, ETurnstileEvent event, EState nextState, IAction action) {
		transitions.add(new Transition(curentState, event, nextState, action));
	}
	
	private enum EState {
		OPEN,
		LOCKED
	}
	
	private class Transition {
		EState currentState;
		ETurnstileEvent event;
		EState nextState;
		IAction action;
		
		public Transition(EState curentState, ETurnstileEvent event, EState nextState, IAction action) {
			this.currentState = curentState;
			this.event = event;
			this.nextState = nextState;
			this.action = action;
		}
	}
	
	// Actions as anonymous implementations of IAction
	private interface IAction {
		void execute();
	}
	
	private IAction doUnlock() {
		return new IAction() {public void execute() {controller.doUnlock();}};
	}
	
	private IAction doLock() {
		return new IAction() {public void execute() {controller.doLock();}};
	}
	
	private IAction doAlarm() {
		return new IAction() {public void execute() {controller.doAlarm();}};
	}
	
	private IAction doReturnCoin() {
		return new IAction() {public void execute() {controller.doReturnCoin();}};
	}
}
