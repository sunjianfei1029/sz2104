package com.bjpowernode.redis.test;

import com.bjpowernode.redis.uitl.RedisUtils;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.Transaction;

import java.util.List;

public class JedisTest {

    public static void main(String[] args) {
        /*Jedis jedis = new Jedis("192.168.150.128",6379);
        jedis.flushDB();
        String str1 = jedis.set("str1", "hello");
        String str2 = jedis.set("str2", "jedis");
        System.out.println("str1:"+ str1);
        System.out.println("str2:"+ str2);

        jedis.close();
        // 获取redis连接池对象
        JedisPool jedisPool = RedisUtils.open("192.168.150.128", 6379);
        // 从连接池中获取jedis对象
        Jedis jedis = jedisPool.getResource();
        jedis.lpush("list","java","php","python","c++");
        RedisUtils.close();*/

        // 事务的操作
        JedisPool jedisPool = RedisUtils.open("192.168.150.128", 6379);
        Jedis jedis = jedisPool.getResource();
        // 开启事务
        Transaction multi = jedis.multi();

        multi.sadd("set1","java","php","python");
        multi.set("str3","multi");
        multi.set("str4","hello");
        List<Object> exec = multi.exec();
        for (Object o : exec) {
            System.out.println(o);
        }

        // 关闭资源
        RedisUtils.close();

    }
}
