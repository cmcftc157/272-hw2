public class Criterion {

    private String name;
    private double weight;
    private String direction;
    private double minValue;
    private double maxValue;
    private String unit;
    private double measuredValue;
    private boolean isValueSet;

    public Criterion(String name, double weight, String direction,
                     double minValue, double maxValue, String unit) {
        this.name        = name;
        this.weight      = weight;
        this.direction   = direction;
        this.minValue    = minValue;
        this.maxValue    = maxValue;
        this.unit        = unit;
        this.isValueSet  = false;
    }



    public String getName()        { return name; }
    public double getWeight()      { return weight; }
    public String getDirection()   { return direction; }
    public double getMinValue()    { return minValue; }
    public double getMaxValue()    { return maxValue; }
    public String getUnit()        { return unit; }
    public double getMeasuredValue() { return measuredValue; }

    public void setMeasuredValue(double measuredValue) {
        this.measuredValue = measuredValue;
        this.isValueSet    = true;
    }



    public double calcScore() {
        if (!isValueSet) {
            throw new IllegalStateException("Measured value not set for criterion: " + name);
        }

        double range = maxValue - minValue;
        double rawScore;

        if (direction.equalsIgnoreCase("higher")) {
            rawScore = 1 + ((measuredValue - minValue) / range) * 4;
        } else {
            rawScore = 5 - ((measuredValue - minValue) / range) * 4;
        }

        rawScore = Math.max(1.0, Math.min(5.0, rawScore));

        return Math.round(rawScore * 2) / 2.0;
    }



    @Override
    public String toString() {
        String dirLabel = direction.equalsIgnoreCase("higher") ? "Higher is better" : "Lower is better";
        return String.format("%s: %.1f %s -> Score: %.1f (%s)",
                name, measuredValue, unit, calcScore(), dirLabel);
    }
}
