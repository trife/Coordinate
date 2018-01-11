package org.wheatgenetics.coordinate.database;

/**
 * Uses:
 * android.content.ContentValues
 * android.content.Context
 * android.database.Cursor
 *
 * org.wheatgenetics.javalib.Utils
 *
 * org.wheatgenetics.coordinate.model.GridModel
 * org.wheatgenetics.coordinate.model.JoinedGridModel
 * org.wheatgenetics.coordinate.model.JoinedGridModels
 * org.wheatgenetics.coordinate.model.Model
 *
 * org.wheatgenetics.coordinate.database.EntriesTable
 * org.wheatgenetics.coordinate.database.Table
 * org.wheatgenetics.coordinate.database.TemplatesTable
 */
public class GridsTable extends org.wheatgenetics.coordinate.database.Table
{
    // region Constants
    private static final java.lang.String TABLE_NAME = "grids";

    private static final java.lang.String TEMP_FIELD_NAME = "temp",
        PERSON_FIELD_NAME = "person", ECELLS_FIELD_NAME = "ecells", OPTIONS_FIELD_NAME = "options",
        STAMP_FIELD_NAME = "stamp";
    private static final java.lang.String TEMPLATETYPE_FIELD_NAME = "templateType",
        TEMPLATEECELLS_FIELD_NAME = "templateEcells",
        TEMPLATEOPTIONS_FIELD_NAME = "templateOptions", TEMPLATESTAMP_FIELD_NAME = "templateStamp";
    // endregion

    // region Fields
    private static java.lang.String idFieldName, joinedQuery;

    private final android.content.Context                            context                    ;
    private       org.wheatgenetics.coordinate.database.EntriesTable entriesTableInstance = null;
    // endregion

    private org.wheatgenetics.coordinate.database.EntriesTable entriesTable()
    {
        if (null == this.entriesTableInstance) this.entriesTableInstance =
            new org.wheatgenetics.coordinate.database.EntriesTable(this.context);
        return this.entriesTableInstance;
    }

    public GridsTable(final android.content.Context context)
    {
        super(
            /* context   => */ context                                                    ,
            /* tableName => */ org.wheatgenetics.coordinate.database.GridsTable.TABLE_NAME,
            /* tag       => */ "GridsTable"                                               );

        this.context = context;

        final java.lang.String
            gridsTableName     = org.wheatgenetics.coordinate.database.GridsTable.TABLE_NAME    ,
            templatesTableName = org.wheatgenetics.coordinate.database.TemplatesTable.TABLE_NAME;
        final java.lang.String
            gridsQualifier = gridsTableName + '.', templatesQualifier = templatesTableName + '.';
        org.wheatgenetics.coordinate.database.GridsTable.idFieldName = gridsQualifier +
            org.wheatgenetics.coordinate.database.Table.ID_FIELD_NAME;
        final java.lang.String
            personFieldName = gridsQualifier +
                org.wheatgenetics.coordinate.database.GridsTable.PERSON_FIELD_NAME,
            ecellsFieldName = gridsQualifier +
                org.wheatgenetics.coordinate.database.GridsTable.ECELLS_FIELD_NAME,
            optionsFieldName = gridsQualifier +
                org.wheatgenetics.coordinate.database.GridsTable.OPTIONS_FIELD_NAME,
            stampFieldName = gridsQualifier +
                org.wheatgenetics.coordinate.database.GridsTable.STAMP_FIELD_NAME,
            tempFieldName = gridsQualifier +
                org.wheatgenetics.coordinate.database.GridsTable.TEMP_FIELD_NAME,

            titleFieldName = templatesQualifier +
                org.wheatgenetics.coordinate.database.TemplatesTable.TITLE_FIELD_NAME,
            templateTypeFieldName = templatesQualifier +
                org.wheatgenetics.coordinate.database.TemplatesTable.TYPE_FIELD_NAME,
            rowsFieldName = templatesQualifier +
                org.wheatgenetics.coordinate.database.TemplatesTable.ROWS_FIELD_NAME,
            colsFieldName = templatesQualifier +
                org.wheatgenetics.coordinate.database.TemplatesTable.COLS_FIELD_NAME,
            erandFieldName = templatesQualifier +
                org.wheatgenetics.coordinate.database.TemplatesTable.ERAND_FIELD_NAME,
            templateEcellsFieldName = templatesQualifier +
                org.wheatgenetics.coordinate.database.TemplatesTable.ECELLS_FIELD_NAME,
            erowsFieldName = templatesQualifier +
                org.wheatgenetics.coordinate.database.TemplatesTable.EROWS_FIELD_NAME,
            ecolsFieldName = templatesQualifier +
                org.wheatgenetics.coordinate.database.TemplatesTable.ECOLS_FIELD_NAME,
            cnumbFieldName = templatesQualifier +
                org.wheatgenetics.coordinate.database.TemplatesTable.CNUMB_FIELD_NAME,
            rnumbFieldName = templatesQualifier +
                org.wheatgenetics.coordinate.database.TemplatesTable.RNUMB_FIELD_NAME,
            templateOptionsFieldName = templatesQualifier +
                org.wheatgenetics.coordinate.database.TemplatesTable.OPTIONS_FIELD_NAME,
            templateStampFieldName = templatesQualifier +
                org.wheatgenetics.coordinate.database.TemplatesTable.STAMP_FIELD_NAME,
            templateIdFieldName = templatesQualifier +
                org.wheatgenetics.coordinate.database.Table.ID_FIELD_NAME;
        org.wheatgenetics.coordinate.database.GridsTable.joinedQuery = "SELECT ALL " +
            org.wheatgenetics.coordinate.database.GridsTable.idFieldName + ", " +
            personFieldName + ", " + ecellsFieldName + ", " + optionsFieldName + ", " +
            stampFieldName + ", " + tempFieldName + ", " +

            titleFieldName +  ", " +
            templateTypeFieldName + " AS " +
                org.wheatgenetics.coordinate.database.GridsTable.TEMPLATETYPE_FIELD_NAME + ", " +
            rowsFieldName + ", " + colsFieldName + ", " + erandFieldName + ", " +
            templateEcellsFieldName + " AS " +
                org.wheatgenetics.coordinate.database.GridsTable.TEMPLATEECELLS_FIELD_NAME + ", " +
            erowsFieldName + ", " + ecolsFieldName + ", " +
            cnumbFieldName + ", " + rnumbFieldName + ", " +
            templateOptionsFieldName + " AS " +
                org.wheatgenetics.coordinate.database.GridsTable.TEMPLATEOPTIONS_FIELD_NAME + ", " +
            templateStampFieldName + " AS " +
                org.wheatgenetics.coordinate.database.GridsTable.TEMPLATESTAMP_FIELD_NAME +
                    " FROM "  + gridsTableName + ", " + templatesTableName +
                    " WHERE " + tempFieldName + " = " + templateIdFieldName;
    }

