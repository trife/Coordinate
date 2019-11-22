package org.wheatgenetics.coordinate.database;

/**
 * Uses:
 * android.content.ContentValues
 * android.content.Context
 * android.database.Cursor
 * android.database.CursorWrapper
 * android.support.annotation.NonNull
 * android.support.annotation.Nullable
 *
 * org.wheatgenetics.javalib.Utils
 *
 * org.wheatgenetics.coordinate.model.Model
 * org.wheatgenetics.coordinate.model.TemplateModel
 * org.wheatgenetics.coordinate.model.TemplateModels
 * org.wheatgenetics.coordinate.model.TemplateType
 *
 * org.wheatgenetics.coordinate.database.Table
 */
public class TemplatesTable extends org.wheatgenetics.coordinate.database.Table
{
    // region Constants
    static final java.lang.String TABLE_NAME = "templates";

    static final java.lang.String TITLE_FIELD_NAME = "title", TYPE_FIELD_NAME   = "type"  ;
    static final java.lang.String ROWS_FIELD_NAME  = "rows" , COLS_FIELD_NAME   = "cols"  ;
    static final java.lang.String ERAND_FIELD_NAME = "erand", ECELLS_FIELD_NAME = "ecells",
        EROWS_FIELD_NAME = "erows", ECOLS_FIELD_NAME = "ecols";
    static final java.lang.String CNUMB_FIELD_NAME       = "cnumb", RNUMB_FIELD_NAME = "rnumb";
    static final java.lang.String ENTRY_LABEL_FIELD_NAME = "entryLabel"                       ;
    static final java.lang.String OPTIONS_FIELD_NAME     = "options"                          ;
    static final java.lang.String STAMP_FIELD_NAME       = "stamp"                            ;
    // endregion

    // region Private Methods
    private android.database.Cursor query(@android.support.annotation.NonNull
    final org.wheatgenetics.coordinate.model.TemplateType templateType)
    {
        return this.queryDistinct(/* selection => */
            org.wheatgenetics.coordinate.database.TemplatesTable.TYPE_FIELD_NAME + " = " +
                templateType.getCode());
    }

    @android.support.annotation.Nullable private org.wheatgenetics.coordinate.model.TemplateModels
    makeTemplateModels(@android.support.annotation.Nullable final android.database.Cursor cursor)
    {
        final org.wheatgenetics.coordinate.model.TemplateModels result;
        if (null == cursor)
            result = null;
        else
            try
            {
                if (cursor.getCount() <= 0)
                    result = null;
                else
                {
                    result = new org.wheatgenetics.coordinate.model.TemplateModels();
                    while (cursor.moveToNext()) result.add(
                        (org.wheatgenetics.coordinate.model.TemplateModel) this.make(cursor));
                }
            }
            finally { cursor.close(); }
        return result;
    }
    // endregion

    public TemplatesTable(final android.content.Context context)
    {
        super(
            /* context   => */ context                                                        ,
            /* tableName => */ org.wheatgenetics.coordinate.database.TemplatesTable.TABLE_NAME,
            /* tag       => */"TemplatesTable");
    }

