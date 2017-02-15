package ca.ciccc.simplerss.db;

import com.github.gfx.android.orma.annotation.Column;
import com.github.gfx.android.orma.annotation.PrimaryKey;
import com.github.gfx.android.orma.annotation.Table;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

@Table
public class FeedStore {

    @PrimaryKey(autoincrement = true)
    public long id;

    // Default: Web Site URL
    @Column(indexed = true)
    @NonNull
    public String title;

    // URL
    @Column
    @NonNull
    public String url;

    // Category
    // It will be categorized by user
    // But WIP..
    @Column
    @Nullable
    public String category;

}
