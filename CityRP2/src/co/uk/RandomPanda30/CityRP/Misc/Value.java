package co.uk.RandomPanda30.CityRP.Misc;

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