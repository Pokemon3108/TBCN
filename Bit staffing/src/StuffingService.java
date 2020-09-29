import java.util.ArrayList;
import java.util.List;

public class StuffingService {

    final String flag = "1101110";
    // final char insertedSymbol=(char)((flag.charAt(flag.length()-1)-1)*(-1)+'0');
    final char insertedSymbol = (char) (Integer.parseInt(String.valueOf(flag.charAt(flag.length() - 1))) ^ 1 + '0');
    //    final String flag = "333";
//    final char insertedSymbol = '2';
    List<Integer> indexesOfStuffedBits = new ArrayList<>();

    public List<Integer> getIndexesOfStuffedBits() {
        return indexesOfStuffedBits;
    }

    public String stuffData(String data) {
        int startIndex = 0;
        StringBuilder stuffedString = new StringBuilder(data);

        int indexEntrance = stuffedString.indexOf(flag, startIndex);
        while (indexEntrance != -1) {
            int indexOfInsertedBit = indexEntrance + flag.length() - 1;
            indexesOfStuffedBits.add(indexOfInsertedBit);
            stuffedString.insert(indexOfInsertedBit, insertedSymbol);
            indexEntrance = stuffedString.indexOf(flag, indexEntrance + 1);
        }

        return stuffedString.toString();

    }

    public String deBitStuffData(String data) {
        final String changedFlag = flag.substring(0, flag.length() - 1) + insertedSymbol + flag.charAt(flag.length() - 1);

        int startIndex = 0;
        StringBuilder stuffedData = new StringBuilder(data);
        int indexEntrance = stuffedData.indexOf(changedFlag, startIndex);
        while (indexEntrance != -1) {
            int indexOfRemovedBit = indexEntrance + changedFlag.length() - 2;
            stuffedData.deleteCharAt(indexOfRemovedBit);
            indexEntrance = stuffedData.indexOf(changedFlag, indexEntrance + 1);
        }

        return stuffedData.toString();
    }

    @Override
    public String toString() {
        return "Flag: " + flag + "\n"
                + "Inserted bit: " + insertedSymbol + "\n";
    }
}
