package codinginterview;
/*
You are given an integer array coins representing coins of different denominations and
an integer amount representing a total amount of money.

Return the number of combinations that make up that amount.
If that amount of money cannot be made up by any combination of the coins, return 0.

You may assume that you have an infinite number of each kind of coin.

The answer is guaranteed to fit into a signed 32-bit integer.

Example 1:
Input: amount = 5, coins = [1,2,5]
Output: 4
Explanation: there are four ways to make up the amount:
5=5
5=2+2+1
5=2+1+1+1
5=1+1+1+1+1
 */

import java.util.Arrays;
import java.util.Random;

public class CoinChangeII {

   /* Using recursion */
  public static int changeRecursive(int amount, int[] coins) {
    return calcChangeRecursive(coins, amount, 0);
  }

  private static int calcChangeRecursive(int[] coins, int amount, int index) {
    if (amount == 0)
      return 1;     // able to make the amt using this sequence, add 1

    if (amount < 0 || index == coins.length)
      return 0;

    return calcChangeRecursive(coins, amount - coins[index], index) // include this coin, keep trying same coin
      + calcChangeRecursive(coins, amount, index + 1);               // exclude this coin, move on to next coin
  }

  /* Using memoization */
  public static int changeMemoization(int amount, int[] coins) {
    int[][] memo = new int[coins.length+1][amount+1];
    // initialize memo
    for (int[] row : memo) {
      Arrays.fill(row, -1);
    }
    return memoization(coins, amount, 0, memo);
  }

  private static int memoization(int[] coins, int amount, int index, int[][] memo) {
    if (amount == 0)
      return 1;     // able to make the amt using this sequence, add 1
    if (amount < 0 || index == coins.length)
      return 0;
    if (memo[index][amount] != -1) {
      return memo[index][amount];
    }
    int includeCount = memoization(coins, amount - coins[index], index, memo); // include this coin, keep trying same coin
    int excludeCount = memoization(coins, amount, index + 1, memo);               // exclude this coin, move on to next coin
    memo[index][amount] = includeCount + excludeCount;
    return memo[index][amount];
  }

  /* Bottom Up Dynamic Programming solution */
  private static int coinChangeIIBottomUpDP(int amount, int[] coins) {
    int[][] dp = new int[coins.length + 1][amount + 1];

    for (int i = 0; i <= coins.length; i++) {
      dp[i][0] = 1;  // for amount 0, there is only one way, with no coins
    }

    // fill up dp table for coins greater than 0 and amt greater than 0
    for (int i = 1; i <= coins.length; i++) {  // i = 1, because of we are filling up dp table for values > 0
      int currCoin = coins[i - 1]; // i - 1 because first coin is stored at index 0
      for (int amt = 1; amt <= amount; amt++) {
        if (currCoin > amt) {
          dp[i][amt] = dp[i - 1][amt];   // same a previous value
        } else {
          dp[i][amt] = dp[i - 1][amt] + dp[i][amt - currCoin];
        }
      }
    }

    return dp[coins.length][amount];
  }

  public static void main(String[] args) {
    Random rand = new Random();
    int[] coins = {1, 2, 5};
    System.out.format("%10s  %12s  %12s  %12s %n", "Amount", "Recursion", "Memoization", "BottomUpDP");
    for (int i = 0; i < 5; i++) {
      int amount = rand.nextInt(20) + 1; // generate random number from 1 to 20
      int recAns = changeRecursive(amount, coins);
      int memoAns = changeMemoization(amount, coins);
      int budpAns = coinChangeIIBottomUpDP(amount, coins);
      System.out.format("%10d  %12d  %12d  %12d %n", amount, recAns, memoAns, budpAns);
    }
  }

}
