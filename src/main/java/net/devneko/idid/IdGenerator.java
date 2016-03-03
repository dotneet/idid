package net.devneko.idid;

import java.util.Random;

public class IdGenerator {

    // Instagram Epoch
    private static final long TIME_OFFSET = 1387263000;

    private SequenceGenerator sequenceGenerator;
    private int maxNumberOfShards = 1;

    public IdGenerator() {
        this.sequenceGenerator = new OnMemorySequenceGenerator();
    }

    public IdGenerator(SequenceGenerator sequenceGenerator, int maxNumberOfShards) {
        this.sequenceGenerator = sequenceGenerator;
    }

    // generate id which composed by 63 bits
    public long generate() {
        // 41 bits
        long timeInMillis = System.currentTimeMillis() - TIME_OFFSET;
        // 10 bits
        long shardId = 1 + new Random().nextInt(maxNumberOfShards);
        // 12 bits
        long sequence = sequenceGenerator.next(String.valueOf(timeInMillis));

        return (timeInMillis << 22) | (shardId << 12) | sequence;
    }
}
