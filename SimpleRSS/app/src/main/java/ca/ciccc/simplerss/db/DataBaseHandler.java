package ca.ciccc.simplerss.db;


import android.content.Context;
import android.support.annotation.NonNull;

import com.github.gfx.android.orma.AccessThreadConstraint;

import java.util.List;

public class DataBaseHandler {
    // For Debug
    private static final String TAG = "DatabaseHandler";

    OrmaDatabase orma;
    FeedStore feedStore = new FeedStore();

    public DataBaseHandler(Context context) {
        orma = OrmaDatabase.builder(context).writeOnMainThread(AccessThreadConstraint.NONE).build();
    }

    //CRUD

    public void insert() {
        feedStore.title = "test";
        feedStore.url =  "https://www.smashingmagazine.com/feed/";
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
