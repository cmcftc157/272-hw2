import java.util.ArrayList;

public class SWSystem {

    private String name;
    private String category;
    private String version;
    private ArrayList<QualityDimension> dimensions;

    public SWSystem(String name, String category, String version) {
        this.name       = name;
        this.category   = category;
        this.version    = version;
        this.dimensions = new ArrayList<>();
    }

    public String getName()     { return name; }
    public String getCategory() { return category; }
    public String getVersion()  { return version; }
    public ArrayList<QualityDimension> getDimensions() { return dimensions; }

    public void addDimension(QualityDimension d) {
        dimensions.add(d);
    }



    public double calcOverallScore() {
        double weightedSum = 0;
        double totalWeight = 0;

        for (QualityDimension d : dimensions) {
            weightedSum += d.calcDimScore() * d.getWeight();
            totalWeight += d.getWeight();
        }

        return totalWeight == 0 ? 0 : Math.round((weightedSum / totalWeight) * 10.0) / 10.0;
    }



    public QualityDimension findWeakestDimension() {
        if (dimensions.isEmpty()) return null;

        QualityDimension weakest = dimensions.get(0);
        for (QualityDimension d : dimensions) {
            if (d.calcDimScore() < weakest.calcDimScore()) {
                weakest = d;
            }
        }
        return weakest;
    }

    private String getQualityLabel(double score) {
        if (score >= 4.5)       return "Excellent Quality";
        else if (score >= 3.5)  return "Good Quality";
        else if (score >= 2.5)  return "Needs Improvement";
        else                    return "Poor Quality";
    }



    public void printReport() {
        System.out.println("========================================");
        System.out.println("SOFTWARE QUALITY EVALUATION REPORT (ISO/IEC 25010)");
        System.out.printf("System: %s v%s (%s)%n", name, version, category);
        System.out.println("========================================");

        for (QualityDimension dim : dimensions) {
            System.out.println();
            System.out.printf("--- %s [%s] (Weight: %.0f) ---%n",
                    dim.getName(), dim.getIsoCode(), dim.getWeight());

            for (Criterion c : dim.getCriteria()) {
                String dirLabel = c.getDirection().equalsIgnoreCase("higher")
                        ? "Higher is better" : "Lower is better";
                System.out.printf("%s: %.1f %s -> Score: %.1f (%s)%n",
                        c.getName(), c.getMeasuredValue(), c.getUnit(),
                        c.calcScore(), dirLabel);
            }

            System.out.printf(">> Dimension Score: %.1f/5 [%s]%n",
                    dim.calcDimScore(), dim.getQualityLabel());
        }

        double overall = calcOverallScore();
        System.out.println();
        System.out.println("========================================");
        System.out.printf("OVERALL QUALITY SCORE: %.1f/5 [%s]%n",
                overall, getQualityLabel(overall));
        System.out.println("========================================");

        QualityDimension weakest = findWeakestDimension();
        if (weakest != null) {
            System.out.println("GAP ANALYSIS (ISO/IEC 25010)");
            System.out.println("========================================");
            System.out.printf("Weakest Characteristic : %s [%s]%n",
                    weakest.getName(), weakest.getIsoCode());
            System.out.printf("Score: %.1f/5 | Gap: %.1f%n",
                    weakest.calcDimScore(), weakest.getQualityGap());
            System.out.printf("Level: %s%n", weakest.getQualityLabel());
            System.out.println(">> This characteristic requires the most improvement.");
            System.out.println("========================================");
        }
    }



    @Override
    public String toString() {
        return String.format("SWSystem{name='%s', version='%s', category='%s'}",
                name, version, category);
    }
}
