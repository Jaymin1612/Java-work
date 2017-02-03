package data;

/**
 * Implementation of Video interface.
 * @see Data
 */
final class VideoObj implements Video {
	private final String title;
	private final int    year;
	private final String director;

	/**
	 * Initialize all object attributes.
	 * Title and director are "trimmed" to remove leading and final space.
	 * @throws IllegalArgumentException if object invariant violated.
	 */
	VideoObj(String title, int year, String director) {
		this.title = title;
		this.director = director;
		this.year = year;
	}

	public String director() {
		
		return director;
	}

	public String title() {
		return title;
	}

	public int year() {
		return year;
	}

	public boolean equals(Object thatObject) {
		if (!(thatObject instanceof Video)) {
			return false;
		}
		Video that = (Video) thatObject;
		return title.equals(that.title())
				&& (year == that.year())
				&& director.equals(that.director());
	}

	public int hashCode() {
		int result = 17;
		result = 37*result + title.hashCode();
		result = 37*result + year;
		result = 37*result + director.hashCode();
		return result;
	}

	public int compareTo(Video that) {
		int titleDiff = title.compareTo(that.title());
		if (titleDiff != 0) {
			return titleDiff;
		}
		int yearDiff = year - that.year();
		if (yearDiff != 0) {
			return yearDiff;
		}
		int directorDiff = director.compareTo(that.director());
		if (directorDiff != 0) {
			return directorDiff;
		}
		return 0;
	}

	public String toString() {
		StringBuilder buffer = new StringBuilder();
		buffer.append(title);
		buffer.append(" (");
		buffer.append(year);
		buffer.append(") : ");
		buffer.append(director);
		return buffer.toString();
	}
}