    // region Overridden Methods
    @java.lang.Override
    org.wheatgenetics.coordinate.model.Model make(final android.database.Cursor cursor)
    {
        if (null == cursor)
            return null;
        else
        {
            final long gridId = cursor.getLong(cursor.getColumnIndex(
                org.wheatgenetics.coordinate.database.Table.ID_FIELD_NAME));
            final int
                rows = cursor.getInt(cursor.getColumnIndex(
                    org.wheatgenetics.coordinate.database.TemplatesTable.ROWS_FIELD_NAME)),
                cols = cursor.getInt(cursor.getColumnIndex(
                    org.wheatgenetics.coordinate.database.TemplatesTable.COLS_FIELD_NAME));

            return new org.wheatgenetics.coordinate.model.JoinedGridModel(
                /* id     => */ gridId,
                /* person => */ cursor.getString(cursor.getColumnIndex(
                    org.wheatgenetics.coordinate.database.GridsTable.PERSON_FIELD_NAME)),
                /* excludedCells => */ cursor.getString(cursor.getColumnIndex(
                    org.wheatgenetics.coordinate.database.GridsTable.ECELLS_FIELD_NAME)),
                /* optionalFields => */ cursor.getString(cursor.getColumnIndex(
                    org.wheatgenetics.coordinate.database.GridsTable.OPTIONS_FIELD_NAME)),
                /* timestamp => */ cursor.getLong(cursor.getColumnIndex(
                    org.wheatgenetics.coordinate.database.GridsTable.STAMP_FIELD_NAME)),

                /* templateId => */ cursor.getLong(cursor.getColumnIndex(
                    org.wheatgenetics.coordinate.database.GridsTable.TEMP_FIELD_NAME)),
                /* title => */ cursor.getString(cursor.getColumnIndex(
                    org.wheatgenetics.coordinate.database.TemplatesTable.TITLE_FIELD_NAME)),
                /* code => */ cursor.getInt(cursor.getColumnIndex(
                    org.wheatgenetics.coordinate.database.GridsTable.TEMPLATETYPE_FIELD_NAME)),
                /* rows                         => */ rows,
                /* cols                         => */ cols,
                /* generatedExcludedCellsAmount => */ cursor.getInt(cursor.getColumnIndex(
                    org.wheatgenetics.coordinate.database.TemplatesTable.ERAND_FIELD_NAME)),
                /* initialExcludedCells => */ cursor.getString(cursor.getColumnIndex(
                    org.wheatgenetics.coordinate.database.TemplatesTable.ECELLS_FIELD_NAME)),
                /* excludedRows => */ cursor.getString(cursor.getColumnIndex(
                    org.wheatgenetics.coordinate.database.TemplatesTable.EROWS_FIELD_NAME)),
                /* excludedCols => */ cursor.getString(cursor.getColumnIndex(
                    org.wheatgenetics.coordinate.database.TemplatesTable.ECOLS_FIELD_NAME)),
                /* colNumbering => */ cursor.getInt(cursor.getColumnIndex(
                    org.wheatgenetics.coordinate.database.TemplatesTable.CNUMB_FIELD_NAME)),
                /* rowNumbering => */ cursor.getInt(cursor.getColumnIndex(
                    org.wheatgenetics.coordinate.database.TemplatesTable.RNUMB_FIELD_NAME)),
                /* templateOptionalFields => */ cursor.getString(cursor.getColumnIndex(
                    org.wheatgenetics.coordinate.database.TemplatesTable.OPTIONS_FIELD_NAME)),
                /* templateTimestamp => */ cursor.getLong(cursor.getColumnIndex(
                    org.wheatgenetics.coordinate.database.GridsTable.TEMPLATESTAMP_FIELD_NAME)),

                /* entryModels => */ this.entriesTable().load(gridId, rows, cols));
        }
    }

