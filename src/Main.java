import java.util.ArrayList;
import java.util.HashMap;

public class Main {

    public static void main(String[] arr) {

        HashMap<String, ArrayList<SWSystem>> allSystems = SWSystemData.getAllSystems();

        ArrayList<SWSystem> webSystems = allSystems.get("Web");



        SWSystem shopSphere = null;
        for (SWSystem sys : webSystems) {
            if (sys.getName().equals("ShopSphere")) {
                shopSphere = sys;
                break;
            }
        }



        for (QualityDimension dim : shopSphere.getDimensions()) {
            switch (dim.getIsoCode()) {

                case "QC.FS":
                    for (Criterion c : dim.getCriteria()) {
                        if (c.getName().equals("Functional Completeness Ratio")) c.setMeasuredValue(94.0);
                        if (c.getName().equals("Functional Correctness Ratio"))  c.setMeasuredValue(91.0);
                    }
                    break;

                case "QC.RE":
                    for (Criterion c : dim.getCriteria()) {
                        if (c.getName().equals("Availability Ratio")) c.setMeasuredValue(99.2);
                        if (c.getName().equals("Defect Density"))     c.setMeasuredValue(2.1);
                    }
                    break;

                case "QC.PE":
                    for (Criterion c : dim.getCriteria()) {
                        if (c.getName().equals("Response Time"))   c.setMeasuredValue(220.0);
                        if (c.getName().equals("CPU Utilisation")) c.setMeasuredValue(38.0);
                    }
                    break;

                case "QC.MA":
                    for (Criterion c : dim.getCriteria()) {
                        if (c.getName().equals("Test Coverage Ratio"))      c.setMeasuredValue(72.0);
                        if (c.getName().equals("Cyclomatic Complexity"))    c.setMeasuredValue(8.5);
                    }
                    break;
            }
        }



        shopSphere.printReport();
    }
}