    // region Overridden Methods
    @java.lang.Override org.wheatgenetics.coordinate.model.Model make(
    final android.database.Cursor cursor)
    {
        if (null == cursor)
            return null;
        else
        {
            class CursorWrapper extends android.database.CursorWrapper
            {
                private CursorWrapper(
                @android.support.annotation.NonNull final android.database.Cursor cursor)
                { super(cursor); }

                // region get() Methods
                private long id()
                {
                    return this.getLong(this.getColumnIndex(
                        org.wheatgenetics.coordinate.database.TemplatesTable.ID_FIELD_NAME));
                }

                private java.lang.String title()
                {
                    return this.getString(this.getColumnIndex(
                        org.wheatgenetics.coordinate.database.TemplatesTable.TITLE_FIELD_NAME));
                }

                private int code()
                {
                    return this.getInt(this.getColumnIndex(
                        org.wheatgenetics.coordinate.database.TemplatesTable.TYPE_FIELD_NAME));
                }

                private int rows()
                {
                    return this.getInt(this.getColumnIndex(
                        org.wheatgenetics.coordinate.database.TemplatesTable.ROWS_FIELD_NAME));
                }

                private int cols()
                {
                    return this.getInt(this.getColumnIndex(
                        org.wheatgenetics.coordinate.database.TemplatesTable.COLS_FIELD_NAME));
                }

                private int generatedExcludedCellsAmount()
                {
                    return this.getInt(this.getColumnIndex(
                        org.wheatgenetics.coordinate.database.TemplatesTable.ERAND_FIELD_NAME));
                }

                private java.lang.String excludeCells()
                {
                    return this.getString(this.getColumnIndex(
                        org.wheatgenetics.coordinate.database.TemplatesTable.ECELLS_FIELD_NAME));
                }

                private java.lang.String excludeRows()
                {
                    return this.getString(this.getColumnIndex(
                        org.wheatgenetics.coordinate.database.TemplatesTable.EROWS_FIELD_NAME));
                }

                private java.lang.String excludeCols()
                {
                    return this.getString(this.getColumnIndex(
                        org.wheatgenetics.coordinate.database.TemplatesTable.ECOLS_FIELD_NAME));
                }

                private int colNumbering()
                {
                    return this.getInt(this.getColumnIndex(
                        org.wheatgenetics.coordinate.database.TemplatesTable.CNUMB_FIELD_NAME));
                }

                private int rowNumbering()
                {
                    return this.getInt(this.getColumnIndex(
                        org.wheatgenetics.coordinate.database.TemplatesTable.RNUMB_FIELD_NAME));
                }

                private java.lang.String entryLabel()
                {
                    return this.getString(this.getColumnIndex(org.wheatgenetics
                        .coordinate.database.TemplatesTable.ENTRY_LABEL_FIELD_NAME));
                }

                private java.lang.String optionalFields()
                {
                    return this.getString(this.getColumnIndex(
                        org.wheatgenetics.coordinate.database.TemplatesTable.OPTIONS_FIELD_NAME));
                }

                private long timestamp()
                {
                    return this.getLong(this.getColumnIndex(
                        org.wheatgenetics.coordinate.database.TemplatesTable.STAMP_FIELD_NAME));
                }
                // endregion
            }
            final CursorWrapper cursorWrapper = new CursorWrapper(cursor);
            return new org.wheatgenetics.coordinate.model.TemplateModel(
                /* id                           => */ cursorWrapper.id   (),
                /* title                        => */ cursorWrapper.title(),
                /* code                         => */ cursorWrapper.code (),
                /* rows                         => */ cursorWrapper.rows (),
                /* cols                         => */ cursorWrapper.cols (),
                /* generatedExcludedCellsAmount => */
                    cursorWrapper.generatedExcludedCellsAmount(),
                /* excludeCells   => */ cursorWrapper.excludeCells  (),
                /* excludeRows    => */ cursorWrapper.excludeRows   (),
                /* excludeCols    => */ cursorWrapper.excludeCols   (),
                /* colNumbering   => */ cursorWrapper.colNumbering  (),
                /* rowNumbering   => */ cursorWrapper.rowNumbering  (),
                /* entryLabel     => */ cursorWrapper.entryLabel    (),
                /* optionalFields => */ cursorWrapper.optionalFields(),
                /* timestamp      => */ cursorWrapper.timestamp     ());
        }
    }