    @java.lang.Override
    android.content.ContentValues getContentValuesForInsert(
    final org.wheatgenetics.coordinate.model.Model model)
    {
        final android.content.ContentValues result = super.getContentValuesForInsert(model);
        {
            final org.wheatgenetics.coordinate.model.GridModel gridModel =
                (org.wheatgenetics.coordinate.model.GridModel) model;

            assert null != gridModel;
            result.put(org.wheatgenetics.coordinate.database.GridsTable.TEMP_FIELD_NAME,
                gridModel.getTemplateId());
            result.put(org.wheatgenetics.coordinate.database.GridsTable.PERSON_FIELD_NAME,
                gridModel.getPerson());
            result.put(org.wheatgenetics.coordinate.database.GridsTable.ECELLS_FIELD_NAME,
                gridModel.excludedCellsAsJson());
            result.put(org.wheatgenetics.coordinate.database.GridsTable.OPTIONS_FIELD_NAME,
                gridModel.optionalFieldsAsJson());
            result.put(org.wheatgenetics.coordinate.database.GridsTable.STAMP_FIELD_NAME,
                gridModel.getTimestamp());
        }
        return result;
    }
    // endregion

    // region Operations
    public boolean exists(final long id)
    {
        return org.wheatgenetics.coordinate.database.Table.exists(this.queryDistinct(
            /* selection => */ org.wheatgenetics.coordinate.database.Table.whereClause(id)));
    }

    public org.wheatgenetics.coordinate.model.JoinedGridModel get(final long id)
    {
        return (org.wheatgenetics.coordinate.model.JoinedGridModel) this.makeFromFirst(
            this.rawQuery(
                /* sql => */ org.wheatgenetics.coordinate.database.GridsTable.joinedQuery +
                    " AND " + org.wheatgenetics.coordinate.database.GridsTable.idFieldName + " = ?",
                /* selectionArgs => */ org.wheatgenetics.javalib.Utils.stringArray(id)));
    }

    public boolean exists()
    {
        return org.wheatgenetics.coordinate.database.Table.exists(this.rawQuery(
            "SELECT ALL * FROM " + org.wheatgenetics.coordinate.database.GridsTable.TABLE_NAME));
    }

    public boolean deleteByTemplateId(final long templateId)
    {
        return this.deleteUsingWhereClause(/* whereClause => */
            org.wheatgenetics.coordinate.database.GridsTable.TEMP_FIELD_NAME + " = " + templateId);
    }

    public org.wheatgenetics.coordinate.model.JoinedGridModels load()
    {
        final org.wheatgenetics.coordinate.model.JoinedGridModels result;
        {
            final android.database.Cursor cursor = this.rawQuery(/* sql => */
                org.wheatgenetics.coordinate.database.GridsTable.joinedQuery);
            if (null == cursor)
                result = null;
            else
                try
                {
                    if (cursor.getCount() <= 0)
                        result = null;
                    else
                    {
                        result = new org.wheatgenetics.coordinate.model.JoinedGridModels();
                        while (cursor.moveToNext()) result.add(
                            (org.wheatgenetics.coordinate.model.JoinedGridModel) this.make(cursor));
                    }
                }
                finally { cursor.close(); }
        }
        return result;
    }
    // endregion
}