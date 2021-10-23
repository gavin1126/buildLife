package com.kjtb.buildlife.Framework.cache;

import java.util.Map;

/**
 *
 * There are only two hard things in computer science :
 * cache invalidation and naming things
 *
 * https://en.wikipedia.org/wiki/Cache_replacement_policies
 * 缓存主要的优点:
 * 延迟
 * 命中率
 *
 * 难点
 * 一致性
 * 无效
 *
 * @Author Dr d
 * @Date 2021-06-26
 **/
public class CacheReplacementPolicy {

    /*
    （remove/replace）Least Recent Used
     */
    void LRU() {

    }

    class LRU_DS{
        int[][] valArr; //val,rank/age
        int pageSize;//固定大小

        Map<Integer,Integer> valMap;//val,rank

    }


    public static void main(String[] args) {

    }
}
