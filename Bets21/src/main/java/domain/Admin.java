package domain;

import javax.persistence.Entity;

@Entity
public class Admin extends Subdito {

	private static Admin instance;

	private Admin() {
		super("Alfredo", "Apostemos");
	}

	public static Admin getInstance() {
		if (instance == null)
			instance = new Admin();
		return instance;
	}

}
