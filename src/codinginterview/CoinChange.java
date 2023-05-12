package codinginterview;

import java.util.Arrays;
import java.util.Random;

/*
You are given an integer array coins representing coins of different denominations
and an integer amount representing a total amount of money.

Return the fewest number of coins that you need to make up that amount.
If that amount of money cannot be made up by any combination of the coins, return -1.

You may assume that you have an infinite number of each kind of coin.

Example 1:
Input: coins = [1,2,5], amount = 11
Output: 3
Explanation: 11 = 5 + 5 + 1

Example 2:
Input: coins = [2], amount = 3
Output: -1

Example 3:
Input: coins = [1], amount = 0
Output: 0
 */
public class CoinChange {

    /**
     * Recursion
     */
    public static int coinChange(int[] coins, int amount) {
        if (amount == 0) {
            return 0;
        }
        if (amount < 0) {
            return -1;
        }
        int minCoins = Integer.MAX_VALUE;
        for (int coin : coins) {
            int subProblemSolution = coinChange(coins, amount - coin);
            if (subProblemSolution != -1) {
                minCoins = Math.min(minCoins, subProblemSolution + 1);
            }
        }
        return minCoins == Integer.MAX_VALUE ? -1 : minCoins;
    }

    /**
     * Memoization: Top Down DP
     */
    public static int coinChangeMemoization(int[] coins, int amount) {
        int[] memo = new int[amount+1];
        Arrays.fill(memo, -2);
        return coinChangeMemo(coins, amount, memo);
    }

    public static int coinChangeMemo(int[] coins, int amount, int[] memo) {
        if (amount == 0) {
            return 0;
        }
        if (amount < 0) {
            return -1;
        }
        if (memo[amount] != -2) {
            return memo[amount];
        }
        int minimumCoins = Integer.MAX_VALUE;
        for (int coin : coins) {
            int subProblemSolution = coinChangeMemo(coins, amount - coin, memo);
            if (subProblemSolution != -1) {
                minimumCoins = Math.min(minimumCoins, subProblemSolution + 1);
            }
        }
        memo[amount] = minimumCoins == Integer.MAX_VALUE ? -1 : minimumCoins;
        return memo[amount];
    }

    /**
     * Bottom up dynamic programming
     */
    public static int coinChangeBottomUpDP(int[] coins, int amount) {
        int[] dpTable = new int[amount + 1];
        Arrays.fill(dpTable, amount + 1);
        dpTable[0] = 0;
        for (int amt = 1; amt <= amount; amt++) {
            for (int coin : coins) {
                if (coin <= amt)
                    dpTable[amt] = Math.min(dpTable[amt], dpTable[amt - coin] + 1);
            }
        }
        return dpTable[amount] > amount ? -1 : dpTable[amount];
    }

    public static void main(String[] args) {
        Random rand = new Random();
        int[] coins = {1, 2, 5};
        System.out.format("%10s  %12s  %12s  %12s %n", "Amount", "Recursion", "Memoization", "BottomUpDP");
        for (int i = 0; i < 5; i++) {
            int amount = rand.nextInt(25) + 1; // generate random number from 1 to 20
            int recAns = coinChange(coins, amount);
            int memoAns = coinChangeMemoization(coins, amount);
            int budpAns = coinChangeBottomUpDP(coins, amount);
            System.out.format("%10d  %12d  %12d  %12d %n", amount, recAns, memoAns, budpAns);
        }
    }
}
