import java.util.ArrayList;
import java.util.List;

public class StuffingService {

    private final String flag = "01101110";
    private final char insertedSymbol = (char) (Integer.parseInt(String.valueOf(flag.charAt(flag.length() - 1))) ^ 1 + '0');
    private List<Integer> indexesOfStuffedBits = new ArrayList<>();

    public List<Integer> getIndexesOfStuffedBits() {
        return indexesOfStuffedBits;
    }

    public String stuffData(String data) {
        StringBuilder stuffedString = new StringBuilder(data);

        int indexEntrance = stuffedString.indexOf(flag.substring(0, flag.length()-1));
        while (indexEntrance != -1) {
            int indexOfInsertedBit = indexEntrance + flag.length() - 1;
            indexesOfStuffedBits.add(indexOfInsertedBit);
            stuffedString.insert(indexOfInsertedBit, insertedSymbol);
            indexEntrance = stuffedString.indexOf(flag.substring(0, flag.length()-1), indexEntrance + 1);
        }

        return stuffedString.toString();

    }

    public String deBitStuffData(String data) {
        final String changedFlag = flag.substring(0, flag.length() - 1) + insertedSymbol;

        StringBuilder deStuffedData = new StringBuilder(data);
        int indexEntrance = deStuffedData.indexOf(changedFlag);
        while (indexEntrance != -1) {
            int indexOfRemovedBit = indexEntrance + changedFlag.length() - 2;
            deStuffedData.deleteCharAt(indexOfRemovedBit);
            indexEntrance = deStuffedData.indexOf(changedFlag, indexEntrance + 1);
        }

        return deStuffedData.toString();

    }

    @Override
    public String toString() {
        return "Flag: " + flag + "\n"
                + "Bit for inserting: " + insertedSymbol + "\n";
    }
}
