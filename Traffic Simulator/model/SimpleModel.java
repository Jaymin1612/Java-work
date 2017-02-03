package model;

import java.util.List;
import java.util.ArrayList;
import java.util.Observable;
import util.Animator;


public class SimpleModel extends Observable {
	/*private List<Agent> agents;
	private Animator animator;
	private boolean disposed;
	private double time;

	
	public SimpleModel(AnimatorBuilder builder) {
		if (builder == null) {
			builder = new NullAnimatorBuilder();
		}
		agents = new ArrayList<Agent>();
		setup(builder);
		animator = builder.getAnimator();
		super.addObserver(animator);
	}

	public void run(int duration) {
		if (disposed)
			throw new IllegalStateException();
		for (int i=0; i<duration; i++) {
			time++;
			Agent[] agents_copy = agents.toArray(new Agent[0]);
			for (Agent a : agents_copy) {
				a.run(time);
			}
			super.setChanged();
			super.notifyObservers();
		}
	}

	
	public void dispose() {
		animator.dispose();
		disposed = true;
	}

	private void setup(AnimatorBuilder builder) {
		Road r1 = new Road();
		builder.addHorizontalRoad(r1, 0, 1, false);
		Car c = new Car();
		r1.accept(c);
		agents.add(c);
		Road r2 = new Road();
		builder.addHorizontalRoad(r2, 1, 1, false);

		/* Road oldroad = null;
		 for (int j = 0; j <= columns; j++) {
		   Road newroad = new Road();
		   builder.addHorizontalRoad(newroad, 0, (eastToWest/ columns-j : j), eastToWest);
		   newroad.setNext(oldroad);
		   oldroad = newroad;
		 }
	}*/
}
