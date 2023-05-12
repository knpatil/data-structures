package algorithms;

/*

Problem: Merge User Accounts

You are a social media manager, and there are a lot of users on your platform with various emails attached to their accounts. For each user, there is a name and several emails associated with that user.

    You noticed that a lot of users have multiple accounts registered with the same email, and you decided to merge some accounts according to the following rules:
      If two accounts have the same name and share at least one common email, they must belong to the same user, and thus can be safely merged.
      This merged account can be merged with other accounts via the same method, so if two accounts with the same name doesn't have a common email, but they each have a common email with a third account with the same name, they can be merged together.
      Two different accounts can share the same name, as long as they cannot be linked back to the same person via email tracking.
      Two different accounts with different names can never be merged, even if they may share a common email.

    After merging the accounts such that you cannot merge any more, output the final remaining accounts in a sorted order.
    That is, the emails of each account is sorted lexicographically, and the accounts are sorted lexicographically by name, then by the first email (if applicable).

Input
    accounts: A matrix of strings (can be uneven). Each row represent an account, with the first entry being the name and the rest being the email associated with the account.

Output
    A matrix of strings representing the final resulting accounts, sorted.

Example 1:

Input:

accounts = [
  ["John", "johnsmith@mail.com", "john_newyork@mail.com"],
  ["John", "johnsmith@mail.com", "john_work@mail.com"],
  ["Mary", "mary@mail.com"],
  ["John", "johnny@mail.com"]
]

Output: [ ["John", "john_newyork@mail.com", "john_work@mail.com","johnsmith@mail.com"], ["John", "johnny@mail.com"], ["Mary", "mary@mail.com"], ]

 */

/*

Solution

This is a classical DSU (Disjoint Set Union) problem.
For each user-email pair under an account, you just perform a union operation on them. In the end, you iterate though all user-email-pair and group the ones with the same ancestor together in the output.
Note that two emails may not belong to the same user if the username is different, so we need to store the username in the union find object as well.

Time Complexity: O(nlog(n)) Space Complexity: O(n)

 */

import java.util.*;

public class MergeUserAccounts {

    private static class UnionFind<T> {
      private HashMap<T, T> id = new HashMap<>();

      public T find(T x) {
        T y = id.getOrDefault(x, x);  // get parent ID for x or just return same element
        if (y != x) {  // recursively find until the parent id is same
          y = find(y);
          id.put(x, y);  // keep updating the ID
        }
        return y;
      }

      public void union(T x, T y) {
        id.put(find(x), find(y));   // merge two connected components
      }
    }

    public static class UserEmailPair implements Comparable<UserEmailPair> {
      private final String user;
      private final String email;

      public UserEmailPair(String user, String email) {
        this.user = user;
        this.email = email;
      }

      @Override
      public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserEmailPair that = (UserEmailPair) o;
        return user.equals(that.user) && email.equals(that.email);
      }

      @Override
      public int hashCode() {
        return Objects.hash(user, email);
      }

      @Override
      public int compareTo(UserEmailPair o) {
        return 0;
      }
    }

    public static List<List<String>> mergeAccounts(List<List<String>> accounts) {
      UnionFind<UserEmailPair> unionFind = new UnionFind<>();
      HashSet<UserEmailPair> allUserEmails = new HashSet<>();

      // Create connected components (build the graph)
      for (List<String> acct: accounts) {
        String userName = acct.get(0);
        UserEmailPair parent = null;
        for (int i = 1; i < acct.size(); i++) {
          String email = acct.get(i);
          UserEmailPair currentPair = new UserEmailPair(userName, email);
          allUserEmails.add(currentPair);
          if (parent == null) {
            parent = currentPair; // make this pair as a parent if null
          } else {
            unionFind.union(parent, currentPair); // merge it with existing parent
          }
        }
      }

      // merge different accounts, connect if email matches
      Map<UserEmailPair, List<UserEmailPair>> accountAssociation = new HashMap<>();
      for (UserEmailPair currPair: allUserEmails) {  // for each element in the disjoint set
        UserEmailPair ancestor = unionFind.find(currPair);
        if (!accountAssociation.containsKey(ancestor)) {
          accountAssociation.put(ancestor, new ArrayList<>());
        }
        accountAssociation.get(ancestor).add(currPair); // merge based on the parent
      }

      // return result
      List<List<String>> result = new ArrayList<>();
      for (Map.Entry<UserEmailPair, List<UserEmailPair>> userInfo: accountAssociation.entrySet()) {
        List<String> oneUser = new ArrayList<>();
        oneUser.add(userInfo.getKey().user);  // userName as a first entry
        Collections.sort(userInfo.getValue());
        for (UserEmailPair emailEntry: userInfo.getValue()) {
          oneUser.add(emailEntry.email);
        }
      }

      Collections.sort(result, (a, b) -> {
        if (a.get(0).compareTo(b.get(0)) == 0)
          return a.get(1).compareTo(b.get(1));
        return a.get(0).compareTo(b.get(0));
      });

      return result;
    }
}
