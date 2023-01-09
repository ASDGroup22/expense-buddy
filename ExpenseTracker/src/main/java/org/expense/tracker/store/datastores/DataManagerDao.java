package org.expense.tracker.store.datastores;


import java.util.List;

public interface DataManagerDao<T> {

    /**
     * Adds a single entry to the data manager
     * @param entry
     */
    public abstract void save(int profileId, T entry);

    /**
     * Delete a single entry from the data manager
     * @param entry
     */
    public abstract void delete(int profileId, T entry);

    /**
     * Updates a single entry from the data manager
     * @param entry
     */
    public abstract void update(int profileId, T entry);

    /**
     * Gets all entries from the data manager
     */
    public abstract List<T> query(int profileId);
}
