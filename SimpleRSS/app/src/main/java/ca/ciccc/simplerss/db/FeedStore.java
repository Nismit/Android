package ca.ciccc.simplerss.db;

import com.github.gfx.android.orma.annotation.Column;
import com.github.gfx.android.orma.annotation.PrimaryKey;
import com.github.gfx.android.orma.annotation.Table;

import android.support.annotation.Nullable;

@Table
public class FeedStore {

    @PrimaryKey
    public long id;

    @Column(indexed = true)
    @Nullable
    public String title;

    @Column
    @Nullable
    public String url;

    @Column
    public String category;

}
