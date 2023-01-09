package org.expense.tracker.store;

import org.expense.tracker.store.datastores.DataRepository;
import org.expense.tracker.store.datastores.repos.InMemoryRepository;

public class ORMDatastoreFactory implements AbstractDataManagerFactory{

    @Override
    public DataRepository getRepository() {
        return new InMemoryRepository();
    }
}
