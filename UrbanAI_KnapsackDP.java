import java.util.*;

class SmartCityProject {
    String name;
    int cost;
    int benefit;

    public SmartCityProject(String name, int cost, int benefit) {
        this.name = name;
        this.cost = cost;
        this.benefit = benefit;
    }
}

public class UrbanAI_KnapsackDP {

    public static void knapsackDP(SmartCityProject[] projects, int budget) {
        int n = projects.length;
        int[][] dp = new int[n + 1][budget + 1];

        // Build DP table
        for (int i = 1; i <= n; i++) {
            for (int w = 0; w <= budget; w++) {
                if (projects[i - 1].cost <= w) {
                    dp[i][w] = Math.max(
                            dp[i - 1][w],
                            dp[i - 1][w - projects[i - 1].cost] + projects[i - 1].benefit
                    );
                } else {
                    dp[i][w] = dp[i - 1][w];
                }
            }
        }

        // Backtrack to find selected projects
        int w = budget;
        List<SmartCityProject> selected = new ArrayList<>();

        for (int i = n; i > 0; i--) {
            if (dp[i][w] != dp[i - 1][w]) {
                selected.add(projects[i - 1]);
                w -= projects[i - 1].cost;
            }
        }

        // Display results
        System.out.println("Optimal Total Benefit: " + dp[n][budget]);
        System.out.println("Selected Projects (maximizing benefit within budget $" + budget + "M):");

        for (SmartCityProject p : selected) {
            System.out.println(" - " + p.name +
                    " | Cost: $" + p.cost + "M" +
                    " | Benefit: " + p.benefit);
        }

        int totalCost = selected.stream()
                                .mapToInt(p -> p.cost)
                                .sum();

        System.out.println("Total Cost: $" + totalCost + "M");
    }

    public static void main(String[] args) {

        SmartCityProject[] projects = {
            new SmartCityProject("Smart Traffic Lights", 5, 45),
            new SmartCityProject("EV Charging Stations", 8, 70),
            new SmartCityProject("Air Quality Sensors", 3, 30),
            new SmartCityProject("Smart Street Lighting", 4, 40),
            new SmartCityProject("Public Wi-Fi Hotspots", 6, 50)
        };

        int budget = 15; // $15 million

        System.out.println("=== UrbanAI Smart City Investment Optimization (0/1 Knapsack DP) ===");
        knapsackDP(projects, budget);
    }
}