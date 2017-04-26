package org.wheatgenetics.coordinate.database;

/**
 * Uses:
 * android.content.ContentValues
 * android.content.Context
 * android.database.Cursor
 * android.util.Log
 *
 * org.wheatgenetics.coordinate.database.Table
 */

public class Entry extends org.wheatgenetics.coordinate.database.Table       // TODO: Stop catching.
{
    public  long             id , grid;
    public  int              col, row ;
    public  java.lang.String value    ;
    private long             stamp    ;


    // region ? Constructors
    public Entry(final android.content.Context context) throws java.lang.Exception
    {
        super(context, org.wheatgenetics.coordinate.database.Entry.TABLE_NAME);

        this.id   = 0;
        this.grid = 0;

        this.col = 0;
        this.row = 0;

        this.value = "";

        this.stamp = 0;
    }

    public Entry(final android.content.Context context, final long entry, final int col,  // TODO: Remove later.
    final int row, final java.lang.String value, final long stamp) throws java.lang.Exception
    {
        super(context, org.wheatgenetics.coordinate.database.Entry.TABLE_NAME);

        this.id   = 0    ;
        this.grid = entry;

        this.col = col;
        this.row = row;

        this.value = value;

        this.stamp = stamp;
    }
    // endregion


    @Override
    public java.lang.String toString()
    {
        return java.lang.String.format("id: %02d entry: %02d col: %02d row: %02d value: %.2f",
            this.id, this.grid, this.col, this.row, this.value);
    }


    // region Storage
    // region Private Constants
    private static final java.lang.String TABLE_NAME = "entries";
    private static final java.lang.String
        ID_FIELD_NAME  = "_id", GRID_FIELD_NAME  = "grid" , COL_FIELD_NAME   = "col"  ,
        ROW_FIELD_NAME = "row", EDATA_FIELD_NAME = "edata", STAMP_FIELD_NAME = "stamp";
    // endregion


    private android.content.ContentValues getValues()
    {
        final android.content.ContentValues contentValues = new android.content.ContentValues();

        contentValues.put(org.wheatgenetics.coordinate.database.Entry.GRID_FIELD_NAME , this.grid );
        contentValues.put(org.wheatgenetics.coordinate.database.Entry.COL_FIELD_NAME  , this.col  );
        contentValues.put(org.wheatgenetics.coordinate.database.Entry.ROW_FIELD_NAME  , this.row  );
        contentValues.put(org.wheatgenetics.coordinate.database.Entry.EDATA_FIELD_NAME, this.value);
        contentValues.put(org.wheatgenetics.coordinate.database.Entry.STAMP_FIELD_NAME, this.stamp);

        return contentValues;
    }

    public void copy(final android.database.Cursor cursor)
    {
        assert cursor != null;
        this.id = cursor.getInt(cursor.getColumnIndex(
            org.wheatgenetics.coordinate.database.Entry.ID_FIELD_NAME));
        this.grid = cursor.getInt(cursor.getColumnIndex(
            org.wheatgenetics.coordinate.database.Entry.GRID_FIELD_NAME));

        this.col = cursor.getInt(cursor.getColumnIndex(
            org.wheatgenetics.coordinate.database.Entry.COL_FIELD_NAME));
        this.row = cursor.getInt(cursor.getColumnIndex(
            org.wheatgenetics.coordinate.database.Entry.ROW_FIELD_NAME));

        this.value = cursor.getString(cursor.getColumnIndex(
            org.wheatgenetics.coordinate.database.Entry.EDATA_FIELD_NAME));

        this.stamp = cursor.getLong(cursor.getColumnIndex(
            org.wheatgenetics.coordinate.database.Entry.STAMP_FIELD_NAME));
    }

    public boolean get(final long id)                                         // TODO: Remove later.
    {
        android.database.Cursor cursor = null;
        try
        {
            cursor = this.queryDistinct(
                org.wheatgenetics.coordinate.database.Entry.ID_FIELD_NAME + "=" + id);
            if (cursor != null)
            {
                cursor.moveToFirst();
                this.copy(cursor);
                cursor.close();
                return true;
            }
        }
        catch (java.lang.Exception e) { if (cursor != null) cursor.close(); }
        return false;
    }

