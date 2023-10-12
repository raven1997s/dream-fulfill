package com.raven.dreamfulfill.common.utils;

import com.alibaba.fastjson.JSON;
import com.raven.dreamfulfill.common.exception.CommonException;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * Description:
 * date: 2023/10/12 15:02
 * 轮盘选择算法: 类似权值随机选择,可以想象成一个轮盘,每个物品在轮盘上占据一定的面积,面积大小与权值成正比。然后随机选择一个角度,物品被选择的概率即为其对应面积占总面积的比例。
 * @author longjiaocao
 */
@Slf4j
public class LotteryDraw<K, V extends Number> {

    private Map<K, V> itemProbabilities;

    // 在构造方法中校验概率总和
    public LotteryDraw(Map<K, V> itemProbabilities) {

        V total = getZeroValue();

        for (V prob : itemProbabilities.values()) {
            total = sum(total, prob);
        }

        if (!total.equals(getTotalValue())) {
            throw new IllegalArgumentException("Probabilities must sum to " + getTotalValue());
        }

        this.itemProbabilities = itemProbabilities;

        log.info("lottery detail : " + JSON.toJSONString(itemProbabilities));
    }

    // 概率累加方法
    protected V sum(V total, V prob) {
        if (total instanceof Integer && prob instanceof Integer) {
            return (V) (Integer) ((Integer) total + (Integer) prob);
        } else if (total instanceof Double && prob instanceof Double) {
            return (V) (Double) ((Double) total + (Double) prob);
        } else {
            throw new RuntimeException("Incompatible types");
        }
    }

    // 获得一个初始值0
    protected V getZeroValue() {
        return (V) (new Integer(0));
    }

    // 返回期望的总概率值,子类可以覆盖以自定义
    protected V getTotalValue() {
        return (V) (new Integer(100));
    }

    // 随机数生成器类
    class RandomGenerator {

        private Random random = new Random();

        public int nextInt(int min, int max) {
            return random.nextInt(max - min) + min;
        }

    }

    // 使用随机数生成器,而不是 Math.random()
    private RandomGenerator randomGenerator = new RandomGenerator();

    public K draw() {

        int random = randomGenerator.nextInt(0, 100);
        log.info("random: {}", random);
        V cumProb = getZeroValue();
        for (Map.Entry<K, V> entry : itemProbabilities.entrySet()) {

            cumProb = sum(cumProb, entry.getValue());
            log.info("key : " + entry.getKey() + " ==>  cumProb : " + cumProb);
            if (random < cumProb.intValue()) {
                log.info("result : " + entry.getKey());
                return entry.getKey();
            }

        }

        // 如果未找到返回异常而不是null
        throw new CommonException("Could not select an item!");

    }

    // 提供一个重置概率的方法
    public void resetProbabilities(Map<K, V> newProbabilities) {

        // 校验新概率和
        V total = getZeroValue();
        for (V prob : newProbabilities.values()) {
            total = sum(total, prob);
        }

        if (!total.equals(getTotalValue())) {
            throw new IllegalArgumentException("New probabilities must sum to " + getTotalValue());
        }

        // 更新概率
        this.itemProbabilities = newProbabilities;

    }

    public static void main(String[] args) {
        // 定义奖品和概率
        Map<String, Integer> prizes = new HashMap<>();
        prizes.put("手机", 20);
        prizes.put("平板", 30);
        prizes.put("口红", 15);
        prizes.put("书籍1", 5);
        prizes.put("游戏2", 5);
        prizes.put("ps5", 4);
        prizes.put("ps4", 6);
        prizes.put("书籍2", 5);
        prizes.put("书籍3", 9);
        prizes.put("游戏3", 1);

        // 创建抽奖对象
        LotteryDraw<String, Integer> lottery = new LotteryDraw<>(prizes);
        System.out.println(lottery.draw());
    }

}