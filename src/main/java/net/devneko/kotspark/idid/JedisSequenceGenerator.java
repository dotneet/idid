package net.devneko.kotspark.idid;

import redis.clients.jedis.Jedis;

import java.util.function.Supplier;

public class JedisSequenceGenerator extends SequenceGenerator {
    private Supplier<Jedis> jedisFactory;
    private Jedis jedis;

    JedisSequenceGenerator(Supplier<Jedis> jedisFactory)  {
        if ( jedisFactory == null ) {
            throw new IllegalArgumentException("jedisFactory must be not null.");
        }
        this.jedisFactory = jedisFactory;
    }

    JedisSequenceGenerator(Jedis jedis)  {
        if ( jedis == null ) {
            throw new IllegalArgumentException("jedis must be not null.");
        }
        this.jedis = jedis;
    }

    @Override
    public Long next(String key) {
        if ( jedis == null ) {
            jedis = jedisFactory.get();
        }
        return jedis.incr(key);
    }
}