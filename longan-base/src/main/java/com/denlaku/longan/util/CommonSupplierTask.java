package com.denlaku.longan.util;

import java.util.List;
import java.util.concurrent.RecursiveTask;

/**
 * @author tianx
 */
public class CommonSupplierTask<V> extends RecursiveTask<List<V>> {

    private List<CommonSupplier<V>> suppliers;
    private CommonApplier contextApplier;
    private boolean throwException;

    public CommonSupplierTask(List<CommonSupplier<V>> suppliers, CommonApplier contextApplier) {
        this(suppliers, contextApplier, false);
    }

    public CommonSupplierTask(List<CommonSupplier<V>> suppliers, CommonApplier contextApplier, boolean throwException) {
        this.suppliers = suppliers;
        this.contextApplier = contextApplier;
        this.throwException = throwException;
    }

    CommonSupplierTask<V> of(CommonSupplier<V> supplier) {
        return new CommonSupplierTask<>(Lists.of(supplier), contextApplier, throwException);
    }

    @Override
    protected List<V> compute() {
        if (Lists.isNotEmpty(suppliers) && contextApplier != null) {
            contextApplier.apply();
        }
        int size = suppliers.size();
        List<V> result = Lists.ofSize(size);
        if (size == 1) {
            V v = doCompute(suppliers.get(0));
            result.add(v);
        } else {
            List<CommonSupplierTask<V>> tasks = Lists.toList(suppliers, this::of);
            invokeAll(tasks);
            tasks.forEach(task -> result.addAll(task.join()));
        }
        return result;
    }

    private V doCompute(CommonSupplier<V> supplier) {
        try {
            return supplier.get();
        } catch (Exception e) {
            if (throwException) {
                throw new RuntimeException(e);
            }
        }
        return null;
    }
}