    @java.lang.Override @android.support.annotation.NonNull
    android.content.ContentValues getContentValuesForInsert(
    @android.support.annotation.NonNull final org.wheatgenetics.coordinate.model.Model model)
    {
        final android.content.ContentValues result = super.getContentValuesForInsert(model);
        {
            final org.wheatgenetics.coordinate.model.TemplateModel templateModel =
                (org.wheatgenetics.coordinate.model.TemplateModel) model;

            result.put(org.wheatgenetics.coordinate.database.TemplatesTable.TITLE_FIELD_NAME,
                templateModel.getTitle());
            result.put(org.wheatgenetics.coordinate.database.TemplatesTable.TYPE_FIELD_NAME,
                templateModel.getType().getCode());
            result.put(org.wheatgenetics.coordinate.database.TemplatesTable.ROWS_FIELD_NAME,
                templateModel.getRows());
            result.put(org.wheatgenetics.coordinate.database.TemplatesTable.COLS_FIELD_NAME,
                templateModel.getCols());

            result.put(org.wheatgenetics.coordinate.database.TemplatesTable.ERAND_FIELD_NAME,
                templateModel.getGeneratedExcludedCellsAmount());
            result.put(org.wheatgenetics.coordinate.database.TemplatesTable.ECELLS_FIELD_NAME,
                templateModel.getExcludedCellsAsJson());
            result.put(org.wheatgenetics.coordinate.database.TemplatesTable.EROWS_FIELD_NAME,
                templateModel.getExcludedRowsAsJson());
            result.put(org.wheatgenetics.coordinate.database.TemplatesTable.ECOLS_FIELD_NAME,
                templateModel.getExcludedColsAsJson());

            result.put(org.wheatgenetics.coordinate.database.TemplatesTable.CNUMB_FIELD_NAME,
                templateModel.getColNumbering());
            result.put(org.wheatgenetics.coordinate.database.TemplatesTable.RNUMB_FIELD_NAME,
                templateModel.getRowNumbering());

            result.put(org.wheatgenetics.coordinate.database.TemplatesTable.ENTRY_LABEL_FIELD_NAME,
                templateModel.getEntryLabel());

            result.put(org.wheatgenetics.coordinate.database.TemplatesTable.OPTIONS_FIELD_NAME,
                templateModel.optionalFieldsAsJson());

            result.put(org.wheatgenetics.coordinate.database.TemplatesTable.STAMP_FIELD_NAME,
                templateModel.getTimestamp());
        }
        return result;
    }
    // endregion

    // region Operations
    public boolean exists(@android.support.annotation.NonNull
    final org.wheatgenetics.coordinate.model.TemplateType templateType)
    {
        return org.wheatgenetics.coordinate.database.TemplatesTable.exists(
            this.query(templateType));
    }

    @android.support.annotation.Nullable
    public org.wheatgenetics.coordinate.model.TemplateModel get(final long id)
    {
        return (org.wheatgenetics.coordinate.model.TemplateModel) this.makeFromFirst(this.queryAll(
            /* selection => */ org.wheatgenetics.coordinate.database.TemplatesTable.whereClause(),
            /* selectionArgs => */ org.wheatgenetics.javalib.Utils.stringArray(id)               ));
    }

    @android.support.annotation.Nullable
    public org.wheatgenetics.coordinate.model.TemplateModel get(@android.support.annotation.NonNull
    final org.wheatgenetics.coordinate.model.TemplateType templateType)
    {
        return (org.wheatgenetics.coordinate.model.TemplateModel)
            this.makeFromFirst(this.query(templateType));
    }

    @android.support.annotation.Nullable
    public org.wheatgenetics.coordinate.model.TemplateModels load()
    {
        return this.makeTemplateModels(this.queryAll(/* orderBy => */
            org.wheatgenetics.coordinate.database.TemplatesTable.TYPE_FIELD_NAME + " ASC"));
    }

    @android.support.annotation.Nullable
    public org.wheatgenetics.coordinate.model.TemplateModels loadUserDefined()
    {
        return this.makeTemplateModels(this.query(
            org.wheatgenetics.coordinate.model.TemplateType.USERDEFINED));
    }
    // endregion
}