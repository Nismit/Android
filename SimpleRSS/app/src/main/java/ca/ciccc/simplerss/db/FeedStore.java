package ca.ciccc.simplerss.db;

import com.github.gfx.android.orma.annotation.Column;
import com.github.gfx.android.orma.annotation.PrimaryKey;
import com.github.gfx.android.orma.annotation.Table;

import android.support.annotation.Nullable;

@Table
public class FeedStore {

    @PrimaryKey(autoincrement = true)
    public long id;

    // Default: Web Site URL
    @Column(indexed = true)
    @Nullable
    public String title;

    // URL
    @Column
    @Nullable
    public String url;

    // Category
    // It will be categorized by user
    // But WIP..
    @Column
    public String category;

}
