package main.java.universe;

import main.java.Constants;

public class Kingdom {

private String name;
	
	private String emblem;
	
	public Kingdom(String n, String e) {
		name = n;
		emblem = e;
	}

	public String getName() {
		return name;
	}

	public String getEmblem() {
		return emblem;
	}

	public static boolean isValidKingdom(String kd) {
		if(Constants.KINGDOMS.containsKey(kd))
			return true;
		
		return false;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((emblem == null) ? 0 : emblem.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Kingdom other = (Kingdom) obj;
		if (emblem == null) {
			if (other.emblem != null)
				return false;
		} else if (!emblem.equals(other.emblem))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Kingdom [name=" + name + ", emblem=" + emblem + "]";
	}
	
}
