import java.util.HashSet;
import java.util.Set;

public class PassObjectsByReferenceTest {
    public static void main(String[] args) {
        Set<String> bucketsToSoftDelete = new HashSet<>();
        Set<Integer> uniqCachedDatasets = new HashSet<>();
        System.out.println("Before");
        for (String bucket: bucketsToSoftDelete) {
            System.out.println("Bucket: " + bucket);
        }

        for (Integer n: uniqCachedDatasets) {
            System.out.println("Num: " + n);
        }
        System.out.println("\n------------------------------------------\n");
        methodCall(bucketsToSoftDelete, uniqCachedDatasets);

        for (String bucket: bucketsToSoftDelete) {
            System.out.println("Bucket: " + bucket);
        }

        for (Integer n: uniqCachedDatasets) {
            System.out.println("Num: " + n);
        }
    }

    private static void methodCall(Set<String> bucketsToSoftDelete, Set<Integer> uniqCachedDatasets) {
        for (int i = 0; i < 5; i++) {
            bucketsToSoftDelete.add("bucket " + i);
            uniqCachedDatasets.add(i + 100);
        }
    }
}
