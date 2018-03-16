
public class Probe {

	private int sampleID;
	private String dateTime;
	private int sourceCode;
	private Double latitude;
	private Double longitude;
	private Double altitude;
	private Double speed;
	private Double heading;
	private char probeDirection;
	private Double distFromRef;
	private String slope;
	private String linkPVID;

	public String getSlope() {
		return slope;
	}

	public void setSlope(String slope) {
		this.slope = slope;
	}

	public Double getDistFromRef() {
		return distFromRef;
	}

	public void setDistFromRef(Double distFromRef) {
		this.distFromRef = distFromRef;
	}

	public Double getDistFromLink() {
		return distFromLink;
	}

	public void setDistFromLink(Double distFromLink) {
		this.distFromLink = distFromLink;
	}

	private Double distFromLink;

	public Probe() {

	}

	public int getSampleID() {
		return sampleID;
	}

	public void setSampleID(int sampleID) {
		this.sampleID = sampleID;
	}

	public String getDateTime() {
		return dateTime;
	}

	public void setDateTime(String dateTime) {
		this.dateTime = dateTime;
	}

	public int getSourceCode() {
		return sourceCode;
	}

	public void setSourceCode(int sourceCode) {
		this.sourceCode = sourceCode;
	}

	public Double getLatitude() {
		return latitude;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	public Double getLongitude() {
		return longitude;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}

	public Double getAltitude() {
		return altitude;
	}

	public void setAltitude(Double altitude) {
		this.altitude = altitude;
	}

	public Double getSpeed() {
		return speed;
	}

	public void setSpeed(Double speed) {
		this.speed = speed;
	}

	public Double getHeading() {
		return heading;
	}

	public void setHeading(Double heading) {
		this.heading = heading;
	}

	public char getProbeDirection() {
		return probeDirection;
	}

	public void setProbeDirection(char probeDirection) {
		this.probeDirection = probeDirection;
	}

	public String getLinkPVID() {
		return linkPVID;
	}

	public void setLinkPVID(String linkPVID) {
		this.linkPVID = linkPVID;
	}
}