    private static int sendInfoLogMsg(final java.lang.String msg)
    {
        return android.util.Log.i("Entry", msg);
    }

    public android.database.Cursor load()                                     // TODO: Remove later.
    {
        org.wheatgenetics.coordinate.database.Entry.sendInfoLogMsg(
            "Loading table " + org.wheatgenetics.coordinate.database.Entry.TABLE_NAME);
        return this.queryAll();
    }

    public long insert()
    {
        org.wheatgenetics.coordinate.database.Entry.sendInfoLogMsg(
            "Inserting into table " + org.wheatgenetics.coordinate.database.Entry.TABLE_NAME);
        return this.insert(this.getValues());
    }

    public boolean update()
    {
        org.wheatgenetics.coordinate.database.Entry.sendInfoLogMsg("Updating table " +
            org.wheatgenetics.coordinate.database.Entry.TABLE_NAME + " on id = " + id);
        return this.update(
            /* contentValues => */ this.getValues(),
            /* whereClause   => */
                org.wheatgenetics.coordinate.database.Entry.ID_FIELD_NAME + "=" + id);
    }

    public boolean delete(final long id)                                      // TODO: Remove later.
    {
        org.wheatgenetics.coordinate.database.Entry.sendInfoLogMsg("Deleting from table " +
            org.wheatgenetics.coordinate.database.Entry.TABLE_NAME + " on id = " + id);
        return this.delete(/* whereClause => */
            org.wheatgenetics.coordinate.database.Entry.ID_FIELD_NAME + "=" + id);
    }

    public boolean delete()                                                   // TODO: Remove later.
    {
        org.wheatgenetics.coordinate.database.Entry.sendInfoLogMsg("Clearing table " +
            org.wheatgenetics.coordinate.database.Entry.TABLE_NAME);
        return super.delete();
    }

    public android.database.Cursor loadByEntry(final int entry)               // TODO: Remove later.
    {
        org.wheatgenetics.coordinate.database.Entry.sendInfoLogMsg("Loading table " +
            org.wheatgenetics.coordinate.database.Entry.TABLE_NAME + " by entry = " + entry);
        return this.queryAllSelection(/* selection => */
            org.wheatgenetics.coordinate.database.Entry.GRID_FIELD_NAME + " = " + entry);
    }

    public boolean getByGrid(final long grid, final int row, final int col)
    {
        android.database.Cursor cursor = null;
        try
        {
            final java.lang.String sel =
                org.wheatgenetics.coordinate.database.Entry.GRID_FIELD_NAME + "= ? AND " +
                org.wheatgenetics.coordinate.database.Entry.COL_FIELD_NAME  + "= ? AND " +
                org.wheatgenetics.coordinate.database.Entry.ROW_FIELD_NAME  + "= ?"      ;

            final java.lang.String[] arg = new java.lang.String[]{java.lang.String.valueOf(grid),
                java.lang.String.valueOf(col), java.lang.String.valueOf(row)};

            cursor = this.queryDistinct(/* selection => */ sel, /* selectionArgs => */ arg);
            if (cursor != null)
            {
                boolean ret = false;
                if (cursor.moveToFirst())
                {
                    this.copy(cursor);
                    ret = true;
                }
                cursor.close();
                return ret;
            }
        }
        catch (java.lang.Exception e) { if (cursor != null) cursor.close(); }
        return false;
    }

    public boolean deleteByGrid(final long grid)
    {
        org.wheatgenetics.coordinate.database.Entry.sendInfoLogMsg("Deleting from table " +
            org.wheatgenetics.coordinate.database.Entry.TABLE_NAME + " on id = " + grid);
        return this.delete(/* whereClause => */
            org.wheatgenetics.coordinate.database.Entry.GRID_FIELD_NAME + "=" + grid);
    }
    // endregion
}