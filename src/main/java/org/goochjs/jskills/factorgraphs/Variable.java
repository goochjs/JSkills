package org.goochjs.jskills.factorgraphs;

public class Variable<TValue> {
	private final String name;
	private final TValue prior;
	private TValue value;
	
	public Variable(TValue prior, String name, Object... args) {
		
		this.name = "Variable[" + String.format(name, args) + "]";
		this.prior = prior;
		resetToPrior();
	}
	
	public void resetToPrior() {
		value = prior;
	}
	
	@Override
	public String toString() {
		return name;
	}
	
	@java.lang.SuppressWarnings("all")
	public TValue getValue() {
		return this.value;
	}
	
	@java.lang.SuppressWarnings("all")
	public void setValue(final TValue value) {
		this.value = value;
	}
}