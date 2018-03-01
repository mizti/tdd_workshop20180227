public class Semver implements Comparable<Semver>{
	private int major;
	private int minor;
	private int patch;
	
	public Semver(int major, int minor, int patch){
		if(major < 0 || minor < 0 || patch < 0) {
			throw new IllegalArgumentException("Major " + major+ " or Minor " + minor + " or Patch " + patch + " is smaller than 0");
		}
		this.major = major;
		this.minor = minor;
		this.patch = patch;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(major);
		sb.append('.');
		sb.append(minor);
		sb.append('.');
		sb.append(patch);
		
		return sb.toString();
	}
	
	@Override
	public int compareTo(Semver target) {
		if(this.major > target.major){
			return 1;
		} else if (this.major < target.major) {
			return -1;
		} 

		if(this.minor > target.minor){
			return 1;
		} else if (this.minor < target.minor) {
			return -1;
		}

		if(this.patch > target.patch){
			return 1;
		} else if (this.patch < target.patch) {
			return -1;
		} 
		
		return 0;
	}
	
	@Override
	public boolean equals(Object target) {
		if (!(target instanceof Semver)) return false;
		Semver s = (Semver)target;
		return (major == s.major && minor == s.minor && patch == s.patch);
	}

	public int getMajor() {
		return this.major;
	}

	public int getMinor() {
		return this.minor;
	}
	
	public int getPatch() {
		return this.patch;
	}
	
	public void majorUpdate() {
		this.major++;
		this.minor = 0;
		this.patch = 0;
	}

	public void minorUpdate() {
		this.minor++;
		this.patch = 0;
	}
	
	public void patchUpdate() {
		this.patch++;
	}
}