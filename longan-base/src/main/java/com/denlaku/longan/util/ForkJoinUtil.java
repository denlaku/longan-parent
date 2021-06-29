package com.denlaku.longan.util;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;

/**
 * @author tianx
 */
public final class ForkJoinUtil {

    public static void execute(CommonApplierAction action) {
        ForkJoinPool fjp = new ForkJoinPool();
        ForkJoinTask<Void> result = fjp.submit(action);
        try {
            result.get();
        } catch (InterruptedException | ExecutionException e) {
            //
        }
    }

    public static void execute(List<CommonApplier> appliers, CommonApplier commonApplier) {
        execute(new CommonApplierAction(appliers, commonApplier));
    }

    public static void execute(List<CommonApplier> appliers) {
        execute(appliers, null);
    }

    public static <V> List<V> submit(CommonSupplierTask<V> action) {
        ForkJoinPool fjp = new ForkJoinPool();
        ForkJoinTask<List<V>> result = fjp.submit(action);
        try {
            return result.get();
        } catch (InterruptedException | ExecutionException e) {
            //
        }
        return Lists.of();
    }

    public static <V> List<V> submit(List<CommonSupplier<V>> suppliers, CommonApplier commonApplier) {
        return submit(new CommonSupplierTask<>(suppliers, commonApplier));
    }

    public static <V> List<V> submit(List<CommonSupplier<V>> suppliers) {
        return submit(suppliers, null);
    }

    public static void will(CommonApplierAction action) {
        ForkJoinPool fjp = new ForkJoinPool();
        fjp.execute(action);
    }

    public static void will(List<CommonApplier> appliers, CommonApplier commonApplier) {
        will(new CommonApplierAction(appliers, commonApplier));
    }

    public static void will(List<CommonApplier> appliers) {
        will(appliers, null);
    }
}
