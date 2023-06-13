package com.star.wlh.effective;

/**
 * @Classname NutritionFacts
 * @Description TODO
 * @Date 2023/6/13 18:28
 * @Created by wlh
 */
public class NutritionFacts {
    private final int servingSize;
    private final int servings;
    private final int calories;
    private final int fat;
    private final int sodium;
    private final int carbohydrate;

    public static class NutritionFactsBuilder {
        private final int servingSize;
        private final int servings;
        private int calories = 0;
        private int fat = 0;
        private int sodium = 0;
        private int carbohydrate = 0;

        public NutritionFactsBuilder(int servingSize, int servings) {
            this.servings = servings;
            this.servingSize = servingSize;
        }

        public NutritionFactsBuilder calories(int val) {
            calories = val;
            return this;
        }

        public NutritionFactsBuilder fat(int val) {
            fat = val;
            return this;
        }

        public NutritionFactsBuilder sodium(int val) {
            sodium = val;
            return this;
        }

        public NutritionFactsBuilder carbohydrate(int val) {
            carbohydrate = val;
            return this;
        }

        public NutritionFacts build() {
            return new NutritionFacts(this);
        }
    }

    public NutritionFacts(NutritionFactsBuilder builder) {
        this.servingSize = builder.servingSize;
        this.servings = builder.servings;
        this.calories = builder.calories;
        this.fat = builder.fat;
        this.sodium = builder.sodium;
        this.carbohydrate = builder.carbohydrate;
    }

    public static void main(String[] args) {
        NutritionFacts build = new NutritionFactsBuilder(12, 10).fat(12).calories(11).sodium(11).carbohydrate(11).build();

    }
}
