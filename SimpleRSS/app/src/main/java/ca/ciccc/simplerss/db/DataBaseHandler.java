package ca.ciccc.simplerss.db;


import android.content.Context;
import android.support.annotation.NonNull;

import java.util.List;

public class DataBaseHandler {

    OrmaDatabase orma;
    FeedStore feedStore = new FeedStore();

    public DataBaseHandler(Context context) {
        orma = OrmaDatabase.builder(context).build();
    }

    //CRUD

    public void insert() {
        orma.insertIntoFeedStore(feedStore);
    }

    @NonNull
    public List<FeedStore> findAll() {
        return orma.relationOfFeedStore().selector().toList();
    }

    public long update(final FeedStore value) {
        return orma.relationOfFeedStore().updater()
                .idEq(value.id)
                .title(value.title)
                .execute();
    }

    public void delete(final FeedStore value) {
        orma.deleteFromFeedStore()
                .idEq(value.id)
                .execute();
    }
}
