package org.goochjs.jskills.factorgraphs;

public class Message<T> {
	private final String nameFormat;
	private final Object[] nameFormatArgs;
	private T value;
	
	public Message() {
		this(null, null, (Object[])null);
	}
	
	public Message(T value, String nameFormat, Object... args) {
		
		this.nameFormat = nameFormat;
		nameFormatArgs = args;
		this.value = value;
	}
	
	@Override
	public String toString() {
		return (nameFormat == null) ? super.toString() : String.format(nameFormat, nameFormatArgs);
	}
	
	@java.lang.SuppressWarnings("all")
	public T getValue() {
		return this.value;
	}
	
	@java.lang.SuppressWarnings("all")
	public void setValue(final T value) {
		this.value = value;
	}
}