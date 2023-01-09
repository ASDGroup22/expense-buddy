package org.expense.tracker.store;

import org.expense.tracker.store.datastores.DataRepository;

public interface AbstractDataManagerFactory {
    public DataRepository getRepository();
}
