package model;

public class AppointmentCollection {
	private static final int DEFAULT_PHYSICAL_SIZE = 10;
	private static final int DEFAULT_GROWTH_FACTOR = 2;
	
	private Appointment[] innerContainer;
	private int size;
	
	public AppointmentCollection(Appointment[] collection) {
		innerContainer = new Appointment[collection.length];
		size =0;
		for (int i = 0; i < collection.length; ++i) {
			
			if (collection[i] != 
					null) {
				innerContainer[size] = collection[i];
				size++; 
				}
				}
		}
	
	public AppointmentCollection(int physicalSize) {
		innerContainer = new Appointment[physicalSize];
		size = 0;
	}

	public AppointmentCollection() {
		this(DEFAULT_PHYSICAL_SIZE);
	}
	public Appointment get (int index) {
		if (index < 0 || index >= size)
			return null;

		return innerContainer[index];
	}
	public int indexOf(Appointment appointment) {
		int i;
		for( i = 0; i< size; i++) {
			if(innerContainer[i].equals(appointment))
				break;
		}
		return i;
	}
	public void add(Appointment f) {
		if (innerContainer.length == size) {
			Appointment[] newContainer = new Appointment[size * DEFAULT_GROWTH_FACTOR];
			for (int i = 0; i < innerContainer.length; i++) {
				newContainer[i] = innerContainer[i];
			}
			innerContainer = newContainer;
		}
		innerContainer[size++] = f;
	}
	public void remove(int index) {
		if (index < 0 || index >= size)
			return;
		
		for (int i = index; i < size - 1; i++) {
			innerContainer[i] = innerContainer[i+1];
		}
		size--;
	}
	public int size() {
		return size;
	}
}
