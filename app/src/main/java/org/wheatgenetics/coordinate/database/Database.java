package org.wheatgenetics.coordinate.database;

/**
 * Uses:
 * android.content.Context
 * android.database.sqlite.SQLiteDatabase
 * android.database.sqlite.SQLiteOpenHelper
 * android.util.Log
 *
 * org.w3c.dom.Document
 * org.w3c.dom.NodeList
 *
 * org.xml.sax.SAXException
 *
 * org.wheatgenetics.coordinate.R
 */

public class Database extends java.lang.Object
{
    private static android.database.sqlite.SQLiteDatabase db = null;

    public static void initialize(final android.content.Context context)
    {
        if (null != org.wheatgenetics.coordinate.database.Database.db)
            org.wheatgenetics.coordinate.database.Database.db.close();

        class SQLiteOpenHelper extends android.database.sqlite.SQLiteOpenHelper
        {
            private static final java.lang.String TAG = "SQLiteOpenHelper";

            // region Fields
            private final android.content.Context context                                      ;
            private       boolean                 createNeeded = false, createSucceeded = false;
            // endregion

            SQLiteOpenHelper(final android.content.Context context)
            {
                super(
                    /* context => */ context       ,
                    /* name    => */ "seedtray1.db",
                    /* factory => */ null          ,
                    /* version => */ 1             );
                this.context = context;
            }

            // region Overridden Methods
            @java.lang.Override
            public void onCreate(final android.database.sqlite.SQLiteDatabase db)
            {
                this.createNeeded = true;
                {
                    org.w3c.dom.NodeList statementNodeList;
                    {
                        org.w3c.dom.Document document;
                        {
                            assert null != this.context;
                            final java.io.InputStream inputStream =
                                this.context.getResources().openRawResource(
                                    org.wheatgenetics.coordinate.R.raw.sql);
                            try
                            {
                                final javax.xml.parsers.DocumentBuilder documentBuilder =
                                    javax.xml.parsers.DocumentBuilderFactory.newInstance()
                                        .newDocumentBuilder();       // throws java.xml.parsers.Par-
                                assert null != documentBuilder;      //  serConfigurationException
                                try
                                {
                                    document = documentBuilder.parse(   // throws org.xml.sax.SAXEx-
                                        /* is       => */ inputStream,  //  ception, java.io.IOEx-
                                        /* systemId => */ null       ); //  ception
                                }
                                catch (final org.xml.sax.SAXException | java.io.IOException e)
                                { return; }           // this.createSucceeded will not be made true.
                            }
                            catch (final javax.xml.parsers.ParserConfigurationException e)
                            { return; }               // this.createSucceeded will not be made true.
                        }
                        assert null != document;
                        statementNodeList = document.getElementsByTagName("statement");
                    }
                    assert null != statementNodeList;
                    assert null != db               ;
                    {
                        java.lang.String statement;
                        for (int i = 0; i < statementNodeList.getLength(); i++)
                        {
                            statement =
                                statementNodeList.item(i).getChildNodes().item(0).getNodeValue();
                            android.util.Log.i(SQLiteOpenHelper.TAG, "statement: " + statement);
                            db.execSQL(statement);
                        }
                    }
                }
                this.createSucceeded = true;
            }

            @java.lang.Override
            public void onUpgrade(final android.database.sqlite.SQLiteDatabase db,
            final int oldVersion, final int newVersion)
            {
                android.util.Log.w(TAG, "Upgrading database from version " + oldVersion +
                    " to " + newVersion + ", which will destroy all old data");
                assert null != db;
                db.execSQL("DROP TABLE IF EXISTS entries");            // TODO: What about templates
                this.onCreate(db);                                     // TODO:  and grids tables?
            }

            @java.lang.Override
            public android.database.sqlite.SQLiteDatabase getReadableDatabase()
            {
                if (this.createNeeded)
                    if (this.createSucceeded) return super.getReadableDatabase(); else return null;
                else
                    return super.getReadableDatabase();
            }

            @java.lang.Override
            public android.database.sqlite.SQLiteDatabase getWritableDatabase()
            {
                if (this.createNeeded)
                    if (this.createSucceeded) return super.getWritableDatabase(); else return null;
                else
                    return super.getWritableDatabase();
            }
            // endregion
        }
        org.wheatgenetics.coordinate.database.Database.db =
            new SQLiteOpenHelper(context).getWritableDatabase();
    }

    static android.database.sqlite.SQLiteDatabase getDb(final android.content.Context context)
    {
        if (null == org.wheatgenetics.coordinate.database.Database.db)
        {
            org.wheatgenetics.coordinate.database.Database.initialize(context);
            assert null != org.wheatgenetics.coordinate.database.Database.db;
        }
        return org.wheatgenetics.coordinate.database.Database.db;
    }
}