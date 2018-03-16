import java.util.ArrayList;

public class Link {

	private String linkPVID;
	private String refNodeID;
	private String nRefNodeID;
	private Double length;
	private int functionClass;
	private char directionOfTravel;
	private int speedCategory;
	private int fromRefSpeedLimit;
	private int toRefSpeedLimit;
	private int fromRefNumLanes;
	private int toRefNumLanes;
	private char multiDigitized;
	private char urban;
	private Double timeZone;
	private ArrayList<Double[]> shapeInfo;
	private ArrayList<Double[]> curvatureInfo;
	private ArrayList<Double[]> slopeInfo;
	private ArrayList<Probe> linkProbes;
	public Link() {

	}

	public String getLinkPVID() {
		return linkPVID;
	}

	public void setLinkPVID(String linkPVID) {
		this.linkPVID = linkPVID;
	}

	public String getRefNodeID() {
		return refNodeID;
	}

	public void setRefNodeID(String refNodeID) {
		this.refNodeID = refNodeID;
	}

	public String getnRefNodeID() {
		return nRefNodeID;
	}

	public void setnRefNodeID(String nRefNodeID) {
		this.nRefNodeID = nRefNodeID;
	}

	public Double getLength() {
		return length;
	}

	public void setLength(Double length) {
		this.length = length;
	}

	public int getFunctionClass() {
		return functionClass;
	}

	public void setFunctionClass(int functionClass) {
		this.functionClass = functionClass;
	}

	public char getDirectionOfTravel() {
		return directionOfTravel;
	}

	public void setDirectionOfTravel(char directionOfTravel) {
		this.directionOfTravel = directionOfTravel;
	}

	public int getSpeedCategory() {
		return speedCategory;
	}

	public void setSpeedCategory(int speedCategory) {
		this.speedCategory = speedCategory;
	}

	public int getFromRefSpeedLimit() {
		return fromRefSpeedLimit;
	}

	public void setFromRefSpeedLimit(int fromRefSpeedLimit) {
		this.fromRefSpeedLimit = fromRefSpeedLimit;
	}

	public int getToRefSpeedLimit() {
		return toRefSpeedLimit;
	}

	public void setToRefSpeedLimit(int toRefSpeedLimit) {
		this.toRefSpeedLimit = toRefSpeedLimit;
	}

	public int getFromRefNumLanes() {
		return fromRefNumLanes;
	}

	public void setFromRefNumLanes(int fromRefNumLanes) {
		this.fromRefNumLanes = fromRefNumLanes;
	}

	public int getToRefNumLanes() {
		return toRefNumLanes;
	}

	public void setToRefNumLanes(int toRefNumLanes) {
		this.toRefNumLanes = toRefNumLanes;
	}

	public char getMultiDigitized() {
		return multiDigitized;
	}

	public void setMultiDigitized(char multiDigitized) {
		this.multiDigitized = multiDigitized;
	}

	public char getUrban() {
		return urban;
	}

	public void setUrban(char urban) {
		this.urban = urban;
	}

	public Double getTimeZone() {
		return timeZone;
	}

	public void setTimeZone(Double timeZone) {
		this.timeZone = timeZone;
	}

	public ArrayList<Double[]> getShapeInfo() {
		return shapeInfo;
	}

	public void setShapeInfo(ArrayList<Double[]> shapeInfo) {
		this.shapeInfo = shapeInfo;
	}

	public ArrayList<Double[]> getCurvatureInfo() {
		return curvatureInfo;
	}

	public void setCurvatureInfo(ArrayList<Double[]> curvatureInfo) {
		this.curvatureInfo = curvatureInfo;
	}

	public ArrayList<Double[]> getSlopeInfo() {
		return slopeInfo;
	}

	public void setSlopeInfo(ArrayList<Double[]> slopeInfo) {
		this.slopeInfo = slopeInfo;
	}

	public ArrayList<Probe> getLinkProbes() {
		return linkProbes;
	}

	public void setLinkProbes(ArrayList<Probe> linkProbes) {
		this.linkProbes = linkProbes;
	}
}
