import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class ReadWrite {
	BufferedReader br = null;
	static FileWriter fw = null;

	public ArrayList<Probe> rdPData(String file_path) throws IOException {
		String temp = "";
		ArrayList<Probe> probe_list = new ArrayList<Probe>();
		try {
			br = new BufferedReader(new FileReader(file_path));
			while ((temp = br.readLine()) != null) {
				String[] str = temp.split(",");
				Probe p = new Probe();
				p.setSampleID(Integer.parseInt(str[0]));
				p.setDateTime(str[1]);
				p.setSourceCode(Integer.parseInt(str[2]));
				p.setLatitude(Double.parseDouble(str[3]));
				p.setLongitude(Double.parseDouble(str[4]));
				p.setAltitude(Double.parseDouble(str[5]));
				p.setSpeed(Double.parseDouble(str[6]));
				p.setHeading(Double.parseDouble(str[7]));
				probe_list.add(p);
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} finally {
			if (br != null) {
				br.close();
			}
		}
		return probe_list;
	}

	public ArrayList<Link> rdLData(String file_path) throws NumberFormatException, IOException {
		ArrayList<Link> link_list = new ArrayList<Link>();
		String temp = "";
		try {
			br = new BufferedReader(new FileReader(file_path));
			while ((temp = br.readLine()) != null) {
				String[] str = temp.split(",");
				Link l = new Link();
				int length = str.length;

				l.setLinkPVID(str[0]);
				l.setRefNodeID(str[1]);
				l.setnRefNodeID(str[2]);
				l.setLength(Double.parseDouble(str[3]));
				l.setFunctionClass(Integer.parseInt(str[4]));
				l.setDirectionOfTravel(str[5].charAt(0));
				l.setSpeedCategory(Integer.parseInt(str[6]));
				l.setFromRefSpeedLimit(Integer.parseInt(str[7]));
				l.setToRefSpeedLimit(Integer.parseInt(str[8]));
				l.setFromRefNumLanes(Integer.parseInt(str[9]));
				l.setToRefNumLanes(str[10].charAt(0));
				l.setMultiDigitized(str[11].charAt(0));
				l.setUrban(str[12].charAt(0));
				l.setTimeZone(Double.parseDouble(str[13]));

				if (length > 14) {

					if (!str[14].isEmpty()) {
						ArrayList<Double[]> shape_data = new ArrayList<>();
						String[] shape_list = str[14].split("\\|");
						for (int i = 0; i < shape_list.length; i++) {
							String[] shape_arr = shape_list[i].split("/");
							Double[] d = new Double[shape_arr.length];
							for (int j = 0; j < shape_arr.length; j++) {
								d[j] = Double.parseDouble(shape_arr[j]);
							}
							shape_data.add(d);
						}
						l.setShapeInfo(shape_data);
					}
				}

				if (length > 15) {

					if (!str[15].isEmpty()) {
						ArrayList<Double[]> cur_data = new ArrayList<>();
						String[] cur_list = str[15].split("\\|");
						for (int i = 0; i < cur_list.length; i++) {
							String[] cur_arr = cur_list[i].split("/");
							Double[] d = new Double[cur_arr.length];
							for (int j = 0; j < cur_arr.length; j++) {
								d[j] = Double.parseDouble(cur_arr[j]);
							}
							cur_data.add(d);
						}
						l.setCurvatureInfo(cur_data);
					}
				}

				if (length > 16) {

					if (!str[16].isEmpty()) {
						ArrayList<Double[]> slope_data = new ArrayList<>();
						String[] slope_list = str[16].split("\\|");
						for (int i = 0; i < slope_list.length; i++) {
							String[] slope_arr = slope_list[i].split("/");
							Double[] d = new Double[slope_arr.length];
							for (int j = 0; j < slope_arr.length; j++) {
								d[j] = Double.parseDouble(slope_arr[j]);
							}
							slope_data.add(d);
						}
						l.setSlopeInfo(slope_data);
					}
				}
				link_list.add(l);
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} finally {
			if (br != null) {
				br.close();
			}
		}
		return link_list;
	}

	public static void wrMPF(Probe probe, int index) throws IOException {

		try {
			if (index == 0) {
				fw = new FileWriter("MatchedPoints.csv", false);
				String first_row = "sampleID,dateTime,sourceCode,latitude,longitude,altitude,speed,heading,linkPVID,direction,distFromRef,distFromLink,slope";
				String data = String.valueOf(probe.getSampleID()) + "," + probe.getDateTime() + ","
						+ probe.getSourceCode() + "," + probe.getLatitude() + "," + probe.getLongitude() + ","
						+ probe.getAltitude() + "," + probe.getSpeed() + "," + probe.getHeading() + ","
						+ probe.getLinkPVID() + "," + probe.getProbeDirection() + "," + probe.getDistFromRef() + ","
						+ probe.getDistFromLink() + "," + probe.getSlope();

				fw.append(first_row);
				fw.append("\n");
				fw.append(data);
				fw.append("\n");
			} else {
				fw = new FileWriter("MatchedPoints.csv", true);
				String data = String.valueOf(probe.getSampleID()) + "," + probe.getDateTime() + ","
						+ probe.getSourceCode() + "," + probe.getLatitude() + "," + probe.getLongitude() + ","
						+ probe.getAltitude() + "," + probe.getSpeed() + "," + probe.getHeading() + ","
						+ probe.getLinkPVID() + "," + probe.getProbeDirection() + "," + probe.getDistFromRef() + ","
						+ probe.getDistFromLink() + "," + probe.getSlope();

				fw.append(data);
				fw.append("\n");
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			fw.flush();
			fw.close();
		}
	}

	public static void wrSD(String linkID, double calculatedMean, double givenMean, int index) throws IOException {

		try {
			if (index == 0) {
				fw = new FileWriter("SlopeEvaluation.csv", false);
				String first_row = "linkPVID,calculatedMeanSlope,givenMeanSlope";
				String data = linkID + "," + String.valueOf(calculatedMean) + "," + String.valueOf(givenMean);
				fw.append(first_row);
				fw.append("\n");
				fw.append(data);
				fw.append("\n");
			} else {
				fw = new FileWriter("SlopeEvaluation.csv", true);
				String data = linkID + "," + String.valueOf(calculatedMean) + "," + String.valueOf(givenMean);
				fw.append(data);
				fw.append("\n");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			fw.flush();
			fw.close();
		}
	}
}
