import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SlopeCalculate {

	public static void sCheck(List<Link> link_list, List<Probe> points) throws IOException {
		Probe oldProbe = null;
		for (int i = 0; i < points.size(); i++) {
			Probe newProbe = points.get(i);
			if (oldProbe == null) {
				newProbe.setSlope("U");
			} else if (newProbe.getLinkPVID() != oldProbe.getLinkPVID()) {
				newProbe.setSlope("U");
			} else {
				double diff = newProbe.getAltitude() - oldProbe.getAltitude();
				double b = radsinform(newProbe.getLatitude(), newProbe.getLongitude(),oldProbe.getLatitude(), oldProbe.getLongitude());
				double s = Math.toRadians(Math.atan(diff * 1000 / b));
				newProbe.setSlope(String.valueOf(s));
			}
			oldProbe = newProbe;
			ReadWrite.wrMPF(newProbe, i);
			
			for (int z = 0; z < link_list.size(); z++) {
				if (link_list.get(z).getLinkPVID() == newProbe.getLinkPVID() && link_list.get(z).getSlopeInfo() != null) {
					ArrayList<Probe> p_list = null;
					if (link_list.get(z).getLinkProbes() != null) {
						p_list = link_list.get(z).getLinkProbes();
					} else {
						p_list = new ArrayList<>();
					}
					p_list.add(newProbe);
					link_list.get(z).setLinkProbes(p_list);
				}
			}
		}
		System.out.println("MatchedPoints.csv file has been created.");
		sCalOther(link_list);
	}


	public static void sCalOther(List<Link> link_list) throws IOException {
		int d_c = 0;
		for (int i = 0; i < link_list.size(); i++) {
			ArrayList<Probe> p_list = link_list.get(i).getLinkProbes();
			double mean = 0.0, total = 0.0, l_mean = 0.0, l_total = 0.0;
			int c = 0, l_c = 0;
			if (p_list != null) {
				for (Probe p : p_list) {
					String s = p.getSlope();
					if (s != "0" && s != "U") {
						if (p.getProbeDirection() == 'T') {
							total -= Double.parseDouble(s);
							c += 1;
						} else {
							total += Double.parseDouble(s);
							c += 1;
						}
					}
				}
				if (c != 0) {
					mean = total / c;
				}
				ArrayList<Double[]> linkSlope = link_list.get(i).getSlopeInfo();
				for (int k = 0; k < linkSlope.size(); k++) {
					l_total += linkSlope.get(k)[1];
					l_c += 1;
				}
				if (l_c != 0) {
					l_mean = l_total / l_c;
				}
				ReadWrite.wrSD(link_list.get(i).getLinkPVID(), mean, l_mean, d_c);
				d_c += 1;
			}
		}
		System.out.println("SlopeEvaluation.csv file has been created.");
		System.out.println("Thank you for your patience");
	}
	
	public static double distancemapmat(Link lnk, Double[] pointmap, int index) {
	        double distance = 0.0;
	        for (int i = 0; i < index - 1; i++) {
	                      distance += radsinform(lnk.getShapeInfo().get(i)[0], lnk.getShapeInfo().get(i)[1],
	                                                 lnk.getShapeInfo().get(i + 1)[0], lnk.getShapeInfo().get(i + 1)[1]);
	        }
	        distance += radsinform(lnk.getShapeInfo().get(index)[0], lnk.getShapeInfo().get(index)[1], pointmap[0],
	                                    pointmap[1]);
	        return distance * 1000;
	}

	public static double radsinform(double lat1, double long1, double lat2, double long2) {
	        final double r = 6372.8;
	        double lat = Math.toRadians(lat2 - lat1);
	        double longi = Math.toRadians(long2 - long1);
	        lat1 = Math.toRadians(lat1);
	        lat2 = Math.toRadians(lat2);
	
	        double a = Math.pow(Math.sin(lat / 2), 2) + Math.pow(Math.sin(longi / 2), 2) * Math.cos(lat1) * Math.cos(lat2);
	        double c = 2 * Math.asin(Math.sqrt(a));
	        return r * c;
	}
}
