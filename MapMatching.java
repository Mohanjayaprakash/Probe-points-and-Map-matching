import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class MapMatching {

	static ArrayList<Probe> arrp = new ArrayList<Probe>();
	static ArrayList<Link> arrli = new ArrayList<Link>();

	public static void main(String[] args) throws IOException {
		ReadWrite f1 = new ReadWrite();
		Scanner scan = new Scanner(System.in);
		System.out.println("Enter file containing all probe points");
		String ppath = scan.next();
		arrp = f1.rdPData(ppath);
		System.out.println("Enter file containing all link data");
		String lpath = scan.next();
		arrli = f1.rdLData(lpath);
		scan.close();
		System.out.println("Please be patient the program is now matching probe points to the link");
		System.out.println("Running...");
		
		Double limin = 0.0, promin = 0.0, matdis = 0.0, mappre = 0.0, disper = 0.0;
		int minind = 0, probpre = 0;
		String linkclose = "", linkpreclose = "";// initializing the variables
		for (int indpro = 0; indpro < arrp.size(); indpro++) { // creating a new probeIndex variable
			Probe p = arrp.get(indpro); // Data type of Probe and assigning the values at that index (Doubt)

			Double[] pointmap = null; // Double Array named MapMatchedpoint
			for (int i = 0; i < arrli.size(); i++) {
				Link link = arrli.get(i);//// Data type of Link and assigning the values at that index (Doubt)
				List<Double> pointdist = new ArrayList<Double>();// New Arraylist distMapMatchedPoints
				for (int j = 0; j < link.getShapeInfo().size() - 1; j++) {// shapeInfo-contains an array of shape
																			// entries consisting of the latitude and
																			// longitude
					Double start[] = link.getShapeInfo().get(j);// New array dstart
					Double end[] = link.getShapeInfo().get(j + 1);// new Array dend
					Double[] vectorl = new Double[] { end[0] - start[0], end[1] - start[1] };
					Double[] vectorp = new Double[] { p.getLatitude() - start[0], p.getLongitude() - start[1] };
					Double mag = Math.sqrt(Math.pow(vectorl[0], 2) + Math.pow(vectorl[1], 2));
					Double[] vectline = new Double[] { vectorl[0] / mag, vectorl[1] / mag };
					Double[] vectorpoint = new Double[] { vectorp[0] / mag, vectorp[1] / mag };
					Double prod = vectline[0] * vectorpoint[0] + vectline[1] * vectorpoint[1];// Double check this with
																								// Orginal
					if (prod < 0.0) {
						prod = 0.0;
					} else if (prod > 1.0) {
						prod = 1.0;
					}
					pointmap = new Double[] { vectorl[0] * prod, vectorl[1] * prod };
					disper = Math.sqrt(Math.pow(vectorp[0] - pointmap[0], 2) + Math.pow(vectorp[1] - pointmap[1], 2))
							* 1000;
					pointmap[0] = pointmap[0] + start[0];
					pointmap[1] = pointmap[1] + start[1];
					pointdist.add(disper);
				}
				limin = Collections.min(pointdist);
				if (i == 0 || limin < promin) {
					promin = limin;
					linkclose = link.getLinkPVID();
					minind = pointdist.indexOf(limin);
					matdis = SlopeCalculate.distancemapmat(link, pointmap, minind);
				}
			}
			if (promin < 20) {
				if (indpro == 0) {
					p.setProbeDirection('U');
				} else {
					if (p.getSampleID() == probpre && linkpreclose == linkclose) {
						if (mappre > matdis) {
							p.setProbeDirection('T');
						} else if (mappre < matdis) {
							p.setProbeDirection('F');
						} else {
							p.setProbeDirection('U');
						}
					} else {
						p.setProbeDirection('U');
					}
				}
				linkpreclose = linkclose;
				mappre = matdis;
				probpre = p.getSampleID();
				p.setDistFromLink(promin);
				p.setDistFromRef(matdis);
				p.setLinkPVID(linkclose);
				/*System.out.println("Probe no: " + indpro + "   ProbeSampleID: " + p.getSampleID() + "   Nearest Link: "
						+ p.getLinkPVID() + "   Direction of Travel: " + p.getProbeDirection()
						+ "   Distance from Reference: " + p.getDistFromRef() + "   Distance from Link: "
						+ p.getDistFromLink());*/
			} else {
				continue;
			}
		}
		SlopeCalculate.sCheck(arrli, arrp);
	}

}
