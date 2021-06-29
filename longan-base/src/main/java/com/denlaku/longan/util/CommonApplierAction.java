package com.denlaku.longan.util;

import java.util.List;
import java.util.concurrent.RecursiveAction;

/**
 * @author tianx
 */
public class CommonApplierAction extends RecursiveAction {

    private List<CommonApplier> appliers;
    private CommonApplier contextApplier;
    private boolean throwException;

    public CommonApplierAction(List<CommonApplier> appliers, CommonApplier contextApplier) {
        this(appliers, contextApplier, false);
    }

    public CommonApplierAction(List<CommonApplier> appliers, CommonApplier contextApplier, boolean throwException) {
        this.appliers = appliers;
        this.contextApplier = contextApplier;
        this.throwException = throwException;
    }

    CommonApplierAction of(CommonApplier applier) {
        return new CommonApplierAction(Lists.of(applier), contextApplier);
    }

    @Override
    protected void compute() {
        if (Lists.isNotEmpty(appliers) && contextApplier != null) {
            contextApplier.apply();
        }
        if (appliers.size() == 1) {
            doCompute(appliers.get(0));
        } else {
            List<CommonApplierAction> actions = Lists.toList(appliers, this::of);
            invokeAll(actions);
            actions.forEach(CommonApplierAction::join);
        }
    }

    private void doCompute(CommonApplier applier) {
        try {
            applier.apply();
        } catch (Exception e) {
            if (throwException) {
                throw new RuntimeException(e);
            }
        }
    }
}
