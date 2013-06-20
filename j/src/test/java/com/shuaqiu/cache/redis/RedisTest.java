package com.shuaqiu.cache.redis;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

import java.io.IOException;
import java.util.List;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import redis.client.RedisClient;
import redis.reply.BulkReply;
import redis.reply.MultiBulkReply;
import redis.reply.StatusReply;

import com.google.common.base.Charsets;

/**
 * @author qiushaohua 2013-5-24
 * 
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class RedisTest {
    private static final String H_TEST_KEY = "htestkey";
    private static final String CHAR_TEST_KEY = "zhcn";

    private static RedisClient redisClient = null;

    private int count = 10000;

    @BeforeClass
    public static void setUp() throws IOException {
        redisClient = new RedisClient("127.0.0.1", 6379);
    }

    @AfterClass
    public static void tearDown() throws IOException {
        if (redisClient != null) {
            redisClient.close();
        }
    }

    @Test
    public void testPutList() {
        Object[] field_or_value = new Object[2 * count];
        for (int i = 0; i < count; i++) {
            field_or_value[2 * i] = "field_" + i;
            field_or_value[2 * i + 1] = "value_" + i;
        }
        StatusReply reply = redisClient.hmset(H_TEST_KEY, field_or_value);
        assertThat("hmset", "OK", is(reply.data()));
    }

    @Test
    public void testGetList() {
        Object[] fields = new Object[count];
        for (int i = 0; i < count; i++) {
            fields[i] = "field_" + i;
        }
        MultiBulkReply reply = redisClient.hmget(H_TEST_KEY, fields);

        List<String> asStringList = reply.asStringList(Charsets.UTF_8);
        int i = 0;
        for (String val : asStringList) {
            assertThat("value", "value_" + i, is(val));
            i++;
        }
    }

    @Test
    public void testSetZhcn() {
        StatusReply reply = redisClient.set(CHAR_TEST_KEY, "中文");
        assertThat("set", "OK", is(reply.data()));
    }

    @Test
    public void testGetZhcn() {
        BulkReply reply = redisClient.get(CHAR_TEST_KEY);
        assertThat("get", "中文", is(reply.asUTF8String()));
    }
}
