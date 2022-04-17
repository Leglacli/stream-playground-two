package brickset;

import repository.Repository;

import java.time.Year;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Represents a repository of {@code LegoSet} objects.
 */
public class LegoSetRepository extends Repository<LegoSet> {

    public LegoSetRepository() {
        super(LegoSet.class, "brickset.json");
    }

    /**
     * Returns a boolean based on an existence of a LegoSet theme
     * @param theme a LegoSet theme
     * @return boolean based on an existence of a LegoSet theme
     */
    public boolean existsWithTheme(String theme) {
        return getAll().stream().anyMatch(legoSet -> legoSet.getTheme().equals(theme));
    }

    /**
     * Prints the names of the LegoSets
     */
    public void printLegoSetNames() {
        getAll().stream()
                .flatMap(num -> Stream.of(num.getName()))
                .forEach(System.out::println);
    }

    /**
     * Returns the sum of all the LegoSet pieces
     * @return the sum of all the LegoSet pieces
     */
    public int getSumOfAllPieces() {
        return getAll().stream()
                .map(LegoSet::getPieces)
                .reduce(0, (element1, element2) -> element1 + element2);
    }

    /**
     * Returns a Map with String LegoSet Number and Integer LegoSet Pieces pairs
     * @return a Map with String LegoSet Number and Integer LegoSet Pieces pairs
     */
    public Map<String, Integer> getNumberAndPiecesMap() {
        return getAll().stream()
                .collect(Collectors.toMap(LegoSet::getNumber, LegoSet::getPieces));
    }

    /**
     * Returns a Map with String LegoSet Theme and a List of LegoSet pairs
     * @return a Map with String LegoSet Theme and a List of LegoSet pairs
     */
    public Map<String, List<LegoSet>> getMapGroupedByTheme() {
        return getAll().stream().collect(
                Collectors.groupingBy(LegoSet::getTheme, Collectors.toList()));
    }

    public static void main(String[] args) {

        var repository = new LegoSetRepository();

        //AnyMatch
        System.out.println(repository.existsWithTheme("Games"));

        //FlatMap
        repository.printLegoSetNames();

        //Reduce
        System.out.println(repository.getSumOfAllPieces());

        //Collectors.toMap
        System.out.println(repository.getNumberAndPiecesMap());

        //Egy vagy több downstream collector
        System.out.println(repository.getMapGroupedByTheme());
    }

}
