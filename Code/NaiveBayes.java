package Code;

import java.util.Map;

public class NaiveBayes {

    //attributes

    //creating attributes to store probabilities of the increase being yes or no
    public double increaseYes;
    public double increaseNo;

    //creating hashmap attributes to store the probabilities that a feature was yes or no
    public Map<String, Double> revenueGrowthYes;
    public Map<String, Double> revenueGrowthNo;

    public Map<String, Double> profitMarginYes;
    public Map<String, Double> profitMarginNo;

    public Map<String, Double> marketSentimentYes;
    public Map<String, Double> marketSentimentNo;

    public Map<String, Double> debtLevelYes;
    public Map<String, Double> debtLevelNo;

    public double prob_Yes;
    public double prob_No;

    public NaiveBayes(){//level 1 functionality with hard code probabilities from the dataset
        increaseYes = 0.61;
        increaseNo = 0.39;

        //probabilities for each label
        revenueGrowthYes = Map.of("high", 0.85, "low", 0.15);
        revenueGrowthNo = Map.of("high", 0.18, "low", 0.82);

        profitMarginYes = Map.of("high", 0.72, "low", 0.28);
        profitMarginNo = Map.of("high", 0.36, "low", 0.64);

        marketSentimentYes = Map.of("Positive", 0.86, "Negative", 0.14);
        marketSentimentNo = Map.of("Positive", 0.26, "Negative", 0.74);

        debtLevelYes = Map.of("high", 0.14, "low", 0.86);
        debtLevelNo = Map.of("high", 0.83, "low", 0.17);
    }


    public String predict(String revenueGrowth, String profitMargin, String marketSentiment, String debtLevel){

        //calculating if the probability based on the user entries if the stock will increase or decrease
        prob_Yes = increaseYes * revenueGrowthYes.get(revenueGrowth) * profitMarginYes.get(profitMargin)
                * marketSentimentYes.get(marketSentiment) * debtLevelYes.get(debtLevel);

        prob_No = increaseNo * revenueGrowthNo.get(revenueGrowth) * profitMarginNo.get(profitMargin)
                * marketSentimentNo.get(marketSentiment) * debtLevelNo.get(debtLevel);

        //return based on which probability is greater
        return (prob_Yes > prob_No) ? "Yes" : "No";
    }
}
