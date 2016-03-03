package net.devneko.kotspark.idid;


import java.util.HashMap;
import java.util.Map;

public class OnMemorySequenceGenerator extends SequenceGenerator {
    private Map<String,Long> sequences = new HashMap<>();

    @Override
    public Long next(String key) {
        synchronized (this) {
            Long currentSequence = sequences.get(key);
            Long newSequence = null;
            if ( currentSequence == null ) {
                newSequence = Long.valueOf(1);
            } else {
                newSequence = currentSequence + 1;
            }
            sequences.put(key, newSequence);
            return newSequence;
        }
    }
}
