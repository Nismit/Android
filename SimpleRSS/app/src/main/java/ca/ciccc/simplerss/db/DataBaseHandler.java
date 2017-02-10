package ca.ciccc.simplerss.db;


import android.content.Context;
import android.support.annotation.NonNull;

import java.util.List;

public class DataBaseHandler {

    OrmaDatabase orma;

    public DataBaseHandler(Context context) {
        orma = OrmaDatabase.builder(context).build();
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
}
