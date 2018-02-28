package org.wheatgenetics.coordinate.database;

/**
 * Uses:
 * android.content.ContentValues
 * android.content.Context
 * android.database.Cursor
 *
 * org.wheatgenetics.coordinate.model.Model
 * org.wheatgenetics.coordinate.model.ProjectModel
 * org.wheatgenetics.coordinate.model.ProjectModels
 *
 * org.wheatgenetics.coordinate.database.Table
 */
public class ProjectsTable extends org.wheatgenetics.coordinate.database.Table
{
    // region Constants
    private static final java.lang.String TABLE_NAME       = "projects"                            ;
    private static final java.lang.String TITLE_FIELD_NAME = "title"   , STAMP_FIELD_NAME = "stamp";
    // endregion

    private org.wheatgenetics.coordinate.model.ProjectModels makeProjectModels(
    final android.database.Cursor cursor)
    {
        final org.wheatgenetics.coordinate.model.ProjectModels result;
        if (null == cursor)
            result = null;
        else
            try
            {
                if (cursor.getCount() <= 0)
                    result = null;
                else
                {
                    result = new org.wheatgenetics.coordinate.model.ProjectModels();
                    while (cursor.moveToNext()) result.add(
                        (org.wheatgenetics.coordinate.model.ProjectModel) this.make(cursor));
                }
            }
            finally { cursor.close(); }
        return result;
    }

    public ProjectsTable(final android.content.Context context)
    {
        super(
            /* context   => */ context                                                       ,
            /* tableName => */ org.wheatgenetics.coordinate.database.ProjectsTable.TABLE_NAME,
            /* tag       => */ "ProjectsTable"                                               );
    }

    // region Overridden Methods
    @java.lang.Override
    org.wheatgenetics.coordinate.model.Model make(final android.database.Cursor cursor)
    {
        return null == cursor ? null : new org.wheatgenetics.coordinate.model.ProjectModel(
            /* id => */ cursor.getLong(cursor.getColumnIndex(
                org.wheatgenetics.coordinate.database.Table.ID_FIELD_NAME)),
            /* title => */ cursor.getString(cursor.getColumnIndex(
                org.wheatgenetics.coordinate.database.ProjectsTable.TITLE_FIELD_NAME)),
            /* timestamp => */ cursor.getLong(cursor.getColumnIndex(
                org.wheatgenetics.coordinate.database.ProjectsTable.STAMP_FIELD_NAME)));
    }

    @java.lang.Override
    android.content.ContentValues getContentValuesForInsert(
    final org.wheatgenetics.coordinate.model.Model model)
    {
        final android.content.ContentValues result = super.getContentValuesForInsert(model);
        {
            final org.wheatgenetics.coordinate.model.ProjectModel projectModel =
                (org.wheatgenetics.coordinate.model.ProjectModel) model;

            assert null != projectModel;
            result.put(org.wheatgenetics.coordinate.database.ProjectsTable.TITLE_FIELD_NAME,
                projectModel.getTitle());
            result.put(org.wheatgenetics.coordinate.database.ProjectsTable.STAMP_FIELD_NAME,
                projectModel.getTimestamp());
        }
        return result;
    }
    // endregion

    // region Public Methods
    public boolean exists()
    {
        return org.wheatgenetics.coordinate.database.Table.exists(this.rawQuery(
            "SELECT ALL * FROM " + org.wheatgenetics.coordinate.database.ProjectsTable.TABLE_NAME));
    }

    public org.wheatgenetics.coordinate.model.ProjectModels load()
    {
        return this.makeProjectModels(this.queryAll(/* orderBy => */
            org.wheatgenetics.coordinate.database.ProjectsTable.TITLE_FIELD_NAME + " ASC, " +
            org.wheatgenetics.coordinate.database.Table.ID_FIELD_NAME            + " ASC"   ));
    }

    public org.wheatgenetics.coordinate.model.ProjectModels loadProjectsWithGrids()
    {
        return this.makeProjectModels(this.queryDistinct(/* selection => */
            org.wheatgenetics.coordinate.database.Table.ID_FIELD_NAME + " IN (SELECT DI" +
            "STINCT projectId FROM grids WHERE projectId IS NOT NULL AND projectId > 0)"));
    }
    // endregion
}