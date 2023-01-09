package org.expense.tracker.store;

    public class DataManagerFactoryProducer {
    public static AbstractDataManagerFactory getFactory(String dataStore){
        switch (dataStore) {
            case "DB":
                return new SQLDatastoreFactory();
            default:
                return new ORMDatastoreFactory();
        }
    }
}
