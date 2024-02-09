package helpers;

import java.util.ArrayList;
import java.util.List;

public class RangeMapper {
    private List<Long> start_destination;
    private List<Long> start_source;
    private List<Long> end_source;

    private int size;
    public RangeMapper() {
        start_destination = new ArrayList<>();
        start_source = new ArrayList<>();
        end_source = new ArrayList<>();
    }

    public void add(long[] dest_sor_range) {
        start_destination.add(dest_sor_range[0]);
        start_source.add(dest_sor_range[1]);
        end_source.add(dest_sor_range[1] + dest_sor_range[2]);
        size = end_source.size();
    }

    public long map(long number) {
        for (int i = 0; i < size; i++) {
            if (start_source.get(i) <= number && number < end_source.get(i)) {
                return number + start_destination.get(i) - start_source.get(i);
            }
        }
        return number;
    }
}
