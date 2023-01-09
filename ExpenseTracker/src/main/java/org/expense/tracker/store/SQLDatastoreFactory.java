package org.expense.tracker.store;

import org.expense.tracker.store.datastores.DataRepository;
import org.expense.tracker.store.datastores.repos.DataRepositoryImpl;

public class SQLDatastoreFactory implements AbstractDataManagerFactory{

    @Override
    public DataRepository getRepository() {
        return new DataRepositoryImpl();
    }
}
