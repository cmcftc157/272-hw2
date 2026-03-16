import java.util.ArrayList;
import java.util.HashMap;

public class SWSystemData {

    private SWSystemData() {}

    public static HashMap<String, ArrayList<SWSystem>> getAllSystems() {
        HashMap<String, ArrayList<SWSystem>> map = new HashMap<>();

        ArrayList<SWSystem> webList = new ArrayList<>();
        webList.add(createECommercePlatform());
        webList.add(createBankingPortal());
        map.put("Web", webList);

        ArrayList<SWSystem> mobileList = new ArrayList<>();
        mobileList.add(createHealthApp());
        map.put("Mobile", mobileList);

        return map;
    }




    private static SWSystem createECommercePlatform() {
        SWSystem s = new SWSystem("ShopSphere", "Web", "3.2.1");



        QualityDimension funcSuit = new QualityDimension("Functional Suitability", "QC.FS", 25);
        funcSuit.addCriterion(new Criterion(
                "Functional Completeness Ratio",
                50, "higher", 50, 100, "%"));
        funcSuit.addCriterion(new Criterion(
                "Functional Correctness Ratio",
                50, "higher", 50, 100, "%"));
        s.addDimension(funcSuit);



        QualityDimension reliability = new QualityDimension("Reliability", "QC.RE", 25);
        reliability.addCriterion(new Criterion(
                "Availability Ratio",
                50, "higher", 95, 100, "%"));
        reliability.addCriterion(new Criterion(
                "Defect Density",
                50, "lower", 0, 20, "defect/KLOC"));
        s.addDimension(reliability);



        QualityDimension performance = new QualityDimension("Performance Efficiency", "QC.PE", 25);
        performance.addCriterion(new Criterion(
                "Response Time",
                50, "lower", 0, 800, "ms"));
        performance.addCriterion(new Criterion(
                "CPU Utilisation",
                50, "lower", 0, 160, "%"));
        s.addDimension(performance);



        QualityDimension maintainability = new QualityDimension("Maintainability", "QC.MA", 25);
        maintainability.addCriterion(new Criterion(
                "Test Coverage Ratio",
                50, "higher", 0, 120, "%"));
        maintainability.addCriterion(new Criterion(
                "Cyclomatic Complexity",
                50, "lower", 1, 20, "score"));
        s.addDimension(maintainability);



        return s;
    }

    private static SWSystem createBankingPortal() {
        SWSystem s = new SWSystem("SecureBank", "Web", "2.1.0");



        QualityDimension security = new QualityDimension("Security", "QC.SE", 35);
        security.addCriterion(new Criterion(
                "Security Test Coverage",
                60, "higher", 0, 100, "%"));
        security.addCriterion(new Criterion(
                "Vulnerability Count",
                40, "lower", 0, 50, "count"));
        s.addDimension(security);



        QualityDimension reliability = new QualityDimension("Reliability", "QC.RE", 35);
        reliability.addCriterion(new Criterion(
                "Availability Ratio",
                50, "higher", 95, 100, "%"));
        reliability.addCriterion(new Criterion(
                "MTBF (Mean Time Between Failures)",
                50, "higher", 0, 8760, "hours"));
        s.addDimension(reliability);



        QualityDimension usability = new QualityDimension("Usability", "QC.US", 30);
        usability.addCriterion(new Criterion(
                "Task Completion Rate",
                50, "higher", 0, 100, "%"));
        usability.addCriterion(new Criterion(
                "User Error Rate",
                50, "lower", 0, 30, "%"));
        s.addDimension(usability);



        return s;
    }

    private static SWSystem createHealthApp() {
        SWSystem s = new SWSystem("VitaTrack", "Mobile", "1.4.2");



        QualityDimension usability = new QualityDimension("Usability", "QC.US", 30);
        usability.addCriterion(new Criterion(
                "Task Completion Rate",
                50, "higher", 0, 100, "%"));
        usability.addCriterion(new Criterion(
                "User Error Rate",
                50, "lower", 0, 30, "%"));
        s.addDimension(usability);



        QualityDimension performance = new QualityDimension("Performance Efficiency", "QC.PE", 35);
        performance.addCriterion(new Criterion(
                "Response Time",
                50, "lower", 100, 2000, "ms"));
        performance.addCriterion(new Criterion(
                "CPU Utilisation",
                50, "lower", 0, 100, "%"));
        s.addDimension(performance);



        QualityDimension reliability = new QualityDimension("Reliability", "QC.RE", 35);
        reliability.addCriterion(new Criterion(
                "Availability Ratio",
                50, "higher", 95, 100, "%"));
        reliability.addCriterion(new Criterion(
                "Defect Density",
                50, "lower", 0, 20, "defect/KLOC"));
        s.addDimension(reliability);



        return s;
    }
}
