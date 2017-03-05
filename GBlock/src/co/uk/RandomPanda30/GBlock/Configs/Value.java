package co.uk.RandomPanda30.GBlock.Configs;

public class Value<T> {

	private T value;

	public Value () {
	};

	public Value (T defValue) {
		value = defValue;
	}

	public T getValue() {
		return value;
	}

	public void setValue(T value) {
		this.value = value;
	}
}